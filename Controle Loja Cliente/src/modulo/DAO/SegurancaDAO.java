package modulo.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modulo.entidades.Seguranca;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class SegurancaDAO {

    Conexao con;

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
            s.setSerialDeAutorizacao(rs.getString("serialaut"));
            s.setSeriralPlacaMae(rs.getString("serialplaca"));
            s.setStatus(rs.getString("status"));
            s.setDataRegistro(rs.getDate("datareg"));
        }
        con.getCONEXAO().close();
        return s;
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
}
