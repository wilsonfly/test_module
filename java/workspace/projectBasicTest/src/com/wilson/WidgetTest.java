package com.wilson;

import java.awt.*;

import javax.swing.*;

public class WidgetTest extends JFrame{

	JPanel jp1, jp2, jp3, jp4, jp5;
	JLabel jlb1, jlb2, jlb3, jlb4;
	JButton jb1, jb2;
	JCheckBox jcb1, jcb2, jcb3;
	JRadioButton jrb1, jrb2;
	ButtonGroup bg;
	JComboBox jcbx1;
	JList jlist;
	JScrollPane jsp;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WidgetTest demo = new WidgetTest();
	}
	
	public WidgetTest()
	{
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jlb1 = new JLabel("运动");
		jlb2 = new JLabel("性别");
		jb1 = new JButton("注册");
		jb2 = new JButton("取消");
		jcb1 = new JCheckBox("足球");
		jcb2 = new JCheckBox("篮球");
		jcb3 = new JCheckBox("网球");
		jrb1 = new JRadioButton("男");
		jrb2 = new JRadioButton("女");
		bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		
		jlb3 = new JLabel("籍贯");
		jlb4 = new JLabel("景点");
		String []jg = {"北京","天津","上海","山东","锦州"};
		jcbx1 = new JComboBox(jg);
		String []jd = {"九寨沟","泰山","崂山","故宫","庐山"};
		jlist = new JList(jd);
		jlist.setVisibleRowCount(3);
		jsp = new JScrollPane(jlist);
		
		this.setLayout(new GridLayout(5,1));
		
		jp1.add(jlb1);
		jp1.add(jcb1);
		jp1.add(jcb2);
		jp1.add(jcb3);
		
		jp2.add(jlb2);
		jp2.add(jrb1);
		jp2.add(jrb2);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		jp4.add(jlb3);
		jp4.add(jcbx1);
		jp5.add(jlb4);
	//	jp5.add(jlist);
		jp5.add(jsp);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		
		this.setSize(300, 300);
		this.setTitle("Hello");
		this.setLocation(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}
