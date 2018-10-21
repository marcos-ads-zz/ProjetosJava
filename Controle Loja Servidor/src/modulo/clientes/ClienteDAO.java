package modulo.clientes;

import ConexaoData.Conexao;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class ClienteDAO {

    Conexao con;

    public int Insert(Cliente objCli) throws Exception {
        int id = 0;
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.cliente(nome_cli, end_cli, telefone_cli, date_registro) values (?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, objCli.getNome_cli().toUpperCase());
        ps.setString(2, objCli.getEnd_cli().toUpperCase());
        ps.setString(3, objCli.getTelefone_cli());
        ps.setDate(4, objCli.getData_registro());
        if (ps.executeUpdate() > 0) {
            ResultSet rs = ps.getGeneratedKeys(); //Aqui você vai receber as chaves criadas, no seu caso será só uma, mas se tiver mais itens auto incremento ele te retornaria.
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } //Não esqueça de implementar o resto, try, catch e fechar as conexões.  
        con.getCONEXAO().close();
        return id;
    }

    public boolean Update(Cliente objCli) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.cliente set nome_cli = ?, end_cli = ?, telefone_cli = ? WHERE id_cli = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);

        ps.setString(1, objCli.getNome_cli().toUpperCase());
        ps.setString(2, objCli.getEnd_cli().toUpperCase());
        ps.setString(3, objCli.getTelefone_cli());
        ps.setInt(4, objCli.getId());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.cliente where id_cli = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<Cliente> TabelaPesquisa() throws Exception {
        con = new Conexao();
        Cliente objCli = null;
        List<Cliente> cliente = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.cliente order by 3";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCli = new Cliente();
            objCli.setId(rs.getInt("id_cli"));
            objCli.setNome_cli(rs.getString("nome_cli"));
            objCli.setEnd_cli(rs.getString("end_cli"));
            objCli.setTelefone_cli(rs.getString("telefone_cli"));
            objCli.setData_registro(rs.getDate("date_registro"));
            cliente.add(objCli);
        }
        con.getCONEXAO().close();
        return cliente;

    }

    public List<Cliente> PesquisaNome(String nome) throws Exception {
        con = new Conexao();
        Cliente objCli = null;
        List<Cliente> cliente = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.cliente where nome_cli like ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, "%" + nome.toUpperCase() + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCli = new Cliente();
            objCli.setId(rs.getInt("id_cli"));
            objCli.setNome_cli(rs.getString("nome_cli"));
            objCli.setEnd_cli(rs.getString("end_cli"));
            objCli.setTelefone_cli(rs.getString("telefone_cli"));
            objCli.setData_registro(rs.getDate("date_registro"));
            cliente.add(objCli);
        }
        con.getCONEXAO().close();
        return cliente;

    }

    public boolean CheckSelect(String nome) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.cliente where nome_cli like ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, "%" + nome + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }

}
