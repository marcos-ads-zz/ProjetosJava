package modulo.campanhas.acompanhamento;

import modulo.campanhas.relatorio.relatorioCampDAO;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulo.contas.Agua;
import modulo.dashboardD.DadosGraficos;
import modulo.metodos.Funcao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Marcos Junior
 */
public class graficosCampanha {

    Funcao fun = new Funcao();
    relatorioCampDAO DAO = new relatorioCampDAO();

    public JFreeChart painelGraficoCampanhas(int matricula, String campanha, String ano, int u) throws PropertyVetoException, Exception {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        double result;
        String mesAbrev[] = {"", "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
        String mesComp[] = {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        if (u == 1) {
            for (int i = 1; i <= 12; i++) {
                result = DAO.TabelaPesquisaRowsQtd(matricula, campanha, fun.convertDateStringToDateSQL("01/" + i + "/" + ano));
                caty.setValue(result, mesComp[i], mesAbrev[i] + " (" + result + ")");
            }
        } else if (u == 0) {
            for (int i = 1; i <= 12; i++) {
                result = DAO.TabelaPesquisaRowsValor(matricula, campanha, fun.convertDateStringToDateSQL("01/" + i + "/" + ano));
                caty.setValue(result, mesComp[i], "(" + result + ")");
            }
        }

        JFreeChart grafico = ChartFactory.createBarChart3D("Acompanhamento de Campanhas do Ano de " + ano,
                "Mês", "Quantidade de Produtos",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        return grafico;
    }

    public JFreeChart painelGraficoBarraAguaConsumo(List<Agua> agua, String ano) throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        agua.forEach((result) -> {
            try {
                caty.setValue(result.getConsumo(), fun.convertDataSQLToDateString(result.getDate_vencimento()), "(" + fun.toString(result.getConsumo()) + ")");
            } catch (Exception ex) {
                Logger.getLogger(DadosGraficos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFreeChart grafico = ChartFactory.createBarChart3D("Consumo de Água em (m³) (" + ano + ")",
                "Data de Vencimento", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        return grafico;
    }
}
