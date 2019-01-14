package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

	private static Population myPop = null;
	private static SelectionMethode myLoterie=null;
	private static Individu typeIndividu =null;

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
		algo.setTaille_pop(200);
		algo.setProb_Mutation(3);
		algo.setNb_enfants(20);
		algo.setFct_crea_individu(CreateIndividu);
		algo.setFct_mutation_individu(Mutation);
		algo.setFct_eval_individu(Evaluation);
		algo.setType_selection_parent(1);
		algo.setType_selection_population(1);
		algo.LancerAlgorithme();

		//setMyPop(new Population(100));
		//setMyLoterie(new LoterieStrategy(10));
		//setMyLoterie(new ElitisteStrategy(5));
		//setMyLoterie(new TournoiStrategy<Double>(5));
		//myLoterie.methodeSelection(myPop);
		//System.out.println(myPop.toString());
	}


	/*public static Population<Double> getMyPop() {
		return myPop;
	}

	public static void setMyPop(Population<Double> myPop) {
		Main.myPop = myPop;
	}


	public static SelectionMethode<Double> getMyLoterie() {
		return myLoterie;
	}

	public static void setMyLoterie(SelectionMethode<Double> myLoterie) {
		Main.myLoterie = myLoterie;
	}*/

		/*Function<Individu<Double>,Double> fct_eval =  (indiv) -> {
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
	}*/}
