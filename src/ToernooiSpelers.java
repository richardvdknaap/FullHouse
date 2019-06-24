import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToernooiSpelers {

    private JTable table = new JTable();

    public ToernooiSpelers(Object id){

        Object idT = id;

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
        scrollPane.setPreferredSize(new Dimension(800,300) );

        table = new JTable(connect.getToernooiSpelers(id));
        scrollPane.setViewportView(table);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(200,50));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Toernooi();
            }
        });
        JButton b1 = new JButton("#1");
        b1.setPreferredSize(new Dimension(100,50));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int srow = table.getSelectedRow();
                Object sid = table.getValueAt(srow, 0);
                connect.winnaar(sid,1,idT);
            }
        });
        JButton b2 = new JButton("#2");
        b2.setPreferredSize(new Dimension(100,50));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int srow = table.getSelectedRow();
                Object sid = table.getValueAt(srow, 0);
                connect.winnaar(sid,2,idT);
            }
        });

        c.anchor = GridBagConstraints.WEST;

        c.insets = new Insets(0,10,5,10);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p1.add(scrollPane,c);
        c.insets = new Insets(0,0,5,0);
        c.gridx = 0;c.gridy = 1;c.weightx = 0.5;p3.add(b1,c);
        c.insets = new Insets(0,0,5,10);
        c.gridx = 1;c.gridy = 1;c.weightx = 0.5;p3.add(b2,c);
        c.gridwidth = 2;c.gridx = 0;c.gridy = 2;c.weightx = 0.5;p3.add(back,c);

        f.add(p1,BorderLayout.WEST);
        f.add(p2,BorderLayout.PAGE_START);
        f.add(p3,BorderLayout.EAST);
        f.setVisible(true);
    }
}
