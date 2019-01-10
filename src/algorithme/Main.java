package algorithme;


public class Main {
	
	private static Population<Double> myPop =null;
	private static SelectionMethode<Double> myLoterie=null;
	//private static Individu<Double> typeIndividu =null;

	public static void main(String[] args) {
		
		//setMyPop(new Population(100));
		//setMyLoterie(new LoterieStrategy(10));
		//setMyLoterie(new ElitisteStrategy(5));
		setMyLoterie(new TournoiStrategy<Double>(5));
		myLoterie.methodeSelection(myPop);
		System.out.println(myPop.toString());
	}

	public static Population<Double> getMyPop() {
		return myPop;
	}

	public static void setMyPop(Population<Double> myPop) {
		Main.myPop = myPop;
	}


	public static SelectionMethode<Double> getMyLoterie() {
		return myLoterie;
	}

	public static void setMyLoterie(SelectionMethode<Double> myLoterie) {
		Main.myLoterie = myLoterie;
	}

}
