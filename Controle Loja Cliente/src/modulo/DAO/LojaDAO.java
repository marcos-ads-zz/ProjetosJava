package modulo.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modulo.entidades.Loja;

/**
 *
 * @author Marcos Junior
 */
public class LojaDAO {

    Conexao con;


    public Loja PesquisaNumeroLoja(int numero_loja) throws Exception {
        con = new Conexao();
        Loja objFun = null;
        String SQL = "select * from relatorios.relatorio.loja where numero_loja = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, numero_loja);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Loja();
            objFun.setId(rs.getInt("idloja"));
            objFun.setNome_loja(rs.getString("nome_loja"));
            objFun.setNumero_loja(rs.getInt("numero_loja"));
            objFun.setGerente_loja(rs.getString("gerente"));
        }
        con.getCONEXAO().close();
        return objFun;
    }





}
