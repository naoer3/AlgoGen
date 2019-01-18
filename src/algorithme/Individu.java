package algorithme;

import java.util.List;

/**
 * Classe permettant de gerer les individus
 * @version 1.0
 * @since 1.0
 * @param <T>
 */
public class Individu<T> {

	/***
	 * Declaration des variables globales
	 * genes: Liste contenant les genes de l'individu
	 * fitness: Contient le score de l'individu
	 */
	private List<T> genes;
	private T fitness;

	/***
	 * Constructeur de la classe Individu
	 */
	public Individu() {}
	
	
	public Individu(List<T> genes){
		this.genes = genes;
	}
	
	/**
	 * Getter sur la variable fitness
	 * @return le score de l'individu
	 */
	public T getFitness() {
		return this.fitness;
	}

	/**
	 * Setter sur le score de l'individu
	 * @param fitness
	 */
	public void setFitness(T fitness) {
		this.fitness = fitness;
	}

	/**
	 * Getter de la variable genes
	 * @return la liste des genes de l'individu
	 */
	public List<T> getGenes() {
		return this.genes;
	}

	/**
	 * Setter sur la liste des genes de l'individu
	 * @param genes
	 */
	public void setGenes(List<T> genes) {
		this.genes = genes;
	}

	/**
	 * Setter sur la valeur d'un gene a un emplacement donne
	 * @param index: Emplacement du gene dans la liste
	 * @param gene: Valeur du nouveau gene
	 */
	public void setGene(int index, T gene) {
		this.genes.set(index, gene);
	}	

	/**
	 * Getter de la variable genes
	 * @param debut: Index du debut de la liste a retourner
	 * @param fin: Index de fin de la liste a retourner
	 * @return la liste de genes entre un emplacement debut et un emplacement fin
	 */
	public List<T> getListGenes(int debut, int fin){
		return this.genes.subList(debut, fin);
	}

	/**
	 * Getter de la taille de la liste genes
	 * @return le nombre de genes d'un individu
	 */
	public int getNbGenes() {
		return this.genes.size();
	}

	/**
	 * Getter d'un gene a un indice donne
	 * @param index
	 * @return le gene a l'indice index
	 */
	public T getGene(int index){
		return this.genes.get(index);
	}

	/***
	 * Methode toString
	 */
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