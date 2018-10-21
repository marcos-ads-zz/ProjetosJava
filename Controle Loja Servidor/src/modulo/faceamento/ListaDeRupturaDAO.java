package modulo.faceamento;

import ConexaoData.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import modulo.metodos.Funcao;

/**
 *
 * @author Marcos Junior
 */
public class ListaDeRupturaDAO {

    //Conexao con;
    public boolean Insert(ListaDeRuptura objFun) throws Exception {

        Conexao con = new Conexao();
        String SQL = "insert into relatorios.relatorio.listaruptura("
                + "cod_inter, "
                + "cod_ean, "
                + "descricao, "
                + "qtd_perdida, "
                + "lancado, "
                + "matricula, "
                + "obs, "
                + "date_cad) "
                + "values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);

        ps.setInt(1, objFun.getCod_interno());
        ps.setString(2, objFun.getCod_produto());
        ps.setString(3, objFun.getDescricao().toUpperCase());
        ps.setInt(4, objFun.getQtd_perdida());
        ps.setInt(5, objFun.getLancado());
        ps.setInt(6, objFun.getMatricula());
        ps.setString(7, objFun.getObservacao().toUpperCase());
        ps.setDate(8, objFun.getData());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public ListaDeRuptura SelectMatricula(int matricula) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;

        String SQL = "select * from relatorios.relatorio.listaruptura where matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);

        ps.setInt(1, matricula);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new ListaDeRuptura();
            objFun.setId(rs.getInt("idlistaruptura"));
            objFun.setData(rs.getDate("date_cad"));
            objFun.setCod_interno(rs.getInt("cod_inter"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setQtd_perdida(rs.getInt("qtd_perdida"));
            objFun.setLancado(rs.getInt("lancado"));
            objFun.setObservacao(rs.getString("obs"));
        }
        con.getCONEXAO().close();
        return objFun;
    }

    public boolean Update(ListaDeRuptura objFun) throws Exception {
        Conexao con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.listaruptura set "
                + "cod_inter = ?, "
                + "cod_ean = ?, "
                + "descricao = ?, "
                + "qtd_perdida = ?, "
                + "lancado = ?, "
                + "matricula = ?, "
                + "obs = ?, "
                + "date_cad = ? "
                + "WHERE idlistaruptura = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);

        ps.setInt(1, objFun.getCod_interno());
        ps.setString(2, objFun.getCod_produto());
        ps.setString(3, objFun.getDescricao().toUpperCase());
        ps.setInt(4, objFun.getQtd_perdida());
        ps.setInt(5, objFun.getLancado());
        ps.setInt(6, objFun.getMatricula());
        ps.setString(7, objFun.getObservacao().toUpperCase());
        ps.setDate(8, objFun.getData());
        ps.setInt(9, objFun.getId());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        Conexao con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.listaruptura where idlistaruptura = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<ListaDeRuptura> SelectDataInicioEFim(Date dataFinal, Date dataInical) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;
        List<ListaDeRuptura> listaRuptura = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.listaruptura where date_cad between ? and ? order by 9";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataFinal);
        ps.setDate(2, dataInical);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new ListaDeRuptura();
            objFun.setId(rs.getInt("idlistaruptura"));
            objFun.setData(rs.getDate("date_cad"));
            objFun.setCod_interno(rs.getInt("cod_inter"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setQtd_perdida(rs.getInt("qtd_perdida"));
            objFun.setLancado(rs.getInt("lancado"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setObservacao(rs.getString("obs"));
            listaRuptura.add(objFun);
        }
        con.getCONEXAO().close();
        return listaRuptura;
    }

    public Object SelectQuantidadeRegistrosMatricula(int matricula) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;
        Object fim = null;
        String SQL = "SELECT COUNT(DISTINCT(listaruptura.idlistaruptura)) as total FROM relatorios.relatorio.listaruptura where matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, matricula);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData metaRS = rs.getMetaData();
        int columnCount = metaRS.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                final Object value = rs.getObject(i);
                fim = value;
            }
        }
        con.getCONEXAO().close();
        return fim;
    }

    public List<Object> SelectOrdenaCodigo() throws Exception {
        int cont = 0;
        Conexao con = new Conexao();
        List<Object> lista = new ArrayList<>();
        String SQL = "SELECT sum(listaruptura.qtd_perdida) as teste, listaruptura.cod_inter\n"
                + "FROM relatorios.relatorio.listaruptura \n"
                + "group by listaruptura.cod_inter \n"
                + "order by 1 desc;";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData metaRS = rs.getMetaData();
        int columnCount = metaRS.getColumnCount();
        while (rs.next()) {
            cont++;
            if (cont < 16) {
                for (int i = 2; i <= columnCount; i++) {
                    final Object value = rs.getObject(i);
                    lista.add(value);
                }
            }
        }
        con.getCONEXAO().close();
        return lista;
    }

    public List<Object> SelectOrdenaQuantidade(int cod) throws Exception {
        Conexao con = new Conexao();
        Object fim = null;
        List<Object> lista = new ArrayList<>();
        String SQL = "SELECT listaruptura.cod_inter, sum(listaruptura.qtd_perdida)\n"
                + "FROM relatorios.relatorio.listaruptura "
                + "WHERE listaruptura.cod_inter = ?\n"
                + "group by listaruptura.cod_inter \n"
                + "order by 1 desc;";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, cod);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData metaRS = rs.getMetaData();
        int columnCount = metaRS.getColumnCount();
        while (rs.next()) {
            for (int i = 2; i <= columnCount; i++) {
                final Object value = rs.getObject(i);
                lista.add(value);
            }
        }
        con.getCONEXAO().close();
        return lista;
    }

