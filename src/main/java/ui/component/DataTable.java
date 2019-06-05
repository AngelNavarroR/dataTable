/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import ui.component.lazy.LazyData;
import ui.component.models.TableModel;
import ui.component.table.ModelTableCellRenderer;
import ui.component.utils.refleccion.InputTextUtils;
import ui.component.utils.refleccion.NumberUtils;
import ui.component.utils.refleccion.ReflectionUtils;

/**
 *
 * @author Angel Navarro
 * @date 05/09/2016
 */
public class DataTable extends LazyData<Object> {

    private static final Logger LOG = Logger.getLogger(DataTable.class.getName());

    // variables de componenete
    private org.edisoncor.gui.button.ButtonSeven atras;
    private org.edisoncor.gui.textField.TextFieldRectImage fieldImage;
    private javax.swing.JLabel jLabel1;
    private org.edisoncor.gui.label.LabelRect labelPages;
    private javax.swing.JPanel panelBotones;
    private org.edisoncor.gui.button.ButtonSeven primero;
    private javax.swing.JScrollPane scroll;
    private org.edisoncor.gui.button.ButtonSeven siguiente;
    private javax.swing.JTable table;
    private org.edisoncor.gui.button.ButtonSeven ultimo;
    // fin variables

    private int WIDTH = 600;
    private int HEIGTH = 450;
    private int alto;
    private JScrollBar barra;
    private int HiegthRows = 20;
    private int rowsForPages;
    private Dimension dimension;
    private TableModel tableModel;
    private boolean lazy;
    private boolean inicio;
    private Map<Integer, Object> wigthColmn;

    //variables para table model
    private Class[] typeColumns = null;
    private String[] nameColumns;
    private String[] columnsValueList;
    private Boolean[] columnEdit;
    private JPanel panel;

    public DataTable() {
    }

    /**
     * Por default los typesColumns son de tipo Object y las celdas no son
     * editables
     *
     * @param nameColumns Nombres de las columnas
     * @param dataList Lista de Objectos que se pintaran en la tabla
     * @param columnsValueList Nombres de la propiedades que se se encuentran en
     * la lista de objetos del parametros <code>dataList<code/>
     * @param panelBotones Panel de Botones donde se relizara la accion cada una
     * de la filas donde se encuentra el boton
     */
    public DataTable(String[] nameColumns, List dataList, String[] columnsValueList, JPanel panelBotones) {
        initComponents();
        this.wigthColmn = new HashMap<>();
        this.nameColumns = nameColumns;
        this.list = dataList;
        this.panel = panelBotones;
        this.columnsValueList = columnsValueList;
        this.inicio = true;
        this.countData = new Long(dataList.size());
        this.defaultAssegnee();
    }

    /**
     * Por default los typesColumns son de tipo Object y las celdas no son
     * editables
     *
     * @param dataList Lista de Objectos que se pintaran en la tabla
     * @param columnsValueList Nombres de la propiedades que se se encuentran en
     * la lista de objetos del parametros <code>dataList<code/>
     * @param panelBotones Panel de Botones donde se relizara la accion cada una
     * de la filas donde se encuentra el boton
     */
    public DataTable(List<Map<String, Object>> dataList, String[] columnsValueList, JPanel panelBotones) {
        initComponents();
        try {
            this.wigthColmn = new HashMap<>();
            if (dataList == null || dataList.isEmpty()) {
                throw new RuntimeException("dataList es nulo o esta vacio");
            }
            int cols = dataList.get(0).keySet().size();
            if (panelBotones != null) {
                cols = cols + 1;
            }
            this.nameColumns = dataList.get(0).keySet().toArray(new String[cols]);
            if (panelBotones != null) {
                this.nameColumns[this.nameColumns.length - 1] = "Opciones";
            }
            this.list = dataList;
            this.panel = panelBotones;
            this.columnsValueList = columnsValueList;
            defaultCellEdit();
            this.inicio = true;
            this.countData = new Long(dataList.size());
            this.defaultAssegnee();
        } catch (RuntimeException ex) {
            LOG.log(Level.SEVERE, "", ex);
        }
    }

