/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import ui.component.utils.refleccion.ReflectionFailureException;
import ui.component.utils.refleccion.ReflectionUtils;

/**
 *
 * @author Angel Navarro
 * @Date 13/06/2016
 */
public class TableModel extends AbstractTableModel {

    private static final Logger LOG = Logger.getLogger(TableModel.class.getName());

    private Class[] typeColumns = null;
    private String[] nameColumns;
    private String[] columnsValueList;
    private Boolean[] columnEdit;
    private Boolean[] rowEdit;
    private Object[][] data;
    private List dataList;
    private JPanel panel;
    private Map<String, Object> map;

    public TableModel() {
    }

    public TableModel(String[] nameColumns, Boolean[] columnEdit, List dataList, String[] columnsValueList, JPanel panelBotones) {
        this.nameColumns = nameColumns;
        this.columnEdit = columnEdit;
        this.dataList = dataList;
        this.panel = panelBotones;
        this.columnsValueList = columnsValueList;
        map = new HashMap<>();
    }

    public TableModel(Class[] typeColumns, String[] nameColumns, Boolean[] columnEdit, List dataList, String[] columnsValueList, JPanel panelBotones) {
        this.typeColumns = typeColumns;
        this.nameColumns = nameColumns;
        this.columnEdit = columnEdit;
        this.dataList = dataList;
        this.panel = panelBotones;
        this.columnsValueList = columnsValueList;
        map = new HashMap<>();
    }

    public TableModel(Class[] typeColumns, String[] nameColumns, List dataList, String[] columnsValueList, JPanel panelBotones) {
        this.typeColumns = typeColumns;
        this.nameColumns = nameColumns;
        this.dataList = dataList;
        this.panel = panelBotones;
        this.columnsValueList = columnsValueList;
        map = new HashMap<>();
    }

    public TableModel(Class[] typeColumns, String[] nameColumns, Boolean[] columnEdit, Object[][] data, JPanel panelBotones) {
        this.typeColumns = typeColumns;
        this.nameColumns = nameColumns;
        this.columnEdit = columnEdit;
        this.data = data;
        this.panel = panelBotones;
    }

    public TableModel(Class[] typeColumns, String[] nameColumns, Object[][] data, JPanel panelBotones) {
        this.typeColumns = typeColumns;
        this.nameColumns = nameColumns;
        this.data = data;
        this.panel = panelBotones;
    }

    @Override
    public String getColumnName(int column) {
        return nameColumns[column];
    }

