import java.util.Comparator;


public class PathComparator implements Comparator<RightRiverCity>{

	@Override
	public int compare(RightRiverCity r1, RightRiverCity r2) {
		return r1.getWeight()-r2.getWeight();
	}

	
	


}









