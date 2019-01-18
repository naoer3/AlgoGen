package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;


public class Main {
  
	public static void main(String[] args) {

		Algorithme<Integer> algo = new Algorithme<>();

		Supplier<Individu<Integer>> CreateIndividu  = ()-> {
			Random r = new Random();
			Individu<Integer> individu = new Individu<Integer>() {};
			List<Integer> list = new ArrayList<>();
			list.add(r.nextInt(10));
			list.add(r.nextInt(10));
			individu.setGenes(list);
			return  individu;
		};
		
		Function<Individu<Integer>,Integer> Evaluation = (Individu<Integer> individu) ->{
			int x = individu.getGene(0);
			int y = individu.getGene(1);
			return (x + 2 * y - 7) *  (x + 2 * y - 7) + (2 * x +y -5)* (2 * x +y -5);
		};
		
		Function<Individu<Integer>,Individu<Integer>> Mutation = (Individu<Integer> individu) ->{
			Random r = new Random();
			int index = r.nextInt(individu.getNbGenes());
			individu.setGene(index, r.nextInt(10));
			return individu;
		};		
		
		algo.setDuree(30);
		algo.setX_iterations(1000);
		algo.setX_non_evolution_pop(10);
		algo.setX_non_evolutions_idividu(30);
		algo.setTaille_pop(200);
		algo.setProb_Mutation(3);
		algo.setNb_enfants(20);
		algo.setFct_crea_individu(CreateIndividu);
		algo.setFct_mutation_individu(Mutation);
		algo.setFct_eval_individu(Evaluation);
		algo.setTaille_tournoi(4);
		algo.setNb_selection_parent(3);
		algo.setNb_selection_population(3);
		algo.setType_selection_parent(1);
		algo.setType_selection_population(1);
		algo.LancerAlgorithme();
	}
}

