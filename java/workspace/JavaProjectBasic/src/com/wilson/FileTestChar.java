/*
 * �ַ������Դ���
 */
package com.wilson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTestChar {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileTestChar demo = new FileTestChar();
		demo.testFileReader();
	}

	public void testFileReader() throws IOException
	{
		FileReader fr = null;
		FileWriter fw = null;
		char []c = new char[1024];
		int n = 0;
		File f = new File("d:/aDir/string.txt");
		
		if(!f.exists())
		{
			try {
				System.out.println("create file:"+f.getName());
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			fr = new FileReader(f);
			fw = new FileWriter("d:/aDir/new_String.txt");

			try {
				while( (n=fr.read(c)) != -1)
				{
					System.out.println(c);//ok
					
					String s = new String(c,0,n);
					System.out.println(s);//ok
					
					//fw.write(c);//ok, but wite NUL into file
					fw.write(c, 0, n);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			fr.close();
			fw.close();
			
		}
	}
}
