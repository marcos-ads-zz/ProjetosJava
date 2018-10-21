package modulo.versao;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 *
 * @author Marcos Junior
 */
public class JifNotasDoSistema extends javax.swing.JInternalFrame {

    private Versao ver = new Versao();

    public JifNotasDoSistema() {
        initComponents();
        setTitle("Notas do Sistema: " + ver.getVersao());
        Ajuste();
    }

    private void Ajuste() {
        try {
            setHorizontalAlignment();
        } catch (BadLocationException ex) {
            Logger.getLogger(JifNotasDoSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void setHorizontalAlignment() throws BadLocationException {
        jEditorNotasdoSistema.setEditorKit(new MyEditorKit());
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_JUSTIFIED);
        StyledDocument doc = (StyledDocument) jEditorNotasdoSistema.getDocument();
        doc.insertString(0, ver.getNotas(), attrs);
        doc.setParagraphAttributes(0, doc.getLength() - 1, attrs, false);
    }

    class MyEditorKit extends StyledEditorKit {

        @Override
        public ViewFactory getViewFactory() {
            return new StyledViewFactory();
        }

        class StyledViewFactory implements ViewFactory {

            @Override
            public View create(Element elem) {
                String kind = elem.getName();
                if (kind != null) {
                    switch (kind) {
                        case AbstractDocument.ContentElementName:
                            return new LabelView(elem);
                        case AbstractDocument.ParagraphElementName:
                            return new ParagraphView(elem);
                        case AbstractDocument.SectionElementName:
                            return new CenteredBoxView(elem, View.TOP);
                        case StyleConstants.ComponentElementName:
                            return new ComponentView(elem);
                        case StyleConstants.IconElementName:
                            return new IconView(elem);
                        default:
                            break;
                    }
                }
                return new LabelView(elem);
            }
        }
    }

    class CenteredBoxView extends BoxView {

        public CenteredBoxView(Element elem, int axis) {
            super(elem, axis);
        }

        @Override
        protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets, int[] spans) {
            super.layoutMajorAxis(targetSpan, axis, offsets, spans);
            int textBlockHeight = 0;
            int offset;
            for (int i = 0; i < spans.length; i++) {
                textBlockHeight = spans[i];
            }
            offset = (targetSpan = textBlockHeight);
            for (int i = 0; i < offsets.length; i++) {
                offsets[i] += offset;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorNotasdoSistema = new javax.swing.JEditorPane();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jEditorNotasdoSistema.setEditable(false);
        jEditorNotasdoSistema.setBackground(new java.awt.Color(0, 204, 153));
        jEditorNotasdoSistema.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas do Sistema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18))); // NOI18N
        jEditorNotasdoSistema.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jScrollPane2.setViewportView(jEditorNotasdoSistema);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorNotasdoSistema;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
