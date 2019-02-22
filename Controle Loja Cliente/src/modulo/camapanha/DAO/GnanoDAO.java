/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.camapanha.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Marcos Junior
 */
public class GnanoDAO {

    Conexao con;

    public int TabelaPesquisaGnanoQtd(int matricula, String campanha, java.sql.Date dataInicio, java.sql.Date dataFinal) throws Exception {
        con = new Conexao();
        int total = 0;
        String SQL = "SELECT sum(quantidade) as qtd FROM relatorios.relatorio.campanha "
                + "where matricula = ? "
                + "and desc_campanha = ? "
                + "and data_registro "
                + "between ? and ?;";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, matricula);
        ps.setString(2, campanha);
        ps.setDate(3, dataInicio);
        ps.setDate(4, dataFinal);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            total = rs.getInt("qtd");
        }
        con.getCONEXAO().close();
        return total;
    }
}
