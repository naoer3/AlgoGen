package algorithme;

/**
 * Classe qui controle le critere d'arret evolution individu
 * @param <T>
 */
public class CritereEvolutionIndividu<T extends Comparable<T>> extends CritereArretMethode<T> {

	/**
	 * Correspond au meilleur individu de la population de la generation precedente
	 */
	private Individu<T> lastBestIndividu = new Individu<T>();
	
	/**
	 * Nombre de fois ou le meilleur individu reste le meme
	 */
	private int nbSameBest = 1;
	
	/**
	 * Constructeur de la classe CritereEvolutionIndividu
	 * @param critere
	 */
	public CritereEvolutionIndividu(int critere)
	{
		setCritere(critere);
	}
	
	/**
	 * Verifie si le critere d'arret est atteint
	 */
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
