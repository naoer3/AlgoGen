package algorithme;



public class LoterieStrategy extends EvalMethode {
	private int myPourcentage = 0;
 
	
	public LoterieStrategy(int Pourcentage)
	{
		setMyPourcentage(Pourcentage);
	}
	

	@Override
	public Population methodeEvaluation(Population p) {
		
		
		return p;
	}


	public int getMyPourcentage() {
		return myPourcentage;
	}


	public void setMyPourcentage(int myPourcentage) {
		this.myPourcentage = myPourcentage;
	}


	

}
