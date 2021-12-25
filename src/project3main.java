import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;


/**
 * @author cagri
 *
 */

/**
 * @param args
 * @throws FileNotFoundException 
 */


public class project3main {
	public static int Prim(int numOfRrcCities, ArrayList <RightRiverCity> rrCities, PriorityQueue<RightRiverCity> adjacentVertices) {
		int totalTax =0;
		HashSet<RightRiverCity> visitedCities = new HashSet<RightRiverCity>();
		RightRiverCity traversed;
		int numOfVertices = numOfRrcCities+1;
		while(!adjacentVertices.isEmpty()) {
			RightRiverCity current = adjacentVertices.poll();
			if(visitedCities.contains(current) || current.getPaths().size() == 0) {
				continue;
			}
			else {
				totalTax += current.getWeight();
				visitedCities.add(current);
				numOfVertices--;
				for (NeighbourNode n: current.getPaths()) {
					if(n.getName().equals(rrCities.get(0).getName())) {
						continue;
					}
					traversed = rrCities.get(Integer.valueOf(n.getName().substring(1)));
					if(!visitedCities.contains(traversed) && traversed.getWeight()>n.getWeight()) {
						adjacentVertices.remove(traversed);
						traversed.setWeight(n.getWeight());
						adjacentVertices.add(traversed);
					}
				}
			}
		}
		if(numOfVertices != 0) {
			return -1;
		}
		return totalTax;
	}


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(new File(args[0])).useLocale(Locale.US);
		PrintStream output = new PrintStream(new File(args[1]));
		int timeLimit = input.nextInt();
		int numOfCities = input.nextInt();
		int numOfLrcCities= 0;
		int numOfRrcCities= 0;
		int totalTax=0;
		// for printing the path
		Stack<LeftRiverCity> shortestPath = new Stack<LeftRiverCity>();
		String shortest = new String();
		// prevent infinite loop
		HashSet<LeftRiverCity> visited = new HashSet<LeftRiverCity>();
		// I assume Leyla's and Mecnun's cities are always on the left side of the river for simplicity.
		String cityOfMecnun = input.next();
		String cityOfLeyla = input.next();
		String cityName;
		int cityCode;
		/**
		 * For temporary Usage
		 */
		LeftRiverCity currentCity;
		LeftRiverCity traversedCity;
		PriorityQueue<LeftRiverCity> dijkstraQueue = new PriorityQueue<LeftRiverCity>(new TemporaryWeightComparator());
		/**
		 * Set for the cities that are located on the left hand side of the river.
		 */
		PriorityQueue<RightRiverCity> adjacentVertices = new PriorityQueue<RightRiverCity>(new PathComparator());
		HashMap<String,LeftRiverCity> lrCities = new HashMap<String,LeftRiverCity>();
		ArrayList <RightRiverCity> rrCities = new ArrayList<RightRiverCity>();
		int tax=0;
		input.nextLine();

		// first part
		while(input.hasNextLine()) {
			Scanner line = new Scanner(input.nextLine());
			cityName = line.next();
			numOfLrcCities++;
			LeftRiverCity lrc = new LeftRiverCity(cityName);
			lrCities.put(cityName,lrc);
			while(line.hasNext()) {
				lrc.addNeighbours(new NeighbourNode(line.next(), line.nextInt()));
			}
			if(lrc.getName().equals(cityOfLeyla)) {
				break;
			}

		}
		numOfRrcCities = numOfCities-numOfLrcCities;
		RightRiverCity leyla = new RightRiverCity(cityOfLeyla);
		rrCities.add(leyla);

		// creating objects for cities that located on the right side of the river. 
		for(int i =1;i<= numOfRrcCities; i++) {
			RightRiverCity rrc = new RightRiverCity("d"+String.valueOf(i));
			rrCities.add(rrc);
			adjacentVertices.add(rrc);
		}

		// Dijkstra's algorithm
		lrCities.get(cityOfMecnun).setTemporaryWeight(0);
		dijkstraQueue.add(lrCities.get(cityOfMecnun));
		while(!dijkstraQueue.isEmpty()) {
			currentCity = dijkstraQueue.poll();
			currentCity.setWeight(currentCity.getTemporaryWeight());
			if(currentCity.getName().equals(cityOfLeyla)) {
				break;
			}
			for (NeighbourNode neighbour: currentCity.getNeighbours()) {
				traversedCity = lrCities.get(neighbour.getName());
				if(!visited.contains(traversedCity)){
					if(neighbour.getWeight() + currentCity.getWeight() < traversedCity.getTemporaryWeight()) {
						dijkstraQueue.remove(traversedCity);
						traversedCity.setTemporaryWeight(neighbour.getWeight() + currentCity.getWeight());
						traversedCity.setParent(currentCity);
						dijkstraQueue.add(traversedCity);
					}

				}
			}
			visited.add(currentCity);
		}
		// saving the path.
		currentCity = lrCities.get(cityOfLeyla);
		shortestPath.add(currentCity);
		while(currentCity.getParent() != null) {
			currentCity = currentCity.getParent();
			shortestPath.add(currentCity);
		}
		// printing the first line
		if(lrCities.get(cityOfLeyla).getWeight() == Integer.MAX_VALUE) {
			output.println(-1);
			output.println(-1);
		}

		else {
			while(!shortestPath.isEmpty()) {
				shortest+=shortestPath.pop().getName()+" ";
			}
			output.println(shortest.substring(0,shortest.length()-1));

		visited.clear();
		if(lrCities.get(cityOfLeyla).getWeight()>timeLimit) {
			output.print(-1);
		}
		else {
			int cityNumber;
			for(int i=1;i<=numOfRrcCities;i++) {
				Scanner line = new Scanner(input.nextLine());
				cityNumber = Integer.valueOf(line.next().substring(1));
				while(line.hasNext()) {
					cityName = line.next();
					cityCode = Integer.valueOf(cityName.substring(1));
					tax = line.nextInt();
					rrCities.get(cityNumber).addPaths(new NeighbourNode(cityName, tax));
					rrCities.get(cityCode).addPaths(new NeighbourNode("d"+String.valueOf(cityNumber), tax));
				}
			}

			for(NeighbourNode leylasNeighbour: lrCities.get(cityOfLeyla).getNeighbours()) {
				if(leylasNeighbour.getName().contains("d")) {
					int weight = leylasNeighbour.getWeight();
					rrCities.get(Integer.valueOf(leylasNeighbour.getName().substring(1))).addPaths(new NeighbourNode(cityOfLeyla, weight));
					rrCities.get(0).addPaths(new NeighbourNode(leylasNeighbour.getName(), weight));
				}
			}

			lrCities.clear();
			rrCities.get(0).setWeight(0);
			adjacentVertices.add(leyla);
			totalTax = Prim(numOfRrcCities, rrCities, adjacentVertices);

			output.print(totalTax*2);
		}
	}
}
}
