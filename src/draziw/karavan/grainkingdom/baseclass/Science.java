package draziw.karavan.grainkingdom.baseclass;

import draziw.karavan.grainkingdom.GameState;

public class Science {
	
/*    --����� �� �������������
    if isScience then
        p ('^^������������:');
        p ('^���������: ', Science.people, ' �������.');
        p ('^�����: ', Science.land, ' �������.');
        p ('^�����: ', Science.corn, ' �������.');
        p ('^�������: ', Science.ships, ' �������.');
        p ('^������� ����: ', Science.war, ' �������.');
        p ('^�����: ', Science.buy, ' �������.');
        p ('^��������: ', Science.policy, ' �������.');
        p ('^��������: ', Science.medicine, ' �������.');
    end*/
	
	public boolean isScience=false;
	public int integrity=0;
	public int count=0;
	
	public int people=0;
	public int land=0;
	public int corn=0;
	public int ships=0;
	public int war=0;
	public int buy=0;
	public int policy=0;
	public int medicine=0;
	
	public Science() {
		// TODO Auto-generated constructor stub
	}
	
	public String fillReportVars(String ss) {
		return String.format(ss,people,land,corn,ships,war,buy,policy,medicine);
	}

	public void newYear() {		
		 integrity = integrity - GameState.getRandom(25);		
	}

}
