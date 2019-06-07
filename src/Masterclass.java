import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Masterclass {

    private JTable table = new JTable();


    public Masterclass(){
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


        table = new JTable(connect.getMasterclass());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,300));
        scrollPane.setViewportView(table);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(200,50));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Menu();
            }
        });


        c.anchor = GridBagConstraints.WEST;

        c.insets = new Insets(0,10,5,10);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p1.add(scrollPane,c);

        c.insets = new Insets(10,0,0,150);
        c.gridx = 0;c.gridy = 1;c.weightx = 0.5;p3.add(back,c);




        f.add(p1,BorderLayout.WEST);
        f.add(p2,BorderLayout.PAGE_START);
        f.add(p3,BorderLayout.EAST);
        f.setVisible(true);
    }
}
