package draziw.karavan.grainkingdom;

import org.xmlpull.v1.XmlPullParser;


import android.content.Context;
import android.util.Log;

public class RoomFromXML {
	public static int tekroom;

	public static TheRoomObject create(int roomnumber,Context cc) {
		tekroom=roomnumber;
		
		return createRoomFromXML(tekroom,cc);
				    
	}
	
	public static TheRoomObject createRoomFromXML(int roomnumber,Context cc) {
		//parse xml
		TheRoomObject theRoom=null;
		try {
		      XmlPullParser xpp = cc.getResources().getXml(R.xml.roomstruct);
		      //цикл по всему xml
		      while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
		    	  if (xpp.getEventType() == XmlPullParser.START_TAG && xpp.getName().equals("room")) {
		    		  String tekRoomId = xpp.getAttributeValue(null, "id");		    		  
		    		  
		    		  
		    		  if (tekRoomId.equals(Integer.toString(roomnumber))) {
		    			  String layoutString=xpp.getAttributeValue(null, "layout");
		    			  boolean roomMultitext="multitext".equals(layoutString);
		    			  
		    			  theRoom=new TheRoomObject();
		    			  
		    			  theRoom.setId(tekRoomId);		    			  
		    			  		    			  
		    			  theRoom.setLayout(layoutString);
		    			  
		    			  		    			 
		    			  
		    			  //цикл по тегам внутри room
		    			  String tekTag = xpp.getName();		    			  
		    			  while (xpp.getEventType() != XmlPullParser.END_TAG || !xpp.getName().equals("room")) {
		    				 
		    			
		    				 //пошло начало тега, запоминаем его ниже
		    				  if (xpp.getEventType() == XmlPullParser.START_TAG) {
		    					  //запоминаем текущий тег.
			    				   tekTag=xpp.getName();
		    					  
		    					  if ("action".equals(tekTag)) {
		    						  String aId = xpp.getAttributeValue(null,"id");
			    					  String mButtonAction = xpp.getAttributeValue(null,"buttonAction");
			    					  theRoom.addAction("", aId, tekRoomId, mButtonAction);		    						  
		    					  }
		    					  
		    					  if ("seekbar".equals(tekTag)) {
		    						  String aId = xpp.getAttributeValue(null,"id");
		    						  String mButtonAction=xpp.getAttributeValue(null,"buttonAction");
		    						  theRoom.addSeekBar(aId,mButtonAction);			    						 
		    					  }	
		    					  if ("text".equals(tekTag)) {		    									    					  
			    						  String functionString = xpp.getAttributeValue(null,"function");
			    						  String imageString="";
			    						  if (roomMultitext) {
			    							  xpp.getAttributeValue(null,"image");
			    						  }			    						  
			    						  xpp.next();// делаем следующий шаг и получаем тело текста
			    						  String textString=xpp.getText();			    						  
			    						  theRoom.addMultiText(textString, functionString, imageString);			    					
		    					  }
		    				  }
		    				  		    				  
		    				  //текст внутри тегов
		    				  if (xpp.getEventType() == XmlPullParser.TEXT) {
		    					  /*	  if ("text".equals(tekTag)) {
		    						  
		    						  theRoom.setTextMain(xpp.getText());		    						  
		    					  }	*/	
		    					  if ("image".equals(tekTag)) {
		    						  theRoom.setImage(xpp.getText());
		    					  }
		    					  
		    					  if ("action".equals(tekTag)) { // заполняем текст последнего в списке
		    						  theRoom.setLastActionText(xpp.getText());
		    					  }
		    					  
		    					  if ("seekbar".equals(tekTag)) {
		    						  theRoom.setLastSeekBarText(xpp.getText());			    						 
		    					  }	
		    				  }
		    				  xpp.next();
		    			  }
		    			  //break; //нужный нам тег room определен
		    			  
		    		  }		    		  
		    	  }
		    	  xpp.next();		    	  
		      }
		} catch (Throwable t) {
		      t.printStackTrace();
		}
		return theRoom;
	}
	
	
	// функция удаляет из строки то, что заключено в теги [ifdel#] [/ifdel#], где # - заданный номер = i
	public static String TextDeleteIfdelString(String textMain, int i) {
		String strStartPattern="[ifdel"+Integer.toString(i)+"]";
		String strEndPattern="[/ifdel"+Integer.toString(i)+"]";
				
		int startPos=textMain.indexOf(strStartPattern);
		int endPos=textMain.indexOf(strEndPattern);
		
		while (startPos!=-1 || endPos!=-1) {
			// обрезаем по шаблонам
			textMain=textMain.substring(0,startPos)+textMain.substring(endPos+strEndPattern.length());
			
			// ищем, а вдруг есть еще шаблоны
			startPos=textMain.indexOf(strStartPattern);
			endPos=textMain.indexOf(strEndPattern);			
		}
				
		return textMain;
	}
	
	public static String TextClearIfdelTag(String textMain) {
		char closeChar=']';
		String strStartPattern="[ifdel";
		String strEndPattern="[/ifdel";
		
		//удаляем сначала все открывающие теги
		int startPos=textMain.indexOf(strStartPattern);
		while (startPos!=-1) {
			int endPos=startPos+strStartPattern.length();
			while (textMain.charAt(endPos)!=closeChar) {
				endPos++;				
			}
			textMain=textMain.substring(0,startPos)+textMain.substring(endPos+1); // вырезаем шаблон [ifdel#], Где # - любые символы			
			startPos=textMain.indexOf(strStartPattern);
		}
		
		//удаляем все закрывающие теги
		startPos=textMain.indexOf(strEndPattern);
		while (startPos!=-1) {
			int endPos=startPos+strEndPattern.length();
			while (textMain.charAt(endPos)!=closeChar) {
				endPos++;				
			}
			textMain=textMain.substring(0,startPos)+textMain.substring(endPos+1); // вырезаем шаблон [ifdel#], Где # - любые символы			
			startPos=textMain.indexOf(strEndPattern);
		}				
		 	
		return textMain;
	}
	
}
