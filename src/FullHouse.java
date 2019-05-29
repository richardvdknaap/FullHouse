import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FullHouse {

    final static String LOOKANDFEEL = "Metal";


    public static void main(String[] args) {

        DbConnect connect = new DbConnect();
        connect.getData();
        new FullHouse();

    }

    public FullHouse(){

        JFrame f = new JFrame("Menu");
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(1200, 800);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton reg = new JButton("Registreren");
        reg.setPreferredSize(new Dimension(150,50));
        reg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Register();
            }
        });


        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0.5;
        p.add(reg,c);

        f.add(p);
        f.setVisible(true);



    }
}


