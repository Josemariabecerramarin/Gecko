package WebScrapping;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Esta clase crea una fichero CSV con los datos que obtenemos de la clase WebScrapping.WebScrapping
 */
public class Writer {
    /**
     *
     * @param listaFunkos recibe una lista de objetos.
     * @param file es el ficherop donde vamos a escribir los datos.
     */
    Writer(List<Funko> listaFunkos, File file){
        CSVWriter csvWriter;
        String[] entries =  new String[4];
        file.delete();
        for (Funko funko: listaFunkos) {
            entries[0]=(funko.titulo);
            entries[1]=(funko.imagen);
            entries[2]=(funko.precio);
            entries[3]=(funko.descripcion);

            try {
                csvWriter = new CSVWriter(new FileWriter(file, true));
                csvWriter.writeNext(entries);
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ruta del archivo " + file.getPath());
    }
}