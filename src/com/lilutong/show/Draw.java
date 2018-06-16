package com.lilutong.show;


	

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.lilutong.controller.Achieve;

import javafx.scene.chart.PieChart.Data;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
public class Draw  extends  JFrame   implements  GLEventListener {

		
		 final private int width = 800;  
    	 final private int height = 800;  

		public void init(GLAutoDrawable drawable) {
		    GL4 gl = drawable.getGL().getGL4();  
	        gl.glClearColor(0.5f, 0.5f, 0.92f, 1.0f);    			
		}
	
    	public Draw() {
	        GLProfile profile = GLProfile.get(GLProfile.GL4);  
	        GLCapabilities capabilities = new GLCapabilities(profile);  
	        GLCanvas canvas = new GLCanvas();  
	        canvas.addGLEventListener(this);  
	  	        
	        this.setTitle("这是我的原型");  
	        this.setName("Minimal OpenGL");  
	          
	        this.getContentPane().add(canvas);  
	          
	        this.setSize(width,height);  
	        this.setLocationRelativeTo(null);  
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
	        this.setVisible(true);  
	        this.setResizable(false);  
	        canvas.requestFocusInWindow();  
		}

		public void dispose(GLAutoDrawable arg0) {
		}

		public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		}
	

		public  void  drawAndFillIt(GLAutoDrawable drawable,List<Point> points)  {
			
			GL2 gl = drawable.getGL().getGL2();
		    gl.glColor3f(0f, 0f, 1f);
			gl.glBegin(GL2.GL_POLYGON);
		    float dota=0.04f;  
		    for(int i=0;i<points.size();i++) {
		    	  gl.glVertex3f((float)points.get(i).getX()*dota, (float)points.get(i).getY()*dota,0);
		      }   
		    gl.glEnd();
		}
		
        public void display(GLAutoDrawable  drawable) {
			
    		try {

        		//获取点
        		Achieve  achieve=new Achieve();	
				achieve.transformTo3d(achieve.readPoints());

				
	    		achieve.move(-1.0, -5.0);
	    		achieve.rotate(0.8*Math.PI);
	    		achieve.move(1.0, 8.0);
	    		achieve.scale(0.8);
	    		
	    		List<Point> points=achieve.transformTo2d(achieve.points);;
				
	    		this.drawAndFillIt(drawable,achieve.points2d);
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
    								
		}


		public  static  void main(String  args[]) throws IOException {
			 Draw g = new Draw();  						
			
		}
		
	}
	