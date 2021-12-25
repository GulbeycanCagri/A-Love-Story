
import java.util.LinkedList;

public class LeftRiverCity {
	private String name;
	private LinkedList<NeighbourNode> neighbours= new LinkedList<NeighbourNode>();
	private int weight;
	/**
	 * For calculating the weight while using algorithms.
	 */
	private int temporaryWeight;
	private LeftRiverCity Parent;
	LeftRiverCity(String name) {
		this.name = name;
		this.weight = Integer.MAX_VALUE;
		this.Parent = null;
		this.temporaryWeight = Integer.MAX_VALUE;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the neighbours
	 */
	public LinkedList<NeighbourNode> getNeighbours() {
		return neighbours;
	}
	public void addNeighbours(NeighbourNode neighbour){
		neighbours.add(neighbour);
	}

	/**
	 * @return the distance
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setWeight(int distance) {
		this.weight = distance;
	}

	/**
	 * @return the parent
	 */
	public LeftRiverCity getParent() {
		return Parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(LeftRiverCity parent) {
		Parent = parent;
	}

	/**
	 * @return the temporaryWeight
	 */
	public int getTemporaryWeight() {
		return temporaryWeight;
	}

	/**
	 * @param temporaryWeight the temporaryWeight to set
	 */
	public void setTemporaryWeight(int temporaryWeight) {
		this.temporaryWeight = temporaryWeight;
	}

}
