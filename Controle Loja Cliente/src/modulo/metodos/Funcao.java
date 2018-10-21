package modulo.metodos;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Junior
 */
public class Funcao {

    public String atualAnoDate() {
        Date data = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy");
        String dataFormatada = formato.format(data);
        return dataFormatada;
    }

    public String atualDate() {
        Date data = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formato.format(data);
        return dataFormatada;
    }

    public java.sql.Date atualDateSQL() {
        Date data = new Date();
        Date dataUtil = data;
        java.sql.Date dataSql = null;
        try {
            dataUtil = new java.sql.Date(dataUtil.getTime());
            dataSql = (java.sql.Date) dataUtil;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converte data para sql: " + e.getMessage());
        }
        return dataSql;
    }

    public String horaAtualDate() {
        Date data = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        String dataFormatada = formato.format(data);
        return dataFormatada;
    }

    public String convertDoubleToString(double valorDouble) {
        Double d = valorDouble;
        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(d);

        return valorString;
    }

    public int convertToInt(String stringInt) {
        if (stringInt == null || stringInt.equals("")) {
            stringInt = "0";
        }
        String d = stringInt;
        return Integer.parseInt(d);
    }

    public int convertToInt2(String stringInt) {
        String d = stringInt;
        return Integer.parseInt(d);
    }

    public Double convertToDouble(String string) {
        if (string == null || string.equals("")) {
            string = "0";
        }
        String x = string;
        x = x.replaceAll("R|\\$|\\.", "").replaceAll("\\.", ".");
        x = x.replace(',', '.');
        double und = Double.parseDouble(x);
        return und;
    }

    public String getMaxDias(int month, int year) {
        Calendar cal = (Calendar) Calendar.getInstance().clone();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        String valor = Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return valor;
    }

    private boolean verificaDoubleSeEValor(String string) {
        boolean retorno = false;
        String precoProduto = string;
        Pattern pattern = Pattern.compile(",\\d{2}$");
        Matcher matcher = pattern.matcher(precoProduto);
        if (matcher.find()) {
            retorno = precoProduto.isEmpty() != true;
        }
        return retorno;
    }

    public java.sql.Date converteDateStringEmSQL(String dateString) throws ParseException {
        String dataString = dateString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date dataSql = new java.sql.Date(sdf.parse(dataString).getTime());
        return dataSql;
    }

    public String converteDataSQLemDateString(java.sql.Date dateSQLstring) throws Exception {
        if (dateSQLstring == null) {
            return null;
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(dateSQLstring.getTime()));
    }

    public java.sql.Date dataIniciosDias(int dias) {
        Date dataFinal = new Date();
        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(dataFinal);
        int numeroDiasParaSubtrair = -dias;
        calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
        Date dataInicial = calendarData.getTime();
        Date dataUtil = dataInicial;
        java.sql.Date dataSql = null;
        try {
            dataUtil = new java.sql.Date(dataUtil.getTime());
            dataSql = (java.sql.Date) dataUtil;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converte data para sql: " + e.getMessage());
        }
        return dataSql;
    }

    public java.sql.Date dataAtualDias() {
        Date dataFinal = new Date();
        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(dataFinal);
        int numeroDiasParaSubtrair = -0;
        calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
        Date dataInicial = calendarData.getTime();
        Date dataUtil = dataInicial;
        java.sql.Date dataSql = null;
        try {
            dataUtil = new java.sql.Date(dataUtil.getTime());
            dataSql = (java.sql.Date) dataUtil;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converte data para sql: " + e.getMessage());
        }
        return dataSql;
    }

    public Double convertStringEmDouble(String valor) {
        String x = valor;
        x = x.replaceAll("R|\\$|\\.", "").replaceAll("\\.", ".");
        x = x.replace(',', '.');
        double und = Double.parseDouble(x);
        return und;
    }

    public boolean testaNumerosInteiros(String letras) {
        boolean teste = false;
        for (int i = 0; i < letras.length(); i++) {
            if (Character.isDigit(letras.charAt(i)) == true) {
                teste = true;
            } else {
                teste = false;
                break;
            }
        }
        return teste;
    }

    public java.sql.Date converteDateEmSql(Date date) {
        Date data = date;
        Date dataUtil = data;
        java.sql.Date dataSql = null;
        try {
            dataUtil = new java.sql.Date(dataUtil.getTime());
            dataSql = (java.sql.Date) dataUtil;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converte data para sql: " + e.getMessage());
        }
        return dataSql;
    }

    public boolean testaNumerosDecimais(String letras) {
        boolean teste = false;
        String replaceAll = letras.replaceAll(",", "");
        for (int i = 0; i < replaceAll.length(); i++) {
            if (Character.isDigit(replaceAll.charAt(i)) == true) {
                teste = true;
            } else {
                teste = false;
                break;
            }
        }
        return teste;
    }

    public java.sql.Date convertDateStringToDateSQL(String dateString) throws ParseException {
        String dataString = dateString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date dataSql = new java.sql.Date(sdf.parse(dataString).getTime());
        return dataSql;
    }
}
