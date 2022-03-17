package BaseDeDatos;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar la tabla categoria situada en mi base de datos
 */
public class CategoriaController {
    private Connection connection;
    Scanner sc;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la conexion hacia postgres
     */
    public CategoriaController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para crear una descripcion
     */


    /**
     * Este metodo sirve para mostrar la categoria de los funkos
     */
    public void showCategoria(){
        System.out.println("\n" + "Titulo: ");
        ResultSet rs = null;
        String sql = "SELECT * FROM categoria";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("- " + rs.getString("titulo"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: tabla titulo no existe");
        }
    }

    /**
     * Este metodo sirve para borrar la tabla categoria
     */
    public void borrarTabla() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table categoria");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla categoria no existe");
        }
    }
}
