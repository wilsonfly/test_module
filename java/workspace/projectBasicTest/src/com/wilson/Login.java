package com.wilson;

import javax.swing.JFrame;
import javax.swing.JPasswordField;

import java.awt.*;

import javax.swing.*;

public class Login extends JFrame{

	JPanel jp1, jp2, jp3;
	JLabel jlb1, jlb2;
	JButton jb1, jb2;
	JTextField jtf1, jtf2;
	JPasswordField jpwd;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Login  demo = new Login();
	}
	
	public Login()
	{
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		jlb1 = new JLabel("Ãû×Ö");
		jlb2 = new JLabel("ÃÜÂë");
		jb1 = new JButton("login");
		jb2 = new JButton("cancel");
		jtf1 = new JTextField(10);
		jpwd = new JPasswordField(10);

		this.setLayout(new GridLayout(3, 1));
		
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2.add(jlb2);
		jp2.add(jpwd);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setTitle("Hello");
		this.setLocation(500, 300);
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}
