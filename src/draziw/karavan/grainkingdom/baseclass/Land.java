package draziw.karavan.grainkingdom.baseclass;

import draziw.karavan.grainkingdom.GameState;


public class Land {
	
/*    --����� � �����
    p ('^^����� �� ������ ����: ', Land.total, ' �����.');
    p ('^�������/�������: ', Land.corn, ' �����.');
    p ('^� ���������� �����: ', Land.war, ' �����.');
    p ('^� ���������� ���������: ', Land.thiefs, ' �����.');
    p ('^���������: ', Land.spoiled, ' �����.');
    p ('^������������: ', Land.bonus, ' �����.');
    p ('^����� �����: ', Land.now, ' �����.');*/
    public static int LAND_MINIMAL=25;
	
	public long total=0;
	public long corn=0;
	public long war=0;
	public long thiefs=0;
	public long spoiled=0;
	public long bonus=0;
	public long now=0;
	
	public long currentPrice=22;	
	public long buyMin=0;
	public long buyMax=0;
	public long cornToSeedAkr=0;
	
	public Land() {
			
	}
	
	public String fillReportVars(String ss) {
		return String.format(ss,total,corn,war,thiefs,spoiled,bonus,now);		
	}

	public void newYear() {	
		  
		    now = now + bonus;
		    now = now - spoiled;
		    now = now - thiefs;
		
		
	
	}
	
	public void svernut() {
	     /*   Land.corn = 0;
        Land.war = 0;
        Land.thiefs = 0;
        Land.spoiled = 0;
        Land.bonus = 0;
        Land.total = Land.now;*/
		corn=0;
		war=0;
		thiefs=0;
		spoiled=0;
		bonus=0;
		total=now;			
	}
	
	
		
}
