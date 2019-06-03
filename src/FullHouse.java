import com.sun.source.tree.NewArrayTree;

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
        JPanel g = new JPanel(new GridBagLayout());
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(1200, 800);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton reg = new JButton("Registreren");
        reg.setPreferredSize(new Dimension(200,50));
        reg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Register();
            }
        });

        // LOG IN KNOP
        JButton inl = new JButton("Log In");
        inl.setPreferredSize(new Dimension(200,50));
        inl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();

            }
        });

        JButton toer = new JButton("Toernooi Weergeven");
        toer.setPreferredSize(new Dimension(200,50));
        toer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              f.dispose();
            }
        });

        JButton master = new JButton("Masterclass weergeven");
        master.setPreferredSize(new Dimension(200,50));
        master.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              f.dispose();
            }
        });

        JButton toeraan = new JButton("Toernooi aanmaken");
        toeraan.setPreferredSize(new Dimension(200,50));
        toeraan.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new RegToernooi();
            }
        });

        JButton masteraan = new JButton("Masterclass aanmaken");
        masteraan.setPreferredSize(new Dimension(200,50));
        masteraan.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        c.insets = new Insets(0,0,5,0);

        // Butoons x en y
        // Registratie
        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0.5;
        p.add(reg,c);

        // Login
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        g.add(inl,c);

        // Toernooi weergeven
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        p.add(toer,c);

        // Maasterclass weergeven
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        p.add(master,c);

        // Maasterclass aanmaken
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0.5;
        p.add(toeraan,c);

        // toernooi aanmaken
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 0.5;
        p.add(masteraan,c);



        f.add(p,BorderLayout.CENTER);
        f.add(g,BorderLayout.PAGE_END);
        f.setVisible(true);



    }
}

