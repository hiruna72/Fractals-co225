import java.awt.*; /* java abstract window toolkit */
import javax.swing.*;
import java.awt.geom.Line2D;

class Mandelbrot extends JPanel {
	protected float realMin;
	protected float imgMin;
	protected float realMax;
	protected float imgMax;
	protected int maxIter;
	protected int width = 800;
	protected int height = 800; 
	protected float cR=0;
    protected float cI=0;
    protected boolean lock=false;//lock is true if Julia Set is drawn.locks the addition of complex parts inside the nested loop  
	Mandelbrot(){		
    	
		this.realMax=1;
		this.realMin=-1;
		this.imgMin=-1;
		this.imgMax=1;
		this.maxIter=1000;
		setSize(width, height);
		setPreferredSize(new Dimension(width, height));
		//setLocation(0,0);
	}
	Mandelbrot(float realMin,float realMax,float imgMin,float imgMax){
		this.realMax=realMax;
		this.realMin=realMin;
		this.imgMin=imgMin;
		this.imgMax=imgMax;
		this.maxIter=1000;		
		setSize(width, height);
    	setPreferredSize(new Dimension(width, height)); 
	}
	Mandelbrot(float realMin,float realMax,float imgMin,float imgMax,int maxIter){
		this.realMax=realMax;
		this.realMin=realMin;
		this.imgMin=imgMin;
		this.imgMax=imgMax;
		this.maxIter=maxIter;
		
    	setPreferredSize(new Dimension(width, height)); 		
	}
	//print point changes the pixel color of the frame one at a time.therefore four threads calling this function!should be controlled and synchronized
	private synchronized void printPoint(Graphics2D frame, Color c, int i,int j) {

    frame.setColor(c); 
    frame.draw(new Line2D.Double(i,j,i,j)); 
    }
	
	//800*800 nested loop is cut down to 400*400 all the vars used inside loops are passed as arguments to avoid synchronization in the middle of a thread
	public void startNestedLoop(Graphics g,int startX,int startY,int stopX,int stopY,int flagX,int flagY,float cR,float cI,boolean lock){
		float cRR=0;
		float cII=0;
		float dX=((this.realMax-this.realMin)/this.width);
    	float dY=((this.imgMax-this.imgMin)/this.height);
    	float y=this.imgMin*(1-flagY*1);
    
    	for(int i=startY;i<=stopY;i++){      
        
        	float x=this.realMin*(1-flagX*1);
        	
        	for(int j=startX;j<=stopX;j++){
            	
            	float rez=x;
            	float imgz=y;
           		int iter=0;
           		cRR=(lock)?cR:x;	//Julia locks the boolean lock
           		cII=(lock)?cI:y;
            while(iter<this.maxIter){
            	//no need of an extra complex number class as the calculation has a pattern !
                float r2 = rez*rez;
                float i2 = imgz*imgz;
                float temp = 2.0F * rez * imgz;

                rez=r2-i2+cRR;
                
                imgz=(float)temp+cII;

                if(r2*r2+i2*i2>4.0){
                  break;
                }
                
                iter++;
                
            }
            if (iter == this.maxIter) {
            	printPoint((Graphics2D)g, new Color(12, 98, 86), j,i); 
              }
            
            else{
            int temp2=(255*iter)/this.maxIter;
          
            printPoint((Graphics2D)g, new Color(0,temp2,temp2), j,i); 
            }
            x+=dX;
           
        }
        y+=dY;
    }
	}
	

	public void paintComponent(Graphics g) { 
   
		super.paintComponent(g);  
		//four anonymous thread class objects are declared
		//each does a 400*400 nested loop 
    	Thread a=new Thread(new Runnable(){
    		public void run(){
    			startNestedLoop(g,0,0,400,400,0,0,cR,cI,lock);
    		}
    	});
    	Thread b=new Thread(new Runnable(){
    		public void run(){
    			startNestedLoop(g,400,0,800,400,1,0,cR,cI,lock);
    		}
    	});
    	Thread c=new Thread(new Runnable(){
    		public void run(){
    			startNestedLoop(g,0,400,400,800,0,1,cR,cI,lock);
    		}
    	});
    	Thread d=new Thread(new Runnable(){
    		public void run(){
    			startNestedLoop(g,400,400,800,800,1,1,cR,cI,lock);
    		}
    	});    	
    	a.start();
    	b.start();
    	c.start();
    	d.start();
    	try{
    		//main thread waits until all the four threads are finished
    		a.join();
        	b.join();
        	c.join();
        	d.join();
    	}
    	catch(InterruptedException e){
    		e.printStackTrace();
    	}
    	
}
}