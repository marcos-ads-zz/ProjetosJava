package modulo.dashboard;

import java.beans.PropertyVetoException;
import java.util.Locale;
import modulo.metodos.Funcao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Marcos Junior
 */
public class DadosGraficos {

    Funcao fun = new Funcao();
//***********************************************Balcão******************************************************

    public JFreeChart painelGraficoPizzaBalcao() throws PropertyVetoException {
        DefaultPieDataset pie = new DefaultPieDataset();
        pie.setValue("Marcos " + 19 + "%", 19);
        pie.setValue("James " + 20 + "%", 20);
        pie.setValue("João " + 10 + "%", 10);
        pie.setValue("Marinalva " + 9 + "%", 9);
        pie.setValue("Sheila " + 25 + "%", 25);
        pie.setValue("Vânia " + 10 + "%", 10);
        pie.setValue("Venicios " + 9 + "%", 9);
        pie.setValue("Valbevan " + 25 + "%", 25);
        JFreeChart grafico = ChartFactory.createPieChart3D("Participação na Meta da Loja",
                pie,
                false,
                true,
                Locale.ENGLISH);
        return grafico;
    }

    public JFreeChart painelGraficoPizzaBalcao2() throws PropertyVetoException {
        DefaultPieDataset pie = new DefaultPieDataset();
        pie.setValue("Marcos " + 19 + "%", 19);
        pie.setValue("James " + 20 + "%", 20);
        pie.setValue("João " + 10 + "%", 10);
        pie.setValue("Marinalva " + 9 + "%", 9);
        pie.setValue("Sheila " + 25 + "%", 25);
        pie.setValue("Vânia " + 10 + "%", 10);
        pie.setValue("Venicios " + 9 + "%", 9);
        pie.setValue("Valbevan " + 25 + "%", 25);
        JFreeChart grafico = ChartFactory.createPieChart3D("Participação na Venda da Loja",
                pie,
                false,
                true,
                Locale.ENGLISH);
        return grafico;
    }

