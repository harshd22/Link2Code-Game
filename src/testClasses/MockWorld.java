package testClasses;

public class MockWorld {

	String[][] world;

	public MockWorld(String[][] world) {
		world = new String[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 1 && j == 1) {
					world[i][j] = "Rock";
				} else if (i == 2 && j == 2) {
					world[i][j] = "Tree";
				} else if (i == 3 && j == 3) {
					world[i][j] = "Barrel";
				} else if (i == 4 && j == 4) {
					world[i][j] = "Bumb";
				} else {
					world[i][j] = "Nothing";
				}
			}
		}
	}

	public boolean right(int x, int y) {
		if (world[x][y + 1] == "Nothing") {
			return true;
		} else {
			return false;
		}
	}

	public boolean left(int x, int y) {
		if (world[x][y - 1] == "Nothing") {
			return true;
		} else {
			return false;
		}
	}

	public boolean top(int x, int y) {
		if (world[x + 1][y] == "Nothing") {
			return true;
		} else {
			return false;
		}
	}

	public boolean bottom(int x, int y) {
		if (world[x - 1][y] == "Nothing") {
			return true;
		} else {
			return false;
		}
	}

}
