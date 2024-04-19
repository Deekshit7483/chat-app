package chat.app.com;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.media.MediaPlayer;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Build;
import android.provider.MediaStore;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class GroupChatActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	public final int REQ_CD_FP2 = 102;
	public final int REQ_CD_FP3 = 103;
	public final int REQ_CD_CAM = 104;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String GROUP_KEY = "";
	private String outputFile = "";
	private double time_passed = 0;
	private String voice_duration = "";
	private boolean replyMode = false;
	private double ReplyPosition = 0;
	private String reply_uid = "";
	private String ddd = "";
	private String extension = "";
	private String STATUS = "";
	private HashMap<String, Object> sts = new HashMap<>();
	private boolean click = false;
	private String push_key = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String creator = "";
	private String total_member = "";
	private String imagePath = "";
	private String imageName = "";
	private String path = "";
	private String video_name = "";
	private String filePath = "";
	private String fileName = "";
	private double online_user = 0;
	private String thumb_String = "";
	
	private ArrayList<HashMap<String, Object>> group_list_chat = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> user_data = new ArrayList<>();
	private ArrayList<String> uids = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> members = new ArrayList<>();
	
	private LinearLayout linear_main;
	private LinearLayout tools;
	private ListView listview1;
	private LinearLayout linear_msg_main;
	private LinearLayout tool;
	private LinearLayout linear21;
	private LinearLayout linear_nm;
	private CircleImageView circleimageview1;
	private LinearLayout linear_n;
	private TextView group_status;
	private TextView group_name;
	private ImageView im_verified;
	private LinearLayout linear_message;
	private ImageView im_send;
	private LinearLayout linear_attach;
	private LinearLayout linear_reply;
	private LinearLayout linear1;
	private LinearLayout lin_document;
	private LinearLayout lin_camera;
	private LinearLayout lin_gallery;
	private ImageView imageview10;
	private TextView textview19;
	private ImageView imageview8;
	private TextView textview15;
	private ImageView imageview11;
	private TextView textview20;
	private LinearLayout linear20;
	private ImageView ig_reply_close;
	private TextView name_reply_out;
	private TextView text_reply_out;
	private ImageView im_send_img;
	private EditText tx_message;
	private ImageView im_attach_file;
	
	private DatabaseReference groups = _firebase.getReference("groups");
	private ChildEventListener _groups_child_listener;
	private Intent in = new Intent();
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
	
	private DatabaseReference group_list = _firebase.getReference("group_list");
	private ChildEventListener _group_list_child_listener;
	private StorageReference voice = _firebase_storage.getReference("voice");
	private OnCompleteListener<Uri> _voice_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _voice_download_success_listener;
	private OnSuccessListener _voice_delete_success_listener;
	private OnProgressListener _voice_upload_progress_listener;
	private OnProgressListener _voice_download_progress_listener;
	private OnFailureListener _voice_failure_listener;
	
	private TimerTask timer;
	private Calendar cal = Calendar.getInstance();
	private DatabaseReference group_chat = _firebase.getReference("group_chat");
	private ChildEventListener _group_chat_child_listener;
	private DatabaseReference user = _firebase.getReference("user");
	private ChildEventListener _user_child_listener;
	private AlertDialog.Builder alert;
	private StorageReference file = _firebase_storage.getReference("file");
	private OnCompleteListener<Uri> _file_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _file_download_success_listener;
	private OnSuccessListener _file_delete_success_listener;
	private OnProgressListener _file_upload_progress_listener;
	private OnProgressListener _file_download_progress_listener;
	private OnFailureListener _file_failure_listener;
	
	private StorageReference video = _firebase_storage.getReference("video");
	private OnCompleteListener<Uri> _video_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _video_download_success_listener;
	private OnSuccessListener _video_delete_success_listener;
	private OnProgressListener _video_upload_progress_listener;
	private OnProgressListener _video_download_progress_listener;
	private OnFailureListener _video_failure_listener;
	
	private StorageReference image = _firebase_storage.getReference("image");
	private OnCompleteListener<Uri> _image_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _image_download_success_listener;
	private OnSuccessListener _image_delete_success_listener;
	private OnProgressListener _image_upload_progress_listener;
	private OnProgressListener _image_download_progress_listener;
	private OnFailureListener _image_failure_listener;
	
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp2 = new Intent(Intent.ACTION_GET_CONTENT);
	private TimerTask tm4;
	private TimerTask tatta;
	private Intent fp3 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_cam;
	private DatabaseReference userstatus = _firebase.getReference("userstatus");
	private ChildEventListener _userstatus_child_listener;
	private MediaPlayer mediaPlayer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.group_chat);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		linear_main = findViewById(R.id.linear_main);
		tools = findViewById(R.id.tools);
		listview1 = findViewById(R.id.listview1);
		linear_msg_main = findViewById(R.id.linear_msg_main);
		tool = findViewById(R.id.tool);
		linear21 = findViewById(R.id.linear21);
		linear_nm = findViewById(R.id.linear_nm);
		circleimageview1 = findViewById(R.id.circleimageview1);
		linear_n = findViewById(R.id.linear_n);
		group_status = findViewById(R.id.group_status);
		group_name = findViewById(R.id.group_name);
		im_verified = findViewById(R.id.im_verified);
		linear_message = findViewById(R.id.linear_message);
		im_send = findViewById(R.id.im_send);
		linear_attach = findViewById(R.id.linear_attach);
		linear_reply = findViewById(R.id.linear_reply);
		linear1 = findViewById(R.id.linear1);
		lin_document = findViewById(R.id.lin_document);
		lin_camera = findViewById(R.id.lin_camera);
		lin_gallery = findViewById(R.id.lin_gallery);
		imageview10 = findViewById(R.id.imageview10);
		textview19 = findViewById(R.id.textview19);
		imageview8 = findViewById(R.id.imageview8);
		textview15 = findViewById(R.id.textview15);
		imageview11 = findViewById(R.id.imageview11);
		textview20 = findViewById(R.id.textview20);
		linear20 = findViewById(R.id.linear20);
		ig_reply_close = findViewById(R.id.ig_reply_close);
		name_reply_out = findViewById(R.id.name_reply_out);
		text_reply_out = findViewById(R.id.text_reply_out);
		im_send_img = findViewById(R.id.im_send_img);
		tx_message = findViewById(R.id.tx_message);
		im_attach_file = findViewById(R.id.im_attach_file);
		fauth = FirebaseAuth.getInstance();
		alert = new AlertDialog.Builder(this);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp2.setType("video/*");
		fp2.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp3.setType("*/*");
		fp3.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		_file_cam = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_cam;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_cam = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_cam);
		} else {
			_uri_cam = Uri.fromFile(_file_cam);
		}
		cam.putExtra(MediaStore.EXTRA_OUTPUT, _uri_cam);
		cam.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		
		im_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					if (tx_message.getText().toString().replace(" ", "").equals("")) {
						if (replyMode) {
							replyMode = false;
							linear_reply.setVisibility(View.GONE);
						}
					}
					else {
						cal = Calendar.getInstance();
						if (replyMode) {
							push_key = group_list.push().getKey();
							map = new HashMap<>();
							map.put("message", tx_message.getText().toString());
							map.put(GROUP_KEY, tx_message.getText().toString());
							map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
							map.put("id", GROUP_KEY);
							map.put("push key", push_key);
							map.put("reply", "true");
							map.put("reply_position", String.valueOf((long)(ReplyPosition)));
							map.put("reply_uid", reply_uid);
							map.put("reply_message", text_reply_out.getText().toString());
							map.put("time", new SimpleDateFormat("hh:mm a").format(cal.getTime()));
							group_chat.child(push_key).updateChildren(map);
							group_list.child(GROUP_KEY).updateChildren(map);
							tx_message.setText("");
						}
						else {
							push_key = group_list.push().getKey();
							map = new HashMap<>();
							map.put("message", tx_message.getText().toString());
							map.put(GROUP_KEY, tx_message.getText().toString());
							map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
							map.put("id", GROUP_KEY);
							map.put("push key", push_key);
							map.put("time", new SimpleDateFormat("hh:mm a").format(cal.getTime()));
							map.put("timestamp", String.valueOf((long)(cal.getTimeInMillis())));
							group_chat.child(push_key).updateChildren(map);
							group_list.child(GROUP_KEY).updateChildren(map);
							tx_message.setText("");
						}
					}
					_Shadow(2, 60, "#FFFFFF", linear_message);
					if (replyMode) {
						replyMode = false;
						linear_reply.setVisibility(View.GONE);
					}
					if (click) {
						click = false;
						linear_attach.setVisibility(View.GONE);
					}
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
		});
		
		lin_document.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp3, REQ_CD_FP3);
				click = false;
				linear_attach.setVisibility(View.GONE);
				_Shadow(2, 60, "#FFFFFF", linear_message);
			}
		});
		
		lin_camera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(cam, REQ_CD_CAM);
				click = false;
				linear_attach.setVisibility(View.GONE);
				_Shadow(2, 60, "#FFFFFF", linear_message);
			}
		});
		
		lin_gallery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp2, REQ_CD_FP2);
				click = false;
				linear_attach.setVisibility(View.GONE);
				_Shadow(2, 60, "#FFFFFF", linear_message);
			}
		});
		
		ig_reply_close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				replyMode = false;
				linear_reply.setVisibility(View.GONE);
				_Shadow(2, 60, "#FFFFFF", linear_message);
			}
		});
		
		im_send_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
				if (replyMode) {
					replyMode = false;
					linear_reply.setVisibility(View.GONE);
				}
				if (click) {
					click = false;
					linear_attach.setVisibility(View.GONE);
				}
				_Shadow(2, 60, "#FFFFFF", linear_message);
			}
		});
		
		tx_message.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.replace(" ", "").equals("")) {
					im_send.setImageResource(R.drawable.ic_mic_none_white);
				}
				else {
					im_send.setImageResource(R.drawable.outline_send);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		im_attach_file.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (click) {
					linear_attach.setVisibility(View.GONE);
					click = false;
					_Shadow(2, 60, "#FFFFFF", linear_message);
					_TransitionManager(linear_main, 200);
				}
				else {
					linear_attach.setVisibility(View.VISIBLE);
					click = true;
					_Shadow(2, 40, "#FFFFFF", linear_message);
					_TransitionManager(linear_main, 200);
				}
				if (replyMode) {
					replyMode = false;
					linear_reply.setVisibility(View.GONE);
					_Shadow(2, 60, "#FFFFFF", linear_message);
					_TransitionManager(linear_main, 200);
				}
			}
		});
		
		_groups_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(GROUP_KEY)) {
					creator = _childValue.get("creator").toString();
					members = new Gson().fromJson(_childValue.get("participants").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					if (_childValue.containsKey("name")) {
						group_name.setText(_childValue.get("name").toString());
					}
					if (_childValue.containsKey("profile")) {
						if (_childValue.get("profile").toString().equals("null")) {
							circleimageview1.setImageResource(R.drawable.upload_1);
						}
						else {
							Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("profile").toString())).into(circleimageview1);
						}
					}
					if (_childValue.containsKey("total")) {
						group_status.setText(_childValue.get("total").toString().concat(" participants"));
						total_member = _childValue.get("total").toString().concat(" participants");
					}
					if (_childValue.get("verified").toString().equals("true")) {
						im_verified.setVisibility(View.VISIBLE);
					}
					else {
						im_verified.setVisibility(View.GONE);
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(GROUP_KEY)) {
					members = new Gson().fromJson(_childValue.get("participants").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					if (_childValue.containsKey("name")) {
						group_name.setText(_childValue.get("name").toString());
					}
					if (_childValue.containsKey("profile")) {
						if (_childValue.get("profile").toString().equals("null")) {
							circleimageview1.setImageResource(R.drawable.upload_1);
						}
						else {
							Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("profile").toString())).into(circleimageview1);
						}
					}
					if (_childValue.containsKey("total")) {
						group_status.setText(_childValue.get("total").toString().concat(" participants"));
						total_member = _childValue.get("total").toString().concat(" participants");
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
		
		_voice_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_voice_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_voice_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_voice_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_voice_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_voice_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_group_chat_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("id").toString().equals(GROUP_KEY)) {
					try {
						group_list_chat.add(_childValue);
						listview1.setAdapter(new Listview1Adapter(group_list_chat));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						listview1.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); listview1.setStackFromBottom(true);
					}catch(Exception e){
						SketchwareUtil.showMessage(getApplicationContext(), e.toString());
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (GROUP_KEY.equals(_childValue.get("id").toString())) {
					try {
						group_list_chat.add(_childValue);
						listview1.setAdapter(new Listview1Adapter(group_list_chat));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						listview1.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); listview1.setStackFromBottom(true);
					}catch(Exception e){
						SketchwareUtil.showMessage(getApplicationContext(), e.toString());
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
		group_chat.addChildEventListener(_group_chat_child_listener);
		
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
		
		_file_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_file_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_file_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_file_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_file_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_file_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_video_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				try {
					Notification.Builder builder = new Notification.Builder(GroupChatActivity.this);
					builder.setContentTitle("Sending video...")
					.setContentText("video")
					.setSmallIcon(R.drawable.ic_upload);
					
					builder.setProgress(0,0,true);
					
					NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
					
					manager.notify(1,builder.build());
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
		};
		
		_video_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_video_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				try {
					push_key = group_chat.push().getKey();
					map = new HashMap<>();
					map.put(GROUP_KEY, "Video 📽");
					map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("id", GROUP_KEY);
					map.put("push key", push_key);
					map.put("video", _downloadUrl);
					map.put("time", new SimpleDateFormat("hh:mm a").format(cal.getTime()));
					Bitmap bitmap =ThumbnailUtils.createVideoThumbnail(path, android.provider.MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
					
					java.io.ByteArrayOutputStream stream = new java.io.ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					
					byte[] byteArray = stream.toByteArray();
					
					thumb_String = android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT);
					map.put("thumbnail", thumb_String);
					group_chat.child(push_key).updateChildren(map);
					group_list.child(GROUP_KEY).updateChildren(map);
					tx_message.setText("");
					_Shadow(2, 60, "#FFFFFF", linear_message);
					if (replyMode) {
						replyMode = false;
						linear_reply.setVisibility(View.GONE);
					}
					if (click) {
						click = false;
						linear_attach.setVisibility(View.GONE);
					}
					thumb_String = "";
					NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); notificationManager.cancel(1);
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
		};
		
		_video_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_video_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_video_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_image_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				try {
					im_send_img.setEnabled(false);
					Notification.Builder builder = new Notification.Builder(GroupChatActivity.this);
					builder.setContentTitle("Sending image...")
					.setContentText(imageName)
					.setSmallIcon(R.drawable.ic_upload);
					
					builder.setProgress(0,0,true);
					
					NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
					
					manager.notify(1,builder.build());
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
		};
		
		_image_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_image_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				try {
					push_key = group_chat.push().getKey();
					map = new HashMap<>();
					map.put(GROUP_KEY, "Image 📷");
					map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("id", GROUP_KEY);
					map.put("push key", push_key);
					map.put("image", _downloadUrl);
					map.put("time", new SimpleDateFormat("hh:mm a").format(cal.getTime()));
					group_chat.child(push_key).updateChildren(map);
					group_list.child(GROUP_KEY).updateChildren(map);
					tx_message.setText("");
					im_send_img.setEnabled(true);
					if (replyMode) {
						replyMode = false;
						linear_reply.setVisibility(View.GONE);
					}
					if (click) {
						click = false;
						linear_attach.setVisibility(View.GONE);
					}
					_Shadow(2, 60, "#FFFFFF", linear_message);
					NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); notificationManager.cancel(1);
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
		};
		
		_image_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_image_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_image_failure_listener = new OnFailureListener() {
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
				if (_childValue.get("chat").toString().equals(GROUP_KEY)) {
					if (_childValue.get("status").toString().equals("online")) {
						online_user++;
						group_status.setText(total_member.concat(" • ".concat(String.valueOf((long)(online_user)).concat(" online"))));
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("chat").toString().equals(GROUP_KEY)) {
					if (_childValue.get("status").toString().equals("online")) {
						online_user++;
						group_status.setText(total_member.concat(" • ".concat(String.valueOf((long)(online_user)).concat(" online"))));
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
		userstatus.addChildEventListener(_userstatus_child_listener);
		
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
		getSupportActionBar().setTitle(null);
		
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		_toolbar.setContentInsetStartWithNavigation(0);
		
		_toolbar.setContentInsetsAbsolute(0, 0);
		
		
		
		tools.removeView(tool);
		_toolbar.addView(tool);
		com.google.android.material.appbar.AppBarLayout appBar =
		    (com.google.android.material.appbar.AppBarLayout) _toolbar.getParent();
		appBar.setElevation(3f);
		appBar.setStateListAnimator(null);
		GROUP_KEY = getIntent().getStringExtra("group_key");
		_rippleRoundStroke(im_send, "#009688", "#FFFFFF", 360, 0, "#FFFFFF");
		_Shadow(2, 60, "#FFFFFF", linear_message);
		tx_message.setMaxHeight(230);
		linear_attach.setVisibility(View.GONE);
		linear_reply.setVisibility(View.GONE);
		_Shadow(0, 18, "#EEEEEE", linear_reply);
		_circleRipple("#E0E0E0", lin_document);
		_circleRipple("#E0E0E0", lin_camera);
		_circleRipple("#E0E0E0", lin_gallery);
		_circleRipple("#E0E0E0", im_attach_file);
		_circleRipple("#E0E0E0", im_send_img);
		_removeScollBar(listview1);
		replyMode = false;
		click = false;
		_setUserStatus("online");
	}
	
	private MediaRecorder myAudioRecorder;
	
	private void fo4o() {
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
				try {
					imagePath = _filePath.get((int)(0));
					imageName = Uri.parse(imagePath).getLastPathSegment();
					image.child(imageName).putFile(Uri.fromFile(new File(imagePath))).addOnFailureListener(_image_failure_listener).addOnProgressListener(_image_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
						@Override
						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
							return image.child(imageName).getDownloadUrl();
						}}).addOnCompleteListener(_image_upload_success_listener);
					SketchwareUtil.showMessage(getApplicationContext(), "sending...");
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP2:
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
				try {
					path = _filePath.get((int)(0));
					video_name = Uri.parse(path).getLastPathSegment();
					video.child(video_name).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_video_failure_listener).addOnProgressListener(_video_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
						@Override
						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
							return video.child(video_name).getDownloadUrl();
						}}).addOnCompleteListener(_video_upload_success_listener);
					SketchwareUtil.showMessage(getApplicationContext(), "Sending...");
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP3:
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
				try {
					filePath = _filePath.get((int)(0));
					fileName = Uri.parse(filePath).getLastPathSegment();
					if (fileName.endsWith(".jpg") || (fileName.endsWith(".png") || fileName.endsWith(".gif"))) {
						image.child(fileName).putFile(Uri.fromFile(new File(filePath))).addOnFailureListener(_image_failure_listener).addOnProgressListener(_image_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
							@Override
							public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
								return image.child(fileName).getDownloadUrl();
							}}).addOnCompleteListener(_image_upload_success_listener);
					}
					else {
						if (fileName.endsWith(".mp4") || fileName.endsWith(".3gp")) {
							video.child(fileName).putFile(Uri.fromFile(new File(filePath))).addOnFailureListener(_video_failure_listener).addOnProgressListener(_video_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return video.child(fileName).getDownloadUrl();
								}}).addOnCompleteListener(_video_upload_success_listener);
						}
						else {
							file.child(fileName).putFile(Uri.fromFile(new File(filePath))).addOnFailureListener(_file_failure_listener).addOnProgressListener(_file_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return file.child(fileName).getDownloadUrl();
								}}).addOnCompleteListener(_file_upload_success_listener);
						}
					}
					SketchwareUtil.showMessage(getApplicationContext(), "Sending...");
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
			else {
				
			}
			break;
			
			case REQ_CD_CAM:
			if (_resultCode == Activity.RESULT_OK) {
				 String _filePath = _file_cam.getAbsolutePath();
				
				
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (replyMode) {
			replyMode = false;
			linear_reply.setVisibility(View.GONE);
			_Shadow(2, 60, "#FFFFFF", linear_message);
			_TransitionManager(linear_main, 200);
		}
		else {
			if (click) {
				click = false;
				linear_attach.setVisibility(View.GONE);
				_Shadow(2, 60, "#FFFFFF", linear_message);
			}
			else {
				_hideKeyboard();
				in.putExtra("link", "null");
				in.setClass(getApplicationContext(), Home_Activity.class);
				startActivity(in);
				finish();
			}
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		_setUserStatus(".... ");
	}
	public void _hideKeyboard() {
		new Thread(new Runnable() {
			@Override public void run() {
				try {
					
					android.view.View view = GroupChatActivity.this.getCurrentFocus();   android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
					
				} catch(Exception _e){}
			}
		}).start(); // Start thread
	}
	
	
	public void _Shadow(final double _sadw, final double _cru, final String _wc, final View _widgets) {
		android.graphics.drawable.GradientDrawable wd = new android.graphics.drawable.GradientDrawable();
		wd.setColor(Color.parseColor(_wc));
		wd.setCornerRadius((int)_cru);
		_widgets.setElevation((int)_sadw);
		_widgets.setBackground(wd);
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
	
	
	public void _circleRipple(final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _setCircleImageURL(final ImageView _imageview, final String _url, final double _strokeWidth, final String _strokeColor) {
		
		//DO NOT REMOVE THIS
	}
	
	
	public void _Menu() {
	}
	@Override
	public boolean onCreateOptionsMenu (Menu menu){
		menu.add(0, 0, 0, "Group Info");
		menu.add(0, 1, 1, "Wallpapers");
		menu.add(0, 2, 2, "Copy URL");
		menu.add(0, 3, 3, "Leave Group");
		return true;
	}
	 @Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case 0:
			break;
			case 1:
			break;
			case 2:
			((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", GROUP_KEY));
			SketchwareUtil.showMessage(getApplicationContext(), "Group address copied!");
			break;
			case 3:
			alert.setMessage("Are you sure want to leave this group?");
			alert.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					
				}
			});
			alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					
				}
			});
			alert.create().show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void _SX_CornerRadius_4(final View _view, final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color1));
		
		gd.setStroke((int)_str, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});
		
		_view.setBackground(gd);
		
		_view.setElevation(2);
	}
	
	
	public void _rippleEffect(final View _view) {
		_view.setBackground(CircleDrawables.getSelectableDrawableFor(Color.parseColor("#b0b0b0")));
		
		_view.setClickable(true);
		
		
	}
	
	public static class Drawables {
		    public static android.graphics.drawable.Drawable getSelectableDrawableFor(int color) {
			        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				            android.graphics.drawable.StateListDrawable stateListDrawable = new android.graphics.drawable.StateListDrawable();
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_pressed},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_focused},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            return stateListDrawable;
				        } else {
				            android.content.res.ColorStateList pressedColor = android.content.res.ColorStateList.valueOf(color);
				            android.graphics.drawable.ColorDrawable defaultColor = new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"));
				            
				android.graphics.drawable.Drawable rippleColor = getRippleColor(color);
				            return new android.graphics.drawable.RippleDrawable(
				                pressedColor,
				                defaultColor,
				                rippleColor
				            );
				        }
			    }
		
		    private static android.graphics.drawable.Drawable getRippleColor(int color) {
			        float[] outerRadii = new float[8];
			        Arrays.fill(outerRadii, 0);
			        android.graphics.drawable.shapes.RoundRectShape r = new android.graphics.drawable.shapes.RoundRectShape(outerRadii, null, null);
			        
			android.graphics.drawable.ShapeDrawable shapeDrawable = new 
			android.graphics.drawable.ShapeDrawable(r);
			        shapeDrawable.getPaint().setColor(color);
			        return shapeDrawable;
			    }
		 
		    private static int lightenOrDarken(int color, double fraction) {
			        if (canLighten(color, fraction)) {
				            return lighten(color, fraction);
				        } else {
				            return darken(color, fraction);
				        }
			    }
		 
		    private static int lighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = lightenColor(red, fraction);
			        green = lightenColor(green, fraction);
			        blue = lightenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static int darken(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = darkenColor(red, fraction);
			        green = darkenColor(green, fraction);
			        blue = darkenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			 
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static boolean canLighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        return canLightenComponent(red, fraction)
			            && canLightenComponent(green, fraction)
			            && canLightenComponent(blue, fraction);
			    }
		 
		    private static boolean canLightenComponent(int colorComponent, double fraction) {
			        int red = Color.red(colorComponent);
			        int green = Color.green(colorComponent);
			        int blue = Color.blue(colorComponent);
			        return red + (red * fraction) < 255
			            && green + (green * fraction) < 255
			            && blue + (blue * fraction) < 255;
			    }
		 
		    private static int darkenColor(int color, double fraction) {
			        return (int) Math.max(color - (color * fraction), 0);
			    }
		 
		    private static int lightenColor(int color, double fraction) {
			        return (int) Math.min(color + (color * fraction), 255);
			    }
	}
	public static class CircleDrawables {
		    public static android.graphics.drawable.Drawable getSelectableDrawableFor(int color) {
			        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				            android.graphics.drawable.StateListDrawable stateListDrawable = new android.graphics.drawable.StateListDrawable();
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_pressed},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_focused},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            return stateListDrawable;
				        } else {
				            android.content.res.ColorStateList pressedColor = android.content.res.ColorStateList.valueOf(color);
				            android.graphics.drawable.ColorDrawable defaultColor = new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"));
				            
				android.graphics.drawable.Drawable rippleColor = getRippleColor(color);
				            return new android.graphics.drawable.RippleDrawable(
				                pressedColor,
				                defaultColor,
				                rippleColor
				            );
				        }
			    }
		
		    private static android.graphics.drawable.Drawable getRippleColor(int color) {
			        float[] outerRadii = new float[180];
			/*YOU CAN CHANGE OUTERRADII TO CHANGE THE ROUNDNESS OF EFFECT*/
			        Arrays.fill(outerRadii, 20);
			        android.graphics.drawable.shapes.RoundRectShape r = new android.graphics.drawable.shapes.RoundRectShape(outerRadii, null, null);
			        
			android.graphics.drawable.ShapeDrawable shapeDrawable = new 
			android.graphics.drawable.ShapeDrawable(r);
			        shapeDrawable.getPaint().setColor(color);
			        return shapeDrawable;
			    }
		 
		    private static int lightenOrDarken(int color, double fraction) {
			        if (canLighten(color, fraction)) {
				            return lighten(color, fraction);
				        } else {
				            return darken(color, fraction);
				        }
			    }
		 
		    private static int lighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = lightenColor(red, fraction);
			        green = lightenColor(green, fraction);
			        blue = lightenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static int darken(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = darkenColor(red, fraction);
			        green = darkenColor(green, fraction);
			        blue = darkenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			 
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static boolean canLighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        return canLightenComponent(red, fraction)
			            && canLightenComponent(green, fraction)
			            && canLightenComponent(blue, fraction);
			    }
		 
		    private static boolean canLightenComponent(int colorComponent, double fraction) {
			        int red = Color.red(colorComponent);
			        int green = Color.green(colorComponent);
			        int blue = Color.blue(colorComponent);
			        return red + (red * fraction) < 255
			            && green + (green * fraction) < 255
			            && blue + (blue * fraction) < 255;
			    }
		 
		    private static int darkenColor(int color, double fraction) {
			        return (int) Math.max(color - (color * fraction), 0);
			    }
		 
		    private static int lightenColor(int color, double fraction) {
			        return (int) Math.min(color + (color * fraction), 255);
		}
	}
	
	
	public void _smoothScroll(final double _position) {
		listview1.smoothScrollToPosition((int)(_position));
	}
	
	
	public void _setImageByExtension(final ImageView _ig, final String _str) {
		try {
			extension = _str.substring((int)(_str.lastIndexOf(".") + 1), (int)(_str.length()));
		} catch(Exception e) {
			extension = "";
		}
		if (extension.equals("avi")) {
			_ig.setImageResource(R.drawable.attach_video);
		}
		else {
			if (extension.equals("html")) {
				_ig.setImageResource(R.drawable.attach_document);
			}
			else {
				if (extension.equals("txt")) {
					_ig.setImageResource(R.drawable.icon_file_doc);
				}
				else {
					if (extension.equals("xml")) {
						_ig.setImageResource(R.drawable.icon_file_xls);
					}
					else {
						if (extension.equals("zip")) {
							_ig.setImageResource(R.drawable.icon_file_unknown);
						}
						else {
							if (extension.equals("doc")) {
								_ig.setImageResource(R.drawable.icon_file_doc);
							}
							else {
								if (extension.equals("pdf")) {
									_ig.setImageResource(R.drawable.icon_file_pdf);
								}
								else {
									if (extension.equals("apk")) {
										_ig.setImageResource(R.drawable.icon_file_doc);
									}
									else {
										_ig.setImageResource(R.drawable.icon_file_unknown);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	public void _setUserStatus(final String _str) {
		if (!_str.equals(STATUS)) {
			sts = new HashMap<>();
			sts.put("chat", GROUP_KEY);
			sts.put("status", _str);
			userstatus.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(sts);
			STATUS = _str;
		}
	}
	
	
	public void _removeScollBar(final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
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
				_view = _inflater.inflate(R.layout.chats_group, null);
			}
			
			final LinearLayout linear_main = _view.findViewById(R.id.linear_main);
			final LinearLayout linear_main2 = _view.findViewById(R.id.linear_main2);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout l_message = _view.findViewById(R.id.l_message);
			final LinearLayout linear_blank = _view.findViewById(R.id.linear_blank);
			final de.hdodenhof.circleimageview.CircleImageView im_avatar = _view.findViewById(R.id.im_avatar);
			final LinearLayout linear_ad = _view.findViewById(R.id.linear_ad);
			final LinearLayout l_repl = _view.findViewById(R.id.l_repl);
			final LinearLayout linear_msg = _view.findViewById(R.id.linear_msg);
			final LinearLayout l_voice = _view.findViewById(R.id.l_voice);
			final LinearLayout linear_file = _view.findViewById(R.id.linear_file);
			final ImageView thumbs = _view.findViewById(R.id.thumbs);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView tx_sender_name = _view.findViewById(R.id.tx_sender_name);
			final ImageView im_verified = _view.findViewById(R.id.im_verified);
			final TextView tx_admin = _view.findViewById(R.id.tx_admin);
			final LinearLayout l_reply = _view.findViewById(R.id.l_reply);
			final TextView tx_reply_name = _view.findViewById(R.id.tx_reply_name);
			final TextView tx_reply_msg = _view.findViewById(R.id.tx_reply_msg);
			final TextView tx_sender_message = _view.findViewById(R.id.tx_sender_message);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView tx_msg_time = _view.findViewById(R.id.tx_msg_time);
			final ImageView im_send_dn = _view.findViewById(R.id.im_send_dn);
			final ImageView im_play = _view.findViewById(R.id.im_play);
			final SeekBar seekbar1 = _view.findViewById(R.id.seekbar1);
			final TextView tx_voice_name = _view.findViewById(R.id.tx_voice_name);
			final ImageView im_voice_send = _view.findViewById(R.id.im_voice_send);
			final TextView tx_voice_send_time = _view.findViewById(R.id.tx_voice_send_time);
			final ImageView im_file = _view.findViewById(R.id.im_file);
			final TextView tx_file_name = _view.findViewById(R.id.tx_file_name);
			final ImageView im_send_file = _view.findViewById(R.id.im_send_file);
			final TextView tx_send_file_time = _view.findViewById(R.id.tx_send_file_time);
			
			try {
				tx_sender_name.setText(user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("name").toString());
				if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("verification").toString().equals("false")) {
					im_verified.setVisibility(View.GONE);
				}
				else {
					im_verified.setVisibility(View.VISIBLE);
				}
				if (!user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("pic").toString().equals("")) {
					Glide.with(getApplicationContext()).load(Uri.parse(user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("pic").toString())).into(im_avatar);
				}
				if (_data.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					linear_main.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
					linear_main2.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
					linear2.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
					_Shadow(1, 5, "#009688", l_message);
					tx_sender_name.setTextColor(0xFFFFFFFF);
					tx_sender_message.setTextColor(0xFFFFFFFF);
					linear_ad.setVisibility(View.GONE);
					im_send_dn.setVisibility(View.VISIBLE);
					im_avatar.setVisibility(View.GONE);
					tx_msg_time.setTextColor(0xFFE3F2FD);
					tx_sender_message.setAutoLinkMask(android.text.util.Linkify.ALL);
					    tx_sender_message.setLinkTextColor(Color.parseColor("#FFFFFF"));
					
					tx_sender_message.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
				}
				else {
					linear_main.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
					linear_main2.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
					linear2.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
					tx_sender_message.setAutoLinkMask(android.text.util.Linkify.ALL);
					    tx_sender_message.setLinkTextColor(Color.parseColor("#008DCD"));
					
					tx_sender_message.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
					tx_msg_time.setTextColor(0xFF9E9E9E);
					_Shadow(1, 5, "#FFFFFF", l_message);
					tx_sender_name.setTextColor(0xFF65A9E0);
					tx_sender_message.setTextColor(0xFF000000);
					im_send_dn.setVisibility(View.GONE);
					if ((_position == 0) || (_data.size() == 1)) {
						tx_sender_name.setVisibility(View.VISIBLE);
						if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("pic").toString().equals("")) {
							im_avatar.setVisibility(View.GONE);
							linear_blank.setVisibility(View.GONE);
						}
						else {
							im_avatar.setVisibility(View.VISIBLE);
							linear_blank.setVisibility(View.GONE);
						}
					}
					else {
						if (_data.get((int)_position).get("uid").toString().equals(_data.get((int)_position - 1).get("uid").toString())) {
							linear_ad.setVisibility(View.GONE);
							im_avatar.setVisibility(View.GONE);
							linear_blank.setVisibility(View.INVISIBLE);
						}
						else {
							if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("uid").toString())).get("pic").toString().equals("")) {
								im_avatar.setVisibility(View.GONE);
								linear_blank.setVisibility(View.GONE);
							}
							else {
								im_avatar.setVisibility(View.VISIBLE);
								linear_blank.setVisibility(View.GONE);
							}
							linear_ad.setVisibility(View.VISIBLE);
						}
					}
				}
				if (_data.get((int)_position).containsKey("time")) {
					tx_msg_time.setText(_data.get((int)_position).get("time").toString());
					tx_voice_send_time.setText(_data.get((int)_position).get("time").toString());
					tx_send_file_time.setText(_data.get((int)_position).get("time").toString());
				}
				if (_data.get((int)_position).containsKey("message")) {
					linear_msg.setVisibility(View.VISIBLE);
					tx_sender_message.setText(_data.get((int)_position).get("message").toString());
				}
				else {
					linear_msg.setVisibility(View.GONE);
				}
				if (_data.get((int)_position).containsKey("reply")) {
					tx_reply_msg.setText(_data.get((int)_position).get("reply_message").toString());
					l_repl.setVisibility(View.VISIBLE);
					l_repl.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							_smoothScroll(Double.parseDouble(_data.get((int)_position).get("reply_position").toString()));
						}
					});
					if (_data.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						tx_reply_name.setTextColor(0xFFFFFFFF);
						tx_reply_msg.setTextColor(0xFFFFFFFF);
						_Shadow(0, 5, "#26A69A", l_repl);
						_Shadow(0, 5, "#26A69A", l_reply);
					}
					else {
						tx_reply_name.setTextColor(0xFF65A9E0);
						tx_reply_msg.setTextColor(0xFF000000);
						_Shadow(0, 5, "#EEEEEE", l_repl);
						_Shadow(0, 5, "#EEEEEE", l_reply);
					}
					if (_data.get((int)_position).get("reply_uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						tx_reply_name.setText("You");
					}
					else {
						tx_reply_name.setText(user_data.get((int)uids.indexOf(_data.get((int)_position).get("reply_uid").toString())).get("name").toString());
					}
				}
				else {
					l_repl.setVisibility(View.GONE);
				}
				if (_data.get((int)_position).containsKey("voice")) {
					l_voice.setVisibility(View.VISIBLE);
					tx_voice_name.setText(_data.get((int)_position).get("voice_during").toString());
					im_play.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							if (_data.get((int)_position).containsKey("path")) {
								if (FileUtil.isExistFile(_data.get((int)_position).get("path").toString())) {
									
								}
								else {
									_firebase_storage.getReferenceFromUrl(_data.get((int)_position).get("voice").toString()).getFile(new File(_data.get((int)_position).get("path").toString())).addOnSuccessListener(_voice_download_success_listener).addOnFailureListener(_voice_failure_listener).addOnProgressListener(_voice_download_progress_listener);
									SketchwareUtil.showMessage(getApplicationContext(), "Downloading... ");
								}
							}
						}
					});
					if (_data.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						seekbar1.getProgressDrawable().setColorFilter(Color.parseColor("#BBDEFB"), PorterDuff.Mode.SRC_IN);
						seekbar1.getThumb().setColorFilter(Color.parseColor("#BBDEFB"), PorterDuff.Mode.SRC_IN);
						im_play.setColorFilter(0xFFBBDEFB, PorterDuff.Mode.MULTIPLY);
						tx_voice_name.setTextColor(0xFFE3F2FD);
						tx_voice_send_time.setTextColor(0xFFE3F2FD);
						im_voice_send.setVisibility(View.VISIBLE);
						im_voice_send.setColorFilter(0xFFE3F2FD, PorterDuff.Mode.MULTIPLY);
					}
					else {
						seekbar1.getProgressDrawable().setColorFilter(Color.parseColor("#008dcd"), PorterDuff.Mode.SRC_IN);
						seekbar1.getThumb().setColorFilter(Color.parseColor("#008dcd"), PorterDuff.Mode.SRC_IN);
						im_play.setColorFilter(0xFF9E9E9E, PorterDuff.Mode.MULTIPLY);
						tx_voice_name.setTextColor(0xFF000000);
						tx_voice_send_time.setTextColor(0xFF9E9E9E);
						im_voice_send.setVisibility(View.GONE);
					}
				}
				else {
					l_voice.setVisibility(View.GONE);
				}
				if (_data.get((int)_position).containsKey("image")) {
					imageview1.setVisibility(View.VISIBLE);
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("image").toString())).into(imageview1);
					imageview1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							in.putExtra("mode", "image");
							in.putExtra("url", _data.get((int)_position).get("image").toString());
							in.setClass(getApplicationContext(), PreviewActivity.class);
							startActivity(in);
						}
					});
				}
				else {
					imageview1.setVisibility(View.GONE);
				}
				if (_data.get((int)_position).containsKey("file")) {
					linear_file.setVisibility(View.VISIBLE);
					tx_file_name.setText(_data.get((int)_position).get("filename").toString());
					_setImageByExtension(im_file, _data.get((int)_position).get("filename").toString());
					if (_data.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						im_voice_send.setColorFilter(0xFFBBDEFB, PorterDuff.Mode.MULTIPLY);
						tx_file_name.setTextColor(0xFFE3F2FD);
						tx_send_file_time.setTextColor(0xFFE3F2FD);
					}
					else {
						im_voice_send.setColorFilter(0xFF9E9E9E, PorterDuff.Mode.MULTIPLY);
						tx_file_name.setTextColor(0xFF000000);
						tx_send_file_time.setTextColor(0xFF9E9E9E);
					}
				}
				else {
					linear_file.setVisibility(View.GONE);
				}
				if (_data.get((int)_position).containsKey("video")) {
					if (_data.get((int)_position).containsKey("thumbnail")) {
						byte[] imageBytes = android.util.Base64.decode(
						group_list_chat.get(_position).get("thumbnail").toString(), android.util.Base64.DEFAULT);
						
						Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
						
						thumbs.setImageBitmap(decodedImage);
						thumbs.setVisibility(View.VISIBLE);
						thumbs.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								in.putExtra("mode", "video");
								in.putExtra("url", _data.get((int)_position).get("video").toString());
								in.setClass(getApplicationContext(), PreviewActivity.class);
								startActivity(in);
							}
						});
					}
					else {
						thumbs.setVisibility(View.GONE);
					}
				}
				else {
					thumbs.setVisibility(View.GONE);
				}
				if (creator.equals(_data.get((int)_position).get("uid").toString())) {
					tx_admin.setVisibility(View.VISIBLE);
				}
				else {
					tx_admin.setVisibility(View.GONE);
				}
				im_avatar.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						in.putExtra("uid", _data.get((int)_position).get("uid").toString());
						in.setClass(getApplicationContext(), DetailsActivity.class);
						startActivity(in);
					}
				});
			}catch(Exception e){}
			
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