package game;

import models.Event;
import models.Human;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HumanCards extends JPanel {
    JFrame frame;
Human human;

    public static final String[] columns = {
            "کارت ها" };
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    private JTable table = new JTable(model);
    private JPanel mainPanel = new JPanel(new BorderLayout());

    public HumanCards(Human human) {
        this.human=human;
        frame=new JFrame();
        frame.setSize(new Dimension(600,600));
        frame.setVisible(true);
        frame.setLayout(null);
        update();
        initPanel();
        table.setRowHeight(table.getRowHeight()+15);

        for (int i = 0; i < models.Event.events.size(); i++) {
            String str= models.Event.events.get(i).toString();
            model.addRow(new Object[]{str});

        }


        this.add(new JScrollPane(table));
        frame.setContentPane(this);repaint();revalidate();
    }
    private void initPanel(){
        this.setBounds(0,0,800,800);
        this.setVisible(true);

    }
    public void update(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    while (model.getRowCount()>0){
                        model.removeRow(0);
                    }
                    for (int i = 0; i < human.getCards().size(); i++) {
                        String str= human.getCards().get(i).toString();
                        model.addRow(new Object[]{str});


                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
