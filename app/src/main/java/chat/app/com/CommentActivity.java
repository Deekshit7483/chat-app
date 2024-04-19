package chat.app.com;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import java.io.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class CommentActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String key = "";
	private String name = "";
	private String profile = "";
	private String verification = "";
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> list_comment = new ArrayList<>();
	private ArrayList<String> uids = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> user_data = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private EditText edittext1;
	private ImageView imageview1;
	
	private DatabaseReference comment = _firebase.getReference("comment");
	private ChildEventListener _comment_child_listener;
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
	private Calendar cl = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.comment);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
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
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		edittext1 = findViewById(R.id.edittext1);
		imageview1 = findViewById(R.id.imageview1);
		fauth = FirebaseAuth.getInstance();
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				key = comment.push().getKey();
				if (edittext1.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Please Enter comment");
				}
				else {
					cl = Calendar.getInstance();
					map = new HashMap<>();
					map.put("comment", edittext1.getText().toString());
					map.put("key", key);
					map.put("uid", getIntent().getStringExtra("key"));
					map.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("time", new SimpleDateFormat("hh:mm a").format(cl.getTime()));
					map.put("date", new SimpleDateFormat("dd:MMM:yyyy").format(cl.getTime()));
					comment.child(key).updateChildren(map);
					map.clear();
					edittext1.setText("");
				}
			}
		});
		
		_comment_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				comment.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list_comment = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list_comment.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(list_comment));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
				comment.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list_comment = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list_comment.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(list_comment));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
		comment.addChildEventListener(_comment_child_listener);
		
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
		_initSlideActivity();
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
				_view = _inflater.inflate(R.layout.comments, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView com = _view.findViewById(R.id.com);
			final TextView name = _view.findViewById(R.id.name);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView date = _view.findViewById(R.id.date);
			
			name.setText(user_data.get((int)uids.indexOf(_data.get((int)_position).get("id").toString())).get("name").toString());
			if (list_comment.get((int)_position).get("uid").toString().equals(getIntent().getStringExtra("key"))) {
				date.setText(list_comment.get((int)_position).get("time").toString().concat(" - ".concat(list_comment.get((int)_position).get("date").toString())));
				if (list_comment.get((int)_position).containsKey("comment")) {
					com.setText(list_comment.get((int)_position).get("comment").toString());
				}
				if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("id").toString())).get("verification").toString().equals("true")) {
					imageview1.setVisibility(View.VISIBLE);
				}
				else {
					if (user_data.get((int)uids.indexOf(_data.get((int)_position).get("id").toString())).get("verification").toString().equals("false")) {
						imageview1.setVisibility(View.GONE);
					}
				}
				linear1.setVisibility(View.VISIBLE);
			}
			else {
				linear1.setVisibility(View.GONE);
			}
			if (list_comment.get((int)_position).get("id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
				name.setTextColor(0xFFFF5722);
			}
			else {
				name.setTextColor(0xFF000000);
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