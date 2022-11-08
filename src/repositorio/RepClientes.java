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

public class RepClientes {
    
    Connection con;

    public boolean inserir(ClienteInsert cliente){
        
        con = ConexaoMySql.getConexao(); 
        
        String sql = "insert into clientes (nome,"
                 + "cpf,telefone,rua,bairro,cidade,data_nascimento) values "
                 + "(?,?,?,?,?,?,?)";
         try{
             con.setAutoCommit(false);
             PreparedStatement stmt = con.prepareStatement(sql);
             
             stmt.setString(1, cliente.getNome());
             stmt.setString(2, cliente.getCpf());
             stmt.setString(3, cliente.getTelefone());
             stmt.setString(4, cliente.getRua());
             stmt.setString(5, cliente.getBairro());
             stmt.setString(6, cliente.getCidade());
             stmt.setString(7, cliente.getData_nascimento());
             
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
    
    public List<ClienteInsert> retornar(){
      
      con = ConexaoMySql.getConexao();
      List<ClienteInsert> clientes = new ArrayList<>();
      
      String sql = "select * from clientes order by id desc";
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              ClienteInsert cliente = new ClienteInsert();
              
              cliente.setId(rs.getInt("id"));
              cliente.setNome(rs.getString("nome"));
              cliente.setCpf(rs.getString("cpf"));
              cliente.setTelefone(rs.getString("telefone"));
              cliente.setRua(rs.getString("rua"));
              cliente.setBairro(rs.getString("bairro"));
              cliente.setCidade(rs.getString("cidade"));
              cliente.setData_nascimento(rs.getString("data_nascimento"));
              
              clientes.add(cliente);
          }            
      }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, "Não foi possivel extrair itens" + ex);
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return clientes;
  }  
  
    public boolean atualizar(ClienteInsert cliente) {

        con = ConexaoMySql.getConexao();
        String sql = "update clientes set nome = ?, "
                + "cpf = ?, telefone = ?,endereco = ?,bairro = ?, cidade = ? where id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getRua());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getCidade());
            stmt.setInt(7, cliente.getId());
             
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
  
    public List<ClienteInsert> pesquisa(String valor, String tipoPesquisa){
      
      con = ConexaoMySql.getConexao();
      List<ClienteInsert> clientes = new ArrayList<>();
      
      String sql = "";
      
      if(tipoPesquisa.equals("comeca")){
       sql = "select * from clientes where nome like '"+valor+"%'";
      }else if(tipoPesquisa.equals("contem")){
       sql = "select * from clientes where nome like '%"+valor+"%'";
      }else if(tipoPesquisa.equals("cpf")){
       sql = "select * from clientes where cpf = '"+valor+"'";
      }
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              ClienteInsert cliente = new ClienteInsert();
              
              cliente.setId(rs.getInt("id"));
              cliente.setNome(rs.getString("nome"));
              cliente.setCpf(rs.getString("cpf"));
              cliente.setTelefone(rs.getString("telefone"));
              cliente.setRua(rs.getString("endereco"));
              cliente.setBairro(rs.getString("bairro"));
              cliente.setCidade(rs.getString("cidade"));
              
              clientes.add(cliente);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return clientes;
  }  
      
    public boolean excluir(int id){
      
      con = ConexaoMySql.getConexao();
      String sql = "delete from clientes where id = ?";
      
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
