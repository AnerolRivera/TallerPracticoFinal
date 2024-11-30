public class Producto {
    // Atributos privados
    private int id;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidad;
    private String descripcion;


    // Constructor para crear un nuevo producto
    public Producto(int id, String nombre, String categoria, double precio, int cantidad, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;

    }

    // Métodos getter y setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   // Métodopara representar el producto en formato String
    @Override
    public String toString() {
        return "ID: " + id + ", " + nombre + " (" + categoria + ") - Precio: $" + precio + " - Stock: " + cantidad + " unidades";
    }
}
