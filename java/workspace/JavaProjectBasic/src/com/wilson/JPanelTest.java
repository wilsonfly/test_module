package com.wilson;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelTest extends JFrame{

	JPanel jp1, jp2;
	JButton jb1, jb2, jb3, jb4, jb5, jb6;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JPanelTest demo = new JPanelTest();
	}

	public JPanelTest ()
	{
		//创建组件
		jp1 = new JPanel();
		jp2 = new JPanel();
		
		jb1 = new JButton("melon");
		jb2 = new JButton("apple");
		jb3 = new JButton("lizhi");
		jb4 = new JButton("putao");
		jb5 = new JButton("orange");
		jb6 = new JButton("banana");
		
		//设置布局管理器
		
		//添加组件
		jp1.add(jb1);
		jp1.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(jb6,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);

		this.setTitle("hello");
		this.setSize(300, 200);
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
