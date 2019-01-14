package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Classe deffinisant la metode de selection de type elitiste
 * @version 1.0
 * @since 1.0
 * @param <T>
 * @see SelectionMethode
 */
public class ElitisteStrategy<T> extends SelectionMethode<T>{

	/**
	 * Pourcentage souhaitee de parents selectione
	 */
	private Double pourcentage = 0.0;
	
	/**
	 * Constructeur de la classe
	 * Initialise une ElitisteStrategy
	 * @param Pourcentage souhaitee de parents selectione
	 */
	public ElitisteStrategy(int pct)
	{
		//setPourcentage(pct);	// TODO
		setPourcentage(0.3);
		//Il faut definir si le poucentage est sous forme de 0.3 ou 30 par exemple
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
		int taille_population = p.getPopulation().size();
		int taille_liste = ((Double)(taille_population*pourcentage)).intValue();
		//taille_liste=(int)Math.round(taille_liste/100)+1;
		
		//System.out.println(taille_liste);
		for(int index = 0; index <= taille_liste; index++) {
			
			list_select.add(p.getPopulation().get(index));
		}
		return list_select;
	}
	
	/// Getter et Setter
	
	/**
	 * Getter de l'attribut pourcentage
	 * @return pourcentage
	 */
	public Double getPourcentage() {
		return pourcentage;
	}

	/**
	 * Setter de l'attribut pourcentage
	 * @param pourcentage
	 */
	public void setPourcentage(Double pct) {
		this.pourcentage = pct;
	}

}
