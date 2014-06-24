package com.wilson;

import java.awt.*;

import javax.swing.*;

public class GridLayoutTest extends JFrame{
	int size = 9;
	JButton jbs[] = new JButton[size];
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GridLayoutTest demo = new GridLayoutTest();
	}

	public GridLayoutTest()
	{
		for(int i=0;i<size;i++)
		{
			jbs[i] = new JButton(String.valueOf(i));
		}
		
		this.setLayout(new GridLayout(3, 3, 10, 10));
		
		for(int i=0;i<size;i++)
		{
			this.add(jbs[i]);
		}
		
		this.setTitle("hello,BorderLayout");
		this.setSize(300, 300);
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
