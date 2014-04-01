package net.terzeron.customdialogtest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	AlertDialog.Builder builder;
	AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new ButtonClickHandler());
    }

    public class ButtonClickHandler implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		Context mContext = MainActivity.this;
    		
    		// 레이아웃은 직접 만들 수 없고 시스템서비스인 레이아웃 인플레이터를 이용해야 함
    		// 이 레이아웃을 얼럿다이알로그에 사용함
    		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    		View layout = inflater.inflate(R.layout.customdialog, (ViewGroup) findViewById(R.id.layout_root));
    		
    		// 텍스트뷰와 이미지뷰를 레이아웃에 배치
    		TextView text = (TextView) layout.findViewById(R.id.text);
    		text.setText("This is a custom dialog");
    		ImageView image = (ImageView) layout.findViewById(R.id.image);
    		image.setImageResource(R.drawable.ic_launcher);
    		
    		// 커스텀 대화창을 만듬
    		builder = new AlertDialog.Builder(mContext);
    		builder.setView(layout);
    		alertDialog = builder.create();
    		alertDialog.show();
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
