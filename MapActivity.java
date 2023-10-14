package com.my.blooddonor;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import java.text.DecimalFormat;

public class MapActivity extends AppCompatActivity {
	
	
	private double lat = 0;
	private double lng = 0;
	private double lat1 = 0;
	private double lng1 = 0;
	
	private Button button1;
	private  mapview1;
	
	private AlertDialog.Builder d;
	private SharedPreferences pref;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.map);
		initialize();
		initializeLogic();
	}
	
	private void initialize() {
		
		button1 = (Button) findViewById(R.id.button1);
		mapview1 = () findViewById(R.id.mapview1);
		d = new AlertDialog.Builder(this);
		pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
	private void initializeLogic() {
		d.setMessage("Turn on GPS location and Wait for map to show your location");
		d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		d.create().show();
	}
	
	private void _extra () {
	} com.google.android.gms.maps.GoogleMap gmap; com.google.android.gms.maps.Projection proj;
	 @Override public boolean dispatchTouchEvent(MotionEvent ev) { int actionType = ev.getAction(); switch (actionType) { case MotionEvent.ACTION_UP: proj = gmap.getProjection(); com.google.android.gms.maps.model.LatLng loc = proj.fromScreenLocation(new Point ((int)ev.getX(), (int)ev.getY())); lat1 = loc.latitude; lng1 = loc.longitude;
			
			pref.edit().putString("lat", String.valueOf(lat1)).commit();
			pref.edit().putString("lng", String.valueOf(lng1)).commit();
		} return super.dispatchTouchEvent(ev);
	}
	{
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
