package draziw.karavan.grainkingdom;

import java.util.Random;

import android.util.Log;
import draziw.karavan.grainkingdom.baseclass.Corn;
import draziw.karavan.grainkingdom.baseclass.Epidemy;
import draziw.karavan.grainkingdom.baseclass.Land;
import draziw.karavan.grainkingdom.baseclass.People;
import draziw.karavan.grainkingdom.baseclass.Science;
import draziw.karavan.grainkingdom.baseclass.Ships;
import draziw.karavan.grainkingdom.baseclass.Statistics;

//����� ���������� �����������, ���������� � ������������ ����������, �� ��� �� ���� ������� singleton

public class GameState {
    static int YEAR=0;
    static boolean LONG_LIFE=false;
    static int RATING=0;
    public static int YOKE=0;
    static int MEGA_MONSTER=218;
	public static boolean isSow=false;;
    
    static People people;
    static Land land;
    static Corn corn;
    static Ships ships;
    public static Science science;
    public static Epidemy epidemy;
    public static Statistics statistics;
    
    public static Random MY_RANDOM=new Random();
    

    	
    
    public static void initialization() {
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
		// TODO Auto-generated method stub
		 int tmp = getRandom (12);		 
	     land.currentPrice = 22 + tmp;
	     long m = (long) Math.floor ((corn.now - 50)/land.currentPrice);
	     long n = (long) Math.floor ( (corn.now - 50 - people.now*40) / land.currentPrice );
	        if (m < 0) {
	            m = 0;
	        }
	        if (n < 0) {
	            n = 0;
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
	        		
	        //Log.d("MyLogs", textMain);
	        textMain=String.format(textMain,YEAR,land.currentPrice,corn.now,people.now,people.cornNeeded(),land.now,people.landNeeded(),n,
	        		n*land.currentPrice,m,m*land.currentPrice,land.currentPrice);
	        
	        if (m == 0) {
 	            // ������ ���� [ifdel2]
	        	textMain=RoomFromXML.TextDeleteIfdelString(textMain,2);
	        } else {
	        	//������ ���� [ifdel1]
	        	textMain=RoomFromXML.TextDeleteIfdelString(textMain,1);
	        }	       	        
	        
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
		 return textMain;	          
	}



	
	

}