    /**
     * Por default los typesColumns son de tipo Object y las celdas no son
     * editables
     *
     * @param dataList Lista de Objectos que se pintaran en la tabla
     * @param panelBotones Panel de Botones donde se relizara la accion cada una
     * de la filas donde se encuentra el boton
     */
    public DataTable(List<Map<String, Object>> dataList, JPanel panelBotones) {
        initComponents();
        try {
            this.wigthColmn = new HashMap<>();
            if (dataList == null || dataList.isEmpty()) {
                throw new RuntimeException("dataList es nulo o esta vacio");
            }
            int cols = dataList.get(0).keySet().size();
            if (panelBotones != null) {
                cols = cols + 1;
            }
            this.nameColumns = dataList.get(0).keySet().toArray(new String[cols]);
            this.list = dataList;
            if (panelBotones != null) {
                this.nameColumns[cols - 1] = "Opciones";
            }
            this.panel = panelBotones;
            this.columnsValueList = dataList.get(0).keySet().toArray(new String[cols]);
            defaultCellEdit();
            this.inicio = true;
            this.countData = new Long(dataList.size());
            this.defaultAssegnee();
        } catch (RuntimeException ex) {
            LOG.log(Level.SEVERE, "", ex);
        }
    }

    public DataTable(String[] columnNames, List dataList, String... values) {
        initComponents();
        try {
            this.wigthColmn = new HashMap<>();
            if (dataList == null || dataList.isEmpty()) {
                throw new RuntimeException("dataList es nulo o esta vacio");
            }
            if (columnNames == null) {
                this.nameColumns = values;
            } else {
                this.nameColumns = values;
            }
            this.columnsValueList = values;
            this.list = dataList;
            this.panel = panelBotones;
            defaultCellEdit();
            this.inicio = true;
            this.countData = new Long(dataList.size());
            this.defaultAssegnee();
        } catch (RuntimeException ex) {
            LOG.log(Level.SEVERE, "", ex);
        }
    }

    public DataTable(List dataList, String... ignoreCols) {
        initComponents();
        try {
            this.wigthColmn = new HashMap<>();
            if (dataList == null || dataList.isEmpty()) {
                throw new RuntimeException("dataList es nulo o esta vacio");
            }
            if (dataList != null && !dataList.isEmpty()) {
                Object cols = dataList.get(0);
                this.nameColumns = ReflectionUtils.getNamesPropertys(cols, ignoreCols);
                this.columnsValueList = nameColumns;
            }
            this.list = dataList;
            this.panel = panelBotones;
            defaultCellEdit();
            this.inicio = true;
            this.countData = new Long(dataList.size());
            this.defaultAssegnee();
        } catch (RuntimeException ex) {
            LOG.log(Level.SEVERE, "", ex);
        }
    }

    /**
     * Constructor para especificar todos los tipos de colunas que se usaran en
     * la tabla
     *
     * @param typeColumns
     * @param nameColumns
     * @param columnEdit
     * @param dataList
     * @param columnsValueList
     * @param panelBotones
     */
    public DataTable(Class[] typeColumns, String[] nameColumns, Boolean[] columnEdit, List dataList, String[] columnsValueList, JPanel panelBotones) {
        initComponents();
        this.wigthColmn = new HashMap<>();
        this.typeColumns = typeColumns;
        this.nameColumns = nameColumns;
        this.columnEdit = columnEdit;
        this.list = dataList;
        this.panel = panelBotones;
        this.columnsValueList = columnsValueList;
        this.inicio = true;
        this.countData = new Long(dataList.size());
        this.defaultAssegnee();
    }

    /**
     * Para el uso de Lazy de consultas a la base de datos
     *
     * @param typeColumns
     * @param nameColumns
     * @param columnEdit
     * @param dataList
     * @param columnsValueList
     * @param panelBotones
     * @param islazy
     */
    public DataTable(Class[] typeColumns, String[] nameColumns, Boolean[] columnEdit, LazyData dataList, String[] columnsValueList, JPanel panelBotones, boolean islazy) {
        this.wigthColmn = new HashMap<>();
        initComponents();
        this.typeColumns = typeColumns;
        this.nameColumns = nameColumns;
        this.columnEdit = columnEdit;
        this.panel = panelBotones;
        this.columnsValueList = columnsValueList;
        this.inicio = true;
        this.lazy = true;
        this.countData = dataList.getCountData();
        this.defaultAssegnee();
    }

