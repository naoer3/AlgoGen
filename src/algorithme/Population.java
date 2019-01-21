package algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Classe representant un ensemble d'individus qui constitue une population
 * @param <T>
 * @see Individu
 */
public class Population<T extends Comparable<T>>{
	
	/**
	 * Fonction permettant de creer des individus
	 */
	private Supplier<Individu<T>> constructeur_indiv = null;
	
	/**
	 * Population qu'on evalue
	 */
	private List<Individu<T>> population = null;
	
	/**
	 * Taille de cette population
	 */
	private int taillePop = 0;
	
	/**
	 * Numero de la generation actuelle
	 */
	private int current_generation = 0;
	
	/**
	 * Individu avec la meilleure fitness
	 */
	private Individu<T> bestIndividu=null;

	/**
	 * Constructeur de la classe
	 * Initialise une population
	 * @param taille Taille souhaitee de la population
	 * @param fct Fonction creant de nouveaux individus
	 * @param eval Fonction d'evaluation de l'algorithme
	 */
	public Population(int taille, Supplier<Individu<T>> fct) {
		this.taillePop = taille;
		this.constructeur_indiv = fct;
		
		this.population = new ArrayList<>();
		
		//Creation d'une nouvelle population
		for(int i = 0; i < taillePop; i++) {
			this.population.add(constructeur_indiv.get());
		}
	}

	/**
	 * Ajoute une liste d'individus a la population existante
	 * @param liste_individus
	 */
	public void AjoutIndividus(List<Individu<T>> liste_individus) {
		population.addAll(liste_individus);
	}
	
	/**
	 * Incremente le numero de la generation courrante
	 */
	public void NewGeneration() {
		current_generation++;
	}
	
	/**
	 * Trie les individus selon leur fitness
	 */
	public void sortPopulation() {
		Collections.sort(population, new ComparatorIndividu<T>());
		bestIndividu = this.population.get(0);
	}
	
	/***
	 * Getter de la variable fitness
	 * @return le meilleur individu de la population
	 */
	public Individu<T> getBest() {
		return bestIndividu;
	}
	
	/***
	 * Getter de la variable population
	 * @return tous les individus de la population
	 */
	public List<Individu<T>> getPopulation() {
		return population;
	}
	
	/***
	 * Setter sur les individus de la population
	 * @param new_population
	 */
	public void setPopulation(List<Individu<T>> new_population) {
		this.population = new_population;
	}
	
	/***
	 * Getter de la taille de la population
	 * @return la taille de la population actuelle
	 */
	public int getNbIndividu() {
		return population.size();
	}
	
	/***
	 * Getter de la variable taillePop
	 * @return la taille de la population souhaitee
	 */
	public int getTaillePop() {
		return taillePop;
	}
	
	/***
	 * Getter de la variable current_generation
	 * @return le numero de generation en cours
	 */
	public int getCurrent_generation() {
		return current_generation;
	}
}
