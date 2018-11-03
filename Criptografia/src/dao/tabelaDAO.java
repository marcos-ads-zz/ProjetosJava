package dao;

import base.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class tabelaDAO {

    public tabelaASCII CpesquisaTabelaASCII(int decimal) throws Exception {
        Conexao con = new Conexao();
        tabelaASCII objFun = null;
        String SQL = "select * from atividades.cifracesar.cifra where DecimalASCII = ?";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setInt(1, decimal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new tabelaASCII();
//            objFun.setId(rs.getInt("id"));
            objFun.setDecimalASCII(rs.getInt("DecimalASCII"));
//            objFun.setBinario(rs.getInt("Binario"));
//            objFun.setHexASCII(rs.getString("HexASCII"));
//            objFun.setReferencia(rs.getString("Referencia"));
//            objFun.setReferenciaCifrada(rs.getString("referenciaCifrada"));
            objFun.setDecimalASCIICifrado(rs.getInt("DecimalASCIICifrado"));
        }
        con.getConexao().close();
        return objFun;
    }

    public tabelaASCII DpesquisaTabelaASCII(int decimal) throws Exception {
        Conexao con = new Conexao();
        tabelaASCII objFun = null;
        String SQL = "select * from atividades.cifracesar.cifra where DecimalASCIICifrado = ?";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setInt(1, decimal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new tabelaASCII();
//            objFun.setId(rs.getInt("id"));
            objFun.setDecimalASCII(rs.getInt("DecimalASCII"));
//            objFun.setBinario(rs.getInt("Binario"));
//            objFun.setHexASCII(rs.getString("HexASCII"));
//            objFun.setReferencia(rs.getString("Referencia"));
//            objFun.setReferenciaCifrada(rs.getString("referenciaCifrada"));
            objFun.setDecimalASCIICifrado(rs.getInt("DecimalASCIICifrado"));
        }
        con.getConexao().close();
        return objFun;
    }
}