    /**
     * Para el uso de Lazy de consultas a la base de datos
     *
     * @param nameColumns
     * @param dataList
     * @param columnsValueList
     * @param panelBotones
     * @param islazy
     */
    public DataTable(String[] nameColumns, LazyData dataList, String[] columnsValueList, JPanel panelBotones, boolean islazy) {
        this.wigthColmn = new HashMap<>();
        initComponents();
        this.nameColumns = nameColumns;
        this.panel = panelBotones;
        this.columnsValueList = columnsValueList;
        this.inicio = true;
        this.lazy = true;
        this.countData = dataList.getCountData();
        this.defaultAssegnee();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void initComponents() {
        scroll = new javax.swing.JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table = new ui.component.table.Table();
        panelBotones = new javax.swing.JPanel();
        primero = new org.edisoncor.gui.button.ButtonSeven();
        atras = new org.edisoncor.gui.button.ButtonSeven();
        fieldImage = new org.edisoncor.gui.textField.TextFieldRectImage();
        jLabel1 = new javax.swing.JLabel();
        labelPages = new org.edisoncor.gui.label.LabelRect();
        siguiente = new org.edisoncor.gui.button.ButtonSeven();
        ultimo = new org.edisoncor.gui.button.ButtonSeven();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setLayout(new java.awt.BorderLayout());

        table.setBounds(0, 0, this.getWidth(), this.getHeight());
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        table.setModel(new DefaultTableModel());
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableListener(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        scroll.setViewportView(table);

        add(scroll, java.awt.BorderLayout.CENTER);

        panelBotones.setAlignmentX(0.0F);
        panelBotones.setAlignmentY(0.0F);

        primero.setText("<<");
        primero.setButtonDimension(new java.awt.Dimension(30, 20));
        primero.setMargin(new java.awt.Insets(2, 2, 2, 2));
        primero.addActionListener(this::primeroActionPerformed);
        panelBotones.add(primero);

        atras.setText("<");
        atras.setButtonDimension(new java.awt.Dimension(30, 20));
        atras.setMargin(new java.awt.Insets(2, 2, 2, 2));
        atras.addActionListener(this::atrasActionPerformed);
        panelBotones.add(atras);

        fieldImage.setDocument(new ui.component.utils.refleccion.InputTextUtils(InputTextUtils.DIGITS, 10));
        fieldImage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldImage.setAnchoDeBorde(2.5F);
        fieldImage.addActionListener(this::fieldImageActionPerformed);
        panelBotones.add(fieldImage);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("/");
        jLabel1.setPreferredSize(new java.awt.Dimension(8, 20));
        panelBotones.add(jLabel1);

        labelPages.setText("1");
        labelPages.setPreferredSize(new java.awt.Dimension(60, 20));
        panelBotones.add(labelPages);

        siguiente.setText(">");
        siguiente.setButtonDimension(new java.awt.Dimension(30, 20));
        siguiente.setMargin(new java.awt.Insets(2, 2, 2, 2));
        siguiente.setMultiClickThreshhold(2L);
        siguiente.addActionListener(this::siguienteActionPerformed);
        panelBotones.add(siguiente);

        ultimo.setText(">>");
        ultimo.setButtonDimension(new java.awt.Dimension(30, 20));
        ultimo.setMargin(new java.awt.Insets(2, 2, 2, 2));
        ultimo.addActionListener(this::ultimoActionPerformed);
        panelBotones.add(ultimo);

        add(panelBotones, java.awt.BorderLayout.PAGE_START);
        setBorder(new BasicBorders.ButtonBorder(Color.lightGray, Color.lightGray, Color.lightGray, Color.lightGray));
    }

    private void primeroActionPerformed(ActionEvent evt) {
        if (totalPages == 0) {
            return;
        }
        actionListenerButtons();
        currentPage = 1;
        if (currentPage <= totalPages) {
            llenar();
        }
    }

    private void atrasActionPerformed(ActionEvent evt) {
        if (totalPages == 0) {
            return;
        }
        actionListenerButtons();
        currentPage--;
        if (currentPage >= 1) {
            llenar();
        } else {
            currentPage++;
        }
    }

    private void fieldImageActionPerformed(ActionEvent evt) {
        if (totalPages == 0) {
            fieldImage.setText("0");
            return;
        }
        actionListenerButtons();
        if (NumberUtils.isEmpetyFiel(fieldImage)) {
            fieldImage.setText("" + currentPage);
            JOptionPane.showMessageDialog(this, "Campo de texto esta vacio", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Integer target = Integer.valueOf(fieldImage.getText());
        if (target > totalPages) {
            fieldImage.setText("" + currentPage);
            JOptionPane.showMessageDialog(this, "Pagina ingresada es mayor a la paguinas de la tabla", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (target <= 0) {
            fieldImage.setText("" + currentPage);
            return;
        }
        currentPage = target;
        if (currentPage <= totalPages) {
            llenar();
        }
    }

    private void siguienteActionPerformed(ActionEvent evt) {
        if (totalPages == 0) {
            return;
        }
        actionListenerButtons();
        currentPage++;
        if (currentPage <= totalPages) {
            llenar();
        } else {
            currentPage--;
        }
    }

    private void ultimoActionPerformed(ActionEvent evt) {
        if (totalPages == 0) {
            return;
        }
        actionListenerButtons();
        currentPage = totalPages;
        if (currentPage <= totalPages) {
            llenar();
        }
    }

    private void actionListenerButtons() {
        alto = dimension.height;
        barra = scroll.getVerticalScrollBar();
    }

    private void llenar() {
        addData(currentPage);
        fieldImage.setText("" + currentPage);
    }

    private void addData(int page) {
        int p = page < totalPages ? (page * rows) : countData.intValue();
        if (lazy) {
            this.tableModel.addData(this.load(this.getPaginaInicial(), p));
        } else {
            if (list != null) {
                dataTemp = list.subList(this.getPaginaInicial(), p);
                this.tableModel.addData(dataTemp);
            }
        }
    }

    @Override
    public int getHeight() {
        return this.HEIGTH;
    }

    @Override
    public int getWidth() {
        return this.WIDTH;
    }

    public void setHeight(int HEIGTH) {
        setBounds(5, 5, this.WIDTH, HEIGTH);
    }

    public void setWidth(int WIDTH) {
        setBounds(5, 5, WIDTH, this.HEIGTH);
    }

    public void setWidthHeight(int WIDTH, int HEIGTH) {
        setBounds(5, 5, WIDTH, HEIGTH);
        table.setBounds(0, 0, WIDTH, HEIGTH);
    }

    private void defaultAssegnee() {
        fieldImage.setText("1");
        pageData = 1;
        table.setRowHeight(HiegthRows);
    }

    public void setHiegthRows(int HiegthRows) {
        this.HiegthRows = HiegthRows;
        table.setRowHeight(HiegthRows);
        update();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        if (inicio) {
            this.HEIGTH = height;
            this.WIDTH = width;
            table.setBounds(0, 0, this.WIDTH, this.HEIGTH);
            super.setBounds(x, y, this.WIDTH, this.HEIGTH);
            super.updateUI();
            update();
        }
    }

    public void update() {
        panelBotones.setVisible(paginar());
        data(typeColumns, nameColumns, columnEdit, columnsValueList, panel, currentPage);
        table.setModel(tableModel);
        if (panel != null) {
            table.setDefaultRenderer(this.panel.getClass(), new ModelTableCellRenderer(this.panel));
            table.setDefaultEditor(this.panel.getClass(), new ModelTableCellRenderer(this.panel));
        }
        dimension = getDimensionTable();
        scroll.setPreferredSize(dimension);
        actionListenerButtons();
    }

    private void data(Class[] typeColumns, String[] nameColumns, Boolean[] columnEdit, String[] columnsValueList, JPanel panelBotones, int page) {
        int p = page < totalPages ? (page * rows) : countData.intValue() - 1;
        if (lazy) {
            this.tableModel = new TableModel(typeColumns, nameColumns, columnEdit, this.load(this.getPaginaInicial(), p), columnsValueList, panel);
        } else {
            if (list != null && !list.isEmpty()) {
                dataTemp = list.subList(this.getPaginaInicial(), (p == 0 ? list.size() : p));
            }
            this.tableModel = new TableModel(typeColumns, nameColumns, columnEdit, dataTemp, columnsValueList, panel);
        }
        TableColumnModel columnModel = getColumnModel();
        table.setColumnModel(columnModel);
    }

    public Dimension getDimensionTable() {
        if (paginar()) {
            return new Dimension(getBounds().width, (getBounds().height - panelBotones.getPreferredSize().height));
        } else {
            return new Dimension(getBounds().width, getBounds().height);
        }
    }

    public boolean paginar() {
        calcularRows();
        if (countData != null || countData > 0l) {
            return rows < countData;
        }
        return false;
    }

    private void calcularRows() {
        int heightPanel = (getBounds().height - 25);
        int heightTable = (getBounds().height - 25) - panelBotones.getPreferredSize().height;
        int rt = heightTable / getHiegthRows();
        int rp = heightPanel / getHiegthRows();
        if (countData != null) {
            if (countData > 0l) {
                if (rowsForPages == 0) {
                    if (countData <= rt) {
                        if (countData <= rp) {
                            rows = rp;
                        } else {
                            rows = rt;
                        }
                    } else {
                        rows = rt;
                    }
                } else {

                    rows = rowsForPages;
                }
                int res = Long.valueOf(countData % rows).intValue();
                int pageTemp = Long.valueOf(countData / rows).intValue();
                if (res == 0) {
                    totalPages = pageTemp;
                } else {
                    totalPages = pageTemp + 1;
                }
            }
        }
        labelPages.setText("" + totalPages);
    }

    public int getHiegthRows() {
        return HiegthRows;
    }

    @Override
    public void removeAll() {
        tableModel.removeAll();
        fieldImage.setText("0");
        labelPages.setText("0");
        this.wigthColmn = new HashMap<>();
        this.typeColumns = null;
        this.nameColumns = null;
        this.columnEdit = null;
        this.list = null;
        this.panel = null;
        this.columnsValueList = null;
        this.inicio = true;
        this.countData = null;
        this.HiegthRows = 20;
        this.barra = null;
        this.tableModel = new TableModel();
        this.lazy = false;
        this.inicio = false;
        this.totalPages = 0;
    }

    public void setWidthColumn(int columnIndex, int width) {
        getColumn(columnIndex).setPreferredWidth(width);
        wigthColmn.put(columnIndex, width);
    }

    public Object getObject() {
        return tableModel.getObject(table.getSelectedRow());
    }

    public void setModel() {
        table.setModel(tableModel);
    }

    public TableModel getModel() {
        return tableModel;
    }

    public TableColumn getColumn(int columnIndex) {
        return getColumnModel().getColumn(columnIndex);
    }

    public TableColumnModel getColumnModel() {
        return table.getColumnModel();
    }

    public void setRowsForPages(int rowsForPages) {
        this.rowsForPages = rowsForPages;
        update();
    }

    public int getRowsForPages() {
        return rowsForPages;
    }

    private void tableListener(MouseEvent ml) {
        if (table.getSelectedRow() >= 0 && table.getSelectedColumn() >= 0) {
            Object panel1 = table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());
            if (panel1 instanceof JPanel) {
                for (Component object : ((JPanel) ((Component) panel1)).getComponents()) {
//                    System.out.println(object);
                }
                Component com = ((Component) panel1).getComponentAt(ml.getX(), ml.getY());
            } else {

            }
        }
    }

    public void setColumnEditable(int columnIndex, boolean edit) {
        try {
            columnEdit[columnIndex] = edit;
            update();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Set cell editor", e);
        }
    }

    public void setColumnEdit(Boolean[] aBoolean) {
        this.columnEdit = aBoolean;
        update();
    }

    private void defaultCellEdit() {
        if (columnEdit == null) {
            columnEdit = new Boolean[nameColumns.length];
            for (int i = 0; i < nameColumns.length; i++) {
                columnEdit[i] = false;
            }
        }
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        table.addMouseListener(l);
    }

    @Override
    public synchronized void addMouseWheelListener(MouseWheelListener l) {
        table.addMouseWheelListener(l);
    }

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        table.addMouseMotionListener(l);
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        table.addKeyListener(l);
    }

    @Override
    public synchronized void addFocusListener(FocusListener l) {
        table.addFocusListener(l);
    }

    public int getSelectedColumn() {
        return table.getSelectedColumn();
    }

    public synchronized void ToolTip(Boolean showToolTip) {
        if (!showToolTip) {
            return;
        }
        Object object = getObject();

        table.setToolTipText("<html>Angel <br/> Navarro</html>");
    }

}
