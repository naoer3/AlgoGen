package algorithme;

import java.util.Collections;


public class LoterieStrategy extends SelectionMethode {
	
	private int myPourcentage = 100;
 
	public LoterieStrategy(int Pourcentage)
	{
		setMyPourcentage(Pourcentage);
	}
	
	@Override
	public Population methodeSelection(Population p){
		Collections.shuffle(p.getMyPopulation()); 
		
		double produit=(myPourcentage*p.getMyPopulation().size());
		int firstIndex=(int)Math.round(produit/100)+1;
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
