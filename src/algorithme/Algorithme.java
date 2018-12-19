package algorithme;

import java.util.ArrayList;
import java.util.List;

public class Algorithme {

	/***
	 * Cr�ation des variables
	 * population: Liste des individus
	 * methode: m�thode de s�lection des individus parents
	 */
	List<MyIndividu> population = new ArrayList<>();
	EvalMethode methode;
	
	/***
	 * Constructeur de la classe Algorithme
	 * @param _population: liste d'individus saisis par le client.
	 * @param _methode: methode (Loterie, Elitiste ou Tournoi) s�lectionn�e pour la s�lection des parents.
	 */
	public Algorithme(List<MyIndividu> _population, EvalMethode _methode)
	{
		for(MyIndividu p : _population)
		{
			population.add(p);
		}
		_methode = methode;
	}
	
	public void LancerAlgorithme(List<MyIndividu> les_individus)
	{
		
	}
}
