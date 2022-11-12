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
import insertDb.EstoqueInsert;
import javax.swing.JOptionPane;

public class RepEstoque {
    
    Connection con;

    public boolean inserir(EstoqueInsert estoque){
        
        con = ConexaoMySql.getConexao(); 
        
        String sql =  "INSERT into estoque (item,quantidade,validade,valor_compra,venda_valor) "
                +     "VALUES (?,?,?,?,?)";
         try{
             con.setAutoCommit(false);
             PreparedStatement stmt = con.prepareStatement(sql);
             
             stmt.setString(1, estoque.getItem_id());
             stmt.setString(2, estoque.getQuantidade());
             stmt.setString(3, estoque.getValidade());
             stmt.setString(4, estoque.getValor_compra());
             stmt.setString(5, estoque.getVenda_valor());
             
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
    
    public boolean excluir(int id){
      
      con = ConexaoMySql.getConexao();
      String sql = "delete from estoque where id = ?";
      
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
    
    /*
    public boolean atualizar(PedidoInsert estoque) {

        con = ConexaoMySql.getConexao();
        String sql = "update clientes set nome = ?, cpf = ?, telefone = ? " 
                    + ",rua = ?,bairro = ?, cidade = ?, Data_nascimento = ? where id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getRua());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getCidade());
            stmt.setString(7, cliente.getData_nascimento());
            stmt.setInt(8, cliente.getId());
             
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
    
    public List<PedidoInsert> retornar(){
      
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
       
    public List<PedidoInsert> pesquisa(String valor, String tipoPesquisa){
      
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
     */ 
    
    
    
}
