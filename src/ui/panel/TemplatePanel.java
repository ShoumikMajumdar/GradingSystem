package ui.panel;

import ui.UIConsts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemplatePanel extends JPanel implements ActionListener {

    private JTableHeader header;
    private JTable table;
    private DefaultTableModel tableModel;

    private JPopupMenu rowPopupMenu;
    private JPopupMenu headerPopupMenu;
    private JPopupMenu cellPopupMenu;

    private JMenuItem menuItemAddRow;
    private JMenuItem menuItemRemoveRow;
    private JMenuItem menuItemRemoveAllRow;

    private JMenuItem menuItemAddCol;
    private JMenuItem menuItemRemoveCol;

    private String[][] tableData;
    private String[] tableHeader;

    public TemplatePanel() {
        super(true);
        initialize();
        addComponent();
        setContent();
        addListener();
    }

    private void initialize() {
        tableData = new String[][]{{"Fuqing Wang", "99","98","96","54","20"},
                {"Xiaoduan Chang", "96","94","98","54","20"},
                {"Zhezhong Jiang", "97","98","99","54","20"},
                {"Shoumik", "99","98","96","51","21"}};
        tableHeader = new String[]{"Name", "Assignment 1", "Assignment2", "Assignment 3", "Midterm", "Final"};
    }

    private void addComponent() {
        add(getMainTable(), BorderLayout.CENTER);
//        add(getActionButtons(), BorderLayout.CENTER);
    }

    private void setContent() {
        
    }

    private void addListener() {
//        table.addMouseListener(new TableMouseListener(table));

        menuItemAddRow.addActionListener(this);
        menuItemRemoveRow.addActionListener(this);
        menuItemRemoveAllRow.addActionListener(this);

        menuItemAddCol.addActionListener(this);
        menuItemRemoveCol.addActionListener(this);

//        table.addMouseListener(new HeaderMouseListener(table));
    }

    private JPanel getMainTable(){
        JPanel jp = new JPanel();
        // creates table
        tableModel = new DefaultTableModel(tableData, tableHeader);
        table = new JTable(tableModel);
        table.setBounds(UIConsts.MAIN_WINDOW_X, UIConsts.MAIN_WINDOW_Y,
                UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT);
        table.setComponentPopupMenu(getRowPopup());
        table.setCellSelectionEnabled(true);
        table.setRowSelectionAllowed(true);

        header = table.getTableHeader();
        header.setComponentPopupMenu(getHeaderPopup());

        // creates scroll panel, add table to panel
        JScrollPane jsp = new JScrollPane(table);

        // add panel to frame
        jp.add(jsp);
        jp.setSize(UIConsts.MAIN_WINDOW_X,UIConsts.MAIN_WINDOW_Y);
        return jp;
    }

    private JPanel getActionButtons(){
        JPanel jp = new JPanel();
        JButton btn_stats = new JButton("Statistics");
        JButton btn_curve = new JButton("Curve");
        JLabel l_search = new JLabel("Search");
        JTextField tf_search = new JTextField("", 8);
        jp.add(btn_stats);
        jp.add(btn_curve);
        jp.add(l_search);
        jp.add(tf_search);
        return jp;
    }

    private JPopupMenu getRowPopup(){
        rowPopupMenu = new JPopupMenu();
        menuItemAddRow = new JMenuItem("Add New Row");
        menuItemRemoveRow = new JMenuItem("Remove Current Row");
        menuItemRemoveAllRow = new JMenuItem("Remove All Rows");

        rowPopupMenu.add(menuItemAddRow);
        rowPopupMenu.add(menuItemRemoveRow);
        rowPopupMenu.add(menuItemRemoveAllRow);

        return rowPopupMenu;
    }

    private JPopupMenu getCellPopup(){
        return null;
    }

    private JPopupMenu getHeaderPopup(){
        headerPopupMenu = new JPopupMenu();
        menuItemAddCol = new JMenuItem("Add New Column");
        menuItemRemoveCol = new JMenuItem("Remove Current Column");

        headerPopupMenu.add(menuItemAddCol);
        headerPopupMenu.add(menuItemRemoveCol);

        return headerPopupMenu;
    }

    private void addNewRow(){
        String[] newRow = {"New Row"};
        tableModel.addRow(newRow);
    }

    private void removeRow(){
        int selectedRow = table.getSelectedRow();
        tableModel.removeRow(selectedRow);
    }

    private void removeAllRows(){
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++){
            tableModel.removeRow(0);
        }
    }

    private void addNewCol(){
        String newCol = JOptionPane.showInputDialog("Please input column name");
        tableModel.addColumn(newCol);
    }

    private void removeCol(){
        int colIndex = table.getSelectedColumn();
        int colCount = table.getSelectedColumnCount();
        TableColumn selectedCol;
        if(colIndex > 0){
            for (int i = 0; i < colCount; i++){
                selectedCol = table.getColumnModel().getColumn(colIndex);
                table.removeColumn(selectedCol);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menu = (JMenuItem) e.getSource();
        if(menu == menuItemAddRow){
            addNewRow();
        } else if (menu == menuItemRemoveRow){
            removeRow();
        } else if (menu == menuItemRemoveAllRow){
            removeAllRows();
        } else if (menu == menuItemAddCol){
            addNewCol();
        } else if (menu == menuItemRemoveCol){
            removeCol();
        }
    }
}