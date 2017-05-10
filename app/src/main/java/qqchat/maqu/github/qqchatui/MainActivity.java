package qqchat.maqu.github.qqchatui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity implements OnClickListener{

	private RadioGroup radioGroup;
	private LinearLayout root;
	private ListView listView;
	private EditText chat;
	private LinearLayout footer;
	private ViewPager viewPager;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
		root=(LinearLayout)findViewById(R.id.root);
		footer=(LinearLayout)findViewById(R.id.footer);
		chat=(EditText)findViewById(R.id.EditText_input_chat);
		listView=(ListView)findViewById(R.id.ListView_body);
		viewPager=(ViewPager)findViewById(R.id.ViewPager_chat);
		onKeyboardLayout(root, footer);
		listView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				InputHelper.getInstance(getApplicationContext()).hideKeyboard(chat);
				if(viewPager.getVisibility()==View.VISIBLE){
					viewPager.setVisibility(View.GONE);
					RadioButton checkButton=(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
					checkButton.setChecked(false);
				}
				
				return false;
			}
		});

		for(int i=0;i<radioGroup.getChildCount();i++){

			radioGroup.getChildAt(i).setOnClickListener(this);
		}
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for(int i=0;i<radioGroup.getChildCount();i++){
					if(radioGroup.getChildAt(i).getId()!=checkedId){
						radioGroup.getChildAt(i).setTag(0);
					}
				}
			}
		});
	}

	/**
	 * ����������ͼ
	 * @param root 
	 * @param bottomView 
	 */
	private void onKeyboardLayout(final View root, final View bottomView) {
		root.getViewTreeObserver().addOnGlobalLayoutListener( new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				root.getWindowVisibleDisplayFrame(rect);
				int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
				if (rootInvisibleHeight > 100) {
					int[] location = new int[2];
					bottomView.getLocationInWindow(location);
					int srollHeight = (location[1] + bottomView.getHeight()) - rect.bottom;
					root.scrollTo(0, srollHeight);
				} else {
					root.scrollTo(0, 0);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		InputHelper.getInstance(getApplicationContext()).hideKeyboard(chat);
		if(Integer.valueOf(v.getTag().toString())==0){
			RadioButton mClickButton=(RadioButton) v;
			v.setTag(1);
			if(viewPager.getVisibility()!=View.VISIBLE){
				viewPager.setVisibility(View.VISIBLE);
				mClickButton.setChecked(true);
				radioGroup.check(v.getId());
			}
			//setCurrentPage()
		}else if(Integer.valueOf(v.getTag().toString())==1){
			v.setTag(0);
			RadioButton mClickButton=(RadioButton) v;
			mClickButton.setChecked(false);
			viewPager.setVisibility(View.GONE);
			radioGroup.check(-1);
		}

}


}
