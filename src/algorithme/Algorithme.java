package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/***
 * Classe contenant l'ensemble de l'algorithme
 *
 */
public class Algorithme<T extends Comparable<T>> {

	/***
	 * Creation des variables
	 * population: Liste des individus
	 * methode: methode de selection des individus parents
	 * taille_pop: Taille de la population utilisee au sein de l'algorithme
	 * mutation: Pourcentage d'elements mutes
	 * croisement: Pourcentage d'elements croises
	 * type_selection: Definition du type de selection des individus (elitiste, tournoi, loterie)
	 * fct_crea_individu: Fonction permettant la creation d'individus au sein de la population 
	 */
	private List<Individu<T>> liste_selection = new ArrayList<>();
	private List<Individu<T>> liste_croisement = new ArrayList<>();
	private List<Individu<T>> liste_mutation = new ArrayList<>();
	private Population<T> population;

	private SelectionMethode<T> selection_parent;
	private SelectionMethode<T> selection_population;
	private Croisement<T> croisement;
	private Mutation<T> mutation;
	private CritereArret<T> critere_arret;

	private int taille_pop;

	private int type_selection_parent;	//0,1 fournit par l'utilisateur
	private int type_selection_population; //0,1 fournit par l'utilisateur
	private double prob_mutation = 3; // defaut
	private int X_iterations;
	private int X_non_evolution_pop;
	private int X_non_evolutions_idividu;
	private int duree;
	private int nb_enfants;

	private Supplier<Individu<T>> fct_crea_individu;
	private Function<Individu<T>,T> fct_eval_individu;
	private Function<Individu<T>,Individu<T>> fct_mutation;
	

	/***
	 * Constructeur de la classe Algorithme
	 */
	public Algorithme(int taille, int select_parent, int select_pop, double prob_mut,
			int nb_enfants, Supplier<Individu<T>> fct_crea, Function<Individu<T>,T> fct_eval) {
		this.taille_pop = taille;
		this.type_selection_parent = select_parent;
		this.type_selection_population = select_pop;
		this.prob_mutation = prob_mut;
		this.nb_enfants = nb_enfants;
		this.fct_crea_individu = fct_crea;
		this.fct_eval_individu = fct_eval;
	}

	// TODO a enlever (juste pour corriger le bug rapidement)
	public Algorithme() {
	}
	
	/***
	 * Methode contenant l'ensemble de l'algorithme genetique a faire tourner
	 * @param les_individus
	 * @param methode
	 */
	public void LancerAlgorithme()
	{
		
		/*	
		Population<T> population = new Population<T>(taille_pop, fct_crea_individu, fct_eval_individu);
		Croisement<T> croisement = new Croisement<T>(fct_crea_individu);
		Mutation<T> mutation = new Mutation<T>();
		*/
		population = new Population<T>(taille_pop, fct_crea_individu, fct_eval_individu);
		croisement = new Croisement<T>(fct_crea_individu);
		mutation = new Mutation<T>(fct_mutation, prob_mutation);
		// duree_donnee en s, X iterations, X_non_evolution_pop, X_non_evolutions_idividu (si 0 pas pris en compte)
		critere_arret = new CritereArret<T>(duree, X_iterations, X_non_evolution_pop, X_non_evolutions_idividu);
		// TODO il manque une strategie
		// TODO
		try {
			switch(type_selection_parent) {
			case 0:
				// TODO Donner nb_enfants 
				selection_parent = new LoterieStrategy<T>(taille_pop);
			case 1:
				selection_parent = new ElitisteStrategy<T>(taille_pop);
			default: // TODO: Generer Exception si autre type_selection que 0 ou 1.
			}		
		}
		catch(Exception ex) {
			ex.toString();
		}

		// TODO 
		try {
			switch(type_selection_population) {
			case 0:
				selection_population = new LoterieStrategy<T>(taille_pop);
			case 1:
				selection_population = new ElitisteStrategy<T>(taille_pop);
			default: // TODO: Generer Exception si autre type_selection que 0 ou 1. 
			}
		}
		catch(Exception ex) {
			ex.toString();
		}

		do{
			//System.out.println( "Population : " + population.toString());
			
			population.EvaluatePopulation();
			
			//System.out.println( "Population : " + population.toString());

			liste_selection = selection_parent.methodeSelection(population);
			
			//System.out.println( "liste_selection : " + liste_selection.toString());

			liste_croisement = croisement.CrossoverPopulation(liste_selection);
			
			//System.out.println( "liste_croisement taille : " + liste_croisement.size());
			//System.out.println( "liste_croisement : " + liste_croisement);

			
			population.AjoutIndividus(liste_croisement);	
			
			//System.out.println( "Population : " + population.toString());

			
			liste_mutation = mutation.doMutation(population.getPopulation());
			//System.out.println("liste_mutation "+liste_mutation.toString() );
			population.setPopulation(liste_mutation);
			population.EvaluatePopulation();			
			selection_population.methodeSelection(population);
			//System.out.println("N� g�neration : " + population.getCurrent_generation());
			//System.out.println(population.toString());
			population.NewGeneration();

			// TODO observer ?
		}while(critere_arret.getEtat(population));
		
		System.out.println("Arret");
		System.out.println("Best individu : "+population.getBest());
	}

