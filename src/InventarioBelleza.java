import java.io.*;
import java.util.*;

public class InventarioBelleza {

    private List<Producto> productos;

    public InventarioBelleza() {
        this.productos = new ArrayList<>();
    }
    //  obtener la lista de productos
    public List<Producto> getProductos() {
        return productos;
    }

    // Cargar productos desde el archivo productos.txt
    public void cargarProductosDesdeArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String categoria = datos[2];
                double precio = Double.parseDouble(datos[3]);
                int cantidad = Integer.parseInt(datos[4]);
                String descripcion = datos[5];
                Producto producto = new Producto(id, nombre, categoria, precio, cantidad, descripcion);
                productos.add(producto);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Mostrar todos los productos
    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            System.out.println("Productos disponibles:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    // Agregar un producto al inventario
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("Producto agregado: " + producto);
    }
    // Métodopara buscar un producto por su ID
    public Producto buscarProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null; // Si no se encuentra el producto, devolver null
    }

    // Actualizar un producto en el inventario
    public boolean actualizarProducto(int id, String nombre, String categoria, double precio, int cantidad, String descripcion) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                producto.setNombre(nombre);
                producto.setCategoria(categoria);
                producto.setPrecio(precio);
                producto.setCantidad(cantidad);
                producto.setDescripcion(descripcion);
                System.out.println("Producto actualizado: " + producto);
                return true;
            }
        }
        return false;
    }
    // Métodopara guardar todos los productos en el archivo
    public void guardarProductosEnArchivo(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Producto producto : productos) {
                // Escribimos cada producto en el archivo, separando los datos con comas
                String linea = producto.getId() + "," +
                        producto.getNombre() + "," +
                        producto.getCategoria() + "," +
                        producto.getPrecio() + "," +
                        producto.getCantidad() + "," +
                        producto.getDescripcion();

                bw.write(linea);
                bw.newLine();  // Escribir un salto de línea entre productos
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }


    // Métodopara eliminar un producto
    public boolean eliminarProducto(int id) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            productos.remove(producto); // Eliminar el producto de la lista
            return true;
        }
        return false; // Si no se encuentra el producto, no se elimina
    }


    // Generar reporte del inventario
    public void generarReporte() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("reporte_inventario.txt"))) {
            double totalInventario = 0;
            bw.write("Reporte de Inventario de Eterna Juventud\n");
            bw.write("***********************************\n");
            for (Producto producto : productos) {
                bw.write(producto.toString() + "\n");
                totalInventario += producto.getPrecio() * producto.getCantidad();
            }
            bw.write("***********************************\n");
            bw.write("Valor Total del Inventario: $" + totalInventario);
            System.out.println("Reporte generado: reporte_inventario.txt");
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

    // Ver el producto más caro
    public void productoMasCaro() {
        Producto masCaro = null;
        for (Producto producto : productos) {
            if (masCaro == null || producto.getPrecio() > masCaro.getPrecio()) {
                masCaro = producto;
            }
        }
        if (masCaro != null) {
            System.out.println("El producto más caro es: " + masCaro);
        } else {
            System.out.println("No hay productos disponibles.");
        }
    }
}
