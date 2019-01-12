package algorithme;

public class CritereArret<T> {

	// TODO phoneState ??

	private int X_iterations;
	private int X_non_evolution_pop;
	private int X_non_evolutions_idividu;
	private long start;
	private long stop;

	public CritereArret(int duree, int X_iterations, int X_non_evolution_pop, int X_non_evolutions_idividu) {
		this.X_iterations = X_iterations;
		this.X_non_evolution_pop = X_non_evolution_pop;
		this.X_non_evolutions_idividu = X_non_evolutions_idividu;
		this.start = System.currentTimeMillis();
		this.stop = start + (duree * 1000);
		//System.out.println("start : " +start );
		//System.out.println("Stop : " +stop );
	}

	public boolean getEtat(Population<T> population) {
		//System.out.println("Current : " +System.currentTimeMillis() );
		//System.out.println("Stop : " +stop );

		int current_generation = population.getCurrent_generation();
		//System.out.println("generation num : " + current_generation);
		if(System.currentTimeMillis() >= stop || current_generation>= X_iterations) {
			// Stop
			return false;
		}
		else {
			// Continue
			return true;
		}


	}
}
