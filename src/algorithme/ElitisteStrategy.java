package algorithme;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe definissant la methode de selection de type elitiste
 * @version 1.0
 * @since 1.0
 * @param <T>
 * @see SelectionMethode
 */
public class ElitisteStrategy<T extends Comparable<T>> extends SelectionMethode<T>{

	/**
	 * Nombre d'individu souhaitee
	 */
	private int nb_individu = 0;
	
	/**
	 * Conservation ou non du meilleur individu
	 */
	private boolean keep_best;
 
	
	/**
	 * Constructeur de la classe
	 * Initialise une ElitisteStrategy
	 * @param Pourcentage souhaite de parents selectionnes
	 */
	public ElitisteStrategy(int nbIndividu, boolean keepbest)
	{
		setNb_individu(nbIndividu);
		setKeep_best(keepbest);	// TODO Il faut definir si le poucentage est sous forme de 0.3 ou 30 par exemple
	}
	
	/// Methodes
	
	/**
	 * Selectionne un pourcentage de parents avec le meilleur score dans la population
	 * @param Population
	 * @see Population
	 */
	@Override
	public List<Individu<T>> methodeSelection(Population<T> p) { 
		List<Individu<T>> list_select = new ArrayList<>();
		p.sortPopulation();
		int taille_liste;
		if(keep_best) {
			taille_liste = nb_individu-1;
			list_select.add(p.getBest());
		}
		else {
			taille_liste = nb_individu;
		}
		for(int index = 0; index <= taille_liste; index++) {
			
			list_select.add(p.getPopulation().get(index));
		}
		return list_select;
	}
	
	/// Getter et Setter
	
		/**
		 * @return the nb_individu
		 */
		public int getNb_individu() {
			return nb_individu;
		}

		/**
		 * @param nb_individu the nb_individu to set
		 */
		public void setNb_individu(int nb_individu) {
			this.nb_individu = nb_individu;
		}
		
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
