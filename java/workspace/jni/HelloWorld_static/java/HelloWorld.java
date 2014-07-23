
class HelloWorld {
	private native void print();
	private native void lprint(String str);
	private native String getLine(String str);
	public static void main(String[] args) {
		new HelloWorld().print();
		new HelloWorld().lprint("<Hello jni, this msg from java !>");
		String input = new HelloWorld().getLine("Input some word,please!\n");	
		System.out.println("Get from jni_world:" + input);
	}

	static { System.loadLibrary("HelloWorld_jni"); }
}
