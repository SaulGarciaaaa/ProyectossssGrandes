/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            opcion = Teclado.compNum("Introduce uan opcion 1-5");
            switch (opcion) {
                case 1 ->
                    bd.insertarpaciente(Teclado.validarDNI("Introduce el dni del usuario"));
                case 2 ->
                    bd.crearvisitas(Teclado.validarDNI("Introduce el dni del usuario"));
                case 3 -> {
                    Map<Visita, Paciente> salida = bd.mostraragenda(Teclado.compFecha("Introduce una fecha"));
                    if (salida.isEmpty()) {
                        System.out.println("No hay visitas para la fecha indicada.");
                    } else {
                        List<Visita> visitas = new ArrayList<>(salida.keySet());
                        List<Paciente> pacientes = new ArrayList<>(salida.values());
                        for (Map.Entry<Visita, Paciente> map : salida.entrySet()) {
                            Visita visita = map.getKey();
                            Paciente paciente = map.getValue();
                            System.out.println("Visita: " + visita.toString() + "Paciente: " + paciente.toString());
                        }
                    }
                }
                case 4 -> {
                    Map<Visita, Paciente> salida = bd.visitaspaciente(Teclado.validarDNI("Introduce el dni del usuario"));
                    if (salida.isEmpty()) {
                        System.out.println("No hay visitas para la fecha indicada.");
                    } else {
                        List<Visita> visitas = new ArrayList<>(salida.keySet());
                        List<Paciente> pacientes = new ArrayList<>(salida.values());
                        for (Map.Entry<Visita, Paciente> map : salida.entrySet()) {
                            Visita visita = map.getKey();
                            Paciente paciente = map.getValue();
                            System.out.println("Visita: " + visita.toString() + "Paciente: " + paciente.toString());
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Saliendo del programa");
                    AccesoBaseDatos.getInstance().cerrar();
                }
                default ->
                    System.out.println("Introduce una opcion valida 1-5");

            }
        } while (opcion != 5);
    }
}
