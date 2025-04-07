/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salsa
 */
public class MetodosBD {

    public Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    public List<Actividades> consultaractividad() {
        List<Actividades> LA = new ArrayList<>();
        String sql = "Select id,nombre,plazasLibres,dia,hora from actividades where plazasLibres>0";
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Actividades A1 = crearActividades(rs);
                if (!LA.add(A1)) {
                    System.out.println("No se inseto una actividad");
                }
            }
        } catch (SQLException a) {
            System.out.println("Error al obtener actividades: " + a.getMessage());
        }
        return LA;
    }

    public void incripcionactividad(String actividad, String dni) {
        String sql = "Select id,nombre,plazasLibres,dia,hora from actividades where nombre=?";
        String sqlu = "Select idActividad from usuarios where dni=?";
        String sqlActualizarPlazas = "UPDATE actividades SET plazasLibres = plazasLibres - 1 WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, actividad);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int idActividad = rs.getInt("id");
                    try (PreparedStatement pstmt2 = getConnection().prepareStatement(sqlu)) {
                        pstmt.setString(1, dni);
                        try (ResultSet rs2 = pstmt.executeQuery()) {
                            if (rs.next()) {
                                int idUsuario = rs.getInt("idUsuario");
                                if (idActividad != idUsuario) {
                                    try (PreparedStatement pstmt3 = getConnection().prepareStatement(sqlActualizarPlazas)) {
                                        pstmt3.setInt(1, idActividad);
                                        pstmt3.executeUpdate();
                                        System.out.println("Se inserto el usuario en la actividad");
                                    }
                                }
                            }
                        }
                    }

                } else {
                    System.out.println("No existe esa actividad");
                }
            }
        } catch (SQLException a) {

        }
    }

    public void anularinscripcion(String dni, String actividad) {
        String sql = "Select id,nombre,plazasLibres,dia,hora from actividades where nombre=?";
        String sqlu = "Select idActividad from usuarios where dni=?";
        String sqluu = "Update usuarios set idActividad=? where dni=?";
        String sqlau = "Update actividades set plazasLibres = plazasLibres - 1 WHERE nombre=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, actividad);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int idActividad = rs.getInt("id");
                    try (PreparedStatement pstmt2 = getConnection().prepareStatement(sqlu)) {
                        pstmt.setString(1, dni);
                        try (ResultSet rs2 = pstmt.executeQuery()) {
                            if (rs.next()) {
                                int idUsuario = rs.getInt("idUsuario");
                                if (idActividad == idUsuario) {
                                    try(PreparedStatement pstmt3=getConnection().prepareStatement(sqluu)){
                                      pstmt3.setString(1,null);
                                      pstmt3.setString(2, dni);
                                      pstmt3.executeUpdate();
                                     try(PreparedStatement pstmt4=getConnection().prepareStatement(sqluu)){
                                      pstmt4.setString(1,actividad);
                                      pstmt4.executeUpdate();
                                    }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException a) {
        }
    }

    public List<Participantes> usuariosEnActividad(String nombre) {
        List<Participantes> LA = new ArrayList<>();
        String sql = "Select id,nombre,plazasLibres,dia,hora from actividades where nombre=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int idActividad = rs.getInt("id");
                    sql = "Select dni,nombre,idActividad from participantes  where idActividad=?";
                    try (PreparedStatement pstmt2 = getConnection().prepareStatement(sql)) {
                        pstmt2.setInt(1, idActividad);
                        try (ResultSet rs2 = pstmt2.executeQuery()) {
                            while (rs2.next()) {
                                Participantes participantes = crearParticipantes(rs2);
                                if (!LA.add(participantes)) {
                                    System.out.println("No se insertaron todos los participantes");
                                }

                            }
                        }
                    }
                }
            }
        } catch (SQLException a) {

        }
        return LA;
    }

    public List<Participantes> consultarUsuarios() {
        List<Participantes> LA = new ArrayList<>();
        String sql = "Select dni,nombre,idActividad from participantes";
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Participantes P1 = crearParticipantes(rs);
                if (!LA.add(P1)) {
                    System.out.println("No se inseto una actividad");
                }
            }
        } catch (SQLException a) {
            System.out.println("Error al obtener actividades: " + a.getMessage());
        }
        return LA;
    }

    public Participantes crearParticipantes(ResultSet rs) throws SQLException {
        return new Participantes(rs.getString("dni"), rs.getString("nombre"));
    }

    public Actividades crearActividades(ResultSet rs) throws SQLException {
        int año = Integer.parseInt(rs.getString("dia").substring(0, 4));
        int mes = Integer.parseInt(rs.getString("dia").substring(5, 7));
        int dia = Integer.parseInt(rs.getString("dia").substring(8, 10));
        LocalDate fecha = LocalDate.of(dia, mes, año);
        // LocalDate fecha = LocalDate.parse(rs.getString("dia"));
        int hora = Integer.parseInt(rs.getString("hora").substring(0, 4));
        int min = Integer.parseInt(rs.getString("hora").substring(5, 7));
        int seg = Integer.parseInt(rs.getString("hora").substring(8, 10));
        LocalTime tiempo = LocalTime.of(hora, min, seg);
        //  LocalTime tiempo = LocalTime.parse(rs.getString("hora"));
        return new Actividades(rs.getString("nombre"), rs.getInt("plazasLibres"), tiempo, fecha);
    }
}
