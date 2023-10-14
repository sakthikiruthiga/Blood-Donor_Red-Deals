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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

public class ReminderActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> mapp = new HashMap<>();
	private String time = "";
	private double n = 0;
	private double len = 0;
	private String pkey = "";
	private String hh = "";
	private String mm = "";
	
	private ArrayList<String> AmPm = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> rem_list = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private TextView textview4;
	private ListView listview1;
	private TextView textview1;
	private EditText edittext1;
	private CheckBox checkbox1;
	private EditText edittext2;
	private TextView textview3;
	private EditText edittext3;
	private Spinner spinner1;
	private Button button1;
	
	private Intent i = new Intent();
	private SharedPreferences mail;
	private DatabaseReference rem = _firebase.getReference("rem");
	private ChildEventListener _rem_child_listener;
	private AlertDialog.Builder d;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.reminder);
		initialize();
		initializeLogic();
	}
	
	private void initialize() {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview4 = (TextView) findViewById(R.id.textview4);
		listview1 = (ListView) findViewById(R.id.listview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		textview3 = (TextView) findViewById(R.id.textview3);
		edittext3 = (EditText) findViewById(R.id.edittext3);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		button1 = (Button) findViewById(R.id.button1);
		mail = getSharedPreferences("mail", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				d.setTitle("Delete this reminder?");
				d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						rem.child(rem_list.get((int)_position).get("pkey").toString()).removeValue();
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
								n = rem_list.size() - 1;
								len = rem_list.size();
								for(int _repeat26 = 0; _repeat26 < (int)(len); _repeat26++) {
									if (rem_list.get((int)n).get("mail").toString().equals(mail.getString("mail", ""))) {
										
									}
									else {
										rem_list.remove((int)(n));
									}
									n--;
								}
								if (rem_list.size() == 0) {
									textview4.setVisibility(View.VISIBLE);
								}
								else {
									textview4.setVisibility(View.GONE);
								}
								listview1.setAdapter(new Listview1Adapter(rem_list));
								((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
					}
				});
				d.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
				return true;
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (edittext1.getText().toString().equals("") || (edittext2.getText().toString().equals("") || edittext3.getText().toString().equals(""))) {
					SketchwareUtil.showMessage(getApplicationContext(), "Please fill require fields.");
				}
				else {
					if ((edittext2.getText().toString().length() > 2) || (edittext3.getText().toString().length() > 2)) {
						SketchwareUtil.showMessage(getApplicationContext(), "Invalid time");
					}
					else {
						if ((Double.parseDouble(edittext2.getText().toString()) > 12) || (Double.parseDouble(edittext2.getText().toString()) < 1)) {
							SketchwareUtil.showMessage(getApplicationContext(), "Invalid time");
						}
						else {
							if ((Double.parseDouble(edittext3.getText().toString()) > 59) || (Double.parseDouble(edittext3.getText().toString()) < 0)) {
								SketchwareUtil.showMessage(getApplicationContext(), "Invalid time");
							}
							else {
								if (edittext2.getText().toString().length() == 1) {
									hh = "0".concat(edittext2.getText().toString());
								}
								else {
									hh = edittext2.getText().toString();
								}
								if (edittext3.getText().toString().length() == 1) {
									mm = "0".concat(edittext3.getText().toString());
								}
								else {
									mm = edittext3.getText().toString();
								}
								if (spinner1.getSelectedItemPosition() == 0) {
									time = hh.concat(":".concat(mm.concat(" AM")));
								}
								else {
									time = hh.concat(":".concat(mm.concat(" PM")));
								}
								mapp = new HashMap<>();
								mapp.put("mail", mail.getString("mail", ""));
								mapp.put("medicine", edittext1.getText().toString());
								mapp.put("time", time);
								if (checkbox1.isChecked()) {
									mapp.put("bf", "T");
								}
								else {
									mapp.put("bf", "F");
								}
								pkey = rem.push().getKey();
								mapp.put("pkey", pkey);
								rem.child(pkey).updateChildren(mapp);
								mapp.clear();
								edittext1.setText("");
								edittext2.setText("");
								edittext3.setText("");
								checkbox1.setChecked(false);
								spinner1.setSelection((int)(0));
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
										n = rem_list.size() - 1;
										len = rem_list.size();
										for(int _repeat111 = 0; _repeat111 < (int)(len); _repeat111++) {
											if (rem_list.get((int)n).get("mail").toString().equals(mail.getString("mail", ""))) {
												
											}
											else {
												rem_list.remove((int)(n));
											}
											n--;
										}
										if (rem_list.size() == 0) {
											textview4.setVisibility(View.VISIBLE);
										}
										else {
											textview4.setVisibility(View.GONE);
										}
										listview1.setAdapter(new Listview1Adapter(rem_list));
										((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
									}
									@Override
									public void onCancelled(DatabaseError _databaseError) {
									}
								});
								SketchwareUtil.showMessage(getApplicationContext(), "Reminder added");
							}
						}
					}
				}
			}
		});
		
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
		AmPm.clear();
		AmPm.add("AM");
		AmPm.add("PM");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, AmPm));
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
				n = rem_list.size() - 1;
				len = rem_list.size();
				for(int _repeat20 = 0; _repeat20 < (int)(len); _repeat20++) {
					if (rem_list.get((int)n).get("mail").toString().equals(mail.getString("mail", ""))) {
						
					}
					else {
						rem_list.remove((int)(n));
					}
					n--;
				}
				if (rem_list.size() == 0) {
					textview4.setVisibility(View.VISIBLE);
				}
				else {
					textview4.setVisibility(View.GONE);
				}
				listview1.setAdapter(new Listview1Adapter(rem_list));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.cust2, null);
			}
			
			final LinearLayout linear3 = (LinearLayout) _v.findViewById(R.id.linear3);
			final TextView textview2 = (TextView) _v.findViewById(R.id.textview2);
			final TextView textview3 = (TextView) _v.findViewById(R.id.textview3);
			final TextView textview4 = (TextView) _v.findViewById(R.id.textview4);
			
			textview2.setText(rem_list.get((int)_position).get("medicine").toString());
			textview3.setText(rem_list.get((int)_position).get("time").toString());
			if (rem_list.get((int)_position).get("bf").toString().equals("T")) {
				textview4.setVisibility(View.VISIBLE);
			}
			else {
				textview4.setVisibility(View.INVISIBLE);
			}
			
			return _v;
		}
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
