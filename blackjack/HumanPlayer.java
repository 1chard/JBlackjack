package blackjack;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name){
        super(name);
        ++humanCount;
    }



    public boolean play(Deck deck){
        System.out.print(this + ".\n");


        if(handValue == 21){
            System.out.print(name + " got Blackjack!\n");
            return false;
        }
        else if(playerDeck.getLength() < 5 && !isBust()){
            System.out.print("Choose, stand or hit: ");

            String output = new Scanner(System.in).next().trim();

            if(output.equals("hit")){
                draw(deck);
                return true;
            }
            else if(output.equals("stand"))
                return false;
            else {
                System.out.printf("Unknown option: %s, try again.\n", output);
                return play(deck);
            }
        }
        else{
            if(isBust())
                System.out.print(name + (ifAdd_S_AtEndOfApostrophe(name)? "'s" : "'") + " hand is dead.\n");
            if(playerDeck.getLength() >= 5)
                System.out.print("Card draws limit beaten.\n");
            return false;
        }
    }

    public static int getHumanCount(){
        return humanCount;
    }

    protected static int humanCount = 0;
}
