package algorithme;

import java.util.Collections;


public class LoterieStrategy extends EvalMethode {
	
	private int myPourcentage = 100;
 
	public LoterieStrategy(int Pourcentage)
	{
		setMyPourcentage(Pourcentage);
	}
	

	@Override
	public Population methodeEvaluation(Population p) {
		Collections.shuffle(p.getMyPopulation()); 
		double firstIndex2=(myPourcentage*p.getMyPopulation().size());
		int firstIndex=(int)Math.round(firstIndex2/100)+1;
		int lastIndex = p.getMyPopulation().size();
		
		for(int index=firstIndex ; index<=lastIndex;index++) {
			p.getMyPopulation().remove(p.getMyPopulation().size()-1);
		}
		
		return p;
	}


	public int getMyPourcentage() {
		return myPourcentage;
	}

	public void setMyPourcentage(int myPourcentage) {
		this.myPourcentage = myPourcentage;
	}


	

}
