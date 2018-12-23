package algorithme;

import java.util.ArrayList;

public class Population {
	private int nbIndividu;
	private ArrayList<MyIndividu> myPopulation = null;
	
	public Population() {
		setNbIndividu(100);
		myPopulation = new ArrayList<MyIndividu>(); 
		setMyPopulation();
		for(int i=0;i<=nbIndividu;i++) {
			System.out.println(myPopulation.get(i).toString());
		}
	}
	public int getNbIndividu() {
		return nbIndividu;
	}
	public void setNbIndividu(int nbIndividu) {
		this.nbIndividu = nbIndividu;
	}
	public ArrayList<MyIndividu> getMyPopulation() {
		return myPopulation;
	}
	public void setMyPopulation() {
		for(int i=0;i<=nbIndividu;i++) {
			//double number = Math.random() * ( 100 - 2 );
			MyIndividu e =new MyIndividu(i);
			myPopulation.add(e);
		}
	}

}
