package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/***
 * Classe contenant l'ensemble de l'algorithme
 * @author Antoine BLAINEAU
 *
 */
public class Algorithme<T> {

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
	
	private SelectionMethode<T> selection_parent;
	private SelectionMethode<T> selection_population;
	private Croisement<T> croisement;
	private Mutation<T> mutation;
	
	private int taille_pop;
	private int type_selection_parent;	//0,1 fournit par l'utilisateur
	private int type_selection_population; //0,1 fournit par l'utilisateur
	private double prob_mutation;
	private double prob_croisement;
	
	private Supplier<Individu<T>> fct_crea_individu;
	private Function<Individu<T>,T> fct_eval_individu;
	
	/***
	 * Constructeur de la classe Algorithme
	 */
	public Algorithme(int taille, int select_parent, int select_pop, double prob_mut,
			double prob_crois, Supplier<Individu<T>> fct_crea, Function<Individu<T>,T> fct_eval) {
		this.taille_pop = taille;
		this.type_selection_parent = select_parent;
		this.type_selection_population = select_pop;
		this.prob_mutation = prob_mut;
		this.prob_croisement = prob_crois;
		this.fct_crea_individu = fct_crea;
		this.fct_eval_individu = fct_eval;
	}
	
	/***
	 * Methode contenant l'ensemble de l'algorithme genetique a faire tourner
	 * @param les_individus
	 * @param methode
	 */
	public void LancerAlgorithme()
	{
		Population<T> population = new Population<T>(taille_pop, fct_crea_individu, fct_eval_individu);
		Croisement<T> croisement = new Croisement<T>(fct_crea_individu);
		Mutation<T> mutation = new Mutation<T>();
		
		switch(type_selection_parent) {
			case 0:
				selection_parent = new LoterieStrategy<T>(taille_pop);
			case 1:
				selection_parent = new ElitisteStrategy<T>(taille_pop);
			default: // TODO: Generer Exception si autre type_selection que 0 ou 1. 
		}		
		
		switch(type_selection_population) {
			case 0:
				selection_population = new LoterieStrategy<T>(taille_pop);
			case 1:
				selection_population = new ElitisteStrategy<T>(taille_pop);
			default: // TODO: Generer Exception si autre type_selection que 0 ou 1. 
		}

		do{
			population.EvaluatePopulation();			
			liste_selection = selection_parent.methodeSelection(population);			
			liste_croisement = croisement.CrossoverPopulation(liste_selection);			
			population.AjoutIndividus(liste_croisement);			
			mutation.methodeMutation(population);			
			population.EvaluatePopulation();			
			selection_population.methodeSelection(population);			
		}while(true);
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
	 * Getter de la variable prob_croisement 
	 * @return le pourcentage de croisement des individus selectionnes
	 */
	public double getProb_Croisement() {
		return prob_croisement;
	}

	/***
	 * Setter sur le pourcentage de croisement des individus selectionnes
	 * @param croisement
	 */
	public void setProb_Croisement(double croisement) {
		this.prob_croisement = croisement;
	}
	
	/***
	 * Getter de la variable Fct_crea_individu
	 * @return une fonction permettant la cr�ation d'un individu
	 */
	public Supplier<Individu<T>> getFct_crea_individu() {
		return fct_crea_individu;
	}

	/***
	 * Setter sur la fonction de creation d'un individu
	 * @param fct_crea_individu
	 */
	public void setFct_crea_individu(Supplier<Individu<T>> fct_crea_individu) {
		this.fct_crea_individu = fct_crea_individu;
	}
	
}
	