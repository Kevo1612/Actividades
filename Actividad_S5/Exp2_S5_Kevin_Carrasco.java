package com.mycompany.actividades.Actividad_S5;

import java.util.ArrayList;
import java.util.Scanner;

public class Exp2_S5_Kevin_Carrasco {
    public static void main (String[] args){
        Scanner scan = new Scanner (System.in);
        int opcion;
        int ubicacion;
        boolean esEstudiante = true;
        boolean esTerceraEdad = true;
        ArrayList<Entrada> entradas = new ArrayList<>();
        for (;;){
            System.out.println("Bienvenido");
            System.out.println("Seleccione la ubicacion de su entrada");
            System.out.println("1. Vip");
            System.out.println("2. Platea");
            System.out.println("3. General");
            opcion = scan.nextInt();
            switch (opcion){
                case 1:
                    Contador.registrarEntradas();
                    System.out.println("Elegiste ubicacion en Vip");                    
                    entradas.add(new Entrada("Vip",30000));
                    break;
                case 2:
                    Contador.registrarEntradas();
                    System.out.println("Elegiste ubicacion en Platea");
                    entradas.add(new Entrada("Platea",20000));
                    break;
                case 3:
                    Contador.registrarEntradas();
                    System.out.println("Elegiste ubicacion en General");
                    entradas.add(new Entrada("General",10000));
                    break;                
                default:
                    System.out.println("Ubicacion invalida, reinicie");
            }
                break;             
        }
        System.out.printf("Total de Entradas: %d\n", Contador.getCantidadEntradas());
        scan.close();        
    }    
}
class Entrada {
    String ubicacionEntrada;
    double precioEntrada;
    public Entrada (String ubicacionEntrada, double precioEntrada){ 
        this.ubicacionEntrada = ubicacionEntrada;
        this.precioEntrada = precioEntrada;
    }
    public void setUbicacionEntrada(String ubicacionEntrada){
        this.ubicacionEntrada = ubicacionEntrada;
    }
    public void setPrecioEntrada(double precioEntrada){
        this.precioEntrada = precioEntrada;
    }
}
class Contador {
    static int cantidadEntradas = 0;
    public static void registrarEntradas(){
        cantidadEntradas++;
    }
    public static int getCantidadEntradas(){
        return cantidadEntradas;
    }
}
class DetalleDeEntrada{
    String ubicacion;
    int precio;
    int numeroEntrada;
    public DetalleDeEntrada(String ubicacion, int precio, int numeroEntrada){
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.numeroEntrada = numeroEntrada;                
    }
    public void mostrarInfoEntrada(){
    }
}