package algorithme;

import java.util.ArrayList;
import java.util.List;

public class CritereEvolutionPopulation<T extends Comparable<T>> extends CritereArretMethode<T> {

	private List<Individu<T>> lastPop = new ArrayList<>();
	private int nbSamePop = 1;
	
	public CritereEvolutionPopulation(int critere)
	{
		setCritere(critere);
	}
	
	@Override
	public boolean getEtat(Population<T> population) {
		List<Individu<T>> currentPop = population.getPopulation();
		if(currentPop.equals(lastPop))
			nbSamePop +=1;
		else
			nbSamePop = 1;
		lastPop = currentPop;
		
		if(nbSamePop>=critere) 
			return false;
		else
			return true;
	}

}
