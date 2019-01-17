package algorithme;

import java.util.ArrayList;
import java.util.List;

public class CritereArret<T extends Comparable<T>> {

	// TODO phoneState ??

	private int X_iterations;
	private int X_non_evolution_pop;
	private int X_non_evolutions_individu;
	private long start;
	private long stop;
	private Individu<T> lastBestIndividu = new Individu<T>();
	private int nbSameBest = 1;
	private List<Individu<T>> lastPop = new ArrayList<>();
	private int nbSamePop = 1;

	// TODO revoir facon de gérer les différents criteres
	// TODO tableau d'objet critere qu'on actualise à chaque fois et qui nous dise si il sont ok
	public CritereArret(int duree, int X_iterations, int X_non_evolution_pop, int X_non_evolutions_idividu) {
		this.X_iterations = X_iterations;
		this.X_non_evolution_pop = X_non_evolution_pop;
		this.X_non_evolutions_individu = X_non_evolutions_idividu;
		this.start = System.currentTimeMillis();
		this.stop = start + (duree * 1000);
		//System.out.println("start : " +start );
		//System.out.println("Stop : " +stop );
	}

	public boolean getEtat(Population<T> population) {
		//System.out.println("Current : " +System.currentTimeMillis() );
		//System.out.println("Stop : " +stop );
		Individu<T> currentBestIndividu = population.getBest();
		List<Individu<T>> currentPop = population.getPopulation();
		if(lastBestIndividu.equals(currentBestIndividu))
			nbSameBest +=1;
		else
			nbSameBest = 1;
		if(currentPop.equals(lastPop))
			nbSamePop +=1;
		else
			nbSamePop = 1;
		lastPop = currentPop;
		int current_generation = population.getCurrent_generation();
		//System.out.println("generation num : " + current_generation);
		if(System.currentTimeMillis() >= stop || current_generation>= X_iterations || nbSameBest >= X_non_evolutions_individu || nbSamePop>=X_non_evolution_pop) {
			// Stop
			return false;
		}
		else {
			// Continue
			return true;
		}


	}
}
