package net.terzeron.mapviewtest;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements LocationListener {
	private GoogleMap map;
	private static final LatLng ROMA = new LatLng(42.093230818037,11.7971813678741);
	private LocationManager locationManager;
	private String provider;
	private Marker startPerc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabledGPS = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
		boolean enabledWiFi = service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		// GPS가 켜져있고 사용자에게 GPS 설정을 보내지 않는다면 대화상자를 보여주고
		// 설정 메뉴로 이동함
		if (!enabledGPS) {
			Toast.makeText(this, "GPS signal not found", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}
		
		// 지역제공자를 선택하는 방법에 대한 기준을 정의
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);
		
		// 위치 필드를 초기화
		if (location != null) {
			Toast.makeText(this, "Selected Provider " + provider, Toast.LENGTH_SHORT).show();
			onLocationChanged(location);
		} else {
			Toast.makeText(this, "not available location", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}
	
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}
	
	public void onLocationChanged(Location location) {
		double lat = location.getLatitude();
		double lng = location.getLongitude();
		Toast.makeText(this, "Location " + lat + ", " + lng, Toast.LENGTH_LONG).show();
		LatLng coordinate = new LatLng(lat, lng);
		Toast.makeText(this, "Location " + coordinate.latitude + ", " + coordinate.longitude, Toast.LENGTH_LONG).show();
		if (startPerc != null) {
			startPerc.remove();
		}
		startPerc = map.addMarker(new MarkerOptions().position(coordinate).title("Start").snippet("Inizio del percorso").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));

		CameraUpdate center = CameraUpdateFactory.newLatLng(coordinate);
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);
		map.moveCamera(center);
		map.animateCamera(zoom);
	}

	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled new provider " + provider, Toast.LENGTH_SHORT).show();
	}
	
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider, Toast.LENGTH_SHORT).show();
	}
	
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// ...
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
