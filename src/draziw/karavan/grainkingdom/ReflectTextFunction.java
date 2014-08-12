package draziw.karavan.grainkingdom;

import java.lang.reflect.InvocationTargetException;

import draziw.karavan.grainkingdom.supportclass.MyCustomSeekBar;

import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

public class ReflectTextFunction {
	
	 static java.lang.reflect.Method method;
	 
	 // ����� ������� �� ����������, ����� ���������� null;
	 
	 public static Spanned startAction(String methodName, String txt) {
		 method=null;
		 try {
			  method = ReflectTextFunction.class.getDeclaredMethod(methodName,String.class);
			} catch (SecurityException e) {
			  // ...
			} catch (NoSuchMethodException e) {
			  // ...				
				Log.d("MyLogs", " no Such Method");
			}
		 if (method!=null) {		
		 
			 try {
				  return (Spanned) method.invoke(null,txt);
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {				
				}	
		 }
		 
		 
		 return new SpannableString("(Warning! no Such Method)"+txt);
	 }
	 
	 public static Spanned thiefCorn(String txt) {
		 
		 // Thief corn
         int tmp = GameState.getRandom (100);
         if (( (tmp > (95 + GameState.science.policy*2)) && (GameState.corn.now > 100) ) || GameState.TEST){
                     
             tmp = GameState.getRandom (100);
             GameState.corn.thiefs = (long) (GameState.corn.thiefs + Math.floor (GameState.corn.now / (tmp + 100)));
             tmp = GameState.getRandom ( 15 - (int)Math.floor(GameState.science.policy/2));
             GameState.people.thiefs = GameState.people.thiefs - tmp;
             if ( (GameState.people.now + GameState.people.thiefs) < 2 ) {
                 GameState.people.thiefs = GameState.people.now - 2;
             }
             GameState.YOKE = GameState.YOKE - 2;
             GameState.statistics.thiefGrain(GameState.corn.thiefs);
             Log.d("MyLogs","txt="+txt);             
             return mFromHtml(txt);
             
         } else {//����� ������� �� ���������� ����� ���������� null
        	 return null;
        	 }	 
	 }
	 
	 public static Spanned thiefLand(String txt) {
		  // Thief land
          int tmp = GameState.getRandom (100);
          if (( (tmp > (95 + GameState.science.policy*2)) && (GameState.land.now > 100) )|| GameState.TEST){
             
             
              tmp = GameState.getRandom (10);
              GameState.land.thiefs += (long)Math.floor (GameState.land.now / (tmp + 10));
              tmp = GameState.getRandom ((int) (15 - Math.floor(GameState.science.policy/2)));
              GameState.people.thiefs = GameState.people.thiefs - tmp;
              if ( (GameState.people.now + GameState.people.thiefs) < 2 ) {
                  GameState.people.thiefs = GameState.people.now - 2;
              }
              GameState.YOKE = GameState.YOKE - 2;              
              GameState.statistics.thiefLand(GameState.land.thiefs);
              return mFromHtml(txt);
          } else {//����� ������� �� ���������� ����� ���������� null
        	  return null;
          }
	 }
	 
	 public static Spanned ratsDeadRadiation(String txt) {
         //-- Rats
         int tmp = GameState.getRandom(100);
         if (tmp > (45 + GameState.science.corn *2.5)) {
           if ((GameState.epidemy.radiation > 0) || GameState.TEST) {
                 //p (imgl 'images/rats_are_dead.png');
                 //p ('����� � ����� ������� ������ �� ��������.^');
        	   return mFromHtml(txt);
           }
          }
         return null;
	 }
	 
	 public static Spanned ratsHere(String txt) {
		//-- Rats
		 if (GameState.epidemy.radiation <= 0) {
	         int tmp = GameState.getRandom(100);
	         if (tmp > (45 + GameState.science.corn *2.5)) {  
	             
	                 if (GameState.corn.now > 0) {
	                     //p (imgl 'images/rats.png');
	                     //p ('����� ��������� ���� ������!^');
	                     GameState.corn.rats = (long) Math.floor (GameState.corn.now / (20 + GameState.science.corn * 1.5));
	                     GameState.statistics.rats(GameState.corn.rats);
	                     return mFromHtml(txt);
	                 }
	           }
	         }	            
	         return null;
	 }
	 
	 public static Spanned hardRain(String txt) {
		 //-- Hard rain
         int tmp = GameState.getRandom(100);
         if (((tmp > 79) && (GameState.corn.seed > 0))|| GameState.TEST) {
             //p (imgl 'images/rain.png');
             //p ('������� ���� ��������� ���� ������!^');
             GameState.statistics.hardRain(GameState.corn.harvest);
             GameState.corn.harvest = 0;    
             return mFromHtml(txt);
         }
         
		 return null;
	 }
	 
	 public static Spanned cholera(String txt) {
	 
		 //-- Cholera
	     int tmp = GameState.getRandom (100);
	     if ( tmp > (75 + GameState.science.medicine*2) || GameState.TEST) {
	         //p (imgl 'images/graveyard.png');
	         //p ('�������� ������ �������� �����������!^');
	         GameState.people.illness = (long) (GameState.people.illness + Math.floor (GameState.people.now / (10 + GameState.science.medicine)));
	         GameState.YOKE = (int) (GameState.YOKE + Math.floor (GameState.people.illness/50));	         
	         GameState.statistics.disease(GameState.people.illness);
	         return mFromHtml(txt);
	     } 
	     return null;
	 }
	 
	 public static Spanned disease(String txt) {
	     //-- Disease
	     int tmp = GameState.getRandom(100);
	     if ( tmp > (95 + GameState.science.medicine*2) || GameState.TEST) {
	         //p (imgl 'images/graveyard.png');
	         //p ('�������� ����� ������� �������� ���������!^');
	         GameState.people.illness = (long) (GameState.people.illness + Math.floor(GameState.people.now / (2 + GameState.science.medicine/10)));
	         GameState.YOKE = (int) (GameState.YOKE + Math.floor(GameState.people.illness/50));         
	         GameState.statistics.disease(GameState.people.illness);
	         return mFromHtml(txt);
	     }
	     return null;
	 }
	 
	 public static Spanned locustRadiation(String txt) {
		//-- Locust radiation
		 if (GameState.epidemy.radiation > 0) {
		     int tmp = GameState.getRandom(100);
		     if ( (GameState.epidemy.bugs == 0) && (tmp > (89.75 + GameState.science.medicine))) {
		         
		             //p (imgl 'images/locust.png');
		             //p ('��-�� �������� ������� �� ������ ��������� � ����� �����.^');	
		        	 return mFromHtml(txt);
		     }
	     }
		 
		 return null;
	 }
	
	 public static Spanned locust(String txt) {
	     //-- Locust
		 if (GameState.epidemy.radiation <= 0) {
		     int tmp = GameState.getRandom(100);
		     if ( (GameState.epidemy.bugs == 0) && (tmp > (89.75 + GameState.science.medicine)) || GameState.TEST) {	         
		             //p (imgl 'images/locust.png');
		             //p ('��������� ��������� �������!^');
		             GameState.epidemy.bugs = 3;
		             GameState.statistics.locust(1);
		             return mFromHtml(txt);	             
	         }	         
	     }
	     return null;
	 }
	 
	 public static Spanned plague(String txt) {
	     //-- Plague
	     int tmp = GameState.getRandom(1000);
	     if (((tmp > (979 + GameState.science.medicine*2)) && (GameState.epidemy.plague == 0)) || GameState.TEST) {
	         tmp = GameState.getRandom(3);
	         int a = tmp + 5;
	         //p (imgl 'images/graveyard.png');
	         //p ('���� ����������� �������� ����!^��������� �����������: ������� ����������, ������ �����������.');
	         //p ('^�� ��������� ������ ������� �������� ������ ����� ', a, ' ���!^');
	         txt=String.format(txt, a);
	         GameState.epidemy.plague = a;	       
	         GameState.statistics.plague(1);
	         return mFromHtml(txt);
	     }
	     return null;
	 }
	 
	 public static Spanned meteorites(String txt) {
	     //-- meteorites
	     int tmp = GameState.getRandom (1000);
	     if (( (tmp > 975) && (GameState.science.isScience) && (GameState.epidemy.radiation == 0)) || GameState.TEST) {
	         tmp = GameState.getRandom (5);
	         int a = tmp + 10;
	         //p (imgl 'images/graveyard.png');
	         //p ('������� ��������� �������� ������� ������� ������������ ��� � �����������.^��������� �����������: ������ ����������� � ������.');
	         //p ('^������ ����������, ��� ������ ����������� ������ � ��������� ', a, ' ���.^');
	         txt=String.format(txt, a);
	         GameState.epidemy.radiation = a;	         
	         GameState.statistics.meteorites(1);
	         return mFromHtml(txt);
	     }
	     return null;
	 }
	 
	 public static Spanned landDec(String txt) {
	     //-- land dec
	     int tmp = GameState.getRandom(1000);
	     if (( (tmp > (875 + GameState.science.land*13.3)) && (GameState.science.isScience) )|| GameState.TEST) {
	         //p (imgl 'images/dry_land.png');
	         //p ('� ���������� ��������� ������ ������ ���� ��������� ����� �����.^');
	         tmp = GameState.getRandom(10);
	         GameState.land.spoiled = (long) (GameState.land.spoiled + Math.floor (GameState.land.now / (tmp + 10)));	         
	         GameState.statistics.landSpoiled(GameState.land.spoiled);
	         return mFromHtml(txt);
	     }
	     return null;
	 }
	 
	 public static Spanned cornDec(String txt) {
     //-- corn dec
	     int tmp = GameState.getRandom (1000);
	     if ( (tmp > (875 + GameState.science.corn*13.3) && (GameState.science.isScience))|| GameState.TEST) {
	         //p (imgl 'images/sand.png');
	         //p('����� ������-���������� ������ ����� ������������ �����.^');
	         tmp = GameState.getRandom (100);
	         GameState.corn.spoiled = (long) (GameState.corn.spoiled + Math.floor (GameState.corn.now / (tmp + 100)));	        
	         GameState.statistics.grainSpoiled(GameState.corn.spoiled);
	         return mFromHtml(txt);
	     }
	     return null;
	 }
	 
	 public static Spanned peopleLeave(String txt) {
	     //-- people leave
	     int tmp = GameState.getRandom(100);
	     if ( tmp > (85 - GameState.YOKE/2.5) || GameState.TEST ) {
	         //p (imgl 'images/rebellion.png');
	         //p ('��������� ������� �������� ���� �����������, ����������� ������� �����.^');
	         tmp = GameState.getRandom ( (int) (15 - Math.floor(GameState.science.policy/2)) );
	         GameState.people.balance = GameState.people.balance - tmp;
	         if ( (GameState.people.now + GameState.people.balance) < 2 ) {
	             GameState.people.balance = GameState.people.now - 2;
	         }         
	         GameState.statistics.peopleLeft(GameState.people.balance);
	         return mFromHtml(txt);
	     }
	     return null;
	 }

	 public static Spanned dealIsOff(String txt) {
	     //-- deal is off
	     int tmp = GameState.getRandom(101);
	     if ( ((GameState.DEAL > 0) && (tmp > 99) ) || GameState.TEST ) {
	         //p (imgl 'images/dry_land.png');
	         //p ('�����������, � ������� �� ��������� ������, ����������� ���������.^���� �������� ��������� �������.^');
	         GameState.statistics.dealOff(GameState.corn.deal);
	         GameState.corn.deal = 0;
	         GameState.DEAL = 0;	
	         return mFromHtml(txt);
	     }
	     return null;
	 }
	 
	 public static Spanned warIgo(String txt) {
     //-- war
	     int tmp = GameState.getRandom(101);
	     if ( ((GameState.YEAR>15) && (tmp>98) && (GameState.epidemy.igo==0) && (GameState.epidemy.radiation==0) && (GameState.YEAR<85) )|| GameState.TEST ) {
	         //p (imgl 'images/city_army_approach.png');
	         tmp = GameState.getRandom (5);
	         int a = tmp + 5;
	         // ('� ������ ������ ����� ����������� ��������� ������. ����������������� ����� ������ ���������� ��� ������: ���������� ������ ����� �� ��������:');
	         // ('^� ������� ', a, ' ��� �� �������� ������� ��� ��������� ����, � �� � ���� ������� ��� �� ������.^');
	         GameState.epidemy.igo = a;
	         txt=String.format(txt, a);
	         return mFromHtml(txt);
	     }
	     return null;
	 }

	 public static Spanned goodNews(String txt) {
	     //-- //Good News
	     //-- people add
	     int tmp = GameState.getRandom(100);
	     if ((tmp > 75) || GameState.TEST ) {
	         //p (imgl 'images/lazy_people.png');
	         //p ('�������� ������� ������� ������ ��������� � ������ �����������.^');
	         tmp = GameState.getRandom (10);
	         int a = (int) (tmp + 10 + Math.floor (GameState.science.policy/2));
	         GameState.people.balance = GameState.people.balance + a;
	         GameState.YOKE = GameState.YOKE - 2;	        
	         GameState.statistics.nomads(GameState.people.balance);
	         return mFromHtml(txt);
	     }
	     return null;	     	     
	 }
	 
	 public static Spanned demographicBoom (String txt) {
	     //-- people add
	     int tmp = GameState.getRandom (100);
	     if ((tmp > 75)|| GameState.TEST) {
	         //p (imgl 'images/population_grow.png');
	         //p ('� ����� ����������� ��������� ��������������� �����!^');
	         tmp = GameState.getRandom (10);
	         int a = (int) (tmp + 15 + Math.floor (GameState.science.policy/2));
	         GameState.people.born = GameState.people.born + a;
	         GameState.YOKE = GameState.YOKE - 2;
	         return mFromHtml(txt);
	     }
	     return null;
	 }
	 
	 public static Spanned artefacts(String txt) {
	     //-- artefacts
	     int tmp = GameState.getRandom (100);
	     if (( (tmp > 90) && (GameState.science.isScience) ) || GameState.TEST) {
	         //p (imgl 'images/city_corn.png');
	         //p ('� ���������� �������� ���� ��������� ���������� �� ������ ���������, ������� ��� ����� �������� �� ����� � �������� �����������.^');
	         tmp = GameState.getRandom(100);
	         GameState.corn.bonus = (long) (GameState.corn.bonus + Math.floor ( GameState.YEAR * 500 * (1 + tmp*0.01) ));	        
	         GameState.statistics.artefacts(GameState.corn.bonus);
	         return mFromHtml(txt);
	     }
	     return null;
	 }

	 public static Spanned cornAdd(String txt) {
	     //-- corn add
	     int tmp = GameState.getRandom (100);
	     if ((tmp > (70 - GameState.science.corn ) && (GameState.science.isScience)) || GameState.TEST) {
	         //p (imgl 'images/city_corn.png');
	         //p ('������ ������ ����������� ������� �������� ������� ������������� �����.^');
	         tmp = GameState.getRandom (2500);
	         int a = tmp + 2500;
	         GameState.corn.bonus = GameState.corn.bonus + a;	         
	         GameState.statistics.artificialGrain(GameState.corn.bonus);
	         return mFromHtml(txt);
	     }
	     return null;
	 }
     
	 public static Spanned landAdd(String txt) {
	     //-- land add
	     int tmp = GameState.getRandom (100);
	     if (( tmp > (70 - GameState.science.land) && (GameState.science.isScience)) || GameState.TEST){
	         //p (imgl 'images/dry_land.png');
	         //p ('������ ������� ������� ������������ ������������ ����� �� �������� ������ �����������. ������ ��� ����������� ���.^');
	         tmp = GameState.getRandom (250);
	         GameState.land.bonus = tmp + 250;	        
	         GameState.statistics.landFertalization(GameState.land.bonus);
	         return mFromHtml(txt);	         
	     }
	     return null;
	 }

	 public static Spanned locustRemove(String txt) {
	     //-- locust remove
	     int tmp = GameState.getRandom (100);
	     if (( (tmp > 90) && (GameState.epidemy.bugs > 0) && (GameState.epidemy.bugs < 7) )|| GameState.TEST) {
	         //p (imgl 'images/corn.png');
	         //p ('����� ����� ������� ����� �������� �� ����������� �������.^');
	         GameState.epidemy.bugs = 0;
	         GameState.YOKE = GameState.YOKE - 2;	         
	         GameState.statistics.locustRemove(1);
	         return mFromHtml(txt);	
	     }
	     return null;
	 }
	 
	 public static Spanned radiationOff(String txt) {
	     //-- radiation off
	     int tmp = GameState.getRandom (100);
	     if (((tmp>90)&& (GameState.epidemy.radiation>0) && (GameState.epidemy.radiation<12)&&(GameState.science.isScience)) || GameState.TEST) {
	         //p (imgl 'images/city_corn.png');
	         //p ('������ ������� ����������� �������� ������������ ����.^');
	         GameState.epidemy.radiation = 0;
	         GameState.YOKE = GameState.YOKE - 2;	         
	         GameState.statistics.radiationOff(1);
	         return mFromHtml(txt);	
	     }
	     return null;
	 }

	 public static Spanned plagueOff(String txt) {
	     //-- plague off
	     int tmp = GameState.getRandom (100);
	     if (((tmp>90)&&(GameState.epidemy.plague>0) && (GameState.epidemy.plague<6) && (GameState.science.isScience))|| GameState.TEST) {
	         //p (imgl 'images/city_corn.png');
	         //p ('������������, �� ������ �������� ���� ����������� ��������� �� ����.^');
	         GameState.epidemy.plague = 0;
	         GameState.YOKE = GameState.YOKE - 2;	         
	         GameState.statistics.plagueOff(1);
	         return mFromHtml(txt);	
	     }
	     return null;
	 }

	 public static Spanned radiationLow(String txt) {
	     //-- Epidemies and Deals
	     if (GameState.epidemy.radiation > 0) {
	         GameState.land.spoiled = (long) (GameState.land.spoiled + Math.floor (GameState.land.now / (50 + GameState.science.land * 5)));
	         GameState.epidemy.radiation = GameState.epidemy.radiation - 1;
	         GameState.YOKE = GameState.YOKE + 1;
	         if (GameState.epidemy.radiation == 0) {
	             //p (imgl 'images/lazy_people.png');
	             //p ('������ ��������� �������� - �������� �������!^');
	        	 return mFromHtml(txt);	             
	         }
	     }
	     return null;
	 }

	 public static Spanned plagueLow(String txt) {
	     if (GameState.epidemy.plague > 0) {
	         GameState.people.illness = (long) (GameState.people.illness + Math.floor (GameState.people.now / (2 + GameState.science.medicine/10) ));
	         GameState.epidemy.plague = GameState.epidemy.plague - 1;
	         GameState.YOKE = (int) (GameState.YOKE + Math.floor (GameState.people.illness / 50));
	         if (GameState.epidemy.plague == 0) {
	             //p (imgl 'images/lazy_people.png');
	             //p ('������ �������� �� ����� - ���� ����������!^');	
	        	 return mFromHtml(txt);
	         }
	     }
	     return null;
	 }

	 public static Spanned bugsLow(String txt){
	     if (GameState.epidemy.bugs > 0) {
	         GameState.epidemy.bugs = GameState.epidemy.bugs - 1;
	         if (GameState.epidemy.bugs == 0) {
	             //p (imgl 'images/city_corn.png');
	             //p ('������� �������-�� ���������!^');
	             //return mFromHtml(txt);
	        	 return mFromHtml(txt);
	         }
	     }
	     return null;
	 }

	 public static Spanned dealDone(String txt) {
	     if (GameState.DEAL > 0) {
	    	 GameState.DEAL = GameState.DEAL - 1;
	         if (GameState.DEAL == 0) {
	             //p (imgl 'images/city_corn.png');
	             //p ('����������� ���! ������ � �������� ������������ ������� ���������!^');
	             GameState.corn.deal = -GameState.DealMoney * 3;
	             GameState.corn.now = GameState.corn.now + GameState.corn.deal;
	             GameState.statistics.dealNumber(1);
	             return mFromHtml(txt);	             
	         }	         
	     }
	     return null;
	 }
	 
	 public static Spanned year7(String txt) {
		   //--  Secret Messages
	       if ((GameState.YEAR == 7) || GameState.TEST) {
	           //p (imgl 'images/main.png');
	           //p ('������������ ����� �������� ���������� �����:^���������� ���������:^���� ���� �����������, ������ ������ ������ ���������� ����� ����������� � ������. ������ ��������� � �������������, ��� � ����� ������ �� ����������� ����� �������.^^                                               ��� ������ ��������.�^');
	           //return mFromHtml(txt);
	    	   return mFromHtml(txt);	   
	       }
	       return null;
	 }

	 public static Spanned year40(String txt) { 
	       if ((GameState.YEAR == 40) || GameState.TEST) {
	           //p (imgl 'images/main.png');
	           //p ('������� ����� � ��� �������� ����� � �������� ��� ������� ���������:^^���������� ���������:^^������ ��� �������� ������ ����������, ��� ���� �������� ��������� ������ ������� � ����� ����. �� ��������� ����� ������, ������ � �� ������ ����� ��������� �� ��� ���� ��� ��������� ����������. ���� ������ ��������� ��������� �������, ��� ������ ����� ����� ������. ����� ������ ������!^^                                               ��� ������ ��������.�^');
	       		return mFromHtml(txt);
	       }
	       return null;
	 }
	 
	 public static Spanned year49(String txt) {

       if ((GameState.YEAR == 49) || GameState.TEST){
           //p (imgl 'images/main.png');
           //p ('� ��� ������� ��� ������� �����. � ����� � ���� ��������:^^���������� ���������:^^������� ���������� �����! ��� ������ � ������� ��� ��������� ����� � ��� �� ������ ���� ����� ����� � �������� � ������������ ����. �� � ���� ��� ����� ������! ������� ������ ����������� �� ���, ��� ������ �� ������� � �����. ��� ��� ���� ������. � ���� � ����. ������ ����� � ��� �� ����. �� �������: ������� ��� ������! ������� ������ ��������, ��� ������ ������ ���������� ������ ���� �������.^                                                       ��� ������ ��������.�^');
           return mFromHtml(txt);
       }
       return null;
	 }

	 public static Spanned year59(String txt) {
	       if ((GameState.YEAR == 59) || GameState.TEST) {
	           //p (imgl 'images/main.png');
	           //p ('������ �� ���������� � ���� ������ ��������� ��������^^��������:^�...��������� ����� ������ ���������� ������: ������� ��� ���? ������ �� ��������. � ����, ��� �� ����� �� ������ �������� ���� ����. �� �� ��� �� ������. � ��� ��������� �. ����� �� ����� 100000 ������� ����� � ��������� ������� �������� ���� ����� � ������� ����������� ��������. ���� ������ ������� ���������� ��� � ����� ������ ����. � �� ������� ��� � �������. ����� ��� �����, ��� ������ ��������. �� ���� ������ ������� ���� ����������� ����� � � ������ ���������� �������.�^');
	           return mFromHtml(txt);
	       }
	       return null;
	 }

	 public static Spanned year80(String txt) {
       if ((GameState.YEAR == 80)|| GameState.TEST) {
           //p (imgl 'images/main.png');
           //p ('� ������� ��� ������ ����� ������� �������.^�� ������ ���� ������� ���, �� ...:^�����������, ���������� ����! � �� �����-������ ����. � - ������. ��... ������ ������. ����� ����������� ��� ���. ��� ���� ��� � �������� ���: �������, ...���� � ������ ������. �������� ���� ������ ���� ��������� �� ���!^�� ������ ����������� ������ ������� � �������������� ��������...^�����������, �� �� ���� ������ � ������ �������� ������� � ������� �����������. � �������� ������� ��� � ������: ������ �� �������� ������������� ����� ���������� �����. ���� �� ������ �� �����������, ���� ��� ����� �������. ����� �������� �������. � ������ ���, ��� ������ ������� ������ ����������.�^');
           int tmp = GameState.getRandom (5);
           GameState.MEGA_MONSTER = 95 + tmp;
           GameState.epidemy.igo = 0;
           return mFromHtml(txt);
       }
       return null;
	 }
	 
	 public static Spanned yearMegaMonster(String txt) {

       if ((GameState.YEAR == GameState.MEGA_MONSTER) || GameState.TEST) {
           //p (imgl 'images/main.png');
           //p ('� ��� ������� ��������� ����, ������ �� ����� �������� ����, �� �� ����� �� �����, ������ ��������.^�� ������ �� ������������ �� ������ � ���������, ��� �� ���� ������. ���� ���������� � �������� ����, ������� ������. �������� ��� ��������� �� ����� � �����... ����� ���� ����� ��������� ����. �� ���������� ����� ������, �� ��� ��������� ������, ��� �������. ��� ������-���������.^�� ���-�, ���� ��������� � ��������� �����. ����������, �������� �� ��� ���, �� ���� ��������� ��� ����� ������ ���� ������ ������� � ��!^');
           return mFromHtml(txt);
       }
       return null;
	 }
	 
	 public static Spanned room1started(String txt){
		 return mFromHtml(txt);		 
	 }
	 
	 public static Spanned room2started(String txt){
		 return mFromHtml(txt);			 
	 }
	 
	 public static Spanned room3started(String txt) {
		 return mFromHtml(GameState.people.fillReportVars(txt));
	 }
	 
	 public static Spanned room4started(String txt) {
		 return mFromHtml(GameState.land.fillReportVars(txt));
	 }
	 
	 public static Spanned room5started(String txt) {
		 return mFromHtml(GameState.corn.fillReportVars(txt));
	 }
	 
	 public static Spanned room6started(String txt) {
		 return mFromHtml(GameState.ships.fillReportVars(txt));
	 }
	 
	 public static Spanned room7started(String txt) {
		 return mFromHtml(GameState.science.fillReportVars(txt));
	 }
	 
	 public static Spanned room8started(String txt) {
		 return mFromHtml(GameState.fillOtherReportVars(txt));
	 }	
	 
	 public static Spanned room9started(String txt) {
		 return mFromHtml(GameState.buyLandOperation(txt));
	 }	
	 
	 public static Spanned room10started(String txt) {
		 return mFromHtml(GameState.sellLandOperation(txt));
	 }	
	 
	 public static Spanned room11started(String txt) {
		 return mFromHtml(GameState.eatOperation(txt));
	 }	
	 
	 public static Spanned room12started(String txt) {
		 return mFromHtml(GameState.sowOperation(txt));
	 }	
	 
	 public static Spanned room13started(String txt) {
		 return mFromHtml(GameState.scienceOperation(txt));
	 }	
	 
	 public static Spanned room14started(String txt) {
		 return mFromHtml(GameState.shipBuildOperation(txt));
	 }	
	 
	 public static Spanned room15started(String txt) {
		 return mFromHtml(GameState.portOperation(txt));
	 }	
	 
	// room 16 - news, mulitext
	 
	 public static Spanned room17started(String txt) {
		 int tmp = GameState.getRandom(100);
	        if (( (tmp>95) && (GameState.DEAL == 0) && (GameState.corn.now >= 5000) ) || GameState.TEST) {
		/* <p>��� %1$d.</p> 
	        p ('�������� �� ��������� ����������� ���������� �������������� ������ � ������� ������� � ���������� ������� ����� 5 ���. ��� ����� ���� ����� ������� ����� � �� ������.^���������� ����� ������� 5000 �������, �������� - 100000 �������.');
	        p ('^������� ������� ����� �� ������� � ����?');
	        p ('^^�� ������ ������ � ���:');
	        p ('^�����: ', Corn.now, ' �������.');
	        p ('^�����: ', People.now, ' �������.');
	        p ('^����� ��� ������� �����: ', People.now*40, ' ������� �����.');
	        p ('^����������� ������: ', Corn.harvest, ' ������� �����.');
	        p ('^������� �������?');*/	
			 txt=String.format(txt,GameState.YEAR,GameState.corn.now,GameState.people.now,GameState.people.cornNeeded(),GameState.corn.harvest);
			 GameState.setSeekBarParams(0, GameState.corn.now, 1);		     
		        
			 return mFromHtml(txt);
		 }
		      // ���������� ��������� ��� SeekBar
	        GameState.dontShowSeekBar();
	        
		 return null;
	 }	
	 
	 public static Spanned room18started(String txt){
		 if ((GameState.epidemy.igo > 0 ) || GameState.TEST) {
	         GameState.epidemy.igo = GameState.epidemy.igo - 1;
	         if (GameState.epidemy.igo == 0) {
	        	 txt=RoomFromXML.TextDeleteIfdelString(txt,1);
	        	 txt=RoomFromXML.TextDeleteIfdelString(txt,2);	
	        	 txt=String.format(txt,GameState.YEAR);
	        	 // ��� ������ ��������
	        	 RoomView.TheTekRoom.removeAction(1);
	        	 RoomView.TheTekRoom.removeAction(2);
	        	 return mFromHtml(txt);
	         }
	         int tmp = GameState.getRandom (1001);
	         if ( tmp > 975) {
	        	 txt=RoomFromXML.TextDeleteIfdelString(txt,1);
	        	 txt=RoomFromXML.TextDeleteIfdelString(txt,3);	
	        	 txt=String.format(txt,GameState.YEAR);
	        	 RoomView.TheTekRoom.removeAction(1);
	        	 RoomView.TheTekRoom.removeAction(2);	
	        	 GameState.epidemy.igo = 0;
	        	 return mFromHtml(txt);
	         } else {
	        	 txt=RoomFromXML.TextDeleteIfdelString(txt,2);
	        	 txt=RoomFromXML.TextDeleteIfdelString(txt,3);
	        	 RoomView.TheTekRoom.removeAction(3);	      
	        	 
	           /* <p>��� %1$d.</p> 
	            *  p ('������ ����� ������� ����. ��������� ��������� ��������� ����������. ���������� ������ ���:');
	             p ('^ - ', pp, ' �������;');
	             p ('^ - ', v, ' ����� �����;');
	             p ('^ - ', qq,  ' ������� �����.');
	             p ('^^��� �� �����������?');*/
	             long pp = GameState.people.now/10; 
	             long qq = GameState.corn.now/10;
	             long v = GameState.land.now/10;
	             
	        	 GameState.stackVar.push(pp);
	        	 GameState.stackVar.push(qq);
	        	 GameState.stackVar.push(v);
	             
	        	 txt=String.format(txt, GameState.YEAR,pp,v,qq);

	        	 
	             return mFromHtml(txt);
	         }
		 }
		 return null;
	 }
	 
	 public static Spanned mFromHtml(String txt) {
		 txt=RoomFromXML.TextClearIfdelTag(txt);
		 return Html.fromHtml(txt);		 
	 }

}
