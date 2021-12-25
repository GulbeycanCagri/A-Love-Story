
import java.util.LinkedList;



public class RightRiverCity{
	private String name;
	private int weight;

	private LinkedList<NeighbourNode> paths= new LinkedList<NeighbourNode>();
	/**
	 * @param name
	 * @param numOfRrCities
	 */	
	RightRiverCity(String name) {
		this.name = name;
		this.weight = Integer.MAX_VALUE;
	}

	public void addPaths(NeighbourNode n) {
		paths.add(n);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public LinkedList<NeighbourNode> getPaths(){
		return paths;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}


}
