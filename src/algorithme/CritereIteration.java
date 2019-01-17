package algorithme;

public class CritereIteration<T extends Comparable<T>> extends CritereArretMethode<T> {

	
	public CritereIteration(int critere)
	{
		setCritere(critere);
	}
	
	
	@Override
	public boolean getEtat(Population<T> population) {
		int current_generation = population.getCurrent_generation();
		if(current_generation>= critere) 
			return false;
		else 
			return true;
	}

}
