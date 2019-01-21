
package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Classe deffinisant la methode de selection de type Tournoi
 * @version 1.7
 * @since 1.6
 * @param <T>
 */
public class Algorithme<T extends Comparable<T>> {

	/**
	 * Objet permettant l'appel des methodes concernant les eventuels criteres d'arret
	 */
	private List<CritereArretMethode<T>> criteres = new ArrayList<>();
	
	/**
	 * Liste d'individus mere, donnee par le client
	 */
	private Population<T> population;

	/**
	 * Objet permettant de definir la methode de selection pour les parents
	 */
	private SelectionMethode<T> selection_parent;
	
	/**
	 * Objet permettant de definir la methode de selection pour une population
	 */
	private SelectionMethode<T> selection_population;
	
	/**
	 * Objet permettant l'appel des methodes de croisement 
	 */
	private Croisement<T> croisement;
	
	/**
	 * Objet permettant l'appel des methodes de mutation
	 */
	private Mutation<T> mutation;
	
	/**
	 * Objet permettant l'appel a la fonction d'evaluation
	 */
	private FitnessEval<T> fitnessEval;

	/**
	 * Citere d'arret de duree
	 * Au bout du temps defini, l'algorithme s'arrete
	 */
	private CritereArretMethode<T> critere_duree;
	
	/**
	 * Critere d'arret individu
	 * Au bout d'un certain nombre d'iteration ou le meilleur individu n'evolue pas, l'algorithme s'arrete
	 */
	private CritereArretMethode<T> critere_individu;
	
	/**
	 * Critere d'arret population
	 * Au bout d'un certain nombre d'iteration ou la population n'evolue pas, l'algorithme s'arrete
	 */
	private CritereArretMethode<T> critere_population;
	
	/**
	 * Critere d'arret iteration
	 * Au bout d'un certain nombre d'iteration, l'algorithme s'arrete
	 */
	private CritereArretMethode<T> critere_iteration;

	/**
	 * Taille de la population fournie par le client
	 */
	private int taille_pop;
	
	/**
	 * Taille permettant l'application de la methode tournoi sur la population
	 */
	private int taille_tournoi;

	/**
	 * Numero de la methode de selection des parents
	 * 0 : Loterie
	 * 1 : Elitiste
	 * 2 : Tournoi
	 */
	private int type_selection_parent;
	
	/**
	 * Numero de la methode de selection de la population
	 * 0 : Loterie
	 * 1 : Elitiste
	 */
	private int type_selection_population;
	
	/**
	 * Pourcentage de mutation par defaut de l'algorithme (ici: 3 si non fournit par le client)
	 */
	private double prob_mutation = 3;
	
	/**
	 * Nombre d'iterations effectuees par l'algorithme (critere d'arret fournit ou non par le client)
	 */
	private int x_iterations_algo;
	
	/**
	 * Nombre d'iterations pour lesquelles la population n'evolue pas (critere d'arret fournit ou non par le client)
	 */
	private int x_stagnation_population;
	
	/**
	 * Nombre d'iterations pour lesquelles un individu n'evolue pas (critere d'arret fournit ou non par le client)
	 */
	private int x_stagnation_individu;
	
	/**
	 * Duree maximum d'execution de l'algorithme (fournit par le client)
	 */
	private int duree;
	
	/**
	 * Nombre d'enfants souhaites (fournit par le client)
	 */
	private int nb_enfants;

	/**
	 * Fonction permettant la creation d'un individu (fournit par le client)
	 */
	private Supplier<Individu<T>> fct_crea_individu;
	
	/**
	 * Fonction permettant l'evaluation d'un individu (fournit par le client)
	 */
	private Function<Individu<T>,T> fct_eval_individu;
	
	/**
	 * Fontion permettant la mutation d'un individu (fournit par le client)
	 */
	private Function<Individu<T>,Individu<T>> fct_mutation;

	/**
	 * Constructeur vide de l'algorithme
	 */
	public Algorithme() {}

	/***
	 * Constructeur de la classe Algorithme
	 * @param taille Taille de la population
	 * @param select_parent Mode de selection des parents
	 * @param select_pop Mode de selection de la population
	 * @param prob_mut Pourcentage de mutation
	 * @param nb_enfants Nombre d'enfants souhaites
	 * @param fct_crea Fonction de creation d'un individu
	 * @param fct_eval Fonction de d'evaluation d'un individu
	 */
	public Algorithme(int taille, int select_parent, int select_pop, double prob_mut,
			int nb_enfants, Supplier<Individu<T>> fct_crea, Function<Individu<T>,T> fct_eval, Function<Individu<T>,Individu<T>> fct_mut) {
		this.taille_pop = taille;
		this.type_selection_parent = select_parent;
		this.type_selection_population = select_pop;
		this.prob_mutation = prob_mut;
		this.nb_enfants = nb_enfants;
		this.fct_crea_individu = fct_crea;
		this.fct_eval_individu = fct_eval;
		this.fct_mutation = fct_mut;
	}

