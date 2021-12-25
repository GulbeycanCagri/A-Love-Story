import java.util.Comparator;

public class TemporaryWeightComparator implements Comparator<LeftRiverCity>{

	@Override
	public int compare(LeftRiverCity c1, LeftRiverCity c2) {
		if(c1.getTemporaryWeight()-c2.getTemporaryWeight()==0) {
			return Integer.valueOf(c1.getName().substring(1))- Integer.valueOf(c2.getName().substring(1));
		}
		return c1.getTemporaryWeight()-c2.getTemporaryWeight() ;
	}

}