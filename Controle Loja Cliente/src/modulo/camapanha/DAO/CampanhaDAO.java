/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.camapanha.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modulo.entidades.Campanha;

/**
 *
 * @author Marcos Junior
 */
public class CampanhaDAO {

    Conexao con;

    public int TabelaPesquisaCampanhasQtdS(String campanha, java.sql.Date dataInicio, java.sql.Date dataFinal) throws Exception {
        con = new Conexao();
        int total = 0;

        String SQL = "SELECT sum(quantidade) as qtd FROM relatorios.relatorio.campanha "
                + "where desc_campanha = ? "
                + "and data_registro "
                + "between ? and ?;";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, campanha);
        ps.setDate(2, dataInicio);
        ps.setDate(3, dataFinal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            total = rs.getInt("qtd");
        }

        con.getCONEXAO().close();
        return total;
    }

    public int TabelaPesquisaCampanhasQtd(int matricula, String campanha, java.sql.Date dataInicio, java.sql.Date dataFinal) throws Exception {
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

    public List<Campanha> TabelaPesquisaCampanhas(java.sql.Date dataInicio, java.sql.Date dataFinal) throws Exception {
        con = new Conexao();
        Campanha campObj;
        List<Campanha> camp = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.campanha "
                + "where data_registro "
                + "between ? and ?;";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataInicio);
        ps.setDate(2, dataFinal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            campObj = new Campanha();
            campObj.setId(rs.getInt("id"));
            campObj.setDesc_campanha(rs.getString("desc_campanha"));
            camp.add(campObj);
        }
        con.getCONEXAO().close();
        return camp;
    }
}
