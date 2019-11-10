/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import banco.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import object.CadObject;
import object.LoginObject;

/**
 *
 * @author caiot
 */
public class Banco {

    public static boolean login(LoginObject login) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM public.\"user\" WHERE usuario=? AND senha=?";
        if (Conexao.acessaBD()) {
            Connection con = Conexao.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, login.getUsuario());
            psmt.setString(2, login.getSenha());
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                psmt.close();
                rs.close();
                con.close();
                
                return true;
            } else {
                psmt.close();
                rs.close();
                con.close();
                
                return false;
            }

        } else {
            return false;
        }
    }
    
    public static boolean cadastro(CadObject cadastro) throws SQLException{
        String sql = "INSERT INTO public.\"user\" VALUES (?, ?, ?)";
        if (Conexao.acessaBD()) {
            Connection con = Conexao.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, cadastro.getUsuario());
            psmt.setString(2, cadastro.getEmail());
            psmt.setString(3, cadastro.getSenha());
            System.out.println(cadastro.getUsuario()+"/"+cadastro.getEmail()+"/"+cadastro.getSenha());
            
            int i = psmt.executeUpdate();
            if (i != 0) {
                con.commit();
                psmt.close();
                con.close();
                System.out.println(i);
                
                return true;
            } else {
                psmt.close();
                con.close();
                
                return false;
            }

        } else {
            return false;
        }
    }
}
