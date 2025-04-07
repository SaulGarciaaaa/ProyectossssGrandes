/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author salsa
 */
public class Actividades {
    private String nombre;
    private int plazasLibres;
    private LocalTime tiempo;
    private LocalDate fecha;

    public Actividades(String nombre, int plazasLibres, LocalTime tiempo,LocalDate fecha) {
        this.nombre = nombre;
        this.plazasLibres = plazasLibres;
        this.tiempo = tiempo;
        this.fecha= fecha;
    }
      public Actividades() {
        this.nombre = Teclado.compNombre("Introduce el nombre de la actividad");
        this.plazasLibres = Teclado.compNum("Introduce el numero de plazas libres");
        this.tiempo = Teclado.comphora("Introduce la hora HH:mm");
        this.fecha=Teclado.compFecha("Introduce la fecha dd-MM-yyyy");
      }

    public String getNombre() {
        return nombre;
    }
     
    @Override
    public String toString() {
        return "Actividades{" + "nombre=" + nombre + ", plazasLibres=" + plazasLibres + ", tiempo=" + tiempo + ", fecha=" + fecha + '}';
    }

   
      
}
