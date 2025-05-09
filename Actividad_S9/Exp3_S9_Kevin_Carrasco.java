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