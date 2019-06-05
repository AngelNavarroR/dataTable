/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui.component;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Angel Navarro
 * @date 19/10/2016
 */
public class BotonesTable extends JPanel{

    private JButton ver;
    private JButton editar;
    private JButton eliminar;
    private int width = 15;
    private int heigth = 15;

    public BotonesTable() {
        initComponents();
    }
    
    public BotonesTable(int width, int heigth) {
        this.heigth = heigth;
        this.width = width;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(1, 3, 5, 0));
        ImageIcon iconVer = new ImageIcon(getClass().getResource("/procesosactiiviti/frm/act/utils/abrir.png"));
        Icon ic = new ImageIcon(iconVer.getImage().getScaledInstance(this.width, this.heigth, Image.SCALE_DEFAULT));
        ver = new JButton(ic);
        ImageIcon iconEdit = new ImageIcon(getClass().getResource("/procesosactiiviti/frm/act/utils/edicion.png"));
        Icon ice = new ImageIcon(iconEdit.getImage().getScaledInstance(this.width, this.heigth, Image.SCALE_DEFAULT));
        editar = new JButton(ice);
        ImageIcon iconElim = new ImageIcon(getClass().getResource("/procesosactiiviti/frm/act/utils/delete.png"));
        Icon icel = new ImageIcon(iconElim.getImage().getScaledInstance(this.width, this.heigth, Image.SCALE_DEFAULT));
        eliminar = new JButton(icel);
        add(ver);
        add(editar);
        add(eliminar);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.print("Evente " + e.getButton());
                System.out.print(" Click count " + e.getClickCount());
                System.out.print(" ID " + e.getID());
                System.out.print("Point " + e.getPoint());
                System.out.print(" X " + e.getX());
                System.out.println(" Y " + e.getY());
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void addActionListenerVer(ActionListener evt){
        final JButton editar1 = editar;
        editar1.addActionListener(evt);
        this.remove(editar);
        add(editar1);
        this.validate();
    }
    
    public void addActionListenerEditar(ActionListener evt){
        final JButton editar1 = editar;
        editar1.addActionListener(evt);
        this.remove(editar);
        add(editar1);
        validate();
    }
    public void addActionListenerEliminar(ActionListener evt){
        final JButton eliminar1 = eliminar;
        eliminar1.addActionListener(evt);
        this.remove(eliminar);
        add(eliminar1);
        validate();
    }
    
}
