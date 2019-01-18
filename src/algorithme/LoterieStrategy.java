package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe permettant de selectionner des individus au sein d'une population selon la methode "Loterie"
 * @version 1.0
 * @since 1.0
 * @param <T>
 * @see SelectionMethode
 */
public class LoterieStrategy<T extends Comparable<T>> extends SelectionMethode<T> {

	/***
	 * Declaration des variables globales
	 * nb_individu: Nombre d'individus selectionnes
	 * keep_best: Booleen permettant d'indiquer si l'on conserve le meilleur individu ou non
	 */
	private int nb_individu = 0;
	private boolean keep_best;

	/***
	 * Constructeur de la classe LoterieStrategy
	 * @param1 nbIndividu 
	 * @param2 keepbest
	 */
	public LoterieStrategy(int nbIndividu, boolean keepbest)
	{
		setNb_individu(nbIndividu);
		setKeep_best(keepbest);
	}
	

	/**
	 * methodeSelection: Permet de selectionner un pourcentage de parents aleatoirement dans la population
	 * @param Population
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

	/**
	 * Getter de la variable nb_individu
	 * @return le nombre d'individus selectionnes
	 */
	public int getNb_individu() {
		return nb_individu;
	}

	/**
	 * Setter sur le nombre d'individus selectionnes
	 * @param nb_individu
	 */
	public void setNb_individu(int nb_individu) {
		this.nb_individu = nb_individu;
	}
	
	/**
	 * Getter de la variable keep_best
	 * @return un booleen pour savoir si l'on conserve le meilleur individu ou non 
	 */
	public boolean isKeep_best() {
		return keep_best;
	}

	/**
	 * Setter sur le booleen permettant de conserver le meilleur individu ou non
	 * @param keep_best
	 */
	public void setKeep_best(boolean keep_best) {
		this.keep_best = keep_best;
	}

}
