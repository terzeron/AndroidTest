package net.terzeron.ttstest;

import java.util.Locale;
import java.util.Random;

import com.example.ch24_5.R;


import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * p. 701 ~ 702
 */
public class Text2SpeechActivity extends Activity  
                       implements TextToSpeech.OnInitListener {
    private static final String TAG = "TextToSpeech";
    private TextToSpeech mTts;
    private Button mPlayButton;
    private EditText mText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text2speech_activity);
        
        // TTS 인스턴스를 만든다 
        mTts = new TextToSpeech(this, this);
        // 버튼의 클릭핸들러를 정의한다
        mPlayButton = (Button) findViewById(R.id.play);
        mText = (EditText) findViewById(R.id.text);
        mPlayButton.setOnClickListener(
                                   new View.OnClickListener() {
            public void onClick(View v) {
                sayIt();
            }
        });
    }
    @Override
    public void onDestroy() {
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }
    
    // TextToSpeech 초기화 완료 시 콜백 호출
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = mTts.setLanguage(Locale.KOREA);
            if (result == TextToSpeech.LANG_MISSING_DATA 
                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            } else {
                mPlayButton.setEnabled(true);
            }
        } else {
            Log.e(TAG, "TextToSpeech 초기화 에러!");
        }
    }

    private void sayIt() {
        String it = mText.getText().toString();
        mTts.speak(it, TextToSpeech.QUEUE_FLUSH, null);
    }
}
