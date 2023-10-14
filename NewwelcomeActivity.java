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
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;

public class NewwelcomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double n = 0;
	private double len = 0;
	private String prev = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> rem_list = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear6;
	private LinearLayout linear4;
	private TextView textview1;
	private LinearLayout linear11;
	private ImageView imageview1;
	private TextView textview2;
	private LinearLayout linear5;
	private LinearLayout linear9;
	private ImageView imageview2;
	private TextView textview3;
	private ImageView imageview3;
	private TextView textview4;
	private LinearLayout linear7;
	private LinearLayout linear10;
	private ImageView imageview4;
	private TextView textview5;
	private ImageView imageview5;
	private TextView textview6;
	
	private DatabaseReference user = _firebase.getReference("user");
	private ChildEventListener _user_child_listener;
	private SharedPreferences mail;
	private AlertDialog.Builder d;
	private Intent i = new Intent();
	private DatabaseReference rem = _firebase.getReference("rem");
	private ChildEventListener _rem_child_listener;
	private Calendar cal = Calendar.getInstance();
	private SharedPreferences rem_alert;
	private TimerTask t;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.newwelcome);
		initialize();
		initializeLogic();
	}
	
	private void initialize() {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		textview4 = (TextView) findViewById(R.id.textview4);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		textview6 = (TextView) findViewById(R.id.textview6);
		mail = getSharedPreferences("mail", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		rem_alert = getSharedPreferences("rem_alert", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				d.setTitle("Are you sure to logout?");
				d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
						mail.edit().putString("mail", "").commit();
						i.setClass(getApplicationContext(), LoginActivity.class);
						startActivity(i);
						SketchwareUtil.showMessage(getApplicationContext(), "Logged out");
					}
				});
				d.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				i.setClass(getApplicationContext(), WelcomeActivity.class);
				startActivity(i);
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				i.setClass(getApplicationContext(), ReminderActivity.class);
				startActivity(i);
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				i.setClass(getApplicationContext(), PharmacyActivity.class);
				startActivity(i);
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				i.setClass(getApplicationContext(), AppointmentActivity.class);
				startActivity(i);
			}
		});
		
		_user_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final String _errorCode = String.valueOf(_param1.getCode());
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		user.addChildEventListener(_user_child_listener);
		
		_rem_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final String _errorCode = String.valueOf(_param1.getCode());
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		rem.addChildEventListener(_rem_child_listener);
	}
	private void initializeLogic() {
		user.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listmap = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listmap.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				n = listmap.size() - 1;
				len = listmap.size();
				for(int _repeat40 = 0; _repeat40 < (int)(len); _repeat40++) {
					if (listmap.get((int)n).get("email").toString().equals(mail.getString("mail", ""))) {
						SketchwareUtil.showMessage(getApplicationContext(), "Welcome ".concat(listmap.get((int)n).get("name").toString()));
						textview2.setText("Hello ".concat(listmap.get((int)n).get("name").toString()));
						break;
					}
					n--;
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						rem.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								rem_list = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										rem_list.add(_map);
									}
								}
								catch (Exception _e) {
									_e.printStackTrace();
								}
								cal = Calendar.getInstance();
								n = rem_list.size() - 1;
								len = rem_list.size();
								for(int _repeat117 = 0; _repeat117 < (int)(len); _repeat117++) {
									if (rem_list.get((int)n).get("mail").toString().equals(mail.getString("mail", ""))) {
										if (rem_list.get((int)n).get("time").toString().equals(new SimpleDateFormat("hh:mm aaa").format(cal.getTime()))) {
											if (rem_list.get((int)n).get("time").toString().equals(prev)) {
												
											}
											else {
												prev = rem_list.get((int)n).get("time").toString();
												rem_alert.edit().putString("medicine", rem_list.get((int)n).get("medicine").toString()).commit();
												i.setClass(getApplicationContext(), RemAlertActivity.class);
												startActivity(i);
												break;
											}
										}
									}
									else {
										rem_list.remove((int)(n));
									}
									n--;
								}
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(t, (int)(1000), (int)(1000));
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						rem.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								rem_list = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										rem_list.add(_map);
									}
								}
								catch (Exception _e) {
									_e.printStackTrace();
								}
								cal = Calendar.getInstance();
								n = rem_list.size() - 1;
								len = rem_list.size();
								for(int _repeat18 = 0; _repeat18 < (int)(len); _repeat18++) {
									if (rem_list.get((int)n).get("mail").toString().equals(mail.getString("mail", ""))) {
										if (rem_list.get((int)n).get("time").toString().equals(new SimpleDateFormat("hh:mm aaa").format(cal.getTime()))) {
											if (rem_list.get((int)n).get("time").toString().equals(prev)) {
												
											}
											else {
												prev = rem_list.get((int)n).get("time").toString();
												rem_alert.edit().putString("medicine", rem_list.get((int)n).get("medicine").toString()).commit();
												i.setClass(getApplicationContext(), RemAlertActivity.class);
												startActivity(i);
												break;
											}
										}
									}
									else {
										rem_list.remove((int)(n));
									}
									n--;
								}
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(t, (int)(1000), (int)(1000));
	}
	
	@Override
	public void onResume() {
		super.onResume();
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						rem.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								rem_list = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										rem_list.add(_map);
									}
								}
								catch (Exception _e) {
									_e.printStackTrace();
								}
								cal = Calendar.getInstance();
								n = rem_list.size() - 1;
								len = rem_list.size();
								for(int _repeat47 = 0; _repeat47 < (int)(len); _repeat47++) {
									if (rem_list.get((int)n).get("mail").toString().equals(mail.getString("mail", ""))) {
										if (rem_list.get((int)n).get("time").toString().equals(new SimpleDateFormat("hh:mm aaa").format(cal.getTime()))) {
											if (rem_list.get((int)n).get("time").toString().equals(prev)) {
												
											}
											else {
												prev = rem_list.get((int)n).get("time").toString();
												rem_alert.edit().putString("medicine", rem_list.get((int)n).get("medicine").toString()).commit();
												i.setClass(getApplicationContext(), RemAlertActivity.class);
												startActivity(i);
												break;
											}
										}
									}
									else {
										rem_list.remove((int)(n));
									}
									n--;
								}
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(t, (int)(1000), (int)(1000));
	}
	
	@Override
	public void onStart() {
		super.onStart();
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						rem.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								rem_list = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										rem_list.add(_map);
									}
								}
								catch (Exception _e) {
									_e.printStackTrace();
								}
								cal = Calendar.getInstance();
								n = rem_list.size() - 1;
								len = rem_list.size();
								for(int _repeat47 = 0; _repeat47 < (int)(len); _repeat47++) {
									if (rem_list.get((int)n).get("mail").toString().equals(mail.getString("mail", ""))) {
										if (rem_list.get((int)n).get("time").toString().equals(new SimpleDateFormat("hh:mm aaa").format(cal.getTime()))) {
											if (rem_list.get((int)n).get("time").toString().equals(prev)) {
												
											}
											else {
												prev = rem_list.get((int)n).get("time").toString();
												rem_alert.edit().putString("medicine", rem_list.get((int)n).get("medicine").toString()).commit();
												i.setClass(getApplicationContext(), RemAlertActivity.class);
												startActivity(i);
												break;
											}
										}
									}
									else {
										rem_list.remove((int)(n));
									}
									n--;
								}
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(t, (int)(1000), (int)(1000));
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
