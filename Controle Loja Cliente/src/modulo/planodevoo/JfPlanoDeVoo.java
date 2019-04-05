package modulo.planodevoo;

/**
 *
 * @author Marcos Junior
 */
public class JfPlanoDeVoo extends javax.swing.JFrame {

    /** Creates new form JfPlanoDeVoo */
    public JfPlanoDeVoo() {
        initComponents();
    }

      @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3PlanoDeVoo = new javax.swing.JPanel();
        jtNomeUsuarioV = new javax.swing.JTextField();
        jtMatriculaVoo = new javax.swing.JTextField();
        jlMat26 = new javax.swing.JLabel();
        jbSalvarVoo = new javax.swing.JButton();
        jbCancelarVoo = new javax.swing.JButton();
        jtLoja5 = new javax.swing.JTextField();
        jlMat28 = new javax.swing.JLabel();
        jlMat29 = new javax.swing.JLabel();
        jfVooVenda1 = new javax.swing.JFormattedTextField();
        jfVooVenda2 = new javax.swing.JFormattedTextField();
        jfVooVenda4 = new javax.swing.JFormattedTextField();
        jfVooVenda3 = new javax.swing.JFormattedTextField();
        jfVooCli1 = new javax.swing.JFormattedTextField();
        jfVooCli2 = new javax.swing.JFormattedTextField();
        jfVooCli3 = new javax.swing.JFormattedTextField();
        jfVooCli4 = new javax.swing.JFormattedTextField();
        jfVooVendaTotal = new javax.swing.JFormattedTextField();
        jfVooCliTotal = new javax.swing.JFormattedTextField();
        jtDataDoRegistroVoo = new javax.swing.JFormattedTextField();
        jbSalvarVoo1 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jlMat30 = new javax.swing.JLabel();
        jfVooVenda5 = new javax.swing.JFormattedTextField();
        jlTituloPlanoDeVoo = new javax.swing.JLabel();
        jlMat31 = new javax.swing.JLabel();
        jfVooVenda6 = new javax.swing.JFormattedTextField();
        jlMat32 = new javax.swing.JLabel();
        jfVooCli5 = new javax.swing.JFormattedTextField();
        jlTituloPlanoDeVoo1 = new javax.swing.JLabel();
        jlTituloPlanoDeVoo2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Gráfico", jPanel1);

        jPanel3PlanoDeVoo.setBackground(new java.awt.Color(0, 153, 153));

