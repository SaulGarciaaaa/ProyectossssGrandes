/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej1bd.prg;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author salsa
 */
public class Usuarios {
    private int id;
    private String usurname;
    private String password;
    private String email;

    public Usuarios(int id, String usurname, String password, String email) {
        this.id = id;
        this.usurname = usurname;
        this.password = password;
        this.email = email;
    }

    public Usuarios() {
        this.usurname = Teclado.compNombre("Introduce el nombre");
        this.password = Teclado.compContraseña("Introduce la contraseña");
        this.email = Teclado.compEmail("Introduce un email ");
    }

    public int getId() {
        return id;
    }

    public String getUsurname() {
        return usurname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return "Usuarios{" + "id=" + id + ", usurname=" + usurname + ", password=" + password + ", email=" + email + '}';
    }

    public static String getMD5(String input) {
         String  resultado= null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            for (byte b : digest) {
                resultado += String.format("%02x", b);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No se pudo encriptar");
        }
        return resultado;
    }
}
