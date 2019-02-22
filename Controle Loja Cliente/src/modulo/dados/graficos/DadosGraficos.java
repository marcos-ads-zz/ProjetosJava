/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.dados.graficos;

import java.beans.PropertyVetoException;
import java.util.List;
import modulo.DAO.FaltasDAO;
import modulo.DAO.UsuarioDAO;
import modulo.camapanha.DAO.CampanhaDAO;
import modulo.camapanha.DAO.GnanoDAO;
import modulo.camapanha.DAO.PowerDAO;
import modulo.entidades.Campanha;
import modulo.entidades.Usuario;
import modulo.metodos.Funcao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Marcos Junior
 */
public class DadosGraficos {

    Funcao fun = new Funcao();

    public JFreeChart painelGraficoRuptura(String ano) throws PropertyVetoException, Exception {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        FaltasDAO qtd = new FaltasDAO();
        int anoC = fun.convertToInt2(ano);
        int jan = qtd.TabelaPesquisaRows("01/01/" + ano, fun.getMaxDias(0, anoC) + "/01/" + ano);
        int fev = qtd.TabelaPesquisaRows("01/02/" + ano, fun.getMaxDias(1, anoC) + "/02/" + ano);
        int mar = qtd.TabelaPesquisaRows("01/03/" + ano, fun.getMaxDias(2, anoC) + "/03/" + ano);
        int abr = qtd.TabelaPesquisaRows("01/04/" + ano, fun.getMaxDias(3, anoC) + "/04/" + ano);
        int may = qtd.TabelaPesquisaRows("01/05/" + ano, fun.getMaxDias(4, anoC) + "/05/" + ano);
        int jun = qtd.TabelaPesquisaRows("01/06/" + ano, fun.getMaxDias(5, anoC) + "/06/" + ano);
        int jul = qtd.TabelaPesquisaRows("01/07/" + ano, fun.getMaxDias(6, anoC) + "/07/" + ano);
        int ago = qtd.TabelaPesquisaRows("01/08/" + ano, fun.getMaxDias(7, anoC) + "/08/" + ano);
        int set = qtd.TabelaPesquisaRows("01/09/" + ano, fun.getMaxDias(8, anoC) + "/09/" + ano);
        int out = qtd.TabelaPesquisaRows("01/10/" + ano, fun.getMaxDias(9, anoC) + "/10/" + ano);
        int nov = qtd.TabelaPesquisaRows("01/11/" + ano, fun.getMaxDias(10, anoC) + "/11/" + ano);
        int dez = qtd.TabelaPesquisaRows("01/12/" + ano, fun.getMaxDias(11, anoC) + "/12/" + ano);

        caty.setValue(jan, "Janeiro", "Jan (" + jan + ")");
        caty.setValue(fev, "Fevereiro", "Fev (" + fev + ")");
        caty.setValue(mar, "Março", "Mar (" + mar + ")");
        caty.setValue(abr, "Abril", "Abr (" + abr + ")");
        caty.setValue(may, "Maio", "Mai (" + may + ")");
        caty.setValue(jun, "Junho", "Jun (" + jun + ")");
        caty.setValue(jul, "Julho", "Jul (" + jul + ")");
        caty.setValue(ago, "Agosto", "Ago (" + ago + ")");
        caty.setValue(set, "Setembro", "Set (" + set + ")");
        caty.setValue(out, "Outubro", "Out (" + out + ")");
        caty.setValue(nov, "Novembro", "Nov (" + nov + ")");
        caty.setValue(dez, "Dezembro", "Dez (" + dez + ")");

        JFreeChart grafico = ChartFactory.createBarChart3D("Acompanhamento de Faltas do Ano de " + ano,
                "Mês", "Quantidade de Faltas",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        return grafico;
    }

    public JFreeChart painelGraficoBarraGnanoBalcao(String campanha, String cargo) throws PropertyVetoException, Exception {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        GnanoDAO g = new GnanoDAO();
        UsuarioDAO u = new UsuarioDAO();
        List<Usuario> funcionarios;
        //Lista de Balconistas
        funcionarios = u.ListaFuncionario(cargo);
        //Usuários + Quantidades
        //Associa quantidade com os Balconistas
        for (Usuario a : funcionarios) {
            caty.setValue(
                    g.TabelaPesquisaGnanoQtd(a.getMatricula(),
                            campanha,
                            fun.primeiroDiaMesAtual(),
                            fun.ultimoDiaMesAtual()),
                    String.valueOf(a.getMatricula()),
                    String.valueOf(a.getMatricula()) + " (" + g.TabelaPesquisaGnanoQtd(a.getMatricula(),
                    campanha,
                    fun.primeiroDiaMesAtual(),
                    fun.ultimoDiaMesAtual()) + ")"
            );
        }
        //Exibe
        JFreeChart grafico = ChartFactory.createStackedBarChart3D("Acompanhamento Venda de Gnano: " + fun.atualDate(),
                "Colaborador", "Gnano",
                caty, PlotOrientation.HORIZONTAL,
                true,
                true,
                true);
        return grafico;
    }

    public JFreeChart painelGraficoBarraPowerBalcao(String campanha, String cargo) throws PropertyVetoException, Exception {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        PowerDAO g = new PowerDAO();
        UsuarioDAO u = new UsuarioDAO();
        List<Usuario> funcionarios;
        //Lista de Balconistas
        funcionarios = u.ListaFuncionario(cargo);
        //Usuários + Quantidades
        //Associa quantidade com os Balconistas
        for (Usuario a : funcionarios) {
            caty.setValue(
                    g.TabelaPesquisaPowerQtd(a.getMatricula(),
                            campanha,
                            fun.primeiroDiaMesAtual(),
                            fun.ultimoDiaMesAtual()),
                    String.valueOf(a.getMatricula()),
                    String.valueOf(a.getMatricula()) + " (" + g.TabelaPesquisaPowerQtd(a.getMatricula(),
                    campanha,
                    fun.primeiroDiaMesAtual(),
                    fun.ultimoDiaMesAtual()) + ")"
            );
        }
        //Exibe
        JFreeChart grafico = ChartFactory.createBarChart3D("Acompanhamento Venda de Power Vita: " + fun.atualDate(),
                "Colaborador", "Power Vita",
                caty, PlotOrientation.HORIZONTAL,
                true,
                true,
                true);
        return grafico;
    }

    public JFreeChart painelGraficoBarraCampanhaBalcao(String campanha, String cargo) throws PropertyVetoException, Exception {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        CampanhaDAO g = new CampanhaDAO();
        UsuarioDAO u = new UsuarioDAO();
        List<Usuario> funcionarios;
        List<Campanha> camp;
        String tipo = null;
        //Lista de Balconistas
        funcionarios = u.ListaFuncionario(cargo);
        camp = g.TabelaPesquisaCampanhas(fun.primeiroDiaMesAtual(), fun.ultimoDiaMesAtual());
        //Usuários + Quantidades
        //Associa quantidade com os Balconistas
        if ("TODAS".equals(campanha)) {
            for (Campanha descr : camp) {
                if (!"POWER VITA".equals(descr.getDesc_campanha()) & !"GNANO".equals(descr.getDesc_campanha())) {
                    caty.setValue(
                            g.TabelaPesquisaCampanhasQtdS(descr.getDesc_campanha(),
                                    fun.primeiroDiaMesAtual(),
                                    fun.ultimoDiaMesAtual()),
                            descr.getDesc_campanha(),
                            descr.getDesc_campanha() + " (" + g.TabelaPesquisaCampanhasQtdS(
                            descr.getDesc_campanha(),
                            fun.primeiroDiaMesAtual(),
                            fun.ultimoDiaMesAtual()) + ")"
                    );
                }
                tipo = "Camapanhas Ativas";
            }
        } else {
            for (Usuario user : funcionarios) {
                caty.setValue(
                        g.TabelaPesquisaCampanhasQtd(user.getMatricula(),
                                campanha,
                                fun.primeiroDiaMesAtual(),
                                fun.ultimoDiaMesAtual()),
                        String.valueOf(user.getMatricula()),
                        String.valueOf(user.getMatricula()) + " (" + g.TabelaPesquisaCampanhasQtd(user.getMatricula(),
                        campanha,
                        fun.primeiroDiaMesAtual(),
                        fun.ultimoDiaMesAtual()) + ")"
                );
            }
            tipo = "Colaborador";
        }
        //Exibe
        JFreeChart grafico = ChartFactory.createStackedBarChart3D("Acompanhamento Campanhas: " + campanha + " " + fun.atualDate(),
                tipo, "Campanhas Geral",
                caty, PlotOrientation.HORIZONTAL,
                true,
                true,
                true);
        return grafico;
    }
}
