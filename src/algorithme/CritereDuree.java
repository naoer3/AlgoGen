package algorithme;

/**
 * Classe qui définit le critère d'arret en fonction du temps
 * @param <T>
 */
public class CritereDuree<T extends Comparable<T>> extends CritereArretMethode<T>  {

	/**
	 * Represente la date de lancement de l'algorithme
	 */
	private long start;
	
	/**
	 * Represente la date d'arret prevue de l'algorithme pour respecter le critere
	 */
	private long stop;
	
	/**
	 * Constructeur de la classe CritereDuree
	 * @param critere
	 */
	public CritereDuree(int critere)
	{
		setCritere(critere);
		this.start = System.currentTimeMillis();
		this.stop = start + (this.critere * 1000);
	}
	
	/**
	 * Verifie si le critere d'arret est atteint
	 */
	@Override
	public boolean getEtat(Population<T> population) {
		if(System.currentTimeMillis() >= stop) return false;
		else return true;
	}

}
