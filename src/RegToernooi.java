import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegToernooi {

    public RegToernooi(){

    DbConnect connect = new DbConnect();
    String[] genderList = {"Maak een Keuze...","Man","Vrouw","Allebei"};
    JFrame f = new JFrame("Toernooi Registreren");
    JPanel p2 = new JPanel(new GridBagLayout());

    JPanel p1 = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    f.setTitle("FullHouse");
    f.setSize(1200, 800);
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel l1 = new JLabel("Thema: ");
    JLabel l2 = new JLabel("Conditie: ");
    JLabel l3 = new JLabel("maxAantal: ");
    JLabel l4 = new JLabel("prijsDeelname: ");
    JLabel l5 = new JLabel("beginTijd: ");
    JLabel l6 = new JLabel("eindTijd: ");
    JLabel l7 = new JLabel("Datum");

    JTextField t1 = new JTextField(13);
    JComboBox t2 = new JComboBox(genderList);
    JTextField t3 = new JTextField(13);
    JTextField t4 = new JTextField(13);
    JTextField t5 = new JTextField(13);
    JTextField t6 = new JTextField(13);
    JTextField t7 = new JTextField(13);

        JButton reg = new JButton("Registreren");
        reg.setPreferredSize(new Dimension(150,50));
        reg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String i1 = t1.getText();
                int i3 =  Integer.parseInt(t3.getText());
                double i4 =  Double.parseDouble(t4.getText());
                String i5 = t5.getText();
                String i6 = t6.getText();
                String i7 = t7.getText();
                String i2 = "";
                if(t2.getSelectedIndex() == 1){
                    i2 = "M";
                }
                if(t2.getSelectedIndex() == 2){
                    i2 = "V";
                }
                if(t2.getSelectedIndex() == 3){
                    i2 = "O";
                }


                connect.addToernooi(i1,i2,i3,i4,i5,i6,i7);
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
