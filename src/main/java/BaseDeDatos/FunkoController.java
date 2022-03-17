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
     * @param connection recibe la conexión hacia postgres
     */
    public FunkoController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este método sirve para crear un funko
     */
    public void createFunko() {
        try {
            System.out.println("----------------------");
            System.out.println("Crear Funko");
            System.out.println("----------------------");

            System.out.println("Categoria: ");
            String[] opciones = new String[]{"MARVEL", "DC COMICS", "ANIME / MANGA"};
            int opcion = menu.elegirOpcion(opciones);
            int id_categoria = 0;
            String categoria = null;

            switch (opcion) {
                case 1:
                    id_categoria = 1;
                    categoria = "\"MARVEL\"";
                    break;
                case 2:
                    id_categoria = 2;
                    categoria = "\"DC\"";
                    break;
                case 3:
                    id_categoria = 3;
                    categoria = "\"ANIME / MANGA\"";
                    break;
            }
            System.out.println("Nombre:");
            String nombre = sc.nextLine();

            System.out.println("Inserta una imagen:");
            String imagen = sc.nextLine();

            System.out.println("Inserta el precio:");
            String precio = sc.nextLine();

            System.out.println("Inserta una descripcion:");
            String descripcion = sc.nextLine();



            String sql = "INSERT INTO funko " +
                    "(id_categoria, nombre, imagen, precio, descripcion) VALUES (?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id_categoria);
            pst.setString(2, nombre);
            pst.setString(3, imagen);
            pst.setString(4, precio);
            pst.setString(5, descripcion);


            pst.executeUpdate();

            pst.close();

            sql = "SELECT COUNT(id_categoria) as size FROM categoria WHERE id_categoria = ?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id_categoria);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int rsSize = rs.getInt("size");

                if ((rsSize == 0)) {
                    try {
                        sql = "INSERT INTO plataforma(idplataforma, categoria) VALUES (?,?)";

                        pst = connection.prepareStatement(sql);
                        pst.setInt(1, id_categoria);
                        pst.setString(2, categoria);

                        pst.executeUpdate();
                        pst.close();
                        System.out.println(id_categoria);
                    } catch (SQLException e) {
                    }
                }
            }
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
            st.executeUpdate("CREATE TABLE IF NOT EXISTS categoria(id_categoria smallint  primary key, categoria varchar(50))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS funko(id_categoria smallint references categoria(id_categoria), nombre varchar(256), imagen varchar(256),  precio varchar(256), descripcion varchar(256))");
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
        System.out.println("precio a buscar");
        String precio = sc.nextLine();
        String sql = "SELECT * FROM funko where precio LIKE '" + precio + "%'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Nombre: " + rs.getString("nombre") + "\n" +
                        "id_Categoria: " + rs.getString("id_categoria")+ "\n" +
                        "Imagen: " + rs.getString("imagen")+ "\n" +
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
     * Este metodo sirve para mostrar los funkos por su id
     */
    public void showFunkoPorId(){
        ResultSet rs = null;
        System.out.println("MARVEL -> 1");
        System.out.println("DC COMICS -> 2");
        System.out.println("ANIME/MANGA -> 3");
        System.out.println("Escribe una id: ");
        String id = sc.nextLine().toUpperCase(Locale.ROOT);
        String sql = "select * from funko where id_categoria='" + id + "'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Nombre: " + rs.getString("nombre") + "\n" +
                        "Categoria: "+ rs.getString("id_categoria") + "\n" +
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
                System.out.println("- " + rs.getString("nombre"));
            }


            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar funkos por categoria
     */
    public void borrarFunkoPorCategoria(){
        try {
            Statement st = connection.createStatement();
            System.out.println("Que categoria quieres eliminar: ");
            System.out.println("Marvel -> 1");
            System.out.println("DC COMICS -> 2");
            System.out.println("Anime/Manga -> 3");
            String categoria = sc.nextLine();
            st.executeUpdate("delete from funko where id_categoria='" + categoria + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar funkos por su nombre
     */
    public void mostrarFunkoPorNombre(){
        ResultSet rs;
        System.out.println("Escribe una palabra que contenga el funko que buscas: ");
        String nombre = sc.nextLine();
        String sql = "SELECT * FROM funko WHERE nombre LIKE '%" + nombre + "%'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Nombre: " + rs.getString("nombre") + "\n" +
                        "Categoria: "+ rs.getString("id_categoria") + "\n" +
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
}