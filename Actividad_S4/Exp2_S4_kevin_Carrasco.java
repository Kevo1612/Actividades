package com.mycompany.actividades.Actividad_S4;

import java.util.ArrayList;
import java.util.Scanner;

public class Exp2_S4_kevin_Carrasco {
      
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        int opcion;
        int zona;
        int cantidadEntradas;
        boolean esEstudiante;
        boolean esTerceraEdad;
        double total = 0.0;
        ArrayList<Entrada> entradas = new ArrayList<>(); 
        for (;;){
            System.out.println("1. Comprar entrada");
            System.out.println("2. Salir");
            System.out.println("Seleccione 1 o 2: ");
            opcion = scan.nextInt();
            if (opcion==1){        
                System.out.println("Seleccione la zona del asiento: ");
                System.out.println("1. Vip");
                System.out.println("2. Platea");
                System.out.println("3. Palco");
                zona = scan.nextInt();
                if (zona  == 1){
                    entradas.add(new Entrada("Vip",30000));
                } else if (zona == 2){
                    entradas.add(new Entrada("Platea",20000));
                } else if (zona == 3){
                    entradas.add(new Entrada("Palco",10000));
                } else{
                    System.out.println("Zona no valida, seleccione una opcion disponible");
                }
            } else if (opcion == 2){
             System.out.println("Continuando...");
             break;
            }
        }
        System.out.println("Eres estudiante?(1=Si/0=No");
        opcion = scan.nextInt();
        if (opcion == 1){
            esEstudiante = true;
        } else{
            esEstudiante = false;
        }
        System.out.println("Eres tercera edad?(1=Si/0=No");
        opcion = scan.nextInt();
        if (opcion == 1){
            esTerceraEdad = true;
        } else{
            esTerceraEdad = false;
        }
        cantidadEntradas = entradas.size();
        while (cantidadEntradas > 0){
            double tempPrecio = entradas.get(cantidadEntradas - 1).getPrecioEntrada();
            if (esTerceraEdad){
                tempPrecio = tempPrecio * 0.85;
                entradas.get(cantidadEntradas - 1).setPrecioEntradaFinal(tempPrecio);
            }
            if (esEstudiante){
                tempPrecio = tempPrecio * 0.9;
                entradas.get(cantidadEntradas - 1).setPrecioEntradaFinal(tempPrecio);
            }
            entradas.get(cantidadEntradas - 1).printDetails();
            cantidadEntradas = cantidadEntradas - 1;
            total = total + tempPrecio;
        }
        System.out.printf("Total a pagar: $%.0f ", total);
    }
}
class Entrada {
    String tipoEntrada;
    double precioEntrada;
    double precioEntradaFinal;
    public Entrada(String tipoEntrada, double precioEntrada){
        this.tipoEntrada = tipoEntrada;
        this.precioEntrada = precioEntrada;
        this.precioEntradaFinal = precioEntradaFinal;
    } 
    public void setTipoEntrada(String tipoEntrada){
        this.tipoEntrada = tipoEntrada;
    }
    public void setPrecioEntrada(double precioEntrada){
        this.precioEntrada = precioEntrada;
    }
    public void setPrecioEntradaFinal(double precioEntradaFinal){
        this.precioEntradaFinal = precioEntradaFinal;
    }    
    public double getPrecioEntrada(){
        return this.precioEntrada;
    }
    public void printDetails() {
        System.out.printf("Ubicacion del asiento: %s\n", this.tipoEntrada);
        System.out.printf("Precio de la entrada: $%.0f\n", this.precioEntrada);
        System.out.printf("Precio de la entrada con descuento: $%.0f\n", this.precioEntradaFinal);
    }
}
