package algorithme;

import java.util.List;

/**
 * Definit un individu
 */
public class Individu<T> {

	/**
	 * Liste des gènes d'un individu
	 */
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
	 * Setter de l'attribut genes
	 * @param genes Nouvelle valeur de genes
	 */
	public void setGenes(List<T> genes) {
		this.genes = genes;
	}

	/**
	 * Change la valeur d'un gene a un emplacement donne
	 * @param index Emplacement du gene dans la liste
	 * @param gene Valeur du nouveau gene
	 */
	public void setGene(int index, T gene) {
		this.genes.set(index, gene);
	}	

	/**
	 * Retourne la liste des genes entre un emplacement debut et un emplacement fin
	 * @param debut Index du debut de la liste a retourner
	 * @param fin Index de fin de la liste a retourner
	 * @return la liste de genes
	 */
	public List<T> getListGenes(int debut, int fin){
		return this.genes.subList(debut, fin);
	}

	/**
	 * Retourne le nombre de genes d'un individu
	 * @return nombre de genes
	 */
	public int getNbGenes() {
		return this.genes.size();
	}

	/**
	 * Retourne un gene pour un indice donne
	 * @param index
	 * @return le gene a l'indice index
	 */
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