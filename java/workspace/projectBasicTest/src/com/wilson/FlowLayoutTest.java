package com.wilson;
import java.awt.*;

import javax.swing.*;

public class FlowLayoutTest extends JFrame{
	JButton jb1, jb2, jb3, jb4, jb5;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FlowLayoutTest demo = new FlowLayoutTest();
	}

	public FlowLayoutTest()
	{
		jb1 = new JButton("mid");
		jb2 = new JButton("north");
		jb3 = new JButton("east");
		jb4 = new JButton("south");
		jb5 = new JButton("west");
		
		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);
		this.add(jb5);
		
		//设置布局管理器
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setResizable(false);
		
		this.setTitle("hello,BorderLayout");
		this.setSize(300, 300);
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
