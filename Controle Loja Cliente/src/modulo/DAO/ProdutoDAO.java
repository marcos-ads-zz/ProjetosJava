package modulo.DAO;

import ConexaoData.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modulo.entidades.Produto;

/**
 *
 * @author Marcos Junior
 */
public class ProdutoDAO {

    Conexao con;

    public boolean Insert(Produto objFun) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.produto(cod_interno, cod_ean, descricao, obs) values (?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objFun.getCod_interno());
        ps.setString(2, objFun.getCod_produto());
        ps.setString(3, objFun.getDescricao().toUpperCase());
        ps.setString(4, objFun.getObserv().toUpperCase());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public Produto PesquisarPorCodInterno(int cod_interno) throws Exception {
        con = new Conexao();
        Produto objFun = null;
        String SQL = "select * from relatorios.relatorio.produto where cod_interno = ? order by 1";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, cod_interno);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Produto();
            objFun.setId(rs.getInt("idproduto"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setObserv(rs.getString("obs"));
        }
        con.getCONEXAO().close();
        return objFun;
    }

    public Produto PesquisarPorCodProduto(String cd_ean) throws Exception {
        con = new Conexao();
        Produto objFun = null;
        String SQL = "select * from relatorios.relatorio.produto where cod_ean = ? order by 1";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, cd_ean);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Produto();
            objFun.setId(rs.getInt("idproduto"));
            objFun.setCod_interno(rs.getInt("cod_interno"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setObserv(rs.getString("obs"));
        }
        con.getCONEXAO().close();
        return objFun;
    }

    public boolean CheckSelect(int u) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.produto where cod_interno = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, u);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }
}
