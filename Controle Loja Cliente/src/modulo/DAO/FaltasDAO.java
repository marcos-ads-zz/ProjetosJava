package modulo.DAO;

import ConexaoData.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modulo.entidades.Faltas;
import modulo.metodos.Funcao;

/**
 *
 * @author Marcos Junior
 */
public class FaltasDAO {

    Conexao con;

    public boolean Insert(Faltas objFun) throws Exception {

        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.listaruptura("
                + "cod_inter, "
                + "cod_ean, "
                + "descricao, "
                + "qtd_perdida, "
                + "matricula, "
                + "obs, "
                + "date_cad) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);

        ps.setInt(1, objFun.getCod_interno());
        ps.setString(2, objFun.getCod_produto());
        ps.setString(3, objFun.getDescricao().toUpperCase());
        ps.setInt(4, objFun.getQtd_perdida());
        ps.setInt(5, objFun.getMatricula());
        ps.setString(6, objFun.getObservacao().toUpperCase());
        ps.setDate(7, objFun.getData());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean CheckSelect(int cod_interno, Date Ultimos30, Date DataInicial) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.listaruptura where date_cad between ? and ? and cod_inter = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, Ultimos30);
        ps.setDate(2, DataInicial);
        ps.setInt(3, cod_interno);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }

    public int TabelaPesquisaRows(String dataInicio, String dataFinal) throws Exception {
        con = new Conexao();
        Funcao fun = new Funcao();
        int total = 0;
        String SQL = "SELECT count(*) as qtd FROM relatorio.listaruptura WHERE date_cad between ? and ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, fun.convertDateStringToDateSQL(dataInicio));
        ps.setDate(2, fun.convertDateStringToDateSQL(dataFinal));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            total = rs.getInt("qtd");
        }
        con.getCONEXAO().close();
        return total;
    }
}
