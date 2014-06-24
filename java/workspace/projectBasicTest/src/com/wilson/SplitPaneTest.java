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
		
		//拆分窗格
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jlist,jl);
		jsp.setOneTouchExpandable(true);
		
		//设置布局管理器
		//this.setLayout(new BorderLayout());
		
		//添加组件
		this.add(jsp);
		
		//设置大小及显示
		this.setLocation(400, 200);
		this.setSize(700,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
}
