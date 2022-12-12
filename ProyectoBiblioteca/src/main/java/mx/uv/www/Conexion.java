package mx.uv.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    /*private String url = "jdbc:mysql://localhost:3306/proyectobiblioteca";
    private String username = "root";
    private String password = "";
    private String driverName = "com.mysql.jdbc.Driver";*/

    private String url = "jdbc:mysql://db4free.net:3306/biblioteca4sw?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private String username = "equipo4sw";
    private String password = "equipo4sw";
    private String driverName = "com.mysql.jdbc.Driver";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url,username,password);
            System.out.println("Se estableció la conexión!");
        } catch (SQLException e) {
            System.out.println("Falló con la conexion");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Falló la carga de la clase del JDBC");
            e.printStackTrace();
        }
        return conn;
    }
}