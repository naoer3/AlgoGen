package algorithme;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui controle le critere d'arret evolution de la population
 * Ce critere controle le fait que la population continue a evolue
 * @param <T>
 */
public class CritereEvolutionPopulation<T extends Comparable<T>> extends CritereArretMethode<T> {

	/**
	 * Liste decrivant la population de l'iteration precedente
	 */
	private List<Individu<T>> lastPop = new ArrayList<>();
	
	/**
	 * Compte le nombre d'iteration ou la population reste inchangee
	 */
	private int nbSamePop = 1;
	
	/**
	 * Constructeur de la classe
	 * @param critere
	 */
	public CritereEvolutionPopulation(int critere)
	{
		setCritere(critere);
	}
	
	/**
	 * Controle si l'etat de l'algorithme a atteint le critere d'arret evolution population
	 */
	@Override
	public boolean getEtat(Population<T> population) {
		List<Individu<T>> currentPop = population.getPopulation();
		if(currentPop.equals(lastPop))
			nbSamePop +=1;
		else
			nbSamePop = 1;
		lastPop = currentPop;
		
		if(nbSamePop>=critere) return false;
		else return true;
	}

}
