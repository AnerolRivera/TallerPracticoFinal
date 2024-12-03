public class Producto {
    // Atributos privados de la clase Producto
    // Estos atributos definen las propiedades de un producto
    private int id;              // Identificador único del producto
    private String nombre;       // Nombre del producto
    private String categoria;    // Categoría a la que pertenece el producto (ejemplo: Ropa, Electrónica, etc.)
    private double precio;       // Precio del producto
    private int cantidad;        // Cantidad disponible en inventario
    private String descripcion;  // Descripción detallada del producto

    // Constructor de la clase Producto
    // Este constructor inicializa los atributos con valores proporcionados al crear un objeto Producto
    public Producto(int id, String nombre, String categoria, double precio, int cantidad, String descripcion) {
        this.id = id;              // Asigna el valor del id al atributo id
        this.nombre = nombre;      // Asigna el valor del nombre al atributo nombre
        this.categoria = categoria;// Asigna el valor de la categoría al atributo categoria
        this.precio = precio;      // Asigna el valor del precio al atributo precio
        this.cantidad = cantidad;  // Asigna el valor de la cantidad al atributo cantidad
        this.descripcion = descripcion; // Asigna el valor de la descripción al atributo descripcion
    }

    // Métodos getter y setter para acceder y modificar los atributos privados

    // Getter para obtener el valor del atributo id
    public int getId() {
        return id;
    }

    // Setter para modificar el valor del atributo id
    public void setId(int id) {
        this.id = id;
    }

    // Getter para obtener el valor del atributo nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para modificar el valor del atributo nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para obtener el valor del atributo categoria
    public String getCategoria() {
        return categoria;
    }

    // Setter para modificar el valor del atributo categoria
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter para obtener el valor del atributo precio
    public double getPrecio() {
        return precio;
    }

    // Setter para modificar el valor del atributo precio
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Getter para obtener el valor del atributo cantidad
    public int getCantidad() {
        return cantidad;
    }

    // Setter para modificar el valor del atributo cantidad
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getter para obtener el valor del atributo descripcion
    public String getDescripcion() {
        return descripcion;
    }

    // Setter para modificar el valor del atributo descripcion
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método sobrescrito toString() para representar el producto en formato String
    // Este método devuelve una cadena legible que describe el producto
    @Override
    public String toString() {
        // Construye y retorna una representación legible del producto
        return "ID: " + id + ", " + nombre + " (" + categoria + ") - Precio: $" + precio + " - Stock: " + cantidad + " unidades";
    }
}
