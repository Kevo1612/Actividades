package com.mycompany.actividades.Actividad_S5;

import java.util.ArrayList;
import java.util.Scanner;

public class Exp2_S5_Kevin_Carrasco {
    public static void main (String[] args){
        Scanner scan = new Scanner (System.in);
        int opcion;
        int ubicacion;
        ArrayList<Entrada> entradas = new ArrayList<>();
        ArrayList<DetalleDeEntrada> detalles = new ArrayList<>();
        for (;;){
            System.out.println("1.Comprar entrada");
            System.out.println("2.Salir");
            System.out.println("Seleccione 1 o 2: ");
            opcion = scan.nextInt();
            if (opcion==1){
                System.out.println("//Bienvenido//");
                System.out.println("Seleccione la ubicacion de su entrada");
                System.out.println("1. Vip");
                System.out.println("2. Platea");
                System.out.println("3. General");
                opcion = scan.nextInt();
                switch (opcion){
                    case 1:
                        Contador.registrarEntradas();
                        System.out.println("Elegiste ubicacion en Vip");
                        System.out.println("Seleccione -Comprar entrada- para agregar otra...");
                        System.out.println("Seleccione -Salir- para continuar la compra...");
                        entradas.add(new Entrada("Vip",30000));
                        detalles.add(new DetalleDeEntrada("Vip", 30000, 1));
                        break;
                    case 2:
                        Contador.registrarEntradas();
                        System.out.println("Elegiste ubicacion en Platea");
                        System.out.println("Seleccione -Comprar entrada- para agregar otra...");
                        System.out.println("Seleccione -Salir- para continuar la compra...");
                        entradas.add(new Entrada("Platea",20000));
                        detalles.add(new DetalleDeEntrada("Platea", 20000, 1));
                        break;
                    case 3:
                        Contador.registrarEntradas();
                        System.out.println("Elegiste ubicacion en General");
                        System.out.println("Seleccione -Comprar entrada- para agregar otra...");
                        System.out.println("Seleccione -Salir- para continuar la compra...");
                        entradas.add(new Entrada("General",10000));
                        detalles.add(new DetalleDeEntrada("General", 10000, 1));
                        break;                
                default:
                    System.out.println("Ubicacion invalida, reinicie");
                }
            } else if (opcion == 2){
                System.out.println("Continuando...");
                break;
            } else {
                System.out.println("Opcion invalida");
                System.exit(0);
                break;
            }                 
        }
        System.out.println("¿Eres EStudiante o Tercera edad?");
            System.out.println("1.-Estudiante");
            System.out.println("2.-Tercera Edad");
            opcion = scan.nextInt();
           /* if (opcion == 1){
                precioEntrada = precioEntrada * 0.9;                
            } else if (opcion == 2){
                precioEntrada = precioEntrada * 0.85;
            }*/
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
    double precio;
    int numeroEntrada;
    public DetalleDeEntrada(String ubicacion, double precio, int numeroEntrada){
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.numeroEntrada = numeroEntrada;                
    }
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public void setNumeroEntrada(int numeroEntrada){
        this.numeroEntrada = numeroEntrada;
    }    
    public double getPrecio(){
        return this.precio;
    }
    public void mostrarInfoEntrada(){
        System.out.printf("Ubicacion de la entrada: ", this.ubicacion);
        System.out.printf("Precio de la entrada: ", this.precio);
        System.out.printf("Numero de la entrada: ", this.numeroEntrada);
    }
}