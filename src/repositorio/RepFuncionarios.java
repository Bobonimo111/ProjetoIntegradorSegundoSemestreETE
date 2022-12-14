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
import insertDb.*;
import javax.swing.JOptionPane;

public class RepFuncionarios {
    
    Connection con;

    public boolean inserir(FuncionarioInsert funcionario){
        
        con = ConexaoMySql.getConexao(); 
        
        String sql = " insert into funcionarios (nome, "
                 +   " telefone,bairro,cidade,data_nascimento,cargo,turno) values "
                 +   " (?,?,?,?,?,?,?) ";
         try{
             con.setAutoCommit(false);
             PreparedStatement stmt = con.prepareStatement(sql);
             
             stmt.setString(1, funcionario.getNome());
             
             stmt.setString(2, funcionario.getTelefone());
             stmt.setString(3, funcionario.getBairro());
             stmt.setString(4, funcionario.getCidade());
             stmt.setString(5, funcionario.getData_nascimento());
             stmt.setString(6, funcionario.getCargo());
             stmt.setString(7, funcionario.getTurno());
             
             stmt.execute();
             con.commit();
             ConexaoMySql.fecharConexao();
             
            return true;
         }catch(SQLException ex){
             try{
                 con.rollback();
                 System.err.println(ex.getMessage());
                 return false;
             }catch(SQLException exSql){
                 System.err.println(exSql.getMessage());
             }
         }
         
       return true;
    }
    
    public List<FuncionarioInsert> retornar(){
      
      con = ConexaoMySql.getConexao();
      List<FuncionarioInsert> funcionarios = new ArrayList<>();
       
      String sql = "select * from funcionarios order by id desc";
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              FuncionarioInsert funcionario = new FuncionarioInsert();
              
              funcionario.setId(rs.getInt("id"));
              funcionario.setNome(rs.getString("nome"));
              funcionario.setEndereco(rs.getString("endereco"));
              funcionario.setTelefone(rs.getString("telefone"));
              funcionario.setBairro(rs.getString("bairro"));
              funcionario.setCidade(rs.getString("cidade"));
              funcionario.setData_nascimento(rs.getString("data_nascimento"));
              funcionario.setCargo(rs.getString("cargo"));
              funcionario.setTurno(rs.getString("turno"));
              
              funcionarios.add(funcionario);
          }            
      }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, "Não foi possivel extrair itens" + ex);
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return funcionarios;
  }  
  
    public boolean atualizar(FuncionarioInsert funcionario) {

        con = ConexaoMySql.getConexao();
        String sql = "update funcionarios set nome = ?, "
                + "endereco = ?,telefone = ?,bairro = ?, cidade = ?,data_nascimento = ?,cargo = ? , turno =?  where id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getEndereco());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getBairro());
            stmt.setString(5, funcionario.getCidade());
            stmt.setString(6, funcionario.getData_nascimento());
            stmt.setString(7, funcionario.getCargo());
            stmt.setString(8, funcionario.getTurno());
            stmt.setInt(9, funcionario.getId());
             
            stmt.execute();

            con.commit();
            ConexaoMySql.fecharConexao();

            return true;

        } catch (SQLException ex) {
            try {
                con.rollback();
                System.err.println(ex);
                return false;
            } catch (SQLException ex1) {
                System.err.println(ex1);
            }

            return false;
        }

    }  
  
    public List<FuncionarioInsert> pesquisa(String valor, String tipoPesquisa){
      
      con = ConexaoMySql.getConexao();
      List<FuncionarioInsert> funcionarios = new ArrayList<>();
      
      String sql = "";
      
      if(tipoPesquisa.equals("comeca")){
       sql = "select * from funcionarios where nome like '"+valor+"%'";
      }else if(tipoPesquisa.equals("contem")){
       sql = "select * from funcionarios where nome like '%"+valor+"%'";
      }
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              FuncionarioInsert funcionario = new FuncionarioInsert();
              
              funcionario.setId(rs.getInt("id"));
              funcionario.setNome(rs.getString("nome"));
              funcionario.setEndereco(rs.getString("endereco"));
              funcionario.setTelefone(rs.getString("telefone"));
              funcionario.setBairro(rs.getString("bairro"));
              funcionario.setCidade(rs.getString("cidade"));
              
              funcionario.setData_nascimento(rs.getString("data_nascimento"));
              funcionario.setCargo(rs.getString("cargo"));
              funcionario.setTurno(rs.getString("turno"));
              
              
              
              funcionarios.add(funcionario);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return funcionarios;
  }  
      
    public boolean excluir(int id){
      
      con = ConexaoMySql.getConexao();
      String sql = "delete from funcionarios where id = ?";
      
      try{
          
          con.setAutoCommit(false);
          PreparedStatement stmt = con.prepareStatement(sql);
          
          stmt.setInt(1, id);
          
          stmt.execute();
          con.commit();
          ConexaoMySql.fecharConexao();
      
          return true;   
      }catch(SQLException ex){
          
          return false;
      }
        
  }
    
    
}
