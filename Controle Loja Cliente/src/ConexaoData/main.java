package ConexaoData;

import java.io.IOException;
import modulo.configuracoes.Config;
import modulo.view.principal.JfPrincipal;

/**
 *
 * @author Marcos Junior
 */
public class main {

    private static Config propr;

    public static void main(String[] args) throws IOException {
        propr.CarregaDados();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JfPrincipal().setVisible(true);
        });
    }
}
