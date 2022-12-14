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
import insertDb.ItemInsert;
import javax.swing.JOptionPane;

public class RepItens {
    
    Connection con;

    public boolean inserir(ItemInsert item){
        
        con = ConexaoMySql.getConexao(); 
        
        String sql = "insert into itens (nome,validade,valor) values (?,?,?)";
         try{
             con.setAutoCommit(false);
             PreparedStatement stmt = con.prepareStatement(sql);
             
             stmt.setString(1, item.getNome());
             stmt.setString(2, item.getValidade());
             stmt.setString(3, item.getValor());
             
             
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
      String sql = "delete from itens where id = ?";
      
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
    
    
    public boolean atualizar(ItemInsert item) {

        con = ConexaoMySql.getConexao();
        String sql = "UPDATE itens set nome = ? , validade = ? , valor = ? " 
                   + "WHERE id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getValidade());
            stmt.setString(3, item.getValor());
            stmt.setInt(4, item.getId());
             
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
    
    public List<ItemInsert> retornar(){
      
      con = ConexaoMySql.getConexao();
      List<ItemInsert> itens = new ArrayList<>();
      
      String sql = "select * from itens order by id desc";
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              ItemInsert item = new ItemInsert();
              
              item.setId(rs.getInt("id"));
              item.setNome(rs.getString("nome"));
              item.setValidade(rs.getString("validade"));
              item.setValor(rs.getString("valor"));
              
              
              itens.add(item);
          }            
      }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, "Não foi possivel extrair itens" + ex);
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return itens;
    }  
    
    /*
    public List<ItemInsert> pesquisa(String valor, String tipoPesquisa){
      
      con = ConexaoMySql.getConexao();
      List<ClienteInsert> items = new ArrayList<>();
      
      String sql = "";
      
      if(tipoPesquisa.equals("comeca")){
       sql = "select * from items where nome like '"+valor+"%'";
      }else if(tipoPesquisa.equals("contem")){
       sql = "select * from items where nome like '%"+valor+"%'";
      }else if(tipoPesquisa.equals("cpf")){
       sql = "select * from items where cpf = '"+valor+"'";
      }
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              ClienteInsert item = new ClienteInsert();
              
              item.setId(rs.getInt("id"));
              item.setNome(rs.getString("nome"));
              item.setCpf(rs.getString("cpf"));
              item.setTelefone(rs.getString("telefone"));
              item.setRua(rs.getString("endereco"));
              item.setBairro(rs.getString("bairro"));
              item.setCidade(rs.getString("cidade"));
              
              items.add(item);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return items;
  }  
     */ 
    
    
    
}
