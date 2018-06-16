package com.lilutong.controller;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.sun.webkit.ContextMenu.ShowContext;

import javafx.geometry.Point2D;
import sun.applet.Main;


public class Achieve {
	
	
	
	public List<com.lilutong.bean.Point> points=null;
	public List<Point> points2d=new ArrayList<Point>();
	public Double matrix[][]=new Double[3][3];
	

	public Achieve() {
	
		points=new ArrayList<com.lilutong.bean.Point>();
		matrix[0]= new Double[] {1.0,0.0,0.0};
		matrix[1]= new Double[] {0.0,1.0,0.0};
		matrix[2]= new Double[] {0.0,0.0,1.0};
	
	}
	
	public List<Point>  readPoints() throws IOException {
        
		List<Point> points=new ArrayList<Point>();
			
		File file=new File("sources/points.txt");
		FileInputStream  fileInputStream=new FileInputStream(file);
		InputStreamReader  inputStreamReader  =new InputStreamReader(fileInputStream);
		BufferedReader  bufferedReader=new BufferedReader(inputStreamReader);
		
		String line="";
		String []  point=new  String[2];
		while(true) {
			line=bufferedReader.readLine();
            if(line!=null) {
            	point=line.split(",");
            	points.add(new Point(Integer.parseInt(point[0]),Integer.parseInt(point[1])));
            }else {
            	break;
            }
		}
		bufferedReader.close();
		inputStreamReader.close();
		fileInputStream.close();

        return  points;
	}
	
	
	public void transformTo3d(List<Point> points) {
		for(int i=0;i<points.size();i++) {
			com.lilutong.bean.Point  point=new com.lilutong.bean.Point();
            point.setX(points.get(i).getX());
            point.setY(points.get(i).getY());
            point.setParam(1.0);
    		this.points.add(point);
		}
		
	}
	public List<Point> transformTo2d(List<com.lilutong.bean.Point> points) {
		for(int i=0;i<points.size();i++) {
			Point  point=new Point();
			point.setLocation(points.get(i).getX(),points.get(i).getY());
    		this.points2d.add(point);
		}
		return this.points2d;
		
	}
    
	public List<com.lilutong.bean.Point> transformPoints(Double  matrix[][]) {
		List<com.lilutong.bean.Point> points=new ArrayList<com.lilutong.bean.Point>();
		
		
		for(int i=0;i<this.points.size();i++) {
			Double x=0.0,y=0.0,param=0.0;   //用于计时
			x+=this.points.get(i).getX()*matrix[0][0];
			x+=this.points.get(i).getY()*matrix[1][0];
			x+=this.points.get(i).getParam()*matrix[2][0];
			y+=this.points.get(i).getX()*matrix[0][1];
			y+=this.points.get(i).getY()*matrix[1][1];
			y+=this.points.get(i).getParam()*matrix[2][1];					
			param+=this.points.get(i).getX()*matrix[0][2];
			param+=this.points.get(i).getY()*matrix[1][2];
			param+=this.points.get(i).getParam()*matrix[2][2];	
			points.add(new com.lilutong.bean.Point(x,y,param));
	   }
		this.points=points;
		return points;
	}
	
	public void showPoints() {
		for(int i=0;i<points.size();i++) {
			System.out.println(points.get(i).toString());
		}
	}
	
	
	
	//旋转
	public void rotate(Double degree) {
		Double cos=Math.cos(degree);
		Double sin=Math.sin(degree);
		matrix[0]= new Double[] {cos,sin,0.0};
		matrix[1]= new Double[] {-sin,cos,0.0};
		matrix[2]= new Double[] {0.0,0.0,1.0};
	   this.transformPoints(matrix);
	}
	//移动
    public void move(Double x,Double y) {
		matrix[0]= new Double[] {1.0,0.0,0.0};
		matrix[1]= new Double[] {0.0,1.0,0.0};
		matrix[2]= new Double[] {x,y,1.0};
	   this.transformPoints(matrix);
	}
    //放缩
    public void scale(Double scale) {
		matrix[0]= new Double[] {scale,0.0,0.0};
		matrix[1]= new Double[] {0.0,scale,0.0};
		matrix[2]= new Double[] {0.0,0.0,1.0};
	   this.transformPoints(matrix);
	}
    public static  void main(String args[]) throws IOException {
		//获取点
		Achieve  achieve=new Achieve();
		List<Point> points =achieve.readPoints();
		achieve.transformTo3d(points);


		achieve.move(-1.0, -10.0);
		achieve.rotate(0.25*Math.PI);
		achieve.move(1.0, 10.0);
		
		achieve.transformTo2d(achieve.points);
		achieve.showPoints();
	}

}
