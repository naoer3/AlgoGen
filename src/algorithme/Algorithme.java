package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.function.Function;

/***
 * Classe contenant l'ensemble de l'algorithme
 * @author Antoine BLAINEAU
 *
 */
public class Algorithme {

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
	
	private Function fct_crea_individu;
	private Function fct_eval_individu;
	
	/***
	 * Constructeur de la classe Algorithme
	 */
	public Algorithme() {}
	
	/***
	 * Méthode contenant l'ensemble de l'algorithme génétique à faire tourner
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
			default: // TODO: Generer Exception si autre type_sélection que 0 ou 1. 
		}		
		
		switch(type_selection_population) {
			case 0:
				selection_population = new LoterieStrategy(taille_pop);
			case 1:
				selection_population = new ElitisteStrategy(taille_pop);
			default: // TODO: Generer Exception si autre type_sélection que 0 ou 1. 
		}

		do{
			population.EvaluatePopulation();
			liste_selection = selection_parent.methodeSelection(population);
			liste_croisement = croisement.methodeCroisement(liste_selection);
			population.AjoutIndividus(liste_croisement);
			mutation.methodeMutation(population);
			selection_population.methodeSelection(population);
		}while(true);
	}

	/***
	 * 
	 * @return
	 */
	public SelectionMethode getSelection_parent() {
		return selection_parent;
	}

	public void setSelection_parent(SelectionMethode selection_parent) {
		this.selection_parent = selection_parent;
	}

	public SelectionMethode getSelection_population() {
		return selection_population;
	}

	public void setSelection_population(SelectionMethode selection_population) {
		this.selection_population = selection_population;
	}

	public int getType_selection_parent() {
		return type_selection_parent;
	}

	public void setType_selection_parent(int type_selection_parent) {
		this.type_selection_parent = type_selection_parent;
	}

	public int getType_selection_population() {
		return type_selection_population;
	}

	public void setType_selection_population(int type_selection_population) {
		this.type_selection_population = type_selection_population;
	}

	public void setCroisement(Croisement croisement) {
		this.croisement = croisement;
	}

	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}

	/***
	 * Getter de la variable taill_pop
	 * @return la taille de la population
	 */
	public int getTaille_pop() {
		return taille_pop;
	}

	/***
	 * Setter sur la taille de la population souhaitée
	 * @param taille_pop
	 */
	public void setTaille_pop(int taille_pop) {
		this.taille_pop = taille_pop;
	}

	/***
	 * Getter du pourcentage de mutation des individus
	 * @return le pourcentage de mutation
	 */
	public double getMutation() {
		return prob_mutation;
	}

	/***
	 * Setter sur la variable prob_mutation pour affecter un pourcentage de mutation aux individus selectionnes
	 * @param mutation 
	 */
	public void setMutation(double mutation) {
		this.prob_mutation = mutation;
	}

	/***
	 * Getter du pourcentage de croisement des individus selectionnes
	 * @return le pourcentage de croisement
	 */
	public double getCroisement() {
		return prob_croisement;
	}

	/***
	 * Setter sur la variable prob_croisement pour affecter un pourcentage de croisement aux individus selectionnes
	 * @param croisement
	 */
	public void setCroisement(double croisement) {
		this.prob_croisement = croisement;
	}

	/***
	 * Getter de la liste de bornes inferieures concernant l'encadrement des nouveaux individus
	 * @return les bornes inférieures
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
	 * Getter de la liste de bornes superieures concernant l'encadrement des nouveaux individus
	 * @return les bornes superieures
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
	
	
	public Function getFct_crea_individu() {
		return fct_crea_individu;
	}

	public void setFct_crea_individu(Function fct_crea_individu) {
		this.fct_crea_individu = fct_crea_individu;
	}
	


}
