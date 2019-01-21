package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;


public class Main {
  
	public static void main(String[] args) {

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
		
		Algorithme<Integer> algo = new Algorithme<>(100,0,1,3,20,CreateIndividu, Evaluation, Mutation);
		algo.setDuree(30);
		algo.setX_iterations(100);
		//algo.setX_non_evolution_pop(500);
		//algo.setX_non_evolutions_idividu(80);
		//algo.setTaille_tournoi(4);
		
		List<Integer> best = algo.LancerAlgorithme();
		System.out.println(best);
	}
}

