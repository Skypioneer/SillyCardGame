package cwang_P3X.cwang_P3X;

import java.util.Scanner;   // Needed for Scanner object

/**
 * This program executes the the SillyCardGame as many times as the user wanna
 * and allows the user to decide a number of players.
 *
 * @author Jason Wang
 * @version 102.0
 */
public class SillyCardGame {

    /**
     * The main method executes three main methods to process the game.
     *
     * @param args A string array containing the command line arguments.
     */
    public static void main(String[] args){

        // Call the welcome method
        welcome();

        // Call the mainGame method
        mainGame();

        // Call the goodbye method
        goodbye();
    }

    /**
     * The mainGame method executes main processes of the game.
     */
    public static void mainGame() {

        int num;            // A variable that holds the number of players
        boolean winner;     // Check whether there is a winner

        // Create GameModel object
        GameModel game;

        // Create Scanner object
        Scanner keyboard = new Scanner(System.in);

        // Check whether there is a winner
        do {
            // Assign the number of players to num
            num = playerNum(keyboard);

            // Assign new GameModel(num) to game
            game = new GameModel(num);

            // Push a popped card from popDealStack to discardStack.
            game.discardStack.push(game.popDealStack());

            // Check if there is a winner
            do {
                winner = loops(game, num);
            }
            while (!winner);

        }while (!repeat(keyboard));

        // Close Scanner object
        keyboard.close();
    }

    /**
     * THe loops method check whole rounds of the game.
     *
     * @param game The GameModel object.
     * @param num The number of players.
     * @return Whether there is a winner.
     */
    public static boolean loops(GameModel game, int num) {

        // Rounds of the game
        for (int i = 0; i < num; i++) {

            System.out.printf("%s's turn, cards:\n", game.getPlayerNames(i));
            System.out.println(game.toString(i));

            System.out.printf("Discard pile card: %s\n",
                                                    game.peekDiscardStack());

            // Assign current card to dequeueCurrentCard
            int dequeueCurrentCard = game.dequeueCurrentCard(i);

            System.out.printf("Your current card: %s\n", dequeueCurrentCard);

            // Check for punishment
            if (dequeueCurrentCard < game.peekDiscardStack()) {
                System.out.println("Your card is LOWER, pick up 2 cards.");
                game.discardStack.push(dequeueCurrentCard);
                game.enqueueCards(i, game.popDealStack());
                game.enqueueCards(i, game.popDealStack());
            } else if (dequeueCurrentCard == game.peekDiscardStack()) {
                System.out.println("Both cards are equal, pick up 1 cards.");
                game.discardStack.push(dequeueCurrentCard);
                game.enqueueCards(i, game.popDealStack());
            } else {
                System.out.println("Your card is HIGHER, turn is over.");
                game.discardStack.push(dequeueCurrentCard);
            }

            // Winner occurs
            if (game.playIsEmpty(i)) {

                System.out.println("You have won the game!\n\n" +
                                                    "The game has finished.");
                return true;
            }

            System.out.println();

        }

        return false;
    }

    /**
     * The playerNum method prompts for a number of players.
     *
     * @param keyboard The Scanner object.
     * @return The number of players.
     */
    public static int playerNum(Scanner keyboard) {
        int playerNum;  // The variable that holds a number of players

        // Check whether the user's input is out of bound
        do {
            System.out.print("How many player(2~6)? ");
            playerNum = keyboard.nextInt();

            if (playerNum < 2 || playerNum > 6)
                System.out.println("Invalid number. Try again.");

        }while (playerNum < 2 || playerNum > 6);

        // Return the number
        return playerNum;
    }

    /**
     * The repeat method ask if the user wanna repeat.
     *
     * @param keyboard The Scanner object.
     * @return Whether the user wanna repeat.
     */
    public static boolean repeat(Scanner keyboard){
        String ans;     // An answer whether the user wanna repeat
        char letter;    // The first letter of the ans

        // Consume the blank line
        keyboard.nextLine();

        // Prompt for playing the game again
        System.out.print("\nPlay again? ");
        ans = keyboard.nextLine();
        letter = ans.charAt(0);

        // Check whether letter is n or N
        return letter == 'n' || letter == 'N';
    }

    /**
     * The welcome method displays welcome message.
     */
    public static void welcome(){
        System.out.println("Welcome to Silly Card Game!\n");
    }

    /**
     * The goodbye method displays goodbye message.
     */
    public static void goodbye(){
        System.out.println("\nThanks for playing Silly Card Game!");
    }
}