package algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Classe permettant d'appliquer la methode de selection "Tournoi" sur une population
 * @version 1.0
 * @since 1.0
 * @param <T>
 * @see SelectionMethode
 */
public class TournoiStrategy<T extends Comparable<T>> extends SelectionMethode<T> {

	/***
	 * Declaration des variables globales
	 * nb_individu: Nombre d'individus à selectionner
	 * taille_tournoi: Taille du tournoi permettant de selectionner les individus
	 */
	private int nb_individu = 0;
	private int taille_tournoi = 0;
 
	/**
	 * Constructeur de la classe TournoiStrategy
	 * @param1: nbIndividu
	 * @param2: tailleTournoi 
	 */
	public TournoiStrategy(int nbIndividu,int tailleTournoi)
	{
		setNb_individu(nbIndividu);
		setTailleTournoi(tailleTournoi);
	}
	
	/**
	 * methodeSelection: Permet de retourner une liste d'individus selectionnes selon l'application
	 * d'un tournoi sur ces derniers. Suivant la taille predefinie, les individus sont compares et les meilleurs sont selectionnes
	 * @param Population
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
	
	/***
	 * Getter de la variable nb_individu
	 * @return le nombre d'individus selectionnes
	 */
	public int getNb_individu() {
		return nb_individu;
	}
	
	/***
	 * Getter de la variable taille_tournoi
	 * @return la taille du tournoi
	 */
	public int getTailleTournoi() {
		return taille_tournoi;
	}

	/***
	 * Setter sur la taille du tournoi
	 * @param tailleTournoi
	 */
	public void setTailleTournoi(int tailleTournoi) {
		this.taille_tournoi = tailleTournoi;
	}

}
