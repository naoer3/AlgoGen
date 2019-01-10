package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Classe representant un ensemble d'individus qui constitue une population
 * @param <T>
 * @see Individu
 */
public class Population<T> {
	
	/**
	 * Constructeur d'un nouvel individu
	 */
	private Supplier<Individu> constructeur_indiv = null;

	
	/**
	 * Liste d'individus qui forme la population
	 */
	private List<Individu> population = null;
	
	/**
	 * Taille souhaitee de la population
	 */
	private int taillePop = 0;
	
	/**
	 * Constructeur de la classe
	 * Initialise une population
	 * @param taille Taille souhaitee de la population
	 * @param fct Fonction creant de nouveaux individus
	 * @param param Parametres a  donner au constructeur d'individu
	 * @param eval Fonction d'evaluation de l'algorithme
	 */
	public Population(int taille, Supplier<Individu> fct) {
		this.taillePop = taille;
		this.constructeur_indiv = fct;
		
		population = new ArrayList<Individu>();
		
		for(int i = 0; i < taillePop; i++) {
			population.add(constructeur_indiv.get());
		}
	}


    public int getNbIndividu() {
		return population.size();
	}
	
	public List<Individu> getPopulation() {
		return population;
	}
	
	public Individu CrossOver(Individu parent1, Individu parent2) {
		
		
		return null;
		
	}
	
	public void Croisement (List<Individu> individus) {
		
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

