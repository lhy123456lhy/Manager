package main.java;

import javax.swing.table.DefaultTableModel;

public class MyModel extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -36930130970727339L;

	@Override
    public boolean isCellEditable(int row, int column) {
        // 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。
        if (column > 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
	
	public MyModel(Object[] columnNames, int rowCount) {
        super(convertToVector(columnNames), rowCount);
    }
}