	/***
	 * Getter de la variable type_selection_parent
	 * @return le type de selection_parent choisi par le client
	 */
	public int getType_selection_parent() {
		return type_selection_parent;
	}

	/***
	 * Setter sur le type de selection_parent choisi par le client
	 * @param type_selection_parent
	 */
	public void setType_selection_parent(int type_selection_parent) {
		this.type_selection_parent = type_selection_parent;
	}

	/***
	 * Getter de la variable type_selection_population
	 * @return le type de selection_population choisi par le client
	 */
	public int getType_selection_population() {
		return type_selection_population;
	}

	/***
	 * Setter sur le type de selection_population choisi par le client
	 * @param type_selection_population
	 */
	public void setType_selection_population(int type_selection_population) {
		this.type_selection_population = type_selection_population;
	}

	/***
	 * Getter de la variable taill_pop
	 * @return la taille de la population
	 */
	public int getTaille_pop() {
		return taille_pop;
	}

	/***
	 * Setter sur la taille de la population souhaitee
	 * @param taille_pop
	 */
	public void setTaille_pop(int taille_pop) {
		this.taille_pop = taille_pop;
	}

	/***
	 * Getter de la variable prob_mutation
	 * @return le pourcentage de mutation des individus
	 */
	public double getProb_Mutation() {
		return prob_mutation;
	}

	/***
	 * Setter sur le pourcentage de mutation aux individus selectionnes
	 * @param mutation 
	 */
	public void setProb_Mutation(double mutation) {
		this.prob_mutation = mutation;
	}


	/***
	 * Getter de la variable Fct_crea_individu
	 * @return une fonction permettant la creation d'un individu
	 */
	public Supplier<Individu<T>> getFct_crea_individu() {
		return this.fct_crea_individu;
	}

	/***
	 * Setter sur la fonction de creation d'un individu
	 * @param fct_crea_individu
	 */
	public void setFct_crea_individu(Supplier<Individu<T>> fct_crea_individu) {
		this.fct_crea_individu = fct_crea_individu;
	}
	
	/***
	 * Getter de la variable Fct_mutation_individu
	 * @return une fonction permettant la mutation d'un individu
	 */
	public Function<Individu<T>, Individu<T>> getFct_mutation_individu() {
		return this.fct_mutation;
	}

	/***
	 * Setter sur la fonction de mutation d'un individu
	 * @param fct_mutation_individu
	 */
	public void setFct_mutation_individu(Function<Individu<T>, Individu<T>> fct_mutation) {
		this.fct_mutation = fct_mutation;
	}
	
	/***
	 * Getter de la variable Fct_eval_individu
	 * @return une fonction permettant l'evaluation d'un individu
	 */
	public Function<Individu<T>, T> getFct_eval_individu() {
		return this.fct_eval_individu;
	}

	/***
	 * Setter sur la fonction de l'evaluation d'un individu
	 * @param fct_mutation_individu
	 */
	public void setFct_eval_individu(Function<Individu<T>, T> fct_eval_individu) {
		this.fct_eval_individu = fct_eval_individu;
	}
	
	/**
	 * @return the x_iterations
	 */
	public int getX_iterations() {
		return X_iterations;
	}

	/**
	 * @param x_iterations the x_iterations to set
	 */
	public void setX_iterations(int x_iterations) {
		X_iterations = x_iterations;
	}

	/**
	 * @return the x_non_evolution_pop
	 */
	public int getX_non_evolution_pop() {
		return X_non_evolution_pop;
	}

	/**
	 * @param x_non_evolution_pop the x_non_evolution_pop to set
	 */
	public void setX_non_evolution_pop(int x_non_evolution_pop) {
		X_non_evolution_pop = x_non_evolution_pop;
	}

	/**
	 * @return the x_non_evolutions_idividu
	 */
	public int getX_non_evolutions_idividu() {
		return X_non_evolutions_idividu;
	}

	/**
	 * @param x_non_evolutions_idividu the x_non_evolutions_idividu to set
	 */
	public void setX_non_evolutions_idividu(int x_non_evolutions_idividu) {
		X_non_evolutions_idividu = x_non_evolutions_idividu;
	}

	/**
	 * @return the duree
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/**
	 * @return the nb_enfants
	 */
	public int getNb_enfants() {
		return nb_enfants;
	}

	/**
	 * @param nb_enfants the nb_enfants to set
	 */
	public void setNb_enfants(int nb_enfants) {
		this.nb_enfants = nb_enfants;
	}
}
