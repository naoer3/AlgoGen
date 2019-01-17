package algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Classe deffinisant la metode de selection de type Tournoi
 * @version 1.0
 * @since 1.0
 * @param <T>
 * @see SelectionMethode
 */
public class TournoiStrategy<T extends Comparable<T>> extends SelectionMethode<T> {
	
	/**
	 * Nombre d'individu souhaitee
	 */
	private int nb_individu = 0;
	
	/**
	 * Taille du tournoi souhaitee
	 */
	private int taille_tournoi = 0;
 
	/**
	 * Constructeur de la classe
	 * Initialise une TournoiStrategy
	 * @param Pourcentage souhaitee de parents selectione
	 */
	public TournoiStrategy(int nbIndividu,int tailleTournoi)
	{
		setNb_individu(nbIndividu);
		setTailleTournoi(tailleTournoi);
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
		List<Individu<T>> list_tournoi = new ArrayList<>();
		int nombreAleatoire =0;
		int taille_population =p.getPopulation().size();
		int taille_liste = nb_individu;
		Random rand = new Random(); 
		
		for(int index=0 ; index<=taille_liste;index++) {
			for(int i=0 ; i<=taille_tournoi;i++) {
				nombreAleatoire = rand.nextInt(taille_population + 1);
				list_tournoi.add((Individu<T>)p.getPopulation().get(nombreAleatoire));
			}
			Collections.sort(list_tournoi, new ComparatorIndividu<T>());
			list_select.add((Individu<T>)list_tournoi.get(0));
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
	 * @param tailleTournoi the tailleTournoi to set
	 */
	public int getTailleTournoi() {
		return taille_tournoi;
	}

	/**
	 * @param tailleTournoi the tailleTournoi to set
	 */
	public void setTailleTournoi(int tailleTournoi) {
		this.taille_tournoi = tailleTournoi;
	}

}
