package com.wilson;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingTest2 extends JFrame{

	JButton jb1 = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingTest2 demo = new SwingTest2();
	}

	public SwingTest2()
	{
		jb1 = new JButton("buttone 1");
		this.add(jb1);
		
		this.setTitle("hello,swing");
		this.setSize(200, 200);
		this.setLocation(new Point(200,100));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
