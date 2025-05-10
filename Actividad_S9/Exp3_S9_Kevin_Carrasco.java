package com.mycompany.actividades.Actividad_S9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exp3_S9_Kevin_Carrasco {
    
}

// Clase para representar a un Cliente
class Cliente {
    private String nombre;
    private int edad;
    private String tipoCliente; // "Normal", "Niño", "Mujer", "Estudiante", "TerceraEdad"
    private boolean esEstudiante;
    private boolean esMujer;
    
    public Cliente(String nombre, int edad, boolean esMujer, boolean esEstudiante) {
        this.nombre = nombre;
        this.edad = edad;
        this.esMujer = esMujer;
        this.esEstudiante = esEstudiante;
        
        // Determinar tipo de cliente para aplicar descuento
        if (edad < 12) {
            this.tipoCliente = "Niño";
        } else if (edad >= 65) {
            this.tipoCliente = "TerceraEdad";
        } else if (esEstudiante) {
            this.tipoCliente = "Estudiante";
        } else if (esMujer) {
            this.tipoCliente = "Mujer";
        } else {
            this.tipoCliente = "Normal";
        }
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public String getTipoCliente() {
        return tipoCliente;
    }
    
    public double getDescuento() {
        switch (tipoCliente) {
            case "Niño":
                return 0.10; // 10% de descuento
            case "Mujer":
                return 0.20; // 20% de descuento
            case "Estudiante":
                return 0.15; // 15% de descuento
            case "TerceraEdad":
                return 0.25; // 25% de descuento
            default:
                return 0.0; // Sin descuento
        }
    }
}

// Clase para representar un Asiento en el teatro
class Asiento {
    private String ubicacion; // "VIP", "Palco", "PlateaBaja", "PlateaAlta", "Galeria"
    private int fila;
    private int numero;
    private boolean ocupado;
    private double precioBase;
    
