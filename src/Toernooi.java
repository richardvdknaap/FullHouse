import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toernooi {

    private JTable table = new JTable();
    private JTable table2 = new JTable();


    public Toernooi(){
        DbConnect connect = new DbConnect();
        JFrame f = new JFrame("Menu");
        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        JPanel p3 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(1700, 500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        table = new JTable(connect.getToernooi());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1200,200));
        scrollPane.setViewportView(table);

        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setPreferredSize(new Dimension(1200,200));

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(200,50));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Menu();
            }
        });

        JButton b0 = new JButton("Tafelindeling Weergeven");
        b0.setPreferredSize(new Dimension(200,50));
        b0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int srow = table.getSelectedRow();
                    Object id = table.getValueAt(srow, 0);
                    table2 = new JTable(connect.getTafel(id));
                    scrollPane2.setViewportView(table2);

                }
            }
        });

        JButton b4 = new JButton("Inleggeld Weergeven");
        b4.setPreferredSize(new Dimension(200,50));
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table2 = new JTable(connect.getInzet());
                scrollPane2.setViewportView(table2);
            }
        });

        JButton b2 = new JButton("Spelers Weergeven");
        b2.setPreferredSize(new Dimension(200,50));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int srow = table.getSelectedRow();
                    Object id = table.getValueAt(srow, 0);
                    f.dispose();
                    new ToernooiSpelers(id);
                }
            }
        });
        JButton b7 = new JButton("Winnaars Weergeven");
        b7.setPreferredSize(new Dimension(200,50));
        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int srow = table.getSelectedRow();
                    Object id = table.getValueAt(srow, 0);
                    table2 = new JTable(connect.getWinnaar(id));
                    scrollPane2.setViewportView(table2);
                }
            }
        });

        JButton b3= new JButton("Te Betalen Spelers");
        b3.setPreferredSize(new Dimension(200,50));
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int srow = table.getSelectedRow();
                    Object id = table.getValueAt(srow, 0);
                    f.dispose();
                    new ToernooiBetalen(id);
                }
            }
        });

        c.anchor = GridBagConstraints.WEST;

        c.insets = new Insets(0,10,5,10);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p1.add(scrollPane,c);
        c.gridx = 0;c.gridy = 1;c.weightx = 0.5;p1.add(scrollPane2,c);

        c.insets = new Insets(10,0,0,150);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p3.add(b2,c);
        c.gridx = 0;c.gridy = 1;c.weightx = 0.5;p3.add(b3,c);
        c.gridx = 0;c.gridy = 2;c.weightx = 0.5;p3.add(b0,c);
        c.gridx = 0;c.gridy = 3;c.weightx = 0.5;p3.add(b4,c);
        c.gridx = 0;c.gridy = 4;c.weightx = 0.5;p3.add(b7,c);
        c.gridx = 0;c.gridy = 5;c.weightx = 0.5;p3.add(back,c);




        f.add(p1,BorderLayout.WEST);
        f.add(p2,BorderLayout.PAGE_START);
        f.add(p3,BorderLayout.EAST);
        f.setVisible(true);
    }
}
