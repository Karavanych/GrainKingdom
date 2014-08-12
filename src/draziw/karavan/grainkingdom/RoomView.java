package draziw.karavan.grainkingdom;

import draziw.karavan.grainkingdom.supportclass.CustomThumbDrawable;
import draziw.karavan.grainkingdom.supportclass.MyCustomButton;

import draziw.karavan.grainkingdom.supportclass.MyCustomSeekBar;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class RoomView {

	public static TheRoomObject TheTekRoom;	
	public static int START_ROOM_INDEX_IN_LOOP=3;
	public static int BUTTON_ID_INCR=100;
	public static int SEEKBAR_ID_INCR=1000;
	public static Activity context;
		
	public static void create(int roomnumber,Activity cc) {
		context=cc;
		
		TheTekRoom = RoomFromXML.create(roomnumber, cc);
		
		if (TheTekRoom==null || !TheTekRoom.isInitialized()) TheTekRoom = RoomFromXML.create(START_ROOM_INDEX_IN_LOOP, cc);
			
		

		
		cc.setContentView(TheTekRoom.getLayoutId(cc));	
		setimage(cc,TheTekRoom);
		if (TheTekRoom.getType()==TheRoomObject.TYPE_MULTYTEXT) {
			TheTekRoom.multiTextRefactoring();
			setMultiText(cc,TheTekRoom);
		} else {
			TheTekRoom.multiTextRefactoring();
			if ((TheTekRoom.getText(cc)==null) && !GameState.TEST) {nextRoom();}
			settext(cc,TheTekRoom);
		}
		addseekbar(cc,TheTekRoom);
		addaction(cc,TheTekRoom);
		
		
	}
	
/*	private static void objectsRefactoring() {
		// TODO Auto-generated method stub
		
		if (TheTekRoom.getSeekBarCount()>0) { //нужно адаптировать seekBar
			
			
		}							
	}*/

	private static void setimage(Activity cc,TheRoomObject TekRoom) {
		ImageView im=(ImageView) cc.findViewById(R.id.baseimage);	
		im.setBackgroundResource(TekRoom.getImageId(cc));				
	}
	
	
	
	private static void settext(Activity cc,TheRoomObject TekRoom) {
		TextView im=(TextView) cc.findViewById(R.id.basetext);
		im.setGravity(Gravity.CENTER);
		
		//im.setTextColor(cc.getResources().getColor(R.color.baseTextColor));
		im.setText(TekRoom.getText(cc),BufferType.SPANNABLE);
			//case 2: im.setTextColor(cc.getResources().getColor(R.color.baseTextColor));im.setText(R.string.textroom2);break;
			//case 3:im.setGravity(Gravity.LEFT);im.setText(GameState.people.reports(cc),BufferType.SPANNABLE);break; 				//
			//case 4:im.setGravity(Gravity.LEFT);im.setText(GameState.corn.reports(cc),BufferType.SPANNABLE);break;
			
	}
	
	private static void setMultiText(Activity cc,TheRoomObject TekRoom) {
		
		 // массив имен атрибутов, из которых будут читаться данные
	    String[] from = { TheRoomObject.ATTRIBUTE_NAME_TEXT,TheRoomObject.ATTRIBUTE_ID_IMAGE};
	    // массив ID View-компонентов, в которые будут вставлять данные
	    int[] to = { R.id.lvText, R.id.lvImg};
	    
	    // создаем адаптер
	    SimpleAdapter sAdapter = new SimpleAdapter(cc, TekRoom.getMultiTextData(), R.layout.itemmultytext,
	        from, to);
	    
	    // определяем список и присваиваем ему адаптер
	    ListView lvSimple = (ListView) cc.findViewById(R.id.lvMultiText);
	    lvSimple.setAdapter(sAdapter);
		
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
		
		MyCustomButton action1=new MyCustomButton(cc);
		action1.setId(idx+BUTTON_ID_INCR);
		action1.setButtonAction(TekRoom.getAction(idx));
		action1.setOnClickListener(action1);
		action1.setTextColor(cc.getResources().getColor(R.color.interractiveTextColor));		
		action1.setText(TekRoom.getActionText(idx));
		LinearLayout.LayoutParams mParams=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		action1.setLayoutParams(mParams);
		maincontainer.addView(action1);
	}
	
	private static void addseekbar(Activity cc,ViewGroup maincontainer,int idx,TheRoomObject TekRoom){
		
		LinearLayout newll=new LinearLayout(cc);
		newll.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout.LayoutParams llparam=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		llparam.weight=1f;
		llparam.gravity=Gravity.CENTER_VERTICAL;
		
		MyCustomSeekBar sb1=new MyCustomSeekBar(cc,GameState.interfaceParams);
		
		if (0==sb1.getMax()) {return;} // не надо ничего добавлять, seek bar пустой исходя из interfaceParams
		
		sb1.setButtonAction(TekRoom.getSeekBarAction(idx));
		sb1.setId(idx+SEEKBAR_ID_INCR);		
		sb1.setThumb(new CustomThumbDrawable(cc));
		sb1.setOnSeekBarChangeListener((MainActivity) cc);
		newll.addView(sb1,llparam);
		
		llparam=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//llparam.weight=0.3f;
		llparam.gravity=Gravity.CENTER_VERTICAL;
		
		Button seekBarButton=new Button(cc);
		seekBarButton.setText(TekRoom.getSeekBarText(idx));
		seekBarButton.setMaxLines(1);
		seekBarButton.setTextColor(cc.getResources().getColor(R.color.baseTextColor));
		seekBarButton.setOnClickListener(sb1);		
		
		newll.addView(seekBarButton,llparam);
		
		maincontainer.addView(newll);
	}
	
	
	
	/*public static void actionByRoomAndViewId(int idx,Activity cc) {
		idx=idx-BUTTON_ID_INCR;
		
		if (TheTekRoom.isAction(idx,TheRoomObject.ACTION_NEXT)) {
			TheRoomObject.onExit();
			Room.nextRoom();
			return;
		}
		
		if (TheTekRoom.isAction(idx,TheRoomObject.ACTION_SVERNUT)) {
			TheRoomObject.onExit();			
			GameState.newYear();
			Room.nextRoom();
			return;
		}
	
	}*/
	
	public static void nextRoom() {
		TheRoomObject.onExit();
		
		RoomView.create(TheTekRoom.getNextRoom(),context);		
	}
	
	

}
