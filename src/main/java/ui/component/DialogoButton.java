/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import org.edisoncor.gui.label.LabelMetric;

/**
 * Muestra un dialogo para inresar el mensaje que ustede dese o un componete que
 * requiera mostra en el dialogo y provee un botton para cerrar el dialogo.
 *
 * <p>
 * Si desea uno que se oculte automaticamente mire {@link DialogGrowl}</p>
 *
 * @author Angel Navarro
 * @date 16/10/2016
 */
public class DialogoButton extends javax.swing.JDialog {

    private static final Logger LOG = Logger.getLogger(DialogoButton.class.getName());

    private String tituloText;
    private JComponent body;

    /**
     * Creates new form DialogGrowl
     *
     * @param parent
     * @param titulo
     * @param body
     * @param modal
     */
    public DialogoButton(java.awt.Frame parent, String titulo, JComponent body, boolean modal) {
        super(parent, modal);
        this.tituloText = titulo;
        this.body = body;
        initComponents();
        title.addCloseAction((ActionEvent e) -> {
            dispose();
        });
    }

    /**
     *
     * @param parent
     * @param titulo
     * @param body
     * @param modal
     */
    public DialogoButton(java.awt.Frame parent, String titulo, String body, boolean modal) {
        super(parent, modal);
        this.tituloText = titulo;
        LabelMetric d = new LabelMetric();
        d.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d.setText(body);
        d.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.body = d;
        initComponents();
        title.addCloseAction((ActionEvent e) -> {
            dispose();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel = new org.edisoncor.gui.panel.PanelNice();
        title = new org.edisoncor.gui.varios.TitleBar();
        salir = new org.edisoncor.gui.button.ButtonSeven();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(super.getParent());
        setPreferredSize(new Dimension(400, 250));

        title.setTitulo(tituloText);

        salir.setText("Salir");
        salir.addActionListener((ActionEvent e) -> {
            salirActionPerformed(e);
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            LOG.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            DialogoButton dialog = new DialogoButton(new javax.swing.JFrame(), "Angel Navrro", "Texto", true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private org.edisoncor.gui.button.ButtonSeven salir;
    private org.edisoncor.gui.panel.PanelNice panel;
    private org.edisoncor.gui.varios.TitleBar title;
    // End of variables declaration                   

    private void salirActionPerformed(ActionEvent e) {
        dispose();
    }

    /**
     * Accion del button para realizar acciones personalizadas.
     *
     * @param evt {@code ActionListener}
     */
    public void addActionListenerButton(ActionListener evt) {
        salir.addActionListener(evt);
    }
}
