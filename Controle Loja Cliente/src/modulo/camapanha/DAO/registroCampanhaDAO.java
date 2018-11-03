package modulo.camapanha.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import modulo.campanhas.CadastroCampanhaDia;

/**
 *
 * @author Marcos Junior
 */
public class registroCampanhaDAO {

    Conexao con;

    public boolean Insert(CadastroCampanhaDia objCamp) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.campanha("
                + "matricula, "
                + "desc_campanha, "
                + "quantidade, "
                + "data_registro) "
                + "values (?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objCamp.getMatricula());
        ps.setString(2, objCamp.getDesc_campanha());
        ps.setInt(3, objCamp.getQuantidade());
        ps.setDate(4, objCamp.getData_registro());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean InsertUltimaChance(CadastroCampanhaDia obj) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.campanha("
                + "matricula, "
                + "desc_campanha, "
                + "valor, "
                + "data_registro) "
                + "values (?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, obj.getMatricula());
        ps.setString(2, obj.getDesc_campanha());
        ps.setDouble(3, obj.getUltimaChance());
        ps.setDate(4, obj.getData_registro());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }
}