    @Override
    public int getRowCount() {
        if (dataList != null) {
            if (dataList.size() > 0) {
                return dataList.size();
            }
        } else if (data != null) {
            return data.length;
        } else {
            return 0;
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (nameColumns != null) {
            return nameColumns.length;
        }
        return -1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (typeColumns == null) {
            if (nameColumns.length == (columnIndex + 1) && panel != null) {
                return panel.getClass();
            }
            return Object.class;
        } else {
            return typeColumns[columnIndex];
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnEdit == null) {
            return false;
        }
        if ((columnEdit.length - 1) <= columnIndex) {
            return false;
        }

        return columnEdit[columnIndex];
    }

    public void setCellEditable(int rowIndex, int columnIndex, boolean edit) {
        throw new RuntimeException("Falta Implementacion");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String separador = ":";
        if (dataList != null) {
            if (dataList.size() > 0) {
                Object d = dataList.get(rowIndex);
                if (d instanceof Map) {

                } else {
                    if (columnsValueList.length == 0) {
                        return new RuntimeException("Columna a extraer de la lista esta vacia.");
                    }
                }

                if (this.panel != null) {
                    if (!isComponet(columnIndex)) {
                        if (d instanceof Map) {
                            return ((Map) d).get(nameColumns[columnIndex]);
                        } else {
                            return getValue(columnsValueList[columnIndex], d, separador);
                        }
                    } else {
                        return panel;
                    }
                } else {
                    if (d instanceof Map) {
                        return ((Map) d).get(nameColumns[columnIndex]);
                    } else {
                        return getValue(columnsValueList[columnIndex], d, separador);
                    }
                }
            }
        } else {
            if (data.length > 0) {
                return data[rowIndex][columnIndex];
            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (dataList != null) {
            if (dataList.size() > 0) {
                Object d = dataList.get(rowIndex);
                if (d instanceof Map) {

                } else {
                    if (columnsValueList.length == 0) {
                        throw new RuntimeException("Columna a extraer de la lista esta vacia.");
                    }
                }
                if (this.panel != null) {
                    if (d instanceof Map) {
                        ((Map) dataList.get(dataList.indexOf(d))).put(columnsValueList[columnIndex], aValue);
                    } else {
                        setValue(columnsValueList[columnIndex], d, aValue);
                    }
                } else {
                    if (d instanceof Map) {
                        aValue = ((Map) d).get(columnsValueList[columnIndex]);
                    } else {
                        setValue(columnsValueList[columnIndex], d, aValue);
                    }
                }
            }
        } else {
            if (data.length > 0) {
                data[rowIndex][columnIndex] = aValue;
            }
        }
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }

    private Object getValue(String value, Object d, String separador) {
        String puntos[];
        if (value.trim().length() == 0) {
            return new RuntimeException("Columna esta vacia");
        }
        try {
            if (value.contains(separador)) {
                puntos = value.split(separador);
                Object fiel = getField(separador, value, d);
                if (fiel != null) {
                    return ReflectionUtils.getFieldValue(fiel, puntos[puntos.length - 1], false);
                } else {
                    return null;
                }
            } else {
                return ReflectionUtils.getFieldValue(d, value, false);
            }
        } catch (ReflectionFailureException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void removeAll() {
        this.nameColumns = null;
        this.columnEdit = null;
        this.dataList = null;
        this.panel = null;
        this.columnsValueList = null;
        this.data = null;
        map = new HashMap<>();
    }

    public void addData(List dataTemp) {
        this.dataList = dataTemp;
        fireTableRowsInserted(0, (dataList.isEmpty() ? 0 : dataList.size() - 1));
    }

    public void addData(Object[][] dataTemp) {
        this.data = dataTemp;
        fireTableRowsInserted(0, (dataList.isEmpty() ? 0 : dataList.size() - 1));
    }

    private void setValue(String columnNane, Object object, Object value) {
        String separador = ":";
        String puntos[];
        if (columnNane.trim().length() == 0) {
            throw new RuntimeException("Columna esta vacia");
        }
        try {
            if (columnNane.contains(separador)) {
                puntos = columnNane.split(separador);
                if (puntos.length == 0) {
                    return;
                }
                puntos = columnNane.split(separador);
                Object fiel = getField(separador, columnNane, value);
                if (fiel != null) {
                    ReflectionUtils.setFieldValue(fiel, puntos[puntos.length - 1], value);
                } else {
                }
            } else {
                ReflectionUtils.setFieldValue(object, columnNane, value);
            }
        } catch (ReflectionFailureException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Object getField(String separador, String columnName, Object d) throws ReflectionFailureException {
        String[] puntos = columnName.split(separador);
        if (puntos.length == 0) {
            return null;
        }
        Object clazzHijos = null;
        int x = 0;
        do {
            if (x == 0) {
                try {
                    clazzHijos = ReflectionUtils.getFieldValue(d, puntos[x], false);
                } catch (ReflectionFailureException ex) {
                    Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ((x + 1) < puntos.length) {
                clazzHijos = ReflectionUtils.getFieldValue(clazzHijos, puntos[x], false);
            } else {
                break;
            }
            x++;
        } while (x < puntos.length);
        return clazzHijos;
    }

    /**
     * Si el tipo de datos pasados son de tipo List retornara una Objeto
     *
     * @param <T>
     * @param rowIndex
     * @return
     */
    public <T> T getObject(int rowIndex) {
        if (dataList != null || !dataList.isEmpty()) {
            if (rowIndex > -1) {
                return (T) dataList.get(rowIndex);
            }
        } else {
            if (rowIndex > -1) {
                return (T) data[rowIndex];
            }
        }
        return null;
    }

    private boolean isComponet(int columnIndex) {
        Class typ = null;
        if (typeColumns == null) {
            if (nameColumns.length == (columnIndex + 1) && panel != null) {
                typ = panel.getClass();
            } else {
                typ = Object.class;
            }
        } else {
            typ = typeColumns[columnIndex];
        }
        return typ == JPanel.class || typ.getSuperclass() == JPanel.class;
    }

    public Class[] getTypeColumns() {
        return typeColumns;
    }

    public void setTypeColumns(Class[] typeColumns) {
        this.typeColumns = typeColumns;
    }

    public String[] getNameColumns() {
        return nameColumns;
    }

    public void setNameColumns(String[] nameColumns) {
        this.nameColumns = nameColumns;
    }

    public Boolean[] getColumnEdit() {
        return columnEdit;
    }

    public void setColumnEdit(Boolean[] columnEdit) {
        this.columnEdit = columnEdit;
    }

    public Boolean[] getRowEdit() {
        return rowEdit;
    }

    public void setRowEdit(Boolean[] rowEdit) {
        this.rowEdit = rowEdit;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public String[] getColumnsValueList() {
        return columnsValueList;
    }

    public void setColumnsValueList(String[] columnsValueList) {
        this.columnsValueList = columnsValueList;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

}
