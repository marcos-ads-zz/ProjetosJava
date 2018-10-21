package modulo.inventario;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class InventarioDAO {

    Conexao con;

    public boolean Insert(Inventario objInv) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.inventario("
                + "loja, "
                + "prazo, "
                + "data_inventario, "
                + "ano, "
                + "total_produtos, "
                + "total_unidades, "
                + "valor_ima, "
                + "valor_venda_estoque, "
                + "valor_venda_falta, "
                + "valor_venda_sobra, "
                + "valor_custo_estoque, "
                + "valor_custo_falta, "
                + "valor_custo_sobra) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objInv.getLoja());
        ps.setInt(2, objInv.getPrazo());
        ps.setDate(3, objInv.getData_inventario());
        ps.setString(4, objInv.getAno());
        ps.setInt(5, objInv.getTotal_produtos());
        ps.setInt(6, objInv.getTotal_unidades());
        ps.setDouble(7, objInv.getValor_ima());
        ps.setDouble(8, objInv.getValor_venda_estoque());
        ps.setDouble(9, objInv.getValor_venda_falta());
        ps.setDouble(10, objInv.getValor_venda_sobra());
        ps.setDouble(11, objInv.getValor_custo_estoque());
        ps.setDouble(12, objInv.getValor_custo_falta());
        ps.setDouble(13, objInv.getValor_custo_sobra());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Update(Inventario objInv) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.inventario set "
                + "loja = ?, "
                + "prazo = ?, "
                + "data_inventario = ?, "
                + "ano = ?, "
                + "total_produtos = ?, "
                + "total_unidades = ?, "
                + "valor_ima = ?, "
                + "valor_venda_estoque = ?, "
                + "valor_venda_falta = ?, "
                + "valor_venda_sobra = ?, "
                + "valor_custo_estoque = ?, "
                + "valor_custo_falta = ?, "
                + "valor_custo_sobra = ? "
                + "WHERE id_inv = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objInv.getLoja());
        ps.setInt(2, objInv.getPrazo());
        ps.setDate(3, objInv.getData_inventario());
        ps.setString(4, objInv.getAno());
        ps.setInt(5, objInv.getTotal_produtos());
        ps.setInt(6, objInv.getTotal_unidades());
        ps.setDouble(7, objInv.getValor_ima());
        ps.setDouble(8, objInv.getValor_venda_estoque());
        ps.setDouble(9, objInv.getValor_venda_falta());
        ps.setDouble(10, objInv.getValor_venda_sobra());
        ps.setDouble(11, objInv.getValor_custo_estoque());
        ps.setDouble(12, objInv.getValor_custo_falta());
        ps.setDouble(13, objInv.getValor_custo_sobra());
        ps.setInt(14, objInv.getId_inv());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.inventario where id_inv = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

    public List<Inventario> TabelaPesquisaPorAno(String ano) throws Exception {
        con = new Conexao();
        Inventario objInv = null;
        List<Inventario> inventario = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.inventario where ano = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, ano);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objInv = new Inventario();
            objInv.setId_inv(rs.getInt("id_inv"));
            objInv.setLoja(rs.getInt("loja"));
            objInv.setPrazo(rs.getInt("prazo"));
            objInv.setData_inventario(rs.getDate("data_inventario"));
            objInv.setAno(rs.getString("ano"));
            objInv.setValor_ima(rs.getDouble("valor_ima"));
            objInv.setTotal_produtos(rs.getInt("total_produtos"));
            objInv.setTotal_unidades(rs.getInt("total_unidades"));
            objInv.setValor_venda_estoque(rs.getDouble("valor_venda_estoque"));
            objInv.setValor_venda_falta(rs.getDouble("valor_venda_falta"));
            objInv.setValor_venda_sobra(rs.getDouble("valor_venda_sobra"));
            objInv.setValor_custo_estoque(rs.getDouble("valor_custo_estoque"));
            objInv.setValor_custo_falta(rs.getDouble("valor_custo_falta"));
            objInv.setValor_custo_sobra(rs.getDouble("valor_custo_sobra"));
            inventario.add(objInv);
        }
        con.getCONEXAO().close();
        return inventario;
    }
    public List<Inventario> TabelaPesquisaTodos() throws Exception {
        con = new Conexao();
        Inventario objInv = null;
        List<Inventario> inventario = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.inventario";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objInv = new Inventario();
            objInv.setId_inv(rs.getInt("id_inv"));
            objInv.setLoja(rs.getInt("loja"));
            objInv.setPrazo(rs.getInt("prazo"));
            objInv.setData_inventario(rs.getDate("data_inventario"));
            objInv.setAno(rs.getString("ano"));
            objInv.setValor_ima(rs.getDouble("valor_ima"));
            objInv.setTotal_produtos(rs.getInt("total_produtos"));
            objInv.setTotal_unidades(rs.getInt("total_unidades"));
            objInv.setValor_venda_estoque(rs.getDouble("valor_venda_estoque"));
            objInv.setValor_venda_falta(rs.getDouble("valor_venda_falta"));
            objInv.setValor_venda_sobra(rs.getDouble("valor_venda_sobra"));
            objInv.setValor_custo_estoque(rs.getDouble("valor_custo_estoque"));
            objInv.setValor_custo_falta(rs.getDouble("valor_custo_falta"));
            objInv.setValor_custo_sobra(rs.getDouble("valor_custo_sobra"));
            inventario.add(objInv);
        }
        con.getCONEXAO().close();
        return inventario;
    }
}
