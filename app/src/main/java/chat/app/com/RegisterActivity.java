package chat.app.com;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import com.stdio.swipetoreply.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class RegisterActivity extends AppCompatActivity {
	
	public final int REQ_CD_FILE = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private boolean emailVerified = false;
	private String gende = "";
	private String photo = "";
	private boolean upload_profile = false;
	private boolean male = false;
	
	private ArrayList<String> gender = new ArrayList<>();
	private ArrayList<String> image = new ArrayList<>();
	private ArrayList<String> checkUsers = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private CircleImageView circleimageview1;
	private TextView textview1;
	private LinearLayout linear_username;
	private LinearLayout linear_name;
	private LinearLayout linear_phone;
	private LinearLayout linear_gender;
	private LinearLayout linear_email;
	private LinearLayout linear_password;
	private CheckBox checkbox1;
	private Button button1;
	private TextView textview2;
	private LinearLayout linear13;
	private ImageView imageview6;
	private EditText username;
	private LinearLayout linear15;
	private ImageView imageview7;
	private EditText name;
	private LinearLayout linear17;
	private ImageView imageview8;
	private EditText phone;
	private LinearLayout linear19;
	private Spinner spinner1;
	private LinearLayout linear9;
	private ImageView imageview4;
	private EditText email;
	private LinearLayout linear11;
	private ImageView imageview5;
	private EditText password;
	
	private Intent i = new Intent();
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
	private AlertDialog.Builder dialog;
	private Intent file = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference profile = _firebase_storage.getReference("profile");
	private OnCompleteListener<Uri> _profile_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _profile_download_success_listener;
	private OnSuccessListener _profile_delete_success_listener;
	private OnProgressListener _profile_upload_progress_listener;
	private OnProgressListener _profile_download_progress_listener;
	private OnFailureListener _profile_failure_listener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.register);
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
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		circleimageview1 = findViewById(R.id.circleimageview1);
		textview1 = findViewById(R.id.textview1);
		linear_username = findViewById(R.id.linear_username);
		linear_name = findViewById(R.id.linear_name);
		linear_phone = findViewById(R.id.linear_phone);
		linear_gender = findViewById(R.id.linear_gender);
		linear_email = findViewById(R.id.linear_email);
		linear_password = findViewById(R.id.linear_password);
		checkbox1 = findViewById(R.id.checkbox1);
		button1 = findViewById(R.id.button1);
		textview2 = findViewById(R.id.textview2);
		linear13 = findViewById(R.id.linear13);
		imageview6 = findViewById(R.id.imageview6);
		username = findViewById(R.id.username);
		linear15 = findViewById(R.id.linear15);
		imageview7 = findViewById(R.id.imageview7);
		name = findViewById(R.id.name);
		linear17 = findViewById(R.id.linear17);
		imageview8 = findViewById(R.id.imageview8);
		phone = findViewById(R.id.phone);
		linear19 = findViewById(R.id.linear19);
		spinner1 = findViewById(R.id.spinner1);
		linear9 = findViewById(R.id.linear9);
		imageview4 = findViewById(R.id.imageview4);
		email = findViewById(R.id.email);
		linear11 = findViewById(R.id.linear11);
		imageview5 = findViewById(R.id.imageview5);
		password = findViewById(R.id.password);
		fauth = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		file.setType("image/*");
		file.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		circleimageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(file, REQ_CD_FILE);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (5 > username.getText().toString().length()) {
					_setError(username, "at least 5 letters");
				}
				else {
					if (checkUsers.contains(username.getText().toString())) {
						_setError(username, "this username already exists");
					}
					else {
						if (5 > name.getText().toString().length()) {
							_setError(name, "at least 5 letters");
						}
						else {
							if (10 > phone.getText().toString().length()) {
								_setError(phone, "enter valid number ");
							}
							else {
								if (email.getText().toString().equals("")) {
									_setError(email, "enter email");
								}
								else {
									if (password.getText().toString().equals("")) {
										_setError(password, "enter password");
									}
									else {
										if (!checkbox1.isChecked()) {
											_Toast("F44336", "FFFFFF", "Accept terms and conditions");
										}
										else {
											if (!gende.equals("")) {
												fauth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(RegisterActivity.this, _fauth_create_user_listener);
											}
											else {
												_Toast("F44336", "FFFFFF", "Select Gender");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		username.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (username.getText().toString().equals(_charSeq.toLowerCase())) {
					_SetBackground(button1, 25, 10, "#0277BD", true);
					button1.setEnabled(true);
				}
				else {
					_Toast("F44336", "FFFFFF", "only lower case");
					_SetBackground(button1, 25, 5, "#29B6F6", true);
					button1.setEnabled(false);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					gende = "";
				}
				if (_position == 1) {
					gende = "Male";
					male = true;
				}
				if (_position == 2) {
					gende = "Female";
					male = false;
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		_user_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				checkUsers.add(_childValue.get("username").toString());
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
		user.addChildEventListener(_user_child_listener);
		
		_profile_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), String.valueOf((long)(_progressValue)));
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
				map = new HashMap<>();
				map.put("username", username.getText().toString());
				map.put("name", name.getText().toString());
				map.put("phone", phone.getText().toString());
				map.put("gender", gende);
				map.put("verification", "false");
				map.put("admin", "false");
				map.put("details", "private");
				map.put("banned", "false");
				map.put("pic", _downloadUrl);
				map.put("bio", "Hey there! I'm using chat this");
				map.put("email", email.getText().toString());
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("password", password.getText().toString());
				user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
				map.clear();
				fauth.getCurrentUser().sendEmailVerification() .addOnCompleteListener(new OnCompleteListener<Void>() {
					@Override
					public void onComplete(Task<Void> task) {
						if (task.isSuccessful()) {
							showMessage("Email sent."); } else {
							showMessage ("Error sending email");}
					} });
				finish();
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
				if (_success) {
					if (upload_profile) {
						profile.child(Uri.parse(photo).getLastPathSegment()).putFile(Uri.fromFile(new File(photo))).addOnFailureListener(_profile_failure_listener).addOnProgressListener(_profile_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
							@Override
							public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
								return profile.child(Uri.parse(photo).getLastPathSegment()).getDownloadUrl();
							}}).addOnCompleteListener(_profile_upload_success_listener);
					}
					else {
						if (male) {
							map = new HashMap<>();
							map.put("username", username.getText().toString());
							map.put("name", name.getText().toString());
							map.put("phone", phone.getText().toString());
							map.put("gender", gende);
							map.put("verification", "false");
							map.put("banned", "false");
							map.put("admin", "false");
							map.put("details", "private");
							map.put("pic", "https://firebasestorage.googleapis.com/v0/b/chatting-app-c016d.appspot.com/o/3135768.png?alt=media&token=3dbc6286-36f4-445c-9319-eb423b93dba3");
							map.put("bio", "Hey there! I'm using chat app");
							map.put("email", email.getText().toString());
							map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
							map.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
							map.put("password", password.getText().toString());
							user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
							map.clear();
							fauth.getCurrentUser().sendEmailVerification() .addOnCompleteListener(new OnCompleteListener<Void>() {
								@Override
								public void onComplete(Task<Void> task) {
									if (task.isSuccessful()) {
										showMessage("Email sent."); } else {
										showMessage ("Error sending email");}
								} });
							finish();
						}
						else {
							map = new HashMap<>();
							map.put("username", username.getText().toString());
							map.put("name", name.getText().toString());
							map.put("phone", phone.getText().toString());
							map.put("gender", gende);
							map.put("verification", "false");
							map.put("banned", "false");
							map.put("admin", "false");
							map.put("details", "private");
							map.put("pic", "https://firebasestorage.googleapis.com/v0/b/chatting-app-c016d.appspot.com/o/4305696.png?alt=media&token=2403950c-2478-4b85-ba6d-da3689ca516d");
							map.put("bio", "Hey there! I'm using chat app");
							map.put("email", email.getText().toString());
							map.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
							map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
							map.put("password", password.getText().toString());
							user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
							map.clear();
							fauth.getCurrentUser().sendEmailVerification() .addOnCompleteListener(new OnCompleteListener<Void>() {
								@Override
								public void onComplete(Task<Void> task) {
									if (task.isSuccessful()) {
										showMessage("Email sent."); } else {
										showMessage ("Error sending email");}
								} });
							finish();
						}
					}
				}
				else {
					_Toast("F44336", "FFFFFF", _errorMessage);
				}
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
		_SetBackground(linear3, 25, 15, "#FFFFFF", true);
		_SetBackground(linear_username, 0, 0, "#000000", true);
		_SetBackground(linear_name, 0, 0, "#000000", true);
		_SetBackground(linear_phone, 0, 0, "#000000", true);
		_SetBackground(linear_email, 0, 0, "#000000", true);
		_SetBackground(linear_password, 0, 0, "#000000", true);
		_SetBackground(linear_gender, 0, 0, "#000000", true);
		_SetBackground(linear9, 0, 0, "#FFFFFF", true);
		_SetBackground(linear11, 0, 0, "#FFFFFF", true);
		_SetBackground(linear13, 0, 0, "#FFFFFF", true);
		_SetBackground(linear15, 0, 0, "#FFFFFF", true);
		_SetBackground(linear17, 0, 0, "#FFFFFF", true);
		_SetBackground(linear19, 0, 0, "#FFFFFF", true);
		_SetBackground(button1, 25, 10, "#0277BD", true);
		gender.add("Select gender");
		gender.add("Male");
		gender.add("Female");
		spinner1.setSelection((int)(0));
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, gender));
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/font1.ttf"), 0);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FILE:
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
				circleimageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
				photo = _filePath.get((int)(0));
				upload_profile = true;
			}
			else {
				upload_profile = false;
			}
			break;
			default:
			break;
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
	
	
	public void _setError(final View _v, final String _message) {
		try {
			EditText edit = (EditText)_v;
			edit.setError(_message);
		} catch(Exception e) {
			showMessage(e.toString());
		}
	}
	
	
	public void _Toast(final String _bg, final String _st, final String _msg) {
		TextView tvu = new TextView(this);
		
		tvu.setLayoutParams(
		  new ViewGroup.LayoutParams(
		    android.widget.LinearLayout
		    .LayoutParams.WRAP_CONTENT,
		    android.widget.LinearLayout
		    .LayoutParams.WRAP_CONTENT)
		);
		tvu.setTextColor(Color.parseColor("#"+_st));
		tvu.setGravity(Gravity.CENTER);
		tvu.setText(_msg);
		
		
		LinearLayout v = new LinearLayout(this);
		
		android.graphics.drawable.GradientDrawable gd =
		  new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor("#"+_bg));
		gd.setStroke((int)getDip(2), Color.parseColor("#"+_st));
		gd.setCornerRadius(6);
		v.setBackground(gd);
		v.setPadding((int)getDip(8),(int)getDip(8),(int)getDip(8),(int)getDip(8));
		
		v.addView(tvu);
		
		
		Toast t = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);
		t.setView(v);
		t.show();
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