/*
 * 事件处理机制
 */

package com.test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EventTest extends JFrame implements ActionListener {

	MyPanelEvent mp = null;
	JButton jb1 = null;
	JButton jb2 = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventTest demo = new EventTest();
	}

	public EventTest() {
		mp = new MyPanelEvent();
		jb1 = new JButton("black");
		jb2 = new JButton("red");

		this.add(jb1, BorderLayout.NORTH);
		mp.setBackground(Color.black);
		this.add(mp);
		this.add(jb2, BorderLayout.SOUTH);

		// 注册监听
		jb1.addActionListener(this);
		// 指定action命令
		jb1.setActionCommand("black_action");
		jb2.addActionListener(this);
		jb2.setActionCommand("red_action");

		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// 对事件处理的方法
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 判断事件
		if (e.getActionCommand().equals("black_action")) {
			System.out.println("click the black button");
			mp.setBackground(Color.black);
		} else if (e.getActionCommand().equals("red_action")) {
			System.out.println("click the red button");
			mp.setBackground(Color.red);
		} else
		{
			System.out.println("in my actionperformed");
		}

	}

}

class MyPanelEvent extends JPanel {
	public void paint(Graphics g) {
		super.paint(g);
		// g.setColor(Color.black);
	}
}