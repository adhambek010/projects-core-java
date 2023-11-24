public class Player {
    /**
     * The name of player.
     */
    private String name;

    /**
     * The cards in the player's current hand.
     */
    private Card[] hand = new Card[10];

    /**
     * The number of cards  in the player's current hand.
     */
    private int numCards;

    /**
     * Creates a player object.
     * @param name  the name of the player.
     */
    public Player(String name) {
        this.name = name;

        // set a player's hand empty.
        this.emptyHand();
    }

    /**
     * Reset the player's hand to have no cards.
     */
    public void emptyHand(){
        for(int c = 0; c < 10; c++){
            this.hand[c] = null;
        }
        this.numCards = 0;
    }

    /**
     * Add a card to the player's hand/
     * @param aCard the card to add
     * @return      weather the sum of the new hand is below or equal.
     */
    public boolean addCard(Card aCard){

        // print error if we already have the max number of cards.
        if(this.numCards == 10){
            System.err.printf("%s's hand already has 10 cards; cannot add another\n", this.name);
            System.exit(1);
        }

        // add a new card in next slot and increment number of cards counter.
        this.hand[this.numCards] = aCard;
        this.numCards++;

        return (this.getHandSum() <= 21);
    }

    /**
     * The sum of the cards on player's hand.
     * @return  the sum.
     */
    public int getHandSum(){
        int hanSum = 0;
        int cardNum;
        int numAces = 0;
        // calc each card's contribution to the sum.
        for(int c = 0; c < this.numCards; c++){

            // get the number for the current card.
            cardNum = this.hand[c].getNumber();

            if(cardNum == 1){ // Ace
                numAces++;
                hanSum += 11;
            }else if(cardNum > 10){ // face card
                hanSum += 10;
            }else{
                hanSum +=cardNum;
            }
        }

        // if we have Aces and our sum > 21, set some /all of them value 1 instead.
        while (hanSum > 21 && numAces > 0){
            hanSum -= 10;
            numAces--;
        }
        return hanSum;
    }

    /**
     * Print the cards in the player's hand.
     * @param showFirstCard     weather the first card hidden or not.
     */
    public void printHand(boolean showFirstCard){
        System.out.printf("%s's cards : \n",this.name;
        for(int c = 0; c < this.numCards; c++){
            if(c == 0 && !showFirstCard){
                System.out.println(" [hidden]");
            }else{
                System.out.printf(" %s\n", this.hand[c].toString());
            }
        }
    }
}
