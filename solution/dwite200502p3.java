// DWITE - February 2005 - Problem 3: Simple Continued Fractions


public final class dwite200502p3 extends DwiteSolution {
	
	public static void main(String[] args) {
		DwiteRunner.run("DATA31.txt", "OUT31.txt", new dwite200502p3());
	}
	
	
	protected void runOnce() {
		int n = io.readIntLine();
		int d = io.readIntLine();
		while (true) {
			io.print(n / d);  // Output whole part
			n %= d;  // Take fractional part
			if (n == 0)
				break;
			int temp = n;  // Reciprocate
			n = d;
			d = temp;
			io.print(",");
		}
		io.println();
	}
	
}
