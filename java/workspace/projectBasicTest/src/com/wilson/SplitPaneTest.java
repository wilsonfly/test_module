package com.wilson;

import java.awt.*;
import javax.swing.*;

public class SplitPaneTest extends JFrame{

	JSplitPane jsp;
	JList jlist;
	JLabel jl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplitPaneTest demo = new SplitPaneTest();
	}

	public SplitPaneTest()
	{
		String []words = {"boy", "girl", "bird"};
		jlist = new JList(words);
		jl = new JLabel(new ImageIcon("images/win7.png"));
		
		//��ִ���
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jlist,jl);
		jsp.setOneTouchExpandable(true);
		
		//���ò��ֹ�����
		//this.setLayout(new BorderLayout());
		
		//������
		this.add(jsp);
		
		//���ô�С����ʾ
		this.setLocation(400, 200);
		this.setSize(700,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
}
