package algorithme;

public class CritereDuree<T extends Comparable<T>> extends CritereArretMethode<T>  {

	private long start;
	private long stop;
	
	public CritereDuree(int critere)
	{
		setCritere(critere);
		this.start = System.currentTimeMillis();
		this.stop = start + (this.critere * 1000);
	}
	
	@Override
	public boolean getEtat(Population<T> population) {
		if(System.currentTimeMillis() >= stop) return false;
		else return true;
	}

}
