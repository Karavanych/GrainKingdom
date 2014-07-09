package draziw.karavan.grainkingdom;

import org.xmlpull.v1.XmlPullParser;





import android.app.Activity;
import android.util.Log;

public class RoomFromXML {
	public static int tekroom;

	public static TheRoomObject create(int roomnumber,Activity cc) {
		tekroom=roomnumber;
		
		return createRoomFromXML(tekroom,cc);
				    
	}
	
	public static TheRoomObject createRoomFromXML(int roomnumber,Activity cc) {
		//parse xml
		TheRoomObject theRoom=new TheRoomObject();
		try {
		      XmlPullParser xpp = cc.getResources().getXml(R.xml.roomstruct);
		      //���� �� ����� xml
		      while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
		    	  if (xpp.getEventType() == XmlPullParser.START_TAG && xpp.getName().equals("room")) {
		    		  String tekRoomId = xpp.getAttributeValue(null, "id");		    		  
		    		  
		    		  if (tekRoomId.equals(Integer.toString(roomnumber))) {
		    			  theRoom.setId(tekRoomId);
		    			  
		    			  //���������� ����� layout ������ ���� � �������
		    			  theRoom.setLayout(xpp.getAttributeValue(null, "layout"));		    			 
		    			  
		    			  //String textMain="";
		    			  //String imageString="";
		    			  //ArrayList<ActionFromXml> actionArray=new ArrayList<ActionFromXml>();
		    			  
		    			  //���� �� ����� ������ room
		    			  String tekTag = xpp.getName();		    			  
		    			  while (xpp.getEventType() != XmlPullParser.END_TAG || !xpp.getName().equals("room")) {
		    				 
		    			
		    				 //����� ������ ����, ���������� ��� ����
		    				  if (xpp.getEventType() == XmlPullParser.START_TAG) {
		    					  //���������� ������� ���.
			    				   tekTag=xpp.getName();
		    					  
		    					  if ("action".equals(tekTag)) {
		    						  String aId = xpp.getAttributeValue(null,"id");
			    					  String aType = xpp.getAttributeValue(null,"type");
			    					  theRoom.addAction("", aId, tekRoomId, aType);		    						  
		    					  }
		    					  
		    					  if ("seekbar".equals(tekTag)) {
		    						  String aId = xpp.getAttributeValue(null,"id");
		    						  theRoom.addSeekBar(aId);			    						 
		    					  }		    					  		    					  
		    				  }
		    				  		    				  
		    				  //����� ������ �����
		    				  if (xpp.getEventType() == XmlPullParser.TEXT) {
		    					  if ("text".equals(tekTag)) {
		    						  theRoom.setTextMain(xpp.getText());		    						  
		    					  }		
		    					  if ("image".equals(tekTag)) {
		    						  theRoom.setImage(xpp.getText());
		    					  }
		    					  
		    					  if ("action".equals(tekTag)) { // ��������� ����� ���������� � ������
		    						  theRoom.setLastActionText(xpp.getText());
		    					  }
		    				  }
		    				  xpp.next();
		    			  }
		    			  //break; //������ ��� ��� room ���������
		    			  
		    		  }		    		  
		    	  }
		    	  xpp.next();		    	  
		      }
		} catch (Throwable t) {
		      t.printStackTrace();
		}
		return theRoom;
	}
	
	
	// ������� ������� �� ������ ��, ��� ��������� � ���� [ifdel#] [/ifdel#], ��� # - �������� ����� = i
	public static String TextDeleteIfdelString(String textMain, int i) {
		String strStartPattern="[ifdel"+Integer.toString(i)+"]";
		String strEndPattern="[/ifdel"+Integer.toString(i)+"]";
				
		int startPos=textMain.indexOf(strStartPattern);
		int endPos=textMain.indexOf(strEndPattern);
		
		while (startPos!=-1 || endPos!=-1) {
			// �������� �� ��������
			textMain=textMain.substring(0,startPos)+textMain.substring(endPos+strEndPattern.length());
			
			// ����, � ����� ���� ��� �������
			startPos=textMain.indexOf(strStartPattern);
			endPos=textMain.indexOf(strEndPattern);			
		}
				
		return textMain;
	}
	
	public static String TextClearIfdelTag(String textMain) {
		char closeChar=']';
		String strStartPattern="[ifdel";
		String strEndPattern="[/ifdel";
		
		//������� ������� ��� ����������� ����
		int startPos=textMain.indexOf(strStartPattern);
		while (startPos!=-1) {
			int endPos=startPos+strStartPattern.length();
			while (textMain.charAt(endPos)!=closeChar) {
				endPos++;				
			}
			textMain=textMain.substring(0,startPos)+textMain.substring(endPos+1); // �������� ������ [ifdel#], ��� # - ����� �������			
			startPos=textMain.indexOf(strStartPattern);
		}
		
		//������� ��� ����������� ����
		startPos=textMain.indexOf(strEndPattern);
		while (startPos!=-1) {
			int endPos=startPos+strEndPattern.length();
			while (textMain.charAt(endPos)!=closeChar) {
				endPos++;				
			}
			textMain=textMain.substring(0,startPos)+textMain.substring(endPos+1); // �������� ������ [ifdel#], ��� # - ����� �������			
			startPos=textMain.indexOf(strEndPattern);
		}				
		 	
		return textMain;
	}
	
}
