/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author salsa
 */
public class MetodosBD {

    public Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    public void insertarpaciente(String dni) {
        if (!existePaciente(dni)) {
            String sql = "Insert into pacientes (dni,nombre,telefono) values(?,?,?)";
            try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
                pstmt.setString(1, dni);
                pstmt.setString(2, Teclado.compNombre("Introduce el nombre"));
                pstmt.setString(3, Teclado.compTelefono("Introduce el telefono"));
                pstmt.executeUpdate();
                System.out.println("Paciente añadido correctamente");
            } catch (SQLException a) {
                System.out.println("Error insertando paciente: " + a.getMessage());
            }
        } else {
            System.out.println("El pacienete ya existe ");
        }
    }

    public boolean existePaciente(String dni) {
        boolean existe = false;
        String sql = "Select dni,nombre,telefono from pacientes where dni=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                existe = rs.next(); // Si hay al menos un resultado, el ID existe
            }
        } catch (SQLException a) {
            System.out.println("Error SQL: " + a.getMessage());
        }
        return existe;
    }

    public void crearvisitas(String dni) {
        if (existePaciente(dni)) {
            String sql = "Insert into visitas(paciente,fecha,tratamiento,observaciones) values(?,?,?,?)";
            try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
                pstmt.setString(1, dni);
                LocalDate L1 = Teclado.compFecha("Introduce la fecha");
                pstmt.setDate(2, Date.valueOf(L1));
                pstmt.setString(3, "" + Teclado.compTratamiento("Introduce el tipo de tratamiento"));
                pstmt.setString(4, Teclado.compNombre("Introduce las observaciones"));
                pstmt.executeUpdate();
            } catch (SQLException a) {
                System.out.println("Error insertando visita: " + a.getMessage());
            }
        } else {
            System.out.println("Inserta a un usuario con ese dni");
        }
    }

    public void mostraragenda(LocalDate fecha) {
        if (fecha != LocalDate.now()) {
            System.out.println("Estas buscando una fecha otro dia que no es hoy");
        }
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Las visitas del dia " + fecha.format(f));
        String sql = "Select id,paciente,fecha,tratamiento,observaciones where fecha=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(fecha));
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean hayVisitas = false;
                while (rs.next()) {
                    hayVisitas = true;
                    int id = rs.getInt("id");
                    Pacientes P1=sacarPaciente(rs.getString("paciente")); 
                    Date fechaVisita = rs.getDate("fecha");
                    String tratamiento = rs.getString("tratamiento");
                    String observaciones = rs.getString("observaciones");
                    // Mostrar la información de la visita
                    System.out.println("ID Visita: " + id);
                    P1.toString();
                    System.out.println("Fecha: " + fechaVisita);
                    System.out.println("Tratamiento: " + tratamiento);
                    System.out.println("Observaciones: " + observaciones);
                    System.out.println("----------------------------");
                }
                if (!hayVisitas) {
                    System.out.println("No hay visitas programadas para el día " + fecha);
                }
            }
        } catch (SQLException a) {
            System.out.println("Error al obtener las visitas: " + a.getMessage());
        }
    }   
    public Pacientes sacarPaciente(String dni){
     Pacientes P1=null;
     String sql="Select dni,nombre,telefono from pacientes where dni=?";
     try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                P1 = new Pacientes(dni, nombre, telefono);
            }
          }
        } catch (SQLException a) {
            System.out.println("Error SQL: " + a.getMessage());
        }
        return P1;
    }

    public void visitaspaciente(String dni) {

    }
}
