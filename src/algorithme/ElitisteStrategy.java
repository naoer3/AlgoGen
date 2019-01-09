package algorithme;



public class ElitisteStrategy extends SelectionMethode{

	private int myPourcentage = 100;
	
	public ElitisteStrategy(int Pourcentage)
	{
		setMyPourcentage(Pourcentage);
	}
	
	@Override
	public Population methodeSelection(Population p) { 
		double produit=(myPourcentage*p.getPopulation().size());
		int firstIndex=(int)Math.round(produit/100)+1;
		int lastIndex = p.getPopulation().size();
		
		for(int index=firstIndex ; index<=lastIndex;index++) {
			p.getPopulation().remove(p.getPopulation().size()-1);
		}
		
		return p;
	}
	
	public int getMyPourcentage() {
		return myPourcentage;
	}

	public void setMyPourcentage(int myPourcentage) {
		this.myPourcentage = myPourcentage;
	}

}
