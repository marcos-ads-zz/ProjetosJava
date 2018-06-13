package modulo.loja;

import ConexaoData.Conexao;
import ConexaoData.DadosDataBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Marcos Junior
 */
public class LojaDAO {

    Conexao con;
    DadosDataBase dados = new DadosDataBase();

    public boolean Insert(Loja objFun) throws Exception {
        con = new Conexao();
        String SQL = "insert into " + dados.getBD() + "." + dados.getSCHE() + ".loja"
                + "(nome_loja, numero_loja, num_ponto, is_estadual, telefone, cnpj, cep, estado, cidade, bairro, logradouro) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objFun.getNome_loja().toUpperCase());
        ps.setInt(2, objFun.getNumero_filial());
        ps.setInt(3, objFun.getNum_ponto());
        ps.setString(4, objFun.getIs_estadual());
        ps.setString(5, objFun.getTelefone());
        ps.setString(6, objFun.getCnpj());
        ps.setString(7, objFun.getCep());
        ps.setString(8, objFun.getEstado().toUpperCase());
        ps.setString(9, objFun.getCidade().toUpperCase());
        ps.setString(10, objFun.getBairro().toUpperCase());
        ps.setString(11, objFun.getLogradouro().toUpperCase());

        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public Loja PesquisaIdLoja() throws Exception {
        con = new Conexao();
        Loja objFun = null;
        String SQL = "select * from " + dados.getBD() + "." + dados.getSCHE() + ".loja";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Loja();
            objFun.setId(rs.getInt("idloja"));
            objFun.setNome_loja(rs.getString("nome_loja"));
            objFun.setNumero_filial(rs.getInt("numero_loja"));
            objFun.setNum_ponto(rs.getInt("num_ponto"));
            objFun.setIs_estadual(rs.getString("is_estadual"));
            objFun.setTelefone(rs.getString("telefone"));
            objFun.setCnpj(rs.getString("cnpj"));
            objFun.setCep(rs.getString("cep"));
            objFun.setEstado(rs.getString("estado"));
            objFun.setBairro(rs.getString("bairro"));
            objFun.setCidade(rs.getString("cidade"));
            objFun.setLogradouro(rs.getString("logradouro"));
        }
        con.getCONEXAO().close();
        return objFun;
    }

    public boolean Update(Loja objFun) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE " + dados.getBD() + "." + dados.getSCHE() + ".loja "
                + "set nome_loja = ?, numero_loja = ?, num_ponto = ?, is_estadual = ?, telefone = ?, cnpj = ?, cep = ?, estado = ?, bairro = ?, "
                + "logradouro = ?, cidade = ? "
                + "WHERE idloja = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objFun.getNome_loja().toUpperCase());
        ps.setInt(2, objFun.getNumero_filial());
        ps.setInt(3, objFun.getNum_ponto());
        ps.setString(4, objFun.getIs_estadual());
        ps.setString(5, objFun.getTelefone());
        ps.setString(6, objFun.getCnpj());
        ps.setString(7, objFun.getCep());
        ps.setString(8, objFun.getEstado().toUpperCase());
        ps.setString(9, objFun.getBairro().toUpperCase());
        ps.setString(10, objFun.getLogradouro().toUpperCase());
        ps.setString(11, objFun.getCidade().toUpperCase());
        ps.setInt(12, objFun.getId());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean CheckSelect(int id) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from " + dados.getBD() + "." + dados.getSCHE() + ".loja where idloja = ?";
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
