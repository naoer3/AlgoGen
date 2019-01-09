package algorithme;


public class Main {
	
	private static Population myPop =null;
	private static EvalMethode myLoterie=null;
	private static Individu typeIndividu =null;

	public static void main(String[] args) {
		
		setMyPop(new Population(100));
		//setMyLoterie(new LoterieStrategy(10));
		//setMyLoterie(new ElitisteStrategy(5));
		setMyLoterie(new TournoiStrategy(5));
		myLoterie.methodeEvaluation(myPop);
		System.out.println(myPop.toString());

	}

	public static Population getMyPop() {
		return myPop;
	}

	public static void setMyPop(Population myPop) {
		Main.myPop = myPop;
	}

	public static EvalMethode getMyLoterie() {
		return myLoterie;
	}

	public static void setMyLoterie(EvalMethode myLoterie) {
		Main.myLoterie = myLoterie;
	}

}
