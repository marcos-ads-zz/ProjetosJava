package modulo.metas;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class MetasDAO {

    Conexao con;

    public boolean Insert(Metas objMeta) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.metas("
                + "valor_meta, "
                + "valor_recarga, "
                + "valor_renta, "
                + "valor_vip, "
                + "meta_power, "
                + "meta_gnano, "
                + "meta_gero, "
                + "meta_clinic, "
                + "meta_anotacao, "
                + "mes, "
                + "ano, "
                + "n_loja, "
                + "date_registro) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDouble(1, objMeta.getValor_meta());
        ps.setDouble(2, objMeta.getValor_recarga());
        ps.setDouble(3, objMeta.getValor_renta());
        ps.setDouble(4, objMeta.getValor_vip());
        ps.setInt(5, objMeta.getMeta_power());
        ps.setInt(6, objMeta.getMeta_gnano());
        ps.setInt(7, objMeta.getMeta_gero());
        ps.setInt(8, objMeta.getMeta_clinic());
        ps.setInt(9, objMeta.getMeta_anotacao());
        ps.setString(10, objMeta.getMes().toUpperCase());
        ps.setInt(11, objMeta.getAno());
        ps.setInt(12, objMeta.getN_loja());
        ps.setDate(13, objMeta.getDate_registro());

        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Update(Metas objMeta) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.metas set "
                + "valor_meta = ?, "
                + "valor_recarga = ?, "
                + "valor_renta = ?, "
                + "valor_vip = ?, "
                + "meta_power = ?, "
                + "meta_gnano = ?, "
                + "meta_gero = ?, "
                + "meta_clinic = ?, "
                + "meta_anotacao = ?, "
                + "mes = ?, "
                + "ano = ?, "
                + "n_loja = ?, "
                + "date_registro = ? "
                + "WHERE id_metas = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDouble(1, objMeta.getValor_meta());
        ps.setDouble(2, objMeta.getValor_recarga());
        ps.setDouble(3, objMeta.getValor_renta());
        ps.setDouble(4, objMeta.getValor_vip());
        ps.setInt(5, objMeta.getMeta_power());
        ps.setInt(6, objMeta.getMeta_gnano());
        ps.setInt(7, objMeta.getMeta_gero());
        ps.setInt(8, objMeta.getMeta_clinic());
        ps.setInt(9, objMeta.getMeta_anotacao());
        ps.setString(10, objMeta.getMes().toUpperCase());
        ps.setInt(11, objMeta.getAno());
        ps.setInt(12, objMeta.getN_loja());
        ps.setDate(13, objMeta.getDate_registro());
        ps.setInt(14, objMeta.getId_metas());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.metas where id_metas = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

    public List<Metas> PesquisaMetaPorMes(String mes, int ano) throws Exception {
        con = new Conexao();
        Metas objFun = null;
        List<Metas> metas = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.metas where mes = ? and ano = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, mes.toUpperCase());
        ps.setInt(2, ano);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Metas();
            objFun.setId_metas(rs.getInt("id_metas"));
            objFun.setValor_meta(rs.getDouble("valor_meta"));
            objFun.setValor_recarga(rs.getDouble("valor_recarga"));
            objFun.setValor_renta(rs.getDouble("valor_renta"));
            objFun.setValor_vip(rs.getDouble("valor_vip"));
            objFun.setMeta_anotacao(rs.getInt("meta_power"));
            objFun.setMeta_clinic(rs.getInt("meta_gnano"));
            objFun.setMeta_gero(rs.getInt("meta_gero"));
            objFun.setMeta_gnano(rs.getInt("meta_clinic"));
            objFun.setMeta_power(rs.getInt("meta_anotacao"));
            objFun.setMes(rs.getString("mes"));
            objFun.setAno(rs.getInt("ano"));
            objFun.setN_loja(rs.getInt("n_loja"));
            objFun.setDate_registro(rs.getDate("date_registro"));
            metas.add(objFun);
        }
        con.getCONEXAO().close();
        return metas;
    }

    public List<Metas> TabelaPesquisaSemParametro() throws Exception {
        con = new Conexao();
        Metas objMetas = null;
        List<Metas> metas = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.metas";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objMetas = new Metas();
            objMetas.setId_metas(rs.getInt("id_metas"));
            objMetas.setValor_meta(rs.getDouble("valor_meta"));
            objMetas.setValor_recarga(rs.getDouble("valor_recarga"));
            objMetas.setValor_renta(rs.getDouble("valor_renta"));
            objMetas.setValor_vip(rs.getDouble("valor_vip"));
            objMetas.setMeta_anotacao(rs.getInt("meta_power"));
            objMetas.setMeta_clinic(rs.getInt("meta_gnano"));
            objMetas.setMeta_gero(rs.getInt("meta_gero"));
            objMetas.setMeta_gnano(rs.getInt("meta_clinic"));
            objMetas.setMeta_power(rs.getInt("meta_anotacao"));
            objMetas.setMes(rs.getString("mes"));
            objMetas.setAno(rs.getInt("ano"));
            objMetas.setN_loja(rs.getInt("n_loja"));
            objMetas.setDate_registro(rs.getDate("date_registro"));
            metas.add(objMetas);
        }
        con.getCONEXAO().close();
        return metas;
    }

    public List<Metas> TabelaPesquisaPorAno(int ano) throws Exception {
        con = new Conexao();
        Metas objMetas = null;
        List<Metas> metas = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.metas where ano = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, ano);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objMetas = new Metas();
            objMetas.setId_metas(rs.getInt("id_metas"));
            objMetas.setValor_meta(rs.getDouble("valor_meta"));
            objMetas.setValor_recarga(rs.getDouble("valor_recarga"));
            objMetas.setValor_renta(rs.getDouble("valor_renta"));
            objMetas.setValor_vip(rs.getDouble("valor_vip"));
            objMetas.setMeta_anotacao(rs.getInt("meta_power"));
            objMetas.setMeta_clinic(rs.getInt("meta_gnano"));
            objMetas.setMeta_gero(rs.getInt("meta_gero"));
            objMetas.setMeta_gnano(rs.getInt("meta_clinic"));
            objMetas.setMeta_power(rs.getInt("meta_anotacao"));
            objMetas.setMes(rs.getString("mes"));
            objMetas.setAno(rs.getInt("ano"));
            objMetas.setN_loja(rs.getInt("n_loja"));
            objMetas.setDate_registro(rs.getDate("date_registro"));
            metas.add(objMetas);
        }
        con.getCONEXAO().close();
        return metas;
    }

    public Metas PesquisarPorMetaPorMes(String mes, int ano) throws Exception {
        con = new Conexao();
        Metas objFun = null;
        String SQL = "select * from relatorios.relatorio.metas where mes = ? and ano = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, mes.toUpperCase());
        ps.setInt(2, ano);
        ResultSet rs = ps.executeQuery();
        objFun = new Metas();
        objFun.setId_metas(rs.getInt("id_metas"));
        objFun.setValor_meta(rs.getDouble("valor_meta"));
        objFun.setValor_recarga(rs.getDouble("valor_recarga"));
        objFun.setValor_renta(rs.getDouble("valor_renta"));
        objFun.setValor_vip(rs.getDouble("valor_vip"));
        objFun.setMeta_anotacao(rs.getInt("meta_power"));
        objFun.setMeta_clinic(rs.getInt("meta_gnano"));
        objFun.setMeta_gero(rs.getInt("meta_gero"));
        objFun.setMeta_gnano(rs.getInt("meta_clinic"));
        objFun.setMeta_power(rs.getInt("meta_anotacao"));
        objFun.setMes(rs.getString("mes"));
        objFun.setAno(rs.getInt("ano"));
        objFun.setN_loja(rs.getInt("n_loja"));
        objFun.setDate_registro(rs.getDate("date_registro"));
        con.getCONEXAO().close();
        return objFun;
    }

    public boolean CheckSelect(int ano) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.metas where ano = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, ano);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }
}
