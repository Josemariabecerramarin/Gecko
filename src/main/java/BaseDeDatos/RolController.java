package BaseDeDatos;

import java.io.*;
import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar la tabla rol situada en mi base de datos
 */
public class RolController {
    private Connection connection;
    Scanner sc;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     */
    public RolController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para crear un rol
     */
    public void createRol() {
        try {
            System.out.println("----------------------");
            System.out.println("Crear Rol");
            System.out.println("----------------------");

            System.out.println("Rol:");
            String rol = sc.nextLine().toUpperCase(Locale.ROOT);

            String sql = "INSERT INTO rol " +
                    "(rol) VALUES (?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, rol);

            pst.executeUpdate();

            pst.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar roles
     */
    public void showRols(){
        System.out.println("\n" + "Roles: ");

        ResultSet rs = null;
        String sql = "SELECT * FROM rol";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("- " + rs.getString("rol"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: tabla rol no existe");
        }
    }

    /**
     * Este metodo sirve para borrar la tabla de rol
     */
    public void borrarTabla() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table rol");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla rol no existe");
        }
    }

    /**
     * Este metodo sirve para crear la tabla de rol
     */
    public void crearTabla(){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE rol(rol varchar(20) primary key)");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla rol ya existe");

        }
    }

    /**
     * Este metodo sirve para modificar el rol de los campeones que comienzan por tal letra
     */
    public void modificarRol(){
        try {
            Statement st = connection.createStatement();
            String rol = menu.RolMenu(connection).toUpperCase(Locale.ROOT);
            System.out.println("Escribe la primera letra del campeon que quieras modificar ?");
            String nom = sc.nextLine().toUpperCase(Locale.ROOT);

            st.executeUpdate("update campeon set rol='" + rol + "' where nom like '" + nom + "%'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
