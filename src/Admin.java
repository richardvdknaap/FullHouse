import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin{

    final static String LOOKANDFEEL = "Metal";
    private boolean login = false;

    public Admin(){

        DbConnect connect = new DbConnect();
        JFrame f = new JFrame("Maak Admin");
        JPanel p = new JPanel(new GridBagLayout());
        JPanel g = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(300, 200);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l1 = new JLabel("Gebruikersnaam: ");
        JLabel l2 = new JLabel("Wachtwoord: ");

        JTextField username = new JTextField(13);
        JPasswordField password = new JPasswordField(13);



        // LOG IN KNOP
        JButton inl = new JButton("Maak Admin");
        inl.setPreferredSize(new Dimension(200,50));
        inl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String i1 = username.getText();
                String i2 = password.getText();

                connect.addUser(i1,i2);
            }
        });

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150,50));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Menu();
            }
        });



        c.insets = new Insets(0,0,5,0);

        c.gridx=0;c.gridy=0;c.weightx=0.5;p.add(l1,c);
        c.gridx=1;c.gridy=0;c.weightx=0.5;p.add(username,c);
        c.gridx=0;c.gridy=1;c.weightx=0.5;p.add(l2,c);
        c.gridx=1;c.gridy=1;c.weightx=0.5;p.add(password,c);
        c.gridx=0;c.gridy=0;c.weightx=0.5;g.add(inl,c);
        c.gridx=1;c.gridy=0;c.weightx=0.5;g.add(back,c);


        f.add(p,BorderLayout.CENTER);
        f.add(p);
        f.add(g,BorderLayout.PAGE_END);
        f.setVisible(true);



    }
}

