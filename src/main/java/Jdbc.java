package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Jdbc {
	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test";
	String name = "root";
	String passwd = "root";
	public  Jdbc() {
		try {
			Class.forName(driver).newInstance(); //????
			conn = DriverManager.getConnection(url, name, passwd);
			stat = conn.createStatement();
			
		} catch (ClassNotFoundException  e) {
			// TODO: handle exception
			System.out.println("找不到这个驱动！");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//删除用户
	public void delete(String username) {
		String sql ="delete from user where account !='admin' and account =\""+username+
				"\"";
		try {
			stat.executeUpdate(sql);
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "该用户不存在！");
			e.printStackTrace();
		}
	}
	//添加用户，注册功能
	public void insert(String name,String account,String password,String phone,String jobAddr,String homeAddr) {
			String sql = "insert into user(name,account,password,phone,jobAddr,homeAddr) value(\""+
					name+"\",\""+account+"\",\""+password+"\",\""+phone+"\",\""+ jobAddr + "\",\"" + homeAddr +"\")";
			try {
				int a = stat.executeUpdate(sql);
				conn.close();
				stat.close();
				if(a==1) {
					JOptionPane.showMessageDialog(null, "注册成功！");
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "对不起，该用户名已存在！");
				e.printStackTrace();
			}
	}
	
		//添加用户，注册功能
		public void update(String name,String account,String password,String phone,String jobAddr,String homeAddr
				,String nameOld,String accountOld) {
			String sql = "update user set name='"+name+ "',account='"+ account+"',password='"+ password+
					"',phone='"+ phone+"',jobAddr='"+ jobAddr+"',homeAddr='"+ homeAddr+
					"'where name='"+nameOld+"'and account='"+accountOld+"'";
			try {
				int a = stat.executeUpdate(sql);
				conn.close();
				stat.close();
				if(a==1) {
					JOptionPane.showMessageDialog(null, "编辑成功！");
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "程序报错了");
				e.printStackTrace();
			}
		}
	
	public boolean compare(String name,String pass) {
		boolean m = false;
		String sql = "select password from user where account='"+name+"'";
		try {
			res = stat.executeQuery(sql); //输出单个结果集。
			if(res.next()) {			//????
				String ps = res.getString(1); //？？？
				if(ps.equals(pass)) {
					m = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "密码错误！");
				}
			}else {
				JOptionPane.showMessageDialog(null, "账户不存在！");
			}
			res.close();
			conn.close();
			stat.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * 判断账号是否存在
	 * @param account
	 * @return
	 */
	public boolean have(String account) {
		boolean m = false;
		String sql = "select count(0) as count from user where account=\""+account+"\"";
		try {
			res = stat.executeQuery(sql); //输出单个结果集。
			if(res.next()) {
				int count = res.getInt("count");
				if(count > 0) {
					return true;
				}
			}
			res.close();
			conn.close();
			stat.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return m;
	}
	
	public List<Department> getAllList() {
		List<Department> listDept = new ArrayList<Department>();
		String sql = "select name,account,password,phone,jobAddr,homeAddr from user where account != 'admin'";
		try {
			res = stat.executeQuery(sql); //输出单个结果集。
			Department dept = null;
			while (res.next()) {
				dept = new Department();
				dept.setName(res.getString("name"));
				dept.setAccount(res.getString("account"));
				dept.setPassword(res.getString("password"));
				dept.setPhone(res.getString("phone"));
				dept.setJobAddr(res.getString("jobAddr"));
				dept.setHomeAddr(res.getString("homeAddr"));
				listDept.add(dept);
			}
			res.close();
			conn.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDept;
	}
}