    public Asiento(String ubicacion, int fila, int numero, double precioBase) {
        this.ubicacion = ubicacion;
        this.fila = fila;
        this.numero = numero;
        this.ocupado = false;
        this.precioBase = precioBase;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public int getFila() {
        return fila;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public boolean isOcupado() {
        return ocupado;
    }
    
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    public double getPrecioBase() {
        return precioBase;
    }
    
    @Override
    public String toString() {
        return "Asiento " + fila + "-" + numero + " (" + ubicacion + ")";
    }
}

// Clase para representar una Entrada
class Entrada {
    private Cliente cliente;
    private Asiento asiento;
    private double precioFinal;
    private String fechaFuncion;
    private String horaFuncion;
    private String idEntrada;
    private static int contadorEntradas = 1000;
    
    public Entrada(Cliente cliente, Asiento asiento, String fechaFuncion, String horaFuncion) {
        this.cliente = cliente;
        this.asiento = asiento;
        this.fechaFuncion = fechaFuncion;
        this.horaFuncion = horaFuncion;
        this.idEntrada = "TM-" + contadorEntradas++;
        
        // Calcular precio final aplicando descuento
        this.precioFinal = asiento.getPrecioBase() * (1 - cliente.getDescuento());
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public Asiento getAsiento() {
        return asiento;
    }
    
    public double getPrecioFinal() {
        return precioFinal;
    }
    
    public String getFechaFuncion() {
        return fechaFuncion;
    }
    
    public String getHoraFuncion() {
        return horaFuncion;
    }
    
    public String getIdEntrada() {
        return idEntrada;
    }
    
    public void imprimirBoleta() {
        System.out.println("\n===== TEATRO MORO - BOLETA =====");
        System.out.println("ID Entrada: " + idEntrada);
        System.out.println("Fecha: " + fechaFuncion + " - Hora: " + horaFuncion);
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Tipo de cliente: " + cliente.getTipoCliente());
        System.out.println("Ubicación: " + asiento.getUbicacion());
        System.out.println("Asiento: Fila " + asiento.getFila() + ", Número " + asiento.getNumero());
        System.out.println("Precio base: $" + asiento.getPrecioBase());
        System.out.println("Descuento aplicado: " + (cliente.getDescuento() * 100) + "%");
        System.out.println("Precio final: $" + precioFinal);
        System.out.println("==============================");
    }
}

// Clase para gestionar el teatro y sus funciones
class Teatro {
    private String nombre;
    private Asiento[][] asientos;
    private int totalFilas;
    private int asientosPorFila;
    
    public Teatro(String nombre, int totalFilas, int asientosPorFila) {
        this.nombre = nombre;
        this.totalFilas = totalFilas;
        this.asientosPorFila = asientosPorFila;
        this.asientos = new Asiento[totalFilas][asientosPorFila];
        
        inicializarAsientos();
    }
    
    private void inicializarAsientos() {
        // Distribución de precios y ubicaciones
        // Las primeras filas son VIP, luego Palco, etc.
        for (int fila = 0; fila < totalFilas; fila++) {
            for (int num = 0; num < asientosPorFila; num++) {
                String ubicacion;
                double precio;
                
                if (fila < 2) {
                    ubicacion = "VIP";
                    precio = 50000;
                } else if (fila < 5) {
                    ubicacion = "Palco";
                    precio = 40000;
                } else if (fila < 10) {
                    ubicacion = "PlateaBaja";
                    precio = 30000;
                } else if (fila < 15) {
                    ubicacion = "PlateaAlta";
                    precio = 20000;
                } else {
                    ubicacion = "Galeria";
                    precio = 10000;
                }
                
                asientos[fila][num] = new Asiento(ubicacion, fila + 1, num + 1, precio);
            }
        }
    }
    
    public void mostrarDisponibilidad() {
        System.out.println("\nDisponibilidad de asientos en " + nombre + ":");
        System.out.println("Leyenda: [O] = Disponible, [X] = Ocupado");
        
        for (int fila = 0; fila < totalFilas; fila++) {
            System.out.print("Fila " + (fila + 1) + ": ");
            for (int num = 0; num < asientosPorFila; num++) {
                if (asientos[fila][num].isOcupado()) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[O] ");
                }
            }
            System.out.print(" - " + asientos[fila][0].getUbicacion());
            System.out.println(" ($" + asientos[fila][0].getPrecioBase() + ")");
        }
    }
    
    public Asiento seleccionarAsiento(int fila, int numero) throws Exception {
        if (fila < 1 || fila > totalFilas || numero < 1 || numero > asientosPorFila) {
            throw new Exception("Asiento inválido. Por favor, seleccione un asiento válido.");
        }
        
        Asiento asiento = asientos[fila - 1][numero - 1];
        if (asiento.isOcupado()) {
            throw new Exception("El asiento seleccionado ya está ocupado. Por favor, elija otro.");
        }
        
        return asiento;
    }
    
    public void ocuparAsiento(int fila, int numero) {
        asientos[fila - 1][numero - 1].setOcupado(true);
    }
    
    public void mostrarPreciosPorZona() {
        System.out.println("\nPrecios por zona:");
        System.out.println("VIP: $50,000");
        System.out.println("Palco: $40,000");
        System.out.println("Platea Baja: $30,000");
        System.out.println("Platea Alta: $20,000");
        System.out.println("Galería: $10,000");
    }
}

// Clase para gestionar el sistema de venta
class SistemaVenta {
    private Teatro teatro;
    private List<Entrada> entradasVendidas;
    private Scanner scanner;
    
    public SistemaVenta(Teatro teatro) {
        this.teatro = teatro;
        this.entradasVendidas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciar() {
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n===== SISTEMA DE VENTA DE ENTRADAS - TEATRO MORO =====");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Ver disponibilidad de asientos");
            System.out.println("3. Ver precios por zona");
            System.out.println("4. Ver entradas vendidas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    comprarEntrada();
                    break;
                case 2:
                    teatro.mostrarDisponibilidad();
                    break;
                case 3:
                    teatro.mostrarPreciosPorZona();
                    break;
                case 4:
                    mostrarEntradasVendidas();
                    break;
                case 5:
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema de venta de Teatro Moro!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
    
    private void comprarEntrada() {
        try {
            // Recopilar información del cliente
            System.out.println("\n== COMPRA DE ENTRADA ==");
            System.out.print("Nombre del cliente: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Edad: ");
            int edad = validarEntradaNumericaPositiva();
            
            System.out.print("¿Es mujer? (S/N): ");
            boolean esMujer = scanner.nextLine().toUpperCase().startsWith("S");
            
            System.out.print("¿Es estudiante? (S/N): ");
            boolean esEstudiante = scanner.nextLine().toUpperCase().startsWith("S");
            
            Cliente cliente = new Cliente(nombre, edad, esMujer, esEstudiante);
            
            // Mostrar disponibilidad de asientos
            teatro.mostrarDisponibilidad();
            
            // Selección de asiento
            System.out.print("\nSeleccione fila: ");
            int fila = validarEntradaNumericaPositiva();
            
            System.out.print("Seleccione número de asiento: ");
            int numeroAsiento = validarEntradaNumericaPositiva();
            
            Asiento asientoSeleccionado = teatro.seleccionarAsiento(fila, numeroAsiento);
            
            // Información de la función
            System.out.print("Fecha de la función (DD/MM/YYYY): ");
            String fecha = scanner.nextLine();
            
            System.out.print("Hora de la función (HH:MM): ");
            String hora = scanner.nextLine();
            
            // Crear entrada y marcar asiento como ocupado
            Entrada nuevaEntrada = new Entrada(cliente, asientoSeleccionado, fecha, hora);
            teatro.ocuparAsiento(fila, numeroAsiento);
            entradasVendidas.add(nuevaEntrada);
            
            // Imprimir boleta
            nuevaEntrada.imprimirBoleta();
            
            System.out.println("\n¡Compra realizada con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private int validarEntradaNumericaPositiva() {
        int valor = 0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            try {
                valor = Integer.parseInt(scanner.nextLine());
                if (valor <= 0) {
                    System.out.print("Por favor, ingrese un número positivo: ");
                } else {
                    entradaValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número válido: ");
            }
        }
        
        return valor;
    }
    
    private void mostrarEntradasVendidas() {
        if (entradasVendidas.isEmpty()) {
            System.out.println("\nNo hay entradas vendidas.");
            return;
        }
        
        System.out.println("\n=== ENTRADAS VENDIDAS ===");
        for (Entrada entrada : entradasVendidas) {
            System.out.println("ID: " + entrada.getIdEntrada() + 
                              " | Cliente: " + entrada.getCliente().getNombre() + 
                              " | Asiento: " + entrada.getAsiento().toString() + 
                              " | Precio: $" + entrada.getPrecioFinal());
        }
    }
}