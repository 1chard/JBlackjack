package blackjack;

final public class Card {
    public Card(CardRank rankIn, CardSuit suitIn){
        this.rank = rankIn;
        this.suit = suitIn;
    }

    public enum CardRank {
        two (2, '2'),
        three (3, '3'),
        four (4, '4'),
        five (5, '5'),
        six (6, '6'),
        seven (7, '7'),
        eight (8, '8'),
        nine (9, '9'),
        ten (10, 'T'),
        joker (10, 'J'),
        queen (10, 'Q'),
        king (10, 'K'),
        ace (1, 'A');

        CardRank(int value, char symbol) {
            this.symbol = symbol;
            this.value = value;
        }

        public final int value;
        public final char symbol;
    }

    public enum CardSuit {
        clubs ('C'),
        diamonds ('D'),
        hearts ('H'),
        spades ('S');

        CardSuit(char symbol){
            this.symbol = symbol;
        }

        public final char symbol;
    }

    public String toString(){
        char characters[] = {rank.symbol, suit.symbol};
        return new String(characters);
    }

    public Card print(){
        System.out.print(toString());

        return this;
    }

    public int value(){
        return rank.value;
    }

    final public CardRank rank;
    final public CardSuit suit;
}
