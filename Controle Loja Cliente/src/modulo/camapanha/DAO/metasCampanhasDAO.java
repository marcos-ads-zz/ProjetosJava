package modulo.camapanha.DAO;

import ConexaoData.Conexao;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modulo.campanhas.CadastroMetasCampanhas;

/**
 *
 * @author Marcos Junior
 */
public class metasCampanhasDAO {

    Conexao con;

    public boolean Insert(CadastroMetasCampanhas objDerm) throws Exception {
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
        ps.setString(1, objDerm.getDescricao_Campanha());
        ps.setDate(2, objDerm.getData_inicio());
        ps.setDate(3, objDerm.getData_fim());
        ps.setInt(4, objDerm.getMeta_mes());
        ps.setInt(5, objDerm.getMeta_trimestre());
        ps.setInt(6, objDerm.getMeta_semestre());
        ps.setInt(7, objDerm.getMeta_anual());
        ps.setString(8, objDerm.getObs());
        ps.setDate(9, objDerm.getData_registro());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<CadastroMetasCampanhas> TabelaPesquisaTodos() throws Exception {
        con = new Conexao();
        CadastroMetasCampanhas objCamp = null;
        java.util.List<CadastroMetasCampanhas> campanhas = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.cad_campanha order by 2";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroMetasCampanhas();
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
            campanhas.add(objCamp);
        }
        con.getCONEXAO().close();
        return campanhas;
    }

    public CadastroMetasCampanhas TabelaPesquisaObs(String descricao) throws Exception {
        con = new Conexao();
        CadastroMetasCampanhas objCamp = null;
        String SQL = "SELECT * FROM relatorios.relatorio.cad_campanha where descricao_campanha = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, descricao);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroMetasCampanhas();
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
        }
        con.getCONEXAO().close();
        return objCamp;
    }
}
