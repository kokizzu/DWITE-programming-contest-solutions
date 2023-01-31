/* 
 * DWITE programming contest solutions
 * January 2006 - Problem 2: "Scrabble"
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/dwite-programming-contest-solutions
 * https://github.com/nayuki/DWITE-programming-contest-solutions
 */

import java.util.HashMap;
import java.util.Map;


public final class dwite200601p2 extends DwiteSolution {
	
	public static void main(String[] args) {
		new dwite200601p2().run("DATA21.txt", "OUT21.txt");
	}
	
	
	private static final Map<Character,Integer> valueByLetter = new HashMap<>();
	static {
		valueByLetter.put('A',  1);
		valueByLetter.put('B',  3);
		valueByLetter.put('C',  3);
		valueByLetter.put('D',  2);
		valueByLetter.put('E',  1);
		valueByLetter.put('F',  4);
		valueByLetter.put('G',  2);
		valueByLetter.put('H',  4);
		valueByLetter.put('I',  1);
		valueByLetter.put('J',  8);
		valueByLetter.put('K',  5);
		valueByLetter.put('L',  1);
		valueByLetter.put('M',  3);
		valueByLetter.put('N',  1);
		valueByLetter.put('O',  1);
		valueByLetter.put('P',  3);
		valueByLetter.put('Q', 10);
		valueByLetter.put('R',  1);
		valueByLetter.put('S',  1);
		valueByLetter.put('T',  1);
		valueByLetter.put('U',  1);
		valueByLetter.put('V',  4);
		valueByLetter.put('W',  4);
		valueByLetter.put('X',  8);
		valueByLetter.put('Y',  4);
		valueByLetter.put('Z', 10);
	}
	
	
	// 0 = normal, 1 = pink, 2 = red, 3 = light blue, 4 = dark blue,
	// 5-9 = same meaning but to be scored and cleared
	private int[][] boardvalue = {
		{2, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 3, 0, 0, 2},
		{0, 1, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 1, 0},
		{0, 0, 1, 0, 0, 0, 3, 0, 3, 0, 0, 0, 1, 0, 0},
		{3, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 3},
		{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
		{0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0},
		{0, 0, 3, 0, 0, 0, 3, 0, 3, 0, 0, 0, 3, 0, 0},
		{2, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 2},
		{0, 0, 3, 0, 0, 0, 3, 0, 3, 0, 0, 0, 3, 0, 0},
		{0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0},
		{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
		{3, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 3},
		{0, 0, 1, 0, 0, 0, 3, 0, 3, 0, 0, 0, 1, 0, 0},
		{0, 1, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 1, 0},
		{2, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 3, 0, 0, 2},
	};
	
	private char[][] board = new char[15][15];
	
	
	protected void runOnce() {
		int col = io.readLine().charAt(0) - 'A';
		int row = io.readIntLine() - 1;
		boolean horz = io.readLine().equals("ACROSS");
		String newletters = io.readLine();
		int dx = horz ? 1 : 0;
		int dy = horz ? 0 : 1;
		
		int score = 0;
		for (int i = 0; i < newletters.length(); row += dy, col += dx) {
			if (board[row][col] == 0) {
				board[row][col] = newletters.charAt(i);
				boardvalue[row][col] += 5;
				i++;
			}
		}
		if (horz) {
			score += scoreHorizontal(col - 1, row);
			for (int x = 0; x < boardvalue[row].length; x++) {
				if (boardvalue[row][x] >= 5)
					score += scoreVertical(x, row);
			}
		} else {
			score += scoreVertical(col, row - 1);
			for (int y = 0; y < boardvalue.length; y++) {
				if (boardvalue[y][col] >= 5)
					score += scoreHorizontal(col, y);
			}
		}
		if (newletters.length() == 7)
			score += 50;
		
		io.println(score);
		
		// Clear premium squares used in this turn
		for (int y = 0; y < boardvalue.length; y++) {
			for (int x = 0; x < boardvalue[y].length; x++) {
				if (boardvalue[y][x] == 5)
					boardvalue[y][x] = 0;
			}
		}
	}
	
	
	private int scoreHorizontal(int x, int y) {
		int start, end;
		for (start = x; start - 1 >= 0 && board[y][start - 1] != 0; start--);
		for (end = x; end + 1 < board[y].length && board[y][end + 1] != 0; end++);
		if (start == end)
			return 0;
		
		int score = 0;
		int wordmult = 1;
		for (int i = start; i <= end; i++) {
			int letterscore = valueByLetter.get(board[y][i]);
			switch (boardvalue[y][i]) {
				case 6 -> wordmult *= 2;
				case 7 -> wordmult *= 3;
				case 8 -> letterscore *= 2;
				case 9 -> letterscore *= 3;
			}
			score += letterscore;
			if (boardvalue[y][i] >= 5)
				boardvalue[y][i] = 5;
		}
		score *= wordmult;
		return score;
	}
	
	
	private int scoreVertical(int x, int y) {
		int start, end;
		for (start = y; start - 1 >= 0 && board[start - 1][x] != 0; start--);
		for (end = y; end + 1 < board.length && board[end + 1][x] != 0; end++);
		if (start == end)
			return 0;
		
		int score = 0;
		int wordmult = 1;
		for (int i = start; i <= end; i++) {
			int letterscore = valueByLetter.get(board[i][x]);
			switch (boardvalue[i][x]) {
				case 6 -> wordmult *= 2;
				case 7 -> wordmult *= 3;
				case 8 -> letterscore *= 2;
				case 9 -> letterscore *= 3;
			}
			score += letterscore;
			if (boardvalue[i][x] >= 5)
				boardvalue[i][x] = 5;
		}
		score *= wordmult;
		return score;
	}
	
}
