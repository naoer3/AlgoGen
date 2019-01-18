package algorithme;

/**
 * Classe qui controle le critere d'arret evolution individu
 * @param <T>
 */
public class CritereEvolutionIndividu<T extends Comparable<T>> extends CritereArretMethode<T> {

	private Individu<T> lastBestIndividu = new Individu<T>();
	
	private int nbSameBest = 1;
	
	public CritereEvolutionIndividu(int critere)
	{
		setCritere(critere);
	}
	
	@Override
	public boolean getEtat(Population<T> population) {
		Individu<T> currentBestIndividu = population.getBest();
		if(lastBestIndividu.equals(currentBestIndividu))
			nbSameBest +=1;
		else
			nbSameBest = 1;
		
		if(nbSameBest>=critere) return false;
		else return true;
	}

}
