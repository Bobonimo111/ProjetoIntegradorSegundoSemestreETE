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
    
    
    public boolean atualizar(EstoqueInsert estoque) {

        con = ConexaoMySql.getConexao();
        String sql = "update estoque set item = ?, quantidade = ?, validade = ? " 
                    + ",valor_compra = ?,venda_valor = ? "
                    + " where id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, estoque.getItem_id());
            stmt.setString(2, estoque.getQuantidade());
            stmt.setString(3, estoque.getValidade());
            stmt.setString(4, estoque.getValor_compra());
            stmt.setString(5, estoque.getVenda_valor());
            stmt.setInt(6, estoque.getId());
            
             
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
    
   
    public List<EstoqueInsert> retornar(){
      
      con = ConexaoMySql.getConexao();
      List<EstoqueInsert> estoques = new ArrayList<>();
      
      String sql = "select * from estoque order by id desc";
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              EstoqueInsert estoque = new EstoqueInsert();
              
              estoque.setId(rs.getInt("id"));
              estoque.setItem_id(rs.getString("item"));
              estoque.setQuantidade(rs.getString("quantidade"));
              estoque.setValidade(rs.getString("validade"));
              estoque.setValor_compra(rs.getString("valor_compra"));
              estoque.setVenda_valor(rs.getString("venda_valor"));
              
              estoques.add(estoque);
          }            
      }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, "Não foi possivel extrair itens" + ex);
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return estoques;
  }  
      
    /*
    public List<PedidoInsert> pesquisa(String valor, String tipoPesquisa){
      
      con = ConexaoMySql.getConexao();
      List<ClienteInsert> estoques = new ArrayList<>();
      
      String sql = "";
      
      if(tipoPesquisa.equals("comeca")){
       sql = "select * from estoques where nome like '"+valor+"%'";
      }else if(tipoPesquisa.equals("contem")){
       sql = "select * from estoques where nome like '%"+valor+"%'";
      }else if(tipoPesquisa.equals("cpf")){
       sql = "select * from estoques where cpf = '"+valor+"'";
      }
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              ClienteInsert estoque = new ClienteInsert();
              
              estoque.setId(rs.getInt("id"));
              estoque.setNome(rs.getString("nome"));
              estoque.setCpf(rs.getString("cpf"));
              estoque.setTelefone(rs.getString("telefone"));
              estoque.setRua(rs.getString("endereco"));
              estoque.setBairro(rs.getString("bairro"));
              estoque.setCidade(rs.getString("cidade"));
              
              estoques.add(estoque);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return estoques;
  }  
     */ 
    
    
    
}
