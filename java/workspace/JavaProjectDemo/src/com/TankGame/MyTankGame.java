package com.TankGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import com.TankGame.MyTankGame.MoveDirect;


public class MyTankGame extends JFrame {

	MyPanel mp = null;
	int windowWeight = 400;
	int windowHight = 300;

	public static void main(String[] args) {

		MyTankGame mtg = new MyTankGame();

	}

	public MyTankGame() {
		mp = new MyPanel(windowWeight, windowHight);
		Thread t = new Thread(mp);
		t.start();

		this.add(mp);
		this.addKeyListener(mp);

		this.setSize(windowWeight, windowHight);
		this.setVisible(true);
	}

	enum MoveDirect {
		UP, DOWN, LEFT, RIGHT
	}

}

class MyPanel extends JPanel implements KeyListener, Runnable {
	int windowWeight, windowHight;
	Hero hero = null;

	public MyPanel(int weight, int hight) {
		this.windowWeight = weight;
		this.windowHight = hight;
		hero = new Hero(100, 10, windowWeight, windowHight);
	}

	public void paint(Graphics g) {
		super.paint(g);

		// 填充背景为黑色
		g.fillRect(0, 0, 400, 300);

		this.drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);

		// this.drawTank(hero.getX(), hero.getY(), g, MoveDirect.UP, 1);

		/*
		 * int bulletSize = this.hero.vbulletUp.size() +
		 * this.hero.vbulletDown.size() + this.hero.vbulletLeft.size() +
		 * this.hero.vbulletRight.size();
		 */
		for (int i = 0; i < this.hero.vbullet.size(); i++) {
			Bullet abullet = hero.vbullet.get(i);
			// 画出一颗子弹
			if (abullet != null && abullet.isLive == true) {
				g.draw3DRect(abullet.x, abullet.y, 1, 1, false);
			}

			if (abullet.isLive == false) {
				this.hero.vbullet.remove(abullet);
			}
		}

	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			this.repaint();
		}

	}

	public void drawTank(int x, int y, Graphics g, MoveDirect direct, int type) {
		// 画出坦克

		switch (type) {
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.yellow);
		default:
			break;
		}

		switch (direct) {
		case UP:
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			g.fillOval(x + 4, y + 10, 10, 10);
			g.drawLine(x + 10, y, x + 10, y + 15);
			break;
		case DOWN:
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			g.fillOval(x + 4, y + 10, 10, 10);
			g.drawLine(x + 10, y + 15, x + 10, y + 30);
			break;
		case LEFT:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			g.fillOval(x + 10, y + 4, 10, 10);
			g.drawLine(x, y + 10, x + 15, y + 10);
			break;
		case RIGHT:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			g.fillOval(x + 10, y + 4, 10, 10);
			g.drawLine(x + 15, y + 10, x + 30, y + 10);

			break;
		default:
			break;
		}

	}

	// 键盘事件处理接口
	// a表示向左，w表示向上，d表示向右，s表示向下
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_W) {
			this.hero.setDirect(MoveDirect.UP);
			this.hero.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			this.hero.setDirect(MoveDirect.DOWN);
			this.hero.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			this.hero.setDirect(MoveDirect.LEFT);
			this.hero.moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			this.hero.setDirect(MoveDirect.RIGHT);
			this.hero.moveRight();
		}

		// 判断是否按下J键
		if (e.getKeyCode() == KeyEvent.VK_J) {
			/*
			 * //控制连发自动数目 if(this.hero.vbullet.size()<=5) { this.hero.shot(); }
			 */
			this.hero.shot();

		}

		// 重新绘制
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
