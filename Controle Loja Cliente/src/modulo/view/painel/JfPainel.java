package modulo.view.painel;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modulo.metodos.DadosGraficos;
import org.jfree.chart.ChartPanel;
import javax.swing.ImageIcon;

/**
 *
 * @author Marcos Junior
 */
public class JfPainel extends javax.swing.JFrame {

    private DadosGraficos dg;
    private int nume = 4;

    public JfPainel() {
        initComponents();
        dg = new DadosGraficos();
        try {
            painelGraficoRuptura();
        } catch (Exception ex) {
            Logger.getLogger(JfPainel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MouseClik(int num) throws PropertyVetoException, Exception {

        if (num != nume) {
            //Pimba
            nume = num;

            switch (num) {
                case 0:
                    //Painel Vendas

                    break;
                case 1:
                    //Painel Power

                    break;
                case 2:
                    //Painel Faltas
                    painelGraficoRuptura();
                    break;
                case 3:
                    //Painel Desconto Vip

                    break;
                case 4:
                    //Painel Campanhas

                    break;

                default:
                    break;
            }
        } else {
            //Nada a fazer
        }
    }

    public void CarregaGraf2() {

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    painelGraficoRuptura();
                    jlErros.setText("");
                } catch (Exception ex) {
                    jlErros.setText(ex.getMessage());

                }
            }
        };
        t.start();
    }

    public String CarregaAnoSelecionado() {
        int ano = jYearChooser1.getYear();
        String anoVigente = Integer.toString(ano);
        return anoVigente;
    }

    public void painelGraficoRuptura() throws PropertyVetoException, Exception {
        String anoVigente = CarregaAnoSelecionado();
        jPanelGraficoFaltas.removeAll();
        jPanelGraficoFaltas.add(new ChartPanel(dg.painelGraficoRuptura(anoVigente)), BorderLayout.CENTER);
        jPanelGraficoFaltas.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtPainelDeMenus = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanelGraficoFaltas = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jBAtualizar = new javax.swing.JButton();
        jlErros = new javax.swing.JLabel();
        jPanel4Vip = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setIconImage(new ImageIcon(getClass().getResource("/icons/icone_sistema.png")).getImage());

        jtPainelDeMenus.setBackground(new java.awt.Color(204, 255, 204));
        jtPainelDeMenus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPainelDeMenusMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafico de Vendas"));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 742, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtPainelDeMenus.addTab("Venda", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafico Power Vita"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 742, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtPainelDeMenus.addTab("Power", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 0, 255));

        jPanelGraficoFaltas.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoFaltas.setLayout(new java.awt.BorderLayout());

        jLabel33.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel33.setText("SELECIONE O ANO VIGENTE");

        jBAtualizar.setBackground(new java.awt.Color(255, 0, 51));
        jBAtualizar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBAtualizar.setText("ATUALIZA");
        jBAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarActionPerformed(evt);
            }
        });

        jlErros.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraficoFaltas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlErros, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlErros))
                    .addComponent(jBAtualizar))
                .addGap(0, 0, 0)
                .addComponent(jPanelGraficoFaltas, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );

        jtPainelDeMenus.addTab("Faltas", jPanel3);

        jPanel4Vip.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel4VipLayout = new javax.swing.GroupLayout(jPanel4Vip);
        jPanel4Vip.setLayout(jPanel4VipLayout);
        jPanel4VipLayout.setHorizontalGroup(
            jPanel4VipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        jPanel4VipLayout.setVerticalGroup(
            jPanel4VipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        jtPainelDeMenus.addTab("Desc Vip", jPanel4Vip);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtPainelDeMenus)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtPainelDeMenus, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarActionPerformed
        CarregaGraf2();
    }//GEN-LAST:event_jBAtualizarActionPerformed

    private void jtPainelDeMenusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPainelDeMenusMouseClicked
        int num = jtPainelDeMenus.getSelectedIndex();
        try {
            MouseClik(num);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(this, "Erro MouseClik 1:" + num + ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro MouseClik 2: " + num + ex);
        }
    }//GEN-LAST:event_jtPainelDeMenusMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAtualizar;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4Vip;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelGraficoFaltas;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JLabel jlErros;
    private javax.swing.JTabbedPane jtPainelDeMenus;
    // End of variables declaration//GEN-END:variables
}
