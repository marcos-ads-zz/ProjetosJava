
package modulo.relatorio;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class dataFrames {

    static Date dataFim;
    static Date dataInicio;

    public static Date getDataFim() {
        return dataFim;
    }

    public static void setDataFim(Date dataFim) {
        dataFrames.dataFim = dataFim;
    }

    public static Date getDataInicio() {
        return dataInicio;
    }

    public static void setDataInicio(Date dataInicio) {
        dataFrames.dataInicio = dataInicio;
    }
    

}
