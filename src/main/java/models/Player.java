package models;

import game.Game;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private ArrayList<Card> cards = new ArrayList<>();
    String name;

    public Player(String name) {
        this.name = name;
    }

    public Card getMinimum() {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < this.cards.size(); i++) {
            ints.add(cards.get(i).getNumber());
        }
        Collections.sort(ints);



           int min = ints.get(0);


        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getNumber() == min) {
                return cards.get(i);
            }
        }

        return null;
    }


    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return " level= " + Game.level
                + "\n"
                + " ninja= " + Game.ninja
                + "\n"
                + " heart= " + Game.heart +
                "\n" +
                " tableCards= " + Game.table.toString() +
                "\n" +
                " humanCards= " + Game.players.get(0).getCards().toString() ;



    }

    public String getName() {
        return name;
    }
}
