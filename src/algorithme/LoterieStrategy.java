package algorithme;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;


public class LoterieStrategy<T> extends SelectionMethode<T> {
	
	private int pourcentage = 100;
 
	public LoterieStrategy(int pct)
	{
		setPourcentage(pct);
	}
	
	@Override
	public List<Individu<T>> methodeSelection(Population<T> p) { 
		List<Individu<T>> list_select = new ArrayList<>();
		int nombreAleatoire =0;
		int taille_population =p.getPopulation().size();
		int taille_liste=taille_population*pourcentage;
		taille_liste=(int)Math.round(taille_liste/100)+1;
		Random rand = new Random(); 
		
		//System.out.println(taille_liste);
		for(int index=0 ; index<=taille_liste;index++) {
			nombreAleatoire = rand.nextInt(taille_population + 1);
			list_select.add((Individu<T>)p.getPopulation().get(nombreAleatoire));
		}
		return list_select;
	}


	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pct) {
		this.pourcentage = pct;
	}

}
