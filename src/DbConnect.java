import java.sql.*;

public class DbConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DbConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl:3306/18146481","18146481","shiesh7oCh");
            st = con.createStatement();

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void getData(){
        try{

            String query = "select * from Speler";
            rs = st.executeQuery(query);
            System.out.println("Records from DB");
            while(rs.next()){
                String naam = rs.getString("naam");
                System.out.println(naam);
            }

        }catch (Exception ex){
            System.out.println(ex);
        }
    }

}
