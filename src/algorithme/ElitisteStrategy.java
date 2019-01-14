package algorithme;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe definissant la methode de selection de type elitiste
 * @version 1.0
 * @since 1.0
 * @param <T>
 * @see SelectionMethode
 */
public class ElitisteStrategy<T extends Comparable<T>> extends SelectionMethode<T>{

	/**
	 * Pourcentage souhaite de parents selectione
	 */
	private int pourcentage = 0;
	
	/**
	 * Constructeur de la classe
	 * Initialise une ElitisteStrategy
	 * @param Pourcentage souhaite de parents selectionnes
	 */
	public ElitisteStrategy(int pct)
	{
		setPourcentage(pct);
	}
	
	/// Methodes
	
	/**
	 * Selectionne un pourcentage de parents avec le meilleur score dans la population
	 * @param Population
	 * @see Population
	 */
	@Override
	public List<Individu<T>> methodeSelection(Population<T> p) { 
		p.sortPopulation();
		List<Individu<T>> list_select = new ArrayList<>();
		int taille_population =p.getPopulation().size();
		int taille_liste=taille_population*pourcentage;
		taille_liste=(int)Math.round(taille_liste/100)+1;
		
		//System.out.println(taille_liste);
		for(int index=0 ; index<=taille_liste;index++) {
			
			list_select.add((Individu<T>)p.getPopulation().get(index));
		}
		return list_select;
	}
	
	/// Getter et Setter
	
	/**
	 * Getter de l'attribut pourcentage
	 * @return pourcentage
	 */
	public int getPourcentage() {
		return pourcentage;
	}

	/**
	 * Setter de l'attribut pourcentage
	 * @param pourcentage
	 */
	public void setPourcentage(int pct) {
		this.pourcentage = pct;
	}

}
