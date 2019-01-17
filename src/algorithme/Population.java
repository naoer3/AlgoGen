package algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Classe representant un ensemble d'individus qui constitue une population
 * @version 1.0
 * @since 1.0
 * @param <T>
 * @see Individu
 */
public class Population<T extends Comparable<T>>{
	
	/**
	 * Declaration des variables globales
	 * constructeur_indiv: Fonction permettant de creer des individus
	 * fct_eval: Fonction permettant d'evaluer des individus
	 * population: Population mere
	 * taillePop: Taille de cette population mere
	 * current_generation: Generation actuelle
	 */
	private Supplier<Individu<T>> constructeur_indiv = null;
	private Function<Individu<T>,T> fct_eval = null;
	private List<Individu<T>> population = null;
	private int taillePop = 0;
	private int current_generation = 0;

	/**
	 * Constructeur de la classe
	 * Initialise une population
	 * @param taille Taille souhaitee de la population
	 * @param fct Fonction creant de nouveaux individus
	 * @param eval Fonction d'evaluation de l'algorithme
	 */
	public Population(int taille, Supplier<Individu<T>> fct, Function<Individu<T>,T> eval) {
		this.taillePop = taille;
		this.constructeur_indiv = fct;
		this.fct_eval = eval;
		
		this.population = new ArrayList<>();
		
		//Creation d'une nouvelle population
		for(int i = 0; i < taillePop; i++) {
			this.population.add(constructeur_indiv.get());
		}
		
		//System.out.println(toString());

	}

	/**
	 * Methode EvaluatePopulation: Evalue le score de toute la population
	 */
	public void EvaluatePopulation() {
		for(Individu<T> individu : this.population) {
			this.Evaluate(individu);
		}
	}
	
	/**
	 * Methode Evaluate: Evalue le score d'un individu donne
	 * @param individu
	 */
	public void Evaluate(Individu<T> individu) {
		individu.setFitness(fct_eval.apply(individu));
	}	
	
	/**
	 * Methode AjutIndividu: Ajoute une liste d'individus a la population existante
	 * @param liste_individus
	 */
	public void AjoutIndividus(List<Individu<T>> liste_individus) {
		population.addAll(liste_individus);
	}
	
	/**
	 * Methode NewGeneration: Incremente le numero de la generation courrante
	 */
	public void NewGeneration() {
		current_generation++;
	}
	
	/***
	 * Methode sortPopulation: Reorganise la population selon le score des individus
	 */
	public void sortPopulation() {
		Collections.sort(population, new ComparatorIndividu<T>());
	}
	
	/***
	 * Getter de la variable fitness
	 * @return le meilleur individu de la population
	 */
	public Individu<T> getBest() {
		return this.population.get(0);
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
	
	/***
	 * Methode toString
	 */
	@Override
	public String toString() {
		String str="";

		for(int i=0;i<taillePop;i++) {
			str+=population.get(i).toString()+"\n";
		}
		return str;		
	}
}
