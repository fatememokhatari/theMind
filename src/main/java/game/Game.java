package game;

import models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends JPanel {
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Bot> bots = new ArrayList<>();
    public static ArrayList<Integer> minimumBotsCards = new ArrayList<>();
    public static int level = 1;
    public static int heart=4;
    public static int ninja = 2;
    public static boolean isContinuing = true;
    public static ArrayList<Card> table = new ArrayList<>();
    GUI.MainFrame frame;
    Game g ;
    int minNum;
    JButton throwCard, ninjaCard, information, smile, happy, poker,myCards;
    JLabel ninjaLabel, heartLabel, levelLabel;
    JTextField ninjaText, heartText, levelText;


    public Game(GUI.MainFrame frame) {
        Collections.shuffle(Card.cards);
        for (int i = 0; i <players.size() ; i++) {
            for (int j = 0; j <level ; j++) {
                players.get(i).getCards().add(Card.cards.get(i*level+j));
            }
        }

        if(level==13){
            JOptionPane.showMessageDialog(null,"تبریک =)) بردین!");
        }
        if (level==1){Event.events.add("level : "+level);}

        new Second();
        this.frame = frame;

        initComponents();
        initPanel();
        actionListener();


       new Thread(new Runnable() {
           @Override
           public void run() {
               boolean b = false;
               for (int i = 0; i < players.size(); i++) {
                   if (players.get(i).getCards().size() != 0) {
                       b = true;
                   }
               }
               while (b) {
                   bots.clear();
                   minimumBotsCards.clear();
                   for (int i = 0; i < players.size(); i++) {
                       if (players.get(i) instanceof Bot) {
                           bots.add((Bot) players.get(i));
                           if (players.get(i).getCards().size() > 0) {
                               minimumBotsCards.add(players.get(i).getMinimum().getNumber());
                           }
                       }
                   }
                   Collections.sort(minimumBotsCards);
                   if (minimumBotsCards.size() == 0) {
                       continue;
                   }
                   minNum = minimumBotsCards.get(0);

                   if (minNum <= Second.timePass) {
                       if (table.size()>0) {
                           int x = table.get(table.size() - 1).getNumber();
                           if (x < minNum) {
                               table.add(getCard(minNum));
                           } else {
                               heart--;
                               Event.showEvent(getPlayer(minNum),Emoji.POKER);
                               if (heart == 0) {
                                   JOptionPane.showMessageDialog(null, "باختین:(!");
                                   Event.showEvent(getPlayer(minNum),Emoji.POKER);
                                   System.exit(0);
                                   break;
                               }
                               table.add(0, getCard(minNum));
                               //Event.events.add("lost heart ...");
                           }
                       }else {
                           table.add(getCard(minNum));
                       }
                       Player p = getPlayer(minNum);
                        Event.events.add(p.getName()+" -> "+getCard(minNum));
                       p.getCards().remove(getCard(minNum));
                       Second.timePass = 0;


                   }


                   b = false;
                   for (int i = 0; i < players.size(); i++) {
                       if (players.get(i).getCards().size() != 0) {
                           b = true;
                       }
                   }
               }
               level++;Event.events.clear();
               Event.events.add("level : "+level);
               ;changeLevel();update();table.clear();new Game(frame);
           }
       }).start();








    }


    private void initPanel() {
        this.setBounds(0, 0, 800, 800);
        this.setVisible(true);
        setLayout(null);
    }


    void initComponents() {
        throwCard = new JButton("انداختن کارت");
        throwCard.setBounds(350, 100, 100, 30);
        this.add(throwCard);
        ninjaCard = new JButton(" کارت نینجا");
        ninjaCard.setBounds(350, 150, 100, 30);
        this.add(ninjaCard);
        information = new JButton(" اتفاقات ");
        information.setBounds(350, 200, 100, 30);
        this.add(information);
        smile = new JButton(" :D ");
        smile.setBounds(350, 250, 100, 30);
        this.add(smile);
        happy = new JButton(" :) ");
        happy.setBounds(350, 300, 100, 30);
        this.add(happy);
        poker = new JButton(" :/ ");
        poker.setBounds(350, 350, 100, 30);
        this.add(poker);

        ninjaLabel = new JLabel("  <-  نینجا  ");
        ninjaLabel.setBounds(50, 50, 70, 30);
        this.add(ninjaLabel);
        ninjaText = new JTextField(String.valueOf(ninja));
        ninjaText.setBounds(120, 50, 70, 30);
        this.add(ninjaText);
        heartLabel = new JLabel(" <- قلب  ");
        heartLabel.setBounds(210, 50, 70, 30);
        this.add(heartLabel);
        heartText = new JTextField(String.valueOf(heart));
        heartText.setBounds(280, 50, 70, 30);
        this.add(heartText);
        myCards=new JButton("کارت های من");myCards.setBounds(350,450,100,30);
        this.add(myCards);


    }

    void actionListener() {
        smile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event.showEvent(players.get(0), Emoji.SMILE);
            }
        });
        happy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event.showEvent(players.get(0), Emoji.HAPPY);
            }
        });
        poker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event.showEvent(players.get(0), Emoji.POKER);
            }
        });
        ninjaCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ninja > 0) {
                    for (int i = 0; i < players.size(); i++) {
                        if (players.get(i).getCards().size() > 0) {
                            Event.events.add("******"+players.get(i).getName() + " -> " + Game.players.get(i).getMinimum().toString());

                            players.get(i).getCards().remove(players.get(i).getMinimum());

                        }
                    }Event.events.add("");
                    ninja--;
                   boolean b = false;
                    for (int i = 0; i < players.size(); i++) {
                        if (players.get(i).getCards().size() != 0) {
                            b = true;
                        }
                    }
                    if (b==false){level++ ;Event.events.clear(); Event.events.add("level : "+level);table.clear();changeLevel();update();new Game(frame);}
                    Second.timePass=0;
                    update();
                }else {JOptionPane.showMessageDialog(null, "نینجا نداری !:((");}

            }
        });
        throwCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (players.get(0).getCards().size() > 0) {
                    Card c = players.get(0).getMinimum();
                    Second.timePass = 0;
                    Event.events.add(  players.get(0).getName() + " -> " + Game.players.get(0).getMinimum().toString());
                    if (table.size() > 0) {
                        if (c.getNumber() > table.get(table.size() - 1).getNumber()) {

                            Game.table.add(c);
                        } else {

                            table.add(0, c);
                            heart--;
                            update();
                            JOptionPane.showMessageDialog(null, "قلب تون سوخت !:((");
                        }
                    } else {
                        table.add(c);

                    }
                    players.get(0).getCards().remove(c);
                    boolean b = false;
                    for (int i = 0; i < players.size(); i++) {
                        if (players.get(i).getCards().size() != 0) {
                            b = true;
                        }
                    }
                    if (b==false){level++ ;Event.events.clear(); Event.events.add("level : "+level);table.clear();changeLevel();update();new Game(frame);}
                    Second.timePass=0;
                    update();
                }else {
                    JOptionPane.showMessageDialog(null, " کارت نداری :((");
                }
            }
        });
        information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowEvent s =new ShowEvent();
            }
        });
        myCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HumanCards h =new HumanCards((Human) players.get(0));
            }
        });
    }


    public Player getPlayer(int min) {
        for (int i = 0; i < bots.size(); i++) {
//            if (bots.get(i).getCards().size()>0&&bots.get(i).getMinimum().getNumber() == min) {
//                return bots.get(i);
           // }
            for (int j = 0; j <bots.get(i).getCards().size() ; j++) {
                if (min==bots.get(i).getCards().get(j).getNumber()){
                    return bots.get(i);
                }
            }
        }
        System.out.println("null");
        return null;
    }

    public Card getCard(int x) {
        for (int i = 0; i < bots.size(); i++) {
            if (bots.get(i).getCards().size()>0&&bots.get(i).getMinimum().getNumber() == x) {
                return bots.get(i).getMinimum();
            }
        }
        return null;
    }

    void update() {
        ninjaText.setText(String.valueOf(ninja));
        heartText.setText(String.valueOf(heart));
        levelText.setText(String.valueOf(level));
        if (heart == 0) {
            JOptionPane.showMessageDialog(null, "باختی ...بای =)))");System.exit(0);
        }
    }
    void changeLevel(){
        switch (level){
            case 3 : heart++;break;
            case 6 : heart++;break;
            case 9 : heart++;break;
            case 2 : ninja++;break;
            case 5 : ninja++;break;
            case 8 : ninja++;break;

        }
    }

}
