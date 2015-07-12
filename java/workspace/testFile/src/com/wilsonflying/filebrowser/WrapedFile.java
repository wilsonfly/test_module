package com.wilsonflying.filebrowser;

import java.io.File;

public class WrapedFile {

	public WrapedFile(File f) {
		file =f;
	}
	
	public File getFile() {
		return file;
	}
	
	@Override
	public String toString() {
		if (getFile()==null) {
			return "";
		}
		
		return String.format("[%s]%s",getFile().isDirectory()?"DIR":"File" , getFile().getName());
	}
	
	private File file = null;
}
