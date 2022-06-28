package models;

import java.util.ArrayList;

public class Event {
    public static ArrayList<String> events =new ArrayList<>();
    public static void showEvent(Player player,Emoji emoji){
        events.add(player.name+" -> "+ emoji.emoji);
    }
}
