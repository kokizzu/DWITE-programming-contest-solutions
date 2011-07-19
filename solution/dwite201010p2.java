// DWITE - October 2010 - Problem 2: Robot Vacuum Prototype
// Solution by Nayuki Minase


public final class dwite201010p2 extends DwiteSolution {
	
	public static void main(String[] args) {
		DwiteRunner.run("DATA2.txt", "OUT2.txt", new dwite201010p2());
	}
	
	
	protected void runOnce() {
		String map = io.readLine();
		int position = map.indexOf('*');
		for (int i = 0; i < 5; i++) {
			String inst = io.readLine();
			if (inst.equals("L"))
				position = Math.max(position - 1, 0);
			else if (inst.equals("R"))
				position = Math.min(position + 1, map.length() - 1);
			else
				throw new IllegalArgumentException();
		}
		print(".", position);
		io.print("*");
		print(".", map.length() - position - 1);
		io.println();
	}
	
	
	private void print(String s, int n) {
		for (int i = 0; i < n; i++)
			io.print(s);
	}
	
}
