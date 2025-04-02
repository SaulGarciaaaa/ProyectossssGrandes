/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej1bd.prg;

import java.util.Scanner;

/**
 *
 * @author salsa
 */
public class Teclado {
    public static String compNombre(String cadena){
        boolean comp=false;
        String nombre;
        do{
            System.out.println(cadena);
            nombre=new Scanner(System.in).nextLine();
            if (nombre.matches("[A-ZÑa-zñÁÉÍÓÚáéíúó ]{1,12}")) {
                comp=true;
            }else{
                System.out.println("Introduce un nombre de entre 1 y 12 letras");
            }
        }while(!comp);
        return nombre;
    }
    public static String compContraseña(String cadena){
        boolean comp=false;
        String contraseña;
        do{
            System.out.println(cadena);
            contraseña=new Scanner(System.in).nextLine();
            if (contraseña.matches("[A-ZÑa-zñÁÉÍÓÚáéíúó 0-9]{1,60}")) {
                comp=true;
            }else{
                System.out.println("Introduce una contraseña valida");
            }
        }while(!comp);
        return contraseña;
    }
    public static String compEmail(String cadena){
        boolean comp=false;
        String email;
        do{
            System.out.println(cadena);
            email=new Scanner(System.in).nextLine();
            if (email.matches("[A-ZÑa-zñÁÉÍÓÚáéíúó 1-9]{5,20}[@gmail.com]")) {
                comp=true;
            }else{
                System.out.println("Introduce un nombre de entre 1 y 12 letras");
            }
        }while(!comp);
        return email;
    }
    public static int compNum(String cadena){
        boolean comp=false;
        int numero;
        do{
            System.out.println(cadena);
            numero=new Scanner(System.in).nextInt();
            if (numero>0) {
                comp=true;
            }else{
                System.out.println("Introduce un numero positivo");
            }
        }while(!comp);
        return numero;
    }
    public static boolean compResultado(String cadena){
        boolean estado = false;
        String caso;
            System.out.println(cadena);
            caso=new Scanner(System.in).nextLine();
            if (caso.matches("(SI|si)")) {
                estado=true;
            }
        return estado;
    }
}
