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
	private ArrayList<Population> population = new ArrayList<>();
	private EvalMethode methode;
	private int taille_pop;
	private double mutation;
	private double croisement;
	private ArrayList<Object> bornes_inf = new ArrayList<>();
	private ArrayList<Object> bornes_sup = new ArrayList<>();
	private int type_selection;
	private Function fct_crea_individu;
	
	/***
	 * Constructeur de la classe Algorithme
	 */
	public Algorithme()
	{
		
	}
	
	/***
	 * Méthode contenant l'ensemble de l'algorithme génétique à faire tourner
	 * @param les_individus
	 * @param methode
	 */
	public void LancerAlgorithme(ArrayList<Population> population, EvalMethode methode, int NbRestant)
	{	
		
		
		// Récupération de la liste d'individus à évaluer selon la méthode voulue
		// Evaluation de la population
		// Sélection des individus
		// Croisement des x meilleurs individus retenus par la méthode
		// Mutation des meilleurs nouveaux individus générés
		// Remplacer les anciens individus par les nouveaux
		// Respect du critère (Oui/Non)
		
	}

	// Getters and Setters
	public ArrayList<Population> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<Population> population) {
		this.population = population;
	}

	public EvalMethode getMethode() {
		return methode;
	}

	public void setMethode(EvalMethode methode) {
		this.methode = methode;
	}

	public int getTaille_pop() {
		return taille_pop;
	}

	public void setTaille_pop(int taille_pop) {
		this.taille_pop = taille_pop;
	}

	public double getMutation() {
		return mutation;
	}

	public void setMutation(double mutation) {
		this.mutation = mutation;
	}

	public double getCroisement() {
		return croisement;
	}

	public void setCroisement(double croisement) {
		this.croisement = croisement;
	}

	public ArrayList<Object> getBornes_inf() {
		return bornes_inf;
	}

	public void setBornes_inf(ArrayList<Object> bornes_inf) {
		this.bornes_inf = bornes_inf;
	}

	public ArrayList<Object> getBornes_sup() {
		return bornes_sup;
	}

	public void setBornes_sup(ArrayList<Object> bornes_sup) {
		this.bornes_sup = bornes_sup;
	}

	public int getType_selection() {
		return type_selection;
	}

	public void setType_selection(int type_selection) {
		this.type_selection = type_selection;
	}
	
	public Function getFct_crea_individu() {
		return fct_crea_individu;
	}

	public void setFct_crea_individu(Function fct_crea_individu) {
		this.fct_crea_individu = fct_crea_individu;
	}
	


}
