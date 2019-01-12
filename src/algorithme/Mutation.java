package algorithme;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Mutation<T> {
	
	private Function<Individu<T>, Individu<T>> fct_mutation;
	private double prob_mutation;

	public Mutation(Function<Individu<T>, Individu<T>> fct_mutation, double prob_mutation) {
		this.fct_mutation = fct_mutation;
		this.prob_mutation=prob_mutation;
	}

	public List<Individu<T>> doMutation(List<Individu<T>> individus)
	{
		System.out.println("Mutation - Avant : " + individus);

		Random rand = new Random();
		for (Individu<T> individu : individus) {
			if((rand.nextInt(100)+1)<=prob_mutation) {
				individu = doIndividuMutation(individu);
			}
		}
		System.out.println("Mutation - Après : " + individus);
		return individus;
	}
	
	public Individu<T> doIndividuMutation(Individu<T> individu){
		return fct_mutation.apply(individu);
	}
	
}
