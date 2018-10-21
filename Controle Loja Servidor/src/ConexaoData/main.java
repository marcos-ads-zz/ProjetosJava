package ConexaoData;

import java.io.IOException;
import modulo.configuracoes.Config;
import modulo.login.JfLoginUi;

/**
 *
 * @author Marcos Junior
 */
public class main {

    private static Config propr;

    public static void main(String[] args) throws IOException, Exception {
        propr.CarregaDados();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfLoginUi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JfLoginUi().setVisible(true);
        });
    }
}
