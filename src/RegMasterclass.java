import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegMasterclass {

    public RegMasterclass(){

        DbConnect connect = new DbConnect();
        String[] genderList = {"Maak een Keuze...","Ja","Nee"};
        JFrame f = new JFrame("Masterclass Registreren");
        JPanel p2 = new JPanel(new GridBagLayout());

        JPanel p1 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(1200, 800);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l0 = new JLabel("Speler Id: ");
        JLabel l1 = new JLabel("Plaatsen: ");
        JLabel l2 = new JLabel("Begintijd: ");
        JLabel l3 = new JLabel("Eindtijd: ");
        JLabel l4 = new JLabel("Datum: ");
        JLabel l5 = new JLabel("Minimale rating: ");
        JLabel l6 = new JLabel("Prijs: ");
        JLabel l7 = new JLabel("Betaald");

        JTextField t0 = new JTextField(13);
        JTextField t1 = new JTextField(13);
        JTextField t2 = new JTextField(13);
        JTextField t3 = new JTextField(13);
        JTextField t4 = new JTextField(13);
        JTextField t5 = new JTextField(13);
        JTextField t6 = new JTextField(13);
        JComboBox t7 = new JComboBox(genderList);

        JButton reg = new JButton("Registreren");
        reg.setPreferredSize(new Dimension(150,50));
        reg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int i0 = Integer.parseInt(t0.getText());
                int i1 = Integer.parseInt(t1.getText());
                String i2 = t2.getText();
                String i3 = t3.getText();
                String i4 = t4.getText();
                int i5 = Integer.parseInt(t5.getText());
                double i6 = Double.parseDouble(t6.getText());
                String i7 = "";
                if(t7.getSelectedIndex() == 1){
                    i7 = "J";
                }
                if(t7.getSelectedIndex() == 2){
                    i7 = "N";
                }

                connect.addMaster(i0,i1,i2,i3,i4,i5,i6,i7);
            }
        });
        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150,50));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new FullHouse();
            }
        });

        c.insets = new Insets(0,10,5,0);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; p1.add(l1,c);
        c.gridx = 1; c.gridy = 0; p1.add(t1,c);
        c.gridx = 0; c.gridy = 1; p1.add(l2,c);
        c.gridx = 1; c.gridy = 1; p1.add(t2,c);
        c.gridx = 0; c.gridy = 2; p1.add(l3,c);
        c.gridx = 1; c.gridy = 2; p1.add(t3,c);
        c.gridx = 0; c.gridy = 3; p1.add(l4,c);
        c.gridx = 1; c.gridy = 3; p1.add(t4,c);
        c.gridx = 0; c.gridy = 4; p1.add(l5,c);
        c.gridx = 1; c.gridy = 4; p1.add(t5,c);
        c.gridx = 0; c.gridy = 5; p1.add(l6,c);
        c.gridx = 1; c.gridy = 5; p1.add(t6,c);
        c.gridx = 0; c.gridy = 6; p1.add(l7,c);
        c.gridx = 1; c.gridy = 6; p1.add(t7,c);



        c.insets = new Insets(0,0,50,0);
        c.gridx = 0; c.gridy = 0; p2.add(reg,c);
        c.gridx = 0; c.gridy = 1; p2.add(back,c);


        f.add(p1,BorderLayout.WEST);
        f.add(p2,BorderLayout.CENTER);
        f.setVisible(true);
    }
}