        jtNomeUsuarioV.setEditable(false);
        jtNomeUsuarioV.setBackground(new java.awt.Color(102, 255, 153));
        jtNomeUsuarioV.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtNomeUsuarioV.setForeground(new java.awt.Color(255, 0, 0));
        jtNomeUsuarioV.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jtMatriculaVoo.setBackground(new java.awt.Color(255, 255, 102));
        jtMatriculaVoo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtMatriculaVoo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatriculaVoo.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtMatriculaVoo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaVooMouseClicked(evt);
            }
        });
        jtMatriculaVoo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMatriculaVooKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtMatriculaVooKeyReleased(evt);
            }
        });

        jlMat26.setBackground(new java.awt.Color(153, 255, 0));
        jlMat26.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat26.setForeground(new java.awt.Color(0, 0, 153));
        jlMat26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat26.setText("Matrícula");

        jbSalvarVoo.setBackground(new java.awt.Color(0, 0, 102));
        jbSalvarVoo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbSalvarVoo.setForeground(new java.awt.Color(255, 255, 255));
        jbSalvarVoo.setText("Pesquisar");
        jbSalvarVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarVooActionPerformed(evt);
            }
        });
        jbSalvarVoo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbSalvarVooKeyPressed(evt);
            }
        });

        jbCancelarVoo.setBackground(new java.awt.Color(204, 0, 0));
        jbCancelarVoo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbCancelarVoo.setForeground(new java.awt.Color(255, 255, 255));
        jbCancelarVoo.setText("Limpar");
        jbCancelarVoo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelarVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarVooActionPerformed(evt);
            }
        });

        jtLoja5.setEditable(false);
        jtLoja5.setBackground(new java.awt.Color(255, 153, 102));
        jtLoja5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtLoja5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLoja5.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jlMat28.setBackground(new java.awt.Color(153, 255, 0));
        jlMat28.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat28.setForeground(new java.awt.Color(0, 0, 153));
        jlMat28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat28.setText("Venda");

        jlMat29.setBackground(new java.awt.Color(153, 255, 0));
        jlMat29.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat29.setForeground(new java.awt.Color(0, 0, 153));
        jlMat29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat29.setText("Cliente");

        jfVooVenda1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda1.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda1.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda1ActionPerformed(evt);
            }
        });

        jfVooVenda2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda2.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda2.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda2.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda2ActionPerformed(evt);
            }
        });

        jfVooVenda4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda4.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda4.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda4.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda4ActionPerformed(evt);
            }
        });

        jfVooVenda3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda3.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda3.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda3.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda3ActionPerformed(evt);
            }
        });

        jfVooCli1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli1.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli1.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli1ActionPerformed(evt);
            }
        });

        jfVooCli2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli2.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli2.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli2.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli2ActionPerformed(evt);
            }
        });

        jfVooCli3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli3.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli3.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli3.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli3ActionPerformed(evt);
            }
        });

        jfVooCli4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli4.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli4.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli4.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli4ActionPerformed(evt);
            }
        });

        jfVooVendaTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVendaTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVendaTotal.setEnabled(false);
        jfVooVendaTotal.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVendaTotal.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfVooVendaTotal.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVendaTotal.setPreferredSize(new java.awt.Dimension(12, 26));

        jfVooCliTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCliTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCliTotal.setEnabled(false);
        jfVooCliTotal.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCliTotal.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfVooCliTotal.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCliTotal.setPreferredSize(new java.awt.Dimension(12, 26));

        try {
            jtDataDoRegistroVoo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataDoRegistroVoo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataDoRegistroVoo.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtDataDoRegistroVoo.setMinimumSize(new java.awt.Dimension(12, 26));
        jtDataDoRegistroVoo.setPreferredSize(new java.awt.Dimension(127, 26));
        jtDataDoRegistroVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDataDoRegistroVooActionPerformed(evt);
            }
        });

        jbSalvarVoo1.setBackground(new java.awt.Color(0, 0, 102));
        jbSalvarVoo1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbSalvarVoo1.setForeground(new java.awt.Color(255, 255, 255));
        jbSalvarVoo1.setText("Alterar");
        jbSalvarVoo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarVoo1ActionPerformed(evt);
            }
        });
        jbSalvarVoo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbSalvarVoo1KeyPressed(evt);
            }
        });

        jlMat30.setBackground(new java.awt.Color(153, 255, 0));
        jlMat30.setFont(new java.awt.Font("Consolas", 3, 24)); // NOI18N
        jlMat30.setForeground(new java.awt.Color(0, 0, 153));
        jlMat30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMat30.setText("TKM Atual");

        jfVooVenda5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda5.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda5.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jfVooVenda5.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda5.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda5ActionPerformed(evt);
            }
        });

        jlTituloPlanoDeVoo.setBackground(new java.awt.Color(51, 255, 51));
        jlTituloPlanoDeVoo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTituloPlanoDeVoo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloPlanoDeVoo.setText("Plano de Voo Individual");

        jlMat31.setBackground(new java.awt.Color(153, 255, 0));
        jlMat31.setFont(new java.awt.Font("Consolas", 3, 24)); // NOI18N
        jlMat31.setForeground(new java.awt.Color(0, 0, 153));
        jlMat31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMat31.setText("Acumulado Venda");

        jfVooVenda6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda6.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda6.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jfVooVenda6.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda6.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda6ActionPerformed(evt);
            }
        });

        jlMat32.setBackground(new java.awt.Color(153, 255, 0));
        jlMat32.setFont(new java.awt.Font("Consolas", 3, 24)); // NOI18N
        jlMat32.setForeground(new java.awt.Color(0, 0, 153));
        jlMat32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMat32.setText("Acumulado Clientes");

        jfVooCli5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli5.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli5.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jfVooCli5.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli5.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli5ActionPerformed(evt);
            }
        });

        jlTituloPlanoDeVoo1.setBackground(new java.awt.Color(51, 255, 51));
        jlTituloPlanoDeVoo1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTituloPlanoDeVoo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloPlanoDeVoo1.setText("Gráfico Anual Individual");

        jlTituloPlanoDeVoo2.setBackground(new java.awt.Color(51, 255, 51));
        jlTituloPlanoDeVoo2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTituloPlanoDeVoo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloPlanoDeVoo2.setText("Acumulado");

        javax.swing.GroupLayout jPanel3PlanoDeVooLayout = new javax.swing.GroupLayout(jPanel3PlanoDeVoo);
        jPanel3PlanoDeVoo.setLayout(jPanel3PlanoDeVooLayout);
        jPanel3PlanoDeVooLayout.setHorizontalGroup(
            jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlTituloPlanoDeVoo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlTituloPlanoDeVoo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jfVooCli5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jfVooVenda5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jfVooVenda6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlMat30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlMat32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlMat31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jlTituloPlanoDeVoo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(jlMat26, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtMatriculaVoo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNomeUsuarioV, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSalvarVoo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSalvarVoo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCancelarVoo)
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jtLoja5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtDataDoRegistroVoo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlMat28, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMat29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jfVooVenda1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jfVooCli1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfVooCli2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVooVenda2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfVooCli3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVooVenda3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfVooVenda4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVooCli4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfVooCliTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVooVendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3PlanoDeVooLayout.setVerticalGroup(
            jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTituloPlanoDeVoo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtNomeUsuarioV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtMatriculaVoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlMat26, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbSalvarVoo)
                        .addComponent(jbSalvarVoo1)
                        .addComponent(jbCancelarVoo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jfVooVenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMat28, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jfVooCli1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMat29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addComponent(jfVooVenda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jfVooCli2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addComponent(jfVooVenda3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jfVooCli3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jfVooVenda4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVooVendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jfVooCli4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfVooCliTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlTituloPlanoDeVoo2)
                .addGap(27, 27, 27)
                .addComponent(jlMat31, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jfVooVenda6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jlMat32, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jfVooCli5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jlMat30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jfVooVenda5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlTituloPlanoDeVoo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtLoja5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDataDoRegistroVoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3PlanoDeVoo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3PlanoDeVoo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Plano de Voo Individual", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtMatriculaVooMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaVooMouseClicked
        jtMatriculaVoo.setText("");
        jtNomeUsuarioV.setText("");
    }//GEN-LAST:event_jtMatriculaVooMouseClicked

    private void jtMatriculaVooKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaVooKeyPressed
        verificaOpcaoSelecionada(evt, "matriVoo");
    }//GEN-LAST:event_jtMatriculaVooKeyPressed

    private void jtMatriculaVooKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaVooKeyReleased
        pesquisarUsuarioNoBanco();
    }//GEN-LAST:event_jtMatriculaVooKeyReleased

    private void jbSalvarVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarVooActionPerformed
        try {
            salvarPlanoDeVoo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha: " + ex);
        }
    }//GEN-LAST:event_jbSalvarVooActionPerformed

    private void jbSalvarVooKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbSalvarVooKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                salvarPlanoDeVoo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Falha: " + ex);
            }
        }
    }//GEN-LAST:event_jbSalvarVooKeyPressed

    private void jbCancelarVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarVooActionPerformed
        limparCampos();
    }//GEN-LAST:event_jbCancelarVooActionPerformed

    private void jfVooVenda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda1ActionPerformed
        jfVooCli1.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooVenda1ActionPerformed

    private void jfVooVenda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda2ActionPerformed
        jfVooCli2.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooVenda2ActionPerformed

    private void jfVooVenda4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda4ActionPerformed
        jfVooCli4.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooVenda4ActionPerformed

    private void jfVooVenda3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda3ActionPerformed
        jfVooCli3.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooVenda3ActionPerformed

    private void jfVooCli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli1ActionPerformed
        jfVooVenda2.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooCli1ActionPerformed

    private void jfVooCli2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli2ActionPerformed
        jfVooVenda3.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooCli2ActionPerformed

    private void jfVooCli3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli3ActionPerformed
        jfVooVenda4.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooCli3ActionPerformed

    private void jfVooCli4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli4ActionPerformed
        jtDataDoRegistroVoo.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooCli4ActionPerformed

    private void jtDataDoRegistroVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDataDoRegistroVooActionPerformed
        jbSalvarVoo.requestFocus();
        somaValores();
    }//GEN-LAST:event_jtDataDoRegistroVooActionPerformed

    private void jbSalvarVoo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarVoo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSalvarVoo1ActionPerformed

    private void jbSalvarVoo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbSalvarVoo1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSalvarVoo1KeyPressed

    private void jfVooVenda5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jfVooVenda5ActionPerformed

    private void jfVooVenda6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jfVooVenda6ActionPerformed

    private void jfVooCli5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jfVooCli5ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3PlanoDeVoo;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbCancelarVoo;
    private javax.swing.JButton jbSalvarVoo;
    private javax.swing.JButton jbSalvarVoo1;
    private javax.swing.JFormattedTextField jfVooCli1;
    private javax.swing.JFormattedTextField jfVooCli2;
    private javax.swing.JFormattedTextField jfVooCli3;
    private javax.swing.JFormattedTextField jfVooCli4;
    private javax.swing.JFormattedTextField jfVooCli5;
    private javax.swing.JFormattedTextField jfVooCliTotal;
    private javax.swing.JFormattedTextField jfVooVenda1;
    private javax.swing.JFormattedTextField jfVooVenda2;
    private javax.swing.JFormattedTextField jfVooVenda3;
    private javax.swing.JFormattedTextField jfVooVenda4;
    private javax.swing.JFormattedTextField jfVooVenda5;
    private javax.swing.JFormattedTextField jfVooVenda6;
    private javax.swing.JFormattedTextField jfVooVendaTotal;
    private javax.swing.JLabel jlMat26;
    private javax.swing.JLabel jlMat28;
    private javax.swing.JLabel jlMat29;
    private javax.swing.JLabel jlMat30;
    private javax.swing.JLabel jlMat31;
    private javax.swing.JLabel jlMat32;
    private javax.swing.JLabel jlTituloPlanoDeVoo;
    private javax.swing.JLabel jlTituloPlanoDeVoo1;
    private javax.swing.JLabel jlTituloPlanoDeVoo2;
    private javax.swing.JFormattedTextField jtDataDoRegistroVoo;
    private javax.swing.JTextField jtLoja5;
    private javax.swing.JTextField jtMatriculaVoo;
    private javax.swing.JTextField jtNomeUsuarioV;
    // End of variables declaration//GEN-END:variables

}
