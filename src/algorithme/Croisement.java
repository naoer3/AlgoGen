package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Croisement<T> {
	
	Supplier<Individu<T>> creation_individu;
	
	public Croisement(Supplier creation_individu) {
		this.creation_individu = creation_individu;
	}
	
	public List<Individu<T>> CrossoverPopulation(List<Individu<T>> selection){
		Individu parent1 = null;
		Individu parent2 = null;
		List<Individu<T>> enfants = new ArrayList();
		int nbParents = selection.size();
		Random rand = new Random();
		for (int i = 0; i <nbParents-1; i++) {
			parent1 = selection.get(rand.nextInt(nbParents));			
			parent2 = selection.get(rand.nextInt(nbParents));
			enfants.add(Crossover(parent1,parent2));
		}
		return enfants;
	}
	
	public <T> Individu Crossover(Individu parent1, Individu parent2) {
		Individu nv_individu = this.creation_individu.get();
		Random rand = new Random();
		int cut_index = rand.nextInt(nv_individu.getNbGenes());
		List<T> genes = parent1.getListGenes(0, cut_index);
		// TODO
		genes.addAll(parent2.getListGenes(cut_index-1, nv_individu.getNbGenes()));
		nv_individu.setGenes(genes);
		return nv_individu;
		
	}

}
