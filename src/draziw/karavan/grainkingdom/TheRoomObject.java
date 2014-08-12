package draziw.karavan.grainkingdom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

public class TheRoomObject {
	
	public static int TYPE_MULTYTEXT=777;
	
	public static String ATTRIBUTE_NAME_TEXT="T1";
	public static String ATTRIBUTE_ID_IMAGE="I1";
	public static String ATTRIBUTE_NAME_FUNCTION="F1";
	
	private boolean initialized=false;
	private int id;
	private String imageString;
	private String layoutString;	
	
	private ArrayList<ActionFromXml> actionArray;
	private ArrayList<SeekBarFromXml> seekBarArray;
	private ArrayList<Map<String, Object>> data;
	
	public int getId() {
		return id;
	}
	
	public int getNextRoom() {
		return id+1;
	}
	
	public TheRoomObject() {
		actionArray=new ArrayList<ActionFromXml>();		
		seekBarArray=new ArrayList<SeekBarFromXml>(); 
		data = new ArrayList<Map<String, Object>>();
	}
	
/*	public TheRoomObject(boolean multiText) {
		this();
		if (multiText) {data = new ArrayList<Map<String, Object>>();}		
	}*/
	
	public void addMultiText(String txt,String function,String img){
		  int imageId=getImageId(img,GameState.context);
		  Map<String, Object> m = new HashMap<String,Object>();
	      m.put(ATTRIBUTE_NAME_TEXT, txt);
	      m.put(ATTRIBUTE_NAME_FUNCTION, function);
	      m.put(ATTRIBUTE_ID_IMAGE, imageId);
	      data.add(m);
	}
	
	
	public void multiTextRefactoring() {
		//for (Map<String, Object> tekMap : data){
		for (Iterator<Map<String, Object>> iterator = data.iterator(); iterator.hasNext(); ){
			Map<String, Object> tekMap = iterator.next();
			String tekFunctionStr=(String) tekMap.get(ATTRIBUTE_NAME_FUNCTION);
			String tekTxt=(String) tekMap.get(ATTRIBUTE_NAME_TEXT);
			Spanned tekSpn=ReflectTextFunction.startAction(tekFunctionStr,tekTxt);
			if (tekSpn!=null) {
				tekMap.put(ATTRIBUTE_NAME_TEXT,tekSpn);
			} else { // вернулся ответ что этого события нет, надо удалять
				iterator.remove();		
			}
		}
		
	}
	
	
	public void setId(String ss) {
		id=Integer.parseInt(ss);
		initialized=true;		
	}
	
	public void setLayout(String ss) {
		layoutString=ss;		
	}
		
/*	public void setTextMain(String ss) {
		textMain=ss;
	}*/
	
