package qqchat.maqu.github.qqchatui;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputHelper {

	private static InputHelper instance;
	private Context mContext;

	public InputHelper(Context mContext){
		this.mContext=mContext;
	}

	public static InputHelper getInstance(Context mContext){
		if(instance==null){
			synchronized (InputHelper.class) {
				if(instance==null){
					instance=new InputHelper(mContext);
				}
			}
		}
		return instance;
	}

	/**
	 * ��ʾ����
	 * @param view
	 */
	public void showKeyboard(View view){
		InputMethodManager manager=(InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);  
		manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);  
	}

	/**
	 * ���ؼ���
	 * @param view
	 */
	public void hideKeyboard(View view){
		InputMethodManager manager = ( InputMethodManager )mContext.getSystemService( Context.INPUT_METHOD_SERVICE );     
		if ( manager.isActive( ) ) {     
			manager.hideSoftInputFromWindow( view.getApplicationWindowToken( ) , 0 );   
		}    
	}
}
