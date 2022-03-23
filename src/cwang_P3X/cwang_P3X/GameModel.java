package cwang_P3X.cwang_P3X;

import java.util.ArrayList; // needed for ArrayList
import java.util.*; // Control every situation

/**
 * The Class GameModel executes all of the game logic.
 */
public class GameModel {

    // Create Class Player
    private static class Player {
        String name;                // The variable that holds a player's name

        // Create a Queue abject
        Queue <Integer> queue;

        /**
         * Constructor.
         */
        public Player() {

            queue = new Queue<>();
        }
    }

    // Create Player object
    private final Player[] PLAYER;

    // Create two Stack objects
    Stack<Integer> dealStack = new Stack<>();
    Stack<Integer> discardStack = new Stack<>();

    final int SEVEN = 7;    // The number of cards to players in the beginning

    /**
     * constructor.
     *
     * @param playerNumber The number of players
     */
    public GameModel(int playerNumber) {

        // Assign new Player[playerNumber] to player
        PLAYER = new Player[playerNumber];

        // Call assignPlayers method
        assignPlayers();

        // Call assignCards method
        assignCards();
    }

    /**
     * The assignPlayers method assigns names of players in player abject.
     */
    public void assignPlayers(){
        for (int i = 0; i < PLAYER.length; i++) {
            PLAYER[i] = new Player();
            PLAYER[i].name = "Player" + (i + 1);
        }

    }

    /**
     * The assignCards method assigns cards in player object.
     */
    public void assignCards() {

        // Call addCard, shuffleDeck, and dealStack method
        dealStack(shuffleDeck(addCard()));

        // Assigns cards in player object
        for (int i = 0; i < SEVEN; i++){
            for (Player value : PLAYER) {
                value.queue.enqueue(dealStack.pop());
            }
        }
    }

    /**
     * The addCard method adds 52 cards to ArrayList cards.
     *
     * @return The ArrayList cards.
     */
    public ArrayList<Integer> addCard() {

        final int COPY = 4;             // The copy number
        final int CARDNUMBER = 13;      // The number of cards

        // Create an ArrayList
        ArrayList<Integer> cards = new ArrayList<>();

        // Assign cards to ArrayList cards
        for (int i = 1; i <= COPY; i++) {
            for (int j = 1; j <= CARDNUMBER; j++) {
                cards.add(j);
            }
        }

        return cards;
    }

    /**
     * The shuffleDeck method shuffles the cards.
     *
     * @param cards The 52 cards without shuffling.
     * @return The shuffled cards.
     */
    private ArrayList<Integer> shuffleDeck(ArrayList<Integer> cards) {

        // Create an Random object
        Random rand = new Random();

        // shuffle cards
        for (int i = cards.size(); i > 1; i--) {
            int j = rand.nextInt(i);
            int temp = cards.get(i - 1);
            cards.set(i - 1, cards.get(j));
            cards.set(j, temp);
        }

        return cards;
    }

    /**
     * The dealStack method push cards to dealStack object.
     *
     * @param cards The shuffled cards.
     */
    public void dealStack(ArrayList<Integer> cards) {

        // Push cards to dealStack object
        for (int i = 0; i < cards.size(); i++) {
            dealStack.push(cards.get(i));
        }
    }

    /**
     * The enqueueCards enqueue cards to queue in player object.
     *
     * @param i The index for location in player object.
     *
     * @param num The number of a card.
     */
    public void enqueueCards(int i, int num) {
        int size;   // The size of discardStack
        int card;   // The top card in the discardStack

        // Enqueue num to queue in player  object.
        PLAYER[i].queue.enqueue(num);

        // Turn over dealStack and discardStack Stacks.
        if (dealStack.empty()){

            // Hold the top card in the discardStack
            card = discardStack.pop();

            // Assign discardStack to size
            size = discardStack.size();

            // Turn over
            for (int k = 0; k < size; k++) {
                dealStack.push(discardStack.pop());
            }

            // Placed The next card in the deal stack into the discard stack.
            discardStack.push(card);
        }
    }

    /**
     * The getPlayerNames returns the name of a player.
     *
     * @param i The index for location in player object.
     *
     * @return The name of a player.
     */
    public String getPlayerNames(int i) {

        return PLAYER[i].name;
    }

    /**
     * The toString method returns the cards that a player holds.
     *
     * @param i The index for location in player object.
     * @return The cards that a player holds.
     */
    public String toString(int i) {

        return PLAYER[i].queue.toString();
    }

    /**
     * The dequeueCurrentCard method returns the dequeued card.
     *
     * @param i The index for location in player object.
     * @return The dequeued card
     */
    public int dequeueCurrentCard(int i) {

        return PLAYER[i].queue.dequeue();
    }

    /**
     * The peekDiscardStack method returns the peeked card in discardStack.
     *
     * @return The peeked card in discardStack.
     */
    public int peekDiscardStack() {

        return discardStack.peek();
    }

    /**
     * The popDealStack method returns the popped card in dealStack.
     *
     * @return The popped card in dealStack.
     */
    public int popDealStack() {

        return dealStack.pop();
    }

    /**
     * The playerIsEmpty method return the boolean of whether the queue in the
     * player object is empty.
     *
     * @param i The index for location in player object.
     * @return The boolean of whether the queue in the player object is empty
     */
    public boolean playIsEmpty(int i) {

        return PLAYER[i].queue.empty();
    }
}
