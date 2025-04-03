/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2;

/**
 *
 * @author salsa
 */
public class Pacientes {
    private String dni;
    private String nombre;
    private String telefono;

    public Pacientes(String dni, String nombre, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }
     public Pacientes() {
        this.dni =Teclado.validarDNI("Introduce el dni");
        this.nombre =Teclado.compNombre("Introduce el nombre");
        this.telefono =Teclado.compTelefono("Introduce el telefono");
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
    
    @Override
    public String toString() {
        return "Pacientes{" + "dni=" + dni + ", nombre=" + nombre + ", telefono=" + telefono + '}';
    }  
}
