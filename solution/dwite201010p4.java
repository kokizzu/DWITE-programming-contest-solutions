// DWITE - October 2010 - Problem 4: Planting Trees
// Solution by Nayuki Minase


public final class dwite201010p4 extends DwiteSolution {
	
	public static void main(String[] args) {
		DwiteRunner.run("DATA4.txt", "OUT4.txt", new dwite201010p4());
	}
	
	
	protected void runOnce() {
		int n = io.readIntLine();
		int[][] trees = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			io.tokenizeLine();
			trees[i][0] = io.readIntToken();
			trees[i][1] = io.readIntToken();
		}
		
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					if (triangleInteriorContains(trees[i], trees[j], trees[k], 0, 0))
						count++;
				}
			}
		}
		io.println(count);
	}
	
	
	private static boolean triangleInteriorContains(int[] a, int[] b, int[] c, int x, int y) {
		int d = parallelogramSignedArea(a[0], a[1], b[0], b[1], x, y);
		int e = parallelogramSignedArea(b[0], b[1], c[0], c[1], x, y);
		int f = parallelogramSignedArea(c[0], c[1], a[0], a[1], x, y);
		return d > 0 && e > 0 && f > 0 || d < 0 && e < 0 && f < 0;
	}
	
	
	private static int parallelogramSignedArea(int x0, int y0, int x1, int y1, int x2, int y2) {
		int a = x0 - x1;
		int b = y0 - y1;
		int c = x0 - x2;
		int d = y0 - y2;
		return a * d - b * c;
	}
	
}
