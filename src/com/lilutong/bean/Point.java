package com.lilutong.bean;

public class Point {

	public Double x;
	public Double y;
	public Double param;
	
	public Point() {
		
	}
	
	
	public Point(Double x, Double y, Double param) {
		super();
		this.x = x;
		this.y = y;
		this.param = param;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public Double getParam() {
		return param;
	}
	public void setParam(Double param) {
		this.param = param;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
	
	
	
	
}
