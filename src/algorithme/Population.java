package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Classe repr�sentant un ensemble d'individus qui constitue une population
 * @author mathp
 * @version 1.0
 * @since 1.0
 * @param <T>
 * @see Individu
 */
public class Population<T> {
	
	/**
	 * Constructeur d'un nouvel individu
	 */
	private Function<T,Individu> constructeur_indiv = null;
	
	/**
	 * Fonction � �valuer dans l'algorithme
	 */
	private Function<Individu,T> fct_eval = null;
	
	/**
	 * Liste d'individus qui forme la population
	 */
	private List<Individu> population = null;
	
	/**
	 * Taille souhait�e de la population
	 */
	private int taillePop = 0;
	
	/**
	 * Constructeur de la classe
	 * Initialise une population
	 * @param taille Taille souhait�e de la population
	 * @param fct Fonction cr�ant de nouveaux individus
	 * @param param Param�tres � donner au constructeur d'individu
	 * @param eval Fonction d'�valuation de l'algorithme
	 */
	public Population(int taille, Function<T,Individu> fct, T param, Function<Individu,T> eval) {
		this.taillePop = taille;
		this.constructeur_indiv = fct;
		this.fct_eval = eval;
		
		population = new ArrayList<Individu>();
		
		for(int i = 0; i < taillePop; i++) {
			population.add(constructeur_indiv.apply(param));
		}
	}

	/**
	 * Evalue la fitness de chaque individu avec la fonction d'�valuation
	 */
	public void Evaluate() {
		for(Individu indiv : population) {
			indiv.setFitness(fct_eval.apply(indiv));
		}
	}
}
