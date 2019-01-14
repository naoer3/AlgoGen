package algorithme;

import java.util.List;

public class TournoiStrategy<T extends Comparable<T>> extends SelectionMethode<T> {
		
	private int myPourcentage = 100;
	
	public TournoiStrategy(int Pourcentage)
	{
		setMyPourcentage(Pourcentage);
	}
	
	@Override
	public List<Individu<T>> methodeSelection(Population<T> p) { 
		double produit=(myPourcentage*p.getPopulation().size());
		int firstIndex=(int)Math.round(produit/100)+1;
		int lastIndex = p.getPopulation().size();
		List<Individu<T>> selection = p.getPopulation();
		
		for(int index=firstIndex ; index<=lastIndex;index++) {
			selection.remove(p.getPopulation().size()-1);
		}		
		return selection;
	}
	
	public int getMyPourcentage() {
		return myPourcentage;
	}

	public void setMyPourcentage(int myPourcentage) {
		this.myPourcentage = myPourcentage;
	}

}