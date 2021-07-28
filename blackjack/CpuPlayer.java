package blackjack;

public class CpuPlayer extends Player{
    public CpuPlayer(){
        super("CPU " + ++cpuCount);
    }

    public CpuPlayer(String nameIn){
        super(nameIn);
        ++cpuCount;
    }

    public CpuPlayer setTryAlwaysWin(boolean value){
        isTryAlwaysWin = value;

        return this;
    }

    public CpuPlayer setAtLeastHandValue(int value){
        atLeastHandValue = value;

        return this;
    }

    public boolean play(Deck deck, Player... players){
        System.out.print(this + ".\n");

        //check if all other players are bust
        {
            boolean otherAreBust = true;

            for(Player player: players) {
                if(!player.isBust()) {
                    otherAreBust = false;
                    break;
                }
            }

            if(otherAreBust)
                return false;
        }

        int highest = 0;
        for(Player player : players){
            if(player.handValue > highest)
                highest = player.handValue;
        }

        /* //TODO discover why i wrote this below
        if(highest < playerDeck.getLength())
            return false;

         */

        if(handValue == 21) {
            System.out.print(name + " got Blackjack!\n");
            return false;
        }
        else if(playerDeck.getLength() < 5 && !isBust()){
            if(handValue < atLeastHandValue && handValue < highest) {
                    draw(deck);

                    return true;
            }
            else if(isTryAlwaysWin) {
                for (Player player : players) {
                    if (handValue < player.handValue || handValue < highest) {
                        draw(deck);

                        return true;
                    }
                }
            }
        }
        else{
            if(isBust())
                System.out.print(name + (ifAdd_S_AtEndOfApostrophe(name)? "'s" : "'") + " hand is dead.\n");
            if(playerDeck.getLength() >= 5)
                System.out.print("Card draws limit beaten.\n");
            return false;
        }

        return false;
    }

    public static int getCpuCount(){
        return cpuCount;
    }

    protected boolean isTryAlwaysWin = false;
    protected int atLeastHandValue = 17;
    protected static int cpuCount = 0;
}
