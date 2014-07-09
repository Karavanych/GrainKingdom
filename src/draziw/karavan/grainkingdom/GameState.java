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

//класс польностью статический, существует в единственном экземпляре, по уму бы надо сделать singleton

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
	    /*  --Остальная информация
	        p ('^^Внешняя/внутренняя политика:');
	        p ('^Рейтинг государства: ', Rating, ' пунктов.');
	        p ('^Гнёт: ', math.floor (Yoke), ' %');

	        --Эпидемии
	        if ( (Epidemy.bugs>0) or (Epidemy.radiation>0) or (Epidemy.plague>0) or (Epidemy.igo>0) ) then
	            p ('^^Эпидемии:');
	        end
	        if (Epidemy.bugs > 0) then
	            p ('^Нашествие саранчи. Лет до окончания: ', Epidemy.bugs);
	        end
	        if (Epidemy.radiation > 0) then
	            p ('^Радиационный фон. Лет до окончания: ', Epidemy.radiation);
	        end
	        if (Epidemy.plague > 0) then
	            p ('^Чума. Лет до окончания: ', Epidemy.plague);
	        end
	        if (Epidemy.igo > 0) then
	            p ('^Выплата дани: Лет до окончания: ', Epidemy.igo);
	        end

	        events = 0;
	        --Рекомендации
	        p ('^^Рекомендации:');
	        if ( (isScience) and ( Science.count < 50) ) then
	            p ('^Тратьте больше зерна на исследования.');
	            events = events + 1;
	        end
	        if ( (isPort) and ((Ships.sail + Ships.now) < 70) ) then
	            p ('^Стройте больше кораблей.');
	            events = events + 1;
	        end
	        if ( (Year > 35) and (People.now < (Year*2.5) ) ) then
	            p ('^Повышайте численность населения.');
	            events = events + 1;
	        end
	        if ( (math.floor(Land.now) < People.now*5) ) then
	            p ('^Купите землю. Людям негде жить!');
	            events = events + 1;
	        end
	        if ( (Year>3) and (not isSow) ) then
	            p ('^Научите людей сеять пшеницу!');
	            events = events + 1;
	            if (Corn.now < 1000) then
	                p (' Вам нужно 1000 бушелей зерна для обучения!');
	            end
	        end
	        if ( (Year > 10) and (not isScience)) then
	            p ('^Постройте академию!');
	            events = events + 1;
	            if (Corn.now < 10000) then
	                p (' Вам нужно 10000 бушелей зерна для строительства!');
	            end
	        end
	        if ( (Year > 25) and (not isPort) and (isScience) ) then
	            p ('^Постройте порт!');
	            events = events + 1;
	            if (Corn.now < 50000) then
	                p (' Вам нужно 50000 бушелей зерна для строительства!');
	            end
	        end
	        if (Yoke > 60)then
	            p ('^Народ на грани революции!');
	            events = events + 1;
	        end
	        if (People.now < 10)then
	            p ('^У Вас очень мало населения!');
	            events = events + 1;
	        end
	        if ( (Year > 70) and (not LongLife)) then
	            p ('^Купите эликсир долголетия!');
	            events = events + 1;
	            if (Corn.now < 100000) then
	                p (' Вам нужно 100000 бушелей зерна для покупки!');
	            end
	        end
	        if (People.hunger > 0) then
	            p '^В Вашем королевстве люди умирают от голода! Выделяйте больше зерна для питания!';
	            events = events + 1;
	        end
	        if (events == 0) then
	            p '^На текущий год Ваш стиль управления королевством не вызывает нареканий. Продолжайте в таком же духе!';
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
	        
	        
/*	        <p>Год %1YEAR Покупка земли.</p>
	        Стоимость одного акра земли: %2 land.currentPrice <br>               
	        <p>На данный момент у Вас: </p>
	        Зерна: %3 corn.now бушелей.<br>
	        Людей: %4 people.now человек.<br>
	        Для питания людям надо: %5 people.cornNeeded() бушелей зерна.<br>
	        Земли в наличии: %6$d land.now акров.<br>
	        Земли требуется: %7$d people.landNeeded() акров.<br>
	        С учётом зерна на питание, Вы можете купить: %8$d n акров земли за %9$d n*land.currentPrice бушелей зерна.<br>
	        Максимум Вы можете купить: %10$d m акров земли за %11$d m*land.currentPrice бушелей зерна.<br>
	        [ifdel1]С учётом того, что надо оставить хотя бы 50 бушелей зерна людям на питание, Вы не можете купить ни одного акра земли!<br>[/ifdel1]
	        [ifdel2]Сколько акров земли Вы хотите купить по цене %12$d land.currentPrice бушель/акр <br>[/ifdel2]  */
	        		
	        //Log.d("MyLogs", textMain);
	        textMain=String.format(textMain,YEAR,land.currentPrice,corn.now,people.now,people.cornNeeded(),land.now,people.landNeeded(),n,
	        		n*land.currentPrice,m,m*land.currentPrice,land.currentPrice);
	        
	        if (m == 0) {
 	            // удалим теги [ifdel2]
	        	textMain=RoomFromXML.TextDeleteIfdelString(textMain,2);
	        } else {
	        	//удалим теги [ifdel1]
	        	textMain=RoomFromXML.TextDeleteIfdelString(textMain,1);
	        }	       	        
	        
	        return textMain;
	}


	public static String sellLandOperation(String textMain) {
		  /*<p>Год %1$d</p> <p>Продажа земли.</p>
	        Стоимость одного акра земли: %2$d <br>               
	        <p>На данный момент у Вас: </p>
	        Зерна: %3$d бушелей.<br>
	        Людей: %4$d человек.<br>
	        Для питания людям надо: %5$d бушелей зерна.<br>
	        Земли в наличии: %6$d акров.<br>
	        Земли требуется: %7$d акров.<br>
	        Сколько акров земли Вы хотите продать за '%8$d бушелей/акр ?  */ 
		 textMain=String.format(textMain,YEAR,land.currentPrice,corn.now,people.now,people.cornNeeded(),land.now,people.landNeeded(),land.currentPrice);
		 return textMain;	          
	}



	
	

}
