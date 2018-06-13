package modulo.faturamento;

import ConexaoData.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modulo.loja.Loja;

/**
 *
 * @author Marcos Junior
 */
public class FaturamentoDAO {

    private Conexao con;

    public int Insert(Faturamento objFat) throws Exception {

        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.faturamento_caixa("
                + "pdv, "
                + "id_caixa, "
                + "matricula, "
                + "turno, "
                + "date_registro, "
                + "date_venda, "
                + "valor_fita, "
                + "valor_credito_digital, "
                + "valor_faturas, "
                + "qtd_cli_geral, "
                + "qtd_cli_credito, "
                + "qtd_cli_fatura, "
                + "valor_cupom_vip, "
                + "valor_desc_vip, "
                + "qtd_itens_vendidos, "
                + "valor_trocas, "
                + "qtd_trocas, "
                + "valor_devolucoes, "
                + "qtd_devolucao, "
                + "valor_venda_caixa, "
                + "qtd_cli_caixa) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//21
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, objFat.getPdv());
        ps.setInt(2, objFat.getId_caixa());
        ps.setInt(3, objFat.getMatricula());
        ps.setString(4, objFat.getTurno());
        ps.setDate(5, objFat.getDate_registro());
        ps.setDate(6, objFat.getDate_venda());
        ps.setDouble(7, objFat.getValor_fita());
        ps.setDouble(8, objFat.getValor_credito_digital());
        ps.setDouble(9, objFat.getValor_faturas());
        ps.setInt(10, objFat.getQtd_cli_geral());
        ps.setInt(11, objFat.getQtd_cli_credito());
        ps.setInt(12, objFat.getQtd_cli_fatura());
        ps.setDouble(13, objFat.getValor_cupom_vip());
        ps.setDouble(14, objFat.getValor_desc_vip());
        ps.setInt(15, objFat.getQtd_itens_vendidos());
        ps.setDouble(16, objFat.getValor_trocas());
        ps.setInt(17, objFat.getQtd_trocas());
        ps.setDouble(18, objFat.getValor_devolucoes());
        ps.setInt(19, objFat.getQtd_devolucao());
        ps.setDouble(20, objFat.getValor_venda_caixa());
        ps.setInt(21, objFat.getQtd_cli_caixa());

        int id = 0;
        if (ps.executeUpdate()
                > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }
        con.getCONEXAO()
                .close();
        return id;
    }

