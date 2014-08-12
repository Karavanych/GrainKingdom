package draziw.karavan.grainkingdom;

import java.lang.reflect.InvocationTargetException;

import draziw.karavan.grainkingdom.baseclass.Land;
import draziw.karavan.grainkingdom.baseclass.People;

import android.app.AlertDialog;
import android.util.Log;

// класс используется для вызовов по названиям процедур описанных в roomstruct.xml
public class ReflectAction {
	
	 java.lang.reflect.Method method;
	 
	 public void startAction(String methodName, long arg1) {
		 method=null;
		 try {
			  method = this.getClass().getDeclaredMethod(methodName,long.class);
			} catch (SecurityException e) {
			  // ...
			} catch (NoSuchMethodException e) {
			  // ...				
				Log.d("MyLogs", " no Such Method");
			}
		 		
		 if (null==method) {
			 showDialog(" no Such Method");
			 return;			 
		 }
		 
		 try {
			  method.invoke(this, arg1);
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {				
			}		 
	 }
	 
	 public void buyLandAction(long tekBuy) {
	
		 if (0<tekBuy && tekBuy<=GameState.land.buyMax) {
			
			 
			 String showText=GameState.context.getResources().getString(R.string.buyLand);
			 showText=String.format(showText,tekBuy,tekBuy*GameState.land.currentPrice);
			 if (GameState.corn.now-tekBuy*GameState.land.currentPrice < GameState.people.cornNeeded()) {
				 showText=showText+"\n"+GameState.context.getResources().getString(R.string.buyLandHunger);
			 }
			 showDialog(showText);
			 
	
			 
			 GameState.corn.land =  (-tekBuy * GameState.land.currentPrice);
		     GameState.land.corn = tekBuy;
		     GameState.land.now += tekBuy;
		     GameState.corn.now += GameState.corn.land;
		     GameState.statistics.landPurchased(tekBuy);
		     
		        
		     GameState.land.currentPrice -= 1;
		     //TODO тут под вопросом формула ниже, вообще не будет срабатывать, policy - float ?
		     int  tmp = GameState.getRandom(10 + 40 * GameState.science.policy);
		     if (tmp < 10) { 
		    	 GameState.land.currentPrice -= 1;
		     }		 
		     
		     RoomView.nextRoom();
			 
		 }		 
	 }
	 
	 public void sellLandAction(long tekSell) {
		 
		 if (0<tekSell && tekSell<=GameState.land.now) {
			 if (tekSell>GameState.land.now-Land.LAND_MINIMAL) {
				 showDialog(GameState.context.getResources().getString(R.string.sellLandMinimal)); 
				 //нужен перевыбор
				 return;
			 }
			 String showText="";
			 
		       if ( tekSell > (1000 + GameState.science.buy*500) ) {
	            GameState.land.currentPrice-=1;
	            showText+=GameState.context.getResources().getString(R.string.sellLandTooMuch);
	            showText=String.format(showText,GameState.land.currentPrice);
	            showText+="\n";
		       }
		       
		       
		      //продали земли %1 за бушелей зерна %2
		       showText+=String.format(GameState.context.getResources().getString(R.string.sellLand),tekSell,GameState.land.currentPrice * tekSell);
		       
		       GameState.land.corn -= tekSell;
		       GameState.land.now = GameState.land.now - tekSell;
		       GameState.corn.land += GameState.land.currentPrice * tekSell;
		       GameState.corn.now += GameState.land.currentPrice * tekSell;
		       GameState.statistics.landSold(tekSell);
		       if (GameState.people.landNeeded()> GameState.land.now ) {
		            GameState.YOKE += Math.floor ((20 - GameState.science.policy)*(GameState.people.now*4f/(GameState.land.now))/3);
		            showText+=GameState.context.getResources().getString(R.string.sellLandDeficit);
		            //p '^Народ недоволен! Слишком высокая плотность населения для такого малого количества земли!';
		       }
		       
		       showDialog(showText);
		        			 		      
		       RoomView.nextRoom();
			 
		 }		 
	 }
	 
	 public void eatAction(long tekEat) {
		 String showText="";
		 showText+=GameState.context.getResources().getString(R.string.peopleEat);
		 showText=String.format(showText,tekEat);
		 		 
		 if ((tekEat < 50) && (GameState.corn.now >= 50)) {
			 showText=GameState.context.getResources().getString(R.string.peopleEatMinimal);
			 showDialog(showText);
			 return;		 
		 }
		 
		 GameState.corn.food = tekEat;
	     GameState.corn.now -=  tekEat;
	     GameState.people.hunger = (long) (GameState.people.now - Math.floor(GameState.corn.food/People.ONE_MAN_EAT));
	     if (GameState.people.hunger <= 0) {
	    	 GameState.people.hunger = 0;
	         GameState.YOKE = (int) (GameState.YOKE - 2 - Math.floor (GameState.science.policy/5));
	     } else {
	         GameState.people.now = GameState.people.now - GameState.people.hunger;
	         showText+=String.format(GameState.context.getResources().getString(R.string.peopleEatHunger),GameState.people.hunger);
	         
	         int tmp = (int) Math.floor (GameState.people.hunger/2.5 - GameState.science.policy/4);
	         if (tmp > 20) {
	             tmp = 20;
	         }
	         GameState.YOKE = GameState.YOKE + tmp;
	     }		 
		 
		 showDialog(showText);	
		 RoomView.nextRoom();
	 }
	 
	 public void sowAction(long tekSow) {
		 String showText="";
		 
		 
	     if (tekSow > GameState.corn.now) {
	    	 showText=GameState.context.getResources().getString(R.string.sowNoCorn);            
	    	 showDialog(showText);
            return;
	     }
	     
        if (tekSow > GameState.land.now) {
        showText=GameState.context.getResources().getString(R.string.sowNoLand);
        showDialog(showText);
            return;
        }
        
        if (tekSow > (GameState.people.now/1.2*4)) {
        	showText=GameState.context.getResources().getString(R.string.sowNoPeople);
        	showDialog(showText);
            return;
        }
        
        GameState.corn.seed = (long) Math.floor (tekSow * GameState.land.cornToSeedAkr);
        GameState.corn.now = GameState.corn.now - GameState.corn.seed;
        GameState.corn.harvest = GameState.corn.seed * GameState.corn.yield;
        GameState.statistics.harvest(GameState.corn.harvest);
        showText+=String.format(GameState.context.getResources().getString(R.string.sowText),tekSow,GameState.corn.seed);
		 
		showDialog(showText);	
		RoomView.nextRoom();
	 }
	 
	 public void scienceAction(long tekScience) {
		 
		 			 	
	        GameState.corn.science = tekScience;
	        GameState.corn.now -= GameState.corn.science;
	        GameState.statistics.scienceSpend(GameState.corn.science);
	        int tmp = GameState.getRandom(10);
	        GameState.science.integrity = (int) (GameState.science.integrity + Math.floor(GameState.corn.science/(20+GameState.science.count/2)) + tmp - 5);
	        if (GameState.science.integrity<0) {
	        	GameState.science.integrity = 1;
	        }
	        String showText=String.format(GameState.context.getResources().getString(R.string.scienceText),tekScience);
			showDialog(showText);	
			RoomView.nextRoom();
		 
	 }
	 
	 public void buildShipsAction(long tekBuild) {
		 	String showText="";
	    	     
	        if (tekBuild*GameState.ships.shipPrice > GameState.corn.now) {
	        	showText=GameState.context.getResources().getString(R.string.scienceText);
	        	showDialog(showText);
	            return;
	        }
	        
	        GameState.ships.build = tekBuild;
	        GameState.ships.now +=  GameState.ships.build;
	        GameState.corn.ships = tekBuild*GameState.ships.shipPrice;
	        GameState.corn.now = GameState.corn.now - GameState.corn.ships;
	        showText=String.format(GameState.context.getResources().getString(R.string.shipsBuild),tekBuild);
	        showDialog(showText);
	        RoomView.nextRoom();
	 }
	 
	 public void portShipsAction(long tekPort) {
		 
		 String showText;
	      if (tekPort > GameState.people.now) {
	    	showText=GameState.context.getResources().getString(R.string.noPeople);         
          	showDialog(showText);
          	return;
	      }
	      if (tekPort > GameState.people.maxMilitary) {
	    	  showText=GameState.context.getResources().getString(R.string.noMilitaryPeople);
	    	  showDialog(showText);
	          return;
	      }
	      if (tekPort > GameState.people.maxShipLoad) {
	      showText=GameState.context.getResources().getString(R.string.portNoShipForMilitary);
	      	showDialog(showText);
	          return;
	      }
	      GameState.people.port = -tekPort;
	      GameState.people.now = GameState.people.now + GameState.people.port;
	      GameState.people.sail = GameState.people.sail - GameState.people.port;
	
	      GameState.ships.port = (long) ((-1) * Math.floor (tekPort/GameState.people.maxShipLoad));
	      GameState.ships.now = GameState.ships.now + GameState.ships.port;
	      GameState.ships.sail = GameState.ships.sail - GameState.ships.port;
		 
	      showText=String.format(GameState.context.getResources().getString(R.string.portPeopleShips),tekPort,GameState.ships.port);
	      
	      showDialog(showText);
	      RoomView.nextRoom();
	 }
	 
	 public void dealAction(long tekDeal) {
		 
	    if (tekDeal < 0) {
	    	tekDeal = 0;
	    }
	    
	    String showText;
	    
	    if (0==tekDeal) {
	    	showText=GameState.context.getResources().getString(R.string.asYouWish);
	    	showDialog(showText);
	    	RoomView.nextRoom();
	    	return;
	    }
	    
	  
	    
        if (tekDeal > GameState.corn.now) {
	    	showText=GameState.context.getResources().getString(R.string.noCorn);         
          	showDialog(showText);
          	return;
        }
              
        
        if ( ((tekDeal < 5000) || (tekDeal > 100000)) ) {
	    	showText=GameState.context.getResources().getString(R.string.breakDeal);         
          	showDialog(showText);
          	RoomView.nextRoom();
          	return;
        }
        
        if (tekDeal > 0) {
            GameState.corn.deal = -tekDeal;
            GameState.corn.now = GameState.corn.now + GameState.corn.deal;
            GameState.DEAL = 5;
            GameState.DealMoney = -tekDeal;
            showText=String.format(GameState.context.getResources().getString(R.string.dealConcluded),tekDeal);
         	showDialog(showText);
          	
        }
        RoomView.nextRoom();
	 }
	 
	 public void payTribute(long tmp) {
		 //обратный порядок
		 long v = GameState.stackVar.pop();
		 long qq = GameState.stackVar.pop();
         long pp =  GameState.stackVar.pop(); 
         	 
		 GameState.people.war = -pp;
         GameState.corn.war   = -qq;
         GameState.land.war   =  -v;
         GameState.people.now = GameState.people.now - GameState.people.war;
         GameState.corn.now = GameState.corn.now - GameState.corn.war;
         GameState.land.now = GameState.land.now - GameState.land.war;
         GameState.statistics.igoPeople(GameState.people.war);
         GameState.statistics.igoGrain(GameState.corn.war);
         GameState.statistics.igoLand(GameState.land.war);
         String showText = GameState.context.getResources().getString(R.string.payTribute);
         showDialog(showText);
         RoomView.nextRoom();         
	 }
	 
	 public void notPayIgo(long tmp) {
		 GameState.epidemy.igo = 0;
         GameState.IGO = true;
         String showText = GameState.context.getResources().getString(R.string.notPayIgo);
         showDialog(showText);
         RoomView.nextRoom();  
	 }
	 
	 
	 public void showDialog(String showText) {
		 AlertDialog.Builder alertadd = new AlertDialog.Builder(GameState.context);
		 alertadd.setMessage(showText);
		 alertadd.setPositiveButton("Ok", null);
		 alertadd.show();		 
	 }
	 
	 
	 
	 
	 public void nextRoom(long tmp) {		 
		 RoomView.nextRoom();		 
	 }

}
