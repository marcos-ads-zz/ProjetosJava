package modulo.ctf;

import ConexaoData.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class CtfDAO {

    Conexao con;

    public int Insert(CTF objCtf) throws Exception {
        int id = 0;
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.ctf("
                + "pdv, "
                + "nsu_ctf, "
                + "mac, "
                + "valor, "
                + "date_ctf, "
                + "date_registro, "
                + "status, "
                + "id_caixa) "
                + "values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, objCtf.getPdv());
        ps.setInt(2, objCtf.getNsu());
        ps.setInt(3, objCtf.getMac());
        ps.setDouble(4, objCtf.getValor());
        ps.setDate(5, objCtf.getDate_ctf());
        ps.setDate(6, objCtf.getDate_registro());
        ps.setString(7, objCtf.getStatus());
        ps.setInt(8, objCtf.getId_user());

        if (ps.executeUpdate() > 0) {
            ResultSet rs = ps.getGeneratedKeys(); //Aqui você vai receber as chaves criadas, no seu caso será só uma, mas se tiver mais itens auto incremento ele te retornaria.
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } //Não esqueça de implementar o resto, try, catch e fechar as conexões.  
        con.getCONEXAO().close();
        return id;
    }

    public boolean Update(CTF objCtf) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.ctf set "
                + "pdv = ?, "
                + "nsu_ctf = ?, "
                + "mac = ?, "
                + "valor = ?, "
                + "date_ctf = ?, "
                + "status = ?, "
                + "id_caixa = ? "
                + "WHERE id_ctf = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objCtf.getPdv());
        ps.setInt(2, objCtf.getNsu());
        ps.setInt(3, objCtf.getMac());
        ps.setDouble(4, objCtf.getValor());
        ps.setDate(5, objCtf.getDate_ctf());
        ps.setString(6, objCtf.getStatus());
        ps.setInt(7, objCtf.getId_user());
        ps.setInt(8, objCtf.getId_ctf());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.ctf where id_ctf = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<CTF> TabelaPesquisaDataAtual(Date dataInicio, Date dataFim) throws Exception {
        con = new Conexao();
        CTF objCtf = null;
        List<CTF> ctf = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.ctf where date_registro between ? and ? order by 6";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataInicio);
        ps.setDate(2, dataFim);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCtf = new CTF();
            objCtf.setId_ctf(rs.getInt("id_ctf"));
            objCtf.setPdv(rs.getInt("pdv"));
            objCtf.setNsu(rs.getInt("nsu_ctf"));
            objCtf.setMac(rs.getInt("mac"));
            objCtf.setValor(rs.getDouble("valor"));
            objCtf.setId_user(rs.getInt("id_caixa"));
            objCtf.setDate_ctf(rs.getDate("date_ctf"));
            objCtf.setDate_registro(rs.getDate("date_registro"));
            objCtf.setStatus(rs.getString("status"));
            ctf.add(objCtf);
        }
        con.getCONEXAO().close();
        return ctf;

    }

    public List<CTF> PesquisaDataCTF(Date data) throws Exception {
        con = new Conexao();
        CTF objCtf = null;
        List<CTF> ctf = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.ctf where date_ctf = ? order by 3";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, data);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCtf = new CTF();
            objCtf.setId_ctf(rs.getInt("id_ctf"));
            objCtf.setPdv(rs.getInt("pdv"));
            objCtf.setNsu(rs.getInt("nsu_ctf"));
            objCtf.setMac(rs.getInt("mac"));
            objCtf.setValor(rs.getDouble("valor"));
            objCtf.setId_user(rs.getInt("id_caixa"));
            objCtf.setDate_ctf(rs.getDate("date_ctf"));
            objCtf.setDate_registro(rs.getDate("date_registro"));
            objCtf.setStatus(rs.getString("status"));
            ctf.add(objCtf);
        }
        con.getCONEXAO().close();
        return ctf;

    }

    public boolean CheckSelect(int id) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.ctf where id_ctf like =?";
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
