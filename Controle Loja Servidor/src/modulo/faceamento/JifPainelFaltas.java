package modulo.faceamento;

import modulo.produtos.ProdutoDAO;
import modulo.usuarios.UsuarioDAO;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.login.UserLogado;
import modulo.produtos.Produto;
import modulo.usuarios.Usuario;
import modulo.metodos.Funcao;
import modulo.versao.Versao;

/**
 *
 * @author Marcos Junior
 */
public final class JifPainelFaltas extends javax.swing.JInternalFrame {

    private Versao ver = new Versao();
    private UserLogado login = new UserLogado();
    private DateFormat formatoHora;
    private String tipoUser;
    private ListaDeRupturaDAO DAOLISTA;
    private ListaDeRuptura objLista;
    private UsuarioDAO DAOUser;
    private Usuario objUser;
    private ProdutoDAO DAOPro;
    private Produto objPro;
    private Funcao fun;
    private String Aviso = "Atenção";

    public JifPainelFaltas() {
        initComponents();
        DAOLISTA = new ListaDeRupturaDAO();
        DAOUser = new UsuarioDAO();
        fun = new Funcao();
        DAOPro = new ProdutoDAO();
        setTitle(ver.getNomesys() + " " + ver.getVersao());
        this.formatoHora = new SimpleDateFormat("EEEEEEEEEEEEEE, dd/MM/yyyy    HH:mm:ss");
        MostraPainel();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void MostraPainel() {
        jPanel2.setVisible(true);
        Thread Tabela1Thred = new Thread(new JifPainelFaltas.clsTabela1());
        Tabela1Thred.start();
    }

    public void FechaPainel() {
        jPanel2.setVisible(false);
        Thread Tabela1Thred = new Thread(new JifPainelFaltas.clsTabela1());
        Tabela1Thred.stop();
    }

    public void TabelaRegistrosDoDia() {
        DefaultTableModel modelo = (DefaultTableModel) jtTabData0.getModel();
        modelo.setNumRows(0);
        try {
            for (ListaDeRuptura p : DAOLISTA.SelectDataInicioEFim(fun.atualDateSQL(), fun.atualDateSQL())) {
                modelo.addRow(new Object[]{
                    p.getCod_interno(), p.getDescricao(), p.getQtd_perdida(), p.getMatricula()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa! " + ex.getMessage());
        }
    }

    public void TabelaTop15MaisSolicitados() {
        DefaultTableModel modelo = (DefaultTableModel) jtTabData1.getModel();
        modelo.setNumRows(0);
        int cont = 0;
        try {
            for (Object p : DAOLISTA.SelectOrdenaCodigo()) {
                cont++;
                modelo.addRow(new Object[]{
                    cont, p, DAOPro.PesquisarPorCodInterno(Integer.parseInt(p.toString())).getDescricao(),
                    DAOLISTA.SelectOrdenaQuantidade(Integer.parseInt(p.toString()))
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa! " + ex.getMessage());
        }
    }

    public void TabelaRegistrosPorMatricula() {
        DefaultTableModel modelo = (DefaultTableModel) jtTabData2.getModel();
        modelo.setNumRows(0);
        try {
            for (Usuario p : DAOUser.TabelaPesquisa()) {
                modelo.addRow(new Object[]{
                    p.getMatricula(), p.getNome(),
                    DAOLISTA.SelectQuantidadeRegistrosMatricula(p.getMatricula())
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa! " + ex.getMessage());
        }
    }

    class clsTabela1 implements Runnable {

        @Override
        public void run() {
            while (true) {
                TabelaRegistrosDoDia();
                TabelaRegistrosPorMatricula();
                TabelaTop15MaisSolicitados();
                try {
                    Thread.sleep(1200000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabData0 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTabData1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtTabData2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));

        jtTabData0.setAutoCreateRowSorter(true);
        jtTabData0.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jtTabData0.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtTabData0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Descrição", "Qtd", "Matrícula"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtTabData0);
        if (jtTabData0.getColumnModel().getColumnCount() > 0) {
            jtTabData0.getColumnModel().getColumn(0).setMaxWidth(65);
            jtTabData0.getColumnModel().getColumn(2).setMaxWidth(45);
            jtTabData0.getColumnModel().getColumn(3).setMaxWidth(65);
        }

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Produtos Cadastrados Hoje!");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Top 15 Mais Solicitados!");

        jtTabData1.setAutoCreateRowSorter(true);
        jtTabData1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jtTabData1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtTabData1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Top", "Cod", "Descrição", "Qtd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtTabData1);
        if (jtTabData1.getColumnModel().getColumnCount() > 0) {
            jtTabData1.getColumnModel().getColumn(0).setMaxWidth(45);
            jtTabData1.getColumnModel().getColumn(1).setMaxWidth(60);
            jtTabData1.getColumnModel().getColumn(3).setMaxWidth(45);
        }

        jtTabData2.setAutoCreateRowSorter(true);
        jtTabData2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jtTabData2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtTabData2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matrícula", "Nome", "Qtd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jtTabData2);
        if (jtTabData2.getColumnModel().getColumnCount() > 0) {
            jtTabData2.getColumnModel().getColumn(0).setMaxWidth(65);
            jtTabData2.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Registros Por Usuário!");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Painel de Indicadores da Lista de Ruptura");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtTabData0;
    private javax.swing.JTable jtTabData1;
    private javax.swing.JTable jtTabData2;
    // End of variables declaration//GEN-END:variables
}
