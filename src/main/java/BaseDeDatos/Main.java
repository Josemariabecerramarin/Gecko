package BaseDeDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Esta clase es la principal donde inicializas tu programa y muestra un menu
 */
public class Main {
    public static void main(String[] args) throws IOException, SQLException, ParseException {
        Menu menu = new Menu();

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();

        FunkoController funkoController = new FunkoController(c);
        RolController rolController = new RolController(c);
        TodoController todoController = new TodoController(c);


        int option = menu.mainMenu();
        while (option > 0 && option < 16) {
            switch (option) {
                case 1:
                    funkoController.borrarTablaFunko();
                    rolController.borrarTabla();
                    break;

                case 2:
                    rolController.crearTabla();
                    funkoController.crearTabla();
                    break;

                case 3:
                    todoController.rellenar();
                    break;
                case 4:
                    funkoController.showFunkoNombre();
                    break;

                case 5:
                    funkoController.showFunkoPorPrecio();
                    break;

                case 6:
                    funkoController.showFunkoCon();
                    break;

                case 7:
                    funkoController.showFunkoPorId();
                    break;

                case 8:
                    funkoController.modificarFunko();
                    break;

                case 9:
                    rolController.modificarRol();
                    break;

                case 10:
                    funkoController.borrarFunko();
                    break;

                case 11:
                    funkoController.borrarFunkoPorPrecio();
                    break;

                case 12:
                    rolController.createRol();
                    break;

                case 13:
                    funkoController.createFunko();
                    break;


                case 14:
                    rolController.showRols();
                    break;

                case 15:
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