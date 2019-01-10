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
 *
 */
public class Algorithme<T> {

	/***
	 * Création des variables
	 * population: Liste des individus
	 * methode: méthode de sélection des individus parents
	 * taille_pop: Taille de la population utilisée au sein de l'algorithme
	 * mutation: Pourcentage d'éléments mutés
	 * croisement: Pourcentage d'éléments croisés
	 * Bornes_inf: Borne définissant la limite inférieure dans laquelle devront se situer les nouveaux individus 
	 * Bornes_sup: Borne définissant la limite supérieure dans laquelle devront se situer les nouveaux individus
	 * type_selection: Définition du type de sélection des individus (élitiste, tournoi, loterie)
	 * fct_crea_individu: Fonction permettant la création d'individus au sein de la population 
	 */
	private List<Individu> liste_selection = new ArrayList<>();
	private List<Individu> liste_croisement = new ArrayList<>();
	
	private SelectionMethode selection_parent;
	private SelectionMethode selection_population;
	private Croisement croisement;
	private Mutation mutation;
	
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
	 * Methode contenant l'ensemble de l'algorithme génétique à faire tourner
	 * @param les_individus
	 * @param methode
	 */
	public void LancerAlgorithme()
	{
		Population population = new Population(taille_pop, fct_crea_individu, fct_eval_individu);
		
		switch(type_selection_parent) {
			case 0:
				selection_parent = new LoterieStrategy(taille_pop);
			case 1:
				selection_parent = new ElitisteStrategy(taille_pop);
			default: // TODO: Generer Exception si autre type_selection que 0 ou 1. 
		}		
		
		switch(type_selection_population) {
			case 0:
				selection_population = new LoterieStrategy(taille_pop);
			case 1:
				selection_population = new ElitisteStrategy(taille_pop);
			default: // TODO: Generer Exception si autre type_selection que 0 ou 1. 
		}

		do{
			population.EvaluatePopulation();
			
			liste_selection = selection_parent.methodeSelection(population);
			
			liste_croisement = croisement.methodeCroisement(liste_selection);
			
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
	 * @return une fonction permettant la cr�ation d'un individu
	 */
	public Supplier getFct_crea_individu() {
		return fct_crea_individu;
	}

	/***
	 * Setter sur la fonction de cr�ation d'un individu
	 * @param fct_crea_individu
	 */
	public void setFct_crea_individu(Supplier fct_crea_individu) {
		this.fct_crea_individu = fct_crea_individu;
	}
	
}
