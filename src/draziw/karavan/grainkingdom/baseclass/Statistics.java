package draziw.karavan.grainkingdom.baseclass;

public class Statistics {
	public long starvedToDeath=0;
	public long peopleBorn=0;
	public long landPurchased=0;
	public long landSold=0;
	public long harvest=0;
	public long scienceSpend=0;
	public long thiefGrain=0;
	public long thiefLand=0;
	public long rats=0;
	public long hardRain=0;
	public long disease=0;
	public long locust=0;
	public long plague=0;
	public long meteorites=0;
	public long landSpoiled=0;
	public long grainSpoiled=0;
	public long peopleLeft=0;
	public long dealOff=0;
	public long nomads=0;
	public long artefacts=0;
	public long artificialGrain=0;
	public long landFertalization=0;
	public long locustRemove=0;
	public long radiationOff=0;
	public long plagueOff=0;
	public long dealNumber=0;
	public long igoPeople=0;
	public long igoGrain=0;
	public long igoLand=0;
	
	public void starvedToDeath(long std) {
		starvedToDeath+=std;		
	}
	public void peopleBorn(long born) {
		
		peopleBorn+=born;		
	}
	
	public void landPurchased(long land){
		landPurchased+=land;		
	}
	
	public void landSold(long land) {
		landSold+=land;		
	}
	public void harvest(long harv) {
		
		harvest=harvest+harv;
		
	}
	public void scienceSpend(long corn) {
		scienceSpend+=corn;		
	}
	public void thiefGrain(long corn) {
		thiefGrain+=corn;	
	}
	public void thiefLand(long land) {
		thiefLand+=land;		
	}
	public void rats(long corn) {
		rats+=corn;
		
	}
	public void hardRain(long corn) {
		hardRain+=corn;		
	}
	
	public void disease(long people) {
		disease+=people;		
	}
	public void locust(int i) {
		locust+=i;		
	}
	public void plague(int i) {
		plague+=i;		
	}
	public void meteorites(int i) {
		meteorites+=i;
		
	}
	public void landSpoiled(long land) {
		landSpoiled+=land;		
	}
	public void grainSpoiled(long corn) {
		grainSpoiled+=corn;
		
	}
	public void peopleLeft(long balance) {
		peopleLeft+=balance;
		
	}
	public void dealOff(long corn) {
		dealOff+=corn;				
	}
	public void nomads(long balance) {
		nomads+=balance;
		
	}
	public void artefacts(long corn) {
		artefacts+=corn;				
	}
	public void artificialGrain(long bonus) {
		artificialGrain+=bonus;		
	}
	public void landFertalization(long bonus) {
		landFertalization+=bonus;		
	}
	public void locustRemove(int i) {
		locustRemove+=i;
		
	}
	public void radiationOff(int i) {
		radiationOff+=i;		
		
	}
	
	public void plagueOff(int i) {
		plagueOff+=i;		
	}
	public void dealNumber(int i) {
		dealNumber+=i;
		
	}
	public void igoPeople(long people) {
		igoPeople+=people;		
	}
	public void igoGrain(long grain) {
		igoGrain+=grain;
		
	}
	public void igoLand(long land) {
		igoLand+=land;		
	}

}
