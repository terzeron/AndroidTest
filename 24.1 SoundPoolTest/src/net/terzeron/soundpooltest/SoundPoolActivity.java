package net.terzeron.soundpooltest;

import com.example.ch24_1.R;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * p. 664
 */
public class SoundPoolActivity extends Activity implements OnTouchListener {
	private SoundPool mSoundPool;
	private int mSoundID;
	boolean mIsloaded = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.soundpool_activity);
		View view = findViewById(R.id.textView1);
		view.setOnTouchListener(this);
		
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		mSoundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				mIsloaded = true;
			}
		});
		mSoundID = mSoundPool.load(this, R.raw.siren, 1);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
			float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
			float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			float volume = actualVolume / maxVolume;
			if (mIsloaded) {
				mSoundPool.play(mSoundID, volume, volume, 1, 0, 1f);
			}
		}
		return false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mSoundPool.release();
		mSoundPool = null;
	}
}
