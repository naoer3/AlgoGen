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
	 * Cr�ation des variables
	 * population: Liste des individus
	 * methode: m�thode de s�lection des individus parents
	 * taille_pop: Taille de la population utilis�e au sein de l'algorithme
	 * prob_mutation: Pourcentage d'�l�ments mut�s
	 * prob_croisement: Pourcentage d'�l�ments crois�s
	 * type_selection: D�finition du type de s�lection des individus (�litiste, tournoi, loterie)
	 * fct_crea_individu: Fonction permettant la cr�ation d'individus au sein de la population 
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
	 * M�thode contenant l'ensemble de l'algorithme g�n�tique � faire tourner
	 * @param les_individus
	 * @param methode
	 */
	public void LancerAlgorithme(ArrayList<Population> population, Selection methode, int NbRestant)
	{	
		
		
		// R�cup�ration de la liste d'individus � �valuer selon la m�thode voulue
		// Evaluation de la population
		// S�lection des individus
		// Croisement des x meilleurs individus retenus par la m�thode
		// Mutation des meilleurs nouveaux individus g�n�r�s
		// Remplacer les anciens individus par les nouveaux
		// Respect du crit�re (Oui/Non)
		
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
	// Voir quel design pattern peut g�rer rapport entre classe (1 classe doit g�rer tous les rapportsn ou alors 1 classe g�re 
	// un rapport avec une classe qui g�re un rapport avec une autre classe, et etc..)

}
