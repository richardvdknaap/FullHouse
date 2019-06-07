import com.appsdeveloperblog.encryption.Security;
import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FullHouse {

    final static String LOOKANDFEEL = "Metal";
    private boolean login = false;


    public static void main(String[] args) {

        new FullHouse();

    }

    public FullHouse(){

        DbConnect connect = new DbConnect();
        JFrame f = new JFrame("Login");
        JPanel p = new JPanel(new GridBagLayout());
        JPanel g = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(300, 150);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton reg = new JButton("Klant aanmaken");
        reg.setPreferredSize(new Dimension(200,50));
        reg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Register();
            }
        });
        JLabel l1 = new JLabel("Gebruikersnaam: ");
        JLabel l2 = new JLabel("Wachtwoord: ");

        JTextField username = new JTextField(13);
        JPasswordField password = new JPasswordField(13);



        // LOG IN KNOP
        JButton inl = new JButton("Log In");
        inl.setPreferredSize(new Dimension(200,50));
        inl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String i1 = username.getText();

                String secpass = connect.getSecpass(i1);


                String salt = connect.getSalt(i1);


                boolean passwordMatch = Security.verifyUserPassword(secpass, secpass, salt);

                if(passwordMatch)
                {
                    login = true;
                } else {
                    login = false;
                }

                login = connect.login(i1,salt,secpass);
                System.out.println(login);
                if (login == true) {
                    f.dispose();
                    new Menu();
                }

            }
        });



        c.insets = new Insets(0,0,5,0);

        c.gridx=0;c.gridy=0;c.weightx=0.5;p.add(l1,c);
        c.gridx=1;c.gridy=0;c.weightx=0.5;p.add(username,c);
        c.gridx=0;c.gridy=1;c.weightx=0.5;p.add(l2,c);
        c.gridx=1;c.gridy=1;c.weightx=0.5;p.add(password,c);
        c.gridx=0;c.gridy=0;c.weightx=0.5;g.add(inl,c);


        f.add(p,BorderLayout.CENTER);
        f.add(p);
        f.add(g,BorderLayout.PAGE_END);
        f.setVisible(true);



    }
}

