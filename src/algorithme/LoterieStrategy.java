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
public class LoterieStrategy<T extends Comparable<T>> extends SelectionMethode<T> {
	
	
	/**
	 * Conservation ou non du meilleur individu
	 */
	private boolean keep_best;
 
	

	/**
	 * Constructeur de la classe
	 * Initialise une LoterieStrategy
	 * @param Pourcentage souhaitee de parents selectione
	 */
	public LoterieStrategy(int nbIndividu, boolean keepbest)
	{
		setNb_individu(nbIndividu);
		setKeep_best(keepbest);
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
		int taille_liste;
		if(keep_best) {
			taille_liste = nb_individu-1;
			p.sortPopulation();
			list_select.add(p.getBest());
		}
		else {
			taille_liste = nb_individu;
		}
		
		int nombreAleatoire =0;
		int taille_population =p.getPopulation().size();
		
		Random rand = new Random(); 
		for(int index=0 ; index<=taille_liste;index++) {
			nombreAleatoire = rand.nextInt(taille_population + 1);
			list_select.add((Individu<T>)p.getPopulation().get(nombreAleatoire));
		}
		return list_select;
	}

	/// Getter et Setter
	
	
	/**
	 * @return the keep_best
	 */
	public boolean isKeep_best() {
		return keep_best;
	}

	/**
	 * @param keep_best the keep_best to set
	 */
	public void setKeep_best(boolean keep_best) {
		this.keep_best = keep_best;
	}

}
