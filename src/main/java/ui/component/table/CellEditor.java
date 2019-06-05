/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Angel Navarro
 * @Date 13/06/2016
 */
public class CellEditor extends AbstractCellEditor implements TableCellEditor {

    private final Component component;
    private JTextField field;
    private Font bold = new Font("Arial", Font.BOLD, 12);

    public CellEditor(Component component) {
        this.component = component;
    }

    @Override
    public Object getCellEditorValue() {
//        if(component == null){
//            field = new JTextField();
//            return field;
//        }
        return component;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        System.out.println(value);
        if (value == null) {
            return null;
        } else {
            if (value instanceof Component) {
                System.out.println("Comp" + value);
                return component;
            } else if(value instanceof Integer){
//                this.setText("" + Integer.valueOf(value.toString()));
//                this.setBackground(new Color(0, 255, 0));
            }

        }
        return null;

    }

}
