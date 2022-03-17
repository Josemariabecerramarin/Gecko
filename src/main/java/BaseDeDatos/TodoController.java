package BaseDeDatos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
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
     * @param connection recibe la conexión hacia postgres
     */
    public TodoController(java.sql.Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para rellenar las tablas de la base de datos desde un fichero
     */
    public void rellenar() {

        String[] rata;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/listafunkos.csv")));
            String linia;
            while ((linia = br.readLine()) != null) {
                rata = linia.split("\"");

                System.out.println(linia);
                int id_categoria = 0;

                try {

                    String categoria = rata[1];

                    switch (categoria) {
                        case "MARVEL":
                            id_categoria = 1;
                            break;
                        case "DC COMICS":
                            id_categoria = 2;
                            break;
                        case "ANIME / MANGA":
                            id_categoria = 3;
                            break;
                    }

                    String sql = "SELECT COUNT(id_categoria) as size FROM categoria WHERE id_categoria = ?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setInt(1, id_categoria);


                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        int rsSize = rs.getInt("size");

                        // La categoría aún no está insertada
                        if ((rsSize == 0)) {

                            sql = "INSERT INTO categoria " + "(id_categoria,categoria) VALUES (?,?)";


                            pst = connection.prepareStatement(sql);
                            pst.setInt(1, id_categoria);
                            pst.setString(2, categoria);

                            pst.executeUpdate();

                            pst.close();


                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println(rata[1]);


                try {
                    // El id_categoria ya lo tengo
                    String nombre = rata[3];
                    String imagen = rata[5];
                    String precio = rata[7];
                    String descripcion = rata[9];


                    System.out.println(nombre);
                    System.out.println(imagen);
                    System.out.println(precio);
                    System.out.println(descripcion);


                    String sql = "INSERT INTO funko " + "(id_categoria, nombre, imagen, precio, descripcion) VALUES (?,?,?,?,?)";


                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setInt(1, id_categoria);
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
