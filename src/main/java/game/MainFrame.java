package GUI;



import game.Game;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(){

        initFrame();
        Game mainPanel=new Game(this);
        this.add(mainPanel);
        update();
    }
    private void initFrame(){
        this.setSize(new Dimension(800,800));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(null);
        setResizable(false);

    }

    public void update(){
        this.repaint();
        this.revalidate();
    }
}
