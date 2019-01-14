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
	 * Pourcentage souhaitee de parents selectione
	 */
	private Double pourcentage = 0.0;
	
	/**
	 * Pourcentage souhaitee de parents selectione
	 */
	private int taille_tournoi = 0;
 
	/**
	 * Constructeur de la classe
	 * Initialise une TournoiStrategy
	 * @param Pourcentage souhaitee de parents selectione
	 */
	public TournoiStrategy(Double pct,int tailleTournoi)
	{
		setPourcentage(pct);
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
		int taille_liste = ((Double)(taille_population*pourcentage)).intValue();
		Random rand = new Random(); 
		
		//System.out.println(taille_liste);
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
	 * Getter de l'attribut pourcentage
	 * @return pourcentage
	 */
	public Double getPourcentage() {
		return pourcentage;
	}

	/**
	 * Setter de l'attribut pourcentage
	 * @param pourcentage
	 */
	public void setPourcentage(Double pct) {
		this.pourcentage = pct;
	}

	public int getTailleTournoi() {
		return taille_tournoi;
	}

	public void setTailleTournoi(int tailleTournoi) {
		this.taille_tournoi = tailleTournoi;
	}

}
