package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.Supplier;

/***
 * Classe contenant l'ensemble de l'algorithme
 * @author Antoine BLAINEAU
 */
public class Algorithme<T> {

	/***
	* VARIABLES GLOBALES
	* 
	* liste_selection: Liste contenant les individus sélectionnes parmi la population de base
	* liste_croisement: Liste contenant les individus enfants nés du croisement de certains individus de la liste_selection
	* 
	* selection_parent: Objet SelectionMethode permettant d'affecter une evaluation a la population "mere" en fonction de la demande du client
	* selection_population: Objet SelectionMethode permettant d'affecter une evaluation a la nouvelle population en fonction de la demande du client
	* croisement: Objet Croisement permettant de generer des croisement entre les individus selectionnes
	* mutation: Objet Mutation permettant de generer des mutations sur des individus selectionnes
	* 
	* taille_pop: Taille de la population utilisee au sein de l'algorithme
	* type_selection_parent: Methode de selection d'individus, au sein de la population "mere", choisie par le client (elitiste, loterie)
	* type_selection_population: Methode de selection d'individus, au sein de la nouvelle population, choisie par le client (elitiste, loterie)
	* prob_mutation: Pourcentage d'elements mutes
	* prob_croisement: Pourcentage d'elements croises
	* 
	* Bornes_inf: Borne definissant la limite inferieure dans laquelle devront se situer les nouveaux individus 
	* Bornes_sup: Borne definissant la limite superieure dans laquelle devront se situer les nouveaux individus
	* 
	* fct_crea_individu: Fonction permettant la creation d'individus au sein de la population
	* fct_eval_individu: Fonction permettant d'évaluer les individus au sein d'une population
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
	
	private ArrayList<Object> bornes_inf = new ArrayList<>();
	private ArrayList<Object> bornes_sup = new ArrayList<>();
	
	private Supplier<Individu<T>> fct_crea_individu;
	private Function<Individu<T>,T> fct_eval_individu;
	
	/***
	 * Constructeur de la classe Algorithme
	 */
	public Algorithme() {}
	
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
				selection_parent = new ElitisteStrategy(taille_pop);
			break;
			default: 
				System.out.println("Vous devez choisir une valeur égale à 0 ou 1");
				// TODO: Generer Exception si autre type_selection que 0 ou 1. 
		}		
		
		switch(type_selection_population) {
			case 0:
				selection_population = new LoterieStrategy<T>(taille_pop);
			case 1:
				selection_population = new ElitisteStrategy(taille_pop);
			break;
			default: 
				System.out.println("Vous devez choisir une valeur égale à 0 ou 1");
				// TODO: Generer Exception si autre type_selection que 0 ou 1. 
		}

		do{
			population.EvaluatePopulation();
			
			liste_selection = selection_parent.methodeSelection(population);
			
			liste_croisement = croisement.CrossoverPopulation(liste_selection);
			
			population.AjoutIndividus(liste_croisement);
			
			mutation.methodeMutation(population);
			
			population.EvaluatePopulation();
			
			selection_population.methodeSelection(population);
			
			System.out.println("A retirer, juste pour puller !");
			
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
	 * Setter sur le type de selection_population choici par le client
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
	 * Getter de la variable bornes_inf
	 * @return les bornes inferieures concernant l'encadrement des nouveaux individus
	 */
	public ArrayList<Object> getBornes_inf() {
		return bornes_inf;
	}

	/***
	 * Setter sur les bornes inferieures concernant l'encadrement des nouveaux individus 
	 * @param bornes_inf
	 */
	public void setBornes_inf(ArrayList<Object> bornes_inf) {
		this.bornes_inf = bornes_inf;
	}

	/***
	 * Getter de la variable bornes_sup
	 * @return les bornes superieures concernant l'encadrement des nouveaux individus
	 */
	public ArrayList<Object> getBornes_sup() {
		return bornes_sup;
	}

	/***
	 * Setter sur les bornes surperieures concernant l'encadrement des nouveaux individus 
	 * @param bornes_sup
	 */
	public void setBornes_sup(ArrayList<Object> bornes_sup) {
		this.bornes_sup = bornes_sup;
	}
	
	/***
	 * Getter de la variable Fct_crea_individu
	 * @return une fonction permettant la crï¿½ation d'un individu
	 */
	public Supplier getFct_crea_individu() {
		return fct_crea_individu;
	}

	/***
	 * Setter sur la fonction de crï¿½ation d'un individu
	 * @param fct_crea_individu
	 */
	public void setFct_crea_individu(Supplier<Individu<T>> fct_crea_individu) {
		this.fct_crea_individu = fct_crea_individu;
	}
	
}
	