package modulo.dashboard;

import java.beans.PropertyVetoException;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulo.campanhas.venda.CadastroCampanhaDia;
import modulo.campanhas.venda.CadastroCampanhaDiaDAO;
import modulo.contas.Agua;
import modulo.contas.Energia;
import modulo.faceamento.ListaDeRupturaDAO;
import modulo.inventario.Inventario;
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

    public JFreeChart painelGraficoBarraInventarioIMA(List<Inventario> inventario, String ano) throws PropertyVetoException, Exception {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        inventario.forEach((result) -> {
            caty.setValue(result.getValor_ima(), result.getData_inventario(), "(" + result.getValor_ima() + ")");
        });
        JFreeChart grafico = ChartFactory.createStackedBarChart3D("IMA (" + ano + ")",
                "Data dos Inventários", "Valor %",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        return grafico;
    }

    public JFreeChart painelGraficoBarraInventarioTotalPro(List<Inventario> inventario, String ano) throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();

        inventario.forEach((result) -> {
            caty.setValue(result.getTotal_produtos(), result.getData_inventario(), "(" + fun.convertDoubleToStringMoeda(result.getTotal_produtos()) + ")");
        });

        JFreeChart grafico = ChartFactory.createStackedBarChart3D("Valor dos Produtos Loja (" + ano + ")",
                "Mês", "Valor dos Inventarios Realizados nas Datas Abaixo",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        return grafico;
    }

    public JFreeChart painelGraficoBarraInventarioFaltas(List<Inventario> inventario, String ano) throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        inventario.forEach((result) -> {
            caty.setValue(result.getValor_custo_falta(), result.getData_inventario(), "(" + fun.convertDoubleToStringMoeda(result.getValor_custo_falta()) + ")");
        });
        JFreeChart grafico = ChartFactory.createStackedBarChart3D("Total de Faltas Custo (" + ano + ")",
                "Mês", "Valor dos Inventarios Realizados nas Datas Abaixo",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        return grafico;
    }

    public JFreeChart painelGraficoBarraInventarioSobra(List<Inventario> inventario, String ano) throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        inventario.forEach((result) -> {
            caty.setValue(result.getValor_custo_sobra(), result.getData_inventario(), "(" + fun.convertDoubleToStringMoeda(result.getValor_custo_sobra()) + ")");
        });
        JFreeChart grafico = ChartFactory.createStackedBarChart3D("Total de Sobras Custo (" + ano + ")",
                "Mês", "Valor dos Inventarios Realizados nas Datas Abaixo",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        return grafico;
    }

//***********************************************Contas da Loja**********************************************
    public JFreeChart painelGraficoBarraEnergValor(List<Energia> energia, String ano) throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        energia.forEach((result) -> {
            try {
                caty.setValue(result.getValor_conta(), fun.convertDataSQLToDateString(result.getDate_vencimento()), "(" + fun.convertDoubleToStringMoeda(result.getValor_conta()) + ")");
            } catch (Exception ex) {
                Logger.getLogger(DadosGraficos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JFreeChart grafico = ChartFactory.createBarChart3D("Valor do Consumo de Energia em (R$) (" + ano + ")",
                "Data de Vencimento", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        return grafico;
    }

    public JFreeChart painelGraficoBarraEnergConsumo(List<Energia> energia, String ano) throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        energia.forEach((result) -> {
            try {
                caty.setValue(result.getConsumo(), fun.convertDataSQLToDateString(result.getDate_vencimento()), "(" + fun.toString(result.getConsumo()) + ")");
            } catch (Exception ex) {
                Logger.getLogger(DadosGraficos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFreeChart grafico = ChartFactory.createBarChart3D("Consumo de Energia em (KWh) (" + ano + ")",
                "Data de Vencimento", "Valor",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        return grafico;
    }

    public JFreeChart painelGraficoBarraAguaValor(List<Agua> agua, String ano) throws PropertyVetoException {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        agua.forEach((result) -> {
            try {
                caty.setValue(result.getValor_conta(), fun.convertDataSQLToDateString(result.getDate_vencimento()), "(" + fun.convertDoubleToStringMoeda(result.getValor_conta()) + ")");
            } catch (Exception ex) {
                Logger.getLogger(DadosGraficos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFreeChart grafico = ChartFactory.createBarChart3D("Valor da Conta de Água em (R$) (" + ano + ")",
                "Data de Vencimento", "Valor",
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
    public JFreeChart painelGraficoRuptura(String ano) throws PropertyVetoException, Exception {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        ListaDeRupturaDAO qtd = new ListaDeRupturaDAO();
        int anoC = fun.convertToInt(ano);
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

    public JFreeChart painelGraficoCampanha(Date dataInicio, Date dataFim, String campanha) throws PropertyVetoException, Exception {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        CadastroCampanhaDiaDAO DAO = new CadastroCampanhaDiaDAO();
        List<CadastroCampanhaDia> camp;
        camp = DAO.PesquisaNomeGraficoCampanha(dataInicio, dataFim, campanha);
        camp.forEach((result) -> {
            try {
                caty.setValue(result.getQuantidade(), dataFim, "(" + result.getQuantidade() + ")");
            } catch (Exception ex) {
                Logger.getLogger(DadosGraficos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFreeChart grafico = ChartFactory.createLineChart3D("Acompanhamento de Campanhas",
                campanha, "Quantidade",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        System.out.println("Positivo");

        return grafico;
    }

    public JFreeChart painelGraficoCampanhaQT(Date dataInicio, Date dataFim, String campanha) throws PropertyVetoException, Exception {
        DefaultCategoryDataset caty = new DefaultCategoryDataset();
        CadastroCampanhaDiaDAO DAO = new CadastroCampanhaDiaDAO();
        List<CadastroCampanhaDia> camp;
        camp = DAO.PesquisaNomeGraficoCampanha(dataInicio, dataFim, campanha);
        //int campQT = DAO.PesquisaNomeGraficoCampanhaQT(dataFim, campanha);
        camp.forEach((result) -> {
            try {
                caty.setValue(DAO.PesquisaNomeGraficoCampanhaQT(dataFim, campanha), dataFim, "(" + DAO.PesquisaNomeGraficoCampanhaQT(dataFim, campanha) + ")");
            } catch (Exception ex) {
                Logger.getLogger(DadosGraficos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JFreeChart grafico = ChartFactory.createLineChart3D("Acompanhamento de Campanhas",
                campanha, "Quantidade",
                caty, PlotOrientation.VERTICAL,
                true,
                true,
                true);
        System.out.println("Positivo");

        return grafico;
    }
}
