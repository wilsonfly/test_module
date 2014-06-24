/*
 * »º³å×Ö·ûÁ÷
 */
package com.wilson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTestString {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileTestString demo = new FileTestString();
		demo.test();
	}
	
	public void test() throws IOException
	{
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			FileReader fr = new FileReader("d:/aDir/String.txt");
			FileWriter fw = new FileWriter("d:/aDir/new_String.txt");
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			
			String s = null;
			try {
				while( (s=br.readLine()) != null)
				{
					System.out.println(s);
					//bw.write(s);//all in one line
					bw.write(s+"\r\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();//if not close the file,there maybe nothing in the file at last
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
