package modulo.camapanha.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import modulo.campanhas.CadastroDescricaoCampanhas;

/**
 *
 * @author Marcos Junior
 */
public class CadastroDescricaoCampanhasDAO {

    Conexao con;

    public boolean Insert(CadastroDescricaoCampanhas objItens) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.itens_campanha("
                + "descricaoCampanha, "
                + "values (?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objItens.getDescricaoCampanha());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }
}
