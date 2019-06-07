import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DbConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String col[] = {"ID","Naam", "Telefoon", "Email", "Geboortedatum","Rating"};
    private String col2[] = {"ID", "Thema", "Conditie","Aantal", "Prijs:", "Begintijd","Eindtijd","Begindatum"};
    private String col3[] = {"Naam ", "Geslacht", "Rating","Datum"};
    private DefaultTableModel model = new DefaultTableModel(col,0);
    private DefaultTableModel model2 = new DefaultTableModel(col2,0);
    private DefaultTableModel model3 = new DefaultTableModel(col3,0);



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
                String id = rs.getString("idSpeler");
                String n = rs.getString("naam");
                String t = rs.getString("telefoon");
                String e = rs.getString("email");
                String g = rs.getString("geboortedatum");
                String r = rs.getString("rating");
                model.addRow(new Object[]{id,n,t,e,g,r});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model;

    }

    public DefaultTableModel getToernooi(){
        try {
            if (model2.getRowCount() > 0) {
                for (int i = model2.getRowCount() - 1; i > -1; i--) {
                    model2.removeRow(i);
                }
            }
            String query = "select * from `18146481`.Toernooi";
            PreparedStatement st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idToernooi");
                String n = rs.getString("Thema");
                String t = rs.getString("Conditie");
                String e = rs.getString("maxAantal");
                String g = rs.getString("Prijsdeelname");
                String r = rs.getString("Begintijd");
                String z = rs.getString("Eindtijd");
                String i = rs.getString("beginDatum");
                model2.addRow(new Object[]{id,n,t,e,g,r,z,i});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model2;

    }

    public void addSpeler(String n, String a, String p, String w, String t, String e, String geb, String ges, int r) {
        try {
            String query = "INSERT INTO `18146481`.`Speler`(`naam`,`adres`,`postcode`,`woonplaats`,`telefoon`,`email`,`geboortedatum`,`geslacht`,`rating`)" +
                    "VALUES(?,?,?,?,?,?,?,?,?);";
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

    public void addUser(String user, String pass) {
        try {
            String query = "INSERT INTO `18146481`.`USERS`(`username`,`password`)" +
                    "VALUES(?,?);";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,user);
            st.setString(2,pass);

            st.executeUpdate();
            System.out.println("DONE");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addMaster(int b, int p, String begin, String eind, String datum, int m, double prijs) {
        try {
            String query = "INSERT INTO `18146481`.`Masterclass`(`bekendeSpeler`,`plaatsen`,`beginTijd`,`eindTijd`,`datum`,`minRating`,`prijs`)" +
                    "VALUES(?,?,?,?,?,?,?);";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setInt(1,b);
            st2.setInt(2,p);
            st2.setString(3,begin);
            st2.setString(4,eind);
            st2.setString(5,datum);
            st2.setInt(6,m);
            st2.setDouble(7,prijs);
            st2.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addToernooi(String t, String c, int a, double p, String b, String e, String d) {
        try {
            String query = "INSERT INTO `18146481`.`Toernooi`(`thema`,`conditie`,`maxAantal`,`prijsDeelname`,`beginTijd`,`eindTijd`,`beginDatum`) " +
                    "VALUES(?,?,?,?,?,?,?);";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setString(1,t);
            st2.setString(2,c);
            st2.setInt(3,a);
            st2.setDouble(4,p);
            st2.setString(5,b);
            st2.setString(6,e);
            st2.setString(7,d);
            st2.executeUpdate();

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
    public void deleteUser(Object id){

        try {
            String query = "UPDATE `18146481`.`Speler` SET " +
                    "Speler.naam = 'VERWIJDERD', " +
                    "Speler.adres = 'VERWIJDERD', " +
                    "Speler.postcode = 'VERWIJDERD'," +
                    "Speler.woonplaats = 'VERWIJDERD', " +
                    "Speler.telefoon = 'VERWIJDERD', " +
                    "Speler.email = 'VERWIJDERD', " +
                    "Speler.geboortedatum = 'VERWIJDERD' " +
                    "WHERE Speler.idSpeler = ?";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setObject(1,id);
            st2.executeUpdate();

        }catch (Exception ex){
            System.out.println(ex);
        }

    }
    public ArrayList<String> listToernooi(Object id){

        ArrayList<String> namen = new ArrayList<>();
        try{
            String query = "SELECT Toernooi.thema from `18146481`.Toernooi WHERE Toernooi.conditie = (SELECT Speler.geslacht FROM `18146481`.Speler WHERE Speler.idSpeler = ?) OR Toernooi.Conditie = 'O'";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setObject(1,id);
            rs = st2.executeQuery();
            while(rs.next()){
                String thema = rs.getString("thema");
                namen.add(thema);
            }
        } catch (Exception ex){
            System.out.println(ex);
        }


        return namen;
    }
    public void inschijvenToernooi(String tName, Object id){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            int toerId = 0;

            String query = "SELECT * from `18146481`.Toernooi WHERE thema = ?";
            System.out.println(query);
            PreparedStatement st1 = con.prepareStatement(query);
            st1.setString(1,tName);
            rs = st1.executeQuery();
            while (rs.next()) {
                toerId = rs.getInt("idToernooi");
            }
            String query2 = "INSERT INTO `18146481`.`Betaald`(`datum`,`idSpeler`,`idToernooi`) " +
                    "VALUES(?,?,?);";
            System.out.println(query2);
            PreparedStatement st2 = con.prepareStatement(query2);
            st2.setString(1,localDate.toString());
            st2.setObject(2,id);
            st2.setInt(3,toerId);
            st2.executeUpdate();

        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public DefaultTableModel getToernooiSpelers(){
        try {
            if (model3.getRowCount() > 0) {
                for (int i = model3.getRowCount() - 1; i > -1; i--) {
                    model3.removeRow(i);
                }
            }
            String query = "select Speler.idSpeler, Speler.naam, Speler.geslacht, Speler.rating, Betaald.datum from `18146481`.Speler JOIN Betaald on Speler.idSpeler = Betaald.idSpeler JOIN Toernooi on Toernooi.idToernooi = Betaald.idToernooi GROUP BY Speler.naam";
            PreparedStatement st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idSpeler");
                String n = rs.getString("naam");
                String t = rs.getString("geslacht");
                String e = rs.getString("rating");
                String g = rs.getString("datum");
                model3.addRow(new Object[]{id,n,t,e,g});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model3;

    }

}
