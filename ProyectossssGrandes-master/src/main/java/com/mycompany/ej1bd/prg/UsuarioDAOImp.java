/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej1bd.prg;

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
public  class UsuarioDAOImp implements Repositorio<Usuarios>{
    private Connection getConnection(){
        return AccesoBaseDatos.getInstance().getConn();
    }
    @Override
    public List<Usuarios> listar() {
        List<Usuarios> usuarios=new ArrayList<>();
      try(Statement stmt= getConnection().createStatement();
           ResultSet rs=stmt.executeQuery("Select id,usurname,password,email from usuarios");){
          while(rs.next()){
              Usuarios usuario=crearUsuarios(rs);
              if (!usuarios.add(usuario)) {
                  throw new Exception("error no se ha insertado el objeto en la colección"); 
              }
          }
      }catch(SQLException a){
          System.out.println("SQLException: " + a.getMessage()); 
      }catch (Exception ex) { 
            System.out.println(ex.getMessage()); 
        } 
      return usuarios;
    }
    @Override
    public Usuarios id(int id) {
        Usuarios usuario=null;
            String sql="Select id,usurname,password,email from usuarios where id=?";
        try(PreparedStatement stmt=getConnection().prepareStatement(sql);){
            stmt.setInt(1, id);
            try(ResultSet rs=stmt.executeQuery();){
                if (rs.next()) {
                    usuario=crearUsuarios(rs);
                }

            }
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage()); 
        }
      return usuario;
    }
    @Override
    public void guarda(Usuarios usuario) {
        String sql=null;
        if (usuario.getId()>0) {
            sql="Update usuarios set usurname=?,password=MD5(?),email=? where id=?";
        }else{
            sql="Insert into usuarios(usurname,password,email) values(?,MD5(?),?)";
        }
        try(PreparedStatement stmt=getConnection().prepareStatement(sql);){
           if (usuario.getId() > 0) { 
                stmt.setInt(4, usuario.getId()); 
            } 
            stmt.setString(1, usuario.getUsurname()); 
            stmt.setString(2, usuario.getPassword()); 
            stmt.setString(3, usuario.getEmail());
            int salida = stmt.executeUpdate(); 
            if (salida != 1) { 
                throw new Exception(" No se ha insertado/modificado un solo registro"); 
            }else{
                System.out.println("Se inserto correctamente");
            }
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage()); 
        }catch (Exception ex) { 
            System.out.println(ex.getMessage()); 
        } 
    }

    @Override
    public void borrar(int id) {
        String sql="Delete from usuarios where id=?";
        try(PreparedStatement stmt=getConnection().prepareStatement(sql);){
            stmt.setInt(1, id);
            int salida=stmt.executeUpdate();
            if (salida!=1) {
                 throw new Exception(" No se ha borrado un solo registro"); 
            }else{
                System.out.println("Se elimino correctamente");
            }
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage()); 
        }  catch (Exception ex) { 
            System.out.println(ex.getMessage()); 
        }
    } 
    
    public Usuarios crearUsuarios(final ResultSet rs) throws SQLException{
        return new Usuarios(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
    }
    public boolean existeUsuario(int id){
         String sql = "SELECT id FROM usuarios WHERE id = ?";
        boolean existe = false;
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                existe = rs.next(); // Si hay al menos un resultado, el ID existe
            }
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return existe;
    }
}
