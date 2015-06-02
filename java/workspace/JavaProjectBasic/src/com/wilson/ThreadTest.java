package com.wilson;

public class ThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Sheep demo = new Sheep();
		//demo.run(); //ok,����û�в���Ч�������߳����н�����ִ�е�����Ĵ���
		demo.start(); //ok
		

		Pig pig = new Pig();
		Thread t = new Thread(pig);
		Monkey monkey = new Monkey();
		Thread t2 = new Thread(monkey);
		t.start();
		t2.start();

	}

}

class Monkey implements Runnable
{
	int times = 0;
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("monkey thread running,times:"+times);
			times++;
			if(times >= 10)
			{
				break;
			}
		}
	}
}

class Pig implements Runnable
{
	int times = 0;
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("pig thread running,times:"+times);
			times++;
			if(times >= 10)
			{
				break;
			}
		}
	}
}

class Sheep extends Thread
{
	int times = 0;
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("sheep thread running,times:"+times);
			times++;
			if(times >= 10)
			{
				break;
			}
		}
	}
}

