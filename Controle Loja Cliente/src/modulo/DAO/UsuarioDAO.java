package modulo.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modulo.entidades.Usuario;

/**
 *
 * @author Marcos Junior
 */
public class UsuarioDAO {

    Conexao con;

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
            objFun.setEmail(rs.getString("email"));
            objFun.setDatnasc(rs.getDate("date_nas"));
            objFun.setTelefone(rs.getString("telefone"));
            objFun.setFuncao(rs.getString("funcao"));
            objFun.setSenha(rs.getString("senha"));
        }
        con.getCONEXAO().close();
        return objFun;
    }

    public List<Usuario> ListaFuncionario(String funcao) throws Exception {
        con = new Conexao();
        Usuario objFun;
        List<Usuario> funcionarios = new ArrayList<>();
        if (funcao.equals("TODOS")) {
            String SQL = "select * from relatorios.relatorio.usuario;";
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objFun = new Usuario();
                objFun.setId(rs.getInt("idusuario"));
                objFun.setMatricula(rs.getInt("matricula"));
                objFun.setNome(rs.getString("nome"));
                funcionarios.add(objFun);
            }
        } else {
            String SQL = "select * from relatorios.relatorio.usuario where funcao = ?";
            PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
            ps.setString(1, funcao);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objFun = new Usuario();
                objFun.setId(rs.getInt("idusuario"));
                objFun.setMatricula(rs.getInt("matricula"));
                objFun.setNome(rs.getString("nome"));
                funcionarios.add(objFun);
            }
        }

        con.getCONEXAO().close();
        return funcionarios;

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
}
