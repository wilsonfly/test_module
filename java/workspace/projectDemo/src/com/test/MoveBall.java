/*
 * �����������Ҽ�����С��λ���ƶ�
 */
package com.test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class MoveBall extends JFrame implements ActionListener {

	MyPanelBall mp = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MoveBall demo = new MoveBall();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public MoveBall() {
		mp = new MyPanelBall();

		this.add(mp);

		this.addKeyListener(mp);
		this.addMouseListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);

		this.setSize(300, 200);
		this.setVisible(true);
	}
}

class MyPanelBall extends JPanel implements KeyListener,MouseListener,MouseMotionListener,WindowListener{
	int x = 0;
	int y = 0;

	public void paint(Graphics g) {
		super.paint(g);
		g.fillOval(x, y, 10, 10);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key down" + e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			y += 2;
			//??????????û���ܹ��������³���
			if (y > this.getWidth())
				y = this.getWidth();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			y -= 2;
			if (y < 0)
				y = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= 2;
			if (x < 0)
				x = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += 2;
			//??????????û���ܹ��������ҳ���
			if (x > this.getHeight())
				x = this.getHeight();
		}

		// ���»���
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key up");

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key input:"+e.getKeyCode());

	}

	//mouse��һϵ�нӿ�
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse click,x="+e.getX()+" y="+e.getY());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse entered!");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse exited!");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//mousemotion �������ӿ�
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse now: x="+e.getX()+" y="+e.getY());
	}

	//window��һЩ�нӿ�
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("window windowActivated!!!");

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("window closed!!!");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("window windowDeactivated!!!");

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
