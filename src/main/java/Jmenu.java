package main.java;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * 
 *菜单模块
 */
public class Jmenu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar mBar;
	private JMenu fileMenu;
	private JMenu setMenu;
	private JMenuItem miExit;
	
	private JMenuItem regitMenu;//注册的菜单
	private JMenuItem listMenu;//列表的菜单
	public Jmenu(){
		super("同学录管理系统");
		setBounds(400, 200, 400, 270);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		
		//面板挂菜单栏
		mBar = new JMenuBar();
		setJMenuBar(mBar);
		
		//菜单栏挂菜单
		fileMenu = new JMenu("信息管理");
		setMenu = new JMenu("设置");
		
		mBar.add(setMenu);
		mBar.add(fileMenu);
		
		//菜单挂菜单项
		miExit = new JMenuItem("退出");
		regitMenu = new JMenuItem("用户注册");
		listMenu = new JMenuItem("用户列表");
		
		setMenu.addSeparator();//分隔条
		setMenu.add(miExit);
		
		fileMenu.addSeparator();//分隔条
		fileMenu.add(regitMenu);
		fileMenu.addSeparator();//分隔条
		fileMenu.add(listMenu);
		
		miExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int judge = JOptionPane.showConfirmDialog(Jmenu.this, "确认退出？");
				if(judge == JOptionPane.OK_OPTION){
					System.exit(0);
				}
			}
		});
		regitMenu.addActionListener(new Regist());
		listMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Smess frame = new Smess();
				frame.setVisible(true);
			}
		});
		setVisible(true);
	}
}
