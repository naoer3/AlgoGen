package algorithme;

import java.util.List;

public abstract class SelectionMethode<T> {
	
	public abstract List<Individu<T>> methodeSelection(Population<T> p);
}
