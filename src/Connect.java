import java.sql.*;

public class Connect {
    private Statement st;
    private Connection con;

    public Connect() {
        try {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://shop.accdb");
            st = con.createStatement();
            System.out.println("Connection Successful");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
        return rs;
    }

    public ResultSet executeUpdate(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
        return null;
    }

}
