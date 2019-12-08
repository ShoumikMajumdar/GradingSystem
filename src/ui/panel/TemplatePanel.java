package ui.panel;

import javax.swing.*;

public class TemplatePanel extends JPanel {

//    private GroupableTableHeader header;
//    private JTable table;
//    private JScrollPane jsp;
//    private DefaultTableModel tableModel;
//
//    private JPopupMenu rowPopupMenu;
//    private JPopupMenu headerPopupMenu;
//    private JPopupMenu cellPopupMenu;
//
//    private JMenuItem menuItemAddRow;
//    private JMenuItem menuItemRemoveRow;
//    private JMenuItem menuItemRemoveAllRow;
//
//    private JMenuItem menuItemAddCol;
//    private JMenuItem menuItemRemoveCol;
//
//    private Object[][] tableData;
//    private Object[] tableHeader;
//
//    public TemplatePanel(Object[][] tableData, Object[] tableHeader) {
//        super(true);
//        update(tableData, tableHeader);
//    }
//
//    private void initialize(Object[][] tableData, Object[] tableHeader) {
//        this.tableData = tableData;
//        this.tableHeader = tableHeader;
//    }
//
//    private void update(Object[][] tableData, Object[] tableHeader) {
//        initialize(tableData, tableHeader);
//        addComponent();
//        setContent();
//        addListener();
//    }
//
//    private void addComponent() {
//        JPanel jp = new JPanel();
//        jp.setLayout(new BorderLayout());
//        jp.add(getMainTable(), BorderLayout.NORTH);
//        jp.add(getActionButtons(), BorderLayout.SOUTH);
//        add(jp);
//    }
//
//    private void setContent() {
//        table.setComponentPopupMenu(getRowPopup());
//        table.setCellSelectionEnabled(true);
//        table.setRowSelectionAllowed(true);
//        table.setPreferredScrollableViewportSize(table.getPreferredSize());
//        table.setFillsViewportHeight(true);
//
//        header.setComponentPopupMenu(getHeaderPopup());
//
//        jsp.setPreferredSize(new Dimension(UIConsts.TABLE_WIDTH, UIConsts.TABLE_HEIGHT));
//    }
//
//    private void addListener() {
//        menuItemAddRow.addActionListener(this);
//        menuItemRemoveRow.addActionListener(this);
//        menuItemRemoveAllRow.addActionListener(this);
//
//        menuItemAddCol.addActionListener(this);
//        menuItemRemoveCol.addActionListener(this);
//        header.addMouseListener(new HeaderMouseListener(header));
//    }
//
//    private JPanel getMainTable(){
//        JPanel jp = new JPanel();
//        // creates table
//        tableModel = new DefaultTableModel();
//
//        Component root = Component.buildTestComponent();
//        ArrayList<String> tableHeaderStr = new ArrayList<String>();
//        buildTableHeader(root, tableHeaderStr);
//        tableModel.setDataVector(null, tableHeaderStr.toArray());
//
//        table = new JTable(tableModel){
//            protected JTableHeader createDefaultTableHeader(){
//                return new GroupableTableHeader(columnModel);
//            }
//        };
//
//        TableColumnModel cm = table.getColumnModel();
//        header = (GroupableTableHeader)table.getTableHeader();
//        Int idx = new Int();
//        idx.i = 0;
//        buildColumnGroupStructure(root, header, null, cm, idx);
//
//        // creates scroll panel, add table to panel
//        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        // add panel to frame
//        jp.add(jsp);
//        return jp;
//    }
//
//    private JPanel getActionButtons(){
//        JPanel jp = new JPanel();
//        jp.setSize(300,300);
//        JButton btn_stats = new JButton("Statistics");
//        JButton btn_curve = new JButton("Curve");
//        JLabel l_search = new JLabel("Search");
//        JTextField tf_search = new JTextField("", 8);
//        jp.add(btn_stats);
//        jp.add(btn_curve);
//        jp.add(l_search);
//        jp.add(tf_search);
//        return jp;
//    }
//
//    private JPopupMenu getRowPopup(){
//        rowPopupMenu = new JPopupMenu();
//        menuItemAddRow = new JMenuItem("Add New Row");
//        menuItemRemoveRow = new JMenuItem("Remove Current Row");
//        menuItemRemoveAllRow = new JMenuItem("Remove All Rows");
//
//        rowPopupMenu.add(menuItemAddRow);
//        rowPopupMenu.add(menuItemRemoveRow);
//        rowPopupMenu.add(menuItemRemoveAllRow);
//
//        return rowPopupMenu;
//    }
//
//    private JPopupMenu getCellPopup(){
//        return null;
//    }
//
//    private JPopupMenu getHeaderPopup(){
//        headerPopupMenu = new JPopupMenu();
//        menuItemAddCol = new JMenuItem("Add New Column");
//        menuItemRemoveCol = new JMenuItem("Remove Current Column");
//
//        headerPopupMenu.add(menuItemAddCol);
//        headerPopupMenu.add(menuItemRemoveCol);
//
//        return headerPopupMenu;
//    }
//
//    private void addNewRow(){
//        String[] newRow = {"New Row"};
//        tableModel.addRow(newRow);
//    }
//
//    private void removeRow(){
//        int selectedRow = table.getSelectedRow();
//        tableModel.removeRow(selectedRow);
//    }
//
//    private void removeAllRows(){
//        int rowCount = tableModel.getRowCount();
//        for (int i = 0; i < rowCount; i++){
//            tableModel.removeRow(0);
//        }
//    }
//
//    private void addNewCol(){
//        String newCol = JOptionPane.showInputDialog("Please input column name");
//        tableModel.addColumn(newCol);
//    }
//
//    private void removeCol(){
//        int colIndex = table.getSelectedColumn();
//        int colCount = table.getSelectedColumnCount();
//        TableColumn selectedCol;
//        if(colIndex > 0){
//            for (int i = 0; i < colCount; i++){
//                selectedCol = table.getColumnModel().getColumn(colIndex);
//                table.removeColumn(selectedCol);
//            }
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JMenuItem menu = (JMenuItem) e.getSource();
//        if(menu == menuItemAddRow){
//            addNewRow();
//        } else if (menu == menuItemRemoveRow){
//            removeRow();
//        } else if (menu == menuItemRemoveAllRow){
//            removeAllRows();
//        } else if (menu == menuItemAddCol){
//            addNewCol();
//        } else if (menu == menuItemRemoveCol){
//            removeCol();
//        }
//    }
//
//    static protected void buildColumnGroupStructure(Component root,
//                                                    GroupableTableHeader header,
//                                                    ColumnGroup parentGroup,
//                                                    TableColumnModel columnModel,
//                                                    Int idx) {
//        if (!root.children.isEmpty()) {
//            ColumnGroup group = new ColumnGroup(root.name);
//            if (parentGroup == null) {
//                header.addColumnGroup(group);
//            } else {
//                parentGroup.add(group);
//            }
//            for (Entry<Integer, Component> entry : root.children.entrySet()) {
//                buildColumnGroupStructure(entry.getValue(), header, group, columnModel, idx);
//            }
//        } else {
//            parentGroup.add(columnModel.getColumn(idx.i++));
//        }
//    }
//
//    static protected void buildTableHeader(Component root, ArrayList<String> header) {
//        if (root.children.isEmpty()) {
//            header.add(root.name);
//        } else {
//            for (Entry<Integer, Component> entry : root.children.entrySet()) {
//                buildTableHeader(entry.getValue(), header);
//            }
//        }
//    }
//
//   public static void main(String[] args) {
//        Component root = Component.buildTestComponent();
//        ArrayList<String> header = new ArrayList<String>();
//        buildTableHeader(root, header);
//        for (String s : header) {
//            System.out.println(s);
//        }
//    }
//
//    private class Int {
//        public int i;
//    }
}