package game;

public class Second {
    public static int timePass =0;

    public Second() {
new Thread(new Runnable() {
    @Override
    public void run() {
        while (true){
            try {
            timePass++;
            System.out.println(Second.timePass);

                Thread.sleep(500);
            } catch (InterruptedException e) {

                break;
            }
        }
    }
}).start();
    }



}
