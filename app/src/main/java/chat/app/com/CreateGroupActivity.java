package chat.app.com;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.Context;
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
import android.os.Vibrator;
import android.text.*;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stdio.swipetoreply.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class CreateGroupActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private boolean cancel = false;
	private String colorCode = "";
	private double selectCount = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private String GROUP_KEY = "";
	private double n = 0;
	private String profilePath = "";
	private String profileName = "";
	private String avatarUrl = "";
	private String CONTACTS_PATH = "";
	
	private ArrayList<String> uids = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> user_data = new ArrayList<>();
	private ArrayList<String> select_states = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> contact_list = new ArrayList<>();
	
	private LinearLayout linear_main;
	private LinearLayout l_item;
	private LinearLayout linear1;
	private ListView listview1;
	private LinearLayout l_pic;
	private EditText tx_group_name;
	private ProgressBar progressbar1;
	private CircleImageView circleimageview1;
	private TextView tx_participant_count;
	
	private DatabaseReference user = _firebase.getReference("user");
	private ChildEventListener _user_child_listener;
	private TimerTask time;
	private Vibrator v;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference profile = _firebase_storage.getReference("profile");
	private OnCompleteListener<Uri> _profile_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _profile_download_success_listener;
	private OnSuccessListener _profile_delete_success_listener;
	private OnProgressListener _profile_upload_progress_listener;
	private OnProgressListener _profile_download_progress_listener;
	private OnFailureListener _profile_failure_listener;
	
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
	
	private DatabaseReference groups = _firebase.getReference("groups");
	private ChildEventListener _groups_child_listener;
	private Intent i = new Intent();
	private DatabaseReference group_list = _firebase.getReference("group_list");
	private ChildEventListener _group_list_child_listener;
	private DatabaseReference allusers = _firebase.getReference("allusers");
	private ChildEventListener _allusers_child_listener;
	private SharedPreferences file;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.create_group);
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
		_fab = findViewById(R.id._fab);
		
		linear_main = findViewById(R.id.linear_main);
		l_item = findViewById(R.id.l_item);
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		l_pic = findViewById(R.id.l_pic);
		tx_group_name = findViewById(R.id.tx_group_name);
		progressbar1 = findViewById(R.id.progressbar1);
		circleimageview1 = findViewById(R.id.circleimageview1);
		tx_participant_count = findViewById(R.id.tx_participant_count);
		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fauth = FirebaseAuth.getInstance();
		file = getSharedPreferences("file", Activity.MODE_PRIVATE);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (!contact_list.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (select_states.get((int)(_position)).equals("t")) {
						_listSet(select_states, _position, "f");
					}
					else {
						_listSet(select_states, _position, "t");
					}
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					selectCount = 0;
					n = 0;
					for(int _repeat27 = 0; _repeat27 < (int)(select_states.size()); _repeat27++) {
						if (select_states.get((int)(n)).equals("t")) {
							selectCount++;
						}
						n++;
					}
					tx_participant_count.setText("Participants: ".concat(String.valueOf((long)(selectCount))));
				}
			}
		});
		
		circleimageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (5 > tx_group_name.getText().toString().length()) {
					SketchwareUtil.CustomToast(getApplicationContext(), "Atleast 5 letters", 0xFFFFFFFF, 12, 0xFF424242, 60, SketchwareUtil.BOTTOM);
				}
				else {
					if (2 > selectCount) {
						v.vibrate((long)(300));
						SketchwareUtil.CustomToast(getApplicationContext(), "Add at least 2 participants to create group.", 0xFFFFFFFF, 12, 0xFF424242, 60, SketchwareUtil.BOTTOM);
					}
					else {
						_fabRotation(true);
						time = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										try {
											GROUP_KEY = groups.push().getKey();
											map = new HashMap<>();
											map.put("creator", FirebaseAuth.getInstance().getCurrentUser().getUid());
											map.put("id", GROUP_KEY);
											map.put("push key", group_list.push().getKey());
											map.put("name", tx_group_name.getText().toString().trim());
											map.put("profile", avatarUrl);
											ArrayList<HashMap<String, Object>> l_prtp = new ArrayList<>();
											for (int _i = 0; _i < select_states.size(); _i++) {
												if (select_states.get((int)(_i)).contains("t")) {
													{
														HashMap<String, Object> _item = new HashMap<>();
														_item.put("uid", contact_list.get((int)_i).get("uid").toString());
														l_prtp.add(_item);
													}
													
												}
											}
											{
												HashMap<String, Object> _item = new HashMap<>();
												_item.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
												l_prtp.add(_item);
											}
											
											map.put("participants", new Gson().toJson(l_prtp));
											map.put("total", String.valueOf((long)(l_prtp.size())));
											map.put(GROUP_KEY, String.valueOf((long)(l_prtp.size())).concat(" participants"));
											map.put("verified", "false");
											groups.child(GROUP_KEY).updateChildren(map);
											group_list.child(GROUP_KEY).updateChildren(map);
										}catch(Exception e){
											SketchwareUtil.showMessage(getApplicationContext(), e.toString());
										}
									}
								});
							}
						};
						_timer.schedule(time, (int)(1000));
						time = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										_fabRotation(false);
										SketchwareUtil.CustomToast(getApplicationContext(), "Created successfully.", 0xFFFFFFFF, 12, 0xFF424242, 60, SketchwareUtil.BOTTOM);
										i.putExtra("group_key", GROUP_KEY);
										i.setClass(getApplicationContext(), GroupChatActivity.class);
										startActivity(i);
										finish();
									}
								});
							}
						};
						_timer.schedule(time, (int)(3000));
					}
				}
			}
		});
		
		_user_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				uids.add(_childKey);
				user_data.add(_childValue);
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				uids.add(_childKey);
				user_data.add(_childValue);
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
		
		_profile_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_profile_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_profile_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				avatarUrl = _downloadUrl;
				l_pic.setEnabled(true);
				_fab.setEnabled(true);
				progressbar1.setVisibility(View.GONE);
				circleimageview1.setVisibility(View.VISIBLE);
			}
		};
		
		_profile_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_profile_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_profile_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_groups_child_listener = new ChildEventListener() {
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
		groups.addChildEventListener(_groups_child_listener);
		
		_group_list_child_listener = new ChildEventListener() {
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
		group_list.addChildEventListener(_group_list_child_listener);
		
		_allusers_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("json")) {
					file.edit().putString("data", _childValue.get("json").toString()).commit();
				}
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
		allusers.addChildEventListener(_allusers_child_listener);
		
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
		com.google.android.material.appbar.AppBarLayout appBar =
		    (com.google.android.material.appbar.AppBarLayout) _toolbar.getParent();
		appBar.setElevation(3f);
		appBar.setStateListAnimator(null);
		setTitle("Create Group");
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		progressbar1.setVisibility(View.GONE);
		_scroll(listview1);
		avatarUrl = "null";
		_rippleRoundStroke(l_pic, "#009688", "#FFFFFF", 500, 0, "#FFFFFF");
		_setProgressBarColor(progressbar1, "#FFFFFF");
		_Elevation(linear1, 1);
		CONTACTS_PATH = FileUtil.getExternalStorageDir().concat("/chatapp");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				progressbar1.setVisibility(View.VISIBLE);
				profilePath = _filePath.get((int)(0));
				profileName = Uri.parse(profilePath).getLastPathSegment();
				profile.child(profileName).putFile(Uri.fromFile(new File(profilePath))).addOnFailureListener(_profile_failure_listener).addOnProgressListener(_profile_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return profile.child(profileName).getDownloadUrl();
					}}).addOnCompleteListener(_profile_upload_success_listener);
				l_pic.setEnabled(false);
				_fab.setEnabled(false);
				circleimageview1.setVisibility(View.GONE);
				Glide.with(getApplicationContext()).load(Uri.parse(_filePath.get((int)(0)))).into(circleimageview1);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		_loadContacts();
	}
	public void _scroll(final View _view) {
		_view.setOnTouchListener(new View.OnTouchListener() {
			Boolean scroll;
			@Override public boolean onTouch(View v, MotionEvent event) {
				scroll = false;
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
					x = event.getY();
					scroll = false;
					case MotionEvent.ACTION_UP:
					
					y = event.getY();
					if (((x - y) < -15)) {
						
						_fab.show();
						
					} 
					if (((y - x) < -15)) {
						
						_fab.hide();
						
						
					}
					
					scroll = false;
				}
				return scroll;
			}
		});
	}
	private double x = 0;
	private double y = 0;
	{
		 
	}
	
	
	public void _fabRotation(final boolean _start) {
		if (_start) {
			_fab.setEnabled(false);
			cancel = false;
			_fab.setImageResource(R.drawable.loading_100);
			time = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (!cancel) {
								_fab.setRotation((float)(_fab.getRotation() + 10));
							}
						}
					});
				}
			};
			_timer.scheduleAtFixedRate(time, (int)(0), (int)(20));
		}
		else {
			_fab.setImageResource(R.drawable.ic_arrow_forward_white);
			time.cancel();
			cancel = true;
			_fab.setRotation((float)(0));
			_fab.setEnabled(true);
		}
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
		_view.setElevation((float)3);
	}
	
	
	public void _Shadow(final double _sadw, final double _cru, final String _wc, final View _widgets) {
		android.graphics.drawable.GradientDrawable wd = new android.graphics.drawable.GradientDrawable();
		wd.setColor(Color.parseColor(_wc));
		wd.setCornerRadius((int)_cru);
		_widgets.setElevation((int)_sadw);
		_widgets.setBackground(wd);
	}
	
	
	public void _setProgressBarColor(final ProgressBar _progressbar, final String _color) {
		if (android.os.Build.VERSION.SDK_INT >= 21) {
			_progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor(_color), PorterDuff.Mode.SRC_IN);
		}
	}
	
	
	public void _Elevation(final View _view, final double _number) {
		
		_view.setElevation((int)_number);
	}
	
	
	public void _listSet(final ArrayList<String> _list, final double _pos, final String _value) {
		_list.set((int)_pos, _value);
	}
	
	
	public void _Ripple_Effect(final View _v) {
		
		android.graphics.drawable.RippleDrawable ripdr1 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#BDBDBD")}), new android.graphics.drawable.ColorDrawable(Color.parseColor("#FFFFFF")), null);
		_v.setBackground(ripdr1);
		
		
		
		//HERE #BDBDBD is the color when clicked (ripple)
		
		//#FFFFFF is the background color of the view
		
		//_v. is the view of course :)
		
		//Join our fb group www.facebook.com/groups/sketchware
		
		//~Nikko~
	}
	
	
	public void _loadContacts() {
		try {
			if (FileUtil.isExistFile(CONTACTS_PATH)) {
				contact_list = new Gson().fromJson(file.getString("data", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				if (contact_list.size() > 0) {
					for(int _repeat33 = 0; _repeat33 < (int)(contact_list.size()); _repeat33++) {
						select_states.add("f");
					}
					listview1.setAdapter(new Listview1Adapter(contact_list));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				}
			}
		}catch(Exception e){
		}
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
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.contact, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final de.hdodenhof.circleimageview.CircleImageView im_avatar = _view.findViewById(R.id.im_avatar);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView im_select = _view.findViewById(R.id.im_select);
			final TextView tx_name = _view.findViewById(R.id.tx_name);
			final TextView tx_bio = _view.findViewById(R.id.tx_bio);
			
			im_select.setColorFilter(0xFF009688, PorterDuff.Mode.MULTIPLY);
			_Ripple_Effect(linear1);
			tx_name.setText(_data.get((int)_position).get("username").toString());
			if (contact_list.get((int)_position).containsKey("uid")) {
				tx_bio.setText(user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("bio").toString());
				Glide.with(getApplicationContext()).load(Uri.parse(user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("pic").toString())).into(im_avatar);
			}
			if (select_states.get((int)(_position)).equals("t")) {
				im_select.setVisibility(View.VISIBLE);
			}
			else {
				im_select.setVisibility(View.GONE);
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