package com.wilson;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.*;


public class TextAreaTest extends JFrame{

	JPanel jp1, jp2;
	JTextArea jta;
	JScrollPane jsp;
	JComboBox jcb;
	JTextField jtf;
	JButton jb;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextAreaTest demo = new TextAreaTest();
	}

	public TextAreaTest()
	{

		jp1 = new JPanel();
		jp2 = new JPanel();
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		String []guys = {"xiaoxue", "xiao", "xue"};
		jcb = new JComboBox(guys);
		jtf = new JTextField(10);
		jb = new JButton("send");
		
		jp2.add(jcb);
		jp2.add(jtf);
		jp2.add(jb);
		
		//this.add(jta);
		this.add(jsp);
		this.add(jp2,BorderLayout.SOUTH);
		

		this.setIconImage(new ImageIcon("images/icon.png").getImage());
		
		this.setLocation(400, 200);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
