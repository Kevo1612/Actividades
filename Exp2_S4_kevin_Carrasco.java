package com.mycompany.actividadsumativa;

import java.util.Scanner;

public class Exp2_S4_kevin_Carrasco {
    public static void main (String[] args){
     Scanner scan = new Scanner(System.in);
     int opcion;
     for (;;){
         System.out.println("1. Comprar de entrada");
         System.out.println("2. Salir");
         System.out.println("Seleccione 1 o 2: ");
         opcion = scan.nextInt();
         if (opcion==1){        
             System.out.println("Seleccione la zona del asiento: ");
             System.out.println("1. Vip");
             System.out.println("2. Platea");
             System.out.println("3. Palco");
             opcion = scan.nextInt();
         }else if (opcion == 2){
             System.out.println("Saliendo del programa...");
             
         }else {
             System.out.println("Opcion invalida, intente denuevo");
         }
         break;
     }
     scan.close();
    }   
}
