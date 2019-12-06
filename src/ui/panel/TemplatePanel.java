package ui.panel;

import ui.UIConsts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemplatePanel extends JPanel implements ActionListener {

    private GroupableTableHeader header;
    private JTable table;
    private JScrollPane jsp;
    private DefaultTableModel tableModel;

    private JPopupMenu rowPopupMenu;
    private JPopupMenu headerPopupMenu;
    private JPopupMenu cellPopupMenu;

    private JMenuItem menuItemAddRow;
    private JMenuItem menuItemRemoveRow;
    private JMenuItem menuItemRemoveAllRow;

    private JMenuItem menuItemAddCol;
    private JMenuItem menuItemRemoveCol;

    private Object[][] tableData;
    private Object[] tableHeader;

    public TemplatePanel(Object[][] tableData, Object[] tableHeader) {
        super(true);
        initialize(tableData, tableHeader);
        addComponent();
        setContent();
        addListener();
    }

    private void initialize(Object[][] tableData, Object[] tableHeader) {
        this.tableData = tableData;
        this.tableHeader = tableHeader;
    }

    private void addComponent() {
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(getMainTable(), BorderLayout.NORTH);
        jp.add(getActionButtons(), BorderLayout.SOUTH);
        add(jp);
    }

    private void setContent() {
        table.setComponentPopupMenu(getRowPopup());
        table.setCellSelectionEnabled(true);
        table.setRowSelectionAllowed(true);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);

        header.setComponentPopupMenu(getHeaderPopup());

        jsp.setPreferredSize(new Dimension(UIConsts.TABLE_WIDTH, UIConsts.TABLE_HEIGHT));
    }

    private void addListener() {
        menuItemAddRow.addActionListener(this);
        menuItemRemoveRow.addActionListener(this);
        menuItemRemoveAllRow.addActionListener(this);

        menuItemAddCol.addActionListener(this);
        menuItemRemoveCol.addActionListener(this);
    }

    // !! hard coded table !!
    private JPanel getMainTable(){
        JPanel jp = new JPanel();
        // creates table
        tableModel = new DefaultTableModel();
        tableModel.setDataVector(tableData, tableHeader);
        table = new JTable(tableModel){
            protected JTableHeader createDefaultTableHeader(){
                return new GroupableTableHeader(columnModel);
            }
        };

        TableColumnModel cm = table.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("Name");

        ColumnGroup g_hw = new ColumnGroup("Homework");

        ColumnGroup g_hw1 = new ColumnGroup("hw1");
        g_hw1.add(cm.getColumn(1));
        g_hw1.add(cm.getColumn(2));

        ColumnGroup g_hw2 = new ColumnGroup("hw2");
        g_hw2.add(cm.getColumn(3));
        g_hw2.add(cm.getColumn(4));

        ColumnGroup g_hw3 = new ColumnGroup("hw3");
        g_hw3.add(cm.getColumn(5));

        ColumnGroup g_hw4 = new ColumnGroup("hw4");
        g_hw4.add(cm.getColumn(6));

        g_hw.add(g_hw1);
        g_hw.add(g_hw2);
        g_hw.add(g_hw3);
        g_hw.add(g_hw4);

        ColumnGroup g_midterm = new ColumnGroup("Midterm");
        g_midterm.add(cm.getColumn(7));
        g_midterm.add(cm.getColumn(8));

        ColumnGroup g_final = new ColumnGroup("Final");
        g_final.add(cm.getColumn(9));

        header = (GroupableTableHeader) table.getTableHeader();
        header.addColumnGroup(g_name);
        header.addColumnGroup(g_hw);
        header.addColumnGroup(g_midterm);
        header.addColumnGroup(g_final);

        // creates scroll panel, add table to panel
        jsp = new JScrollPane(table);

        // add panel to frame
        jp.add(jsp);
        return jp;
    }

    private JPanel getActionButtons(){
        JPanel jp = new JPanel();
        jp.setSize(300,300);
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