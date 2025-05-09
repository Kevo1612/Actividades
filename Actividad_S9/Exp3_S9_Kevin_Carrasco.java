package com.mycompany.actividades.Actividad_S9;

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