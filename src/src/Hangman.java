package src;
/**
 * Eric Tolman
 * Cody Bronson
 * Samson Nelson
 * 
 * Java 1410
 * Team Assignment
 * Fall 2016
 * 
 * Hangman
 */

// Whats ups
import java.util.Scanner;

public class Hangman {

	// Explode method
	public static String explode(String word) {
		String explode;
		explode = word.replaceAll(".(?=.)", "$0 ");
		return explode;
	}

	// Word string
	static String[] wordList = { "javascript" };

	static int randomWord;
	static String word;
	// Error Count
	static int count = 0;

	public static void main(String[] args) {

		// Declaring Variables
		boolean endGame = false;
		char now;
		String response;
		char letter;
		String letterUsed;
		char[] wordChar;
		Scanner scan = new Scanner(System.in);

		do {
			// Defining Variables and alphabet Array
			wordChar = randomPickWord();
			letterUsed = " ";
			char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
					'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
			String alphabetString = new String(alphabet);
			response = " ";
			String word1 = explode(word);

			// Replacing correct letters on the word
			while (word.equals(String.valueOf(wordChar)) == false
					&& (response.toLowerCase().equals("exit") == false & count != 6)) {
				drawMan(count);
				System.out.println();
				for (int i = 0; i < word.length(); i++) {
					now = word.charAt(i);
					if (now != 32 && wordChar[i] == 32) {
						System.out.print(" _ ");
					} else if (now != 32 && wordChar[i] != 32) {
						System.out.print(" " + wordChar[i] + " ");
					} else {
						System.out.print(" ");
					}
				}
				// Letter Entry
				String alphabet1 = alphabetString.replaceAll(letterUsed, "_");
				System.out.println("\nPick a letter: \t\t" + letterUsed);
				response = scan.next();
				letter = response.toLowerCase().charAt(0);

				if (word.replace(letter, (char) 48) == word && letterUsed.replace(letter, (char) 48) == letterUsed) {
					count = count + 1;
					letterUsed = letter + " " + letterUsed;
				}

				else if (word.replace(letter, (char) 48) == word
						&& letterUsed.replace(letter, (char) 48) != letterUsed) {
					System.out.println("That letter has already been used. ");
				}

				for (int i = 0; i < word.length(); i++) {
					if (word.toLowerCase().charAt(i) == letter)
						wordChar[i] = word.charAt(i);

					if (response.equals("exit") == true)
						System.exit(0); // end the program
				}
			}

			// End of game
			if (response.toLowerCase().equals("exit") == false && word.equals(String.valueOf(wordChar)) == false) {
				drawMan(6);
				System.out.println("\nYou are dead. \nType exit to quit, or 1 to continue.");
				count = 0;
			} else if (word.equals(String.valueOf(wordChar)) == true) {

				System.out.println("You win! \nType exit to quit, or 1 to continue.");
			}

			// Show word
			System.out.println("The word was: " + word1);
			response = scan.next();
			if (response.equals("exit"))
				endGame = true;
		} while (endGame == false);
	}

	// Randomizing the word
	private static char[] randomPickWord() {

		randomWord = (int) ((Math.random() * (wordList.length)) + 1);
		int lengthWord = wordList[randomWord - 1].length();
		char[] wordChar = new char[lengthWord];
		for (int i = 0; i < lengthWord; i++) {
			wordChar[i] = 32;
		}
		word = wordList[randomWord - 1];

		return wordChar;
	}

	// Drawing the Hangman
	private static void drawMan(int x) {

		System.out.print("________");
		if (x > 6)
			System.out.print("\n|      |");
		System.out.print("\n|");

		if (x > 0)
			System.out.print("      0");
		System.out.print("\n|");
		if (x > 1)
			System.out.print("     \\|/");
		System.out.print("\n|");
		if (x > 2)
			System.out.print("      |");
		if (x < 6)
			System.out.print("\n|");
		if (x > 3 && x < 6)
			System.out.print("     /");
		if (x > 4 && x < 6)
			System.out.print(" \\");
		if (x < 6)
			System.out.print("\n==============");

		if (x > 5) {
			System.out.print("\n===== / \\ ===");
		}
	}
}