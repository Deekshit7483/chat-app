package chat.app.com;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.stdio.swipetoreply.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class DetailsActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String uid = "";
	private HashMap<String, Object> map = new HashMap<>();
	private boolean ownAccount = false;
	private String userpost = "";
	private String selfpost = "";
	private double n = 0;
	private HashMap<String, Object> mymap = new HashMap<>();
	private HashMap<String, Object> mymap2 = new HashMap<>();
	private double fans = 0;
	private double stars = 0;
	private HashMap<String, Object> notice = new HashMap<>();
	private String sender_name = "";
	private double position = 0;
	private String verified = "";
	private String profile = "";
	private String user_id = "";
	private String edit_text1 = "";
	private String name = "";
	private String STATUS = "";
	private HashMap<String, Object> sts = new HashMap<>();
	private String key = "";
	private String URL = "";
	private String username = "";
	
	private ArrayList<String> gender = new ArrayList<>();
	private ArrayList<String> allkeys = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> followers = new ArrayList<>();
	private ArrayList<String> following = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list_post = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear7;
	private LinearLayout linear10;
	private GridView gridview1;
	private LinearLayout linear5;
	private LinearLayout linear11;
	private CircleImageView circleimageview1;
	private TextView textview1;
	private LinearLayout linear6;
	private TextView textview7;
	private TextView textview2;
	private ImageView imageview1;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear12;
	private TextView textview3;
	private TextView textview4;
	private TextView textview5;
	private TextView textview6;
	private TextView textview8;
	private TextView textview9;
	private Button button1;
	private Button button2;
	
	private FirebaseAuth fauth;
	private OnCompleteListener<AuthResult> _fauth_create_user_listener;
	private OnCompleteListener<AuthResult> _fauth_sign_in_listener;
	private OnCompleteListener<Void> _fauth_reset_password_listener;
	private OnCompleteListener<Void> fauth_updateEmailListener;
	private OnCompleteListener<Void> fauth_updatePasswordListener;
	private OnCompleteListener<Void> fauth_emailVerificationSentListener;
	private OnCompleteListener<Void> fauth_deleteUserListener;
	private OnCompleteListener<Void> fauth_updateProfileListener;
	private OnCompleteListener<AuthResult> fauth_phoneAuthListener;
	private OnCompleteListener<AuthResult> fauth_googleSignInListener;
	
	private Intent i = new Intent();
	private DatabaseReference user = _firebase.getReference("user");
	private ChildEventListener _user_child_listener;
	private DatabaseReference userposts = _firebase.getReference("userpost");
	private ChildEventListener _userposts_child_listener;
	private DatabaseReference selfposts = _firebase.getReference("selfpost");
	private ChildEventListener _selfposts_child_listener;
	private Calendar c = Calendar.getInstance();
	private DatabaseReference post = _firebase.getReference("post");
	private ChildEventListener _post_child_listener;
	private Intent pos = new Intent();
	private AlertDialog.Builder Dialog;
	private DatabaseReference userstatus = _firebase.getReference("userstatus");
	private ChildEventListener _userstatus_child_listener;
	private DatabaseReference follownoti = _firebase.getReference("follownoti");
	private ChildEventListener _follownoti_child_listener;
	private SharedPreferences mode;
	private DatabaseReference report = _firebase.getReference("report");
	private ChildEventListener _report_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.details);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		vscroll1 = findViewById(R.id.vscroll1);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear7 = findViewById(R.id.linear7);
		linear10 = findViewById(R.id.linear10);
		gridview1 = findViewById(R.id.gridview1);
		linear5 = findViewById(R.id.linear5);
		linear11 = findViewById(R.id.linear11);
		circleimageview1 = findViewById(R.id.circleimageview1);
		textview1 = findViewById(R.id.textview1);
		linear6 = findViewById(R.id.linear6);
		textview7 = findViewById(R.id.textview7);
		textview2 = findViewById(R.id.textview2);
		imageview1 = findViewById(R.id.imageview1);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear12 = findViewById(R.id.linear12);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		textview8 = findViewById(R.id.textview8);
		textview9 = findViewById(R.id.textview9);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		fauth = FirebaseAuth.getInstance();
		Dialog = new AlertDialog.Builder(this);
		mode = getSharedPreferences("mode", Activity.MODE_PRIVATE);
		
		textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), FollowActivity.class);
				i.putExtra("uid", getIntent().getStringExtra("uid"));
				startActivity(i);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (ownAccount) {
					SketchwareUtil.showMessage(getApplicationContext(), "It's your account");
				}
				else {
					if (allkeys.contains("followers")) {
						n = allkeys.indexOf("followers");
						if (listmap.get((int)n).containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid()) && listmap.get((int)n).get(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString().equals("true")) {
							mymap = new HashMap<>();
							mymap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "false");
							userposts.child("followers").updateChildren(mymap);
							mymap2 = new HashMap<>();
							mymap2.put(Uri.parse(userpost).getLastPathSegment(), "false");
							selfposts.child("following").updateChildren(mymap2);
						}
						else {
							mymap = new HashMap<>();
							mymap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "true");
							userposts.child("followers").updateChildren(mymap);
							mymap2 = new HashMap<>();
							mymap2.put(Uri.parse(userpost).getLastPathSegment(), "true");
							selfposts.child("following").updateChildren(mymap2);
							c = Calendar.getInstance();
							notice = new HashMap<>();
							notice.put("sender id", FirebaseAuth.getInstance().getCurrentUser().getUid());
							notice.put("time", new SimpleDateFormat("hh:mm a").format(c.getTime()));
							notice.put("id", uid);
							follownoti.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat(uid)).updateChildren(notice);
						}
					}
					else {
						mymap = new HashMap<>();
						mymap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "true");
						userposts.child("followers").updateChildren(mymap);
						mymap2 = new HashMap<>();
						mymap2.put(Uri.parse(userpost).getLastPathSegment(), "true");
						selfposts.child("following").updateChildren(mymap2);
						c = Calendar.getInstance();
						notice = new HashMap<>();
						notice.put("sender id", FirebaseAuth.getInstance().getCurrentUser().getUid());
						notice.put("time", new SimpleDateFormat("hh:mm a").format(c.getTime()));
						notice.put("id", uid);
						follownoti.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat(uid)).updateChildren(notice);
					}
				}
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), PrivateChatActivity.class);
				i.putExtra("first user", FirebaseAuth.getInstance().getCurrentUser().getUid());
				i.putExtra("uid", uid);
				startActivity(i);
			}
		});
		
		_user_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("id")) {
					if (_childKey.equals(uid)) {
						if (uid.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							ownAccount = true;
							button1.setVisibility(View.GONE);
							button2.setVisibility(View.GONE);
						}
						else {
							ownAccount = false;
							button1.setText("Follow");
							button2.setText("Message");
						}
						textview1.setText(_childValue.get("username").toString());
						textview2.setText(_childValue.get("name").toString());
						textview9.setText(_childValue.get("phone").toString());
						textview7.setText(_childValue.get("bio").toString());
						Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("pic").toString())).into(circleimageview1);
						circleimageview1.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								i.setClass(getApplicationContext(), FullPicActivity.class);
								i.putExtra("post", _childValue.get("pic").toString());
								startActivity(i);
							}
						});
						name = _childValue.get("name").toString();
						username = _childValue.get("username").toString();
						if (_childValue.get("details").toString().equals("private")) {
							linear12.setVisibility(View.GONE);
						}
						else {
							linear12.setVisibility(View.VISIBLE);
						}
						if (_childValue.get("admin").toString().equals("true")) {
							imageview1.setVisibility(View.VISIBLE);
							imageview1.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									SketchwareUtil.showMessage(getApplicationContext(), "Admin");
								}
							});
						}
						else {
							if (_childValue.get("admin").toString().equals("false")) {
								if (_childValue.get("verification").toString().equals("true")) {
									imageview1.setVisibility(View.VISIBLE);
									imageview1.setOnClickListener(new View.OnClickListener() {
										@Override
										public void onClick(View _view) {
											SketchwareUtil.showMessage(getApplicationContext(), "Verified user");
										}
									});
								}
								else {
									imageview1.setVisibility(View.GONE);
								}
							}
						}
					}
				}
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					sender_name = _childValue.get("name").toString();
					profile = _childValue.get("pic").toString();
					if (_childValue.get("verification").toString().equals("true")) {
						verified = "true";
					}
					else {
						verified = "false";
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("id")) {
					if (_childKey.equals(uid)) {
						if (uid.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							ownAccount = true;
							button1.setVisibility(View.GONE);
							button2.setVisibility(View.GONE);
						}
						else {
							ownAccount = false;
							button1.setText("Follow");
							button2.setText("Message");
						}
						textview1.setText(_childValue.get("username").toString());
						textview2.setText(_childValue.get("name").toString());
						circleimageview1.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								i.setClass(getApplicationContext(), FullPicActivity.class);
								i.putExtra("post", _childValue.get("pic").toString());
								startActivity(i);
							}
						});
						if (_childValue.get("verification").toString().equals("true")) {
							imageview1.setVisibility(View.VISIBLE);
						}
						else {
							imageview1.setVisibility(View.GONE);
						}
					}
				}
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
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		user.addChildEventListener(_user_child_listener);
		
		_userposts_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				userposts.addListenerForSingleValueEvent(new ValueEventListener() {
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
						allkeys.add(_childKey);
						if (_childKey.equals("followers")) {
							SketchwareUtil.getAllKeysFromMap(_childValue, followers);
							n = 0;
							fans = 0;
							for(int _repeat20 = 0; _repeat20 < (int)(followers.size()); _repeat20++) {
								if (_childValue.get(followers.get((int)(n))).toString().equals("true")) {
									fans++;
								}
								else {
									
								}
								n++;
							}
							textview4.setText(String.valueOf((long)(fans)));
						}
						if (_childKey.equals("following")) {
							SketchwareUtil.getAllKeysFromMap(_childValue, following);
							n = 0;
							stars = 0;
							for(int _repeat40 = 0; _repeat40 < (int)(following.size()); _repeat40++) {
								if (_childValue.get(following.get((int)(n))).toString().equals("true")) {
									stars++;
								}
								else {
									
								}
								n++;
							}
							textview6.setText(String.valueOf((long)(stars)));
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				userposts.addListenerForSingleValueEvent(new ValueEventListener() {
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
						allkeys.add(_childKey);
						if (_childKey.equals("followers")) {
							SketchwareUtil.getAllKeysFromMap(_childValue, followers);
							n = 0;
							fans = 0;
							for(int _repeat20 = 0; _repeat20 < (int)(followers.size()); _repeat20++) {
								if (_childValue.get(followers.get((int)(n))).toString().equals("true")) {
									fans++;
								}
								else {
									
								}
								n++;
							}
							textview4.setText(String.valueOf((long)(fans)));
						}
						if (_childKey.equals("following")) {
							SketchwareUtil.getAllKeysFromMap(_childValue, following);
							n = 0;
							stars = 0;
							for(int _repeat40 = 0; _repeat40 < (int)(following.size()); _repeat40++) {
								if (_childValue.get(following.get((int)(n))).toString().equals("true")) {
									stars++;
								}
								else {
									
								}
								n++;
							}
							textview6.setText(String.valueOf((long)(stars)));
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		userposts.addChildEventListener(_userposts_child_listener);
		
		_selfposts_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (!FirebaseAuth.getInstance().getCurrentUser().getUid().equals(uid)) {
					if (_childKey.equals("following")) {
						if (_childValue.containsKey(uid)) {
							if (_childValue.get(uid).toString().equals("true")) {
								_SetBackground(button1, 25, 15, "#CFD8DC", true);
								button1.setText("Following");
							}
							else {
								_SetBackground(button1, 25, 15, "#1976D2", true);
								button1.setText("Follow");
							}
						}
						else {
							button1.setText("Follow");
							_SetBackground(button1, 25, 15, "#1976D2", true);
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (!FirebaseAuth.getInstance().getCurrentUser().getUid().equals(uid)) {
					if (_childKey.equals("following")) {
						if (_childValue.containsKey(uid)) {
							if (_childValue.get(uid).toString().equals("true")) {
								_SetBackground(button1, 25, 15, "#CFD8DC", true);
								button1.setText("Following");
							}
							else {
								_SetBackground(button1, 25, 15, "#1976D2", true);
								button1.setText("Follow");
							}
						}
						else {
							button1.setText("Follow");
							_SetBackground(button1, 25, 15, "#1976D2", true);
						}
					}
				}
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
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		selfposts.addChildEventListener(_selfposts_child_listener);
		
		_post_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("post")) {
					position = 0;
					list_post.add((int)position, _childValue);
					gridview1.setAdapter(new Gridview1Adapter(list_post));
					gridview1.setNumColumns((int)3);
					gridview1.setVerticalSpacing((int)5);
					gridview1.setHorizontalSpacing((int)5);
					gridview1.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
					gridview1.setFastScrollEnabled(true);
					
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("post")) {
					position = 0;
					for(int _repeat14 = 0; _repeat14 < (int)(list_post.size()); _repeat14++) {
						if (_childKey.equals(list_post.get((int)position).get("push_key").toString())) {
							list_post.add((int)position, _childValue);
							list_post.remove((int)(position));
						}
						position++;
					}
				}
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
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		post.addChildEventListener(_post_child_listener);
		
		_userstatus_child_listener = new ChildEventListener() {
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
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		userstatus.addChildEventListener(_userstatus_child_listener);
		
		_follownoti_child_listener = new ChildEventListener() {
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
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		follownoti.addChildEventListener(_follownoti_child_listener);
		
		_report_child_listener = new ChildEventListener() {
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
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		report.addChildEventListener(_report_child_listener);
		
		fauth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		fauth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_fauth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fauth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fauth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		uid = getIntent().getStringExtra("uid");
		imageview1.setVisibility(View.GONE);
		userposts.removeEventListener(_userposts_child_listener);
		userpost = "userposts/".concat(uid);
		selfposts.removeEventListener(_selfposts_child_listener);
		selfpost = "userposts/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		userposts =
		_firebase.getReference(userpost);
		selfposts =
		_firebase.getReference(selfpost);
		userposts.addChildEventListener(_userposts_child_listener);
		selfposts.addChildEventListener(_selfposts_child_listener);
		_initSlideActivity();
		_setUserStatus("Online");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		c = Calendar.getInstance();
		_setUserStatus("last seen at ".concat(new SimpleDateFormat("hh:mm a").format(c.getTime()).concat("- ".concat(new SimpleDateFormat("dd:MMM").format(c.getTime())))));
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), Home_Activity.class);
		startActivity(i);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (mode.getString("dark", "").equals("true")) {
			linear3.setBackgroundColor(0xFF000000);
			gridview1.setBackgroundColor(0xFF000000);
			textview1.setTextColor(0xFFFFFFFF);
			textview7.setTextColor(0xFFFFFFFF);
			textview2.setTextColor(0xFFFFFFFF);
			textview3.setTextColor(0xFFFFFFFF);
			textview4.setTextColor(0xFFFFFFFF);
			textview5.setTextColor(0xFFFFFFFF);
			textview6.setTextColor(0xFFFFFFFF);
			textview8.setTextColor(0xFFFFFFFF);
			textview9.setTextColor(0xFFFFFFFF);
		}
		else {
			if (mode.getString("dark", "").equals("false")) {
				linear3.setBackgroundColor(0xFFFFFFFF);
				gridview1.setBackgroundColor(0xFFFFFFFF);
				textview1.setTextColor(0xFF000000);
				textview7.setTextColor(0xFF000000);
				textview2.setTextColor(0xFF000000);
				textview3.setTextColor(0xFF000000);
				textview4.setTextColor(0xFF000000);
				textview5.setTextColor(0xFF000000);
				textview6.setTextColor(0xFF000000);
				textview8.setTextColor(0xFF000000);
				textview9.setTextColor(0xFF000000);
			}
		}
	}
	public void _SetBackground(final View _view, final double _radius, final double _shadow, final String _color, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
		}
	}
	
	
	public void _initSlideActivity() {
		getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
		ViewConfiguration vc = ViewConfiguration.get(this);
		MIN_DISTANCE = vc.getScaledTouchSlop();
		
		rootView =(ViewGroup)getWindow().findViewById(Window.ID_ANDROID_CONTENT);
		//converts percent to 0-225 range .
		maxAlpha =(int) ((225.0d/100.0d)* MAX_SCRIM_ALPHA); 
		try{
			convertFromTranslucent = Activity.class.getDeclaredMethod("convertFromTranslucent");         convertFromTranslucent.setAccessible(true);
			java.lang.reflect.Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions"); 	getActivityOptions.setAccessible(true);
			options = getActivityOptions.invoke(this);
				Class<?>[] classes = Activity.class.getDeclaredClasses();
			 Class<?> translucentConversionListenerClazz = null;
				for (Class clazz : classes) {
						if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
								translucentConversionListenerClazz = clazz; 
						} 
				} 
				 convertToTranslucent = Activity.class.getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz, ActivityOptions.class);
				convertToTranslucent.setAccessible(true);
		} catch (Exception e) {
			showMessage(e.toString());
			 }
	}
	// Custom Variables
	//You can change it to color of your choice 
	private static final int SCRIM_COLOR = 0xFF000000;
	//Alpha is not taken into consideration while calculating scrim color  so it dosent matter .
	
	private static final int  SCRIM_R = Color.red(SCRIM_COLOR);
	private static final int SCRIM_G = Color.green(SCRIM_COLOR);
	private static final int  SCRIM_B = Color.blue(SCRIM_COLOR);
	private static final int MAX_SCRIM_ALPHA= 80;
	//in percentage
	private ViewGroup rootView ;
	private boolean enableSwipe= false;
	private boolean lockSwipe = false;
	private float downX;
	private float downY;
	private float MIN_DISTANCE ;
	private int maxAlpha;
	private java.lang.reflect.Method convertFromTranslucent;
	private java.lang.reflect.Method getActivityOptions;
	
	private Object options;
	
	private java.lang.reflect.Method convertToTranslucent;
	// Detect touch Events
	 @Override public boolean dispatchTouchEvent(MotionEvent event) { 
		switch(event.getAction()) { 
			case MotionEvent.ACTION_DOWN: 
			downX = event.getRawX();
			downY =event.getRawY();
			enableSwipe = false;
			lockSwipe = false;
			//convert activity to transparent
			try {
					convertToTranslucent.invoke(this, null, options); 
			} catch (Throwable t) {
			}
			break; 
			case MotionEvent.ACTION_MOVE: 
			if (!lockSwipe){
				if(enableSwipe){
					float translation = event.getRawX() -downX - MIN_DISTANCE;
					if (translation >= rootView.getWidth() || translation<= 0){
						rootView.setTranslationX(0);
					}else{
						rootView.setTranslationX(translation);
						//calculate distance scrolled in percentage
						int distanceInPercentage =(int)( ((double)translation/(double)rootView.getWidth())*100);
						
						//calculate alpha from distance in range 0 - maxAlpha
						
						int alpha =(int) ( ((double)maxAlpha/100.0d)*distanceInPercentage);
						
						//alpha will be greater when it is scrolled more this we do not need this but we need the inverse of it so subtract it from maxAlpha
						alpha = maxAlpha - alpha;
						
						int scrimColor = Color.argb(alpha,SCRIM_R,SCRIM_G,SCRIM_B);
						
						getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(scrimColor));
					}
				}else{
					if(Math.abs(event.getRawY() - downY) >= MIN_DISTANCE){
						enableSwipe = false;
						lockSwipe = true;
					}else{
						enableSwipe = event.getRawX() -downX >= MIN_DISTANCE;
					}
				}
			}
			break; 
			case MotionEvent.ACTION_UP: 
			if(rootView.getTranslationX() > rootView.getWidth() / 5){
				rootView.animate() 
				.translationX(rootView.getWidth())
				.setListener(
				new AnimatorListenerAdapter() { 
							@Override public void onAnimationEnd(Animator animation) { 
						
							super.onAnimationEnd(animation);
						finish();
						overridePendingTransition(0, 0);
						
					} });
			}else{
				rootView.animate() 
				.translationX(0)
				.setListener(
				new AnimatorListenerAdapter() { 
							@Override public void onAnimationEnd(Animator animation) { 
						super.onAnimationEnd(animation);
						// convert activity back to normal
						try {
							 convertFromTranslucent.invoke(this);
							        } catch (Throwable t) {}
					} });
				enableSwipe =false;
				lockSwipe = false;
			}
			break; 
			default:
			enableSwipe =false;
			lockSwipe = false;
			break; 
		}
		if (enableSwipe){
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}
	
	{
	}
	
	
	public void _Menu() {
	}
	@Override
	public boolean onCreateOptionsMenu (Menu menu){
		menu.add(0, 0, 0, "Report");
		menu.add(0, 1, 1, "Share");
		return true;
	}
	 @Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case 0:
			Dialog.setTitle("Report");
			Dialog.setMessage("Report ".concat(name.concat(" Account")));
			View inflated_view = getLayoutInflater().inflate(R.layout.
			dialog_edittext
			, null);
			Dialog.setView(inflated_view);
			
			final EditText edittext1 = new EditText(DetailsActivity.this);
			edittext1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
			edittext1.setHint("Write message");
			LinearLayout dialog_linear1 = inflated_view.findViewById(R.id.linear1);
			dialog_linear1.addView(edittext1);
			Dialog.setPositiveButton("Send ", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					key = report.push().getKey();
					edit_text1= edittext1.getText().toString();
					map = new HashMap<>();
					map.put("message", edit_text1);
					map.put("id", uid);
					map.put("sender id", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("key", key);
					report.child(key).updateChildren(map);
					map.clear();
					SketchwareUtil.showMessage(getApplicationContext(), "Reported. ");
				}
			});
			Dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					
				}
			});
			Dialog.create().show();
			break;
			case 1:
			URL = "https://www.chatapp.com/".concat(username.concat("/"));
			Intent i = new Intent(android.content.Intent.ACTION_SEND); i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_TEXT,URL); startActivity(Intent.createChooser(i,"Compartilhar usando.."));
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void _setUserStatus(final String _str) {
		if (!_str.equals(STATUS)) {
			sts = new HashMap<>();
			sts.put("status", _str);
			userstatus.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(sts);
			STATUS = _str;
		}
	}
	
	public class Gridview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.grid_list, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			if (mode.getString("dark", "").equals("true")) {
				linear1.setBackgroundColor(0xFF000000);
			}
			else {
				if (mode.getString("dark", "").equals("false")) {
					linear1.setBackgroundColor(0xFFFFFFFF);
				}
			}
			if (_data.get((int)_position).get("id").toString().equals(getIntent().getStringExtra("uid"))) {
				if (_data.get((int)_position).containsKey("post")) {
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("post").toString())).into(imageview1);
				}
				imageview1.setVisibility(View.VISIBLE);
			}
			else {
				imageview1.setVisibility(View.GONE);
			}
			imageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					pos.setClass(getApplicationContext(), FullPicActivity.class);
					pos.putExtra("post", _data.get((int)_position).get("post").toString());
					startActivity(pos);
				}
			});
			
			return _view;
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
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}