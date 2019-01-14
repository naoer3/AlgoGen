package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
	
	public static void main(String[] args) {
		
		Function<Individu<Double>,Double> fct_eval =  (indiv) -> {
			Double x = indiv.getGenes().get(0);
			Double y = (2 * x) + 3;
			return y;
		};
		
		Supplier<Individu<Double>> fct_crea =  () -> {
			Random r = new Random();
			//x compris entre 0 et 100
			double gene = 0 + (100 - 0) * r.nextDouble();
			List<Double> genes = new ArrayList<>();
			genes.add(gene);
			Individu<Double> indiv = new Individu<>(genes);
			return indiv;
		};
		
		int taille_pop = 100;
		int selection_parent = 0;
		int selection_population = 1;
		double prob_mutation = 0.03;
		double prob_croisement = 0.2;
		
		Algorithme<Double> algo = new Algorithme<>(taille_pop, selection_parent, selection_population, prob_mutation, prob_croisement, fct_crea, fct_eval);
		algo.LancerAlgorithme();
	}
}
