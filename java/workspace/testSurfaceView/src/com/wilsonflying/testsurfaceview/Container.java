package com.wilsonflying.testsurfaceview;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

public class Container {

	private List<Container> children = null;
	private float x=0,y=0;
	
	public Container() {
		// TODO Auto-generated constructor stub
		children = new ArrayList<Container>();
	}
	
	public void MyDraw(Canvas canvas){
		canvas.save();
		
		canvas.translate(getX(), getY());
		drawChildView(canvas);
		for (Container child : children) {
			child.MyDraw(canvas);
		}
		
		canvas.restore();
	}
	
	public void drawChildView(Canvas canvas){
		//draw child view in this interface 
	}
	
	public void add(Container child){
		children.add(child);
	}
	
	public void remove(Container child){
		children.remove(child);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
