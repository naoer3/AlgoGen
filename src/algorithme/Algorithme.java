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
	 * Creation des variables
	 * population: Liste des individus
	 * methode: methode de selection des individus parents
	 * taille_pop: Taille de la population utilisee au sein de l'algorithme
	 * mutation: Pourcentage d'elements mutes
	 * croisement: Pourcentage d'elements croises
	 * Bornes_inf: Borne definissant la limite inferieure dans laquelle devront se situer les nouveaux individus 
	 * Bornes_sup: Borne definissant la limite superieure dans laquelle devront se situer les nouveaux individus
	 * type_selection: Definition du type de selection des individus (elitiste, tournoi, loterie)
	 * fct_crea_individu: Fonction permettant la creation d'individus au sein de la population 
	 */
	private List<Individu<T>> liste_selection = new ArrayList<>();
	private List<Individu<T>> liste_croisement = new ArrayList<>();
	
	private SelectionMethode<T> selection_parent;
	private SelectionMethode<T> selection_population;
	private Croisement<T> croisement;
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
	 * Methode contenant l'ensemble de l'algorithme genetique a faire tourner
	 * @param les_individus
	 * @param methode
	 */
	public void LancerAlgorithme()
	{
		Population<T> population = new Population<T>(taille_pop, fct_crea_individu, fct_eval_individu);
		Croisement<T> croisement = new Croisement();
		Mutation mutation = new Mutation();
		
		switch(type_selection_parent) {
			case 0:
				selection_parent = new LoterieStrategy<T>(taille_pop);
			case 1:
				selection_parent = new ElitisteStrategy<T>(taille_pop);
			default: // TODO: Generer Exception si autre type_slection que 0 ou 1. 
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
