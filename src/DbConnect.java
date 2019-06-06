import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class DbConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String col[] = {"Naam", "Telefoon", "Email", "Geboortedatum","Rating"};
    private DefaultTableModel model = new DefaultTableModel(col,0);

    public DbConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl:3306/18146481", "18146481", "shiesh7oCh");
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public DefaultTableModel getSpeler(String name) {
        try {
            if (model.getRowCount() > 0) {
                for (int i = model.getRowCount() - 1; i > -1; i--) {
                    model.removeRow(i);
                }
            }
            String query = "select * from `18146481`.Speler WHERE Speler.naam LIKE ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,"%"+name+"%");
            rs = st.executeQuery();

            while (rs.next()) {
                String n = rs.getString("naam");
                String t = rs.getString("telefoon");
                String e = rs.getString("email");
                String g = rs.getString("geboortedatum");
                String r = rs.getString("rating");
                model.addRow(new Object[]{n,t,e,g,r});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model;

    }

    public void addSpeler(String n, String a, String p, String w, String t, String e, String geb, String ges, int r) {
        try {
            String query = "INSERT INTO `18146481`.`Speler`(`naam`,`adres`,`postcode`,`woonplaats`,`telefoon`,`email`,`geboortedatum`,`geslacht`,`rating`)" +
                    "VALUES(?,?,?,?,?,?,?,?,?));";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,n);
            st.setString(2,a);
            st.setString(3,p);
            st.setString(4,w);
            st.setString(5,t);
            st.setString(6,e);
            st.setString(7,geb);
            st.setString(8,ges);
            st.setInt(9,r);

            st.executeUpdate();
            System.out.println("DONE");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addMaster(int b, int p, String begin, String eind, String datum, int m, double prijs, String betaald) {
        try {
            String query = "INSERT INTO `18146481`.`Masterclass`(`bekendeSpeler`,`plaatsen`,`beginTijd`,`eindTijd`,`datum`,`minRating`,`prijs`,`betaald`)" +
                    "VALUES(?,?,?,?,?,?,?,?));";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1,b);
            st.setInt(2,p);
            st.setString(3,begin);
            st.setString(4,eind);
            st.setString(5,datum);
            st.setInt(6,m);
            st.setDouble(7,prijs);
            st.setString(8,betaald);
            st.executeQuery();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addToernooi(String t, String c, int a, double p, String b, String e, String d) {
        try {
            String query = "INSERT INTO `18146481`.`Toernooi`(`thema`,`conditie`,`maxAantal`,`prijsDeelname`,`beginTijd`,`eindTijd`,`beginDatum`) " +
                    "VALUES(?,?,?,?,?,?,?));";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,t);
            st.setString(2,c);
            st.setInt(3,a);
            st.setDouble(4,p);
            st.setString(5,b);
            st.setString(6,e);
            st.setString(7,d);
            st.executeQuery();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Boolean login(String user, String pass) {
        boolean login =false;
        try {
            String query = "SELECT * FROM `18146481`.`USERS` WHERE username = ? and password = ?";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setString(1, user);
            st2.setString(2, pass);
            rs = st2.executeQuery();
            if(rs.next()==false){
                login = false;
            }
            else{
                login = true;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return login;
    }


}
