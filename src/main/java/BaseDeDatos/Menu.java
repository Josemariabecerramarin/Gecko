package BaseDeDatos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Esta clase sirve para mostrar menus
 */
public class Menu {
    private int option;
    private String opciones;

    /**
     * Este es un constructor y llama a la clase padre suyo(nose quien es)
     */
    public Menu() {
        super();
    }

    /**
     * Este metodo sirve para mostrar un menu
     * @return devuelte la opcion que elegiste en numero
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {

            System.out.println(" \nMENU PRINCIPAL \n");

            System.out.println("1. Borrar Tablas");
            System.out.println("2. Craer Tablas");
            System.out.println("3. Rellenar Tablas");
            System.out.println("4. Mostrar los que sean ?");
            System.out.println("5. Mostrar los funkos que tengan un ?");
            System.out.println("6. Mostrar todos los funkos que empiezan por ?");
            System.out.println("7. Modificar el nombre de un funko");
            System.out.println("8. Modificar la descripcion de los funkos que empiezan por ?");
            System.out.println("9. Eliminar un funko");
            System.out.println("10. Eliminar eliminar los funko con la descripcion ?");
            System.out.println("11. Añadir una descripcion");
            System.out.println("12. Añadir un funko");
            System.out.println("13. Mostrar funkos");
            System.out.println("14. Mostrar descripciones");
            System.out.println("15. Exit");
            System.out.println("Esculli opció: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
                && option != 8 && option != 9 && option != 10 && option != 11 && option != 12 && option != 13 && option != 14 && option != 15);


        return option;
    }

    /**
     * Este metodo sirve para mostrar un menu de descripcion
     * @param c recibe la coneccion
     * @return devuelve el rol que elegiste
     */
    public String DescripcionMenu(Connection c){
        CategoriaController categoriaController = new CategoriaController(c);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(;;){
            categoriaController.showCategoria();
            System.out.println("Elige la descripcion: ");
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return opciones;
        }
    }

    /**
     * Este metodo sirve para mostrar un menu de nombres de los funkos
     * @param c recibe la conexion
     * @return devuelve el nombre que elegiste
     */
    public String NomMenu(Connection c){
        FunkoController funkoController = new FunkoController(c);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n" + "Funkos: ");
        for(;;){
            funkoController.showFunkoNombre();
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return opciones;
        }
    }

    /**
     * Este metodo sirve para auntenticar
     * @param tries recibe la cantidad de intento
     * @return devuelve unos datos de tipo Identity
     * @throws IOException es un tipo de excepciones
     */
    public Identity authenticate(int tries) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("===========================Funko============================");
        System.out.println("============================================================");
        System.out.println("Avís: tens " + (3 - tries) + " intents per loginarte");
        System.out.println("============================================================");
        System.out.println("Inserta nom del usuari: ");
        String user = br.readLine();
        System.out.println("Inserta contrasenya: ");
        String password = br.readLine();

        Identity identity = new Identity(user, password);
        return identity;
    }
}