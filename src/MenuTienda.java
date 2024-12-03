import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuTienda {

    private static final String RUTA_ARCHIVO = "C:\\Users\\IRIVER1\\IdeaProjects\\TallerPracticoFinal\\productosBelleza.txt";
    private InventarioBelleza inventario;
    private Scanner scanner;

    public MenuTienda() {
        this.inventario = new InventarioBelleza();
        this.scanner = new Scanner(System.in);
        inventario.cargarProductosDesdeArchivo(RUTA_ARCHIVO);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n**************************************+*");
            System.out.println("*           Bienvenidos a                *");
            System.out.println("*          Eterna Juventud               *");
            System.out.println("*     Tu tienda virtual de belleza       *");
            System.out.println("******************************************");
            System.out.println("1. Agregar producto");
            System.out.println("2. Actualizar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Buscar productos");
            System.out.println("5. Generar reporte de inventario");
            System.out.println("6. Ver cantidad de productos por categoría");
            System.out.println("7. Ver producto más caro");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    actualizarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    buscarProducto();
                    break;
                case 5:
                    generarReporte();
                    break;
                case 6:
                    cantidadDeProductosPorCategoria();
                    break;
                case 7:
                    productoMasCaro();
                    break;
                case 8:
                    System.out.println("¡Gracias por visitar Eterna Juventud!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 8);
    }

    private void agregarProducto() {
        System.out.print("Ingrese el Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la Categoría: ");
        String categoria = scanner.nextLine();

        double precio = obtenerDoubleValido();  // Validación para el precio
        int cantidad = obtenerEnteroValido();  // Validación para la cantidad

        scanner.nextLine();  // Limpiar el buffer
        System.out.print("Ingrese la Descripción: ");
        String descripcion = scanner.nextLine();

        int id = obtenerIdUnico(); // Verificación de ID único

        Producto producto = new Producto(id, nombre, categoria, precio, cantidad, descripcion);
        inventario.agregarProducto(producto);
        inventario.guardarProductosEnArchivo(RUTA_ARCHIVO);
        System.out.println("El Producto fue agregado exitosamente.");
    }

    // Validar que el precio sea un número decimal válido
    private double obtenerDoubleValido() {
        while (true) {
            try {
                System.out.print("Ingrese el Precio del producto: ");
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("¡Error! Por favor ingrese un número válido para el precio.");
            }
        }
    }

    // Validar que la cantidad sea un número entero válido
    private int obtenerEnteroValido() {
        while (true) {
            try {
                System.out.print("Ingrese la Cantidad del producto: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("¡Error! Por favor ingrese un número entero válido para la cantidad.");
            }
        }
    }

    // Método para verificar si el ID ingresado ya existe en el inventario
    private boolean productoExiste(int id) {
        for (Producto producto : inventario.getProductos()) {
            if (producto.getId() == id) {
                return true;  // El ID ya existe
            }
        }
        return false;  // El ID es único
    }

    // Pedir un ID único para el producto
    private int obtenerIdUnico() {
        int id;
        while (true) {
            System.out.print("Ingrese el ID del producto (debe ser único): ");
            id = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            // Verificar si el ID ya existe
            if (!productoExiste(id)) {
                return id;  // Si el ID no existe, lo devolvemos
            } else {
                System.out.println("El ID ingresado ya existe. Por favor ingrese otro ID.");
            }
        }
    }

    private void actualizarProducto() {
        // Pedimos al usuario que ingrese el ID del producto que desea actualizar
        System.out.print("Ingrese el ID del producto que desea actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer de entrada

        // Verificamos si el producto con ese ID existe en el inventario
        Producto productoExistente = inventario.buscarProductoPorId(id);
        if (productoExistente == null) {  // Si el producto no existe
            System.out.println("El producto con ID " + id + " no existe.");
            return;  // Terminamos la función sin pedir más datos
        }

        // Si el producto existe, pedimos los nuevos datos
        System.out.print("Ingrese el Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la Nueva categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Ingrese el Nuevo precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese la Nueva cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer
        System.out.print("Ingrese la Nueva descripción: ");
        String descripcion = scanner.nextLine();

        // Intentamos actualizar el producto en el inventario
        boolean actualizado = inventario.actualizarProducto(id, nombre, categoria, precio, cantidad, descripcion);
        if (actualizado) {
            // Si la actualización fue exitosa, guardamos los productos en el archivo
            inventario.guardarProductosEnArchivo(RUTA_ARCHIVO);
            System.out.println("El Producto fue actualizado exitosamente.");
        } else {
            // Si por alguna razón no se pudo actualizar, mostramos un mensaje de error
            System.out.println("Error al actualizar el producto.");
        }
    }

    private void eliminarProducto() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        boolean eliminado = inventario.eliminarProducto(id);
        if (eliminado) {
            inventario.guardarProductosEnArchivo(RUTA_ARCHIVO);
            System.out.println("El producto fue eliminado exitosamente.");
        } else {
            System.out.println("El Producto no existe.");
        }
    }

    // Métodode búsqueda que muestra las opciones
    private void buscarProducto() {
        System.out.println("¿Cómo desea buscar el producto?");
        System.out.println("1. Buscar por Categoría");
        System.out.println("2. Buscar por Nombre");
        System.out.println("3. Buscar por ID");
        System.out.print("Selecciona una opción: ");
        int opcionBusqueda = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        switch (opcionBusqueda) {
            case 1:
                buscarPorCategoria();
                break;
            case 2:
                buscarPorNombre();
                break;
            case 3:
                buscarPorId();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    // Métodopara buscar por categoría
    private void buscarPorCategoria() {
        System.out.print("Ingrese la categoría para buscar: ");
        String categoria = scanner.nextLine();

        System.out.println("Productos en la categoría '" + categoria + "':");
        boolean encontrado = false;
        for (Producto producto : inventario.getProductos()) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(producto);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron productos en esta categoría.");
        }
    }

    // Métodopara buscar por nombre
    private void buscarPorNombre() {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.println("Productos con el nombre '" + nombre + "':");
        boolean encontrado = false;
        for (Producto producto : inventario.getProductos()) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(producto);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron productos con este nombre.");
        }
    }

    // Métodopara buscar por ID
    private void buscarPorId() {
        System.out.print("Ingrese el ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        Producto producto = inventario.buscarProductoPorId(id);
        if (producto != null) {
            System.out.println("Producto encontrado: ");
            System.out.println(producto);
        } else {
            System.out.println("No se encontró un producto con ese ID.");
        }
    }

    private void generarReporte() {
        // Reporte de todos los productos
        System.out.println("\nReporte de todos los productos:");
        for (Producto producto : inventario.getProductos()) {
            System.out.println(producto);
        }
    }

    private void cantidadDeProductosPorCategoria() {
        // Cantidad de productos por categoría
        System.out.print("Ingrese la categoría para contar productos: ");
        String categoria = scanner.nextLine();
        int count = 0;
        for (Producto producto : inventario.getProductos()) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                count++;
            }
        }
        System.out.println("Cantidad de productos en la categoría '" + categoria + "': " + count);
    }

    private void productoMasCaro() {
        Producto productoCaro = null;
        for (Producto producto : inventario.getProductos()) {
            if (productoCaro == null || producto.getPrecio() > productoCaro.getPrecio()) {
                productoCaro = producto;
            }
        }
        if (productoCaro != null) {
            System.out.println("El producto más caro es: " + productoCaro);
        } else {
            System.out.println("No hay productos en el inventario.");
        }
    }
}