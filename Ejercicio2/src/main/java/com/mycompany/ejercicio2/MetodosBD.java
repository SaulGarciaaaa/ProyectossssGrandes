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
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salsa
 */
public class MetodosBD {

    Connection conn;

    public MetodosBD() {
        this.conn = AccesoBaseDatos.getInstance().getConn();
    }

    // 
    public void insertarpaciente(String dni) {
        if (!existePaciente(dni)) {
            String sql = "Insert into pacientes (dni,nombre,telefono) values(?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dni);
                pstmt.setString(2, Teclado.compNombre("Introduce el nombre"));
                pstmt.setString(3, Teclado.compTelefono("Introduce el telefono"));
                if (pstmt.executeUpdate() != 1) {
                    throw new Exception("No se pudo introducir a el paciente");
                }
                System.out.println("Paciente añadido correctamente");
            } catch (SQLException a) {
                System.out.println("Error insertando paciente: " + a.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("El pacienete ya existe ");
        }
    }

    // 
    public boolean existePaciente(String dni) {
        boolean existe = false;
        String sql = "Select dni,nombre,telefono from pacientes where dni=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                existe = rs.next(); // Si hay al menos un resultado, el ID existe
            }
        } catch (SQLException a) {
            System.out.println("Error SQL: " + a.getMessage());
        }
        return existe;
    }

    //
    public List<Paciente> mostrarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "Select dni,nombre,telefono from pacientes";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Paciente p = crearPaciente(rs);
                if (!pacientes.add(p)) {
                    throw new Exception("No se añadio un paciente");
                }
            }
        } catch (SQLException ex) {

        } catch (Exception a) {
            System.out.println(a.getMessage());
        }
        return pacientes;
    }

    //
    public Paciente crearPaciente(final ResultSet rs) throws SQLException {
        return new Paciente(rs.getString(1), rs.getString(2), rs.getString(3));
    }

    public void crearvisitas(String dni) {
        if (existePaciente(dni)) {
            String sql = "Insert into visitas(dni,fecha,tratamiento,observaciones) values(?,?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                boolean comp = false;
                LocalDate L1;
                do {
                    L1 = Teclado.compFecha("Introduce la fecha");
                    if (!L1.isBefore(LocalDate.now())) {
                        comp = true;
                    } else {
                        System.out.println("Introduce una fecha superior a la de creacion");
                    }
                } while (!comp);
                pstmt.setString(1, dni);
                pstmt.setDate(2, Date.valueOf(L1));
                pstmt.setString(3, "" + Teclado.compTratamiento("Introduce el tipo de tratamiento"));
                pstmt.setString(4, Teclado.compNombre("Introduce las observaciones"));
                if (pstmt.executeUpdate() != 1) {
                    throw new Exception("No e creo la visita");
                }
                System.out.println("Se creo la visita perfectamente");
            } catch (SQLException a) {
                System.out.println("Error insertando visita: " + a.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Inserta a un usuario con ese dni usando la opcion 1");
        }
    }

    public List<Visita> mostrarVisitas() {
        List<Visita> visitas = new ArrayList<>();
        String sql = "Select id,dni,fecha,tratamiento,observacion from visitas ";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Visita V1 = crearVisita(rs);
                if (!visitas.add(V1)) {
                    throw new Exception("No se pudo crear la visita");
                }
            }
        } catch (SQLException ex) {

        } catch (Exception a) {
            System.out.println(a.getMessage());
        }
        return visitas;
    }

    public Visita crearVisita(final ResultSet rs) throws SQLException {
        return new Visita(rs.getInt(1), rs.getDate(3).toLocalDate(), Teclado.compTratamientoInsertar(rs.getString(4)), rs.getString(5));
    }

    public Map<Visita,Paciente> mostraragenda(LocalDate fecha) {
        Map<Visita,Paciente> salida=new HashMap<>();
        if (fecha != LocalDate.now()) {
            System.out.println("Estas buscando una fecha otro dia que no es hoy");
        }
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Las visitas del dia " + fecha.format(f));
        String sql = "Select id,dni,fecha,tratamiento,observaciones from visitas where fecha=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(fecha));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int dni = rs.getInt("dni");
                    Visita V1 = crearVisita(rs);
                    sql = "Select dni,nombre,telefono from pacientes where dni=?";
                    try (PreparedStatement pstmt2 = conn.prepareStatement(sql);) {
                        pstmt2.setInt(1, dni);
                        try (ResultSet rs2 = pstmt2.executeQuery()) {
                            while (rs2.next()) {
                                Paciente P1 = crearPaciente(rs2);
                               salida.put(V1,P1);
                            }
                        }
                    }
                }
            }
        } catch (SQLException a) {
            System.out.println("Error al obtener las visitas: " + a.getMessage());
        }
        return salida;
    }

    public Map<Visita,Paciente> visitaspaciente(String dni) {
         Map<Visita,Paciente> salida=new HashMap<>();
        if (existePaciente(dni)) {
            String sql = "Select id,dni,fecha,tratamiento,observaciones from visitas where dni=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dni);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Paciente P1 = sacarPaciente(dni);
                        Visita V1 = crearVisita(rs);
                         salida.put(V1,P1);
                    }
                }
            } catch (SQLException a) {
                System.out.println("Error SQL: " + a.getMessage());
            }
        } else {
            System.out.println("El paciente no existe crealo");
        }
        return salida;
    }

    public Paciente sacarPaciente(String dni) {
        Paciente P1 = null;
        String sql = "Select dni,nombre,telefono from pacientes where dni=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String telefono = rs.getString("telefono");
                    P1 = new Paciente(dni, nombre, telefono);
                }
            }
        } catch (SQLException a) {
            System.out.println("Error SQL: " + a.getMessage());
        }
        return P1;
    }
}
