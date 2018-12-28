package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Population {
	
	//Définir T
	/**
	 * Constructeur d'un nouvel individu
	 */
	private Function<T,MyIndividu> constructeur_indiv = null;
	
	/**
	 * Liste d'individus qui forme la population
	 */
	private List<MyIndividu> population = null;
	
	/**
	 * Taille souhaitée de la population
	 */
	private int taillePop = 0;
	
	/**
	 * Constructeur de la classe
	 * Initialise une population
	 * @param taille
	 */
	public Population(int taille) {
		this.taillePop = taille;
		
		population = new ArrayList<MyIndividu>();
		
		for(int i = 0; i < taillePop; i++) {
			population.add(constructeur_indiv.apply());
		}
	}

	/**
	 * Evalue la fitness de chaque individus
	 */
	public void Evaluate() {
		
	}
	
	// A revoir !!!!!!!!!!!
	/**
	 * Recree une population selon la méthode de sélection choisie
	 * par l'utilisateur
	 * @return
	 */
	public List<MyIndividu> Selection() {
		
		return null;
	}
	
	public List<MyIndividu> CrossOver() {
		
		return null;
	}
	
	public void setConstructeur(Function<T,MyIndividu> fct) {
		this.constructeur_indiv = fct;
	}
}
