package com.wilson;

import java.awt.*;
import javax.swing.*;

public class BorderLayoutTest extends JFrame{

	JButton jb1, jb2, jb3, jb4, jb5;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BorderLayoutTest demo = new BorderLayoutTest();
		
	}
	
	public BorderLayoutTest()
	{
		jb1 = new JButton("mid");
		jb2 = new JButton("north");
		jb3 = new JButton("east");
		jb4 = new JButton("south");
		jb5 = new JButton("west");
		
		this.add(jb1,BorderLayout.CENTER);
		this.add(jb2,BorderLayout.NORTH);
		this.add(jb3,BorderLayout.EAST);
		this.add(jb4,BorderLayout.SOUTH);
		this.add(jb5,BorderLayout.WEST);
		
		this.setTitle("hello,BorderLayout");
		this.setSize(200, 200);
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
