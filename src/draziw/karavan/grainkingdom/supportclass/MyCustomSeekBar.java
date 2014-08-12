package draziw.karavan.grainkingdom.supportclass;


import java.util.HashMap;

import draziw.karavan.grainkingdom.GameState;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.view.View;
import android.view.View.OnClickListener;



public class MyCustomSeekBar extends SeekBar implements OnClickListener {
	
	public static String SEEK_BAR_MAX_VALUE_KEY="SeekBarMinValue";
	public static String SEEK_BAR_MIN_VALUE_KEY="SeekBarMaxValue";
	public static String SEEK_BAR_STEP_KEY="SeekBarStep";

	
	
	private CustomThumbDrawable mThumb;
	public long minValue=0;
	public long maxValue=0;
	public long stepProgress;
	public String buttonAction;

	public MyCustomSeekBar(Context context) {
		super(context);
		
	}
	
	public MyCustomSeekBar(Activity cc, HashMap<String, Number> interfaceParams) {
		super(cc);
		minValue=(Long) interfaceParams.get(SEEK_BAR_MIN_VALUE_KEY);
		maxValue=(Long) interfaceParams.get(SEEK_BAR_MAX_VALUE_KEY);
		stepProgress=(Long) interfaceParams.get(SEEK_BAR_STEP_KEY);
		this.setMax((int)(maxValue-minValue));
	}
	
	public MyCustomSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	public MyCustomSeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}
	
	
	public void setButtonAction(String mButtonAction) {
		buttonAction=mButtonAction;
	}

	@Override
	public void setThumb(Drawable thumb) {
		if (thumb instanceof CustomThumbDrawable) {
			mThumb = (CustomThumbDrawable) thumb;
			mThumb.setParrent(this);
		}
		super.setThumb(thumb);
	}

	public int getAbsoluteProgress() {		
		return (int) (this.getProgress()+minValue);
	}

	@Override
	public void onClick(View v) {
		// Seek bar Button Click		
		GameState.reflectAction.startAction(buttonAction, getAbsoluteProgress());		
	}

	
	
	
	
	
	
}
