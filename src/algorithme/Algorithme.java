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
public class Algorithme {

	/***
	 * Création des variables
	 * population: Liste des individus
	 * methode: méthode de sélection des individus parents
	 * taille_pop: Taille de la population utilisée au sein de l'algorithme
	 * prob_mutation: Pourcentage d'éléments mutés
	 * prob_croisement: Pourcentage d'éléments croisés
	 * type_selection: Définition du type de sélection des individus (élitiste, tournoi, loterie)
	 * fct_crea_individu: Fonction permettant la création d'individus au sein de la population 
	 */
	private ArrayList<Population> population = new ArrayList<>();
	private Selection methode;
	private int taille_pop;
	private double prob_mutation;
	private double prob_croisement;
	private int type_selection;
	private Supplier fct_crea_individu;
	
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
	public void LancerAlgorithme(ArrayList<Population> population, Selection methode, int NbRestant)
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

	public Selection getMethode() {
		return methode;
	}

	public void setMethode(Selection methode) {
		this.methode = methode;
	}

	public int getTaille_pop() {
		return taille_pop;
	}

	public void setTaille_pop(int taille_pop) {
		this.taille_pop = taille_pop;
	}

	public double getProb_mutation() {
		return prob_mutation;
	}

	public void setProb_mutation(double mutation) {
		this.prob_mutation = mutation;
	}

	public double getProb_croisement() {
		return prob_croisement;
	}

	public void setProb_croisement(double croisement) {
		this.prob_croisement = croisement;
	}

	public int getType_selection() {
		return type_selection;
	}

	public void setType_selection(int type_selection) {
		this.type_selection = type_selection;
	}
	
	public Supplier getFct_crea_individu() {
		return fct_crea_individu;
	}

	public void setFct_crea_individu(Supplier fct_crea_individu) {
		this.fct_crea_individu = fct_crea_individu;
	}
	
// TODO
	// Voir quel design pattern peut gérer rapport entre classe (1 classe doit gérer tous les rapportsn ou alors 1 classe gère 
	// un rapport avec une classe qui gère un rapport avec une autre classe, et etc..)

}
