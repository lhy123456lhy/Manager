package main.java;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 注册模块
 *
 */
public class Regist implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame f2 = new JFrame();
		f2.setTitle("同学录管理系统");
		f2.setBounds(400, 200, 400, 400);
		Container conn = f2.getContentPane(); //生成一个容器
		conn.setLayout(new GridLayout(9,1));  //设置容器布局
		f2.invalidate();
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//实例化各控件
		JPanel f2p0 = new JPanel();
		JPanel f2p1 = new JPanel();
		JPanel f2p2 = new JPanel();
		JPanel f2p3 = new JPanel();
		JPanel f2p4 = new JPanel();
		JPanel f2p5 = new JPanel();
		JPanel f2p6 = new JPanel();
		JPanel f2p7 = new JPanel();
		JPanel f2p8 = new JPanel();
		JLabel f2l1 = new JLabel("欢迎注册！");
		JLabel f2l0 = new JLabel(" 姓     名：");
		JLabel f2l2 = new JLabel(" 账     号：");
		JLabel f2l3 = new JLabel(" 密     码：");
		JLabel f2l4 = new JLabel("确认密码：");
		JLabel f2l5 = new JLabel("电	    话：");
		JLabel f2l6 = new JLabel("工作单位：");
		JLabel f2l7	= new JLabel("家庭住址：");
		TextField f2j6 = new TextField(15);
		
		JButton f2b1 = new JButton("注册");
		TextField f2j0 = new TextField(15);
		TextField f2j1 = new TextField(15);
		TextField f2j2 = new TextField(15);
		f2j2.setEchoChar('*');
		TextField f2j3 = new TextField(15);
		f2j3.setEchoChar('*');
		TextField f2j4 = new TextField(15);
		TextField f2j5 = new TextField(15);
		
		f2l1.setFont(new Font("宋体", Font.BOLD, 20));
		f2p1.add(f2l1);
		
		f2p0.add(f2l0);
		f2p0.add(f2j0);
		
		f2p2.add(f2l2);
		f2p2.add(f2j1);
		
		f2p3.add(f2l3);
		f2p3.add(f2j2);
		
		f2p4.add(f2l4);
		f2p4.add(f2j3);
		//f2j3.addFocusListener(new jiance(f2j2,f2j3));
		f2p5.add(f2l5);
		f2p5.add(f2j4);
		
		f2p6.add(f2l6);
		f2p6.add(f2j5);
		
		f2p7.add(f2l7);
		f2p7.add(f2j6);
		
		f2p8.add(f2b1);
		f2b1.addActionListener(new ActionListener() {//注册的按钮点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = f2j0.getText();//姓名
				String account = f2j1.getText();//账号
				String password1 = f2j2.getText();//密码
				String password2 = f2j3.getText();//确认密码
				String phone = f2j4.getText();//电话
				String jobAddr = f2j5.getText();//工作单位
				String homeAddr = f2j6.getText();//家庭住址
				if(name == null || name.equals("")) {
					JOptionPane.showMessageDialog(null, "姓名不能为空");
				}
				if(account == null || account.equals("")) {
					JOptionPane.showMessageDialog(null, "账号不能为空");
				}
				if(password1 == null || password1.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空");
				}
				if(password2 == null || password2.equals("")) {
					JOptionPane.showMessageDialog(null, "确认密码不能为空");
				}
				if(!password1.equals(password2)) {
					JOptionPane.showMessageDialog(null, "两次密码不相同");
				}
				Jdbc d1 = new Jdbc();
				if(!d1.have(account)) {
					Jdbc d2 = new Jdbc();
					d2.insert(name, account, password1, phone, jobAddr, homeAddr);
				}else {
					JOptionPane.showMessageDialog(null, "账号已经存在");
				}
				f2.dispose();
			}
		});
		conn.add(f2p1);
		conn.add(f2p0);
		conn.add(f2p2);
		conn.add(f2p3);
		conn.add(f2p4);
		conn.add(f2p5);
		conn.add(f2p6);
		conn.add(f2p7);
		conn.add(f2p8);
		f2.setVisible(true);
	}
}
