package ui.panel;

import javax.swing.*;

public class TablePanel extends JPanel {

    private Object[][] tableData = new Object[][]{
            {"Fuqing Wang", "99","98","96","54","20","98","96","54","20","1"},
            {"Xiaoduan Chang", "96","94","98","54","20","98","96","54","20","2"},
            {"Zhezhong Jiang", "97","98","99","54","20","98","96","54","20","3"},
            {"Shoumik", "99","98","96","51","21","98","96","54","20","4"}};
    private Object[] tableHeader = new Object[]{"Name","TTT-I", "TTT-II", "BlackJack-I", "BlackJack-II", "Trianta-ena", "Cave Adventure", "Midterm-Written", "Midterm-Code", "Final"};


    public TablePanel(){
        super(true);
        addTable();
    }

    public void addTable(){
        add(new GTable(null, null));
    }
}
