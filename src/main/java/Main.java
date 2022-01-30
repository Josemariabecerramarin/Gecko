import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase lanza la aplicacion.
 */
public class Main {
  /**
   * Desde aqui se lanza el programa
   */
  public static void main(String[] args) {
    WebScrapping ws = new WebScrapping();



    ws.Scrapping();
  }


}
