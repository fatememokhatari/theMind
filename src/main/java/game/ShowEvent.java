package game;

import javax.swing.*;
import models.Event;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowEvent extends JPanel{
    JFrame frame;

    JButton Return;
    public static final String[] columns = {
            "رویداد ها" };
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    private JTable table = new JTable(model);
    private JPanel mainPanel = new JPanel(new BorderLayout());

    public ShowEvent( ) {
        frame=new JFrame();
        frame.setSize(new Dimension(600,600));
        frame.setVisible(true);
        frame.setLayout(null);
        update();
        initPanel();
table.setRowHeight(table.getRowHeight()+7);

        for (int i = 0; i <Event.events.size(); i++) {
            String str=Event.events.get(i).toString();
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
                    for (int i = 0; i <Event.events.size(); i++) {
                        String str=Event.events.get(i).toString();
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
