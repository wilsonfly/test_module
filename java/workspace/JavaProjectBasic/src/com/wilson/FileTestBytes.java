/*
 * 字节流测试代码
 */

package com.wilson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTestBytes {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileTestBytes demo = new FileTestBytes();
		demo.testFile();
		demo.testDir();
		demo.testInput();
		demo.testOutput();
		demo.copyPicture();
	}
	
	public void copyPicture()
	{
		//二进制文件只能用字节流完成
		File f = new File("d:/aDir/win7.png");

		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(f);
			fos = new FileOutputStream("d:/aDir/new_win7.png");
			
			int n = 0;
			byte []bytes = new byte[1024];
			
			try {
				while( (n=fis.read(bytes)) != -1 )
				{
					/*
					 * wrong
					String s = new String(bytes,0,n);
					fos.write(s.getBytes());
					*/
					fos.write(bytes);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
	
	public void testOutput()
	{
		File f = new File("d:\\aDir\\output.txt");
		//字节输出流
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			String s = "Hello world!!!\n";
			String s2 = "小雪\n";
			byte []bytes = new byte[1024];
		//	byte []bytes2 = "new Hello world!";//error
			try {

				fos.write(s.getBytes());
				fos.write(s2.getBytes());
				fos.write(bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void testInput() throws IOException
	{
		File f = new File("d:\\aDir\\test.txt");
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			int n = 0;
			byte []bytes = new byte[1024];
			
			while( (n=fis.read(bytes)) != -1 )
			{
				String s = new String(bytes,0,n);
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭文件流必须在finally中执行
			fis.close();
		}
	}

	public void testFile() {
		// File f = new File("d:\\test.txt");
		File f = new File("d:\\aDir\\test.txt");

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("file existed");
		}

		System.out.println("file size:" + f.length());
		System.out.println("file readable:" + f.canRead());
	}

	public void testDir() {
		File d = new File("d:\\aDir");
		if (d.isDirectory()) {
			System.out.println("dir exist");
		} else {
			d.mkdir();
			System.out.println("dir create");

		}

		// File l = new File("d:\\aDir");//is ok
		File l = new File("d:/aDir");// is ok

		if (l.isDirectory()) {
			File lists[] = l.listFiles();
			for (int i = 0; i < lists.length; i++) {
				System.out.println("file name:" + lists[i].getName());
			}
		} else {
			System.out.println("d:\\aDir not dir");
		}
	}

}
