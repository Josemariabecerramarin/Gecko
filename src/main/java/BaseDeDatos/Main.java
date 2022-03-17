package BaseDeDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Esta clase es la principal donde inicializas tu programa y muestra el menu
 */
public class Main {
    public static void main(String[] args) throws IOException, SQLException, ParseException {
        Menu menu = new Menu();

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();

        FunkoController funkoController = new FunkoController(c);
        CategoriaController categoriaController = new CategoriaController(c);
        TodoController todoController = new TodoController(c);


        int option = menu.mainMenu();
        while (option > 0 && option < 16) {
            switch (option) {
                case 1:
                    funkoController.borrarTablaFunko();
                    categoriaController.borrarTabla();
                    break;

                case 2:
                    funkoController.crearTabla();
                    break;

                case 3:
                    todoController.rellenar();
                    break;
                case 4:
                    funkoController.createFunko();
                    break;
                case 5:
                    funkoController.showFunkoNombre();
                    break;

                case 6:
                    funkoController.showFunkoPorPrecio();
                    break;

                case 7:
                    funkoController.borrarFunkoPorCategoria();
                    break;

                case 8:
                    funkoController.showFunkoPorId();
                    break;

                case 9:
                    funkoController.mostrarFunkoPorNombre();
                    break;
                case 10:
                    connectionFactory.disconnect();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Introduce una de las opciones");
                    break;
            }
            option = menu.mainMenu();
        }
    }
}