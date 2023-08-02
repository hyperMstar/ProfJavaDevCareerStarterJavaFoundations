import java.util.Random;

public class GuessIt {
    public static void main(String[] args) {
        int randomNum = new Random().nextInt(10) +1;
        String guessedNumText = null;
        int wrongGuessCount = 0;
        do {
            guessedNumText = System.console().readLine("Please guess a number between 1 and 10 inclusively: ");
            if (guessedNumText.matches("-?\\d{1,2}")) {
                int guessedNum = Integer.parseInt(guessedNumText);
                if(guessedNum == randomNum) {
                    String tryText = wrongGuessCount == 1 ? "try" : "tries";
                    System.out.printf("The random number was %d. You got it in %d %s! %n", randomNum, wrongGuessCount, tryText);
                    return;
                }
                else {
                    wrongGuessCount++;
                    System.out.printf("You didn't get it!%n");
                    if(wrongGuessCount >= 4) {
                        System.out.printf("You've had %d incorrect guesses. The random number is %d. Game Over! %n", wrongGuessCount, randomNum);
                        return;
                    }
                }
            }
        } while (!"q".equals(guessedNumText));

    }
}
