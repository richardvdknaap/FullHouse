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
    private String col[] = {"ID","Naam","Adres","Postcode","Woonplaats", "Telefoon", "Email", "Geboortedatum","Geslacht","Rating"};
    private String col2[] = {"ID", "Thema", "Conditie","Max Aantal","Aantal spelers","Aantal betaald", "Prijs:", "Begintijd","Eindtijd","Begindatum","Locatie","Adres"};
    private String col3[] = {"idSpeler","Naam ", "Geslacht", "Rating","Betaaldatum"};
    private String col4[] = {"idSpeler","Naam ", "Geslacht", "Rating"};
    private String col5[] = {"ID","Thema", "Betaalde Spelers","Prijs Deelname", "Totale inleggeld"};
    private String col6[] = {"ID","Bekende Speler", "Begin Tijd", "Eind Tijd","Datum","Minimale Rating", "Prijs Deelname"};
    private DefaultTableModel model = new DefaultTableModel(col,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private DefaultTableModel model2 = new DefaultTableModel(col2,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private DefaultTableModel model3 = new DefaultTableModel(col3,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private DefaultTableModel model4 = new DefaultTableModel(col4,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private DefaultTableModel model5 = new DefaultTableModel(col5,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private DefaultTableModel model6 = new DefaultTableModel(col6,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private DefaultTableModel model7 = new DefaultTableModel(col4,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };



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
                String a = rs.getString("adres");
                String p = rs.getString("postcode");
                String w = rs.getString("woonplaats");
                String t = rs.getString("telefoon");
                String e = rs.getString("email");
                String g = rs.getString("geboortedatum");
                String ge = rs.getString("geslacht");
                String r = rs.getString("rating");
                model.addRow(new Object[]{id,n,a,p,w,t,e,g,ge,r});

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
            String query = "select Toernooi.idToernooi, Toernooi.thema, Toernooi.conditie, Toernooi.maxAantal, count(DISTINCT(Betaald.idSpeler)) as 'Bezet', count(Betaald.datum) as 'Betaald', Toernooi.prijsDeelname, Toernooi.beginTijd, Toernooi.eindTijd, Toernooi.beginDatum, Locatie.naam, Locatie.adres" +
                    " from `18146481`.Toernooi " +
                    "left join `18146481`.Betaald on Betaald.idToernooi = Toernooi.idToernooi " +
                    "left join `18146481`.Speler on Speler.idSpeler = Betaald.idSpeler " +
                    "left join `18146481`.Locatie on Locatie.idLocatie = Toernooi.locatie " +
                    "group by Toernooi.idToernooi";
            System.out.println(query);
            PreparedStatement st2 = con.prepareStatement(query);
            rs = st2.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idToernooi");
                String n = rs.getString("Thema");
                String t = rs.getString("Conditie");
                String e = rs.getString("maxAantal");
                String b = rs.getString("Bezet");
                String bet = rs.getString("Betaald");
                String g = rs.getString("Prijsdeelname");
                String r = rs.getString("Begintijd");
                String z = rs.getString("Eindtijd");
                String i = rs.getString("beginDatum");
                String l = rs.getString("naam");
                String a = rs.getString("adres");
                model2.addRow(new Object[]{id,n,t,e,b,bet,g,r,z,i,l,a});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model2;

    }
    public DefaultTableModel getInzet(){
        try {
            if (model5.getRowCount() > 0) {
                for (int i = model5.getRowCount() - 1; i > -1; i--) {
                    model5.removeRow(i);
                }
            }
            String query = "select Toernooi.idToernooi, Toernooi.thema, count(Betaald.datum) as 'Betaald', Toernooi.prijsDeelname, (count(Betaald.datum) * Toernooi.prijsDeelname) as 'Totaal'" +
                    " from `18146481`.Toernooi " +
                    "left join `18146481`.Betaald on Betaald.idToernooi = Toernooi.idToernooi " +
                    "left join `18146481`.Speler on Speler.idSpeler = Betaald.idSpeler " +
                    "left join `18146481`.Locatie on Locatie.idLocatie = Toernooi.locatie " +
                    "group by Toernooi.idToernooi";
            PreparedStatement st2 = con.prepareStatement(query);
            rs = st2.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idToernooi");
                String n = rs.getString("Thema");
                String bet = rs.getString("Betaald");
                String g = rs.getString("Prijsdeelname");
                String a = rs.getString("Totaal");
                model5.addRow(new Object[]{id,n,bet,g,a});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model5;

    }
    public DefaultTableModel getMaster(){
        try {
            if (model6.getRowCount() > 0) {
                for (int i = model6.getRowCount() - 1; i > -1; i--) {
                    model6.removeRow(i);
                }
            }
            String query = "select Masterclass.idMasterclass, Speler.naam, Masterclass.beginTijd, Masterclass.eindTijd, Masterclass.datum, Masterclass.minRating, Masterclass.prijs " +
                    " from `18146481`.Masterclass " +
                    "left join `18146481`.Speler on Speler.idSpeler = Masterclass.bekendeSpeler " +
                    "group by Masterclass.idMasterclass ";
            PreparedStatement st2 = con.prepareStatement(query);
            rs = st2.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idMasterclass");
                String n = rs.getString("naam");
                String bet = rs.getString("beginTijd");
                String g = rs.getString("eindTijd");
                String a = rs.getString("datum");
                String b = rs.getString("minRating");
                String c = rs.getString("prijs");
                model6.addRow(new Object[]{id,n,bet,g,a,b,c});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model6;

    }
    public DefaultTableModel getMasterSpelers(Object rating){
        try {
            if (model7.getRowCount() > 0) {
                for (int i = model7.getRowCount() - 1; i > -1; i--) {
                    model7.removeRow(i);
                }
            }

            String query = "select Speler.idSpeler, Speler.naam, Speler.geslacht, Speler.rating " +
                    "from `18146481`.Speler " +
                    "WHERE Speler.rating >= ? " +
                    "GROUP BY Speler.idSpeler;";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setObject(1,rating);
            rs = st2.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idSpeler");
                String n = rs.getString("naam");
                String bet = rs.getString("geslacht");
                String g = rs.getString("rating");
                model7.addRow(new Object[]{id,n,bet,g});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model7;

    }

    public DefaultTableModel getMasterclass(){
        try {
            if (model4.getRowCount() > 0) {
                for (int i = model4.getRowCount() - 1; i > -1; i--) {
                    model4.removeRow(i);
                }
            }
            String query = "select Masterclass.idMasterclass, Masterclass.bekendeSpeler, Masterclass.plaatsen, Masterclass.beginTijd, Masterclass.eindTijd, Masterclass.datum, Masterclass.minRating, Masterclass.prijs from `18146481`.Masterclass ";
            System.out.println(query);
            PreparedStatement st2 = con.prepareStatement(query);
            rs = st2.executeQuery();

            while (rs.next()) {
                System.out.println(2);
                String id = rs.getString("idMasterclass");
                String n = rs.getString("bekendeSpeler");
                String t = rs.getString("plaatsen");
                String e = rs.getString("beginTijd");
                String b = rs.getString("eindTijd");
                String g = rs.getString("datum");
                String r = rs.getString("minRating");
                String z = rs.getString("prijs");
                model4.addRow(new Object[]{id,n,t,e,b,g,r,z});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model4;

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

    public void addUser(String user, String salt, String secpass) {
        try {
            String query = "INSERT INTO `18146481`.`USERS`(`username`,`salt`,`secPass`)" +
                    "VALUES(?,?,?);";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,user);
            st.setString(2,salt);
            st.setString(3,secpass);


            st.executeUpdate();
            System.out.println("DONE");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public String getSalt(String user){
        String salt = "";
        try{
            String query = "SELECT * FROM `18146481`.`USERS` WHERE USERS.username LIKE ?";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setString(1,user);
            rs = st2.executeQuery();
            while (rs.next()){

               salt = rs.getString("salt");
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        System.out.println(salt);
        return salt;
    }

    public String getSecpass(String user){
        String secpass = "";
        try{
            String query = "SELECT * FROM `18146481`.`USERS` WHERE USERS.username LIKE ?";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setString(1,user);
            rs = st2.executeQuery();
            while (rs.next()){

                secpass = rs.getString("secPass");
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return secpass;
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

    public void addToernooi(String t, String c, int a, double p, String b, String e, String d, int r) {
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

            String query2 = "INSERT INTO `18146481`.`Ronde`(`ronde`,`idToernooi`) " +
                    "VALUES(?,?);";


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }



    public Boolean login(String user, String salt, String secpass) {
        boolean login =false;
        try {
            String query = "SELECT * FROM `18146481`.`USERS` WHERE username = ? and salt = ? and secPass = ?";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setString(1, user);
            st2.setString(2, salt);
            st2.setString(3, secpass);
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

    public void inschijvenMasterclass(Object id){

        try {
            String query = "update `18146481`.`Masterclass` set plaatsen = plaatsen -1 WHERE idMasterclass LIKE ?";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setObject(1, id);
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
            String query2 = "INSERT INTO `18146481`.`Betaald`(`idSpeler`,`idToernooi`) " +
                    "VALUES(?,?);";
            System.out.println(query2);
            PreparedStatement st2 = con.prepareStatement(query2);
            //st2.setString(1,localDate.toString());
            st2.setObject(1,id);
            st2.setInt(2,toerId);
            st2.executeUpdate();

        }catch (Exception ex){
            System.out.println(ex);
        }
    }



    public DefaultTableModel getToernooiSpelers(Object id){
        try {
            if (model3.getRowCount() > 0) {
                for (int i = model3.getRowCount() - 1; i > -1; i--) {
                    model3.removeRow(i);
                }
            }
            String query = "select Speler.idSpeler, Speler.naam, Speler.geslacht, Speler.rating, Betaald.datum " +
                    "from `18146481`.Speler " +
                    "JOIN Betaald on Speler.idSpeler = Betaald.idSpeler " +
                    "JOIN Toernooi on Toernooi.idToernooi = Betaald.idToernooi " +
                    "WHERE Toernooi.idToernooi = ? " +
                    "GROUP BY Speler.idSpeler;";
            System.out.println(query);
            PreparedStatement st = con.prepareStatement(query);
            st.setObject(1,id);
            rs = st.executeQuery();


            while (rs.next()) {
                String id1 = rs.getString("idSpeler");
                String n = rs.getString("naam");
                String t = rs.getString("geslacht");
                String e = rs.getString("rating");
                String g = rs.getString("datum");
                model3.addRow(new Object[]{id1,n,t,e,g});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model3;

    }
    public DefaultTableModel getTeBetalenSpelers(Object id){
        try {
            if (model4.getRowCount() > 0) {
                for (int i = model4.getRowCount() - 1; i > -1; i--) {
                    model4.removeRow(i);
                }
            }
            String query = "select Speler.idSpeler, Speler.naam, Speler.geslacht, Speler.rating " +
                    "from `18146481`.Speler " +
                    "JOIN Betaald on Speler.idSpeler = Betaald.idSpeler " +
                    "JOIN Toernooi on Toernooi.idToernooi = Betaald.idToernooi " +
                    "WHERE Toernooi.idToernooi = ? AND Betaald.datum is null " +
                    "GROUP BY Speler.naam;";


            System.out.println(query);
            PreparedStatement st = con.prepareStatement(query);
            st.setObject(1,id);
            rs = st.executeQuery();


            while (rs.next()) {
                String id1 = rs.getString("idSpeler");
                String n = rs.getString("naam");
                String t = rs.getString("geslacht");
                String e = rs.getString("rating");
                model4.addRow(new Object[]{id1,n,t,e});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model4;

    }

    public DefaultTableModel getMasterclassSpelers(Object id){
        try {
            if (model3.getRowCount() > 0) {
                for (int i = model3.getRowCount() - 1; i > -1; i--) {
                    model3.removeRow(i);
                }
            }
            String query = "select Speler.idSpeler, Speler.naam, Speler.geslacht, Speler.rating, Betaald.datum from `18146481`.Speler JOIN Betaald on Speler.idSpeler = Betaald.idSpeler JOIN Toernooi on Toernooi.idToernooi = Betaald.idToernooi WHERE Toernooi.idToernooi = ? GROUP BY Speler.naam;";
            PreparedStatement st = con.prepareStatement(query);
            st.setObject(1,id);
            rs = st.executeQuery();


            while (rs.next()) {
                String id1 = rs.getString("idSpeler");
                String n = rs.getString("naam");
                String t = rs.getString("geslacht");
                String e = rs.getString("rating");
                String g = rs.getString("datum");
                model3.addRow(new Object[]{id1,n,t,e,g});

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return model3;

    }

    }
