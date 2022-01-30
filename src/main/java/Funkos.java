
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene una lista de la clase Funko
 */
@XmlRootElement(name="Funkos")
public class Funkos {
    List<Funko> funkos = new ArrayList<>();

    /**
     * Aqui cogemos la lista de Funko.
     * @return nos devuelve.
     */
    public List<Funko> getFunkos() {
        return funkos;
    }

    /**
     * Aqui asignamos una lista de la clase Funko
     * @param funkos recibe el que asignaremos a la lista
     */
    @XmlElement(name="Funko")
    public void setFunkos(List<Funko> funkos) {
        this.funkos = funkos;
    }

    /**
     * Añade los datos en la lista funko
     * @param funko recibe esos datos
     */
    public void addFunkos(Funko funko){
        this.funkos.add(funko);
    }
}
