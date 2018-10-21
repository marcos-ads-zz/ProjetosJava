package modulo.usuarios;

import ConexaoData.Conexao;
import modulo.metodos.ConvertMD5;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class UsuarioDAO {

    private Conexao con;
    private ConvertMD5 md5 = new ConvertMD5();

    public boolean Insert(Usuario objFun) throws Exception {

        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.usuario(matricula, nome, email, date_nas, telefone, funcao, senha) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objFun.getMatricula());
        ps.setString(2, objFun.getNome().toUpperCase());
        ps.setString(3, objFun.getEmail().toUpperCase());
        ps.setDate(4, objFun.getDatnasc());
        ps.setString(5, objFun.getTelefone());
        ps.setString(6, objFun.getFuncao().toUpperCase());
        ps.setString(7, md5.MD5String(objFun.getSenha()));
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public Usuario PesquisaPorMatricula(int matricula) throws Exception {
        con = new Conexao();
        Usuario objFun = null;

        String SQL = "select * from relatorios.relatorio.usuario where matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);

        ps.setInt(1, matricula);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Usuario();
            objFun.setId(rs.getInt("idusuario"));
            objFun.setNome(rs.getString("nome"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setEmail(rs.getString("email"));
            objFun.setDatnasc(rs.getDate("date_nas"));
            objFun.setTelefone(rs.getString("telefone"));
            objFun.setFuncao(rs.getString("funcao"));
            objFun.setSenha(rs.getString("senha"));
        }
        con.getCONEXAO().close();
        return objFun;
    }

    public boolean Update(Usuario objFun) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.usuario set nome = ?, email = ?, date_nas = ?, telefone = ?, funcao = ? WHERE matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objFun.getNome().toUpperCase());
        ps.setString(2, objFun.getEmail().toUpperCase());
        ps.setDate(3, objFun.getDatnasc());
        ps.setString(4, objFun.getTelefone());
        ps.setString(5, objFun.getFuncao().toUpperCase());
        ps.setInt(6, objFun.getMatricula());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean UpdateAtualizacao(Usuario objFun) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.usuario set funcao = ? WHERE matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(5, objFun.getFuncao().toUpperCase());
        ps.setInt(6, objFun.getMatricula());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean UpdateSenha(Usuario objFun) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.usuario set senha = ? WHERE matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, md5.MD5String(objFun.getSenha()));
        ps.setInt(2, objFun.getMatricula());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int matricula) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.usuario where matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, matricula);
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<Usuario> TabelaPesquisa() throws Exception {
        con = new Conexao();
        Usuario objFun = null;
        List<Usuario> funcionarios = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.usuario order by 3";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Usuario();
            objFun.setId(rs.getInt("idusuario"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setNome(rs.getString("nome"));
            objFun.setEmail(rs.getString("email"));
            objFun.setDatnasc(rs.getDate("date_nas"));
            objFun.setTelefone(rs.getString("telefone"));
            objFun.setFuncao(rs.getString("funcao"));
            objFun.setSenha(rs.getString("senha"));
            funcionarios.add(objFun);
        }
        con.getCONEXAO().close();
        return funcionarios;

    }

    public List<Usuario> TabelaPesquisa2(int matricula) throws Exception {
        con = new Conexao();
        Usuario objFun = null;
        List<Usuario> user = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.usuario where matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, matricula);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Usuario();
            objFun.setId(rs.getInt("idusuario"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setNome(rs.getString("nome"));
            objFun.setEmail(rs.getString("email"));
            objFun.setDatnasc(rs.getDate("date_nas"));
            objFun.setTelefone(rs.getString("telefone"));
            objFun.setFuncao(rs.getString("funcao"));
            objFun.setSenha(rs.getString("senha"));
            user.add(objFun);
        }
        con.getCONEXAO().close();
        return user;

    }

    public Usuario PesquisaID(int id) throws Exception {
        con = new Conexao();
        Usuario objFun = null;
        String SQL = "select * from relatorios.relatorio.usuario where idusuario = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Usuario();
            objFun.setMatricula(rs.getInt("matricula"));
        }
        con.getCONEXAO().close();
        return objFun;
    }

    public boolean CheckSelect(int matricula) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.usuario where matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, matricula);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }

    public Usuario PesquisaMatriculaR(int matricula) throws Exception {
        con = new Conexao();
        Usuario objFun = null;

        String SQL = "select * from relatorios.relatorio.usuario where matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);

        ps.setInt(1, matricula);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new Usuario();
            objFun.setId(rs.getInt("idusuario"));
            objFun.setNome(rs.getString("nome")); 
        }
        con.getCONEXAO().close();
        return objFun;
    }
}
