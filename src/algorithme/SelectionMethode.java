package algorithme;

import java.util.List;

public abstract class SelectionMethode<T extends Comparable<T>> {
	
	public abstract List<Individu<T>> methodeSelection(Population<T> p);
}
