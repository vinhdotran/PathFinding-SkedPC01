public class main {

	/*
	// Edit this matrix for testing
	static char[][] map = new char[][] {
		//0   1   2   3   4   5   6   7   8   9
		{'X','X','X','X','X','X','X','X','X','X'}, //0
		{'X',' ',' ',' ','X','X',' ','X',' ','X'}, //1
		{'X',' ','X',' ',' ','X',' ','X',' ','X'}, //2
		{'X','S','X','X',' ',' ',' ','X',' ','X'}, //3
		{'X',' ','X',' ',' ','X',' ',' ',' ','X'}, //4
		{'X',' ',' ',' ','X','X',' ','X',' ','X'}, //5
		{'X',' ','X',' ',' ','X',' ','X',' ','X'}, //6
		{'X',' ','X','X',' ',' ',' ','X',' ','X'}, //7
		{'X',' ',' ','O',' ','X',' ',' ',' ','X'}, //8
		{'X','X','X','X','X','X','X','X','X','X'}, //9
	};
	*/

	public static void main(String[] args) {
		// TODO read input char matrix from file?
		int row = 100;
		int col = 100;
		if (args.length > 1) {
			col = Integer.valueOf(args[1]);
		}
		if (args.length > 0) {
			row = Integer.valueOf(args[0]);
		}

		mapGenerator mapGen = new mapGenerator(row, col);
		char[][] map = mapGen.generateMap();

		pathFindingSolution sol = new pathFindingSolution();
		sol.findShortestPath(map);
	}
}
