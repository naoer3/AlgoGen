package algorithme;

import java.util.Comparator;

public class ComparatorIndividu<T extends Comparable<T>> implements Comparator<Individu<T>>{

	@Override
	public int compare(Individu<T> indiv1, Individu<T> indiv2) {
		T fit1 = indiv1.getFitness();
		T fit2 = indiv2.getFitness();
		return fit1.compareTo(fit2);
	}

}
