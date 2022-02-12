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
            System.out.println("5. Mostrar los campeones que tengan un ?");
            System.out.println("6. Mostrar todos los campeon que empiezan por ?");
            System.out.println("7. Modificar el nombre de un campeon");
            System.out.println("8. Modificar el rols de los campeones que empiezan por ?");
            System.out.println("9. Eliminar un campeon");
            System.out.println("10. Eliminar campeones con el rol ?");
            System.out.println("11. Añadir un rol");
            System.out.println("12. Añadir un campeon");
            System.out.println("13. Mostrar campeones");
            System.out.println("14. Mostrar roles");
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
     * Este metodo sirve para mostrar un menu de rol
     * @param c recibe la coneccion
     * @return devuelve el rol que elegiste
     */
    public String RolMenu(Connection c){
        RolController rolController = new RolController(c);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(;;){
            rolController.showRols();
            System.out.println("Elige el rol: ");
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
     * Este metodo sirve para mostrar un menu de nombres de campeon
     * @param c recibe la coneccion
     * @return devuelve el nombre que elegiste
     */
    public String NomMenu(Connection c){
        FunkoController funkoController = new FunkoController(c);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n" + "Campeones: ");
        for(;;){
            funkoController.showCampeonNom();
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
        System.out.println("============================ACB=============================");
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