package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe deffinisant la metode de selection de type loterie
 * @version 1.0
 * @since 1.0
 * @param <T>
 * @see SelectionMethode
 */
public class LoterieStrategy<T> extends SelectionMethode<T> {
	
	/**
	 * Pourcentage souhaitee de parents selectione
	 */
	private int pourcentage = 0;
 
	/**
	 * Constructeur de la classe
	 * Initialise une LoterieStrategy
	 * @param Pourcentage souhaitee de parents selectione
	 */
	public LoterieStrategy(int pct)
	{
		setPourcentage(pct);
	}
	
	/// Methodes
	
	/**
	 * Selectionne un pourcentage de parents aléatoirement dans la population
	 * @param Population
	 * @see Population
	 */
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

	/// Getter et Setter
	
	/**
	 * Getter de l'attribut pourcentage
	 * @return pourcentage
	 */
	public int getPourcentage() {
		return pourcentage;
	}

	/**
	 * Setter de l'attribut pourcentage
	 * @param pourcentage
	 */
	public void setPourcentage(int pct) {
		this.pourcentage = pct;
	}

}
