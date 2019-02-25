import java.util.Random;


public class mapGenerator {
	int numberOfRow;
	int numberOfCol;

	public mapGenerator() {
		this(10, 10);
	}

	public mapGenerator(int row, int col) {
		this.numberOfRow = clampValue(row);
		this.numberOfCol = clampValue(col);
	}

	private int clampValue(int input) {
		input = Math.max(input, 10);
		input = Math.min(input, 100);
		return input;
	}

	public char[][] generateMap() {
		char[][] generatedMap = new char[numberOfRow][numberOfCol];
		int minRowIndex = 0;
		int maxRowIndex = numberOfRow - 1;
		int minColIndex = 0;
		int maxColIndex = numberOfCol - 1;

		Random randomGenerator = new Random();

		for (int i = 0; i < numberOfRow; i++) {
			for (int j = 0; j < numberOfCol; j++) {
				//Boundary
				if (i == minRowIndex || j == minColIndex || i == maxRowIndex || j == maxColIndex) {
					generatedMap[i][j] = 'X';
				} else {
					if (randomGenerator.nextInt(3) == 0) {
						// 1/3 chance to X
						generatedMap[i][j] = 'X';
					} else {
						generatedMap[i][j] = ' ';
					}
				}
			}
		}
		int srcRow = randomGenerator.nextInt(maxRowIndex - 1) + 1;
		int srcCol = randomGenerator.nextInt(maxColIndex - 1) + 1;
		int desRow = randomGenerator.nextInt(maxRowIndex - 1) + 1;
		int desCol = randomGenerator.nextInt(maxColIndex - 1) + 1;

		int halfRow = numberOfRow/2;
		int halfCol = numberOfCol/2;
		// ensure src and des are on the other side
		if (desRow/halfRow == srcRow/halfRow) {
			desRow = (desRow + halfRow) % numberOfRow;
		}
		if (desCol/halfCol == srcCol/halfCol) {
			desCol = (desCol + halfCol) % numberOfCol;
		}
		generatedMap[srcRow][srcCol] = 'S';
		generatedMap[desRow][desCol] = 'O';

		return generatedMap;
	}

	public static void printMap(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
	}
}
