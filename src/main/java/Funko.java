import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase contiene unas variables ue utilizaremos para añadirlos al XML.
 */

@XmlRootElement(name = "Funko")
public class Funko {
    String titulo;
    String imagen;
    String precio;
    String descripcion;

    /**
     * Este constructor que recibira unos datos y los asignara
     * @param titulo recibe un valor para titulo
     * @param imagen recibe un valor para imagen
     * @param precio recibe un valor para precio
     * @param descripcion recibe un valor para descripcion
     */
    Funko(String titulo, String imagen, String precio, String descripcion) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    /**
     * Este es un constructor ue esta vacio
     */
    Funko(){}

    /**
     * Aqui cogemos el titulo
     * @param titulo nos devuelve el titulo
     */
    @XmlElement(name = "Titulo")
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Sirve para asignar el valor de titulo
     * @return nos devuelve el valor de titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Aqui cogemos la imagen
     * @param imagen nos devuelve la imagen
     */
    @XmlElement(name = "Imagen")
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    /**
     * Sirve para asignar el valor de imagen
     * @return nos devuelve el valor de imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Aqui cogemos el precio
     * @param precio nos devuelve el precio
     */
    @XmlElement(name = "Precio")
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * Sirve para asignar el valor de precio
     * @return nos devuelve el valor de precio
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Aqui cogemosd el descripcion
     * @param descripcion nos devuelve la descripcion
     */
    @XmlElement(name = "Descripcion")
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Sirve para asignar el valor de descripcion
     * @return nos devuelve el valor de descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }



}
