package chat.app.com;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.stdio.swipetoreply.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String url = "";
	private boolean emailverified = false;
	
	private LinearLayout linear1;
	private ImageView img1;
	
	private TimerTask t;
	private Intent i = new Intent();
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		img1 = findViewById(R.id.img1);
		net = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								i.setClass(getApplicationContext(), LoginActivity.class);
								startActivity(i);
							}
						});
					}
				};
				_timer.schedule(t, (int)(3000));
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(MainActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_p5,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView t2 = (TextView) bottomSheetView.findViewById(R.id.t2);
				
				TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
				
				TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
				
				ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				i1.setImageResource(R.drawable.thumb_1);
				t1.setText("No internet !");
				t2.setText("Sorry can't connect to the internet at this moment. please try again later.");
				b1.setText("Cancel");
				b2.setText("Try again");
				_RoundAndBorder(i1, "#D50000", 0, "#D50000", 100);
				_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
				_rippleRoundStroke(b1, "#FFFFFF", "#EEEEEE", 15, 2.5d, "#EEEEEE");
				_rippleRoundStroke(b2, "#D50000", "#40FFFFFF", 15, 0, "#000000");
				i1.setElevation((float)0.1d);
				_ICC(i1, "#FFFFFF", "#FFFFFF");
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						bottomSheetDialog.dismiss();
						finishAffinity();
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						net.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "Me", _net_request_listener);
					}
				});
				bottomSheetDialog.setCancelable(false);
				bottomSheetDialog.show();
			}
		};
	}
	
	private void initializeLogic() {
		url = "android.resource://" + getPackageName() + "/raw/img1";
		Glide.with(getApplicationContext()).load(Uri.parse(url)).into(img1);
		net.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com ", "Me", _net_request_listener);
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
	
	
	public void _ICC(final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}
	
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
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