    public int SelectRegistros2(int matricula) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;
        int cont = 0;
        String SQL = "SELECT * FROM relatorios.relatorio.listaruptura where matricula = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, matricula);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            cont++;
        }
        con.getCONEXAO().close();
        return cont;
    }

    public List<ListaDeRuptura> SelectMatriculaDataInicioEFim(int matricula, Date dataFinal, Date dataInical) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objRup = null;
        List<ListaDeRuptura> listaRuptura = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.listaruptura where date_cad between ? and ? and matricula = ? order by 9";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataInical);
        ps.setDate(2, dataFinal);
        ps.setInt(3, matricula);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objRup = new ListaDeRuptura();
            objRup.setId(rs.getInt("idlistaruptura"));
            objRup.setData(rs.getDate("date_cad"));
            objRup.setCod_interno(rs.getInt("cod_inter"));
            objRup.setCod_produto(rs.getString("cod_ean"));
            objRup.setDescricao(rs.getString("descricao"));
            objRup.setQtd_perdida(rs.getInt("qtd_perdida"));
            objRup.setLancado(rs.getInt("lancado"));
            objRup.setMatricula(rs.getInt("matricula"));
            objRup.setObservacao(rs.getString("obs"));
            listaRuptura.add(objRup);
        }
        con.getCONEXAO().close();
        return listaRuptura;
    }

    public List<ListaDeRuptura> TabelaPesquisa() throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;
        List<ListaDeRuptura> listaRuptura = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.listaruptura order by 9";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new ListaDeRuptura();
            objFun.setId(rs.getInt("idlistaruptura"));
            objFun.setData(rs.getDate("date_cad"));
            objFun.setCod_interno(rs.getInt("cod_inter"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setQtd_perdida(rs.getInt("qtd_perdida"));
            objFun.setLancado(rs.getInt("lancado"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setObservacao(rs.getString("obs"));

            listaRuptura.add(objFun);
        }
        con.getCONEXAO().close();
        return listaRuptura;

    }

    public List<ListaDeRuptura> TabelaPesquisaMatricula(int matricula, Date Ultimos30, Date DataInicial) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;
        List<ListaDeRuptura> listaRuptura = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.listaruptura where date_cad between ? and ? and matricula = ? order by 9";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, Ultimos30);
        ps.setDate(2, DataInicial);
        ps.setInt(3, matricula);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new ListaDeRuptura();
            objFun.setId(rs.getInt("idlistaruptura"));
            objFun.setData(rs.getDate("date_cad"));
            objFun.setCod_interno(rs.getInt("cod_inter"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setQtd_perdida(rs.getInt("qtd_perdida"));
            objFun.setLancado(rs.getInt("lancado"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setObservacao(rs.getString("obs"));

            listaRuptura.add(objFun);
        }
        con.getCONEXAO().close();
        return listaRuptura;

    }

    public List<ListaDeRuptura> TabelaPesquisaCodInterno(int cod_interno, Date Ultimos30, Date DataInicial) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;
        List<ListaDeRuptura> listaRuptura = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.listaruptura where date_cad between ? and ? and cod_inter = ? order by 9";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, Ultimos30);
        ps.setDate(2, DataInicial);
        ps.setInt(3, cod_interno);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new ListaDeRuptura();
            objFun.setId(rs.getInt("idlistaruptura"));
            objFun.setData(rs.getDate("date_cad"));
            objFun.setCod_interno(rs.getInt("cod_inter"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setQtd_perdida(rs.getInt("qtd_perdida"));
            objFun.setLancado(rs.getInt("lancado"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setObservacao(rs.getString("obs"));

            listaRuptura.add(objFun);
        }
        con.getCONEXAO().close();
        return listaRuptura;

    }

    public List<ListaDeRuptura> TabelaPesquisaDescricao(String descricao, Date Ultimos30, Date DataInicial) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;
        List<ListaDeRuptura> listaRuptura = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.listaruptura where date_cad between ? and ? and descricao like ? order by 9";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, Ultimos30);
        ps.setDate(2, DataInicial);
        ps.setString(3, descricao);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new ListaDeRuptura();
            objFun.setId(rs.getInt("idlistaruptura"));
            objFun.setData(rs.getDate("date_cad"));
            objFun.setCod_interno(rs.getInt("cod_inter"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setQtd_perdida(rs.getInt("qtd_perdida"));
            objFun.setLancado(rs.getInt("lancado"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setObservacao(rs.getString("obs"));

            listaRuptura.add(objFun);
        }
        con.getCONEXAO().close();
        return listaRuptura;

    }

    public List<ListaDeRuptura> TabelaPesquisaObservacao(String observ, Date Ultimos30, Date DataInicial) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;
        List<ListaDeRuptura> listaRuptura = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.listaruptura where date_cad between ? and ? and obs = ? order by 9";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, Ultimos30);
        ps.setDate(2, DataInicial);
        ps.setString(3, observ);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new ListaDeRuptura();
            objFun.setId(rs.getInt("idlistaruptura"));
            objFun.setData(rs.getDate("date_cad"));
            objFun.setCod_interno(rs.getInt("cod_inter"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setQtd_perdida(rs.getInt("qtd_perdida"));
            objFun.setLancado(rs.getInt("lancado"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setObservacao(rs.getString("obs"));

            listaRuptura.add(objFun);
        }
        con.getCONEXAO().close();
        return listaRuptura;

    }

    public List<ListaDeRuptura> TabelaPesquisaData(Date dataInicio, Date dataFinal) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun;
        List<ListaDeRuptura> listaRuptura = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.listaruptura where date_cad between ? and ? order by 9";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataInicio);
        ps.setDate(2, dataFinal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new ListaDeRuptura();
            objFun.setId(rs.getInt("idlistaruptura"));
            objFun.setData(rs.getDate("date_cad"));
            objFun.setCod_interno(rs.getInt("cod_inter"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setQtd_perdida(rs.getInt("qtd_perdida"));
            objFun.setLancado(rs.getInt("lancado"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setObservacao(rs.getString("obs"));
            listaRuptura.add(objFun);
        }
        con.getCONEXAO().close();
        return listaRuptura;

    }

    public List<ListaDeRuptura> TabelaPesquisadaPorID(int id) throws Exception {
        Conexao con = new Conexao();
        ListaDeRuptura objFun = null;
        List<ListaDeRuptura> listaRuptura = new ArrayList<>();

        String SQL = "select * from relatorios.relatorio.listaruptura where idlistaruptura = ? order by 4";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objFun = new ListaDeRuptura();
            objFun.setData(rs.getDate("date_cad"));
            objFun.setCod_interno(rs.getInt("cod_inter"));
            objFun.setCod_produto(rs.getString("cod_ean"));
            objFun.setDescricao(rs.getString("descricao"));
            objFun.setQtd_perdida(rs.getInt("qtd_perdida"));
            objFun.setLancado(rs.getInt("lancado"));
            objFun.setMatricula(rs.getInt("matricula"));
            objFun.setObservacao(rs.getString("obs"));
            listaRuptura.add(objFun);
        }
        con.getCONEXAO().close();
        return listaRuptura;
    }

    public int TabelaPesquisaRows(String dataInicio, String dataFinal) throws Exception {
        Conexao con = new Conexao();
        Funcao fun = new Funcao();
        int total = 0;
        String SQL = "SELECT count(*) as qtd FROM relatorio.listaruptura WHERE date_cad between ? and ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, fun.convertDateStringToDateSQL(dataInicio));
        ps.setDate(2, fun.convertDateStringToDateSQL(dataFinal));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            total = rs.getInt("qtd");
        }
        con.getCONEXAO().close();
        return total;
    }

    public boolean CheckSelect(int cod_interno, Date Ultimos30, Date DataInicial) throws Exception {
        Conexao con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.listaruptura where date_cad between ? and ? and cod_inter = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, Ultimos30);
        ps.setDate(2, DataInicial);
        ps.setInt(3, cod_interno);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }
}
