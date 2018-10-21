package modulo.seguranca;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class SegurancaDAO {

    Conexao con;

    public boolean Insert(Seguranca Serial) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.seguranca("
                + "autorizador, "
                + "serialaut, "
                + "serialplaca, "
                + "status, "
                + "datareg, "
                + "obs) values (?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, Serial.getAutorizador());
        ps.setString(2, Serial.getMD5DeAutorizacao().toUpperCase());
        ps.setString(1, Serial.getSeriralPlacaMae().toUpperCase());
        ps.setString(2, Serial.getStatus().toUpperCase());
        ps.setDate(1, Serial.getDataRegistro());
        ps.setString(2, Serial.getObs().toUpperCase());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }
    
    public boolean Update(Seguranca Serial) throws Exception {
        con = new Conexao();
        String SQL = "update relatorios.relatorio.seguranca set "
                + "autorizador = ?, "
                + "serialaut = ?, "
                + "serialplaca = ?, "
                + "status = ?, "
                + "datareg = ?, "
                + "obs = ? "
                + "WHERE serialplaca = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, Serial.getAutorizador());
        ps.setString(2, Serial.getMD5DeAutorizacao().toUpperCase());
        ps.setString(3, Serial.getSeriralPlacaMae().toUpperCase());
        ps.setString(4, Serial.getStatus().toUpperCase());
        ps.setDate(5, Serial.getDataRegistro());
        ps.setString(6, Serial.getObs().toUpperCase());
        ps.setString(7, Serial.getSeriralPlacaMae().toUpperCase());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean InsertSerial(Seguranca Serial) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.seguranca(serialplaca, datareg) values (?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, Serial.getSeriralPlacaMae().toUpperCase());
        ps.setDate(2, Serial.getDataRegistro());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean CheckSelect(String u) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.seguranca where serialplaca = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, u);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }

    public Seguranca PesquisaRegistro(String serial) throws Exception {
        con = new Conexao();
        Seguranca s = null;

        String SQL = "select * from relatorios.relatorio.seguranca where serialplaca = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);

        ps.setString(1, serial);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            s = new Seguranca();
            s.setId(rs.getInt("id"));
            s.setMD5DeAutorizacao(rs.getString("serialaut"));
            s.setSeriralPlacaMae(rs.getString("serialplaca"));
            s.setStatus(rs.getString("status"));
            s.setDataRegistro(rs.getDate("datareg"));
        }
        con.getCONEXAO().close();
        return s;
    }

    public List<Seguranca> PesquisaGeral() throws Exception {
        con = new Conexao();
        Seguranca s = null;
        List<Seguranca> segura = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.seguranca";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            s = new Seguranca();
            s.setId(rs.getInt("id"));
            s.setAutorizador(rs.getInt("autorizador"));
            s.setMD5DeAutorizacao(rs.getString("serialaut"));
            s.setSeriralPlacaMae(rs.getString("serialplaca"));
            s.setStatus(rs.getString("status"));
            s.setDataRegistro(rs.getDate("datareg"));
            s.setObs(rs.getString("obs"));
            segura.add(s);
        }
        con.getCONEXAO().close();
        return segura;
    }
}
