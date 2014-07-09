package draziw.karavan.grainkingdom;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Room {

	public static TheRoomObject TheTekRoom;	
	public static int START_ROOM_INDEX_IN_LOOP=3;
	public static int BUTTON_ID_INCR=100;
	public static int SEEKBAR_ID_INCR=1000;
		
	public static void create(int roomnumber,Activity cc) {		
		TheTekRoom = RoomFromXML.create(roomnumber, cc);
		if (!TheTekRoom.isInitialized()) TheTekRoom = RoomFromXML.create(START_ROOM_INDEX_IN_LOOP, cc);
			
		TheTekRoom.textRefactoring();
		
		cc.setContentView(TheTekRoom.getLayoutId(cc));	
		setimage(cc,TheTekRoom);
		settext(cc,TheTekRoom);
		addseekbar(cc,TheTekRoom);
		addaction(cc,TheTekRoom);
		
	}
	
	private static void setimage(Activity cc,TheRoomObject TekRoom) {
		ImageView im=(ImageView) cc.findViewById(R.id.baseimage);	
		im.setBackgroundResource(TekRoom.getImageId(cc));				
	}
	
	
	
	private static void settext(Activity cc,TheRoomObject TekRoom) {
		TextView im=(TextView) cc.findViewById(R.id.basetext);
		im.setGravity(Gravity.CENTER);
		
		im.setTextColor(cc.getResources().getColor(R.color.baseTextColor));
		im.setText(TekRoom.getText(cc));
			//case 2: im.setTextColor(cc.getResources().getColor(R.color.baseTextColor));im.setText(R.string.textroom2);break;
			//case 3:im.setGravity(Gravity.LEFT);im.setText(GameState.people.reports(cc),BufferType.SPANNABLE);break; 				//
			//case 4:im.setGravity(Gravity.LEFT);im.setText(GameState.corn.reports(cc),BufferType.SPANNABLE);break;
			
	}
	
	private static void addseekbar(Activity cc,TheRoomObject TekRoom) {
		// 1 - content, child - Scrool , child2 - LinearLayout or another
		// content
		ViewGroup maincontainer = (ViewGroup) ((ViewGroup) ((ViewGroup) cc
				.findViewById(android.R.id.content)).getChildAt(0))
				.getChildAt(0);
		// попробуем использовать viewgroup так как у нас может быть и не
		// linearlayout основным контейнером
		
		for (int idx=0;idx<TekRoom.getSeekBarCount();idx++) {
			addseekbar(cc,maincontainer,idx,TekRoom);		
		}
		
	}
	
	private static void addaction(Activity cc,TheRoomObject TekRoom) {
		// 1 - content, child - Scrool , child2 - LinearLayout or another
		// content
		ViewGroup maincontainer = (ViewGroup) ((ViewGroup) ((ViewGroup) cc
				.findViewById(android.R.id.content)).getChildAt(0))
				.getChildAt(0);
		// попробуем использовать viewgroup так как у нас может быть и не
		// linearlayout основным контейнером
		
		for (int idx=0;idx<TekRoom.getActionCount();idx++) {
			addbutton(cc,maincontainer,idx,TekRoom);		
		}
		
	}
	
	private static void addbutton(Activity cc,ViewGroup maincontainer,int idx,TheRoomObject TekRoom){
		Button action1=new Button(cc);
		action1.setId(idx+BUTTON_ID_INCR);
		action1.setOnClickListener((MainActivity) cc);
		action1.setTextColor(cc.getResources().getColor(R.color.interractiveTextColor));		
		action1.setText(TekRoom.getActionText(idx));
		maincontainer.addView(action1);
	}
	
	private static void addseekbar(Activity cc,ViewGroup maincontainer,int idx,TheRoomObject TekRoom){
		SeekBar sb1=new SeekBar(cc);
		sb1.setId(idx+SEEKBAR_ID_INCR);	
		sb1.setOnSeekBarChangeListener((MainActivity) cc);		
		maincontainer.addView(sb1);
	}
	
	
	
	public static void actionByRoomAndViewId(int idx,Activity cc) {
		idx=idx-BUTTON_ID_INCR;
		
		if (TheTekRoom.isAction(idx,TheRoomObject.ACTION_NEXT)) {
			TheRoomObject.onExit();
			Room.create(TheTekRoom.getNextRoom(),cc);	
			return;
		}
		
		if (TheTekRoom.isAction(idx,TheRoomObject.ACTION_SVERNUT)) {
			TheRoomObject.onExit();			
			GameState.newYear();
			Room.create(START_ROOM_INDEX_IN_LOOP,cc);
			return;
		}
	
	}
	
	

}
