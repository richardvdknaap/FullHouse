import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toernooi {

    private JTable table = new JTable();


    public Toernooi(){
        DbConnect connect = new DbConnect();
        JFrame f = new JFrame("Menu");
        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        JPanel p3 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(1300, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,300));

        table = new JTable(connect.getToernooi());
        scrollPane.setViewportView(table);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(200,50));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Menu();
            }
        });

        JButton b2 = new JButton("Spelers Weergeven");
        b2.setPreferredSize(new Dimension(200,50));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ToernooiSpelers();
                f.dispose();

            }
        });

        c.anchor = GridBagConstraints.WEST;

        c.insets = new Insets(0,10,5,10);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p1.add(scrollPane,c);

        c.insets = new Insets(10,0,0,150);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p3.add(b2,c);
        c.gridx = 0;c.gridy = 1;c.weightx = 0.5;p3.add(back,c);




        f.add(p1,BorderLayout.WEST);
        f.add(p2,BorderLayout.PAGE_START);
        f.add(p3,BorderLayout.EAST);
        f.setVisible(true);
    }
}