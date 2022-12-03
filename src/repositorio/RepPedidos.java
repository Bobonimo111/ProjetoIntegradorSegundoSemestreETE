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
import insertDb.PedidoInsert;
import javax.swing.JOptionPane;

public class RepPedidos {
    
    Connection con;

    public boolean inserir(PedidoInsert pedido){
        
        con = ConexaoMySql.getConexao(); 
        
        String sql =  "insert into pedidos (client_id,data_entrega,item,observacao,quantidade) "
                    + "VALUES (?,?,?,?,?)";
         try{
             con.setAutoCommit(false);
             PreparedStatement stmt = con.prepareStatement(sql);
             
             stmt.setString(1, pedido.getCliente_id());
             stmt.setString(2, pedido.getData_entrega());
             stmt.setString(3, pedido.getItem());
             stmt.setString(4, pedido.getObservacao());
             stmt.setString(5, pedido.getQuantidade());
             
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
      String sql = "delete from pedidos where id = ?";
      
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
    
    
    
    public boolean atualizar(PedidoInsert pedido) {

        con = ConexaoMySql.getConexao();
        String sql = "update Pedidos set client_id= ?, data_entrega= ?, item= ?,"
                + "  observacao= ?, quantidade= ? WHERE id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, pedido.getCliente_id());
            stmt.setString(2, pedido.getData_entrega());
            stmt.setString(3, pedido.getItem());
            stmt.setString(4, pedido.getObservacao());
            stmt.setString(5, pedido.getQuantidade());
            stmt.setInt(6, pedido.getId());
            
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
      List<PedidoInsert> Pedidos = new ArrayList<>();
      
      String sql = "select * from Pedidos order by id desc";
      
      try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
              
                PedidoInsert Pedido = new PedidoInsert();
              
                Pedido.setId(rs.getInt("id"));
                Pedido.setCliente_id(rs.getString("client_id"));
                Pedido.setData_entrega(rs.getString("data_entrega"));
                Pedido.setItem(rs.getString("item"));
                Pedido.setObservacao(rs.getString("observacao"));
                Pedido.setQuantidade(rs.getString("quantidade"));
                
              
                Pedidos.add(Pedido);
          }            
      }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, "Não foi possivel extrair itens" + ex);
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return Pedidos;
  }  
      
    /*
    public List<PedidoInsert> pesquisa(String valor, String tipoPesquisa){
      
      con = ConexaoMySql.getConexao();
      List<ClienteInsert> Pedidos = new ArrayList<>();
      
      String sql = "";
      
      if(tipoPesquisa.equals("comeca")){
       sql = "select * from Pedidos where nome like '"+valor+"%'";
      }else if(tipoPesquisa.equals("contem")){
       sql = "select * from Pedidos where nome like '%"+valor+"%'";
      }else if(tipoPesquisa.equals("cpf")){
       sql = "select * from Pedidos where cpf = '"+valor+"'";
      }
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              ClienteInsert Pedido = new ClienteInsert();
              
              Pedido.setId(rs.getInt("id"));
              Pedido.setNome(rs.getString("nome"));
              Pedido.setCpf(rs.getString("cpf"));
              Pedido.setTelefone(rs.getString("telefone"));
              Pedido.setRua(rs.getString("endereco"));
              Pedido.setBairro(rs.getString("bairro"));
              Pedido.setCidade(rs.getString("cidade"));
              
              Pedidos.add(Pedido);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return Pedidos;
  }  
     */ 
    
    
    
}
