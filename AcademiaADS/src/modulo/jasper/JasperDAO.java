package modulo.jasper;

import ConexaoData.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modulo.metodos.Funcao;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Marcos Junior
 */
public class JasperDAO {

    private Conexao con;
    private Funcao fun;
    private String urlUser = "relatorios/user/usuario.jasper";
    private String urlPro = "relatorios/user/produto.jasper";
    private String urlLoja = "relatorios/user/loja.jasper";
    private String urlListaCompleta = "relatorios/user/listaderuptura.jasper";
    private String urlListaPadrao = "relatorios/user/listaderupturapadrao.jasper";
    private String urlListageral = "relatorios/user/listageral.jasper";
    private String urlListarecuperada = "relatorios/user/listarecuperados.jasper";
    private String urlListaEnvio = "relatorios/user/listaenvio.jasper";
    private String urlListaReforco = "relatorios/user/listarsemRepetidos.jasper";

    public void RelatorioProduto() {
        try {
            con = new Conexao();
            fun = new Funcao();
            String SQL = "select * from relatorios.relatorio.produto order by 1";
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlPro, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório!" + ex);
        }
    }

    public void RelatorioUsuário() {
        try {
            con = new Conexao();
            fun = new Funcao();
            String SQL = "select * from relatorios.relatorio.usuario";
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlUser, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório! " + ex);
        }
    }

    public void RelatorioLoja() {
        try {
            con = new Conexao();
            fun = new Funcao();
            String SQL = "select * from relatorios.relatorio.loja";
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlLoja, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório! " + ex);
        }
    }

    public void RelatorioLista() {
        try {
            con = new Conexao();
            fun = new Funcao();
            String SQL = "select * from relatorios.relatorio.listaruptura2 order by 4";
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlListarecuperada, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório!" + ex);
        }
    }

    public void RelatorioReforco() {
        try {
            con = new Conexao();
            fun = new Funcao();
            String SQL = "SELECT sum(listaruptura.qtd_perdida) as qtd_perdida, listaruptura.cod_inter, listaruptura.descricao\n"
                    + "FROM relatorios.relatorio.listaruptura\n"
                    + "group by listaruptura.cod_inter, listaruptura.descricao\n"
                    + "order by 3;";
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlListaReforco, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório! " + ex);
        }
    }

    public void RelatorioReforcoData(Date data1, Date data2) {
        try {
            con = new Conexao();
            fun = new Funcao();
            String SQL = "SELECT sum(listaruptura.qtd_perdida) "
                    + "as qtd_perdida, "
                    + "listaruptura.cod_inter, "
                    + "listaruptura.descricao\n"
                    + "FROM relatorios.relatorio.listaruptura\n"
                    + "WHERE date_cad BETWEEN ? AND ?\n"
                    + "group by listaruptura.cod_inter, listaruptura.descricao\n"
                    + "order by 3;";
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ps.setDate(1, data1);
            ps.setDate(2, data2);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlListaReforco, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório! " + ex);
        }
    }

    public void RelatorioData(Date data1, Date data2, int loja) {
        String SQL = "SELECT * FROM relatorios.relatorio.listaruptura WHERE date_cad BETWEEN ? AND ? order by 4";
        try {
            con = new Conexao();
            fun = new Funcao();
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ps.setDate(1, data1);
            ps.setDate(2, data2);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlListaCompleta, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório! " + ex);
        }
    }

    public void RelatorioDataEnvio(Date data1, Date data2, int loja) {
        String SQL = "SELECT sum(listaruptura.qtd_perdida) "
                + "as qtd_perdida, "
                + "listaruptura.cod_inter, "
                + "listaruptura.descricao, "
                + "listaruptura.date_cad "
                + "FROM relatorios.relatorio.listaruptura "
                + "WHERE date_cad BETWEEN ? AND ? "
                + "group by listaruptura.cod_inter, listaruptura.descricao, "
                + "listaruptura.date_cad "
                + "order by 4;";
        try {
            con = new Conexao();
            fun = new Funcao();
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ps.setDate(1, data1);
            ps.setDate(2, data2);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlListaEnvio, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório!" + ex);
        }
    }

    public void RelatorioMatricula(int matricula, int numeroloja, Date data1, Date data2) {
        String SQL = "SELECT * FROM relatorios.relatorio.listaruptura, relatorios.relatorio.loja, "
                + "relatorios.relatorio.usuario WHERE usuario.matricula = ? AND "
                + "listaruptura.matricula = ? AND "
                + "loja.numero_loja = ? AND "
                + "listaruptura.date_cad BETWEEN ? AND ? order by 4";
        try {
            con = new Conexao();
            fun = new Funcao();
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ps.setInt(1, matricula);
            ps.setInt(2, matricula);
            ps.setInt(3, numeroloja);
            ps.setDate(4, data1);
            ps.setDate(5, data2);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlListaPadrao, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório! " + ex);
        }
    }

    public void RelatorioCodigoInterno(int codigo, int numeroloja, Date data1, Date data2) {
        String SQL = "SELECT * FROM relatorios.relatorio.listaruptura, relatorios.relatorio.loja "
                + "WHERE "
                + "listaruptura.cod_inter = ? AND "
                + "loja.numero_loja = ? AND "
                + "listaruptura.date_cad BETWEEN ? AND ? order by 4";
        try {
            con = new Conexao();
            fun = new Funcao();
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ps.setInt(1, codigo);
            ps.setInt(2, numeroloja);
            ps.setDate(3, data1);
            ps.setDate(4, data2);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlListageral, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório!" + ex);
        }
    }

    public void RelatorioDescricao(String descricao, int numeroloja, Date data1, Date data2) {
        String SQL = "SELECT * FROM relatorios.relatorio.listaruptura, relatorios.relatorio.loja "
                + "WHERE "
                + "listaruptura.descricao = ? AND "
                + "loja.numero_loja = ? AND "
                + "listaruptura.date_cad BETWEEN ? AND ? order by 4";
        try {
            con = new Conexao();
            fun = new Funcao();
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ps.setString(1, descricao);
            ps.setInt(2, numeroloja);
            ps.setDate(3, data1);
            ps.setDate(4, data2);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlListageral, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório! " + ex);
        }
    }

    public void RelatorioObservacao(String obs, int numeroloja, Date data1, Date data2) {
        String SQL = "SELECT * FROM relatorios.relatorio.listaruptura, relatorios.relatorio.loja "
                + "WHERE "
                + "listaruptura.obs = ? AND "
                + "loja.numero_loja = ? AND "
                + "listaruptura.date_cad BETWEEN ? AND ? order by 4";
        try {
            con = new Conexao();
            fun = new Funcao();
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ps.setString(1, obs);
            ps.setInt(2, numeroloja);
            ps.setDate(3, data1);
            ps.setDate(4, data2);
            ResultSet rs = ps.executeQuery();
            JRResultSetDataSource relaResult = new JRResultSetDataSource(rs);
            JasperPrint jpPrint = JasperFillManager.fillReport(urlListageral, new HashMap<>(), relaResult);
            fun.getFrameLocal(jpPrint);
            con.getCONEXAO().close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Relatório! " + ex);
        }
    }
}
