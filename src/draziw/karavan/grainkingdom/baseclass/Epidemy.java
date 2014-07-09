package draziw.karavan.grainkingdom.baseclass;

public class Epidemy {
	
 /*   --Эпидемии
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
    end*/
		
	public int bugs=0;
	public int radiation=0;
	public int plague=0;
	public int igo=0;
	
	public boolean isEpidemy() {
		if (bugs>0 || radiation>0 || plague>0 || igo>0) {
			return true;			
		}
		return false;		
	}

	public void newYear() {
	    if (plague > 0) {plague = plague - 1;}    			
	}
			
	
	

}
