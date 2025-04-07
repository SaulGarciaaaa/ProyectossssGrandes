/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author salsa
 */
public class Participantes {
    private String dni;
    private String nombre;

    public Participantes(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }
     public Participantes() {
        this.dni = Teclado.validarDNI("Introduce el dni del participante");
        this.nombre = Teclado.compNombre("Introduce el nombre del participante");
    }

    public String getDni() {
        return dni;
    }
    
    @Override
    public String toString() {
        return "Participantes{" + "dni=" + dni + ", nombre=" + nombre + '}';
    }
    
}
