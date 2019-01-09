package algorithme;

public class MyIndividu<T> extends Individu {
	
	private T fitness;

	public T getFitness() {
		return fitness;
	}

	public void setFitness(T fitness) {
		this.fitness = fitness;
	}
	
}
