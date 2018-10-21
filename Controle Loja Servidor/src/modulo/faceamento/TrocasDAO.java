package modulo.faceamento;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import modulo.troca.ItensDeixados;
import modulo.troca.ItensLevados;
import modulo.troca.Trocas;

/**
 *
 * @author Marcos Junior
 */
public class TrocasDAO {

    private String SQLTroca = "insert into relatorios.relatorio.trocaedevolucao("
            + "id_autorizador, "
            + "id_caixa, "
            + "id_lojaCupom, "
            + "id_lojaTroca, "
            + "id_cli, "
            + "date_registro, "
            + "hora_registro, "
            + "n_cupom, n_pdv, "
            + "date_cupom, "
            + "tipo, "
            + "motivo_registro) values (?,?,?,?,?,?,?,?,?,?,?,?);";

    private String SQLDeixados = "insert into relatorios.relatorio.itensdeixados("
            + "id_troca, "
            + "id_produto, "
            + "date_cupom, "
            + "date_registro, "
            + "quantidade, "
            + "valorunitario, "
            + "valortotaldeixado) "
            + "values (?,?,?,?,?,?,?)";//7

    private String SQLLevados = "insert into relatorios.relatorio.itenslevados("
            + "id_troca, "
            + "id_produto, "
            + "date_cupom, "
            + "date_registro, "
            + "quantidade, "
            + "valorunitario, "
            + "valortotallevado) "
            + "values (?,?,?,?,?,?,?)";//7

    public void Insert(Trocas objTroca) throws Exception {

        Conexao conTroca = new Conexao();

        PreparedStatement psTroca = conTroca.getCONEXAO().prepareStatement(SQLTroca, Statement.RETURN_GENERATED_KEYS);
        psTroca.setInt(1, objTroca.getId_autorizador());
        System.out.println("getId_autorizador: " + objTroca.getId_autorizador());
        psTroca.setInt(2, objTroca.getId_caixa());
        System.out.println("getId_caixa: " + objTroca.getId_caixa());
        psTroca.setInt(3, objTroca.getId_lojaCupom());
        System.out.println("getId_lojaCupom: " + objTroca.getId_lojaCupom());
        psTroca.setInt(4, objTroca.getId_lojaRegistro());
        System.out.println("getId_lojaRegistro: " + objTroca.getId_lojaRegistro());
        psTroca.setInt(5, objTroca.getId_cli());
        System.out.println("getId_cli: " + objTroca.getId_cli());
        psTroca.setDate(6, objTroca.getDate_registro());
        System.out.println("getDate_registro: " + objTroca.getDate_registro());
        psTroca.setString(7, objTroca.getHora_registro());
        System.out.println("getHora_registro: " + objTroca.getHora_registro());
        psTroca.setInt(8, objTroca.getN_cupom());
        System.out.println("getN_cupom: " + objTroca.getN_cupom());
        psTroca.setInt(9, objTroca.getN_pdv());
        System.out.println("getN_pdv: " + objTroca.getN_pdv());
        psTroca.setDate(10, objTroca.getDate_cupom());
        System.out.println("getDate_cupom: " + objTroca.getDate_cupom());
        psTroca.setString(11, objTroca.getTipo().toUpperCase());
        System.out.println("getTipo: " + objTroca.getTipo());
        psTroca.setString(12, objTroca.getMotivo_registro().toUpperCase());
        System.out.println("getMotivo_registro: " + objTroca.getMotivo_registro());
        int id = 0;
        if (psTroca.executeUpdate() > 0) {
            ResultSet rs = psTroca.getGeneratedKeys(); //Aqui você vai receber as chaves criadas, no seu caso será só uma, mas se tiver mais itens auto incremento ele te retornaria.
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } //Não esqueça de implementar o resto, try, catch e fechar as conexões.  
        conTroca.getCONEXAO().close();
//------------------------------------------------------------------------------

        Conexao conDeixado = new Conexao();
        PreparedStatement psDeixado = conDeixado.getCONEXAO().prepareStatement(SQLDeixados);
        for (ItensDeixados d : objTroca.getListaDeixados()) {
            psDeixado.setInt(1, id);
            psDeixado.setInt(2, d.getId_produto());
            psDeixado.setDate(3, d.getDate_cupom());
            psDeixado.setDate(4, d.getDate_registro());
            psDeixado.setInt(5, d.getQuatidade());
            psDeixado.setDouble(6, d.getValorUnitario());
            psDeixado.setDouble(7, d.getValorTotalDeixado());
            psDeixado.executeUpdate();
        }
        conDeixado.getCONEXAO().close();
//------------------------------------------------------------------------------,

        Conexao conLevado = new Conexao();
        PreparedStatement psLevado = conLevado.getCONEXAO().prepareStatement(SQLLevados);
        for (ItensLevados l : objTroca.getListaLevados()) {
            psLevado.setInt(1, id);
            psLevado.setInt(2, l.getId_produto());
            psLevado.setDate(3, l.getDate_cupom());
            psLevado.setDate(4, l.getDate_registro());
            psLevado.setInt(5, l.getQuatidade());
            psLevado.setDouble(6, l.getValorUnitario());
            psLevado.setDouble(7, l.getValorTotalLevados());
            psLevado.executeUpdate();
        }
        conLevado.getCONEXAO().close();
    }
}
