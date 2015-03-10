package com.test;
import javax.swing.*;

import java.awt.*;

public class DrawTest1 extends JFrame{


	MyPanelTest mp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawTest1 demo = new DrawTest1();
	}

	public DrawTest1()
	{
		mp = new MyPanelTest();
		
		this.add(mp);
		
		this.setLocation(400, 200);
		this.setSize(700,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

class MyPanelTest extends JPanel
{
	//覆盖JPanel 的paint 类
	//Grahics 是灰兔的重要类，可以理解为一只画笔
	public void paint(Graphics g) {
		super.paint(g);
		g.drawOval(10, 10, 30, 30);
		g.drawLine(10, 10, 30, 40);
		g.draw3DRect(50, 50, 80, 90, true);
		
		g.setColor(Color.green);
		g.fillRect(150, 10, 30, 50);
		Image im = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/win7.png"));
		g.drawImage(im, 200, 100, 200, 150, this);
		
		g.setColor(Color.blue);
		g.setFont(new Font("隶书", Font.BOLD, 15));
		g.drawString("Hello World! 中华英雄", 10, 200);
	}
	
}