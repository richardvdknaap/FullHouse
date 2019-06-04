import java.sql.*;

public class DbConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DbConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl:3306/18146481", "18146481", "shiesh7oCh");
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void getData() {
        try {

            String query = "select * from Speler";
            rs = st.executeQuery(query);
            System.out.println("Records from DB");
            while (rs.next()) {
                String naam = rs.getString("naam");
                //System.out.println(naam);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addSpeler(String n, String a, String p, String w, String t, String e, String geb, String ges, int r) {
        try {
            String query = "INSERT INTO `18146481`.`Speler`(`naam`,`adres`,`postcode`,`woonplaats`,`telefoon`,`email`,`geboortedatum`,`geslacht`,`rating`)" +
                    "VALUES(" +
                    "'" + n + "'" + "," +
                    "'" + a + "'" + "," +
                    "'" + p + "'" + "," +
                    "'" + w + "'" + "," +
                    "'" + t + "'" + "," +
                    "'" + e + "'" + "," +
                    "'" + geb + "'" + "," +
                    "'" + ges + "'" + "," +
                    r + ");";

            st.executeUpdate(query);
            System.out.println("DONE");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public void addMaster(int p, String bt, String et, String dat, int minr, double pri, String bet){
        try{
            String query = "INSERT INTO `18146481`.`Masterclass`(`plaatsen`,`beginTijd`,`eindTijd`,`datum`,`minRating`,`prijs`,`betaald`) "+
                    "VALUES(" +
                    + p + ","+
                    "'" + bt + "'" + ","+
                    "'" + et + "'" + ","+
                    "'" + dat + "'" + ","+
                    "'" + minr + "'" + ","+
                     pri + ","+
                    "'" + bet + "'" + ");";
            System.out.println(query);
            st.executeUpdate(query);
        }
        catch (Exception ex){

    public void addMaster() {
        try {
            String query = "";
            st.executeUpdate(query);
            System.out.println("DONE");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addToernooi(String t, String c, int a, double p, String b, String e, String d) {
        try {
            String query = "INSERT INTO `18146481`.`Toernooi`(`thema`,`conditie`,`maxAantal`,`prijsDeelname`,`beginTijd`,`eindTijd`,`beginDatum`) " +
                    "VALUES(" +
                    "'" + t + "'" + "," +
                    "'" + c + "'" + "," +
                    a + "," +
                    p + "," +
                    "'" + b + "'" + "," +
                    "'" + e + "'" + "," +
                    "'" + d + "'" + ");";
            st.executeUpdate(query);
            System.out.println(query);
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
