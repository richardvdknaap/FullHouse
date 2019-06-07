import javax.swing.*;
import java.awt.*;

public class ToernooiSpelers {

    private JTable table = new JTable();

    public ToernooiSpelers(){

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

        //table = new JTable(connect.getToernooiSpelers());
        scrollPane.setViewportView(table);

    }
}
