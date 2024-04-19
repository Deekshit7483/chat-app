package chat.app.com;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OneSignal;
import com.stdio.swipetoreply.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.File;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import org.json.JSONObject;

public class Home_Activity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private HashMap<String, Object> map = new HashMap<>();
	private double n = 0;
	private double fans = 0;
	private double stars = 0;
	private String userpost = "";
	private String selfpost = "";
	private String string = "";
	private String image = "";
	private String video = "";
	private String files = "";
	private String status = "";
	private String audios = "";
	private String avatar_url = "";
	private String list_chat_str = "";
	private String chat_str = "";
	private boolean list_chat_ = false;
	private boolean pause = false;
	private String uid2 = "";
	private String POSTS = "";
	private String shareimage = "";
	private String STATUS = "";
	private HashMap<String, Object> sts = new HashMap<>();
	private double number = 0;
	private double length = 0;
	private HashMap<String, Object> map_like = new HashMap<>();
	private double num_like = 0;
	private double likes = 0;
	private double n_ls_like = 0;
	private HashMap<String, Object> put_like = new HashMap<>();
	private HashMap<String, Object> mapvar = new HashMap<>();
	private double duration = 0;
	
	private ArrayList<String> uids = new ArrayList<>();
	private ArrayList<String> names = new ArrayList<>();
	private ArrayList<String> avatars = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> allkeys = new ArrayList<>();
	private ArrayList<String> followers = new ArrayList<>();
	private ArrayList<String> following = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> noti = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list_post = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm_list_chat = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> user_data = new ArrayList<>();
	private ArrayList<String> all_user_if = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> post_map = new ArrayList<>();
	private ArrayList<String> userUID = new ArrayList<>();
	private ArrayList<String> my_available_groups = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> group_data = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm_like = new ArrayList<>();
	private ArrayList<String> ls_like = new ArrayList<>();
	private ArrayList<String> postKeys = new ArrayList<>();
	private ArrayList<String> postlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear24;
	private LinearLayout linear2;
	private LinearLayout linear_post;
	private LinearLayout linear_noti;
	private LinearLayout linear_home;
	private LinearLayout linear_sreach;
	private LinearLayout linear_profile;
	private ListView posts;
	private TextView textview5;
	private ListView notice;
	private LinearLayout linear30;
	private ListView chat;
	private ImageView imageview9;
	private LinearLayout linear27;
	private ListView users;
	private ImageView imageview8;
	private LinearLayout linear29;
	private EditText edittext1;
	private LinearLayout linear22;
	private LinearLayout linear10;
	private LinearLayout linear18;
	private ImageView imageview7;
	private LinearLayout linear13;
	private LinearLayout linear26;
	private CircleImageView circleimageview1;
	private TextView username;
	private LinearLayout linear14;
	private TextView text;
	private TextView name;
	private ImageView imageview1;
	private LinearLayout linear19;
	private LinearLayout linear20;
	private TextView textview3;
	private TextView Follower;
	private TextView textview4;
	private TextView followin;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private ImageView imageview_post;
	private ImageView imageview_noti;
	private ImageView imageview_home;
	private ImageView imageview_sreach;
	private ImageView imageview_profile;
	private LinearLayout _drawer_linear3;
	private LinearLayout _drawer_linear6;
	private LinearLayout _drawer_linear_settings;
	private LinearLayout _drawer_linear_edit;
	private LinearLayout _drawer_linear_post;
	private LinearLayout _drawer_linear_share;
	private LinearLayout _drawer_linear_logout;
	private TextView _drawer_setting;
	private TextView _drawer_edit_profile;
	private TextView _drawer_upload_post;
	private TextView _drawer_share;
	private TextView _drawer_Logout;
	
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
	
	private DatabaseReference user = _firebase.getReference("user");
	private ChildEventListener _user_child_listener;
	private DatabaseReference selfposts = _firebase.getReference("selfpost");
	private ChildEventListener _selfposts_child_listener;
	private DatabaseReference userposts = _firebase.getReference("userpost");
	private ChildEventListener _userposts_child_listener;
	private DatabaseReference post = _firebase.getReference("post");
	private ChildEventListener _post_child_listener;
	private Intent i = new Intent();
	private Intent pos = new Intent();
	private TimerTask t;
	private SharedPreferences mutes;
	private DatabaseReference list_chat = _firebase.getReference("+list_chat_str+");
	private ChildEventListener _list_chat_child_listener;
	private DatabaseReference chatU1 = _firebase.getReference("+chat_str+");
	private ChildEventListener _chatU1_child_listener;
	private SharedPreferences share;
	private SharedPreferences listchat;
	private AlertDialog.Builder dialog;
	private AlertDialog.Builder alert;
	private SharedPreferences pesan;
	private SharedPreferences delete;
	private SharedPreferences mode;
	private StorageReference post_ = _firebase_storage.getReference("post");
	private OnCompleteListener<Uri> _post__upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _post__download_success_listener;
	private OnSuccessListener _post__delete_success_listener;
	private OnProgressListener _post__upload_progress_listener;
	private OnProgressListener _post__download_progress_listener;
	private OnFailureListener _post__failure_listener;
	
	private OSSubscriptionObserver OS;
	
	private OnCompleteListener FCM_onCompleteListener;
	private Calendar cl = Calendar.getInstance();
	private DatabaseReference userstatus = _firebase.getReference("userstatus");
	private ChildEventListener _userstatus_child_listener;
	private DatabaseReference follownoti = _firebase.getReference("follownoti");
	private ChildEventListener _follownoti_child_listener;
	private AlertDialog.Builder logout;
	private DatabaseReference groups = _firebase.getReference("groups");
	private ChildEventListener _groups_child_listener;
	private DatabaseReference group_list = _firebase.getReference("group_list");
	private ChildEventListener _group_list_child_listener;
	private DatabaseReference like = _firebase.getReference("like_key");
	private ChildEventListener _like_child_listener;
	private Calendar get = Calendar.getInstance();
	private Calendar from = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home_);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(Home_Activity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		linear1 = findViewById(R.id.linear1);
		linear24 = findViewById(R.id.linear24);
		linear2 = findViewById(R.id.linear2);
		linear_post = findViewById(R.id.linear_post);
		linear_noti = findViewById(R.id.linear_noti);
		linear_home = findViewById(R.id.linear_home);
		linear_sreach = findViewById(R.id.linear_sreach);
		linear_profile = findViewById(R.id.linear_profile);
		posts = findViewById(R.id.posts);
		textview5 = findViewById(R.id.textview5);
		notice = findViewById(R.id.notice);
		linear30 = findViewById(R.id.linear30);
		chat = findViewById(R.id.chat);
		imageview9 = findViewById(R.id.imageview9);
		linear27 = findViewById(R.id.linear27);
		users = findViewById(R.id.users);
		imageview8 = findViewById(R.id.imageview8);
		linear29 = findViewById(R.id.linear29);
		edittext1 = findViewById(R.id.edittext1);
		linear22 = findViewById(R.id.linear22);
		linear10 = findViewById(R.id.linear10);
		linear18 = findViewById(R.id.linear18);
		imageview7 = findViewById(R.id.imageview7);
		linear13 = findViewById(R.id.linear13);
		linear26 = findViewById(R.id.linear26);
		circleimageview1 = findViewById(R.id.circleimageview1);
		username = findViewById(R.id.username);
		linear14 = findViewById(R.id.linear14);
		text = findViewById(R.id.text);
		name = findViewById(R.id.name);
		imageview1 = findViewById(R.id.imageview1);
		linear19 = findViewById(R.id.linear19);
		linear20 = findViewById(R.id.linear20);
		textview3 = findViewById(R.id.textview3);
		Follower = findViewById(R.id.Follower);
		textview4 = findViewById(R.id.textview4);
		followin = findViewById(R.id.followin);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		imageview_post = findViewById(R.id.imageview_post);
		imageview_noti = findViewById(R.id.imageview_noti);
		imageview_home = findViewById(R.id.imageview_home);
		imageview_sreach = findViewById(R.id.imageview_sreach);
		imageview_profile = findViewById(R.id.imageview_profile);
		_drawer_linear3 = _nav_view.findViewById(R.id.linear3);
		_drawer_linear6 = _nav_view.findViewById(R.id.linear6);
		_drawer_linear_settings = _nav_view.findViewById(R.id.linear_settings);
		_drawer_linear_edit = _nav_view.findViewById(R.id.linear_edit);
		_drawer_linear_post = _nav_view.findViewById(R.id.linear_post);
		_drawer_linear_share = _nav_view.findViewById(R.id.linear_share);
		_drawer_linear_logout = _nav_view.findViewById(R.id.linear_logout);
		_drawer_setting = _nav_view.findViewById(R.id.setting);
		_drawer_edit_profile = _nav_view.findViewById(R.id.edit_profile);
		_drawer_upload_post = _nav_view.findViewById(R.id.upload_post);
		_drawer_share = _nav_view.findViewById(R.id.share);
		_drawer_Logout = _nav_view.findViewById(R.id.Logout);
		fauth = FirebaseAuth.getInstance();
		mutes = getSharedPreferences("mutes", Activity.MODE_PRIVATE);
		share = getSharedPreferences("share", Activity.MODE_PRIVATE);
		listchat = getSharedPreferences("listchat", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		alert = new AlertDialog.Builder(this);
		pesan = getSharedPreferences("pesan", Activity.MODE_PRIVATE);
		delete = getSharedPreferences("delete", Activity.MODE_PRIVATE);
		mode = getSharedPreferences("mode", Activity.MODE_PRIVATE);
		logout = new AlertDialog.Builder(this);
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), AboutAppActivity.class);
				startActivity(i);
			}
		});
		
		notice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				i.setClass(getApplicationContext(), DetailsActivity.class);
				i.putExtra("uid", noti.get((int)_position).get("sender id").toString());
				i.putExtra("user", "noti");
				startActivity(i);
			}
		});
		
		chat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (!lm_list_chat.get((int)_position).containsKey("id")) {
					if (lm_list_chat.get((int)_position).containsKey("uid") && lm_list_chat.get((int)_position).containsKey("uid2")) {
						if (lm_list_chat.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							i.putExtra("type", "null");
							i.putExtra("uid", lm_list_chat.get((int)_position).get("uid2").toString());
							i.setClass(getApplicationContext(), PrivateChatActivity.class);
							startActivity(i);
						}
						else {
							i.putExtra("type", "null");
							i.putExtra("uid", lm_list_chat.get((int)_position).get("uid").toString());
							i.setClass(getApplicationContext(), PrivateChatActivity.class);
							startActivity(i);
						}
					}
				}
				else {
					i.putExtra("group_key", lm_list_chat.get((int)_position).get("id").toString());
					i.setClass(getApplicationContext(), GroupChatActivity.class);
					startActivity(i);
				}
			}
		});
		
		chat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (lm_list_chat.get((int)_position).containsKey("uid") && lm_list_chat.get((int)_position).containsKey("uid2")) {
					alert.setTitle("Options");
					alert.setMessage("Delete or Mute Chat.");
					alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_deleteChat(_position);
						}
					});
					alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					if (lm_list_chat.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (mutes.getString(lm_list_chat.get((int)_position).get("uid2").toString(), "").contains("true")) {
							alert.setNeutralButton("Unmute", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									_muteChat(_position, false);
								}
							});
						}
						else {
							alert.setNeutralButton("Mute", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									_muteChat(_position, true);
								}
							});
						}
					}
					else {
						if (mutes.getString(lm_list_chat.get((int)_position).get("uid").toString(), "").contains("true")) {
							alert.setNeutralButton("Unmute", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									_muteChat(_position, false);
								}
							});
						}
						else {
							alert.setNeutralButton("Mute", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									_muteChat(_position, true);
								}
							});
						}
					}
					alert.create().show();
				}
				return true;
			}
		});
		
		imageview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("Group chat");
				dialog.setPositiveButton("Create group", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						i.setClass(getApplicationContext(), CreateGroupActivity.class);
						startActivity(i);
					}
				});
				dialog.setNegativeButton("Join group", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				i.setClass(getApplicationContext(), DetailsActivity.class);
				i.putExtra("uid", list_map.get((int)_position).get("id").toString());
				i.putExtra("user", "sreach");
				startActivity(i);
			}
		});
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_hideKeyboard();
				imageview8.setVisibility(View.GONE);
			}
		});
		
		edittext1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				imageview8.setVisibility(View.VISIBLE);
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				imageview8.setVisibility(View.VISIBLE);
				user.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list_map = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list_map.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (_charSeq.length() > 0) {
							number = list_map.size() - 1;
							length = list_map.size();
							for(int _repeat20 = 0; _repeat20 < (int)(length); _repeat20++) {
								if (list_map.get((int)number).get("username").toString().toLowerCase().contains(_charSeq.toLowerCase()) || list_map.get((int)number).get("name").toString().toLowerCase().contains(_charSeq.toLowerCase())) {
									
								}
								else {
									list_map.remove((int)(number));
								}
								number--;
							}
						}
						users.setAdapter(new UsersAdapter(list_map));
						((BaseAdapter)users.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.openDrawer(Gravity.RIGHT);
				//mahdi_313
			}
		});
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_TabStatus(imageview_post, true);
				_TabStatus(imageview_home, false);
				_TabStatus(imageview_noti, false);
				_TabStatus(imageview_sreach, false);
				_TabStatus(imageview_profile, false);
				linear_post.setVisibility(View.VISIBLE);
				linear_noti.setVisibility(View.GONE);
				linear_home.setVisibility(View.GONE);
				linear_sreach.setVisibility(View.GONE);
				linear_profile.setVisibility(View.GONE);
			}
		});
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_TabStatus(imageview_noti, true);
				_TabStatus(imageview_post, false);
				_TabStatus(imageview_home, false);
				_TabStatus(imageview_sreach, false);
				_TabStatus(imageview_profile, false);
				linear_post.setVisibility(View.GONE);
				linear_noti.setVisibility(View.VISIBLE);
				linear_home.setVisibility(View.GONE);
				linear_sreach.setVisibility(View.GONE);
				linear_profile.setVisibility(View.GONE);
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_TabStatus(imageview_home, true);
				_TabStatus(imageview_post, false);
				_TabStatus(imageview_noti, false);
				_TabStatus(imageview_sreach, false);
				_TabStatus(imageview_profile, false);
				linear_post.setVisibility(View.GONE);
				linear_noti.setVisibility(View.GONE);
				linear_home.setVisibility(View.VISIBLE);
				linear_sreach.setVisibility(View.GONE);
				linear_profile.setVisibility(View.GONE);
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_TabStatus(imageview_sreach, true);
				_TabStatus(imageview_post, false);
				_TabStatus(imageview_noti, false);
				_TabStatus(imageview_home, false);
				_TabStatus(imageview_profile, false);
				linear_post.setVisibility(View.GONE);
				linear_noti.setVisibility(View.GONE);
				linear_home.setVisibility(View.GONE);
				linear_sreach.setVisibility(View.VISIBLE);
				linear_profile.setVisibility(View.GONE);
			}
		});
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_TabStatus(imageview_profile, true);
				_TabStatus(imageview_post, false);
				_TabStatus(imageview_noti, false);
				_TabStatus(imageview_sreach, false);
				_TabStatus(imageview_home, false);
				linear_post.setVisibility(View.GONE);
				linear_noti.setVisibility(View.GONE);
				linear_home.setVisibility(View.GONE);
				linear_sreach.setVisibility(View.GONE);
				linear_profile.setVisibility(View.VISIBLE);
			}
		});
		
		_user_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				user.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list_map = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list_map.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						users.setAdapter(new UsersAdapter(list_map));
						((BaseAdapter)users.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
				uids.add(_childKey);
				user_data.add(_childValue);
				names.add(_childValue.get("name").toString());
				avatars.add(_childValue.get("pic").toString());
				all_user_if.add(_childValue.get("uid").toString());
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					username.setText(_childValue.get("username").toString());
					name.setText(_childValue.get("name").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("pic").toString())).into(circleimageview1);
					text.setText(_childValue.get("bio").toString());
					if (_childValue.get("admin").toString().equals("true")) {
						imageview1.setVisibility(View.VISIBLE);
					}
					else {
						if (_childValue.get("admin").toString().equals("false")) {
							if (_childValue.get("verification").toString().equals("true")) {
								imageview1.setVisibility(View.VISIBLE);
							}
							else {
								imageview1.setVisibility(View.GONE);
							}
						}
					}
				}
				try {
					if (!getIntent().getStringExtra("link").contains("null")) {
						if (all_user_if.contains(getIntent().getStringExtra("link"))) {
							i.putExtra("type", "intent");
							i.putExtra("uid", getIntent().getStringExtra("link"));
							i.setClass(getApplicationContext(), PrivateChatActivity.class);
							startActivity(i);
						}
						else {
							finishAffinity();
						}
					}
				}catch(Exception e){}
				try {
					((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
				}catch(Exception e){}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				user.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list_map = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list_map.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						users.setAdapter(new UsersAdapter(list_map));
						((BaseAdapter)users.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
				uids.add(_childKey);
				names.add(_childValue.get("name").toString());
				avatars.add(_childValue.get("pic").toString());
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					username.setText(_childValue.get("username").toString());
					name.setText(_childValue.get("name").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("pic").toString())).into(circleimageview1);
					circleimageview1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							i.setClass(getApplicationContext(), FullPicActivity.class);
							i.putExtra("post", _childValue.get("pic").toString());
							startActivity(i);
						}
					});
					if (_childValue.get("admin").toString().equals("true")) {
						imageview1.setVisibility(View.VISIBLE);
					}
					else {
						if (_childValue.get("admin").toString().equals("false")) {
							if (_childValue.get("verification").toString().equals("true")) {
								imageview1.setVisibility(View.VISIBLE);
							}
							else {
								imageview1.setVisibility(View.GONE);
							}
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
		
		_selfposts_child_listener = new ChildEventListener() {
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
		selfposts.addChildEventListener(_selfposts_child_listener);
		
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
							Follower.setText(String.valueOf((long)(fans)));
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
							followin.setText(String.valueOf((long)(stars)));
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
							Follower.setText(String.valueOf((long)(fans)));
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
							followin.setText(String.valueOf((long)(stars)));
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
		
		_post_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				post.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list_post = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list_post.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						postlist.add(_childKey);
						_SortMap(list_post, "push key", false, false);
						Collections.reverse(list_post);
						posts.setAdapter(new PostsAdapter(list_post));
						((BaseAdapter)posts.getAdapter()).notifyDataSetChanged();
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
				post.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list_post = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list_post.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						_SortMap(list_post, "push key", false, false);
						Collections.reverse(list_post);
						posts.setAdapter(new PostsAdapter(list_post));
						((BaseAdapter)posts.getAdapter()).notifyDataSetChanged();
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
				((BaseAdapter)posts.getAdapter()).notifyDataSetChanged();
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		post.addChildEventListener(_post_child_listener);
		
		_list_chat_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (list_chat_) {
					_get_chat(_childValue);
				}
				try {
					((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
				}catch(Exception e){}
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
		list_chat.addChildEventListener(_list_chat_child_listener);
		
		_chatU1_child_listener = new ChildEventListener() {
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
		chatU1.addChildEventListener(_chatU1_child_listener);
		
		_post__upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_post__download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_post__upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_post__download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				_sharelmage(shareimage);
			}
		};
		
		_post__delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_post__failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
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
				follownoti.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						noti = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								noti.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						notice.setAdapter(new NoticeAdapter(noti));
						((BaseAdapter)notice.getAdapter()).notifyDataSetChanged();
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
		
		_groups_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("participants").toString().contains(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					my_available_groups.add(_childKey);
					group_data.add(_childValue);
				}
				try {
					((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
				}catch(Exception e){}
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
		groups.addChildEventListener(_groups_child_listener);
		
		_group_list_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (my_available_groups.contains(_childValue.get("id").toString())) {
					if (_childValue.containsKey("uid")) {
						share.edit().putString(_childKey, _childValue.get("uid").toString()).commit();
					}
					_get_chat(_childValue);
				}
				try {
					((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
				}catch(Exception e){}
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
		group_list.addChildEventListener(_group_list_child_listener);
		
		_like_child_listener = new ChildEventListener() {
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
		like.addChildEventListener(_like_child_listener);
		
		_drawer_setting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SettingActivity.class);
				startActivity(i);
			}
		});
		
		_drawer_edit_profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), EditProfileActivity.class);
				startActivity(i);
			}
		});
		
		_drawer_upload_post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), UploadPostActivity.class);
				startActivity(i);
			}
		});
		
		_drawer_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "please wait");
				shareApplication();
			}
		});
		
		_drawer_Logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				logout.setTitle("Chat app. ");
				logout.setMessage("Do you want to log out! ");
				logout.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
							FirebaseAuth.getInstance().signOut();
							i.setClass(getApplicationContext(), LoginActivity.class);
							startActivity(i);
						}
					}
				});
				logout.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				logout.create().show();
			}
		});
		
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
		_UI_Logic_onCreat();
		_hide();
		_DG_DrawerTransparent();
		StrictMode.VmPolicy.Builder builder = 
		    new StrictMode.VmPolicy.Builder(); 
		    StrictMode.setVmPolicy(builder.build());
		    if(Build.VERSION.SDK_INT>=24){ 
			         try{
				        java.lang.reflect.Method m = 
				              StrictMode.class.getMethod(
				        "disableDeathOnFileUriExposure"); 
				              m.invoke(null); 
				       }
			      catch(Exception e){ 
				        showMessage(e.toString()); 
				       } 
			    }
		imageview1.setVisibility(View.GONE);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				_drawer.openDrawer(Gravity.RIGHT);
				SketchwareUtil.hideKeyboard(getApplicationContext());
			}
		});
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		androidx.drawerlayout.widget.DrawerLayout
		.LayoutParams lp = new androidx.drawerlayout.widget.DrawerLayout
		.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		lp.gravity=Gravity.RIGHT;
		_nav_view.setLayoutParams(lp);
		//mahdi_313
		userposts.removeEventListener(_userposts_child_listener);
		userpost = "userposts/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		selfposts.removeEventListener(_selfposts_child_listener);
		selfpost = "userposts/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		userposts =
		_firebase.getReference(userpost);
		selfposts =
		_firebase.getReference(selfpost);
		userposts.addChildEventListener(_userposts_child_listener);
		selfposts.addChildEventListener(_selfposts_child_listener);
		image = FileUtil.getExternalStorageDir().concat("/chatapp/images/");
		video = FileUtil.getExternalStorageDir().concat("/chatapp/videos/");
		files = FileUtil.getExternalStorageDir().concat("/chatapp/files/");
		status = FileUtil.getExternalStorageDir().concat("/chatapp/status/");
		audios = FileUtil.getExternalStorageDir().concat("/chatapp/audios/");
		POSTS = FileUtil.getExternalStorageDir().concat("/chatapp/posts/");
		if (!FileUtil.isExistFile(image)) {
			FileUtil.makeDir(image);
		}
		if (!FileUtil.isExistFile(video)) {
			FileUtil.makeDir(video);
		}
		if (!FileUtil.isExistFile(files)) {
			FileUtil.makeDir(files);
		}
		if (!FileUtil.isExistFile(status)) {
			FileUtil.makeDir(status);
		}
		if (!FileUtil.isExistFile(audios)) {
			FileUtil.makeDir(audios);
		}
		if (!FileUtil.isExistFile(POSTS)) {
			FileUtil.makeDir(POSTS);
		}
		list_chat.removeEventListener(_list_chat_child_listener);
		list_chat_str = "chat list/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		list_chat = _firebase.getReference(list_chat_str);
		list_chat.addChildEventListener(_list_chat_child_listener);
		chatU1.removeEventListener(_chatU1_child_listener);
		chat_str = "chat";
		chatU1 = _firebase.getReference(chat_str);
		chatU1.addChildEventListener(_chatU1_child_listener);
		list_chat_ = true;
		share.edit().putString("list_chat_", "ya").commit();
		_setUserStatus("Online");
		
		OneSignal.startInit(Home_Activity.this)
		.inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
		.unsubscribeWhenNotificationsAreDisabled(false).init();
		OSPermissionSubscriptionState OS = OneSignal.getPermissionSubscriptionState();
		
		boolean isEnabled = OS.getPermissionStatus().getEnabled();
		boolean isSubscribed = OS.getSubscriptionStatus().getSubscribed();
		boolean subscriptionSetting = OS.getSubscriptionStatus().getUserSubscriptionSetting();
		String userID = OS.getSubscriptionStatus().getUserId();
		String pushToken = OS.getSubscriptionStatus().getPushToken();
		
		map = new HashMap<>();
		map.put("push_notif_id", userID);
		user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
		map.clear();
		imageview8.setVisibility(View.GONE);
		user.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				list_map = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						list_map.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				users.setAdapter(new UsersAdapter(list_map));
				((BaseAdapter)users.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		post.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				list_post = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						list_post.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				_SortMap(list_post, "push key", false, false);
				Collections.reverse(list_post);
				posts.setAdapter(new PostsAdapter(list_post));
				((BaseAdapter)posts.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		pause = true;
		share.edit().putString("list_chat_", "").commit();
		list_chat_ = false;
		cl = Calendar.getInstance();
		_setUserStatus("last seen at ".concat(new SimpleDateFormat("hh:mm a").format(cl.getTime()).concat("- ".concat(new SimpleDateFormat("dd:MMM").format(cl.getTime())))));
	}
	
	@Override
	public void onResume() {
		super.onResume();
		list_chat_ = true;
		share.edit().putString("list_chat_", "ya").commit();
		pause = false;
		_get_list_chat();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		share.edit().putString("list_chat_", "").commit();
		list_chat_ = false;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		_setUserStatus("Online");
		if (mode.getString("dark", "").equals("true")) {
			linear27.setBackgroundColor(0xFF000000);
			linear2.setBackgroundColor(0xFF000000);
			linear24.setBackgroundColor(0xFF000000);
			edittext1.setTextColor(0xFFFFFFFF);
			username.setTextColor(0xFFFFFFFF);
			text.setTextColor(0xFFFFFFFF);
			name.setTextColor(0xFFFFFFFF);
			textview3.setTextColor(0xFFFFFFFF);
			Follower.setTextColor(0xFFFFFFFF);
			textview4.setTextColor(0xFFFFFFFF);
			followin.setTextColor(0xFFFFFFFF);
			imageview9.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
			imageview8.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
			imageview7.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
			edittext1.setBackgroundColor(0xFF000000);
			linear29.setBackgroundColor(0xFFFFFFFF);
			_Shadow(5, 45, "#FFFFFF", linear29);
			_Shadow(5, 45, "#000000", edittext1);
			_drawer_linear3.setBackgroundColor(0xFF000000);
			_drawer_setting.setTextColor(0xFFFFFFFF);
			_drawer_edit_profile.setTextColor(0xFFFFFFFF);
			_drawer_upload_post.setTextColor(0xFFFFFFFF);
			_drawer_share.setTextColor(0xFFFFFFFF);
			_drawer_Logout.setTextColor(0xFFFFFFFF);
		}
		else {
			if (mode.getString("dark", "").equals("false")) {
				linear27.setBackgroundColor(0xFFFFFFFF);
				linear2.setBackgroundColor(0xFFFFFFFF);
				linear24.setBackgroundColor(0xFFFFFFFF);
				edittext1.setTextColor(0xFF000000);
				username.setTextColor(0xFF000000);
				text.setTextColor(0xFF000000);
				name.setTextColor(0xFF000000);
				textview3.setTextColor(0xFF000000);
				Follower.setTextColor(0xFF000000);
				textview4.setTextColor(0xFF000000);
				followin.setTextColor(0xFF000000);
				imageview9.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
				imageview8.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
				imageview7.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
				edittext1.setBackgroundColor(0xFFFFFFFF);
				linear29.setBackgroundColor(0xFF000000);
				_Shadow(5, 45, "#000000", linear29);
				_Shadow(5, 45, "#FFFFFF", edittext1);
				_drawer_linear3.setBackgroundColor(0xFFFFFFFF);
				_drawer_setting.setTextColor(0xFF000000);
				_drawer_edit_profile.setTextColor(0xFF000000);
				_drawer_upload_post.setTextColor(0xFF000000);
				_drawer_share.setTextColor(0xFF000000);
				_drawer_Logout.setTextColor(0xFF000000);
			}
		}
	}
	public void _UI_Logic_onCreat() {
		_NavStatusBarColor("#FFFFFF", "#FFFFFF");
		_DARK_ICONS();
		_shadAnim(linear2, "elevation", 10, 300);
		_RippleEffects("#F5F5F5", imageview_post);
		_RippleEffects("#F5F5F5", imageview_home);
		_RippleEffects("#F5F5F5", imageview_noti);
		_RippleEffects("#F5F5F5", imageview_sreach);
		_RippleEffects("#F5F5F5", imageview_profile);
		_TabStatus(imageview_home, true);
		_TabStatus(imageview_post, false);
		_TabStatus(imageview_noti, false);
		_TabStatus(imageview_sreach, false);
		_TabStatus(imageview_profile, false);
		_setBgCorners(linear2, 0, "#F2FFFFFF");
		linear_post.setVisibility(View.GONE);
		linear_noti.setVisibility(View.GONE);
		linear_home.setVisibility(View.VISIBLE);
		linear_sreach.setVisibility(View.GONE);
		linear_profile.setVisibility(View.GONE);
	}
	
	
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _DARK_ICONS() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
	}
	
	
	public void _RippleEffects(final String _color, final View _view) {
		android.content.res.ColorStateList clr = new android.content.res.ColorStateList(new int[][]{new int[]{}},new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdr = new android.graphics.drawable.RippleDrawable(clr, null, null);
		_view.setBackground(ripdr);
	}
	
	
	public void _TabStatus(final ImageView _ic, final boolean _st) {
		if (_st) {
			_ICC(_ic, "#00B7C3", "#D8DCE8");
			_shadAnim(_ic, "scaleY", 1.1d, 200);
			_shadAnim(_ic, "scaleX", 1.1d, 200);
		}
		else {
			_ICC(_ic, "#D8DCE8", "#00B7C3");
			_shadAnim(_ic, "scaleY", 0.95d, 200);
			_shadAnim(_ic, "scaleX", 0.95d, 200);
		}
	}
	
	
	public void _setBgCorners(final View _view, final double _radius, final String _color) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
		gd.setColor(Color.parseColor("#" + _color.replace("#", ""))); /* color */
		gd.setCornerRadius((int)_radius); /* radius */
		gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
		_view.setBackground(gd);
	}
	
	
	public void _shadAnim(final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_view);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.start();
	}
	
	
	public void _clickAnim(final View _view) {
		_view.setOnTouchListener(new OnTouchListener() { @Override public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:{
						_shadAnim(_view, "elevation", 8, 100);
						_shadAnim(_view, "scaleX", 1.4d, 100);
						_shadAnim(_view, "scaleY", 1.4d, 100);
						break;}
					case MotionEvent.ACTION_UP:{
						_shadAnim(_view, "elevation", 5, 100);
						_shadAnim(_view, "scaleX", 1.0d, 100);
						_shadAnim(_view, "scaleY", 1.0d, 100);
						break;}}
				return false; } });
	}
	
	
	public void _ICC(final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}
	
	
	public void _DG_DrawerTransparent() {
		final LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view); _nav_view.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
		//mahdi_313
	}
	
	
	public void _hide() {
		try{ 
			getSupportActionBar().hide(); 
		} catch (Exception e){}
		//mahdi_313
	}
	
	
	public void _Shadow(final double _sadw, final double _cru, final String _wc, final View _widgets) {
		android.graphics.drawable.GradientDrawable wd = new android.graphics.drawable.GradientDrawable();
		wd.setColor(Color.parseColor(_wc));
		wd.setCornerRadius((int)_cru);
		_widgets.setElevation((int)_sadw);
		_widgets.setBackground(wd);
	}
	
	
	public void _checkIfRead(final double _position, final String _data, final View _view, final TextView _text) {
		if (!share.getString(_data, "").equals(lm_list_chat.get((int)_position).get("push key").toString())) {
			_text.setTypeface(_text.getTypeface(), Typeface.BOLD);
		}
		else {
			_text.setTypeface(_text.getTypeface(), Typeface.NORMAL);
		}
	}
	
	
	public void _SortMap(final ArrayList<HashMap<String, Object>> _listMap, final String _key, final boolean _isNumber, final boolean _Ascending) {
		final Object _keyObject = _key;
		Collections.sort(_listMap, new Comparator<HashMap<String,Object>>(){
			public int compare(HashMap<String,Object> _compareMap1, HashMap<String,Object> _compareMap2){
				if (_isNumber) {
					int _count1 = Integer.valueOf(_compareMap1.get(_key).toString());
					int _count2 = Integer.valueOf(_compareMap2.get(_key).toString());
					if (_Ascending) {
						return _count1 < _count2 ? -1 : _count1 < _count2 ? 1 : 0;
					}
					else {
						return _count1 > _count2 ? -1 : _count1 > _count2 ? 1 : 0;
					}
				}
				else {
					if (_Ascending) {
						return (_compareMap1.get(_key).toString()).compareTo(_compareMap2.get(_key).toString());
					}
					else {
						return (_compareMap2.get(_key).toString()).compareTo(_compareMap1.get(_key).toString());
					}
				}
			}});
		///Use true or false blocks if sorting number of listmap
	}
	
	
	public void _Share() {
	}
	private void shareApplication() { 
		      android.content.pm.ApplicationInfo app = 
		      getApplicationContext().getApplicationInfo(); 
		      String filePath = app.sourceDir;
		      Intent intent = new Intent(Intent.ACTION_SEND); 
		      intent.setType("*/*"); 
		      java.io.File originalApk = new java.io.File(filePath); 
		      try {  
			        java.io.File tempFile = new java.io.File(getExternalCacheDir() + "/ExtractedApk"); 
			         if (!tempFile.isDirectory()) 
			         if (!tempFile.mkdirs()) 
			         return; 
			         tempFile = new java.io.File(tempFile.getPath() + "/" + 
			         "export.apk");
			         if (!tempFile.exists()) 
			          {
				           try{
					             if (!tempFile.createNewFile()) { 
						               return; }
					            }
				           catch (java.io.IOException e){} 
				          } 
			         java.io.InputStream in = new java.io.FileInputStream (originalApk);
			         java.io.OutputStream out = new java.io.FileOutputStream(tempFile);
			         byte[] buf = new byte[1024];
			         int len; 
			         while ((len = in.read(buf)) > 0) { 
				            out.write(buf, 0, len); 
				         } 
			         in.close(); 
			         out.close(); 
			         intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
			         startActivity(Intent.createChooser(intent, "Share app via"));
			      } 
		      catch (java.io.IOException e) 
		      { showMessage(e.toString()); 
			      } 
		   }
	{
	}
	
	
	public void _get_chat(final HashMap<String, Object> _childValue) {
		if (!pause) {
			lm_list_chat.add(_childValue);
			_SortMap(lm_list_chat, "push key", false, false);
			chat.setAdapter(new ChatAdapter(lm_list_chat));
			((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
		}
	}
	
	
	public void _get_list_chat() {
		
	}
	
	
	public void _deleteChat(final double _position) {
		dialog.setMessage("Are you sure want to delete this Conversation?");
		dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				if (lm_list_chat.get((int)_position).containsKey("uid") && lm_list_chat.get((int)_position).containsKey("uid2")) {
					if (lm_list_chat.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						chatU1.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(lm_list_chat.get((int)_position).get("uid2").toString()))).removeValue();
						list_chat.child(lm_list_chat.get((int)_position).get("uid2").toString()).removeValue();
						pesan.edit().putString(lm_list_chat.get((int)_position).get("uid2").toString(), "").commit();
						lm_list_chat.remove((int)(_position));
						share.edit().putString("list chat", new Gson().toJson(lm_list_chat)).commit();
						_SortMap(lm_list_chat, "push key", false, false);
						chat.setAdapter(new ChatAdapter(lm_list_chat));
						((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
					}
					else {
						chatU1.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(lm_list_chat.get((int)_position).get("uid").toString()))).removeValue();
						list_chat.child(lm_list_chat.get((int)_position).get("uid").toString()).removeValue();
						pesan.edit().putString(lm_list_chat.get((int)_position).get("uid").toString(), "").commit();
						lm_list_chat.remove((int)(_position));
						share.edit().putString("list chat", new Gson().toJson(lm_list_chat)).commit();
						_SortMap(lm_list_chat, "push key", false, false);
						chat.setAdapter(new ChatAdapter(lm_list_chat));
						((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
					}
				}
			}
		});
		dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		dialog.create().show();
	}
	
	
	public void _muteChat(final double _position, final boolean _mute_chat) {
		if (_mute_chat) {
			if (lm_list_chat.get((int)_position).containsKey("uid") && lm_list_chat.get((int)_position).containsKey("uid2")) {
				if (lm_list_chat.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					mutes.edit().putString(lm_list_chat.get((int)_position).get("uid2").toString(), "true").commit();
					_SortMap(lm_list_chat, "push key", false, false);
					chat.setAdapter(new ChatAdapter(lm_list_chat));
					((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
				}
				else {
					mutes.edit().putString(lm_list_chat.get((int)_position).get("uid").toString(), "true").commit();
					_SortMap(lm_list_chat, "push key", false, false);
					chat.setAdapter(new ChatAdapter(lm_list_chat));
					((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
				}
			}
		}
		else {
			if (lm_list_chat.get((int)_position).containsKey("uid") && lm_list_chat.get((int)_position).containsKey("uid2")) {
				if (lm_list_chat.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					mutes.edit().remove(lm_list_chat.get((int)_position).get("uid2").toString()).commit();
					_SortMap(lm_list_chat, "push key", false, false);
					chat.setAdapter(new ChatAdapter(lm_list_chat));
					((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
				}
				else {
					mutes.edit().remove(lm_list_chat.get((int)_position).get("uid").toString()).commit();
					_SortMap(lm_list_chat, "push key", false, false);
					chat.setAdapter(new ChatAdapter(lm_list_chat));
					((BaseAdapter)chat.getAdapter()).notifyDataSetChanged();
				}
			}
		}
	}
	
	
	public void _optionMenu(final double _position) {
		final AlertDialog dialog2 = new AlertDialog.Builder(Home_Activity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.options, null);
		dialog2.setView(inflate);
		dialog2.setCanceledOnTouchOutside(false);
		dialog2.setCancelable(false);
		
		dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		
		final TextView copy = (TextView) inflate.findViewById(R.id.textview1);
		
		final TextView edit = (TextView) inflate.findViewById(R.id.textview2);
		
		final TextView delete = (TextView) inflate.findViewById(R.id.textview3);
		
		final TextView share = (TextView) inflate.findViewById(R.id.textview4);
		
		final TextView report = (TextView) inflate.findViewById(R.id.textview5);
		
		final LinearLayout one = (LinearLayout) inflate.findViewById(R.id.lin1);
		
		final LinearLayout two = (LinearLayout) inflate.findViewById(R.id.lin2);
		
		final LinearLayout three = (LinearLayout) inflate.findViewById(R.id.lin3);
		
		final LinearLayout four = (LinearLayout) inflate.findViewById(R.id.lin4);
		
		final LinearLayout five = (LinearLayout) inflate.findViewById(R.id.lin5);
		
		final TextView cancel = (TextView) inflate.findViewById(R.id.textview6);
		final LinearLayout main = (LinearLayout) inflate.findViewById(R.id.linear1);
		if (mode.getString("night", "").equals("true")) {
			_Shadow(5, 15, "#000000", main);
			one.setBackgroundColor(0x607D8B);
			two.setBackgroundColor(0x607D8B);
			three.setBackgroundColor(0x607D8B);
			four.setBackgroundColor(0x607D8B);
			five.setBackgroundColor(0x607D8B);
		}
		else {
			_Shadow(5, 15, "#FFFFFF", main);
		}
		if (list_post.get((int)_position).get("id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
			report.setVisibility(View.GONE);
			four.setVisibility(View.GONE);
			edit.setVisibility(View.VISIBLE);
			delete.setVisibility(View.VISIBLE);
		}
		else {
			one.setVisibility(View.GONE);
			two.setVisibility(View.GONE);
			edit.setVisibility(View.GONE);
			delete.setVisibility(View.GONE);
			report.setVisibility(View.VISIBLE);
		}
		copy.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				if (list_post.get((int)_position).containsKey("name")) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", " @".concat(list_post.get((int)_position).get("name").toString())));
				}
				SketchwareUtil.showMessage(getApplicationContext(), "Copied! ");
				dialog2.dismiss(); } });
		edit.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				i.setClass(getApplicationContext(), EditPostActivity.class);
				i.putExtra("img", list_post.get((int)_position).get("post").toString());
				i.putExtra("caption", list_post.get((int)_position).get("caption").toString());
				i.putExtra("key", list_post.get((int)_position).get("key").toString());
				i.putExtra("comment", list_post.get((int)_position).get("comment").toString());
				startActivity(i);
				dialog2.dismiss(); } });
		delete.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				post.child(list_post.get((int)_position).get("key").toString()).removeValue();
				((BaseAdapter)posts.getAdapter()).notifyDataSetChanged();
				SketchwareUtil.showMessage(getApplicationContext(), "Post Deleted!");
				dialog2.dismiss(); } });
		share.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				shareimage = POSTS.concat(list_post.get((int)_position).get("post name").toString());
				if (FileUtil.isExistFile(POSTS.concat(list_post.get((int)_position).get("post name").toString()))) {
					_sharelmage(POSTS.concat(list_post.get((int)_position).get("post name").toString()));
				}
				else {
					_firebase_storage.getReferenceFromUrl(list_post.get((int)_position).get("post").toString()).getFile(new File(POSTS.concat(list_post.get((int)_position).get("post name").toString()))).addOnSuccessListener(_post__download_success_listener).addOnFailureListener(_post__failure_listener).addOnProgressListener(_post__download_progress_listener);
				}
				SketchwareUtil.showMessage(getApplicationContext(), "sharing... ");
				dialog2.dismiss(); } });
		report.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Post reported the admin will review soon! ");
				dialog2.dismiss(); } });
		cancel.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				dialog2.dismiss(); } });
		dialog2.show();
	}
	
	
	public void _Chat_Link(final TextView _Text_) {
		_Text_.setTextIsSelectable(true);
		_Text_.setClickable(true);
		android.text.util.Linkify.addLinks(_Text_, android.text.util.Linkify.ALL);
		_Text_.setLinkTextColor(Color.parseColor("#517DA4"));
		_Text_.setLinksClickable(true);
	}
	
	
	public void _sharelmage(final String _path) {
		Uri imgPath= Uri.parse(_path);
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_STREAM, imgPath);
		sendIntent.setType("image/png");
		Intent shareIntent = Intent.createChooser(sendIntent, null);
		startActivity(shareIntent);
	}
	
	
	public void _setUserStatus(final String _str) {
		if (!_str.equals(STATUS)) {
			sts = new HashMap<>();
			sts.put("status", _str);
			userstatus.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(sts);
			STATUS = _str;
		}
	}
	
	
	public void _hideKeyboard() {
		new Thread(new Runnable() {
			@Override public void run() {
				try {
					
					android.view.View view = Home_Activity.this.getCurrentFocus();   android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
					
				} catch(Exception _e){}
			}
		}).start(); // Start thread
	}
	
	public class PostsAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public PostsAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.post, null);
			}
			
			final LinearLayout linear15 = _view.findViewById(R.id.linear15);
			final LinearLayout linear19 = _view.findViewById(R.id.linear19);
			final LinearLayout linear18 = _view.findViewById(R.id.linear18);
			final LinearLayout linear17 = _view.findViewById(R.id.linear17);
			final TextView textview = _view.findViewById(R.id.textview);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			final LinearLayout linear16 = _view.findViewById(R.id.linear16);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview2 = _view.findViewById(R.id.circleimageview2);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final ImageView imageview8 = _view.findViewById(R.id.imageview8);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final ImageView imageview7 = _view.findViewById(R.id.imageview7);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final ImageView comment = _view.findViewById(R.id.comment);
			
			if (mode.getString("dark", "").equals("true")) {
				linear15.setBackgroundColor(0xFF000000);
				textview.setTextColor(0xFFFFFFFF);
				textview3.setTextColor(0xFFFFFFFF);
				textview1.setTextColor(0xFFFFFFFF);
				textview2.setTextColor(0xFFFFFFFF);
				imageview7.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				comment.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
			}
			else {
				if (mode.getString("dark", "").equals("false")) {
					linear15.setBackgroundColor(0xFFFFFFFF);
					textview.setTextColor(0xFF000000);
					textview3.setTextColor(0xFF000000);
					textview1.setTextColor(0xFF000000);
					textview2.setTextColor(0xFF000000);
					imageview7.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
					imageview1.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
					comment.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
				}
			}
			textview3.setVisibility(View.GONE);
			textview1.setText(user_data.get((int)uids.indexOf(_data.get((int)_position).get("id").toString())).get("name").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(user_data.get((int)uids.indexOf(_data.get((int)_position).get("id").toString())).get("pic").toString())).into(circleimageview2);
			if (list_post.get((int)_position).containsKey("caption")) {
				textview.setText(list_post.get((int)_position).get("caption").toString());
				textview.setVisibility(View.VISIBLE);
			}
			else {
				textview.setVisibility(View.GONE);
			}
			if (list_post.get((int)_position).containsKey("post")) {
				Glide.with(getApplicationContext()).load(Uri.parse(list_post.get((int)_position).get("post").toString())).into(imageview3);
			}
			if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("id").toString())).get("verification").toString().equals("true")) {
				imageview8.setVisibility(View.VISIBLE);
			}
			else {
				if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("id").toString())).get("verification").toString().equals("false")) {
					imageview8.setVisibility(View.GONE);
				}
			}
			if (list_post.get((int)_position).containsKey("mention")) {
				textview3.setVisibility(View.VISIBLE);
				map = new Gson().fromJson(_data.get((int)_position).get("mention").toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
				textview3.setText(" @".concat(map.get("name").toString()));
				textview3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						pos.putExtra("uid", map.get("id").toString());
						pos.setClass(getApplicationContext(), DetailsActivity.class);
						startActivity(pos);
					}
				});
			}
			else {
				textview3.setVisibility(View.GONE);
			}
			imageview3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					pos.setClass(getApplicationContext(), FullPicActivity.class);
					pos.putExtra("post", list_post.get((int)_position).get("post").toString());
					startActivity(pos);
				}
			});
			textview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					pos.setClass(getApplicationContext(), DetailsActivity.class);
					pos.putExtra("uid", list_post.get((int)_position).get("id").toString());
					startActivity(pos);
				}
			});
			imageview7.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					_optionMenu(_position);
				}
			});
			comment.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					pos.setClass(getApplicationContext(), CommentActivity.class);
					pos.putExtra("key", list_post.get((int)_position).get("key").toString());
					startActivity(pos);
				}
			});
			if (list_post.get((int)_position).get("comment").toString().equals("Comment on")) {
				comment.setVisibility(View.VISIBLE);
				comment.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						pos.setClass(getApplicationContext(), CommentActivity.class);
						pos.putExtra("key", list_post.get((int)_position).get("key").toString());
						startActivity(pos);
					}
				});
			}
			else {
				if (list_post.get((int)_position).get("comment").toString().equals("Comment off")) {
					comment.setVisibility(View.INVISIBLE);
				}
			}
			map = list_post.get((int)_position);
			SketchwareUtil.getAllKeysFromMap(map, postKeys);
			n = 0;
			likes = 0;
			for(int _repeat555 = 0; _repeat555 < (int)(postKeys.size()); _repeat555++) {
				if (map.get(postKeys.get((int)(n))).toString().equals("true")) {
					likes++;
				}
				n++;
			}
			textview2.setText(String.valueOf((long)(likes)));
			if (map.containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
				if (map.get(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString().equals("true")) {
					imageview1.setImageResource(R.drawable.ic_favorite_black);
				}
				else {
					imageview1.setImageResource(R.drawable.ic_favorite_outline_black);
				}
			}
			else {
				imageview1.setImageResource(R.drawable.ic_favorite_outline_black);
			}
			imageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (list_post.get((int)_position).containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (list_post.get((int)_position).get(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString().equals("true")) {
							mapvar = new HashMap<>();
							mapvar.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "false");
							post.child(postlist.get((int)(_position))).updateChildren(mapvar);
						}
						else {
							mapvar = new HashMap<>();
							mapvar.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "true");
							post.child(postlist.get((int)(_position))).updateChildren(mapvar);
						}
					}
					else {
						mapvar = new HashMap<>();
						mapvar.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "true");
						post.child(postlist.get((int)(_position))).updateChildren(mapvar);
					}
				}
			});
			if (list_post.get((int)_position).containsKey("time")) {
				get = Calendar.getInstance();
				from.setTimeInMillis((long)(Double.parseDouble(list_post.get((int)_position).get("time").toString())));
				duration = (long)(get.getTimeInMillis() - from.getTimeInMillis());
				get.setTimeInMillis((long)((long)(get.getTimeInMillis() - from.getTimeInMillis())));
				if ((duration == 1000) || (1000 < duration)) {
					textview4.setText(new SimpleDateFormat("s").format(get.getTime()).concat(" seconds ago"));
				}
				if ((duration == 60000) || (60000 < duration)) {
					textview4.setText(new SimpleDateFormat("m").format(get.getTime()).concat(" minutes ago"));
				}
				if ((duration == 3600000) || (3600000 < duration)) {
					textview4.setText(new SimpleDateFormat("h").format(get.getTime()).concat(" hours ago"));
				}
				if ((duration == 86400000) || (86400000 < duration)) {
					textview4.setText(new SimpleDateFormat("d").format(get.getTime()).concat(" days ago"));
				}
				if ((duration == 2592000000d) || (2592000000d < duration)) {
					textview4.setText(new SimpleDateFormat("M").format(get.getTime()).concat(" months ago"));
				}
			}
			else {
				textview4.setText("");
			}
			
			return _view;
		}
	}
	
	public class NoticeAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public NoticeAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.notification, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			
			if (mode.getString("dark", "").equals("true")) {
				linear1.setBackgroundColor(0xFF263238);
				textview1.setTextColor(0xFFFFFFFF);
				textview3.setTextColor(0xFFFFFFFF);
			}
			else {
				if (mode.getString("dark", "").equals("false")) {
					linear1.setBackgroundColor(0xFFFFFFFF);
					textview1.setTextColor(0xFF000000);
					textview3.setTextColor(0xFF000000);
				}
			}
			if (_data.get((int)_position).get("id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
				Glide.with(getApplicationContext()).load(Uri.parse(user_data.get((int)uids.indexOf(_data.get((int)_position).get("sender id").toString())).get("pic").toString())).into(circleimageview1);
				textview1.setText(user_data.get((int)uids.indexOf(_data.get((int)_position).get("sender id").toString())).get("name").toString());
				if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("sender id").toString())).get("verification").toString().equals("true")) {
					imageview1.setVisibility(View.VISIBLE);
				}
				else {
					if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("sender id").toString())).get("verification").toString().equals("false")) {
						imageview1.setVisibility(View.GONE);
					}
				}
				linear1.setVisibility(View.VISIBLE);
			}
			else {
				linear1.setVisibility(View.GONE);
			}
			
			return _view;
		}
	}
	
	public class ChatAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public ChatAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.chats, null);
			}
			
			final LinearLayout linear_main = _view.findViewById(R.id.linear_main);
			final de.hdodenhof.circleimageview.CircleImageView im_avatar = _view.findViewById(R.id.im_avatar);
			final LinearLayout l_data = _view.findViewById(R.id.l_data);
			final TextView time = _view.findViewById(R.id.time);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView tx_lasted_message = _view.findViewById(R.id.tx_lasted_message);
			final TextView tx_name = _view.findViewById(R.id.tx_name);
			final ImageView im_verified = _view.findViewById(R.id.im_verified);
			final ImageView im_mute = _view.findViewById(R.id.im_mute);
			
			if (mode.getString("dark", "").equals("true")) {
				linear_main.setBackgroundColor(0xFF263238);
				time.setTextColor(0xFFFFFFFF);
				tx_lasted_message.setTextColor(0xFFFFFFFF);
				tx_name.setTextColor(0xFFFFFFFF);
				im_mute.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
			}
			else {
				if (mode.getString("dark", "").equals("false")) {
					linear_main.setBackgroundColor(0xFFFFFFFF);
					time.setTextColor(0xFF000000);
					tx_lasted_message.setTextColor(0xFF000000);
					tx_name.setTextColor(0xFF000000);
					im_mute.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
				}
			}
			im_verified.setVisibility(View.INVISIBLE);
			im_mute.setVisibility(View.INVISIBLE);
			try {
				chat.setVisibility(View.VISIBLE);
				if (!_data.get((int)_position).containsKey("id")) {
					if (_data.get((int)_position).containsKey("lasted")) {
						tx_lasted_message.setText(_data.get((int)_position).get("lasted").toString());
					}
					if (_data.get((int)_position).containsKey("uid2") && _data.get((int)_position).containsKey("uid")) {
						if (_data.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid2").toString())).get("verification").toString().equals("true")) {
								im_verified.setVisibility(View.VISIBLE);
							}
							else {
								im_verified.setVisibility(View.GONE);
							}
							tx_name.setText(user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid2").toString())).get("username").toString());
							avatar_url = user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid2").toString())).get("pic").toString();
							Glide.with(getApplicationContext()).load(Uri.parse(avatar_url)).into(im_avatar);
							if (mutes.getString(_data.get((int)_position).get("uid2").toString(), "").contains("true")) {
								im_mute.setVisibility(View.VISIBLE);
							}
							else {
								im_mute.setVisibility(View.GONE);
							}
							_checkIfRead(_position, _data.get((int)_position).get("uid2").toString(), linear_main, tx_lasted_message);
							try {
							}catch(Exception e){
							}
						}
						else {
							if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("verification").toString().equals("true")) {
								im_verified.setVisibility(View.VISIBLE);
							}
							else {
								im_verified.setVisibility(View.GONE);
							}
							tx_name.setText(user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("username").toString());
							avatar_url = user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("pic").toString();
							Glide.with(getApplicationContext()).load(Uri.parse(avatar_url)).into(im_avatar);
							if (mutes.getString(_data.get((int)_position).get("uid").toString(), "").contains("true")) {
								im_mute.setVisibility(View.VISIBLE);
							}
							else {
								im_mute.setVisibility(View.GONE);
							}
							_checkIfRead(_position, _data.get((int)_position).get("uid").toString(), linear_main, tx_lasted_message);
							try {
							}catch(Exception e){
							}
						}
					}
				}
				else {
					try {
						if (_data.get((int)_position).containsKey("id")) {
							if (group_data.get((int)my_available_groups.indexOf(_data.get((int)_position).get("id").toString())).get("verified").toString().equals("true")) {
								im_verified.setVisibility(View.VISIBLE);
							}
							else {
								im_verified.setVisibility(View.GONE);
							}
							tx_name.setText(group_data.get((int)my_available_groups.indexOf(_data.get((int)_position).get("id").toString())).get("name").toString());
							if (group_data.get((int)my_available_groups.indexOf(_data.get((int)_position).get("id").toString())).get("profile").toString().equals("null")) {
								im_avatar.setImageResource(R.drawable.upload_1);
							}
							else {
								Glide.with(getApplicationContext()).load(Uri.parse(group_data.get((int)my_available_groups.indexOf(_data.get((int)_position).get("id").toString())).get("profile").toString())).into(im_avatar);
							}
							try {
								if (share.getString(_data.get((int)_position).get("id").toString(), "").equals("")) {
									tx_lasted_message.setText(_data.get((int)_position).get(_data.get((int)_position).get("id").toString()).toString());
								}
								else {
									if (share.getString(_data.get((int)_position).get("id").toString(), "").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
										tx_lasted_message.setText("You : ".concat(_data.get((int)_position).get(_data.get((int)_position).get("id").toString()).toString()));
									}
									else {
										tx_lasted_message.setText(user_data.get((int)uids.indexOf(share.getString(_data.get((int)_position).get("id").toString(), ""))).get("name").toString().concat(" : ").concat(_data.get((int)_position).get(_data.get((int)_position).get("id").toString()).toString()));
									}
								}
							}catch(Exception e){}
						}
					}catch(Exception e){}
					im_mute.setVisibility(View.GONE);
				}
			}catch(Exception e){}
			
			return _view;
		}
	}
	
	public class UsersAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public UsersAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.user_list, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			if (mode.getString("dark", "").equals("true")) {
				linear1.setBackgroundColor(0xFF263238);
				textview2.setTextColor(0xFFFFFFFF);
				textview1.setTextColor(0xFFFFFFFF);
			}
			else {
				if (mode.getString("dark", "").equals("false")) {
					linear1.setBackgroundColor(0xFFFFFFFF);
					textview2.setTextColor(0xFF000000);
					textview1.setTextColor(0xFF000000);
				}
			}
			if (list_map.get((int)_position).containsKey("name")) {
				textview1.setText(list_map.get((int)_position).get("name").toString());
			}
			if (list_map.get((int)_position).containsKey("username")) {
				textview2.setText(list_map.get((int)_position).get("username").toString());
			}
			if (list_map.get((int)_position).containsKey("pic")) {
				Glide.with(getApplicationContext()).load(Uri.parse(list_map.get((int)_position).get("pic").toString())).into(circleimageview1);
			}
			if (list_map.get((int)_position).get("verification").toString().equals("true")) {
				imageview1.setVisibility(View.VISIBLE);
			}
			else {
				if (list_map.get((int)_position).get("verification").toString().equals("false")) {
					imageview1.setVisibility(View.GONE);
				}
			}
			
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