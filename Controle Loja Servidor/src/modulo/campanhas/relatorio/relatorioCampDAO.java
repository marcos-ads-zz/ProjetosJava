package modulo.campanhas.relatorio;

import ConexaoData.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modulo.campanhas.acompanhamento.CadastroCampanhaDia;

/**
 *
 * @author Marcos Junior
 */
public class relatorioCampDAO {

    Conexao con;

    public List<CadastroCampanhaDia> pesquisaCampanhasPorMes(int matricula, String campanha, Date data) throws Exception {
        con = new Conexao();
        CadastroCampanhaDia objCamp;
        List<CadastroCampanhaDia> camp = new ArrayList<>();
        String SQL = "select "
                + "id, "
                + "matricula, "
                + "desc_campanha, "
                + "quantidade, "
                + "data_registro, "
                + "EXTRACT(Month from data_registro) mes, "
                + "EXTRACT (Year from data_registro) ano "
                + "from relatorios.relatorio.campanha "
                + "WHERE date_trunc('month', data_registro) = ? "
                + "AND matricula = ? "
                + "AND desc_campanha = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, data);
        ps.setInt(2, matricula);
        ps.setString(3, campanha.toUpperCase());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroCampanhaDia();
            objCamp.setId(rs.getInt("id"));
            objCamp.setMatricula(rs.getInt("matricula"));
            objCamp.setDesc_campanha(rs.getString("desc_campanha"));
            objCamp.setQuantidade(rs.getInt("quantidade"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            camp.add(objCamp);
        }
        con.getCONEXAO().close();
        return camp;
    }
//SELECT
//    matricula , desc_campanha, quantidade,
//            data_registro,
//            EXTRACT
//    (Month from data_registro
//    ) mes ,
//            EXTRACT (Year from data_registro
//    ) ano //FROM relatorio.campanha
    //WHERE date_trunc('month', data_registro) = '2018-10-01'
    //AND matricula = '27826'
    //AND desc_campanha = 'POWER VITA'

    public List<CadastroCampanhaDia> TabelaPesquisaTodos() throws Exception {
        con = new Conexao();
        CadastroCampanhaDia objCamp = null;
        java.util.List<CadastroCampanhaDia> agua = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.campanha order by 5";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroCampanhaDia();
            objCamp.setId(rs.getInt("id"));
            objCamp.setMatricula(rs.getInt("matricula"));
            objCamp.setDesc_campanha(rs.getString("desc_campanha"));
            objCamp.setQuantidade(rs.getInt("quantidade"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            agua.add(objCamp);
        }
        con.getCONEXAO().close();
        return agua;
    }

    public List<CadastroCampanhaDia> TabelaPesquisaTodosCamp(String campanha, Date dataInicio, Date dataFim) throws Exception {
        con = new Conexao();
        CadastroCampanhaDia objCamp;
        java.util.List<CadastroCampanhaDia> agua = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.campanha "
                + "where data_registro between ? and ? "
                + "and desc_campanha = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataInicio);
        ps.setDate(2, dataFim);
        ps.setString(3, campanha);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroCampanhaDia();
            objCamp.setId(rs.getInt("id"));
            objCamp.setMatricula(rs.getInt("matricula"));
            objCamp.setDesc_campanha(rs.getString("desc_campanha"));
            objCamp.setQuantidade(rs.getInt("quantidade"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            agua.add(objCamp);
        }
        con.getCONEXAO().close();
        return agua;
    }

    public int TabelaPesquisaRows(int matricula, String campanha, Date data) throws Exception {
        con = new Conexao();
        int total = 0;
        String SQL = "SELECT "
                + "SUM(quantidade) AS qtd "
                + "FROM relatorio.campanha "
                + "where date_trunc('month', data_registro) = ? "
                + "AND matricula = ? "
                + "AND desc_campanha = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, data);
        ps.setInt(2, matricula);
        ps.setString(3, campanha);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            total = rs.getInt("qtd");
        }
        con.getCONEXAO().close();
        return total;
    }

    public int TabelaPesquisaRowsCamp(String campanha, Date dataInicio, Date dataFim) throws Exception {
        con = new Conexao();
        int total = 0;
        String SQL = "SELECT "
                + "SUM(quantidade) AS qtd "
                + "FROM relatorio.campanha "
                + "where data_registro between ? and ? "
                + "and desc_campanha = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataInicio);
        ps.setDate(2, dataFim);
        ps.setString(3, campanha);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            total = rs.getInt("qtd");
        }
        con.getCONEXAO().close();
        return total;
    }

}
