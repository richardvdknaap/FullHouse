import javax.swing.*;
import java.awt.*;

public class Speler {

    public Speler() {

        DbConnect connect = new DbConnect();

        JFrame f = new JFrame("Menu");
        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(1200, 800);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable();

        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p1.add(table,c);

        f.add(p1,BorderLayout.WEST);
        f.add(p2,BorderLayout.CENTER);
        f.setVisible(true);

    }
}