    public JFreeChart painelGraficoBarraBalcao() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(50000, "Marcos", "Marcos");
        caty.setValue(20000, "James", "James");
        caty.setValue(10000, "João", "João");
        caty.setValue(80000, "Marinalva", "Marinalva");
        caty.setValue(70000, "Sheila", "Sheila");
        caty.setValue(10000, "Vânia", "Vânia");
        caty.setValue(80000, "Venicios", "Venicios");
        caty.setValue(70000, "Valbevan", "Valbevan");
        JFreeChart grafico = ChartFactory.createBarChart3D("Venda Por Balconista", "Colaborador",
                "Venda",
                caty,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraTKBalcao() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(65.25, "Marcos", "Marcos");
        caty.setValue(55.80, "James", "James");
        caty.setValue(86.25, "João", "João");
        caty.setValue(54.40, "Marinalva", "Marinalva");
        caty.setValue(70.40, "Sheila", "Sheila");
        caty.setValue(65.25, "Vânia", "Vânia");
        caty.setValue(55.80, "Venicios", "Venicios");
        caty.setValue(86.25, "Valbevan", "Valbevan");
        JFreeChart grafico = ChartFactory.createBarChart3D("Tiket Médio: " + "Mês de Janeiro",
                "Colaborador", "Ticket Médio",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

//***********************************************Campanhas**************************************************
    public JFreeChart painelGraficoBarraCampGnano() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(2, "Marcos", "Marcos");
        caty.setValue(4, "James", "James");
        caty.setValue(1, "João", "João");
        caty.setValue(8, "Marinalva", "Marinalva");
        caty.setValue(2, "Sheila", "Sheila");
        caty.setValue(2, "Vânia", "Vânia");
        caty.setValue(4, "Venicios", "Venicios");
        caty.setValue(1, "Valbevan", "Valbevan");
        JFreeChart grafico = ChartFactory.createBarChart3D("Venda Gnano",
                "Colaborador", "Venda",
                caty,
                PlotOrientation.VERTICAL, true, true, false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraCampPower() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(15, "Marcos", "Marcos");
        caty.setValue(35, "James", "James");
        caty.setValue(40, "João", "João");
        caty.setValue(25, "Marinalva", "Marinalva");
        caty.setValue(23, "Sheila", "Sheila");
        caty.setValue(12, "Vânia", "Vânia");
        caty.setValue(18, "Venicios", "Venicios");
        caty.setValue(38, "Valbevan", "Valbevan");
        JFreeChart grafico = ChartFactory.createBarChart3D("Venda Gnano",
                "Colaborador", "Venda",
                caty,
                PlotOrientation.VERTICAL, true, true, false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraCampGero() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(4, "Marcos", "Marcos");
        caty.setValue(8, "James", "James");
        caty.setValue(3, "João", "João");
        caty.setValue(6, "Marinalva", "Marinalva");
        caty.setValue(2, "Sheila", "Sheila");
        caty.setValue(5, "Vânia", "Vânia");
        caty.setValue(2, "Venicios", "Venicios");
        caty.setValue(7, "Valbevan", "Valbevan");
        JFreeChart grafico = ChartFactory.createBarChart3D("Venda Gnano",
                "Colaborador", "Venda",
                caty,
                PlotOrientation.VERTICAL, true, true, false);
        return grafico;
    }

    public JFreeChart painelGraficoPizzaCamp() throws PropertyVetoException {
        DefaultPieDataset pie = new DefaultPieDataset();
        pie.setValue("Marcos " + 19 + "%", 19);
        pie.setValue("James " + 20 + "%", 20);
        pie.setValue("João " + 10 + "%", 10);
        pie.setValue("Marinalva " + 9 + "%", 9);
        pie.setValue("Sheila " + 25 + "%", 25);
        pie.setValue("Vânia " + 10 + "%", 10);
        pie.setValue("Venicios " + 9 + "%", 9);
        pie.setValue("Valbevan " + 25 + "%", 25);
        JFreeChart grafico = ChartFactory.createPieChart3D("Participação na Meta da Power Vita",
                pie,
                false,
                true,
                Locale.ENGLISH);
        return grafico;
    }
//***********************************************Farmaceutico***********************************************

    public JFreeChart painelGraficoPizzaFarmaceutico() throws PropertyVetoException {
        DefaultPieDataset pie = new DefaultPieDataset();
        pie.setValue("Evilasio " + 19 + "%", 19);
        pie.setValue("Mayara " + 20 + "%", 20);
        pie.setValue("Vanessa " + 10 + "%", 10);
        pie.setValue("Romero " + 9 + "%", 9);
        JFreeChart grafico = ChartFactory.createPieChart3D("Participação na Meta da Loja",
                pie, false, true, Locale.ENGLISH);
        return grafico;
    }

    public JFreeChart painelGraficoBarraVendaFarm() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(50000, "Evilasio", "Evilasio");
        caty.setValue(20000, "Mayara", "Mayara");
        caty.setValue(10000, "Vanessa", "Vanessa");
        caty.setValue(80000, "Romero", "Romero");
        JFreeChart grafico = ChartFactory.createBarChart3D("Venda Por Farmacêutico",
                "Colaborador", "Venda", caty, PlotOrientation.VERTICAL, true, true, false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraFarmaTK() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(65.25, "Evilasio", "Evilasio");
        caty.setValue(55.80, "Mayara", "Mayara");
        caty.setValue(86.25, "Vanessa", "Vanessa");
        caty.setValue(54.40, "Romero", "Romero");
        JFreeChart grafico = ChartFactory.createBarChart3D("Tiket Médio: " + "Mês de Janeiro",
                "Colaborador", "Ticket Médio", caty, PlotOrientation.VERTICAL, true, true, false);
        return grafico;
    }

    public JFreeChart GraficoBarraClinicFarma() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(30, "Evilasio", "Evilasio");
        caty.setValue(100, "Mayara", "Mayara");
        caty.setValue(80, "Vanessa", "Vanessa");
        caty.setValue(40, "Romero", "Romero");
        JFreeChart grafico = ChartFactory.createBarChart3D("Clinic Farma", "Colaborador",
                "Cadastros", caty, PlotOrientation.VERTICAL, true, true, false);
        return grafico;
    }
//***********************************************Venda Loja*************************************************

    public JFreeChart GraficoBarraVenda() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(50000, "Janeiro", "Janeiro");
        caty.setValue(20000, "Fevereiro", "Fevereiro");
        caty.setValue(10000, "Março", "Março");
        caty.setValue(80000, "Abril", "Abril");
        caty.setValue(70000, "Maio", "Maio");
        caty.setValue(50000, "Junho", "Junho");
        caty.setValue(20000, "Julho", "Julho");
        caty.setValue(10000, "Agosto", "Agosto");
        caty.setValue(80000, "Setembro", "Setembro");
        caty.setValue(70000, "Outubro", "Outubro");
        caty.setValue(80000, "Novembro", "Novembro");
        caty.setValue(70000, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("Venda Loja",
                "Mês", "Venda", caty, PlotOrientation.VERTICAL, true, true, false);
        return grafico;
    }

//***********************************************Caixa******************************************************
    public JFreeChart painelGraficoPizzaCaixa() throws PropertyVetoException {
        DefaultPieDataset pie = new DefaultPieDataset();
        pie.setValue("Marcos " + 19 + "%", 19);
        pie.setValue("James " + 20 + "%", 20);
        pie.setValue("João " + 10 + "%", 10);
        pie.setValue("Marinalva " + 9 + "%", 9);
        pie.setValue("Sheila " + 25 + "%", 25);
        pie.setValue("Vânia " + 10 + "%", 10);
        pie.setValue("Venicios " + 9 + "%", 9);
        pie.setValue("Valbevan " + 25 + "%", 25);
        JFreeChart grafico = ChartFactory.createPieChart3D("Participação na Meta da Recarga",
                pie,
                false,
                true,
                Locale.ENGLISH);
        return grafico;
    }

    public JFreeChart painelGraficoBarraCredCaixa() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(50000, "Marcos", "Marcos");
        caty.setValue(20000, "James", "James");
        caty.setValue(10000, "João", "João");
        caty.setValue(80000, "Marinalva", "Marinalva");
        caty.setValue(70000, "Sheila", "Sheila");
        caty.setValue(10000, "Vânia", "Vânia");
        caty.setValue(80000, "Venicios", "Venicios");
        caty.setValue(70000, "Valbevan", "Valbevan");
        JFreeChart grafico = ChartFactory.createBarChart3D("Venda Por Caixa", "Colaborador",
                "Venda",
                caty,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraVendaCaixa() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(50000, "Marcos", "Marcos");
        caty.setValue(20000, "James", "James");
        caty.setValue(10000, "João", "João");
        caty.setValue(80000, "Marinalva", "Marinalva");
        caty.setValue(70000, "Sheila", "Sheila");
        caty.setValue(10000, "Vânia", "Vânia");
        caty.setValue(80000, "Venicios", "Venicios");
        caty.setValue(70000, "Valbevan", "Valbevan");
        JFreeChart grafico = ChartFactory.createBarChart3D("Venda Por Caixa", "Colaborador",
                "Venda",
                caty,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraTKCaixa() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(65.25, "Marcos", "Marcos");
        caty.setValue(55.80, "James", "James");
        caty.setValue(86.25, "João", "João");
        caty.setValue(54.40, "Marinalva", "Marinalva");
        caty.setValue(70.40, "Sheila", "Sheila");
        caty.setValue(65.25, "Vânia", "Vânia");
        caty.setValue(55.80, "Venicios", "Venicios");
        caty.setValue(86.25, "Valbevan", "Valbevan");
        JFreeChart grafico = ChartFactory.createBarChart3D("Tiket Médio: " + "Mês de Janeiro",
                "Colaborador", "Ticket Médio",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }
//***********************************************Inventario*************************************************

    public JFreeChart painelGraficoBarraInventarioIMA() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(0.15, "Janeiro", "Janeiro");
        caty.setValue(0.75, "Fevereiro", "Fevereiro");
        caty.setValue(0.55, "Março", "Março");
        caty.setValue(0.25, "Abril", "Abril");
        caty.setValue(0.35, "Maio", "Maio");
        caty.setValue(0.28, "Junho", "Junho");
        caty.setValue(0.31, "Julho", "Julho");
        caty.setValue(0.25, "Agosto", "Agosto");
        caty.setValue(0.13, "Setembro", "Setembro");
        caty.setValue(0.60, "Outubro", "Outubro");
        caty.setValue(0.70, "Novembro", "Novembro");
        caty.setValue(0.25, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("IMA",
                "Mês", "Valor %",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraInventarioTotalPro() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(1133530.15, "Janeiro", "Janeiro");
        caty.setValue(1283730.75, "Fevereiro", "Fevereiro");
        caty.setValue(1036530.15, "Março", "Março");
        caty.setValue(1003932.25, "Abril", "Abril");
        caty.setValue(1013550.35, "Maio", "Maio");
        caty.setValue(1053539.28, "Junho", "Junho");
        caty.setValue(1083531.31, "Julho", "Julho");
        caty.setValue(1092530.25, "Agosto", "Agosto");
        caty.setValue(1113030.13, "Setembro", "Setembro");
        caty.setValue(1113530.60, "Outubro", "Outubro");
        caty.setValue(1103530.70, "Novembro", "Novembro");
        caty.setValue(1125250.25, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("Valor dos Produtos Loja",
                "Mês", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraInventarioFaltas() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(5250.15, "Janeiro", "Janeiro");
        caty.setValue(2526.75, "Fevereiro", "Fevereiro");
        caty.setValue(10256.55, "Março", "Março");
        caty.setValue(7262.25, "Abril", "Abril");
        caty.setValue(12560.35, "Maio", "Maio");
        caty.setValue(8922.28, "Junho", "Junho");
        caty.setValue(2023.31, "Julho", "Julho");
        caty.setValue(4163.25, "Agosto", "Agosto");
        caty.setValue(2026.13, "Setembro", "Setembro");
        caty.setValue(9260.60, "Outubro", "Outubro");
        caty.setValue(7526.70, "Novembro", "Novembro");
        caty.setValue(4623.25, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("Total de Faltas",
                "Mês", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraInventarioSobra() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(1250.15, "Janeiro", "Janeiro");
        caty.setValue(4022.75, "Fevereiro", "Fevereiro");
        caty.setValue(5226.55, "Março", "Março");
        caty.setValue(6226.25, "Abril", "Abril");
        caty.setValue(2633.35, "Maio", "Maio");
        caty.setValue(1289.28, "Junho", "Junho");
        caty.setValue(8263.31, "Julho", "Julho");
        caty.setValue(4669.25, "Agosto", "Agosto");
        caty.setValue(3695.13, "Setembro", "Setembro");
        caty.setValue(9686.60, "Outubro", "Outubro");
        caty.setValue(8669.70, "Novembro", "Novembro");
        caty.setValue(2636.25, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("Total de Sobras",
                "Mês", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

//***********************************************Contas da Loja**********************************************
    public JFreeChart painelGraficoBarraEnergValor() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(8450, "Janeiro", "Janeiro");
        caty.setValue(7851, "Fevereiro", "Fevereiro");
        caty.setValue(9354, "Março", "Março");
        caty.setValue(7253, "Abril", "Abril");
        caty.setValue(8251, "Maio", "Maio");
        caty.setValue(8255, "Junho", "Junho");
        caty.setValue(9854, "Julho", "Julho");
        caty.setValue(10191, "Agosto", "Agosto");
        caty.setValue(8275, "Setembro", "Setembro");
        caty.setValue(7129, "Outubro", "Outubro");
        caty.setValue(6295, "Novembro", "Novembro");
        caty.setValue(9255, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("Valor do Consumo de Energia KWh",
                "Mês", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraEnergConsumo() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(10450, "Janeiro", "Janeiro");
        caty.setValue(12851, "Fevereiro", "Fevereiro");
        caty.setValue(13354, "Março", "Março");
        caty.setValue(18253, "Abril", "Abril");
        caty.setValue(16251, "Maio", "Maio");
        caty.setValue(15255, "Junho", "Junho");
        caty.setValue(14854, "Julho", "Julho");
        caty.setValue(15991, "Agosto", "Agosto");
        caty.setValue(13275, "Setembro", "Setembro");
        caty.setValue(14129, "Outubro", "Outubro");
        caty.setValue(11295, "Novembro", "Novembro");
        caty.setValue(12255, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("Consumo de Energia KWh",
                "Mês", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraAguaValor() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(50.56, "Janeiro", "Janeiro");
        caty.setValue(71.40, "Fevereiro", "Fevereiro");
        caty.setValue(54.56, "Março", "Março");
        caty.setValue(83.85, "Abril", "Abril");
        caty.setValue(61.12, "Maio", "Maio");
        caty.setValue(65.56, "Junho", "Junho");
        caty.setValue(74.95, "Julho", "Julho");
        caty.setValue(91.35, "Agosto", "Agosto");
        caty.setValue(75.56, "Setembro", "Setembro");
        caty.setValue(89.95, "Outubro", "Outubro");
        caty.setValue(95.78, "Novembro", "Novembro");
        caty.setValue(73.10, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("Valor da Conta de Água",
                "Mês", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    public JFreeChart painelGraficoBarraAguaConsumo() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(14, "Janeiro", "Janeiro");
        caty.setValue(18, "Fevereiro", "Fevereiro");
        caty.setValue(13, "Março", "Março");
        caty.setValue(16, "Abril", "Abril");
        caty.setValue(12, "Maio", "Maio");
        caty.setValue(14, "Junho", "Junho");
        caty.setValue(18, "Julho", "Julho");
        caty.setValue(19, "Agosto", "Agosto");
        caty.setValue(12, "Setembro", "Setembro");
        caty.setValue(11, "Outubro", "Outubro");
        caty.setValue(17, "Novembro", "Novembro");
        caty.setValue(10, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("Consumo de Água",
                "Mês", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    //***********************************************Indisponíveis*******************************************
    public JFreeChart painelGraficoBarraIndispiniveisValor() throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        caty.setValue(1450.56, "Janeiro", "Janeiro");
        caty.setValue(2851.40, "Fevereiro", "Fevereiro");
        caty.setValue(3354.56, "Março", "Março");
        caty.setValue(8253.85, "Abril", "Abril");
        caty.setValue(6251.12, "Maio", "Maio");
        caty.setValue(5255.56, "Junho", "Junho");
        caty.setValue(4854.95, "Julho", "Julho");
        caty.setValue(5991.35, "Agosto", "Agosto");
        caty.setValue(3275.56, "Setembro", "Setembro");
        caty.setValue(4129.95, "Outubro", "Outubro");
        caty.setValue(1295.78, "Novembro", "Novembro");
        caty.setValue(2255.10, "Dezembro", "Dezembro");
        JFreeChart grafico = ChartFactory.createBarChart3D("Indisponíve",
                "Mês", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return grafico;
    }

    //***********************************************Ruptura******************************************************
    
}
