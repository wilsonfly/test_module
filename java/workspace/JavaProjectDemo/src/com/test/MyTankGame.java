package com.test;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import com.test.MyTankGame.MoveDirect;

public class MyTankGame extends JFrame{

	MyPanel mp = null;
	public static void main(String[] args) {

		MyTankGame mtg = new MyTankGame();
		
	}
	
	public MyTankGame()
	{
		mp = new MyPanel();
		
		this.add(mp);
		this.addKeyListener(mp);
		
		this.setSize(400,300);
		this.setVisible(true);
	}
	
	enum MoveDirect {
		UP,DOWN,LEFT,RIGHT
	}

}


class MyPanel extends JPanel implements KeyListener
{
	
	Hero hero = null;
	
	public MyPanel()
	{
		hero = new Hero(100,10);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);

		//��䱳��Ϊ��ɫ
		g.fillRect(0, 0, 400, 300);
		
		this.drawTank(hero.getX(), hero.getY(), g, 0, 1);


	}
	
	public void drawTank(int x, int y, Graphics g, int direct, int type)
	{
		//����̹��
		
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
		//����
		case 0:
			g.fill3DRect(hero.getX(), hero.getY(), 5, 30, false);
			g.fill3DRect(hero.getX()+15, hero.getY(), 5, 30, false);
			g.fill3DRect(hero.getX()+5, hero.getY()+5, 10, 20, false);
			g.fillOval(hero.getX()+4, hero.getY()+10, 10, 10);
			g.drawLine(hero.getX()+9, hero.getY(), hero.getX()+9, hero.getY()+15);
			break;

		default:
			break;
		}
		
		

	}

	//�����¼������ӿ�
	//a��ʾ����w��ʾ���ϣ�d��ʾ���ң�s��ʾ����
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			this.hero.setDirect( MoveDirect.UP);
			this.hero.moveUp();
		}else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			this.hero.setDirect( MoveDirect.DOWN);
			this.hero.moveDown();
		}else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			this.hero.setDirect( MoveDirect.LEFT);
			this.hero.moveLeft();
		}else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			this.hero.setDirect( MoveDirect.RIGHT);
			this.hero.moveRight();
		}
		
		//���»���
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

class Tank
{
	//̹������
	int x=0,y=0;
	//̹�˷���0��ʾ���ϣ�1��ʾ���ң�2��ʾ���£�3��ʾ����
	MoveDirect direct = MoveDirect.UP;
	//̹���ٶ�
	int speed=1;
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public MoveDirect getDirect() {
		return direct;
	}

	public void setDirect(MoveDirect direct) {
		this.direct = direct;
	}

	public Tank(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}

class Hero extends Tank
{

	public Hero(int x, int y)
	{
		super(x,y);
	}
	
	public void moveUp()
	{
		y-=speed;
	}
	public void moveDown()
	{
		y+=speed;
	}
	public void moveLeft()
	{
		x-=speed;
	}
	public void moveRight()
	{
		x+=speed;
	}
}