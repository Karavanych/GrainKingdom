package draziw.karavan.grainkingdom;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

public class TheRoomObject {
	
	//возможные действия
	public static String ACTION_NEXT="NEXT";
	public static String ACTION_NEW_YEAR="NEWYEAR";
	public static String ACTION_SVERNUT="SVERNUT";
	public static String ACTION_BUYLAND="BUYLAND";
	
	private boolean initialized=false;
	private int id;
	private String textMain;
	private String imageString;
	private String layoutString;
	private ArrayList<ActionFromXml> actionArray;
	private ArrayList<SeekBarFromXml> seekBarArray;
	private int varcount=0;// в тексте комнаты, количество переменных
	
	
	public int getId() {
		return id;
	}
	
	public int getNextRoom() {
		return id+1;
	}
	
	public TheRoomObject() {
		// TODO Auto-generated constructor stub
		actionArray=new ArrayList<ActionFromXml>();		
		seekBarArray=new ArrayList<SeekBarFromXml>(); 
	}
	
	public void setId(String ss) {
		id=Integer.parseInt(ss);
		initialized=true;		
	}
	
	public void setLayout(String ss) {
		layoutString=ss;		
	}
	
	public void setTextMain(String ss) {
		textMain=ss;
	}
	
	public void setImage(String ss) {
		imageString=ss;
	}
	
	public void setVarCount(String ss) {
		varcount=Integer.parseInt(ss);
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
		int resID=-1;
		if (null==imageString) return resID;
		
			resID = cc.getResources().getIdentifier(imageString, "drawable", cc.getPackageName());			
		
		return resID;
	}

	public Spanned getText(Context cc) {
		// TODO Auto-generated method stub		
		return Html.fromHtml(textMain);
	}
	

	
	public void textRefactoring() {		
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
		default:
			return;
		}
		
		textMain=RoomFromXML.TextClearIfdelTag(textMain);
		
	}

	public static void onExit() {		
		
	}
	
	
	// START for SeekBar
			public class SeekBarFromXml {
				public String id;
				
				public SeekBarFromXml(String mId) {
					id=mId;
				}
			}
			
			public void addSeekBar(String mId) {
				seekBarArray.add(new SeekBarFromXml(mId));		
			}
			
			public int getSeekBarCount() {
				return seekBarArray.size();
			}
			
	// END For SeekBar
	
	//START for Action
			public class ActionFromXml {
				public String text;
				public String id;
				public String roomId;
				public String type;		
				
				public ActionFromXml(String mText,String mId,String mRoomId,String mType) {
					// 
					text=mText;
					id=mId;
					roomId=mRoomId;
					type=mType;
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
			
			public String getActionType(int idx) {
				return actionArray.get(idx).type;			
			}
			
			public boolean isAction(int idx,String sType) {
				return getActionType(idx).equals(sType);	
			}
			
	// End for Action

}

