/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component;

import componentsUtils.UtilsComponents;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import org.edisoncor.gui.label.LabelMetric;

/**
 * Provee un dialogo que se muestra por un tiempo determinado por defecto es de
 * 4 segundos, usted puede ingresar el tiempo que dese que se muestre y asi
 * mostrar el dialogo por el tiempo que ustede requiera si desee otro que usted
 * pueda ocultar por su cuenta mire {@link DialogoButton}
 *
 * @author Angel Navarro
 * @date 15/10/2016
 */
public class DialogGrowl extends javax.swing.JDialog {

    private static final Logger LOG = Logger.getLogger(DialogGrowl.class.getName());

    private String tituloText;
    private JComponent body;
    private long delay = 4 * 1000L;

    /**
     * Muestra el dialgo por defecto el tiempo que se uestra es 1800
     * milisegundos despues de esta tiempo el elemto de cierra.
     *
     * @param parent Componente parent asociado.
     * @param titulo Titulo del Dialogo.
     * @param body Componete Que se agregara al cuerpo del dialogo.
     * @param modal Cuando es true bloque el componente parent.
     */
    public DialogGrowl(java.awt.Frame parent, String titulo, JComponent body, boolean modal) {
        super(parent, modal);
        this.tituloText = titulo;
        this.body = body;
        initComponents();
        title.addCloseAction((ActionEvent e) -> {
            dispose();
        });
        hideGrowl();
    }

    /**
     * Muestra el elemento por el tiempo que se le especifica el
     * <code>delay</code> y despues de ese tiempo se cierra automaticamente.
     *
     * @param parent Componente parent asociado.
     * @param titulo Titulo del Dialogo.
     * @param body Componete Que se agregara al cuerpo del dialogo.
     * @param modal Cuando es true bloque el componente parent.
     * @param delay Tiempo de espera antes de ocultarse.
     */
    public DialogGrowl(java.awt.Frame parent, String titulo, JComponent body, boolean modal, Long delay) {
        super(parent, modal);
        this.tituloText = titulo;
        this.body = body;
        initComponents();
        this.delay = delay * 1000;
        title.addCloseAction((ActionEvent e) -> {
            dispose();
        });
        hideGrowl();
    }

    /**
     * Muestra el elemento por el tiempo que se le especifica el
     * <code>delay</code> y despues de ese tiempo se cierra automaticamente.
     *
     * @param parent Componente parent asociado.
     * @param titulo Titulo del Dialogo.
     * @param body Texto a Incluir dentro del Dialogo.
     * @param modal Cuando es true bloque el componente parent.
     */
    public DialogGrowl(java.awt.Frame parent, String titulo, String body, boolean modal) {
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
        hideGrowl();
    }

    /**
     * Muestra el elemento por el tiempo que se le especifica el
     * <code>delay</code> y despues de ese tiempo se cierra automaticamente.
     *
     * @param parent Componente parent asociado.
     * @param titulo Titulo del Dialogo.
     * @param body Texto a Incluir dentro del Dialogo.
     * @param modal Cuando es true bloque el componente parent.
     * @param delay Tiempo de espera antes de ocultarse.
     */
    public DialogGrowl(java.awt.Frame parent, String titulo, String body, boolean modal, Long delay) {
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
        this.delay = delay * 1000l;
        hideGrowl();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel = new org.edisoncor.gui.panel.PanelNice();
        title = new org.edisoncor.gui.varios.TitleBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(super.getParent());
        System.out.println("x " + getOwner().getX() + " y " + getOwner().getY());
        System.out.println("x " + getOwner().getInsets() + " y " + getOwner().getPreferredSize());
//        setPreferredSize(new Dimension(400, 250));

        title.setTitulo(tituloText);
        
        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
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
        setLocation(UtilsComponents.locationParent(this, 2));
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
            DialogGrowl dialog = new DialogGrowl(new javax.swing.JFrame(), "Angel Navrro", "Texto", true, 2500l);
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
    private org.edisoncor.gui.panel.PanelNice panel;
    private org.edisoncor.gui.varios.TitleBar title;
    // End of variables declaration                   

    private void hideGrowl() {
        Timer t = new Timer(tituloText);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                LOG.info("Ocultando Componente...");
                dispose();
            }
        }, delay);
        setVisible(true);
    }
}
