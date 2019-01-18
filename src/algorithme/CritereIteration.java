package algorithme;

/**
 * Classe qui controle le critere d'arret du nombre d'iteration
 * Ce critere controle le nombre d'iteration de l'algorithme
 * @param <T>
 */
public class CritereIteration<T extends Comparable<T>> extends CritereArretMethode<T> {

	/**
	 * Constructeur de la classe CritereIteration
	 * @param critere
	 */
	public CritereIteration(int critere)
	{
		setCritere(critere);
	}
	
	/**
	 * Controle si l'etat de l'algorithme a atteint le critere d'arret iteration
	 */
	@Override
	public boolean getEtat(Population<T> population) {
		int current_generation = population.getCurrent_generation();
		if(current_generation>= critere) return false;
		else return true;
	}

}
