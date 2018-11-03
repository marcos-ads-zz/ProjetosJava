package modulo.camapanha.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import modulo.entidades.CampanhasPlanoDeVoo;

/**
 *
 * @author Marcos Junior
 */
public class PlanoVooDAO {

    Conexao con;

    public boolean Insert(CampanhasPlanoDeVoo objFar) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.plano_voo("
                + "matricula, "
                + "pdv1, "
                + "pdv2, "
                + "pdv3, "
                + "pdv4, "
                + "totalpdvs, "
                + "cli1, "
                + "cli2, "
                + "cli3, "
                + "cli4, "
                + "totalcli, "
                + "data_registro) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objFar.getMatricula());
        ps.setDouble(2, objFar.getPdv1());
        ps.setDouble(3, objFar.getPdv2());
        ps.setDouble(4, objFar.getPdv3());
        ps.setDouble(5, objFar.getPdv4());
        ps.setDouble(6, objFar.getTotalpdvs());
        ps.setInt(7, objFar.getCli1());
        ps.setInt(8, objFar.getCli2());
        ps.setInt(9, objFar.getCli3());
        ps.setInt(10, objFar.getCli4());
        ps.setInt(11, objFar.getTotalcli());
        ps.setDate(12, objFar.getData_registro());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }
}
