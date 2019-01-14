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

	// TODO a laisser ?! ne laisse pas le choix à l'utilisateur
	public Individu(List<T> genes){
		this.genes = genes;
	}
	
	// TODO a enlever (juste pour corriger le bug)
	public Individu(){
	}
	
	
	/**
	 * Retourne le score de l'individu
	 * @return fitness de l'individu
	 */
	public T getFitness() {
		return this.fitness;
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
		return this.genes;
	}

	/**
	 * @param genes the genes to set
	 */
	public void setGenes(List<T> genes) {
		this.genes = genes;
	}

	public void setGene(int index, T gene) {
		this.genes.set(index, gene);
	}	

	public List<T> getListGenes(int debut, int fin){
		return this.genes.subList(debut, fin);
	}

	public int getNbGenes() {
		return this.genes.size();
	}

	public T getGene(int index){
		return this.genes.get(index);
	}

	// TODO
	public String toString() {
		String str ="";
		System.out.println(genes);
		for (T t : genes) {
			str += t.toString();
			str += " ";
		}
		if(this.fitness != null)
			str+=" => " + this.fitness.toString();
		return str;		
	}

}