    public boolean Update(Faturamento objFat) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.faturamento_caixa set "
                + "pdv = ?, "
                + "id_caixa = ?, "
                + "matricula = ?, "
                + "turno = ?, "
                + "date_registro = ?, "
                + "date_venda = ?, "
                + "valor_fita = ?, "
                + "valor_credito_digital = ?, "
                + "valor_faturas = ?, "
                + "qtd_cli_geral = ?, "
                + "qtd_cli_credito = ?, "
                + "qtd_cli_fatura = ?, "
                + "valor_cupom_vip = ?, "
                + "valor_desc_vip = ?, "
                + "qtd_itens_vendidos = ?, "
                + "valor_trocas = ?, "
                + "qtd_trocas = ?, "
                + "valor_devolucoes = ?, "
                + "qtd_devolucao = ?, "
                + "valor_venda_caixa = ?, "
                + "qtd_cli_caixa = ? "
                + "WHERE id_fat = ?;";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objFat.getPdv());
        ps.setInt(2, objFat.getId_caixa());
        ps.setInt(3, objFat.getMatricula());
        ps.setString(4, objFat.getTurno());
        ps.setDate(5, objFat.getDate_registro());
        ps.setDate(6, objFat.getDate_venda());
        ps.setDouble(7, objFat.getValor_fita());
        ps.setDouble(8, objFat.getValor_credito_digital());
        ps.setDouble(9, objFat.getValor_faturas());
        ps.setInt(10, objFat.getQtd_cli_geral());
        ps.setInt(11, objFat.getQtd_cli_credito());
        ps.setInt(12, objFat.getQtd_cli_fatura());
        ps.setDouble(13, objFat.getValor_cupom_vip());
        ps.setDouble(14, objFat.getValor_desc_vip());
        ps.setInt(15, objFat.getQtd_itens_vendidos());
        ps.setDouble(16, objFat.getValor_trocas());
        ps.setInt(17, objFat.getQtd_trocas());
        ps.setDouble(18, objFat.getValor_devolucoes());
        ps.setInt(19, objFat.getQtd_devolucao());
        ps.setDouble(20, objFat.getValor_venda_caixa());
        ps.setInt(21, objFat.getQtd_cli_caixa());
        ps.setInt(22, objFat.getId_fat());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<Faturamento> TabelaPesquisa() throws Exception {
        con = new Conexao();
        Faturamento objFat = null;
        List<Faturamento> faturamento = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.faturamento_caixa";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFat = new Faturamento();
            objFat.setDate_registro(rs.getDate("date_registro"));
            objFat.setDate_venda(rs.getDate("date_venda"));
            objFat.setId_caixa(rs.getInt("id_caixa"));
            objFat.setId_fat(rs.getInt("id_fat"));
            objFat.setMatricula(rs.getInt("matricula"));
            objFat.setPdv(rs.getInt("pdv"));
            objFat.setQtd_cli_caixa(rs.getInt("qtd_cli_caixa"));
            objFat.setQtd_cli_credito(rs.getInt("qtd_cli_credito"));
            objFat.setQtd_cli_fatura(rs.getInt("qtd_cli_fatura"));
            objFat.setQtd_cli_geral(rs.getInt("qtd_cli_geral"));
            objFat.setQtd_devolucao(rs.getInt("qtd_devolucao"));
            objFat.setQtd_itens_vendidos(rs.getInt("qtd_itens_vendidos"));
            objFat.setQtd_trocas(rs.getInt("qtd_trocas"));
            objFat.setTurno(rs.getString("turno"));
            objFat.setValor_credito_digital(rs.getInt("valor_credito_digital"));
            objFat.setValor_cupom_vip(rs.getInt("valor_cupom_vip"));
            objFat.setValor_desc_vip(rs.getInt("valor_desc_vip"));
            objFat.setValor_devolucoes(rs.getInt("valor_devolucoes"));
            objFat.setValor_faturas(rs.getInt("valor_faturas"));
            objFat.setValor_fita(rs.getInt("valor_fita"));
            objFat.setValor_trocas(rs.getInt("valor_trocas"));
            objFat.setValor_venda_caixa(rs.getInt("valor_venda_caixa"));
            faturamento.add(objFat);
        }
        con.getCONEXAO().close();
        return faturamento;
    }

    public List<Faturamento> TabelaPesquisaData(Date dataFinal, Date dataInical) throws Exception {
        con = new Conexao();
        Faturamento objFat = null;
        List<Faturamento> faturamento = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.faturamento_caixa where date_venda between ? and ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataInical);
        ps.setDate(2, dataFinal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFat = new Faturamento();
            objFat.setDate_registro(rs.getDate("date_registro"));
            objFat.setDate_venda(rs.getDate("date_venda"));
            objFat.setId_caixa(rs.getInt("id_caixa"));
            objFat.setId_fat(rs.getInt("id_fat"));
            objFat.setMatricula(rs.getInt("matricula"));
            objFat.setPdv(rs.getInt("pdv"));
            objFat.setQtd_cli_caixa(rs.getInt("qtd_cli_caixa"));
            objFat.setQtd_cli_credito(rs.getInt("qtd_cli_credito"));
            objFat.setQtd_cli_fatura(rs.getInt("qtd_cli_fatura"));
            objFat.setQtd_cli_geral(rs.getInt("qtd_cli_geral"));
            objFat.setQtd_devolucao(rs.getInt("qtd_devolucao"));
            objFat.setQtd_itens_vendidos(rs.getInt("qtd_itens_vendidos"));
            objFat.setQtd_trocas(rs.getInt("qtd_trocas"));
            objFat.setTurno(rs.getString("turno"));
            objFat.setValor_credito_digital(rs.getInt("valor_credito_digital"));
            objFat.setValor_cupom_vip(rs.getInt("valor_cupom_vip"));
            objFat.setValor_desc_vip(rs.getInt("valor_desc_vip"));
            objFat.setValor_devolucoes(rs.getInt("valor_devolucoes"));
            objFat.setValor_faturas(rs.getInt("valor_faturas"));
            objFat.setValor_fita(rs.getInt("valor_fita"));
            objFat.setValor_trocas(rs.getInt("valor_trocas"));
            objFat.setValor_venda_caixa(rs.getInt("valor_venda_caixa"));
            faturamento.add(objFat);
        }
        con.getCONEXAO().close();
        return faturamento;
    }
}
