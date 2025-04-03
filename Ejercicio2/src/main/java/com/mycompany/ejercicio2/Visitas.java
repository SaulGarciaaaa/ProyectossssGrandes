/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author salsa
 */
public class Visitas {
    private int id;
    private Pacientes pacientes;
    private LocalDate fecha;
    private Tratamiento tratamiento;
    private String obsevaciobes;

    public Visitas(int id, Pacientes pacientes, LocalDate fecha, Tratamiento tratamiento, String obsevaciobes) {
        this.id = id;
        this.pacientes = pacientes;
        this.fecha = fecha;
        this.tratamiento = tratamiento;
        this.obsevaciobes = obsevaciobes;
    }
        public Visitas() {
        this.fecha = Teclado.compFecha("Introduce la fecha dd-MM-yyyy");
        this.tratamiento = Teclado.compTratamiento("Introduce el tipo de tratamiento Implantologia,Ortodoncia,Periodoncia,Estetica,Extracciones ");
        this.obsevaciobes = Teclado.compNombre("Introduce las observaciones");
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Visitas{" + "id=" + id + ", pacientes=" + pacientes.toString() + ", fecha=" + fecha.format(f) + ", tratamiento=" + tratamiento + ", obsevaciobes=" + obsevaciobes + '}';
    }
    
}
