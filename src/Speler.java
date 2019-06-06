import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Speler {

    private JTable table = new JTable();

    public Speler() {

        DbConnect connect = new DbConnect();

        JFrame f = new JFrame("Menu");
        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(900, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l = new JLabel("Naam: ");

        JTextField t = new JTextField(13);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,300));

        JButton b = new JButton("Ophalen");
        b.setPreferredSize(new Dimension(200,50));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.removeAll();
                scrollPane.setViewportView(table);
                String name = t.getText();
                table = new JTable(connect.getSpeler(name));
                scrollPane.setViewportView(table);
            }
        });
        c.anchor = GridBagConstraints.CENTER;

        c.insets = new Insets(0,100,5,0);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p2.add(l,c);
        c.insets = new Insets(0,0,5,150);
        c.gridx = 1;c.gridy = 0;c.weightx = 0.5;p2.add(t,c);
        c.insets = new Insets(0,0,5,0);
        c.gridx = 2;c.gridy = 0;c.weightx = 0.5;p2.add(b,c);

        c.insets = new Insets(0,10,5,10);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p1.add(scrollPane,c);

        f.add(p1,BorderLayout.SOUTH);
        f.add(p2,BorderLayout.CENTER);

        f.setVisible(true);

    }
}
