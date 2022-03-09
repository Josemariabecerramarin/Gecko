package BaseDeDatos;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar la tabla funko situada en mi base de datos
 */
public class FunkoController {
    private Connection connection;
    Scanner sc;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     */
    public FunkoController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para crear un funko
     */
    public void createFunko() {
        try {
            System.out.println("----------------------");
            System.out.println("Crear Funko");
            System.out.println("----------------------");

            System.out.println("Categoria: ");
            String categoria = sc.nextLine().toUpperCase(Locale.ROOT);

            System.out.println("Nombre:");
            String nombre = sc.nextLine().toUpperCase(Locale.ROOT);

            System.out.println("Inserta una imagen:");
            String imagen = menu.DescripcionMenu(connection).toUpperCase(Locale.ROOT);

            System.out.println("Inserta el precio:");
            String precio = menu.DescripcionMenu(connection).toUpperCase(Locale.ROOT);

            System.out.println("Inserta una descripcion:");
            String descripcion = menu.DescripcionMenu(connection).toUpperCase(Locale.ROOT);

            ;

            String sql = "INSERT INTO funko " +
                    "(categoria, nombre, imagen, precio, descripcion) VALUES (?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, categoria);
            pst.setString(2, nombre);
            pst.setString(3, imagen);
            pst.setString(4, precio);
            pst.setString(5, descripcion);

            pst.executeUpdate();

            pst.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * Este metodo sirve para borrar la tabla funko
     */
    public void borrarTablaFunko() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table funko");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla funko no existe");
        }
    }

    /**
     * Este metodo sirve para crear la tabla de funko
     */
    public void crearTabla(){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE funko(id smallint primary key GENERATED ALWAYS AS IDENTITY, categoria varchar(200), nombre varchar(256), imagen varchar(256),  precio varchar(256), descripcion varchar(256))");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: La tabla funko ya existe");
        }
    }

    /**
     * Este metodo sirve para mostrar funkos por precio
     */
    public void showFunkoPorPrecio(){
        ResultSet rs = null;
        String precio = menu.DescripcionMenu(connection).toUpperCase(Locale.ROOT);
        String sql = "SELECT * FROM funko where precio='" + precio + "'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Nombre: " + rs.getString("nombre") + "\n" +
                        "Categoria: " + rs.getString("categoria")+ "\n" +
                        "Imagen: " + rs.getString("imagen")+ "\n" +
                        "Precio: " + rs.getString("precio")+ "\n" +
                        "Descripcion: " + rs.getString("descripcion") +
                        "id: " + rs.getInt("id"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar funko por un texto especifico
     */
    public void showFunkoCon(){
        ResultSet rs = null;
        System.out.println("Escribe el texto: ");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);
        String sql = "select * from funko where nom like '%" + letra + "%'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Nombre: " + rs.getString("nombre") + "\n" +
                        "Categoria: " + rs.getString("categoria") + "\n" +
                        "id: " + rs.getInt("id"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar funko por id
     */
    public void showFunkoPorId(){
        ResultSet rs = null;
        System.out.println("Escribe una id: ");
        String id = sc.nextLine().toUpperCase(Locale.ROOT);
        String sql = "select * from funko where id='" + id + "'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Nom: " + rs.getString("nom") + "\n" +
                        "Categoria: "+ rs.getString("categoria") + "\n" +
                        "Imagen: " + rs.getString("imagen") + "\n" +
                        "Precio: " + rs.getString("precio")+ "\n" +
                        "Descripcion: " + rs.getString("descripcion"));
            }


            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar los nombres de los funko
     */
    public void showFunkoNombre(){
        ResultSet rs = null;
        String sql = "SELECT nombre FROM funko";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("- " + rs.getString("nombre")+
                        "Categoria: "+ rs.getString("categoria"));
            }


            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * Este metodo sirve para modificar el nombre de un funko
     */
    public void modificarFunko(){
        try {
            Statement st = connection.createStatement();
            String nombre = menu.NomMenu(connection).toUpperCase(Locale.ROOT);
            System.out.println("Escribe el nuevo nombre: ");
            String newNom = sc.nextLine().toUpperCase(Locale.ROOT);

            st.executeUpdate("update funko set nombre='" + newNom + "' where nombre='" + nombre + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar un funko
     */
    public void borrarFunko(){
        try {
            Statement st = connection.createStatement();
            System.out.println("A quien quieres eliminar: ");
            String nombre = menu.NomMenu(connection).toUpperCase(Locale.ROOT);
            st.executeUpdate("delete from funko where nombre='" + nombre + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar funkos por precio
     */
    public void borrarFunkoPorPrecio(){
        try {
            Statement st = connection.createStatement();
            System.out.println("Que precio quieres eliminar: ");
            String precio = menu.DescripcionMenu(
                    connection).toUpperCase(Locale.ROOT);
            st.executeUpdate("delete from funko where precio='" + precio + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}