package net.terzeron.servicetest;

import com.example.net.terzeron.servicetest.R;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class Mp3Service extends Service {
	private MediaPlayer mp = null;

	public void onCreate() {
		Toast.makeText(this, "서비스 생성", Toast.LENGTH_SHORT).show();
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
		Notification notification = new Notification(R.drawable.ic_launcher,
				getText(R.string.app_name), System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, Mp3Service.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(this, "Mp3Sample", "das beer boot ",
				pendingIntent);
		startForeground(startId, notification);
		playSong();
		return START_STICKY;
	}

	private void playSong() {
		try {
			mp = MediaPlayer.create(this, R.raw.das_beer_boot);
			mp.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onDestroy() {
		mp.stop();
		Toast.makeText(this, "서비스 종료", Toast.LENGTH_SHORT).show();
	}
}
