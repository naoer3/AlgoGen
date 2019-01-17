package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Classe permettant d'executer un algorithme genetique sur une population
 * @version 1.0
 * @since 1.0
 * @param <T>
 */
public class Algorithme<T extends Comparable<T>> {

	/***
	 * Declarations des variables globales
	 * parents: Liste des individus selectionnes par l'agorithme
	 * enfants: Liste d'individus enfants suite a l'application des croisements sur les parents
	 * population_mutee:  Liste d'individus suite a l'application des mutations
	 * population: Liste d'individus mere, donnee par le client
	 * selection_parent: Objet permettant de definir la methode de selection pour les parents
	 * selection_population: Objet permettant de definir la methode de selection pour une population
	 * croisement: Objet permettant l'appel des methodes de croisement 
	 * mutation: Objet permettant l'appel des methodes de mutation
	 * critere_arret: Objet permettant l'appel des methodes concernant les eventuels criteres d'arret
	 * taille_pop: Taille de la population fournie par le client
	 * taille_tournoi: Taille permettant l'application de la methode tournoi sur la population
	 * type_selection_parent: Type de selection des parents sur la population (fournit par le client)
	 * type_selection_population: Type de selection de la nouvelle population sur la population mut�e (fournit par le client)
	 * prob_mutation: Pourcentage de mutation par defaut de l'algorithme (ici: 3 si non fournit par le client)
	 * x_iterations_algo: Nombre d'iterations effectuees par l'algorithme (critere d'arret fournit ou non par le client)
	 * x_stagnation_population: Nombre d'iterations pour lesquelles la population n'evolue pas (critere d'arret fournit ou non par le client)
	 * x_stagnation_individu: Nombre d'iterations pour lesquelles un individu n'evolue pas (critere d'arret fournit ou non par le client)
	 * duree: Duree maximum d'execution de l'algorithme (fournit par le client)
	 * nb_enfants: Nombre d'enfants souhaites (fournit par le client)
	 * fct_crea_individu: Fonction permettant la creation d'un individu (fournit par le client)
	 * fct_eval_individu: Fonction permettant l'evaluation d'un individu (fournit par le client)
	 * fct_mutation: Fontion permettant la mutation d'un individu (fournit par le client)
	 */
	private List<Individu<T>> parents = new ArrayList<>();
	private List<Individu<T>> enfants = new ArrayList<>();
	private List<Individu<T>> population_mutee = new ArrayList<>();
	private Population<T> population;

	private SelectionMethode<T> selection_parent;
	private SelectionMethode<T> selection_population;
	private Croisement<T> croisement;
	private Mutation<T> mutation;
	private CritereArret<T> critere_arret;

	private int taille_pop;
	private int nb_selection_parent;
	private int nb_selection_population;
	private int taille_tournoi;

	private int type_selection_parent;	//0,1 fournit par l'utilisateur
	private int type_selection_population; //0,1 fournit par l'utilisateur
	private double prob_mutation = 3; // defaut
	private int x_iterations_algo;
	private int x_stagnation_population;
	private int x_stagnation_individu;
	private int duree;
	private int nb_enfants;

	private Supplier<Individu<T>> fct_crea_individu;
	private Function<Individu<T>,T> fct_eval_individu;
	private Function<Individu<T>,Individu<T>> fct_mutation;
	
	public Algorithme() {}

