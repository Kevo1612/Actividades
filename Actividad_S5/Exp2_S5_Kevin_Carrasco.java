package com.mycompany.actividades.Actividad_S5;

import java.util.Scanner;

public class Exp2_S5_Kevin_Carrasco {
    public static void main (String[] args){
        Scanner scan = new Scanner (System.in);
        int opcion;
        int ubicacion;
        boolean esEstudiante = true;
        boolean esTerceraEdad = true;
        for (;;){
            System.out.println("Bienvenido");
            System.out.println("Seleccione la ubicacion de su entrada");
            System.out.println("1. Vip");
            System.out.println("2. Platea");
            System.out.println("3. Palco");
            opcion = scan.nextInt();
            if (opcion >= 1 && opcion <=3){
                System.out.println("Eres estudiante?");
                opcion = scan.nextInt();                
            } else{
                System.out.println("Opcion invalida, volviendo al inicio"); 
                break;
            } 
        }        
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