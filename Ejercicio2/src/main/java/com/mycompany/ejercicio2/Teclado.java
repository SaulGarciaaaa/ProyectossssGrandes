/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author salsa
 */
public class Teclado {
    public static int compNum(String cadena) {
        boolean comp = false;
        int num = 0;
        do {
            try {
                System.out.println(cadena);
                num = new Scanner(System.in).nextInt();
                if (num > 0) {
                    comp = true;
                } else {
                    System.out.println("Introduce un numero entero mayor a 0");
                }
            } catch (java.lang.ArithmeticException a) {
                System.out.println("Introduce un numero entero valido");
            }
        } while (!comp);
        return num;
    }

    public static String compNombre(String cadena) {
        boolean comp = false;
        String nombre = null;
        do {
            System.out.println(cadena);
            nombre = new Scanner(System.in).nextLine();
            if (nombre.matches("[A-ZÑa-zñÁÉÍÓÚáéíúó ]{3,15}")) {
                comp = true;
            } else {
                System.out.println("Introduce nombre valido");
            }

        } while (!comp);
        return nombre;
    }

    public static String compTelefono(String cadena) {
        boolean comp = false;
        String nombre = null;
        do {
            System.out.println(cadena);
            nombre = new Scanner(System.in).nextLine();
            if (nombre.matches("[0-9]{9}")) {
                comp = true;
            } else {
                System.out.println("Introduce un telefono valido");
            }
        } while (!comp);
        return nombre;
    }

    public static String validarDNI(String cadena) {
        String guarda = null;
        boolean comp = false;
        do {
            try {
                System.out.println(cadena);
                guarda = new Scanner(System.in).nextLine().toUpperCase();
                if (guarda.length() != 9) {
                    System.out.println("DNI incorrecto. El DNI debe tener 9 caracteres.");
                    comp = false;
                } else {
                    String numero = guarda.substring(0, 8);
                    char letra = guarda.charAt(8);
                    String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";
                    int resto = Integer.parseInt(numero) % 23;
                    if (letrasDNI.charAt(resto) != letra) {
                        System.out.println("DNI incorrecto. La letra no es correcta.");
                        comp = false;
                    } else {
                        comp = true;
                    }
                }
            } catch (java.lang.NumberFormatException a) {
                System.out.println("Introduce 8 numeros y una letra en mayusculas");
            }
        } while (!comp);
        return guarda;
    }

    public static LocalDate compFecha(String cadena) {
        LocalDate L1 = null;
        boolean comp = false;
        do {
            try {
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                L1 = LocalDate.parse(new Scanner(System.in).nextLine(), f);
                comp = true;
            } catch (java.time.format.DateTimeParseException a) {
                System.out.println("Introduce una fecha valida");
            }
        } while (!comp);
        return L1;
    }
    public static Tratamiento compTratamiento(String cadena){
        Tratamiento T1=null;
        boolean comp=false;
        String datos=null;
        do{
            System.out.println(cadena); 
            datos=new Scanner(System.in).nextLine().toLowerCase();
            switch(datos){
                case "implantologia"->{
                    T1=T1.Implantologia;
                    comp=true;
                }
                case "ortodoncia"->{
                    T1=T1.Ortodoncia;
                    comp=true;
                }   
                case "periodoncia"->{
                    T1=T1.Periodoncia;
                    comp=true;
                }
                case "estetica"->{
                    T1=T1.Estetica;
                    comp=true;
                }
                case "extracciones"->{
                    T1=T1.Extracciones;
                    comp=true;
                 }
                default->
                    System.out.println("Introduce una opcion valida Implantologia,Ortodoncia,Periodoncia,Estetica,Extracciones");
            }
        }while(!comp);
        return T1;
    }
    public static Tratamiento compTratamientoInsertar(String tratamiento){
        Tratamiento T1=null;
        boolean comp=false;
        do{
            switch(tratamiento){
                case "implantologia"->{
                    T1=T1.Implantologia;
                    comp=true;
                }
                case "ortodoncia"->{
                    T1=T1.Ortodoncia;
                    comp=true;
                }   
                case "periodoncia"->{
                    T1=T1.Periodoncia;
                    comp=true;
                }
                case "estetica"->{
                    T1=T1.Estetica;
                    comp=true;
                }
                case "extracciones"->{
                    T1=T1.Extracciones;
                    comp=true;
                 }
                default->
                    System.out.println("Introduce una opcion valida Implantologia,Ortodoncia,Periodoncia,Estetica,Extracciones");
            }
        }while(!comp);
        return T1;
    }
}
