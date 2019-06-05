/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.table;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ModelTableCellRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

    JPanel panel;
    private static final long serialVersionUID = 1L;

    public ModelTableCellRenderer(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        panel = (JPanel) value;
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
        } else {
            panel.setBackground(table.getBackground());
        }
        
        if (value instanceof JPanel) {
            Component[] cs = panel.getComponents();
            
            for (Component c : cs) {
                if (c instanceof JButton) {
                    final JButton b = (JButton) c;
                    panel.remove(c);
                    b.addActionListener((e) -> {
                        System.out.println("Listener " + b);
                    });
                    panel.add(b);
                }
            }
        }
        return panel;

    }

    @Override
    public Object getCellEditorValue() {
        System.out.println("Cell Editor Value ");
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        panel = (JPanel) value;
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
        } else {
            panel.setBackground(table.getBackground());
        }
        if (value instanceof JPanel) {
            Component[] cs = panel.getComponents();
            System.out.println(cs);
            for (Component c : cs) {
                if (c instanceof JButton) {
                    final JButton b = (JButton) c;
                    b.addActionListener((e) -> {
                        System.out.println("Listener " + b);
                    });
                    panel.add(b);

                }
            }
        }
        return panel;
    }
}
