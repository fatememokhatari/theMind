package models;

import java.util.ArrayList;

public class Card {
    private int number;
    public static ArrayList<Card> cards = new ArrayList<>();

    public Card(int number) {
        this.number = number;
        cards.add(this);
    }


    //GetterSetters:

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                '}';
    }
}
