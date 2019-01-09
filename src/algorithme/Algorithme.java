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
	 * Cr�ation des variables
	 * population: Liste des individus
	 * methode: m�thode de s�lection des individus parents
	 * taille_pop: Taille de la population utilis�e au sein de l'algorithme
	 * mutation: Pourcentage d'�l�ments mut�s
	 * croisement: Pourcentage d'�l�ments crois�s
	 * Bornes_inf: Borne d�finissant la limite inf�rieure dans laquelle devront se situer les nouveaux individus 
	 * Bornes_sup: Borne d�finissant la limite sup�rieure dans laquelle devront se situer les nouveaux individus
	 * type_selection: D�finition du type de s�lection des individus (�litiste, tournoi, loterie)
	 * fct_crea_individu: Fonction permettant la cr�ation d'individus au sein de la population 
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
	 * M�thode contenant l'ensemble de l'algorithme g�n�tique � faire tourner
	 * @param les_individus
	 * @param methode
	 */
	public void LancerAlgorithme(ArrayList<Population> population, EvalMethode methode, int NbRestant)
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
