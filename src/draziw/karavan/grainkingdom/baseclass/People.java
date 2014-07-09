package draziw.karavan.grainkingdom.baseclass;

import draziw.karavan.grainkingdom.GameState;


public class People {
	
	/*<string name="populationreports">
    \n��������� �� ������ ����: People.total �������.
    \n��������: People.born �������.
    \n������ �� ������: People.hunger �������.
    \n������ �� ��������: People.illness �������.
    \n������� � ����:  People.war �������.
    \n���������: People.suicide �������.
    \n� ���������� ���������: People.thiefs �������.
    \n� �����: People.port �������.
    \n� ��������: People.sail �������.
    \n��������: People.balance �������.
    \n����� ���������: People.now �������.</string>*/
	
	public int total=0;
	public int born=0;
	public int hunger=0;
	public int illness=0;
	public int war=0;
	public int suicide=0;
	public int thiefs=0;
	public int port=0;
	public int sail=0;
	public int balance=0;
	public int now=0;
		
	
	public People() {
		
	}
	
	/*public mySpannableString reports(Activity cc) {
		
		String recstr = cc.getResources().getString(R.string.populationreports);
		recstr=String.format(recstr,total,born,hunger,illness,war,suicide,thiefs,port,sail,balance,now);
		
		mySpannableString msps=new mySpannableString(recstr);
		//msps.setSpanToLine(new ForegroundColorSpan(cc.getResources().getColor(R.color.titleTextColor)),2, 0, 0, 0);
		
		return msps;
		
	}*/
	
	public String fillReportVars(String ss) {
		return String.format(ss,total,born,hunger,illness,war,suicide,thiefs,port,sail,balance,now);		
	}

	public void newYear() {
		
		    			
			if (0==GameState.epidemy.radiation) {
			    double tmp = Math.floor(GameState.getRandom(2));		        
		        born = (int) (born + Math.floor (now * (0.1 + GameState.science.people/50f)) + tmp - 2);
		        
		        tmp = GameState.getRandom(100);
		        if ((born<1) && (tmp>25)) {
		            born = 1;
		        }
		        
		        if (born < 0) {
		            born = 0;
		        }
		        
		    } else {
		        born = 0;
		    }
			
			GameState.statistics.peopleBorn(born);
		    
		    if (GameState.YOKE > 35) {
		        int tmp = GameState.getRandom (500);
		        suicide = (int) Math.floor (now * GameState.YOKE / (500 + tmp + GameState.science.people * 50));
		    }
		    
		    now = now + balance;
		    now = now + born;
		    now = now - illness;
		    now = now - thiefs;
		    now = now - suicide;

		    
		
		
	
	}
	
	public void svernut() {
        /*People.born = 0;
        People.hunger = 0;
        People.illness = 0;
        People.suicide = 0;
        People.war = 0;
        People.thiefs = 0;
        People.port = 0;
        People.balance = 0;
        People.total = People.now;*/
		
		GameState.statistics.starvedToDeath(hunger);		
		
		born=0;
		hunger=0;
		illness=0;
		suicide=0;
		war=0;
		port=0;
		balance=0;
		total=now;			
	}
	
	public int cornNeeded() {
		return now*40;
	}
	
	public int landNeeded() {
		return now*5;
	}
	
}


