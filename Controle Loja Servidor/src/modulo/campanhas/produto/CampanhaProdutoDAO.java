package modulo.campanhas.produto;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class CampanhaProdutoDAO {

    Conexao con;

    public boolean Insert(produtoCampanha objItens) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.itens_campanha("
                + "descricaocampanha) "
                + "values (?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objItens.getDescricaoCampanha());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Update(produtoCampanha objCli) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.itens_campanha set descricaoCampanha = ? WHERE id = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objCli.getDescricaoCampanha().toUpperCase());
        ps.setInt(2, objCli.getId());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.itens_campanha where id = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<produtoCampanha> TabelaPesquisa() throws Exception {
        con = new Conexao();
        produtoCampanha objCamp = null;
        List<produtoCampanha> cliente = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.itens_campanha order by 2";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new produtoCampanha();
            objCamp.setId(rs.getInt("id"));
            objCamp.setDescricaoCampanha(rs.getString("descricaocampanha"));
            cliente.add(objCamp);
        }
        con.getCONEXAO().close();
        return cliente;

    }

    public List<produtoCampanha> PesquisaNome(String nome) throws Exception {
        con = new Conexao();
        produtoCampanha objCamp = null;
        List<produtoCampanha> cliente = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.itens_campanha where descricaocampanha like ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, "%" + nome.toUpperCase() + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new produtoCampanha();
            objCamp.setId(rs.getInt("id"));
            objCamp.setDescricaoCampanha(rs.getString("descricaocampanha"));
            cliente.add(objCamp);
        }
        con.getCONEXAO().close();
        return cliente;

    }

    public boolean CheckSelect(String nome) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.itens_campanha where descricaocampanha like ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, "%" + nome + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }

}
