/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author salsa
 */
public class Actividad {
    private int id;
    private String nombre;
    private int plazasLibres;
    private LocalTime tiempo;
    private LocalDate fecha;

    public Actividad(String nombre, int plazasLibres, LocalTime tiempo,LocalDate fecha) {
        this.nombre = nombre;
        this.plazasLibres = plazasLibres;
        this.tiempo = tiempo;
        this.fecha= fecha;
    }
      public Actividad() {
        this.nombre = Teclado.compNombre("Introduce el nombre de la actividad");
        this.plazasLibres = Teclado.compNum("Introduce el numero de plazas libres");
        this.tiempo = Teclado.comphora("Introduce la hora HH:mm");
        this.fecha=Teclado.compFecha("Introduce la fecha dd-MM-yyyy");
      }

    public Actividad(int id, String nombre, int plazasLibres, LocalTime tiempo, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.plazasLibres = plazasLibres;
        this.tiempo = tiempo;
        this.fecha = fecha;
    }
      

    public String getNombre() {
        return nombre;
    }
     
    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("HH:mm");
        return "Actividades{" + "nombre=" + nombre + ", plazasLibres=" + plazasLibres + ", tiempo=" + tiempo.format(f2) + ", fecha=" + fecha.format(f) + '}';
    }

   
      
}
