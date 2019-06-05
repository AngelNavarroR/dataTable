/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentsUtils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Angel Navarro
 * @date 24/09/2016
 */
public class UtilsComponents {

    private static final Logger LOG = Logger.getLogger(UtilsComponents.class.getName());

    public static void borrarContentTextField(JPanel panel) {
        if (panel == null) {
            throw new RuntimeException("Panel es null");
        }
        Component[] components = panel.getComponents();
        int count = 0;
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField tf = (JTextField) component;
                tf.setText("");
                count++;
            }
        }
    }

    public static void agregarContentTextField(JPanel panel, Object content) {
        if (panel == null) {
            throw new RuntimeException("Panel es null");
        }
        Component[] components = panel.getComponents();
        int count = 0;
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField tf = (JTextField) component;
                tf.setText((String) content);
                count++;
            }
        }
    }

    /**
     *
     * @param l {@link Map<String, Object>} con los
     * @param rows Numero de filas para los componetes de {@link  JLabel} Y
     * {@link JTextField}
     * @param col Numero de columnas para la ubicacion de los coponentes.
     * @return {@link JPanel com los {@ JLabel} Y {@link JTextField} que los
     * campos contenidos en los mapa, el key de es el valor que se mostrara en
     * el texto del label, por defecto tiene vetical y horizontal grap 5.
     */
    public static JPanel createPanelHashMap(Map<String, Object> l, int rows, int col) {
        JPanel temp = new JPanel(new GridLayout(rows, col, 5, 5));
        for (Map.Entry<String, Object> entry : l.entrySet()) {
            JTextField field = null;
            JLabel lTemp = new JLabel(entry.getKey());
            if (!UtilsString.isEmpetyObject(entry.getValue())) {
                field = new JTextField(entry.getValue().toString());
            } else {
                field = new JTextField();
            }
            field.setName(entry.getKey());
            temp.add(lTemp);
            temp.add(field);
        }
        return temp;
    }

    /**
     *
     * @param map
     * @param parent
     * @param vGrap
     * @param hGrap
     * @param col
     * @return
     */
    public static JPanel createPanelGroupLay(Map<String, Object> map, Container parent, int vGrap, int hGrap, int col) {
        JPanel panel = null;
        try {
            if (col <= 0) {
                return null;
            }
            panel = new JPanel();

            GroupLayout layout = new GroupLayout(parent);
            parent.setLayout(layout);
            Integer count = 0;
            // Fila 
            ParallelGroup horizontal = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
            ParallelGroup horzAux = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
            SequentialGroup seqGroupHorz = layout.createSequentialGroup();
            SequentialGroup fila = layout.createSequentialGroup();
            // Columna
            ParallelGroup vertical = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
            ParallelGroup vertAux = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
            SequentialGroup seqGroupVert = layout.createSequentialGroup();
            SequentialGroup column = layout.createSequentialGroup();
            List<SequentialGroup> cols = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                JTextField field = null;
                JLabel lTemp = new JLabel(entry.getKey());
                lTemp.setName(entry.getKey());
                if (!UtilsString.isEmpetyObject(entry.getValue())) {
                    field = new JTextField(entry.getValue().toString());
                } else {
                    field = new JTextField();
                }
                field.setName(entry.getKey());
                /* contiene los componentes de la primera fila */
                fila.addComponent(lTemp);
                fila.addGap(hGrap);
                count++;
                addHorzGroup(fila, horzAux, seqGroupHorz, hGrap, col, count, layout);
                fila.addComponent(field);
                addHorzGroup(fila, horzAux, seqGroupHorz, hGrap, col, count, layout);

                /* contiene los componentes de las columnas */
                column.addComponent(lTemp);
                column.addGap(vGrap);
                cols.add(column);
                addVertGroup(cols, col, vertAux, seqGroupVert, vGrap, layout);
                column = layout.createSequentialGroup();
                column.addComponent(field);
                column.addGap(vGrap);
                cols.add(column);
                addVertGroup(cols, col, vertAux, seqGroupVert, vGrap, layout);

            }
            horizontal.addGroup(seqGroupHorz);
            layout.setHorizontalGroup(horizontal);

            vertical.addGroup(seqGroupVert);
            layout.setHorizontalGroup(vertical);

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Create Panel Group Layout", e);
        }
        return panel;
    }

    private static void addVertGroup(List<SequentialGroup> cols, int col, ParallelGroup vertAux, SequentialGroup seqGroupVert, int vGrap, GroupLayout layout) {
        if (cols.size() == col) {
            for (SequentialGroup col1 : cols) {
                vertAux.addGroup(col1);
            }
            cols = new ArrayList<>();
            seqGroupVert.addGap(vGrap);
            seqGroupVert.addGroup(vertAux);
            vertAux = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        }
    }

    private static void addHorzGroup(SequentialGroup fila, ParallelGroup horzAux, SequentialGroup seqGroupHorz, int hGrap, int col, Integer count, GroupLayout layout) {
        if (count == col) {
            horzAux.addGroup(fila);
            seqGroupHorz.addGap(hGrap);
            seqGroupHorz.addGroup(horzAux);
            horzAux = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);

        }
    }

    public static Rectangle getRectangleComponetLength(JComponent panel) {
        Component[] cs = panel.getComponents();
        int length = cs.length - 1;
        return new Rectangle(cs[length].getX(), cs[length].getY(), cs[length].getWidth(), cs[length].getHeight());
    }

    public static Rectangle getRectangleComponet(JComponent panel, int index) {
        Component[] cs = panel.getComponents();
        if (cs.length > index) {
            throw new RuntimeException("Index es mayor al indices de componentes que contiene la lista");
        }
        return new Rectangle(cs[index].getX(), cs[index].getY(), cs[index].getWidth(), cs[index].getHeight());
    }

    /**
     * 0 CENTER 1 RIGTH_TOP 2 RIGTH_BOTTOM
     *
     * @param component
     * @param localtion
     * @return
     */
    public static Point locationParent(Window component, int localtion) {
        try {
            if (component == null) {
                System.out.println("Componentes son nulos");
                return null;
            }
            Window parent = component.getOwner();
            if (parent == null) {
                System.out.println("Parent es nulo");
                return null;
            }
            System.out.println("location ");
            Dimension size = component.getPreferredSize();
            Dimension parentSize = parent.getPreferredSize();
            int x = 0;
            int y = 0;
            System.out.println("RIGTH_CENTER: ");
            System.out.println("RIGTH_BOTTOM: ");
            System.out.println("LEFT_TOP: ");
            System.out.println("LEFT_CENTER: ");
            System.out.println("LEFT_BOTTON: ");
            System.out.println("CENTER: ");
            switch (localtion) {
                case 1:
                    x = ((parentSize.width - size.width) - 20);
                    y = 30;
                case 2:
                    x = ((parentSize.width - size.width) - 20);
                    y = ((parentSize.height - size.height) - 10);
                default:

                    break;
            }
            System.out.print("Component width " + size.width);
            System.out.println(" Parent width " + parentSize.width + " point: x " + x + " y " + y);
            return new Point(x, y);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Create Point location.", e);
        }
        return null;
    }

}
