package main.java;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * 数据列表模块
 *
 */
public class Smess extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	JButton deleteButton = new JButton("删除");
	JButton editButton = new JButton("编辑");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Smess frame = new Smess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Smess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 600, 600);
		contentPane.add(scrollPane);

		table = getTable();
		FitTableColumns(table);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setRowHeight(23);
			String[] columns = { "姓名", "账号", "电话", "工作单位", "家庭住址", "操作" };
			MyModel model = new MyModel(columns, 0);
			List<Department> students = getstudents();
			for (Department department : students) {
				String[] args = { department.getName(), department.getAccount(), department.getPhone(),
						department.getJobAddr(), department.getHomeAddr() };
				model.addRow(args);
			}
			table.setModel(model);
			ActionPanelEditorRenderer er = new ActionPanelEditorRenderer(deleteButton, editButton);
			TableColumn column = table.getColumnModel().getColumn(5);
			column.setCellRenderer(er);
			column.setCellEditor(er);

			table.setRowSelectionAllowed(false);// 设置按钮点击整行选中
			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int judge = JOptionPane.showConfirmDialog(contentPane, "");
					if (judge == JOptionPane.OK_OPTION) {
						int count = table.getSelectedRow();// 获取你选中的行号（记录）
						String username = table.getValueAt(count, 1).toString();// 读取你获取行号的某一列的值（也就是字段）
						Jdbc j = new Jdbc();
						j.delete(username);// 删除数据库中的数据
						dispose();
						Smess frame = new Smess();
						frame.setVisible(true);
					}
				}
			});
			editButton.addActionListener(new Edit(table,this));
		}

		return table;
	}

	private List<Department> getstudents() {
		Jdbc d = new Jdbc();
		List<Department> allList = d.getAllList();
		return allList;
	}

	public void FitTableColumns(JTable myTable) {
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) myTable.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // 此行很重要
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}
};

class ActionPanelEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel2 = new JPanel();

	public ActionPanelEditorRenderer(JButton deleteButton, JButton editButton) {
		super();
		panel2.add(deleteButton);
		panel2.add(editButton);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		panel2.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
		panel2.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
		return panel2;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		panel2.setBackground(table.getSelectionBackground());
		return panel2;
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}
}
