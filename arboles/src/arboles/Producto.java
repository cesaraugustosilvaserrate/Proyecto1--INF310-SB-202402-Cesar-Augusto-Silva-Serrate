package arboles;  // Asegúrate de que esté en el paquete correcto

public class Producto implements Comparable<Producto> {

    private int id;  // Identificador del producto
    private String nombre;  // Nombre del producto
    private double precio;  // Precio del producto

    // Constructor de la clase Producto
    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Métodos getters y setters para acceder y modificar los atributos
    public int getId() {
        return id;  // Retorna el ID
    }

    public void setId(int id) {
        this.id = id;  // Establece un nuevo ID
    }

    public String getNombre() {
        return nombre;  // Retorna el nombre del producto
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;  // Establece un nuevo nombre
    }

    public double getPrecio() {
        return precio;  // Retorna el precio del producto
    }

    public void setPrecio(double precio) {
        this.precio = precio;  // Establece un nuevo precio
    }

    // Método toString para representar el producto como una cadena
    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + '}';
    }

    // Implementación del método compareTo de Comparable, comparando por nombre
    @Override
    public int compareTo(Producto otroProducto) {
        return this.nombre.compareTo(otroProducto.nombre);  // Comparar productos por nombre
    }

    // Método equals para comparar productos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return this.nombre.equalsIgnoreCase(producto.nombre);  // Compara por nombre
    }

    // Método hashCode basado en el nombre
    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}

