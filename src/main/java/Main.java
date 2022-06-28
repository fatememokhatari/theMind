import game.Game;
import game.Second;
import models.Bot;
import models.Card;
import models.Human;

import java.util.Collections;

public class Main {


    public static void main(String[] args) throws InterruptedException {



        for (int i = 1; i <=100 ; i++) {
            new Card(i);
        }

        Human human =new Human("fateme");

        Game.players.add(human);
        Collections.shuffle(Card.cards);
       // human.getCards().add(Card.cards.get(0));
        Bot b = new Bot("tohidi");
        Bot b1 = new Bot("mmd");
        Bot b2 = new Bot("zari");
       // b.getCards().add(Card.cards.get(1));
       // System.out.println(b.getCards().get(0)+"b1");
        //b1.getCards().add(Card.cards.get(2));
       // System.out.println(b1.getCards().get(0)+"b2");
        Game.players.add(b);
        Game.players.add(b1);
        Game.players.add(b2);

        GUI.MainFrame frame=new GUI.MainFrame();

        //Game game=new Game(frame);



    }

}
