import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InschrMasterclass {

    private JTable table = new JTable();
    private JTable table2 = new JTable();

    public InschrMasterclass(){

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


            table = new JTable(connect.getMaster());
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(1100,200));
            scrollPane.setViewportView(table);

            JScrollPane scrollPane2 = new JScrollPane(table2);
            scrollPane2.setPreferredSize(new Dimension(1100,200));

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
                    if (!table.getSelectionModel().isSelectionEmpty()) {
                        int srow = table.getSelectedRow();
                        Object rating = table.getValueAt(srow, 5);
                        table2 = new JTable(connect.getMasterSpelers(rating));
                        scrollPane2.setViewportView(table2);
                    }
                }
            });
            JButton b3 = new JButton("Speler Inschrijven");
            b3.setPreferredSize(new Dimension(200,50));
            b3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!table.getSelectionModel().isSelectionEmpty() && !table2.getSelectionModel().isSelectionEmpty()) {
                        int srow = table.getSelectedRow();
                        Object id = table.getValueAt(srow, 0);
                        int srow2 = table.getSelectedRow();
                        Object rating = table.getValueAt(srow2, 5);
                        int srow3 = table2.getSelectedRow();
                        Object idSpeler = table2.getValueAt(srow3, 0);
                        connect.inschijvenMasterclass(id, idSpeler);
                        table2 = new JTable(connect.getMasterSpelers(rating));
                        scrollPane2.setViewportView(table2);
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
            c.gridx = 0;c.gridy = 2;c.weightx = 0.5;p3.add(back,c);




            f.add(p1,BorderLayout.WEST);
            f.add(p2,BorderLayout.PAGE_START);
            f.add(p3,BorderLayout.EAST);
            f.setVisible(true);
    }

}
