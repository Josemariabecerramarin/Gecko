package WebScrapping;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Esta clase sirve para guardar todos los javascripts que hagamos
 */
public class JavaScripts {
    /**
     * En este metodo creo un scroll para que la web muestre todos los datos
     * @param veces le decimos cuantas veces hará el scroll
     * @param driver le mandamos la variable de la clase WebScrapping.WebScrapping
     * @param wait le mandamos la variable de la clase WebScapping
     */
    public void scrollWindow(int veces, WebDriver driver, WebDriverWait wait){
        for (int i = 0; i < veces; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1000)", "");
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByClassName("s_title_block")));
        }
    }
}
