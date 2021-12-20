package main.java;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 登录模块，这是入口
 *
 */
public class Login {
	public static void main(String[] args) {
		JFrame f1 = new JFrame();
		f1.setTitle("同学录管理系统");
		f1.setBounds(400, 200, 400, 270);
		f1.setResizable(false);
		f1.invalidate(); // 保证组件是有效的布局
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 实例化各组件
		Container con = f1.getContentPane(); //生成一个容器
		con.setLayout(new GridLayout(7,1));  //设置容器布局
		JLabel welcome = new JLabel("同学录管理系统");
		JLabel usname = new JLabel();
		JLabel paswd = new JLabel();
		JTextField text1 = new JTextField(12);
		JPasswordField text2 = new JPasswordField(12);
		JButton login = new JButton("登陆");
		JButton reset = new JButton("重置");
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		//调用FocusListener方法，获取鼠标焦点，实现提示功能。
		text1.addFocusListener(new Tip(text1,"请输入账号：")); 
		text2.addFocusListener(new Tip(text2, "请输入密码："));
		welcome.setFont(new Font("宋体", Font.BOLD, 20));
		p1.add(welcome);
		con.add(p1); 
		usname.setText("账号：");
		paswd.setText("密码：");
		p2.add(usname);
		p2.add(text1);	
		con.add(p2); 
		p3.add(paswd);
		p3.add(text2);
		con.add(p3);
		p4.add(login);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Jdbc d = new Jdbc();
				String na = text1.getText();
				@SuppressWarnings("deprecation")
				String ps = text2.getText();
				if(e.getSource()==login) {
					if(na.equals("请输入账号：")||ps.equals("请输入密码：")) {
						JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
					}
					if(d.compare(na,ps)) {
						JOptionPane.showMessageDialog(null, "登陆成功");
						f1.dispose();//关闭登录页面
						new Jmenu();//打开菜单页面
					}
				}
			}
		});
		reset.addActionListener(new ActionListener() {//监听重置按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				text1.setText("");
				text2.setText("");
			}
		});
		p4.add(reset);
		con.add(p4);
		f1.setVisible(true);
	}
}
