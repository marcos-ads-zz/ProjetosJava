package modulo.camapanha.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modulo.entidades.CampanhasPlanoDeVoo;

/**
 *
 * @author Marcos Junior
 */
public class PlanoVooDAO {

    Conexao con;

    public boolean Insert(CampanhasPlanoDeVoo objVoo) throws Exception {
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
        ps.setInt(1, objVoo.getMatricula());
        ps.setDouble(2, objVoo.getPdv1());
        ps.setDouble(3, objVoo.getPdv2());
        ps.setDouble(4, objVoo.getPdv3());
        ps.setDouble(5, objVoo.getPdv4());
        ps.setDouble(6, objVoo.getTotalpdvs());
        ps.setInt(7, objVoo.getCli1());
        ps.setInt(8, objVoo.getCli2());
        ps.setInt(9, objVoo.getCli3());
        ps.setInt(10, objVoo.getCli4());
        ps.setInt(11, objVoo.getTotalcli());
        ps.setDate(12, objVoo.getData_registro());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Update(CampanhasPlanoDeVoo objVoo) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.plano_voo "
                + "set "
                + "pdv1 = ?, "
                + "pdv2 = ?, "
                + "pdv3 = ?, "
                + "pdv4 = ?, "
                + "totalpdvs = ?, "
                + "cli1 = ?, "
                + "cli2 = ?, "
                + "cli3 = ?, "
                + "cli4 = ?, "
                + "totalcli = ? "
                + "WHERE matricula = ?"
                + "AND data_registro = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDouble(1, objVoo.getPdv1());
        ps.setDouble(2, objVoo.getPdv2());
        ps.setDouble(3, objVoo.getPdv3());
        ps.setDouble(4, objVoo.getPdv4());
        ps.setDouble(5, objVoo.getTotalpdvs());
        ps.setInt(6, objVoo.getCli1());
        ps.setInt(7, objVoo.getCli2());
        ps.setInt(8, objVoo.getCli3());
        ps.setInt(9, objVoo.getCli4());
        ps.setInt(10, objVoo.getTotalcli());
        ps.setInt(11, objVoo.getMatricula());
        ps.setDate(12, objVoo.getData_registro());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<CampanhasPlanoDeVoo> PesquisaPorMatricula(int matricula) throws Exception {
        con = new Conexao();
        CampanhasPlanoDeVoo objVoo = null;
        List<CampanhasPlanoDeVoo> voo = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.cliente order by 3";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objVoo = new CampanhasPlanoDeVoo();
            objVoo.setMatricula(rs.getInt("matricula"));
            objVoo.setPdv1(rs.getDouble("pdv1"));
            objVoo.setPdv2(rs.getDouble("pdv2"));
            objVoo.setPdv3(rs.getDouble("pdv3"));
            objVoo.setPdv4(rs.getDouble("pdv4"));
            objVoo.setTotalpdvs(rs.getDouble("totalpdvs"));
            objVoo.setCli1(rs.getInt("cli1"));
            objVoo.setCli2(rs.getInt("cli2"));
            objVoo.setCli3(rs.getInt("cli3"));
            objVoo.setCli4(rs.getInt("cli4"));
            objVoo.setTotalcli(rs.getInt("totalcli"));
            objVoo.setData_registro(rs.getDate("data_registro"));
            voo.add(objVoo);
        }
        con.getCONEXAO().close();
        return voo;

    }

    public List<CampanhasPlanoDeVoo> PesquisaPorData(java.sql.Date dataInicio, java.sql.Date dataFim) throws Exception {
        con = new Conexao();
        CampanhasPlanoDeVoo objVoo = null;
        List<CampanhasPlanoDeVoo> voo = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.cliente order by 3";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objVoo = new CampanhasPlanoDeVoo();
            objVoo.setMatricula(rs.getInt("matricula"));
            objVoo.setPdv1(rs.getDouble("pdv1"));
            objVoo.setPdv2(rs.getDouble("pdv2"));
            objVoo.setPdv3(rs.getDouble("pdv3"));
            objVoo.setPdv4(rs.getDouble("pdv4"));
            objVoo.setTotalpdvs(rs.getDouble("totalpdvs"));
            objVoo.setCli1(rs.getInt("cli1"));
            objVoo.setCli2(rs.getInt("cli2"));
            objVoo.setCli3(rs.getInt("cli3"));
            objVoo.setCli4(rs.getInt("cli4"));
            objVoo.setTotalcli(rs.getInt("totalcli"));
            objVoo.setData_registro(rs.getDate("data_registro"));
            voo.add(objVoo);
        }
        con.getCONEXAO().close();
        return voo;

    }

    public CampanhasPlanoDeVoo PesquisarUserIndividual(int matricula, java.sql.Date dataRegistro) throws Exception {
        con = new Conexao();
        CampanhasPlanoDeVoo objVoo = null;

        String SQL = "SELECT * FROM relatorios.relatorio.plano_voo "
                + "where matricula = ? "
                + "and data_registro = ? ";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, matricula);
        ps.setDate(2, dataRegistro);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objVoo = new CampanhasPlanoDeVoo();
            objVoo.setMatricula(rs.getInt("matricula"));
            objVoo.setPdv1(rs.getDouble("pdv1"));
            objVoo.setPdv2(rs.getDouble("pdv2"));
            objVoo.setPdv3(rs.getDouble("pdv3"));
            objVoo.setPdv4(rs.getDouble("pdv4"));
            objVoo.setTotalpdvs(rs.getDouble("totalpdvs"));
            objVoo.setCli1(rs.getInt("cli1"));
            objVoo.setCli2(rs.getInt("cli2"));
            objVoo.setCli3(rs.getInt("cli3"));
            objVoo.setCli4(rs.getInt("cli4"));
            objVoo.setTotalcli(rs.getInt("totalcli"));
            objVoo.setData_registro(rs.getDate("data_registro"));
        }
        con.getCONEXAO().close();
        return objVoo;

    }

    public boolean ChekRegistro(int matricula, java.sql.Date dataRegistro) throws Exception {
        con = new Conexao();
        CampanhasPlanoDeVoo objVoo = null;

        String SQL = "SELECT * FROM relatorios.relatorio.plano_voo "
                + "where matricula = ? "
                + "and data_registro = ? ";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, matricula);
        ps.setDate(2, dataRegistro);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objVoo = new CampanhasPlanoDeVoo();
            objVoo.setMatricula(rs.getInt("matricula"));
        }
        con.getCONEXAO().close();
        //System.out.println("modulo.camapanha.DAO.PlanoVooDAO.ChekRegistro()" + objVoo == null);
        return objVoo == null;

    }

}
