package cz.gravelcz.skola;

import java.util.Scanner;

public class PythagoreanTripleSolver {

	private static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		solveTriple();
		generateTriples(10);
		s.close();
	}

	private static void solveTriple() {
		System.out.print("Zadej číslo c: ");
		
		int c = s.nextInt();
		if (c <= 0) {
			System.out.println("Řešení neexistuje.");
			return;
		}
		
		if (c % 4 != 1) { // pokud c != 4 * t + 1 kde t náleží N tak není řešení
			System.out.println("Řešení neexistuje.");
			return;
		}
		
		// n = sqrt(C - m^2) kde sqrt(c / 2) <= m <= sqrt(c)
		double min = Math.sqrt(c / 2);
		double max = Math.sqrt(c);
		
		for (int m = (int) min; m <= max; m++) {
			if (min <= m && m <= max) {
				double nd = Math.sqrt(c - m * m);
				int n = (int) nd;
				
				if (nd == n) {
					int a = m * m - n * n;
					int b = 2 * m * n;
					
					if (b == 0 || a == 0) {
						continue;
					}
					
					if (gcd(a, b, c) != 1) {
						continue;
					}
					
					if (m * m + n * n == c) {
						System.out.println(a + ", " + b + ", " + c);
						return;
					}
				}
			}
		}
		
		System.out.println("Řešení neexistuje.");
	}

	// gcd = greatest common divisor = největší společný dělitel
	// hádám že vstup nebude 0
	private static int gcd(int a, int b, int c) {
		if (a == 0 || b == 0 || c == 0) {
			return 0;
		}
		
		int gcd = 1;
		int min = Math.min(a, Math.min(b, c));
		for (int i = 1; i <= min; i++) {
			if (a % i == 0 && b % i == 0 && c % i == 0) {
				gcd = i;
			}
		}
		
		return gcd;
	}
	
	private static void generateTriples(int limit) {
		int a , b , c = 0;
		int m = 2;
		
		int count = 0;
		
		StringBuffer solutions = new StringBuffer();
		
		while (count < limit) {
			// Euclid's formula
			// řešení je libovolný pár celých čísel kde m > n > 0, tedy můžeme použít n < m
			// a = m^2 - n^2
			// b = 2 * m * n
			// c = m^2 + n^2
			// https://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple
			for (int n = 1; n < m; n++) {
				// 
				a = m * m - n * n;
				b = 2 * m * n;
				c = m * m + n * n;
				
				solutions.append("(" + a + ", " + b + ", " + c + ")").append(", ");
				count++;
			}
			m++;
		}
		
		System.out.println(solutions.toString());
	}

	
}
