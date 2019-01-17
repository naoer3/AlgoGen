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
		
	/// Attributs
	
	/**
	 * Constructeur d'un nouvel individu
	 */
	private Supplier<Individu<T>> constructeur_indiv = null;
	
	/**
	 * Liste d'individus qui forme la population
	 */
	private List<Individu<T>> population = null;
	
	/**
	 * Taille souhaitee de la population
	 */
	private int taillePop = 0;
	
	/**
	 * Numero de la generation en cours
	 */
	private int current_generation = 0;
	
	/**
	 * Meilleur individu de la population
	 */
	private Individu<T> bestIndividu = new Individu<>();
	
	//Constructeur
	
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
		
		//System.out.println(toString());

	}
	
	// TODO à voir si utile
	public Population() {
		
	}

	/// Methodes
	
	/*/**
	 * Evalue la fitness de toute la population
	 * @see Evaluate
	 */
	/*public void EvaluatePopulation() {
		for(Individu<T> individu : this.population) {
			this.Evaluate(individu);
		}
	}*/
	
	/**
	 * Evalue la fitness d'un individu donne
	 * @param individu
	 */
	/*public void Evaluate(Individu<T> individu) {
		individu.setFitness(fct_eval.apply(individu));
	}*/	
	
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
	
	// TODO : commentaire, que fait le comparator
	public void sortPopulation() {
		Collections.sort(population, new ComparatorIndividu<T>());
		bestIndividu = this.population.get(0);
	}
	
	/**
	 * Retourne l'individu avec la meilleure fitness
	 * @return le meilleur individu de la population
	 */
	public Individu<T> getBest() {
		return bestIndividu;
	}
	
	/// Getter et Setter
	
	/**
	 * Getter de l'attribut population qui definit tous les individus de notre population
	 * @return population
	 */
	public List<Individu<T>> getPopulation() {
		return population;
	}
	
	/**
	 * Setter de l'attribut population
	 * @param new_population
	 */
	public void setPopulation(List<Individu<T>> new_population) {
		this.population = new_population;
	}
	
	/**
	 * Getter de la taille de la population actuelle
	 * @return population.size()
	 */
	public int getNbIndividu() {
		return population.size();
	}
	
	/**
	 * Getter de l'attribut taillePop qui decrit la taille de la population souhaitee
	 * @return taillePop
	 */
	public int getTaillePop() {
		return taillePop;
	}
	
	/**
	 * Getter de l'attribut current_generation qui decrit le numero de generation en cours
	 * @return current_generation
	 */
	public int getCurrent_generation() {
		return current_generation;
	}
	
	// TODO
	@Override
	public String toString() {
		String str="";

		for(int i=0;i<taillePop;i++) {
			str+=population.get(i).toString()+"\n";
		}
		return str;		
	}
}
