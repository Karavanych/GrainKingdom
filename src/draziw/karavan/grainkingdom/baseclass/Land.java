package draziw.karavan.grainkingdom.baseclass;


public class Land {
	
/*    --����� � �����
    p ('^^����� �� ������ ����: ', Land.total, ' �����.');
    p ('^�������/�������: ', Land.corn, ' �����.');
    p ('^� ���������� �����: ', Land.war, ' �����.');
    p ('^� ���������� ���������: ', Land.thiefs, ' �����.');
    p ('^���������: ', Land.spoiled, ' �����.');
    p ('^������������: ', Land.bonus, ' �����.');
    p ('^����� �����: ', Land.now, ' �����.');*/
    
	public int total=0;
	public int corn=0;
	public int war=0;
	public int thiefs=0;
	public int spoiled=0;
	public int bonus=0;
	public int now=0;
	public int currentPrice=22;
	
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
