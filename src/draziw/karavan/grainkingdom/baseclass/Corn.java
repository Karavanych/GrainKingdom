package draziw.karavan.grainkingdom.baseclass;

import android.app.Activity;
import android.text.style.ForegroundColorSpan;
import draziw.karavan.grainkingdom.GameState;
import draziw.karavan.grainkingdom.R;
import draziw.karavan.grainkingdom.supportclass.mySpannableString;

public class Corn {
	
   /* --Отчёт о зерне
    p ('^^Зерно на начало года: ', Corn.total, ' бушелей.');
    p ('^Пошло на семена: ', Corn.seed, ' бушелей.');
    p ('^Урожай: ', Corn.harvest, ' бушелей.');
    if (Corn.land >= 0) then
        p ('^Выручено при продаже земли: ', Corn.land, ' бушелей.');
    else
        p ('^Уплачено при покупке земли: ', Corn.land, ' бушелей.');
    end
    p ('^На постройку кораблей: ', Corn.ships, ' бушелей.');
    p ('^Пошло на еду: ', Corn.food, ' бушелей.');
    p ('^В результате войны: ', Corn.war, ' бушелей.');
    p ('^В результате воровства: ', Corn.thiefs, ' бушелей.');
    p ('^Съедено крысами: ', Corn.rats, ' бушелей.');
    p ('^В уплату наёмникам: ', Corn.fee, ' бушелей.');
    p ('^В уплату учёным: ', Corn.science, ' бушелей.');
    p ('^Испорчено: ', Corn.spoiled, ' бушелей.');
    p ('^Приобретено: ', Corn.bonus, ' бушелей.');
    p ('^Расход/прибыль со сделки: ', Corn.deal, ' бушелей.');
    p ('^Планируемая урожайность: ', Corn.yield, ' бушель/акр.');
    p ('^Итого зерна: ', Corn.now, ' бушелей.');*/
	
	public long total=0;
	public long seed=0;
	public long harvest=0;
	public long land=0;
	public long ships=0;
	public long food=0;
	public long war=0;
	public long thiefs=0;
	public long rats=0;
	public long fee=0;
	public long science=0;
	public long spoiled=0;
	public long bonus=0;
	public long deal=0;
	public long yield=0;
	public long now=0;
	
	public Corn() {

	}
	
/*	public mySpannableString reports(Activity cc) {
		String recstr = cc.getResources().getString(R.string.cornreports);
		recstr=String.format(recstr,total,seed,harvest,land>0?land:0,land<0?-land:0,ships,food,war,thiefs,rats,fee,science,spoiled,bonus,deal,yield,now);
		
		mySpannableString msps=new mySpannableString(recstr);
		//msps.setSpanToLine(new ForegroundColorSpan(cc.getResources().getColor(R.color.titleTextColor)),2, 0, 0, 0);
		
		return msps;
		
	}*/
	
	public String fillReportVars(String ss) {
		return String.format(ss,total,seed,harvest,land>0?land:0,land<0?-land:0,ships,food,war,thiefs,rats,fee,science,spoiled,bonus,deal,yield,now);		
	}

	public void newYear() {
       
		/* -- Corn
		    if (Epidemy.bugs == 0) then
		        tmp = math.random (3);
		        Corn.yield = tmp + 2 + math.floor (Science.land/3);
		    else
		        Corn.yield = 0;
		    end
		    if not isSow then
		        Corn.yield = 0;
		    end
		    Corn.now = Corn.now + Corn.harvest;
		    Corn.now = Corn.now + Corn.bonus;
		    Corn.now = Corn.now - Corn.rats;
		    Corn.now = Corn.now - Corn.spoiled;
		    Corn.now = Corn.now - Corn.thiefs;*/
		
		if (0==GameState.epidemy.bugs) {			
			yield=(int) (GameState.getRandom(3)+2+Math.floor(GameState.science.land/3f));
		} else yield=0;
		
		if (!GameState.isSow) yield=0;
		
		// основной подсчет
		now = now + harvest;
		now = now + bonus;
		now = now - rats;
		now = now - spoiled;
		now = now - thiefs;
			
		
	}
	
	public void svernut() {				
		
		seed=0;
		harvest=0;
		land=0;
		ships=0;
		food=0;
		war=0;
		thiefs=0;
		rats=0;
		fee=0;
		science=0;
		spoiled=0;
		bonus=0;
		deal=0;
		total=now;	
		
	}
}
