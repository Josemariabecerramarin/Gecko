package WebScrapping;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.List;

/**
 * Esta clase crea un fichero XML con los datos que sacamos de la clase WebScrapping.WebScrapping
 */
public class JAXB {
    File file = new File("funkos.xml");
    Funkos funkosList = new Funkos();
    Funko funko;
    JAXBContext jaxbContext;
    Marshaller jaxbMarshaller;

    /**
     * Este metodo recibe un parametro y lo convierte en XML
     * @param funkos recibe una lista de objetos.
     */
    JAXB(List<Funko> funkos) {
        file.delete();
        try {
            jaxbContext = JAXBContext.newInstance(Funkos.class);

            for (Funko f : funkos) {
                funko = new Funko(f.titulo, f.imagen, f.precio, f.descripcion);
                funkosList.addFunkos(funko);
            }

            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(funkosList, file);
//            jaxbMarshaller.marshal(datas, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}