	public void setImage(String ss) {
		imageString=ss;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
		
	
	
	
	public int getLayoutId(Context cc) {
		int resID=-1;
		if (null==layoutString) return resID;
		
			resID = cc.getResources().getIdentifier(layoutString, "layout", cc.getPackageName());			
			
		return resID;		
	}
	
	public int getImageId(Context cc) {
		return getImageId(imageString,cc);		
	}
	
	public int getImageId(String tekStr,Context cc) {
		int resID=-1;
		if (null==tekStr) return resID;
		
			resID = cc.getResources().getIdentifier(tekStr, "drawable", cc.getPackageName());			
		
		return resID;
	}

	public Spanned getText(Context cc) {		
		//return Html.fromHtml(textMain);
		if (data.size()==0) return null;
		return (Spanned) data.get(0).get(ATTRIBUTE_NAME_TEXT);
	}
	

	
	/*public void textRefactoring(String textMain) {		
		switch(id) {
		case 3: //People.reports
			textMain=GameState.people.fillReportVars(textMain);
			break;
		case 4: //Land.reports
			textMain=GameState.land.fillReportVars(textMain);
			break;
		case 5://Corn.reports
			textMain=GameState.corn.fillReportVars(textMain);
			break;
		case 6://Ships.reports
			textMain=GameState.ships.fillReportVars(textMain);
			break;
		case 7://Science.reports
			textMain=GameState.science.fillReportVars(textMain);
			break;
		case 8://Gamestate.other reports
			textMain=GameState.fillOtherReportVars(textMain);
			break;
		case 9://Gamestate.buyLandOperation
			textMain=GameState.buyLandOperation(textMain);
			break;
		case 10://Gamestate.selllandOperation
			textMain=GameState.sellLandOperation(textMain);
			break;			
		case 11://Gamestate.eatOperation
			textMain=GameState.eatOperation(textMain);
			break;	
		case 12:// Gamestate.sowOperation			
			textMain=GameState.sowOperation(textMain);			
			break;
		case 13:// GameState.scienceOperation
			textMain=GameState.scienceOperation(textMain);
			break;
		case 14:// GameState.shipBuildOperation
			textMain=GameState.shipBuildOperation(textMain);
			break;
		case 15://GameState.portOperation
			textMain=GameState.portOperation(textMain);
			break;
		default:
			return;
		}
				
		textMain=RoomFromXML.TextClearIfdelTag(textMain);			
	}*/

	public static void onExit() {		
		
	}
	
	
	// START for SeekBar
			public class SeekBarFromXml {
				public String id;
				public String text;
				public String buttonAction;
				
				public SeekBarFromXml(String mId,String mButtonAction) {
					id=mId;
					buttonAction=mButtonAction;
				}
				
				public void setText(String mText) {
					text=mText;			
				}
			}
			
			public void addSeekBar(String mId,String mButtonAction) {
				seekBarArray.add(new SeekBarFromXml(mId,mButtonAction));		
			}
			
			public int getSeekBarCount() {
				return seekBarArray.size();
			}
			
			public void setLastSeekBarText(String mText) {
				  if (seekBarArray.size()>0) {
					  seekBarArray.get(seekBarArray.size()-1).setText(mText);
				  }	
			}
			
			public Spanned getSeekBarText(int idx) {						
				return Html.fromHtml(seekBarArray.get(idx).text);				
			}
			
			public String getSeekBarAction(int idx) {
				return seekBarArray.get(idx).buttonAction;
			}
			
	// END For SeekBar
	
	//START for Action
			public class ActionFromXml {
				public String text;
				public String id;
				public String roomId;
				public String buttonAction;		
				
				public ActionFromXml(String mText,String mId,String mRoomId,String mType) {
					// 
					text=mText;
					id=mId;
					roomId=mRoomId;
					buttonAction=mType;
				}
				
				public void setText(String mText) {
					text=mText;			
				}
			}
			
			
			public void addAction(String mText,String mId,String mRoomId,String mType) {
				actionArray.add(new ActionFromXml(mText,mId,mRoomId,mType));		
			}
			
			public void setLastActionText(String mText) {
				  if (actionArray.size()>0) {
					  actionArray.get(actionArray.size()-1).setText(mText);
				  }		
			}
			
			public int getActionCount() {
				return actionArray.size();
			}
			
			public Spanned getActionText(int idx) {
				return Html.fromHtml(actionArray.get(idx).text);				
			}
			
			public String getAction(int idx) {				
				return actionArray.get(idx).buttonAction;			
			}
			
			public boolean isAction(int idx,String sType) {
				return getAction(idx).equals(sType);	
			}
			
			public void removeAction(int i) {				
				for ( Iterator<ActionFromXml> iterator = actionArray.iterator(); iterator.hasNext(); ){
					 ActionFromXml tekAction = iterator.next();
					 if (tekAction.id.equals(Integer.toString(i))) {
						 iterator.remove();
					 }				
				}
			}			

			
	// End for Action
			
	public int getType() {
		if (data!=null && data.size()>1) return TYPE_MULTYTEXT;
		else return 0;		
	}
			
	public ArrayList<Map<String, Object>> getMultiTextData() {
		return data;
	}


}

