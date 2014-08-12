package draziw.karavan.grainkingdom.supportclass;

import draziw.karavan.grainkingdom.GameState;
import android.content.Context;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MyCustomButton extends Button implements OnClickListener {
	
	public String buttonAction;

	public MyCustomButton(Context context) {
		super(context);		
	}
	
	public void setButtonAction(String mButtonAction) {
		buttonAction=mButtonAction;
	}

	@Override
	public void onClick(View v) {
		// Action Button Click			
		GameState.reflectAction.startAction(buttonAction, 0);		
	}

}
