package com.mycompany.actividades.Actividad_S8;

import java.util.ArrayList;
import java.util.Scanner;

public class Exp3_S8_Kevin_Carrasco {
    private static ArrayList<Entrada> entradas = new ArrayList<>();
    private static ArrayList<Venta> ventas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int contadorEntradas = 1;
    private static int contadorVentas = 1;
    private static int totalEntradasDisponibles = 100; 
    private static int totalEntradasVIP = 10; 
    private static int totalEntradasGeneral = 20; 

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE GESTIÓN DE ENTRADAS =====");
        System.out.println("1. Reservar entradas");
        System.out.println("2. Comprar entradas");
        System.out.println("3. Modificar una venta existente");
        System.out.println("4. Imprimir boleto");
        System.out.println("5. Salir");
        System.out.print("Ingrese una opción: ");
    }

    private static int obtenerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
            System.out.print("Ingrese una opción: ");
        }
        return scanner.nextInt();
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                reservarEntradas();
                break;
            case 2:
                comprarEntradas();
                break;
            case 3:
                modificarVenta();
                break;
            case 4:
                imprimirBoleto();
                break;
            case 5:
                System.out.println("¡Gracias por usar nuestro sistema!");
                break;
            default:
                System.out.println("Opción inválida. Por favor, intente nuevamente.");
        }
    }

    private static void reservarEntradas() {
        scanner.nextLine();

        System.out.println("\n===== RESERVA DE ENTRADAS =====");
        System.out.print("Nombre de la persona que reserva: ");
        String nombre = scanner.nextLine();

        System.out.print("Cantidad de entradas a reservar: ");
        int cantidad = obtenerEntero();

        System.out.print("Tipo de entrada (General/VIP): ");
        String tipo = scanner.next();

        if (tipo.equalsIgnoreCase("VIP")) {
            if (cantidad > totalEntradasVIP) {
                System.out.println("No hay suficientes entradas VIP disponibles. Entradas restantes: " + totalEntradasVIP);
                return;
            }
            totalEntradasVIP -= cantidad;
        } else if (tipo.equalsIgnoreCase("General")) {
            if (cantidad > totalEntradasGeneral) {
                System.out.println("No hay suficientes entradas General disponibles. Entradas restantes: " + totalEntradasGeneral);
                return;
            }
            totalEntradasGeneral -= cantidad;
        } else {
            System.out.println("Tipo de entrada inválido.");
            return;
        }

        Entrada entrada = new Entrada(contadorEntradas, nombre, cantidad, tipo, "Reservada");
        entradas.add(entrada);

        System.out.println("\nReserva realizada con éxito:");
        System.out.println("ID de Reserva: " + contadorEntradas);
        System.out.println("Nombre: " + nombre);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Tipo: " + tipo);
        System.out.println("Entradas VIP restantes: " + totalEntradasVIP);
        System.out.println("Entradas General restantes: " + totalEntradasGeneral);

        contadorEntradas++;
    }

    private static void comprarEntradas() {
        scanner.nextLine(); 

        System.out.println("\n===== COMPRA DE ENTRADAS =====");
        System.out.println("1. Comprar entrada nueva");
        System.out.println("2. Comprar una entrada reservada");
        System.out.print("Ingrese una opción: ");

        int opcionCompra = obtenerEntero();

        switch (opcionCompra) {
            case 1:
                comprarEntradaNueva();
                break;
            case 2:
                comprarEntradaReservada();
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    private static void comprarEntradaNueva() {
        scanner.nextLine(); 

        System.out.print("Nombre del comprador: ");
        String nombre = scanner.nextLine();

        System.out.print("Cantidad de entradas: ");
        int cantidad = obtenerEntero();

        System.out.print("Tipo de entrada (General/VIP): ");
        String tipo = scanner.next();

        System.out.print("Categoría del comprador (Normal/Estudiante/Tercera Edad): ");
        String categoria = scanner.next();

        if (tipo.equalsIgnoreCase("VIP")) {
            if (cantidad > totalEntradasVIP) {
                System.out.println("No hay suficientes entradas VIP disponibles. Entradas restantes: " + totalEntradasVIP);
                return;
            }
            totalEntradasVIP -= cantidad;
        } else if (tipo.equalsIgnoreCase("General")) {
            if (cantidad > totalEntradasGeneral) {
                System.out.println("No hay suficientes entradas General disponibles. Entradas restantes: " + totalEntradasGeneral);
                return;
            }
            totalEntradasGeneral -= cantidad;
        } else {
            System.out.println("Tipo de entrada inválido.");
            return;
        }

        double precio = calcularPrecio(tipo, cantidad, categoria);

        System.out.println("\nTotal a pagar: $" + precio);
        System.out.print("Confirmar compra (S/N): ");

        String confirmacion = scanner.next();
        if (confirmacion.equalsIgnoreCase("S")) {
            Venta venta = new Venta(contadorVentas, nombre, cantidad, tipo, precio);
            ventas.add(venta);

            System.out.println("\n¡Compra realizada con éxito!");
            System.out.println("ID de Venta: " + contadorVentas);
            System.out.println("Entradas VIP restantes: " + totalEntradasVIP);
            System.out.println("Entradas General restantes: " + totalEntradasGeneral);
            contadorVentas++;
        } else {
            System.out.println("Compra cancelada");
        }
    }

    private static void comprarEntradaReservada() {
        mostrarReservas();

        System.out.print("Ingrese el ID de la reserva que desea comprar: ");
        int idReserva = obtenerEntero();

        Entrada entradaReservada = null;
        for (Entrada entrada : entradas) {
            if (entrada.getId() == idReserva && entrada.getEstado().equals("Reservada")) {
                entradaReservada = entrada;
                break;
            }
        }

        if (entradaReservada != null) {
            System.out.print("Categoría del comprador (Normal/Estudiante/Tercera Edad): ");
            String categoria = scanner.next(); // Solicitar la categoría al usuario

            double precio = calcularPrecio(entradaReservada.getTipo(), entradaReservada.getCantidad(), categoria);

            System.out.println("\nDetalles de la reserva:");
            System.out.println("Nombre: " + entradaReservada.getNombre());
            System.out.println("Cantidad: " + entradaReservada.getCantidad());
            System.out.println("Tipo: " + entradaReservada.getTipo());
            System.out.println("Total a pagar: $" + precio);

            System.out.print("Confirmar compra (S/N): ");
            String confirmacion = scanner.next();

            if (confirmacion.equalsIgnoreCase("S")) {
                Venta venta = new Venta(contadorVentas, entradaReservada.getNombre(),
                        entradaReservada.getCantidad(), entradaReservada.getTipo(), precio);
                ventas.add(venta);

                entradaReservada.setEstado("Vendida");

                System.out.println("\n¡Compra realizada con éxito!");
                System.out.println("ID de Venta: " + contadorVentas);
                contadorVentas++;
            } else {
                System.out.println("Compra cancelada");
            }
        } else {
            System.out.println("Reserva no encontrada o ya está vendida");
        }
    }

    private static void mostrarReservas() {
        System.out.println("\n===== RESERVAS DISPONIBLES =====");
        boolean hayReservas = false;

        for (Entrada entrada : entradas) {
            if (entrada.getEstado().equals("Reservada")) {
                System.out.println("ID: " + entrada.getId() +
                        " | Nombre: " + entrada.getNombre() +
                        " | Cantidad: " + entrada.getCantidad() +
                        " | Tipo: " + entrada.getTipo());
                hayReservas = true;
            }
        }

        if (!hayReservas) {
            System.out.println("No hay reservas disponibles");
        }
    }

    private static double calcularPrecio(String tipo, int cantidad) {
        double precioUnitario;

        if (tipo.equalsIgnoreCase("VIP")) {
            precioUnitario = 10000.0;
        } else {
            precioUnitario = 5000.0;
        }

        return precioUnitario * cantidad;
    }

    private static double calcularPrecio(String tipo, int cantidad, String categoria) {
        double precioUnitario;

        if (tipo.equalsIgnoreCase("VIP")) {
            precioUnitario = 10000.0;
        } else {
            precioUnitario = 5000.0;
        }

        double precioTotal = precioUnitario * cantidad;

        // Aplicar descuentos
        if (categoria.equalsIgnoreCase("Estudiante")) {
            precioTotal *= 0.90; // 10% de descuento
        } else if (categoria.equalsIgnoreCase("Tercera Edad")) {
            precioTotal *= 0.85; // 15% de descuento
        }

        return precioTotal;
    }

    private static void modificarVenta() {
        mostrarVentas();

        System.out.print("Ingrese el ID de la venta a modificar: ");
        int idVenta = obtenerEntero();

        Venta ventaModificar = null;
        for (Venta venta : ventas) {
            if (venta.getId() == idVenta) {
                ventaModificar = venta;
                break;
            }
        }

        if (ventaModificar != null) {
            System.out.println("\n===== MODIFICAR VENTA =====");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar cantidad");
            System.out.println("3. Modificar tipo de entrada");
            System.out.println("4. Cancelar modificación");
            System.out.print("Ingrese una opción: ");

            int opcionModificar = obtenerEntero();
            scanner.nextLine();

            switch (opcionModificar) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    ventaModificar.setNombre(nuevoNombre);
                    System.out.println("Nombre modificado con éxito");
                    break;
                case 2:
                    System.out.print("Nueva cantidad: ");
                    int nuevaCantidad = obtenerEntero();

                    // Revertir la cantidad anterior
                    if (ventaModificar.getTipo().equalsIgnoreCase("VIP")) {
                        totalEntradasVIP += ventaModificar.getCantidad();
                    } else if (ventaModificar.getTipo().equalsIgnoreCase("General")) {
                        totalEntradasGeneral += ventaModificar.getCantidad();
                    }

                    // Validar la nueva cantidad
                    if (ventaModificar.getTipo().equalsIgnoreCase("VIP")) {
                        if (nuevaCantidad > totalEntradasVIP) {
                            System.out.println("No hay suficientes entradas VIP disponibles. Entradas restantes: " + totalEntradasVIP);
                            return;
                        }
                        totalEntradasVIP -= nuevaCantidad;
                    } else if (ventaModificar.getTipo().equalsIgnoreCase("General")) {
                        if (nuevaCantidad > totalEntradasGeneral) {
                            System.out.println("No hay suficientes entradas General disponibles. Entradas restantes: " + totalEntradasGeneral);
                            return;
                        }
                        totalEntradasGeneral -= nuevaCantidad;
                    }

                    System.out.print("Categoría del comprador (Normal/Estudiante/Tercera Edad): ");
                    String categoriaCantidad = scanner.next();

                    double nuevoPrecio = calcularPrecio(ventaModificar.getTipo(), nuevaCantidad, categoriaCantidad);
                    ventaModificar.setCantidad(nuevaCantidad);
                    ventaModificar.setPrecio(nuevoPrecio);
                    System.out.println("Cantidad modificada con éxito");
                    System.out.println("Nuevo precio: $" + nuevoPrecio);
                    break;
                case 3:
                    System.out.print("Nuevo tipo (General/VIP): ");
                    String nuevoTipo = scanner.nextLine();

                    // Revertir la cantidad anterior
                    if (ventaModificar.getTipo().equalsIgnoreCase("VIP")) {
                        totalEntradasVIP += ventaModificar.getCantidad();
                    } else if (ventaModificar.getTipo().equalsIgnoreCase("General")) {
                        totalEntradasGeneral += ventaModificar.getCantidad();
                    }

                    // Validar el nuevo tipo
                    if (nuevoTipo.equalsIgnoreCase("VIP")) {
                        if (ventaModificar.getCantidad() > totalEntradasVIP) {
                            System.out.println("No hay suficientes entradas VIP disponibles. Entradas restantes: " + totalEntradasVIP);
                            return;
                        }
                        totalEntradasVIP -= ventaModificar.getCantidad();
                    } else if (nuevoTipo.equalsIgnoreCase("General")) {
                        if (ventaModificar.getCantidad() > totalEntradasGeneral) {
                            System.out.println("No hay suficientes entradas General disponibles. Entradas restantes: " + totalEntradasGeneral);
                            return;
                        }
                        totalEntradasGeneral -= ventaModificar.getCantidad();
                    } else {
                        System.out.println("Tipo de entrada inválido.");
                        return;
                    }

                    System.out.print("Categoría del comprador (Normal/Estudiante/Tercera Edad): ");
                    String categoriaTipo = scanner.next();

                    double nuevoPrecioTipo = calcularPrecio(nuevoTipo, ventaModificar.getCantidad(), categoriaTipo);
                    ventaModificar.setTipo(nuevoTipo);
                    ventaModificar.setPrecio(nuevoPrecioTipo);
                    System.out.println("Tipo modificado con éxito");
                    System.out.println("Nuevo precio: $" + nuevoPrecioTipo);
                    break;
                case 4:
                    System.out.println("Modificación cancelada");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } else {
            System.out.println("ID de venta no encontrado");
        }
    }

    private static void mostrarVentas() {
        System.out.println("\n===== VENTAS REGISTRADAS =====");
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas");
            return;
        }

        for (Venta venta : ventas) {
            System.out.println("ID: " + venta.getId() +
                    " | Nombre: " + venta.getNombre() +
                    " | Cantidad: " + venta.getCantidad() +
                    " | Tipo: " + venta.getTipo() +
                    " | Precio: $" + venta.getPrecio());
        }
    }

    private static void imprimirBoleto() {
        mostrarVentas();

        System.out.print("Ingrese el ID de la venta para imprimir boleto: ");
        int idVenta = obtenerEntero();

        Venta ventaBoleto = null;
        for (Venta venta : ventas) {
            if (venta.getId() == idVenta) {
                ventaBoleto = venta;
                break;
            }
        }

        if (ventaBoleto != null) {
            System.out.println("\n========================================");
            System.out.println("            BOLETO DE ENTRADA           ");
            System.out.println("========================================");
            System.out.println("ID de Venta: " + ventaBoleto.getId());
            System.out.println("Nombre: " + ventaBoleto.getNombre());
            System.out.println("Cantidad: " + ventaBoleto.getCantidad());
            System.out.println("Tipo: " + ventaBoleto.getTipo());
            System.out.println("Precio Total: $" + ventaBoleto.getPrecio());
            System.out.println("Fecha: " + java.time.LocalDate.now());
            System.out.println("========================================");
            System.out.println("  ¡Gracias por su compra!  ");
            System.out.println("========================================");
        } else {
            System.out.println("ID de venta no encontrado");
        }
    }

    private static int obtenerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
            System.out.print("Ingrese nuevamente: ");
        }
        return scanner.nextInt();
    }
}

class Entrada {
    private int id;
    private String nombre;
    private int cantidad;
    private String tipo;
    private String estado;

    public Entrada(int id, String nombre, int cantidad, String tipo, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

class Venta {
    private int id;
    private String nombre;
    private int cantidad;
    private String tipo;
    private double precio;

    public Venta(int id, String nombre, int cantidad, String tipo, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}