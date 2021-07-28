package blackjack;

public class Player {
    public Player(){
        this.name = "Player " + ++playersCount;
    }

    public Player(String nameIn){
        playersCount++;
        this.name = nameIn;
    }

    public Player draw(Deck deck){
        Card tempCard = deck.draw();

        aceParser(tempCard);
        playerDeck.add(tempCard);

        return this;
    }

    protected void aceParser(Card card){
        if(card.rank != Card.CardRank.ace)
            handValue += card.value();
        else{
            handValue += 11;
            swapOfAceValueRemaining++;
        }

        if( handValue > 21 && swapOfAceValueRemaining > 0){
            handValue -= 10;
            swapOfAceValueRemaining--;
        }
    }

    public int score(){
        return handValue;
    }

    public boolean isBust(){
        return (handValue > 21);
    }

    public Deck getPlayerDeck(){
        return playerDeck;
    }

    public String toString(){
        return (name + (ifAdd_S_AtEndOfApostrophe(name) ? "'s" : "'") + " score: " + handValue + ". On hand: " + playerDeck) ;
    }

    public Player rewind(){
        handValue = 0;
        swapOfAceValueRemaining = 0;

        return this;
    }

    protected boolean ifAdd_S_AtEndOfApostrophe(String string){
        return (string.charAt(string.length() - 1) != 's');
    }

    public static int getPlayersCount(){
        return playersCount;
    }
    public static Results getResultFromPlayerPerspective(Player mainPlayer, Player... othersPlayers){

        boolean isThereADrawWithYou = false;


        //RULE: doesn't matter score's values if score() > 21, you lose (or draw if everyone is bust, but it should never happen)
        for(Player targetPlayer : othersPlayers) {

            if (!mainPlayer.isBust()) { //check if win
                if (targetPlayer.handValue > mainPlayer.handValue && !targetPlayer.isBust())//target has higher score AND is not bust, you instant lose
                    return Results.LOSE;

                else if (targetPlayer.handValue == mainPlayer.handValue)//draw, return draw if there is nobody higher than you
                    isThereADrawWithYou = true;

                else
                    continue;
            }
            else{
                if(!targetPlayer.isBust()) //you're bust AND target is not bust, you lose
                    return Results.LOSE;

                else{ //target is bust, either. Draw.
                    isThereADrawWithYou = true;
                }
            }
        }

        return isThereADrawWithYou? Results.DRAW: Results.WIN;
    }

    public static Player getWinnerGeneralPerspective(Player... players){
        Player winner = null;
        int winnerScore = -1;

        for(Player target : players){
            if(winnerScore < target.handValue && !target.isBust()) {
                winner = target;
                winnerScore = target.handValue;
            }
            else if(winnerScore == target.handValue)
                winner = null;
            else //not winner
                continue;
        }

        return winner;
    }


    public enum Results{
        WIN (0),
        LOSE (1),
        DRAW (2);

        Results(int result){
            this.value = result;
        }

        public final int value;
    }

    protected int handValue = 0;
    protected byte swapOfAceValueRemaining = 0; //up to four, as there are 4 aces at a deck, by default
    public final String name;
    protected Deck playerDeck = new Deck();
    private static int playersCount = 0;
}
