package draziw.karavan.grainkingdom.baseclass;

public class Epidemy {
	
 /*   --��������
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
