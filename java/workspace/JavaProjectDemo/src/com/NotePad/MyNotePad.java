package com.NotePad;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class MyNotePad extends JFrame implements ActionListener{

	JTextArea jta = null;
	//�˵���
	JMenuBar jmb = null;
	//��һ��JMenu
	JMenu jm1 = null;
	//��һ��JMenuItem
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyNotePad demo = new MyNotePad();
		
	}

	public MyNotePad()
	{
		jta = new JTextArea();
		jmb = new JMenuBar();
		jm1 = new JMenu("��(o)");
		jm1.setMnemonic('F');
		jmi1 = new JMenuItem("��", new ImageIcon("images/new.png"));
		jmi2 = new JMenuItem("����", new ImageIcon("images/save.png"));
		
		//ע�����
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		
		
		this.setJMenuBar(jmb);
		jmb.add(jm1);
		jm1.add(jmi1);
		jm1.add(jmi2);
		
		this.add(jta);
		this.setTitle("MyNotePad");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	//
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("open"))
		{
			System.out.println("try to open...");
			
			JFileChooser jfch = new JFileChooser();
			jfch.setDialogTitle("��ѡ���ļ�");
			jfch.showOpenDialog(null);
			jfch.showOpenDialog(null);
			jfch.setVisible(true);
			
			//�õ�ѡ����ļ��ľ���·��
			String filename = jfch.getSelectedFile().getAbsolutePath();
			//System.out.println("seltect:"+filename);
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(filename);
				br = new BufferedReader(fr);
				
				String s = null;
				String allText = null;
				while( (s=br.readLine()) != null)
				{
					allText += s+"\r\n";
				}
				jta.setText(allText);
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}finally
			{
				try {
					br.close();
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getActionCommand().equals("save"))
		{
			//���ֱ���Ի���
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("����Ϊ...");
			jfc.showSaveDialog(null);
			jfc.setVisible(true);
			
			//�õ������ļ��ľ���·��
			String filename = jfc.getSelectedFile().getAbsolutePath();
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(filename);
				bw = new BufferedWriter(fw);
				
				bw.write(jta.getText());//���ݶ��ʱ��Ἣ��ռ���ڴ棬��Ҫ�Ż�
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
	}
}