	/***
	 * Constructeur de la classe Algorithme
	 * @param taille: Taille de la population
	 * @param select_parent: Mode de selection des parents
	 * @param select_pop: Mode de selection de la population
	 * @param prob_mut: Pourcentage de mutation
	 * @param nb_enfants: Nombre d'enfants souhait�s
	 * @param fct_crea: Fonction de cr�ation d'un individu
	 * @param fct_eval: Fonction de d'�valuation d'un individu
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
	
	/***
	 * Methode LancerAlgorithme qui contient l'appel des methoes necessaire au traitement genetique d'une population
	 */
	public void LancerAlgorithme()
	{
		population = new Population<T>(taille_pop, fct_crea_individu, fct_eval_individu);
		croisement = new Croisement<T>(fct_crea_individu);
		mutation = new Mutation<T>(fct_mutation, prob_mutation);
		critere_arret = new CritereArret<T>(duree, x_iterations_algo, x_stagnation_population, x_stagnation_individu);
		// TODO il manque une strategie
		try {
			switch(type_selection_parent) {
			case 0:
				// TODO Donner nb_enfants 
				selection_parent = new LoterieStrategy<T>(nb_selection_parent, false);
			case 1:
				selection_parent = new ElitisteStrategy<T>(nb_selection_parent, false);
			case 2:
				selection_parent = new TournoiStrategy<T>(nb_selection_parent,taille_tournoi);
			default: // TODO: Generer Exception si autre type_selection que 0 ou 1.
			}		
		}
		catch(Exception ex) {
			ex.toString();
		}

		try {
			switch(type_selection_population) {
			case 0:
				selection_population = new LoterieStrategy<T>(nb_selection_population, true);
			case 1:
				selection_population = new ElitisteStrategy<T>(nb_selection_population, true);
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
			parents = selection_parent.methodeSelection(population);
			//System.out.println( "parents : " + parents.toString());
			enfants = croisement.CrossoverPopulation(parents);
			//System.out.println( "enfants taille : " + enfants.size());
			//System.out.println( "enfants : " + enfants);
			population.AjoutIndividus(enfants);	
			//System.out.println( "Population : " + population.toString());
			population_mutee = mutation.doMutation(population.getPopulation());
			//System.out.println("population_mutee "+population_mutee.toString() );
			population.setPopulation(population_mutee);
			population.EvaluatePopulation();			
			selection_population.methodeSelection(population);
			//System.out.println("Nouvelle generation : " + population.getCurrent_generation());
			//System.out.println(population.toString());
			population.NewGeneration();
			// TODO observer ?
		}while(critere_arret.getEtat(population));
	
		System.out.println("Arret");
		System.out.println("Best individu : "+population.getBest());
	}

	/***
	 * Getter de la variable type_selection_parent
	 * @return le type de selection_parent fournit par le client
	 */
	public int getType_selection_parent() {
		return type_selection_parent;
	}

	/***
	 * Setter sur le type de selection_parent fournit par le client
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
	
	/***
	 * Getter de la variable x_iterations_algo
	 * @return le nombre d'iterations maximum de l'algorithme
	 */
	public int getX_iterations() {
		return x_iterations_algo;
	}

	/***
	 * Setter sur la le nombre d'iterations maximum de l'algorithme
	 * @param x_iterations_algo
	 */
	public void setX_iterations(int x_iterations) {
		x_iterations_algo = x_iterations;
	}

	/***
	 * Getter de la variable x_stagnation_population
	 * @return nombre d'iterations au bout desquelles l'algorithme s'arrete si la population n'a pas evolue
	 */
	public int getX_non_evolution_pop() {
		return x_stagnation_population;
	}

	/***
	 * Setter sur le nombre d'iterations au bout desquelles l'algorithme s'arrete si la population n'a pas evolue
	 * @param x_stagnation_population
	 */
	public void setX_non_evolution_pop(int x_non_evolution_pop) {
		x_stagnation_population = x_non_evolution_pop;
	}

	/***
	 * Getter de la variable x_stagnation_individu
	 * @return nombre d'iterations au bout desquelles l'algorithme s'arrete si le meilleur individu n'a pas evolue
	 */
	public int getX_non_evolutions_idividu() {
		return x_stagnation_individu;
	}

	/***
	 * Setter sur le nombre d'iterations au bout desquelles l'algorithme s'arrete si le meilleur individu n'a pas evolue
	 * @param x_stagnation_individu
	 */
	public void setX_non_evolutions_idividu(int x_non_evolutions_idividu) {
		x_stagnation_individu = x_non_evolutions_idividu;
	}

	/***
	 * Getter de la variable duree
	 * @return la duree maximale d'execution de l'algorithme
	 */
	public int getDuree() {
		return duree;
	}

	/***
	 * Setter sur la duree maximale d'execution de l'algorithme
	 * @param x_stagnation_individu
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/***
	 * Getter de la variable nb_enfants
	 * @return le nombre d'enfants generes par les croisements
	 */
	public int getNb_enfants() {
		return nb_enfants;
	}

	/***
	 * Setter sur le nombre d'enfants generes par les croisements
	 * @param nb_enfants
	 */
	public void setNb_enfants(int nb_enfants) {
		this.nb_enfants = nb_enfants;
	}

	/***
	 * Getter de la variable taille_tournoi
	 * @return la taille du tournoi
	 */
	public int getTaille_tournoi() {
		return taille_tournoi;
	}

	/***
	 * Setter sur la taille du tournoi
	 * @param taille_tournoi
	 */
	public void setTaille_tournoi(int taille_tournoi) {
		this.taille_tournoi = taille_tournoi;
	}

	/***
	 * Getter de la variable nb_selection_parent
	 * @return le nombre de parents selectionnes
	 */
	public int getNb_selection_parent() {
		return nb_selection_parent;
	}

	/***
	 * Setter sur le nombre de parents selectionnes
	 * @param nb_selection_parent
	 */
	public void setNb_selection_parent(int nb_selection_parent) {
		this.nb_selection_parent = nb_selection_parent;
	}

	/***
	 * Getter de la variable nb_selection_population
	 * @return le nombre d'individus selectionnes parmis la population mutee
	 */
	public int getNb_selection_population() {
		return nb_selection_population;
	}

	/***
	 * Setter sur le nombre d'individus selectionnes parmis la population mutee
	 * @param nb_selection_population
	 */
	public void setNb_selection_population(int nb_selection_population) {
		this.nb_selection_population = nb_selection_population;
	}
}
