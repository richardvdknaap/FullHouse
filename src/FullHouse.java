import javax.swing.*;
import java.awt.*;

public class FullHouse {

    public static void main(String[] args) {

        DbConnect connect = new DbConnect();
        connect.getData();
        new FullHouse();

    }

    public FullHouse(){

        JFrame f = new JFrame();
        JPanel p1 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(1200, 800);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton registreren = new JButton("Registreren");

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0.5;
        p1.add(registreren,c);

        f.add(p1);
        f.setVisible(true);

    }


}
