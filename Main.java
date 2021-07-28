import blackjack.*;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Deck deck1 = new Deck().create().shuffle();
        HumanPlayer player = new HumanPlayer("sucker");
        CpuPlayer dealer = new CpuPlayer("dealer").setTryAlwaysWin(true);
        int result[] = {0, 0, 0};
        //           wins losses draws

        while (true) {
            player.draw(deck1);
            player.draw(deck1);

            dealer.draw(deck1);

            while (player.play(deck1));
            while (dealer.play(deck1, player));

            System.out.print( (Player.getWinnerGeneralPerspective(player, dealer) == null)?
                    "Draw. " :
                    Player.getWinnerGeneralPerspective(player, dealer).name + " won this round. ");
            System.out.print( player.name + ' ' + Player.getResultFromPlayerPerspective(player, dealer).name().toLowerCase() + ".\n");

            result[Player.getResultFromPlayerPerspective(player, dealer).value]++;

            deck1.rewind().moveCards(player.getPlayerDeck()).moveCards(dealer.getPlayerDeck());
            player.rewind();
            dealer.rewind();

            boolean escape = true;
            while (escape) {
                System.out.print("Won: " + result[0] + "; Lost: " + result[1] + "; Tied " + result[2] + ". Continue? [Y/n]: ");
                char option = new Scanner(System.in).next().charAt(0);
                switch (option) {
                    case 'N':
                    case 'n':
                        return;

                    case 'Y':
                    case 'y':
                        escape = false;
                        break;

                    default:
                        System.out.print("Try again.\n");
                }
            }
        }
    }
}
