package com.mycompany.actividades.Actividad_S5;

import java.util.ArrayList;
import java.util.Scanner;

public class Exp2_S5_Kevin_Carrasco{
    public static void buscarEntradaPorNumero(int numero, ArrayList<Entrada> inputEntrada){
        for(int i = 0; i < inputEntrada.size();i++){
            if(inputEntrada.get(i).numero == numero){
                System.out.printf("Su entrada es la: %d\n", inputEntrada.get(i).numero);
                System.out.printf("Su entrada esta en sector: %s\n", inputEntrada.get(i).ubicacion);
                System.out.printf("Su entrada esta en sector: %d\n", inputEntrada.get(i).precio);
            }
        }
    }
    public static void main (String[] args){
        Scanner scan = new Scanner (System.in);
        int opcion = 0;
        int ubicacion = 0;
        ArrayList<Entrada> entrada = new ArrayList<>();
        boolean continuarCiclo = true;
        //Menu para comprar entrada
        while(continuarCiclo){
            System.out.println("1.Comprar entrada");
            System.out.println("2.Salir");
            System.out.println("Seleccione 1 o 2: ");
            opcion = scan.nextInt();
            //Seleccion de ubicacion
            if (opcion==1){
                System.out.println("//Bienvenido//");
                System.out.println("Seleccione la ubicacion de su entrada");
                System.out.println("1. Vip");
                System.out.println("2. Platea");
                System.out.println("3. General");
                opcion = scan.nextInt();
                switch (opcion){
                    case 1:
                        System.out.println("Elegiste ubicacion en Vip");
                        System.out.println("Seleccione -Comprar entrada- para agregar otra...");
                        System.out.println("Seleccione -Salir- para continuar la compra...");
                        Contador.registrarEntrada();
                        entrada.add(new Entrada("Vip",30000, Contador.getCantidadEntradas()));
                        Contador.sumarTotal(30000);
                        break;
                    case 2:                        
                        System.out.println("Elegiste ubicacion en Platea");
                        System.out.println("Seleccione -Comprar entrada- para agregar otra...");
                        System.out.println("Seleccione -Salir- para continuar la compra...");
                        Contador.registrarEntrada();
                        entrada.add(new Entrada("Platea",20000, Contador.getCantidadEntradas()));
                        Contador.sumarTotal(20000);
                        break;
                    case 3:                        
                        System.out.println("Elegiste ubicacion en General");
                        System.out.println("Seleccione -Comprar entrada- para agregar otra...");
                        System.out.println("Seleccione -Salir- para continuar la compra...");
                        Contador.registrarEntrada();
                        entrada.add(new Entrada("General",10000, Contador.getCantidadEntradas()));
                        Contador.sumarTotal(10000);
                        break;
                default:
                    System.out.println("Ubicacion invalida, reinicie");
                }
            } else if (opcion == 2){
                if (Contador.getCantidadEntradas() == 0){
                    System.out.println("No compraste entradas");
                    System.exit(1);
                }
                System.out.println("Continuando...");
                continuarCiclo = false;
            } else {
                System.out.println("Opcion invalida");
                System.exit(1);
            }                 
        }
        //Asignacion del descuento por categoria de cliente
        System.out.println("¿Eres EStudiante o Tercera edad?");
        System.out.println("1.-Estudiante");
        System.out.println("2.-Tercera Edad");
        System.out.println("3.-Ninguno");
        opcion = scan.nextInt();
        if (opcion == 1){
            Contador.asignarDescuento(0.9);
        } else if (opcion == 2){
            Contador.asignarDescuento(0.85);
        } else if(opcion == 3){
            Contador.asignarDescuento(0.0);
            System.out.println("No tiene descuento");
        } else{
            System.out.println("Opcion Invalida");
            System.exit(1);
        }
        //Asignacion de descuento por promocion
        //Cada 3 entradas se aplica 10% de descuento
        //Y solo se aplica el descuento mayor
        int descuentoPromocion;
        descuentoPromocion = 1 - ((Contador.getCantidadEntradas() / 3) / 10);
        if(Contador.getDescuento() < descuentoPromocion){
            Contador.asignarDescuento(descuentoPromocion);
        }
        //Eliminacion de ultima entrada
        //entrada.remove(scan.nextInt() - 1);
        System.out.printf("Total de Entradas: %d\n", Contador.getCantidadEntradas());
        System.out.println("Ingrese numero de entrada que busca");
        buscarEntradaPorNumero(scan.nextInt(), entrada);
        scan.close();        
    }   
}
class Contador {
    static int cantidadEntradas = 0;
    static double descuento = 0.0;
    static double totalIngresos = 0.0;
    public static void registrarEntrada(){
        cantidadEntradas++;
    }
    public static void asignarDescuento(double cantidadDescuento){
        descuento = cantidadDescuento;
    }
    public static void sumarTotal(double cantidad){
        totalIngresos = totalIngresos + cantidad;
    }
    public static int getCantidadEntradas(){
        return cantidadEntradas;
    }
    public static double getDescuento(){
        return descuento;
    }
}
class Entrada{
    String ubicacion;
    double precio;
    int numero;
    public Entrada(String ubicacion, double precio, int numero){
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.numero = numero;
    }
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }    
    public double getPrecio(){
        return this.precio;
    }
    public void mostrarInfo(){
        System.out.printf("Ubicacion de la entrada: ", this.ubicacion);
        System.out.printf("Precio de la entrada: ", this.precio);
        System.out.printf("Numero de la entrada: ", this.numero);
    }
}