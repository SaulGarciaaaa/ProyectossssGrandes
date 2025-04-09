/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author salsa
 */
public class Participante {
    private Actividad actividad;
    private String dni;
    private String nombre;

    public Participante(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }
     public Participante() {
        this.dni = Teclado.validarDNI("Introduce el dni del participante");
        this.nombre = Teclado.compNombre("Introduce el nombre del participante");
    }

    public Participante(Actividad actividad, String dni, String nombre) {
        this.actividad = actividad;
        this.dni = dni;
        this.nombre = nombre;
    }
    
    public String getDni() {
        return dni;
    }
    
    @Override
    public String toString() {
        return "Participantes{" + "dni=" + dni + ", nombre=" + nombre + '}';
    }
    
}
