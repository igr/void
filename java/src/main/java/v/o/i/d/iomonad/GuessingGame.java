package v.o.i.d.iomonad;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

	private static final PrintStream out = System.out;
	private static final InputStream in = System.in;

	public static void main(String[] args) {
		final var game = IoMonad
				.of(() -> out.println("Welcome to the Guessing Game!"))
				.bind(ignored -> generateRandomNumber())
				.map(number -> number + 1)  // [0, 100) -> [1, 100]
				.bind(number -> IoMonad.of(() -> {
					out.println("I'm thinking of a number between 1 and 100.");
					return number;
				}))
				.bind(GuessingGame::guessLoop);

		try {
			game.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Generates a number [0, 100)
	private static IoMonad<Integer> generateRandomNumber() {
		return IoMonad.of(() -> new Random().nextInt(100));
	}

	private static IoMonad<Void> guessLoop(int number) {
		return IoMonad
				.of(() -> out.print("Take a guess: "))
				.bind(ignored -> readUserGuess())
				.bind(guess -> checkUserInput(guess, number));
	}

	private static IoMonad<String> readUserGuess() {
		return IoMonad.of(() -> new Scanner(in).nextLine());
	}

	private static IoMonad<Void> checkUserInput(String guess, int number) {
		return IoMonad
				.of(() -> {
					try {
						return Integer.parseInt(guess);
					} catch (NumberFormatException e) {
						return null;    // well...
					}
				})
				.bind(userGuess -> {
					if (userGuess == null) {
						return IoMonad
								.of(() -> out.println("Please enter a valid number."))
								.bind(ignored -> guessLoop(number));
					} else {
						return checkGuess(userGuess, number);
					}
				});
	}

	private static IoMonad<Void> checkGuess(int guess, int number) {
		if (guess < number) {
			return IoMonad
					.of(() -> out.println("Too low! Try again."))
					.bind(ignored -> guessLoop(number));
		} else if (guess > number) {
			return IoMonad
					.of(() -> out.println("Too high! Try again."))
					.bind(ignored -> guessLoop(number));
		} else {
			return IoMonad.of(() -> out.println("Congratulations! You guessed the right number!"));
		}
	}
}
