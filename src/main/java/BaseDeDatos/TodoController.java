package BaseDeDatos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Esta clase sirve para leer fichero CSV y meter en la base de datos
 */
public class TodoController {
    private Connection connection;
    Scanner sc;

    /**
     * Esto es el constructor de la clase
     *
     * @param connection recibe la coneccion hacia postgres
     */
    public TodoController(java.sql.Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para rellenar datos de un fichero hacia las tablas de base de datos
     */
    public void rellenar() {

        String[] rata;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/listafunkos.csv")));
            String linia;
            while ((linia = br.readLine()) != null) {
                rata = linia.split("\"");

                System.out.println(linia);

                try {
                    String descripcion = rata[1];

                    String sql = "INSERT INTO categoria " +
                            "(nombre) VALUES (?)";

                    System.out.println(descripcion);


                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, descripcion);

                    pst.executeUpdate();

                    pst.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    String categoria = rata[1];
                    String nombre = rata[3];
                    String imagen = rata[5];
                    String precio = rata[7];
                    String descripcion = rata[9];

                    String sql = "INSERT INTO funko " +
                            "(categoria, nombre, imagen, precio, descripcion) VALUES (?,?,?,?,?)";

                    System.out.println(nombre);
                    System.out.println(imagen);
                    System.out.println(precio);
                    System.out.println(descripcion);

                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, categoria);
                    pst.setString(2, nombre);
                    pst.setString(3, imagen);
                    pst.setString(4, precio);
                    pst.setString(5, descripcion);


                    pst.executeUpdate();

                    pst.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
