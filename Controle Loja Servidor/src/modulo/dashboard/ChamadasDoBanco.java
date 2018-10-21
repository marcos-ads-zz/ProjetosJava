package modulo.dashboard;

import modulo.metas.MetasDAO;

/**
 *
 * @author Marcos Junior
 */
public class ChamadasDoBanco {

    private MetasDAO DAOMETA;

    public double ConsultaMeta() throws Exception {
        DAOMETA = new MetasDAO();
        return DAOMETA.PesquisarPorMetaPorMes("março", 2018).getValor_meta();
    }
}
