package draziw.karavan.grainkingdom;

import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import draziw.karavan.grainkingdom.baseclass.Corn;
import draziw.karavan.grainkingdom.baseclass.Epidemy;
import draziw.karavan.grainkingdom.baseclass.Land;
import draziw.karavan.grainkingdom.baseclass.People;
import draziw.karavan.grainkingdom.baseclass.Science;
import draziw.karavan.grainkingdom.baseclass.Ships;
import draziw.karavan.grainkingdom.baseclass.Statistics;
import draziw.karavan.grainkingdom.supportclass.MyCustomSeekBar;

//����� ���������� �����������, ���������� � ������������ ����������, �� ��� �� ���� ������� singleton

public class GameState {
	public static boolean TEST=true;
	
	public static Context context;
	
    static int YEAR=0;
    static boolean LONG_LIFE=false;
    static int RATING=0;
    public static int YOKE=0;
    static int MEGA_MONSTER=218;
	public static boolean isSow=false;
	public static long DEAL=0;
	public static long DealMoney=0;
    
    static People people;
    static Land land;
    public static Corn corn;
    static Ships ships;
    public static Science science;
    public static Epidemy epidemy;
    public static Statistics statistics;
    public static ReflectAction reflectAction=new ReflectAction();
    
    public static Random MY_RANDOM=new Random();
    
    public static HashMap<String, Number> interfaceParams = new HashMap<String, Number>(); // ����������� ��� ���������� seekbar ��������

	

    public static void initialization(Context cc) {
    	context=cc;
    	
    	people=new People();
    	land=new Land();
    	corn=new Corn();
    	ships=new Ships();
    	science=new Science();
    	epidemy=new Epidemy();
    	statistics=new Statistics();
    	
    	people.now=100;
    	corn.now=10000;
    	corn.yield=5;
    	land.now=1000;
    	
    		    	    
    	GameState.svernut();
    	        	       	   
    }
	
	
	public static String fillOtherReportVars(String ss) {
	    /*  --��������� ����������
	        p ('^^�������/���������� ��������:');
	        p ('^������� �����������: ', Rating, ' �������.');
	        p ('^���: ', math.floor (Yoke), ' %');

	        --��������
	        if ( (Epidemy.bugs>0) or (Epidemy.radiation>0) or (Epidemy.plague>0) or (Epidemy.igo>0) ) then
	            p ('^^��������:');
	        end
	        if (Epidemy.bugs > 0) then
	            p ('^��������� �������. ��� �� ���������: ', Epidemy.bugs);
	        end
	        if (Epidemy.radiation > 0) then
	            p ('^������������ ���. ��� �� ���������: ', Epidemy.radiation);
	        end
	        if (Epidemy.plague > 0) then
	            p ('^����. ��� �� ���������: ', Epidemy.plague);
	        end
	        if (Epidemy.igo > 0) then
	            p ('^������� ����: ��� �� ���������: ', Epidemy.igo);
	        end

	        events = 0;
	        --������������
	        p ('^^������������:');
	        if ( (isScience) and ( Science.count < 50) ) then
	            p ('^������� ������ ����� �� ������������.');
	            events = events + 1;
	        end
	        if ( (isPort) and ((Ships.sail + Ships.now) < 70) ) then
	            p ('^������� ������ ��������.');
	            events = events + 1;
	        end
	        if ( (Year > 35) and (People.now < (Year*2.5) ) ) then
	            p ('^��������� ����������� ���������.');
	            events = events + 1;
	        end
	        if ( (math.floor(Land.now) < People.now*5) ) then
	            p ('^������ �����. ����� ����� ����!');
	            events = events + 1;
	        end
	        if ( (Year>3) and (not isSow) ) then
	            p ('^������� ����� ����� �������!');
	            events = events + 1;
	            if (Corn.now < 1000) then
	                p (' ��� ����� 1000 ������� ����� ��� ��������!');
	            end
	        end
	        if ( (Year > 10) and (not isScience)) then
	            p ('^��������� ��������!');
	            events = events + 1;
	            if (Corn.now < 10000) then
	                p (' ��� ����� 10000 ������� ����� ��� �������������!');
	            end
	        end
	        if ( (Year > 25) and (not isPort) and (isScience) ) then
	            p ('^��������� ����!');
	            events = events + 1;
	            if (Corn.now < 50000) then
	                p (' ��� ����� 50000 ������� ����� ��� �������������!');
	            end
	        end
	        if (Yoke > 60)then
	            p ('^����� �� ����� ���������!');
	            events = events + 1;
	        end
	        if (People.now < 10)then
	            p ('^� ��� ����� ���� ���������!');
	            events = events + 1;
	        end
	        if ( (Year > 70) and (not LongLife)) then
	            p ('^������ ������� ����������!');
	            events = events + 1;
	            if (Corn.now < 100000) then
	                p (' ��� ����� 100000 ������� ����� ��� �������!');
	            end
	        end
	        if (People.hunger > 0) then
	            p '^� ����� ����������� ���� ������� �� ������! ��������� ������ ����� ��� �������!';
	            events = events + 1;
	        end
	        if (events == 0) then
	            p '^�� ������� ��� ��� ����� ���������� ������������ �� �������� ���������. ����������� � ����� �� ����!';
	        end*/
	        
		return String.format(ss,RATING,YOKE);
	}
	
