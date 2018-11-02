package modulo.campanhas.meta;

import ConexaoData.Conexao;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modulo.campanhas.produto.produtoCampanha;

/**
 *
 * @author Marcos Junior
 */
public class MetasCampanhasDAO {

    Conexao con;

    public boolean Insert(metaCampanha obj) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.cad_campanha("
                + "descricao_campanha, "
                + "data_inicio, "
                + "data_fim, "
                + "meta_mes, "
                + "meta_trimestre, "
                + "meta_semestre, "
                + "meta_anual, "
                + "obs, "
                + "data_registro) "
                + "values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, obj.getDescricao_Campanha());
        ps.setDate(2, obj.getData_inicio());
        ps.setDate(3, obj.getData_fim());
        ps.setInt(4, obj.getMeta_mes());
        ps.setInt(5, obj.getMeta_trimestre());
        ps.setInt(6, obj.getMeta_semestre());
        ps.setInt(7, obj.getMeta_anual());
        ps.setString(8, obj.getObs());
        ps.setDate(9, obj.getData_registro());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<metaCampanha> TabelaPesquisaTodos() throws Exception {
        con = new Conexao();
        metaCampanha objCamp = null;
        java.util.List<metaCampanha> agua = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.cad_campanha order by 2";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new metaCampanha();
            objCamp.setId(rs.getInt("id"));
            objCamp.setDescricao_Campanha(rs.getString("descricao_campanha"));
            objCamp.setData_inicio(rs.getDate("data_inicio"));
            objCamp.setData_fim(rs.getDate("data_fim"));
            objCamp.setMeta_mes(rs.getInt("meta_mes"));
            objCamp.setMeta_trimestre(rs.getInt("meta_trimestre"));
            objCamp.setMeta_semestre(rs.getInt("meta_semestre"));
            objCamp.setMeta_anual(rs.getInt("meta_anual"));
            objCamp.setObs(rs.getString("obs"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            agua.add(objCamp);
        }
        con.getCONEXAO().close();
        return agua;
    }

    public boolean Update(metaCampanha obj) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.cad_campanha set "
                + "descricao_campanha = ?, "
                + "data_inicio = ?, "
                + "data_fim = ?, "
                + "meta_mes = ?, "
                + "meta_trimestre = ?, "
                + "meta_semestre = ?, "
                + "meta_anual = ?, "
                + "obs = ?, "
                + "data_registro = ? "
                + "WHERE id = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, obj.getDescricao_Campanha());
        ps.setDate(2, obj.getData_inicio());
        ps.setDate(3, obj.getData_fim());
        ps.setInt(4, obj.getMeta_mes());
        ps.setInt(5, obj.getMeta_trimestre());
        ps.setInt(6, obj.getMeta_semestre());
        ps.setInt(7, obj.getMeta_anual());
        ps.setString(8, obj.getObs());
        ps.setDate(9, obj.getData_registro());
        ps.setInt(10, obj.getId());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.cad_campanha where id = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<metaCampanha> TabelaPesquisa() throws Exception {
        con = new Conexao();
        metaCampanha objCamp = null;
        List<metaCampanha> cliente = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.cad_campanha order by 2";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new metaCampanha();
            objCamp.setId(rs.getInt("id"));
            objCamp.setDescricao_Campanha(rs.getString("descricao_campanha"));
            objCamp.setData_inicio(rs.getDate("data_inicio"));
            objCamp.setData_fim(rs.getDate("data_fim"));
            objCamp.setMeta_mes(rs.getInt("meta_mes"));
            objCamp.setMeta_trimestre(rs.getInt("meta_trimestre"));
            objCamp.setMeta_semestre(rs.getInt("meta_semestre"));
            objCamp.setMeta_anual(rs.getInt("meta_anual"));
            objCamp.setObs(rs.getString("obs"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            cliente.add(objCamp);
        }
        con.getCONEXAO().close();
        return cliente;

    }

    public List<metaCampanha> PesquisaNome(String nome) throws Exception {
        con = new Conexao();
        metaCampanha objCamp = null;
        List<metaCampanha> cliente = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.cad_campanha where descricao_campanha like ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, "%" + nome.toUpperCase() + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new metaCampanha();
            objCamp.setId(rs.getInt("id"));
            objCamp.setDescricao_Campanha(rs.getString("descricao_campanha"));
            objCamp.setData_inicio(rs.getDate("data_inicio"));
            objCamp.setData_fim(rs.getDate("data_fim"));
            objCamp.setMeta_mes(rs.getInt("meta_mes"));
            objCamp.setMeta_trimestre(rs.getInt("meta_trimestre"));
            objCamp.setMeta_semestre(rs.getInt("meta_semestre"));
            objCamp.setMeta_anual(rs.getInt("meta_anual"));
            objCamp.setObs(rs.getString("obs"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            cliente.add(objCamp);
        }
        con.getCONEXAO().close();
        return cliente;

    }

    public boolean CheckSelect(String nome) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.cad_campanha where descricao_campanha like ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, "%" + nome + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }

    public List<produtoCampanha> TabelaPesquisaDescricao() throws Exception {
        con = new Conexao();
        produtoCampanha objCamp = null;
        List<produtoCampanha> camp = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.itens_campanha order by 2";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new produtoCampanha();
            objCamp.setId(rs.getInt("id"));
            objCamp.setDescricaoCampanha(rs.getString("descricaocampanha"));
            camp.add(objCamp);
        }
        con.getCONEXAO().close();
        return camp;

    }
}
