package algorithme;

import java.util.ArrayList;

public class Population {
	private ArrayList<Individu> myPopulation = null;
	
	public Population(int nbIndividu) {
		myPopulation = new ArrayList<Individu>(); 
		setMyPopulation(nbIndividu);
	}
	public int getNbIndividu() {
		return myPopulation.size();
	}
	
	public ArrayList<Individu> getMyPopulation() {
		return myPopulation;
	}
	public void setMyPopulation(int size) {
		for(int i=0;i<size;i++) {
			//double number = Math.random() * ( 100 - 2 );
			MyIndividu2 e =new MyIndividu2(i);
			myPopulation.add(e);
		}
	}
	
	@Override
	public String toString() {
		String str="";
		for(int i=0;i<getNbIndividu();i++) {
			str+=myPopulation.get(i).toString()+"\n";
		}
		return str;
		
	}

}
