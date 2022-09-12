/* 
 * DWITE programming contest solutions
 * November 2010 - Problem 5: "Cogwheels"
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/dwite-programming-contest-solutions
 * https://github.com/nayuki/DWITE-programming-contest-solutions
 */


public final class dwite201011p5 extends DwiteSolution {
	
	public static void main(String[] args) {
		new dwite201011p5().run("DATA5.txt", "OUT5.txt");
	}
	
	
	protected void runOnce() {
		io.tokenizeLine();
		int n = io.readIntToken();
		int m = io.readIntToken();
		
		int[][] connections = new int[m][2];  // 0-based indexing
		for (int i = 0; i < m; i++) {
			io.tokenizeLine();
			connections[i][0] = io.readIntToken() - 1;
			connections[i][1] = io.readIntToken() - 1;
		}
		
		// Propagate directions
		char[] directions = new char[n];  // 0-based indexing
		directions[0] = 'A';  // Clockwise
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int a = connections[j][0];
				int b = connections[j][1];
				char dirA = directions[a];
				char dirB = directions[b];
				if      (dirA == 0  ) directions[a] = oppositeDirection(dirB);
				else if (dirB == 0  ) directions[b] = oppositeDirection(dirA);
				else if (dirA == 'L') directions[b] = 'L';
				else if (dirB == 'L') directions[a] = 'L';
				else if (dirA == dirB) { directions[a] = directions[b] = 'L'; }
				// Else a and b do turn and have different directions, do nothing
			}
		}
		
		io.println("" + directions[1] + directions[2]);
	}
	
	
	private static char oppositeDirection(char dir) {
		return switch (dir) {
			case 0 -> 0;
			case 'A' -> 'B';
			case 'B' -> 'A';
			case 'L' -> 'L';
			default -> throw new IllegalArgumentException();
		};
	}
	
}
