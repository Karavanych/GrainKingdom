package draziw.karavan.grainkingdom.baseclass;

import draziw.karavan.grainkingdom.GameState;

public class Science {
	
/*    --Отчёт об исследованиях
    if isScience then
        p ('^^Исследования:');
        p ('^Население: ', Science.people, ' уровень.');
        p ('^Земля: ', Science.land, ' уровень.');
        p ('^Зерно: ', Science.corn, ' уровень.');
        p ('^Корабли: ', Science.ships, ' уровень.');
        p ('^Военное дело: ', Science.war, ' уровень.');
        p ('^Рынок: ', Science.buy, ' уровень.');
        p ('^Политика: ', Science.policy, ' уровень.');
        p ('^Медицина: ', Science.medicine, ' уровень.');
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
