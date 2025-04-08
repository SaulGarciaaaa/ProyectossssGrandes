/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio2;

import java.util.List;

/**
 *
 * @author salsa
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        MetodosBD bd = new MetodosBD();
        int opcion;
        do {
            System.out.println("1. Insertar paciente");
            System.out.println("2. Crear visita");
            System.out.println("3. Mostrar agenda del día");
            System.out.println("4. Mostrar visitas de un paciente");
            System.out.println("5. Salir");
            opcion =Teclado.compNum("Introduce uan opcion 1-5");
            switch (opcion) {
                case 1 -> bd.insertarpaciente(Teclado.validarDNI("Introduce el dni del usuario"));
                case 2->bd.crearvisitas(Teclado.validarDNI("Introduce el dni del usuario"));
                case 3 -> bd.mostraragenda(Teclado.compFecha("Introduce una fecha"));
                case 4-> bd.visitaspaciente(Teclado.validarDNI("Introduce el dni del usuario"));
                case 5->{
                    System.out.println("Saliendo del programa");
                    AccesoBaseDatos.getInstance().cerrar();
                }  
                default->
                    System.out.println("Introduce una opcion valida 1-5");
                
            }
        } while (opcion != 5);
    } 
}
