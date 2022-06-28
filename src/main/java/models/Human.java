package models;

import game.Game;
import game.Second;

import java.util.Scanner;

public class Human extends Player  implements Runnable{

    public Human(String name) {

        super(name);

    }



    public void ninja() {
        for (int i = 0; i < Game.players.size(); i++) {
            if (Game.players.get(i).getCards().size() > 0) {
                System.out.println(Game.players.get(i).name + " : " + Game.players.get(i).getMinimum().toString());
                Game.players.get(i).getCards().remove(Game.players.get(i).getMinimum());
            }
        }
    }


    @Override
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(toString());
                Scanner scanner = new Scanner(System.in);

                while (Game.isContinuing) {
                    int action = scanner.nextInt();
                    if (action == 0) {

                        Card c = getMinimum();
                        Game.table.add(c);
                        Second.timePass =0;
                        System.out.println(toString());


                    }
                    if (action == 1) {
                        ninja();
                    }
                    if (action == 2) {
                       //  Emoji.showEmoji(this, Emoji.HAPPY);
                    }
                    if (action == 3) {
                        //  Emoji.showEmoji(this, Emoji.SMILE);
                    }
                    if (action == 4) {
                        // Emoji.showEmoji(this, Emoji.POKER);
                    }

                }
            }
        });
    }
}
// 0 -> playCard
// 1 -> ninja
// 2 -> :)
// 3 -> :D
// 4 -> :/
