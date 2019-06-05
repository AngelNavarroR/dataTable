/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import ui.component.models.TableModel;

/**
 *
 * @author Angel Navarro Date Jun 13, 2016
 */
public class Table extends JTable {

    private Image image;
    private Color colortexto;

    public Table() {
        iniciarTabla();
    }

    public Table(javax.swing.table.TableModel dm) {
        super(dm);
        this.colortexto = Color.ORANGE;
        iniciarTabla();
    }

    public Table(Image image, Color colortexto, TableModel dm) {
        super(dm);
        this.image = image;
        this.colortexto = colortexto;
        iniciarTabla();
    }

    public Table(Color colortexto, TableModel dm) {
        super(dm);
        this.colortexto = colortexto;
        iniciarTabla();
    }

    public Table(Image image, TableModel dm) {
        super(dm);
        this.image = image;
        iniciarTabla();
    }

    private void iniciarTabla() {
        setFillsViewportHeight(true); //Expandir las filas a todo el alto
        setOpaque(false); //Sin Opacidad
        // setGridColor(Color.YELLOW); //Cambiar el color del grid de la tabla
        // setSelectionBackground(Color.BLUE); //Color de la seleccion
        setForeground(colortexto); //Color del texto
//        setRowHeight(35);
    }

//Metodo que nos permite hacer transparente las filas
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        final Component c = super.prepareRenderer(renderer, row, column);
        if (c instanceof JComponent && image != null) {
            ((JComponent) c).setOpaque(false); //Hacemos transparente a las filas
        }
        return c;
    }

//Pintamos la imagen de fondo
    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        super.paint(graphics);
        repaint();
    }
}
