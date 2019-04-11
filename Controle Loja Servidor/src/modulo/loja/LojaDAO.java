/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.loja;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Marcos Junior
 */
public class LojaDAO {

    Conexao con;

    public boolean Insert(Loja objFun) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.loja(nome_loja, numero_loja, gerente) values (?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objFun.getNome_loja().toUpperCase());
        ps.setInt(2, objFun.getNumero_loja());
        ps.setString(3, objFun.getGerente_loja().toUpperCase());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public Loja PesquisaNumeroLoja(int id) throws Exception {
        con = new Conexao();
        Loja objFun = null;
        String SQL = "select * from relatorios.relatorio.loja where idloja = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
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

    public boolean Update(Loja objFun) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.loja set "
                + "nome_loja = ?, "
                + "gerente = ?, "
                + "numero_loja = ? "
                + "WHERE idloja = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objFun.getNome_loja().toUpperCase());
        ps.setString(2, objFun.getGerente_loja().toUpperCase());
        ps.setInt(3, objFun.getNumero_loja());
        ps.setInt(4, objFun.getId());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean CheckSelect(int id) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.loja where idloja = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }
}
