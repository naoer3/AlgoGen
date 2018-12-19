package algorithme;

import java.util.ArrayList;
import java.util.List;

public class Algorithme {

	/***
	 * Création des variables
	 * population: Liste des individus
	 * methode: méthode de sélection des individus parents
	 */
	List<MyIndividu> population = new ArrayList<>();
	EvalMethode methode;
	
	/***
	 * Constructeur de la classe Algorithme
	 * @param _population: liste d'individus saisis par le client.
	 * @param _methode: methode (Loterie, Elitiste ou Tournoi) sélectionnée pour la sélection des parents.
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
