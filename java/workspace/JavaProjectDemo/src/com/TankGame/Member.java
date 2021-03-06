package com.TankGame;

import java.util.Vector;

import com.TankGame.MyTankGame.MoveDirect;


class Tank {
	// 坦克坐标
	int x = 0, y = 0;
	// 坦克方向，0表示向上，1表示向右，2表示向下，3表示向左
	MoveDirect direct = MoveDirect.UP;
	// 坦克速度
	int speed = 1;

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

	public Tank(int x, int y) {
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

class Hero extends Tank {
	int windowWeight, windowHight;
	Bullet bullet = null;
	Vector<Bullet> vbullet = new Vector<Bullet>();

	/*
	 * Vector<Bullet> vbulletUp = new Vector<Bullet>(); Vector<Bullet>
	 * vbulletDown = new Vector<Bullet>(); Vector<Bullet> vbulletLeft = new
	 * Vector<Bullet>(); Vector<Bullet> vbulletRight = new Vector<Bullet>();
	 */
	// Vector<Bullet> []vbullet = new []Vector<Bullet>();

	public Hero(int x, int y, int weight, int hight) {
		super(x, y);
		this.windowWeight = weight;
		this.windowHight = hight;
	}

	public void shot() {
		switch (this.direct) {
		case UP:
			// 创建一颗子弹，然后放入向量
			bullet = new Bullet(x + 10, y, windowWeight, windowHight,
					MoveDirect.UP);
			// vbulletUp.add(bullet);
			vbullet.add(bullet);
			break;
		case DOWN:
			bullet = new Bullet(x + 10, y + 30, windowWeight, windowHight,
					MoveDirect.DOWN);
			// vbulletDown.add(bullet);
			vbullet.add(bullet);
			break;
		case LEFT:
			bullet = new Bullet(x, y + 10, windowWeight, windowHight,
					MoveDirect.LEFT);
			// vbulletLeft.add(bullet);
			vbullet.add(bullet);
			break;
		case RIGHT:
			bullet = new Bullet(x + 30, y + 10, windowWeight, windowHight,
					MoveDirect.RIGHT);
			// vbulletRight.add(bullet);
			vbullet.add(bullet);
			break;
		}

		Thread t = new Thread(bullet);
		t.start();

	}

	public void moveUp() {
		y -= speed;
		if (y < 0)
			y = 0;
	}

	public void moveDown() {
		y += speed;
		if (y + 30 > windowHight)
			y = windowHight - 30;
	}

	public void moveLeft() {
		x -= speed;
		if (x < 0)
			x = 0;
	}

	public void moveRight() {
		x += speed;
		if (x + 30 > windowWeight)
			x = windowWeight - 30;
	}
}

class Bullet implements Runnable {
	int x, y;
	int speed = 2;
	MoveDirect direct = MoveDirect.UP;
	boolean isLive = true;
	int windowWeight, windowHight;

	public Bullet(int x, int y, int weight, int hight, MoveDirect direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
		this.windowWeight = weight;
		this.windowHight = hight;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(Thread.currentThread().getName()
					+ " is running, bullet pos x:" + x + " y:" + y);

			switch (direct) {
			case UP:
				y -= speed;
				break;
			case DOWN:
				y += speed;
				break;
			case LEFT:
				x -= speed;
				break;
			case RIGHT:
				x += speed;
				break;

			default:
				break;
			}

			// 判断该子弹是否碰到边缘.
			if (x < 0 || x > windowWeight || y < 0 || y > windowHight) {
				System.out.println("quit now");
				this.isLive = false;
				break;
			}
		}
	}

}