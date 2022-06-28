package models;

public enum Emoji {

    SMILE(":D "),
    HAPPY(":) "),
    POKER(":/ ");

    String emoji;

    Emoji(String emoji) {
        this.emoji = emoji;
    }
    public static void showEmoji(Player player,Emoji emoji){
        System.out.println(player.name+" -> "+ emoji.emoji);
    }
}
