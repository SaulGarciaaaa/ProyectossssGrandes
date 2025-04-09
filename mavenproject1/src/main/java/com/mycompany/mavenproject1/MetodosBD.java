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

    public List<Actividad> consultaractividad() {
        List<Actividad> LA = new ArrayList<>();
        String sql = "Select id,nombre,plazasLibres,dia,hora from actividades where plazasLibres>0";
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Actividad A1 = crearActividades(rs);
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

    public List<Participante> usuariosEnActividad(String nombre) {
        List<Participante> LA = new ArrayList<>();
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
                                Participante participantes = crearParticipantes(rs2);
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

    public List<Participante> consultarUsuarios() {
        List<Participante> LA = new ArrayList<>();
        String sql = "Select dni,nombre,idActividad from participantes";
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Participante P1 = crearParticipantes(rs);
                if (!LA.add(P1)) {
                    System.out.println("No se inseto una actividad");
                }
            }
        } catch (SQLException a) {
            System.out.println("Error al obtener actividades: " + a.getMessage());
        }
        return LA;
    }

    public Participante crearParticipantes(ResultSet rs) throws SQLException {
        return new Participante(rs.getString("dni"), rs.getString("nombre"));
    }

    public Actividad crearActividades(ResultSet rs) throws SQLException {
    return new Actividad(rs.getString("nombre"), rs.getInt("plazasLibres"), rs.getTime("hora").toLocalTime(), rs.getDate("dia").toLocalDate());
    }
}
