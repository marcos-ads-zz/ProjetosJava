/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.metodos;

import java.beans.PropertyVetoException;
import modulo.DAO.FaltasDAO;
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
}
