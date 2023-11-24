import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args) {
        // init.
        Scanner sc = new Scanner(System.in);
        Deck theDeck = new Deck(1,true);

        // init the player objects.
        Player me = new Player("Adkham");
        Player dealer = new Player("Dealer");

        me.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());
        me.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());

        boolean meDone = false;
        boolean dealerDone = false;
        String ans;

        // player's turn.
        while(!meDone || !dealerDone) {
            System.out.print("Hit or Stay? (Enter H or S) : ");
            ans = sc.next();
            System.out.println();
            // if the player hits
            if (ans.compareToIgnoreCase("H") == 0) {
                // add next card in the deck and store weather player is busted.
                meDone = !me.addCard((theDeck.dealNextCard()));
                dealer.printHand(true);
            } else {
                meDone = true;
            }

            // daler's turn
            if(!dealerDone){
                if(dealer.getHandSum() < 17){
                    System.out.println("The Dealer hits\n");
                    dealerDone = !dealer.addCard(theDeck.dealNextCard());
                    dealer.printHand(false);
                }else {
                    System.out.println("The Dealer stays\n");
                    dealerDone = true;
                }
            }
            System.out.println();
        }
        sc.close();
        me.printHand(true);
        dealer.printHand(true);

        int mySum = me.getHandSum();
        int dealerSum = dealer.getHandSum();

        if(mySum > dealerSum && mySum < 21 || dealerSum > 21){
            System.out.println("You Win!");
        }else{
            System.out.println("Dealer Wins!");
        }
    }
}