	public static void newYear() {					 
		
		YEAR+=1;						
		
		epidemy.newYear();
		corn.newYear();
		science.newYear();
		
	    
	    YOKE = YOKE + getRandom(10) - 5;
	    if (YOKE<0) YOKE=0;
	    
	    if (YEAR > 10) {
	        RATING = (int) Math.floor(people.now/(YEAR*2.5f)+(land.now*27+corn.now)/(YEAR*100f)+(ships.port+ships.sail)/(YEAR/10f)+(science.count)/(YEAR*0.8f));
	    }
	    else RATING = 0;
	    
	    land.newYear();
		people.newYear();								
	}
	
	public static void svernut() {
		people.svernut();
		land.svernut();
		corn.svernut();
		ships.svernut();		
	}
   
	
	public static int getRandom(int max) {		
	    return MY_RANDOM.nextInt(max);	    	 
	}


	public static String buyLandOperation(String textMain) {		
		 int tmp = getRandom (12);		 
	     land.currentPrice = 22 + tmp;
	     land.buyMax= (long) Math.floor ((corn.now - 50)/land.currentPrice);
	     land.buyMin = (long) Math.floor ( (corn.now - 50 - people.now*40) / land.currentPrice );
	        if (land.buyMax < 0) {
	        	land.buyMax = 0;
	        }
	        if (land.buyMin < 0) {
	        	land.buyMin = 0;
	        }
	        
	        
/*	        <p>��� %1YEAR ������� �����.</p>
	        ��������� ������ ���� �����: %2 land.currentPrice <br>               
	        <p>�� ������ ������ � ���: </p>
	        �����: %3 corn.now �������.<br>
	        �����: %4 people.now �������.<br>
	        ��� ������� ����� ����: %5 people.cornNeeded() ������� �����.<br>
	        ����� � �������: %6$d land.now �����.<br>
	        ����� ���������: %7$d people.landNeeded() �����.<br>
	        � ������ ����� �� �������, �� ������ ������: %8$d n ����� ����� �� %9$d n*land.currentPrice ������� �����.<br>
	        �������� �� ������ ������: %10$d m ����� ����� �� %11$d m*land.currentPrice ������� �����.<br>
	        [ifdel1]� ������ ����, ��� ���� �������� ���� �� 50 ������� ����� ����� �� �������, �� �� ������ ������ �� ������ ���� �����!<br>[/ifdel1]
	        [ifdel2]������� ����� ����� �� ������ ������ �� ���� %12$d land.currentPrice ������/��� <br>[/ifdel2]  */
	        		
	       
	        textMain=String.format(textMain,YEAR,land.currentPrice,corn.now,people.now,people.cornNeeded(),land.now,people.landNeeded(),land.buyMin,
	        		land.buyMin*land.currentPrice,land.buyMax,land.buyMax*land.currentPrice,land.currentPrice);
	        
	        if (0==land.buyMax) {
 	            // ������ ���� [ifdel2]
	        	textMain=RoomFromXML.TextDeleteIfdelString(textMain,2);
	        } else {
	        	//������ ���� [ifdel1]
	        	textMain=RoomFromXML.TextDeleteIfdelString(textMain,1);
	        }	       	        
	        
	        // ���������� ��������� ��� SeekBar
	        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MIN_VALUE_KEY, 0l);
	        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MAX_VALUE_KEY, land.buyMax);
	        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_STEP_KEY, 1l);	       	        	        
	        
	        return textMain;
	}


	public static String sellLandOperation(String textMain) {
		  /*<p>��� %1$d</p> <p>������� �����.</p>
	        ��������� ������ ���� �����: %2$d <br>               
	        <p>�� ������ ������ � ���: </p>
	        �����: %3$d �������.<br>
	        �����: %4$d �������.<br>
	        ��� ������� ����� ����: %5$d ������� �����.<br>
	        ����� � �������: %6$d �����.<br>
	        ����� ���������: %7$d �����.<br>
	        ������� ����� ����� �� ������ ������� �� '%8$d �������/��� ?  */ 
		 textMain=String.format(textMain,YEAR,land.currentPrice,corn.now,people.now,people.cornNeeded(),land.now,people.landNeeded(),land.currentPrice);
		 
		 
	        // ���������� ��������� ��� SeekBar
	        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MIN_VALUE_KEY, 0l);
	        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MAX_VALUE_KEY,land.now );
	        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_STEP_KEY, 1l);
	        
		 return textMain;	          
	}


	public static String eatOperation(String textMain) {
	 /*    <p>��� %1$d.</p> <p>�������.</p>
	        ������� ������� ����� �� ��������� ����� ���������� � ����?<br>
	        <p>�� ������ ������ � ���:<p>
	        �����: %2$d �������.<br>
	        �����: %3$d �������.<br>
	        ����� ����: %4$d ������� �����.<br>
	        <p>������� �������?<p> */
		
		textMain=String.format(textMain,YEAR,corn.now,people.now,people.cornNeeded());
		
        // ���������� ��������� ��� SeekBar
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MIN_VALUE_KEY, 0l);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MAX_VALUE_KEY,corn.now);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_STEP_KEY, 1l);
		
		return textMain;		
	}


	public static String sowOperation(String textMain) {
		
	     if (GameState.epidemy.bugs>0) {
	    	 textMain=RoomFromXML.TextDeleteIfdelString(textMain,1);
	    	 textMain=RoomFromXML.TextDeleteIfdelString(textMain,3);
	    	 
	         // ���������� ��������� ��� SeekBar,  ������� ������ ������ ��� ����
	    	 dontShowSeekBar();
	    	 
	    	 return textMain;
	     }
	     
	     if (GameState.epidemy.radiation>0) {
	    	 textMain=RoomFromXML.TextDeleteIfdelString(textMain,1);
	    	 textMain=RoomFromXML.TextDeleteIfdelString(textMain,2);	
	    	 
	         // ���������� ��������� ��� SeekBar,  ������� ������ ������ ��� ����
	    	 dontShowSeekBar();	    	 
	    	 
	    	 return textMain;
	     }
	     
	    
     GameState.land.cornToSeedAkr = (3 + GameState.science.corn/2);
	
     long p_tmp = (long) Math.floor (GameState.land.now / (1.3 - GameState.science.land/50));
     long pp = p_tmp;
     if ( pp > Math.floor (GameState.corn.now/GameState.land.cornToSeedAkr) ) {
         pp = (long) Math.floor (GameState.corn.now/GameState.land.cornToSeedAkr);
     }
     if ( pp > ((GameState.people.now)*3.5) ) { 
         pp = (long) Math.floor ((GameState.people.now)*3.5);
     }
     long m = (long) Math.floor (pp*GameState.land.cornToSeedAkr);
     
	 textMain=RoomFromXML.TextDeleteIfdelString(textMain,2);
	 textMain=RoomFromXML.TextDeleteIfdelString(textMain,3);
     
     textMain=String.format(textMain,GameState.YEAR,GameState.corn.now,p_tmp,GameState.people.now,pp,m);
     
     /*dsc = function (s)
    	        p ('������� ����� ����� �� ������� �������?');
    	        p ('^^�� ������ ������ � ���:');
    	        p ('^�����: ', Corn.now, ' �������.');
    	        p ('^����������� �����: ', p_tmp, ' �����.' );
    	        p ('^�����: ', People.now, ' �������.');
    	        p ('^����� ����� ������� ', pp, ' ����� �����. ��� ����� ����������� ', m, ' ������� �����.');
    	        p ('^������� ����� �������?')*/;
    	        
      // ���������� ��������� ��� SeekBar
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MIN_VALUE_KEY, 0l);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MAX_VALUE_KEY,pp);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_STEP_KEY, 1l);
        
        return textMain;

		
		
	}


	public static String scienceOperation(String textMain) {
		
		if (science.people==10 && science.land==10 && science.corn==10 && science.ships==10 && science.war==10 && science.buy==10 && science.policy==10 && science.medicine==10) {
			textMain=RoomFromXML.TextDeleteIfdelString(textMain,2);
			textMain=String.format(textMain,GameState.YEAR);
			dontShowSeekBar();
			return textMain;
		}
		textMain=RoomFromXML.TextDeleteIfdelString(textMain,1);
		
		
	/*    dsc = function (s)
	            p ('������� ������� ����� �� ��������� �� ������������?');
	            p ('^^�� ������ ������ � ���:');
	            p ('^�����: ', Corn.now, ' �������.');
	            p ('^�����: ', People.now, ' �������.');
	            p ('^������� �������?');*/	
		
		textMain=String.format(textMain,GameState.YEAR,GameState.corn.now,GameState.people.now);
		
	      // ���������� ��������� ��� SeekBar
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MIN_VALUE_KEY, 0l);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MAX_VALUE_KEY,GameState.corn.now);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_STEP_KEY, 1l);		
		
		
		return textMain;
	}

	public static String shipBuildOperation(String textMain) {
 /*       <p>�� ������ ������ � ���:</p>
        �����: ', Corn.now, ' �������.<br>
        �����: ', People.now, ' �������.<br>
        ���� �� ���� �������: ', ShipPrice, ' ������� �����.<br>
        ������ ��������: ', Ships.now, ' ����.<br>
        ����������� ����� ���������: ', m, ' �������� �� ', m*ShipPrice, ' ������� �����.<br>
        ������� �������� ���������?<br>*/
		int tmp = getRandom (10000);
	    GameState.ships.shipPrice = tmp + 20000 - GameState.science.buy*150 - GameState.science.ships*75;
	    long m = (long) Math.floor (GameState.corn.now/GameState.ships.shipPrice);
	    textMain=String.format(textMain,GameState.YEAR,GameState.corn.now,GameState.people.now,GameState.ships.shipPrice,GameState.ships.now,m,m*GameState.ships.shipPrice);
	    
	    // � ������ ������ ������� ������ ���� ��� ����� ������� �����
		if (m <= 0) {
			textMain = RoomFromXML.TextDeleteIfdelString(textMain, 1);
		} else {
			textMain = RoomFromXML.TextDeleteIfdelString(textMain, 2);
		}
		
	      // ���������� ��������� ��� SeekBar
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MIN_VALUE_KEY, 0l);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MAX_VALUE_KEY,m);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_STEP_KEY, 1l);			
	    
		return textMain;		
	}


	public static String portOperation(String textMain) {
		
		
		if (GameState.ships.now == 0) {
			textMain = RoomFromXML.TextDeleteIfdelString(textMain, 1);
			textMain = RoomFromXML.TextDeleteIfdelString(textMain, 3);
			textMain=String.format(textMain, GameState.YEAR);

			dontShowSeekBar();			
			return textMain;
		}			
		
       int tmp = getRandom(100);
       if (tmp > 85) {
			textMain = RoomFromXML.TextDeleteIfdelString(textMain, 1);
			textMain = RoomFromXML.TextDeleteIfdelString(textMain, 2);
			textMain=String.format(textMain, GameState.YEAR);
			dontShowSeekBar();			
			return textMain;
       }
       
       people.maxMilitary = (long) Math.floor (GameState.people.now / (1.3 - GameState.science.war/50));
       people.maxShipLoad = 15 + GameState.science.ships * 2;
       
       long m;
       if (people.maxMilitary > (GameState.ships.now*people.maxShipLoad)) {
           m = GameState.ships.now * people.maxShipLoad;
       } else {
           m = people.maxMilitary;
       }
       
       /*<p>��� %1$d.</p> <p>����������� ��������.</p>
		������� ������� �� ��������� � ��������?<br>
       <p>�� ������ ������ � ���:</p>
       ��������: ', Ships.now, ' ����.<br>
       �����: ', People.now, ' �������.<br>
       ������������� ���������: ', qq, ' �������.<br>
       ����������� ����� ���������: ', m, ' ������.<br>
       ������� ������� ���������?	*/
       textMain=String.format(textMain, GameState.YEAR,GameState.ships.now,GameState.people.now,people.maxMilitary,m);
       
       textMain = RoomFromXML.TextDeleteIfdelString(textMain, 2);
       textMain = RoomFromXML.TextDeleteIfdelString(textMain, 3);
       
       // ���������� ��������� ��� SeekBar
       interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MIN_VALUE_KEY, 0l);
       interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MAX_VALUE_KEY,m);
       interfaceParams.put(MyCustomSeekBar.SEEK_BAR_STEP_KEY, 1l);	
		
		return textMain;
	}


	private static void dontShowSeekBar() {	
		interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MIN_VALUE_KEY, 0l);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_MAX_VALUE_KEY,0l);
        interfaceParams.put(MyCustomSeekBar.SEEK_BAR_STEP_KEY, 0l);			
	}

}
