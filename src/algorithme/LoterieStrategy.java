package algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LoterieStrategy<T> extends SelectionMethode<T> {
	
	private int pourcentage = 100;
 
	public LoterieStrategy(int pct)
	{
		setMyPourcentage(pct);
	}
	
	@Override
	public List<Individu<T>> methodeSelection(Population<T> p) { 
		List<Individu<T>> list_select = new ArrayList<>();
		
		int taille_population =p.getPopulation().size();
		int taille_liste=taille_population*pourcentage;
		
		taille_liste=(int)Math.round(taille_liste/100)+1;
		
		System.out.println(taille_liste);

		for(int index=0 ; index<=taille_liste;index++) {

			list_select.add((Individu<T>)p.getPopulation().get(index));
		}
		return list_select;
	}


	public int getMyPourcentage() {
		return pourcentage;
	}

	public void setMyPourcentage(int myPourcentage) {
		this.pourcentage = myPourcentage;
	}

}
