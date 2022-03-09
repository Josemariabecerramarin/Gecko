package BaseDeDatos;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar la tabla descripcion situada en mi base de datos
 */
public class CategoriaController {
    private Connection connection;
    Scanner sc;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     */
    public CategoriaController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para crear una descripcion
     */
    public void createTitulo() {
        try {
            System.out.println("----------------------");
            System.out.println("Crear descripcion");
            System.out.println("----------------------");

            System.out.println("Descripcion: ");
            String descripcion = sc.nextLine().toUpperCase(Locale.ROOT);

            String sql = "INSERT INTO categoria " +
                    "(categoria) VALUES (?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, descripcion);

            pst.executeUpdate();

            pst.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar las descripciones de los funkos
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
     * Este metodo sirve para borrar la tabla de descripcion
     */
    public void borrarTabla() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table categoria");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla descripcion no existe");
        }
    }

    /**
     * Este metodo sirve para crear la tabla de titulo
     */
    public void crearTabla(){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE categoria(id smallint primary key GENERATED ALWAYS AS IDENTITY, nombre varchar(256))");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla descripcion ya existe");

        }
    }

    /**
     * Este metodo sirve para modificar la descripcion de los funkos que comienzan por tal letra
     */
    public void modificarDescripcion(){
        try {
            Statement st = connection.createStatement();
            String descripcion = menu.DescripcionMenu(connection).toUpperCase(Locale.ROOT);
            System.out.println("Escribe la primera letra del campeon que quieras modificar ?");
            String nombre = sc.nextLine().toUpperCase(Locale.ROOT);

            st.executeUpdate("update funko set descripcion='" + descripcion + "' where nombre like '" + nombre + "%'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
