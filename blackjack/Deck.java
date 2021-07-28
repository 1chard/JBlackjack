package blackjack;
import java.util.Iterator;

public class Deck implements Iterable<Card>, Iterator<Card>{
    public Deck create(){
        for(Card.CardSuit suit : Card.CardSuit.values()){
            for(Card.CardRank rank : Card.CardRank.values()){
                array[length++] = new Card(rank, suit);
            }
        }

        return this;
    }

    public Deck shuffle(int timesToShuffle){
        int randomNumber = 0;
        Card temp = null;

        for(int i=0; i<timesToShuffle; ++i){
            randomNumber = (int)(Math.random() * 52);

            if(randomNumber == 52 || randomNumber == 0)
                continue;

            temp = array[0];
            array[0] = array[randomNumber];
            array[randomNumber] = temp;
        }

        return this;
    }

    public Deck shuffle(){
        shuffle(200); //should be random enough

        return this;
    }

    public String toString(){
        String toReturn = "";

        for(int i=0; i < length; ++i){
            if(array[i] != null)
                toReturn = toReturn + ' ' + array[i].toString();
            else
                toReturn = toReturn + " XX";
        }

        return toReturn.trim();
    }

    public Deck print(){
        System.out.print(toString());

        return this;
    }

    public Deck rewind(){

        if(drawIndex > 0) {
            for (int i = drawIndex; i < 52; ++i) {
                array[i - drawIndex] = array[i];
                array[i] = null;
            }
        }

        length -= drawIndex;
        drawIndex = 0;
        forEachIndex = 0;

        return this;
    }

    public Card draw() throws ExceptionSolitaire{
        try{
            Card copy = array[drawIndex];
            array[drawIndex] = null;
            ++drawIndex;
            ++forEachIndex;
            return copy;
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            int copy = drawIndex - 1;
            throw new ExceptionSolitaire("Card " + copy + " is out of index");
        }
    }

    public Deck add(Card card){
        array[length++] = card;
        return this;
    }

    public Deck moveCards(Deck deckToGetCards){
        for(Card card : deckToGetCards)
            array[length++] = card;

        deckToGetCards.clear();

        return this;
    }

    public Deck clear(){
        for(int i=0; i < 52; ++i)
            array[i] = null;

        length = 0;
        forEachIndex = 0;
        drawIndex = 0;

        return this;
    }

    public int getLength(){
        return length;
    }

    private int drawIndex = 0;
    private int length = 0;
    private Card array[] = new Card[52];



    @Override
    public Iterator<Card> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (forEachIndex < length)
            return true;
        else {
            forEachIndex = drawIndex;
            return false;
        }
    }

    @Override
    public Card next() {
        return array[forEachIndex++];
    }

    private int forEachIndex = 0;
}
