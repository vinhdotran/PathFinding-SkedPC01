import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class pathFindingSolution {
	static final double SQRT_2 = 1.41421356237f;
	static char[][] directions = new char[][] {
		{'`', '^', '/'},
		{'<', 'S', '>'},
		{',', 'V', '\\'}
	};

	// Nested class
	public class Node {
		// all public, lazy on get set
		public int row;
		public int col;
		public boolean visited;
		public String key;
		public Node previousNode; // Node has least code to reach this node
		public double cost; // Cost from Source node to this node
		
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
			this.key = getKeyByIndex(row, col);
			this.visited = false;
			this.previousNode = null;
			this.cost = Double.MAX_VALUE;
		}

		// Get cost from this node to neighborNode
		public double getCost(Node neighborNode) {
			if (row == neighborNode.row || col == neighborNode.col){
				return 1;
			}
			return SQRT_2;
		}

		@Override
		public String toString() {
			return key + " cost: " + cost;
		}
	}

	// private members
	Node srcNode;
	Node desNode;
	Map<String, Node> nodeMap;

	// Constructor
	public pathFindingSolution() {
		this.nodeMap = new HashMap<String, Node>();
	}

	public void findShortestPath(char[][] sourceData) {
		initializeNodeMap(sourceData);
		if(this.srcNode == null || this.desNode == null) {
			// exception, cannot init src and des Node
		}
		List<Node> toBeVisitedNodes = new ArrayList<Node>();
		toBeVisitedNodes.add(this.srcNode);

		while (!toBeVisitedNodes.isEmpty()) {
			Node node = toBeVisitedNodes.remove(0);
			if (node == this.desNode) {
				break;
			}
			visitNode(node, toBeVisitedNodes);
		}

		// Print the output
		printMapWithPath(sourceData);
	}

	private void printMapWithPath(char[][] sourceData) {
		if (this.desNode.previousNode == null) {
			// exception: could not find path to des
			System.out.println("Not reachable.");
			mapGenerator.printMap(sourceData);
			return;
		}

		Node temp = this.desNode;
		while (temp.previousNode != null) {
			// TODO: clone sourceData?
			sourceData[temp.row][temp.col] = getDirectionChar(temp.previousNode, temp);
			temp = temp.previousNode;
		}

		mapGenerator.printMap(sourceData);
		System.out.println("Cost: " + this.desNode.cost);
	}

	private void visitNode(Node node, List<Node> toBeVisitedNodes) {
		if (node.visited) {
			return;
		}

		node.visited = true;

		// Fill the least cost for neighbor nodes
		for (Node neighbor : getNeighborNodes(node)) {
			double estCost = node.cost + node.getCost(neighbor);
			if (estCost < neighbor.cost){
				neighbor.cost = estCost;
				neighbor.previousNode = node;
			}
			if (!neighbor.visited && !toBeVisitedNodes.contains(neighbor)) {
				toBeVisitedNodes.add(neighbor);
			}
		}
	}

	private Map<String, Node> initializeNodeMap(char[][] sourceData) {
		for (int i = 0; i < sourceData.length; i++) {
			for (int j = 0; j < sourceData[i].length; j++) {
				if (sourceData[i][j] != 'X') {
					Node node = new Node(i, j);
					if (sourceData[i][j] == 'S') {
						this.srcNode = node;
						this.srcNode.cost = 0;
					} else if (sourceData[i][j] == 'O') {
						this.desNode = node;
					}
					nodeMap.put(node.key, node);
				}
			}
		}

		return nodeMap;
	}

	private List<Node> getNeighborNodes(Node node) {
		List<Node> neighborNodes = new ArrayList<Node>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				// Exclude it-self
				if (i != 0 || j != 0) {
					String key = getKeyByIndex(node.row + i, node.col + j);
					if (nodeMap.containsKey(key)) {
						// Strictly check for diagonal move
						Node neigborNode = nodeMap.get(key);
						if (nodeMap.containsKey(getKeyByIndex(node.row, neigborNode.col))
								|| nodeMap.containsKey(getKeyByIndex(neigborNode.row, node.col))) {
							neighborNodes.add(nodeMap.get(key));
						}
					}
				}
			}
		}
		return neighborNodes;
	}

	private char getDirectionChar(Node node1, Node node2) {
		return directions[node2.row - node1.row + 1][node2.col - node1.col + 1];
	}

	private static String getKeyByIndex(int x, int y) {
		return x + "_" + y;
	}
}
