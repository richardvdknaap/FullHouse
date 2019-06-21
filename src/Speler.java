import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Speler {

    private JTable table = new JTable();
    private JComboBox list = new JComboBox();
    int iSelectedIndex =-1;

    public Speler() {

        DbConnect connect = new DbConnect();
        String yn[] = {"Ja","Nee"};

        JFrame f = new JFrame("Menu");
        JFrame o = new JFrame();
        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        JPanel p3 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        f.setTitle("FullHouse");
        f.setSize(1500, 800);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setPreferredSize(new Dimension(200,50));

        JLabel l = new JLabel("Naam: ");

        JTextField t = new JTextField(13);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000,500));


        JButton b1 = new JButton("Ophalen");
        b1.setPreferredSize(new Dimension(200,50));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = t.getText();
                table = new JTable(connect.getSpeler(name));
                scrollPane.setViewportView(table);
            }
        });
        JButton b3 = new JButton("Toernooien Ophalen");
        b3.setPreferredSize(new Dimension(200,50));
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.removeAllItems();
                list.addItem("Selecteer toernooi...");
                if (!table.getSelectionModel().isSelectionEmpty()){
                    int srow = table.getSelectedRow();
                    Object sid = table.getValueAt(srow, 0);
                    //list = new JComboBox(connect.listToernooi(sid).toArray());
                    Object[] data = connect.listToernooi(sid).toArray();
                    for (int x=0;x<data.length;x++){
                        list.addItem(data[x]);
                    }
                }
            }
        });
        JButton inschrijventoer = new JButton("Inschrijven Toernooi");
        inschrijventoer.setPreferredSize(new Dimension(200,50));
        inschrijventoer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    String sToer = (list.getSelectedItem().toString());
                    int srow = table.getSelectedRow();
                    Object sid = table.getValueAt(srow, 0);
                    connect.inschijvenToernooi(sToer, sid);
                }
            }
        });
        JButton b2 = new JButton("Verwijderen");
        b2.setPreferredSize(new Dimension(200,50));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int srow = table.getSelectedRow();
                    Object sname = table.getValueAt(srow, 1);
                    int input = (int) JOptionPane.showOptionDialog(o, "Weet je zeker dat je " + sname + " wilt verwijderen?", "Selecteer een optie", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, yn, yn[1]);
                    if (input == 0) {
                        Object id = table.getValueAt(srow, 0);
                        connect.deleteUser(id);
                        String name = t.getText();
                        table = new JTable(connect.getSpeler(name));
                        scrollPane.setViewportView(table);
                    }
                }
            }
        });
        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(200,50));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new Menu();
            }
        });
        JButton master = new JButton("Maak Masterclass");
        master.setPreferredSize(new Dimension(200,50));
        master.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int srow = table.getSelectedRow();
                    Object id = table.getValueAt(srow, 0);
                    f.dispose();
                    new RegMasterclass(id);
                }
            }
        });
        /*JButton inschrijfMaster = new JButton("Inschrijven Masterclass");
        inschrijfMaster.setPreferredSize(new Dimension(200,50));
        inschrijfMaster.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int srow = table.getSelectedRow();
                    Object id = table.getValueAt(srow, 0);
                    connect.inschijvenMasterclass(id);
                }
            }
        });*/


        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0,10,5,0);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p2.add(l,c);
        c.insets = new Insets(0,0,5,150);
        c.gridx = 1;c.gridy = 0;c.weightx = 0.5;p2.add(t,c);
        c.insets = new Insets(0,0,5,0);
        c.gridx = 2;c.gridy = 0;c.weightx = 0.5;p2.add(b1,c);
        c.insets = new Insets(0,0,5,200);
        c.gridx = 3;c.gridy = 0;c.weightx = 0.5;p2.add(b3,c);

        c.insets = new Insets(0,10,5,10);
        c.gridx = 0;c.gridy = 0;c.weightx = 0.5;p1.add(scrollPane,c);

        c.insets = new Insets(10,0,0,5);
        c.gridx = 1;c.gridy = 9;c.weightx = 0.5;p3.add(b2,c);
        c.gridx = 0;c.gridy = 1;c.weightx = 0.5;p3.add(master,c);
        //c.gridx = 1;c.gridy = 1;c.weightx = 0.5;p3.add(inschrijfMaster,c);
        c.gridx = 0;c.gridy = 9;c.weightx = 0.5;p3.add(back,c);
        c.gridx = 0;c.gridy = 2;c.weightx = 0.5;p3.add(list,c);
        c.gridx = 1;c.gridy = 2;c.weightx = 0.5;p3.add(inschrijventoer,c);

        f.add(p1,BorderLayout.WEST);
        f.add(p2,BorderLayout.PAGE_START);
        f.add(p3,BorderLayout.EAST);

        f.setVisible(true);

    }
}