	/***
	 * Methode LancerAlgorithme qui contient l'appel des methoes necessaire au traitement genetique d'une population
	 */
	public List<T> LancerAlgorithme()
	{
		boolean add_critere_duree = false;
		boolean add_critere_iteration = false;
		boolean add_critere_population = false;
		boolean add_critere_individu = false;
		
		List<Individu<T>> parents = new ArrayList<>();
		List<Individu<T>> enfants = new ArrayList<>();
		List<Individu<T>> population_mutee = new ArrayList<>();
		
		try {

			if(duree < 0) throw new IllegalArgumentException("Duree must be greater than zero: " + duree);			
			else if(duree > 0)add_critere_duree = true;
			
			if(x_iterations_algo < 0) throw new IllegalArgumentException("Nb_iterations must be greater than zero: " + x_iterations_algo);
			else if(x_iterations_algo > 0) add_critere_iteration = true;
			
			if(x_stagnation_population < 0) throw new IllegalArgumentException("Stagnation_population must be greater than zero: " + x_stagnation_population);
			else if(x_stagnation_population > 0) add_critere_population = true;
			
			if(x_stagnation_individu < 0) throw new IllegalArgumentException("Stagnation_individu must be greater than zero: " + x_stagnation_individu);
			else if(x_stagnation_individu > 0) add_critere_individu = true;
			
			if(taille_tournoi<=2 && type_selection_parent==2)
				throw new IllegalArgumentException("Taille du tournoi doit etre superieur a  2: " + taille_tournoi);
			if(nb_enfants>=taille_pop)
				throw new IllegalArgumentException("Le nombre d'enfant doit inferieur a la taille de la population: " + nb_enfants);
			if(taille_pop<=0)
				throw new IllegalArgumentException("Taille popopulation doit etre superieur a zero: " + taille_pop);
      
			this.SelectCritere(add_critere_duree, add_critere_iteration, add_critere_population, add_critere_individu);
			
			boolean continu_algorithme=true;
			
			population = new Population<T>(taille_pop, fct_crea_individu);
			croisement = new Croisement<T>(fct_crea_individu);
			mutation = new Mutation<T>(fct_mutation, prob_mutation);
			fitnessEval = new FitnessEval<T>(fct_eval_individu);

			try {
				switch(type_selection_parent) {
				case 0:
					selection_parent = new LoterieStrategy<T>(nb_enfants + 1, false);
					break;
				case 1:
					selection_parent = new ElitisteStrategy<T>(nb_enfants + 1, false);
					break;
				case 2:
					selection_parent = new TournoiStrategy<T>(nb_enfants + 1,taille_tournoi);
					break;
				default: 
					throw new IllegalArgumentException("Selection parent type must be in range [0..2]: " + type_selection_parent);
				}		
			}
			catch(Exception ex) {
				ex.toString();
			}

			try {
				switch(type_selection_population) {
				case 0:
					selection_population = new LoterieStrategy<T>(taille_pop, true);
					break;
				case 1:
					selection_population = new ElitisteStrategy<T>(taille_pop, true);
					break;
				default: 
					throw new IllegalArgumentException("Selection population type must be in range [0..1]: " + type_selection_population);
				}
			}
			catch(Exception ex) {
				ex.toString();
			}

			do{
				//On attribue une fitness a chaque individu de la population
				fitnessEval.EvaluatePopulation(population.getPopulation());
				//On selectionne les parents 
				parents = selection_parent.methodeSelection(population);
				//Avec ces parents, on cree des enfants
				enfants = croisement.CrossoverPopulation(parents);
				//On ajoute les enfants a la population existante
				population.AjoutIndividus(enfants);	
				//On mute les individus de la population
				population_mutee = mutation.doMutation(population.getPopulation());
				//On change la population pour qu'elle prenne en compte les individus mutes
				population.setPopulation(population_mutee);
				//On reevalue la population
				fitnessEval.EvaluatePopulation(population.getPopulation());
				//On selectionne les individus de notre population finale
				selection_population.methodeSelection(population);
				//On incremente de 1 le nombre de generation de notre population
				population.NewGeneration();

				for(CritereArretMethode<T> c : criteres) {
					if(criteres.isEmpty()) throw new IllegalArgumentException("You must select a stop condition");
					if(!c.getEtat(population)) continu_algorithme=false;
				}
			}while(continu_algorithme);
			
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage().toString());
		}
		
		return population.getBest().getGenes();
	}

	/**
	 * Definit les criteres utilises dans l'algorithme
	 * @param ajout_duree
	 * @param ajout_iterations
	 * @param ajout_evolution_pop
	 * @param ajout_evolutions_idividu
	 */
	public void SelectCritere(boolean ajout_duree, boolean ajout_iterations, boolean ajout_evolution_pop, boolean ajout_evolutions_idividu) {
		if(ajout_duree) {
			critere_duree=new CritereDuree<T>(duree);
			criteres.add(critere_duree);
		}
		if(ajout_iterations) {
			critere_iteration=new CritereIteration<T>(x_iterations_algo);
			criteres.add(critere_iteration);
		}
		if(ajout_evolution_pop) {
			critere_population=new CritereEvolutionPopulation<T>(x_stagnation_population);
			criteres.add(critere_population);
		}
		if(ajout_evolutions_idividu) {
			critere_individu=new CritereEvolutionIndividu<T>(x_stagnation_individu);
			criteres.add(critere_individu);
		}
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
}

