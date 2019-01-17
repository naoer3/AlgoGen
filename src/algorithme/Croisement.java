package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Classe permettant de generer des croisements entre les individus d'une population
 * @version 1.0
 * @since 1.0
 * @param <T>
 */
public class Croisement<T> {
	
	/***
	 * Declaration des variables
	 * creation_individu: Fonction permettant de creer un individu
	 */
	Supplier<Individu<T>> creation_individu;
	
	/***
	 * Constructeur de la classe Croisement
	 * @param creation_individu
	 */
	public Croisement(Supplier<Individu<T>> creation_individu) {
		this.creation_individu = creation_individu;
	}
	
	/***
	 * CrossoverPopulation: Methode permettant d'effectuer des croisements au sein d'une population
	 * @param selection
	 * @return une liste d'enfants
	 */
	public List<Individu<T>> CrossoverPopulation(List<Individu<T>> selection){
		Individu<T> parent1 = null;
		Individu<T> parent2 = null;
		List<Individu<T>> enfants = new ArrayList<Individu<T>>();
		int nbParents = selection.size();
		Random rand = new Random();
		// TODO gerer pas prendre le meme parent
		for (int i = 0; i <nbParents-1; i++) {
			parent1 = selection.get(rand.nextInt(nbParents));
			parent2 = selection.get(rand.nextInt(nbParents));
			enfants.add(Crossover(parent1,parent2));
		}
		return enfants;
	}
	
	/***
	 * Crossover: Methode permettant d'effectuer le croisement de deux parents particuliers
	 * @param1 parent1
	 * @param2 parent2
	 * @return un enfant
	 */
	public Individu<T> Crossover(Individu<T> parent1, Individu<T> parent2) {
		Individu<T> nv_individu = this.creation_individu.get();
		Random rand = new Random();
		int cut_index = rand.nextInt(nv_individu.getNbGenes()-1)+1;
		List<T> genes = new ArrayList<>();
		genes.addAll(parent1.getListGenes(0, cut_index));
		genes.addAll(parent2.getListGenes(cut_index, nv_individu.getNbGenes()));
		nv_individu.setGenes(genes);
		return nv_individu;
		
	}

}
