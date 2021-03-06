package WebScrapping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * En esta clase abrimos una web, la cual recorreremos cogiendo de ella toda la informacion que queremos.
 */
public class WebScrapping {

    /**
     * Aqui iniciaremos otros metodos
     */
    public void Scrapping() {
        /**
         * @param js llamamos a la clase WebScrapping.JavaScripts
         * @param w llamamos a la clase WebScrapping.Writer
         * @param file creamos el archivos y definimos la ruta del csv
         * @param jaxb llamamos a la clase WebScrapping.JAXB
         */
        JavaScripts js = new JavaScripts();
        Writer w;
        File file = new File("src/main/resources/listafunkos.csv");
        JAXB jaxb;

        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv("HOME"));
        //  System.out.println(System.getenv(""));

        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        // File pathBinary = new File("src/main/resources/firefox");
        //  FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
        // DesiredCapabilities desired = new DesiredCapabilities();
        FirefoxOptions options = new FirefoxOptions();
        // desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
        WebDriver driver = new FirefoxDriver(options);

        List<Funko> funkos = new ArrayList<Funko>();
        ArrayList<String> paginas = new ArrayList<String>();
        paginas.add("22-marvel?page");
        paginas.add("26-dc-comics?page");
        paginas.add("24-anime-manga?page");

        for (String page : paginas) {


            driver.get("https://bellascositas.es/" + page);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement lista = driver.findElement(new By.ByClassName("list"));
            lista.click();

            js.scrollWindow(100, driver, wait);
            /**
             * @param funkoElements esta lista buscara todos los datos dentro de la clase que le asignamos
             * @param funkoElement buscara uno a uno los elementos de las clases
             * @param titulo va a buscar el contenido dentro de de la clase que le asignamos
             * @param imagen va a buscar el contenido dentro de de la clase que le asignamos
             * @param precio va a buscar el contenido dentro de de la clase que le asignamos
             * @param descripcion va a buscar el contenido dentro de de la clase que le asignamos
             */
            List<WebElement> funkoElements = driver.findElements(new By.ByClassName("ajax_block_product"));


            String titulo;
            String imagen;
            String precio;
            String descripcion;
            for (WebElement funkoElement : funkoElements) {

                try {
                    String categoria = driver.findElement(new By.ByClassName("page_heading")).getText();
                    titulo = funkoElement.findElement(new By.ByClassName("s_title_block")).getText();
                    imagen = funkoElement.findElement(new By.ByClassName("front-image")).getAttribute("data-src");
                    precio = funkoElement.findElement(new By.ByClassName("price")).getText();
                    descripcion = funkoElement.findElement(new By.ByClassName("product-desc")).getText();

                    System.out.println(categoria);
                    System.out.println(titulo);
                    System.out.println(imagen);
                    System.out.println(precio);
                    System.out.println(descripcion + "\n");

                    Funko funko = new Funko();
                    funko.setCategoria(categoria);
                    funko.setTitulo(titulo);
                    funko.setImagen(imagen);
                    funko.setPrecio(precio);
                    funko.setDescripcion(descripcion);
                    funkos.add(funko);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        w = new Writer(funkos, file);
        jaxb = new JAXB(funkos);
        driver.close();
    }
}
