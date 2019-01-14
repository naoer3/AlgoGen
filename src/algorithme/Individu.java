package algorithme;

import java.util.List;

/**
 * Definit un individu
 */
public class Individu<T> {
	
	private List<T> genes;
		
	/**
	 * Score de l'individu
	 */
	private T fitness;
	
	public Individu(List<T> genes){
		this.genes = genes;
	}
	
	/**
	 * Retourne le score de l'individu
	 * @return fitness de l'individu
	 */
	public T getFitness() {
		return fitness;
	}

	/**
	 * Permet de modifier le score de l'individu
	 * @param fitness la nouvelle fitness de l'individu
	 */
	public void setFitness(T fitness) {
		this.fitness = fitness;
	}

	/**
	 * @return the genes
	 */
	public List<T> getGenes() {
		return genes;
	}

	/**
	 * @param genes the genes to set
	 */
	public void setGenes(List<T> genes) {
		this.genes = genes;
	}	
	
	public List<T> getListGenes(int debut, int fin){
		return genes.subList(debut, fin);
	}
	
	public int getNbGenes() {
		return genes.size();
	}
	
}