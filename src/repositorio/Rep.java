/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;


import com.mysql.jdbc.MySQLConnection;
import conexao.ConexaoMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import insertDb.ClienteInsert;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlo
 */
public class Rep {
    
    Connection con;
    
    public int quantidade(String tabela){
        int quant = -1;
        con = ConexaoMySql.getConexao();
        String sql = "select count(id) as total from "+ tabela ;
        try{
            con.setAutoCommit(false);
                        
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                quant = rs.getInt("total");
            }
            
            stmt.execute();
            con.commit();
            ConexaoMySql.fecharConexao();
        
        }catch(SQLException ex){
             try{
                 con.rollback();
                 System.err.println(ex.getMessage());
                 return -1;
                 
             }catch(SQLException exSql){
                 System.err.println(exSql.getMessage());
             }
        }
        return quant;
        
    }
    public boolean excluir(String tabela , int id){
      
      con = ConexaoMySql.getConexao();
      String sql = "delete from "+ tabela +" where id = " + id;
      
      try{
          con.setAutoCommit(false);
          
          PreparedStatement stmt = con.prepareStatement(sql);
          
          stmt.execute();
          con.commit();
          ConexaoMySql.fecharConexao();
      
          return true;   
      }catch(SQLException ex){
          
          return false;
      }
        
  }
}

