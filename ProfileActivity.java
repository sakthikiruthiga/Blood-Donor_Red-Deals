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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {
	
	
	private LinearLayout linear10;
	private TextView textview15;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear9;
	private LinearLayout linear8;
	private TextView textview1;
	private TextView textview6;
	private TextView textview2;
	private TextView textview11;
	private TextView textview3;
	private TextView textview12;
	private TextView textview4;
	private TextView textview13;
	private TextView textview5;
	private TextView textview14;
	private ImageView imageview1;
	private ImageView imageview2;
	private ImageView imageview3;
	
	private SharedPreferences ud;
	private Intent i = new Intent();
	private Intent i2 = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile);
		initialize();
		initializeLogic();
	}
	
	private void initialize() {
		
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		textview15 = (TextView) findViewById(R.id.textview15);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview6 = (TextView) findViewById(R.id.textview6);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview11 = (TextView) findViewById(R.id.textview11);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview12 = (TextView) findViewById(R.id.textview12);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview13 = (TextView) findViewById(R.id.textview13);
		textview5 = (TextView) findViewById(R.id.textview5);
		textview14 = (TextView) findViewById(R.id.textview14);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		ud = getSharedPreferences("ud", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("http://wa.me/91".concat(ud.getString("phone", ""))));
				startActivity(i);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				i.setAction(Intent.ACTION_CALL);
				i.setData(Uri.parse("tel:".concat(ud.getString("phone", ""))));
				startActivity(i);
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				i2.setClass(getApplicationContext(), Map2Activity.class);
				startActivity(i2);
			}
		});
	}
	private void initializeLogic() {
		textview6.setText(ud.getString("name", ""));
		textview11.setText(ud.getString("blood", ""));
		textview12.setText(ud.getString("age", ""));
		textview13.setText(ud.getString("email", ""));
		textview14.setText(ud.getString("phone", ""));
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
