package com.wilson;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame jf = new JFrame();
		JButton jb1 = new JButton("buttone 1");
		
		jf.setTitle("hello,swing");
		jf.setSize(200, 200);
		jf.setLocation(new Point(200,100));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf.add(jb1);
		
		jf.setVisible(true);
	}

}
