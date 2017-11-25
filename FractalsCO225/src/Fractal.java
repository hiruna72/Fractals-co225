//written by E/14/302-Hiruna Samarakoon

import javax.swing.*;

class Fractal {     

    public static void main(String [] args) { 
    int g=0;
    JFrame frame = new JFrame("Fractals"); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    frame.setLayout(null);
    //check if it is Mandelbrot
    if(args.length>0 && args[0].equals("Mandelbrot")){
        if( args.length==1)
        frame.setContentPane(new Mandelbrot());//default constructor is invoked 
        else if(args.length==5){
        	try{
        		float a=Float.parseFloat(args[1]);
        		float b=Float.parseFloat(args[2]);
        		float c=Float.parseFloat(args[3]);
        		float d=Float.parseFloat(args[4]);
        		frame.setContentPane(new Mandelbrot(a,b,c,d));//pass values to the constructor Mandelbrot
        	}
        catch (Exception pe) {
            System.out.println(pe);
            frame.dispose();
            return;
        }
        }
        	
        
        else if(args.length==6){
        	try{
        		float a=Float.parseFloat(args[1]);
        		float b=Float.parseFloat(args[2]);
        		float c=Float.parseFloat(args[3]);
        		float d=Float.parseFloat(args[4]);
        		int e=Integer.parseInt(args[5]);
        		frame.setContentPane(new Mandelbrot(a,b,c,d,e));
        	}
        catch (Exception pe) {
           // pe.printStackTrace();
            System.out.println(pe);
            frame.dispose();//frame should be deleted if it is wrong input
            return;
        }
        }
        
        else{
            System.out.println("error in input-'Mandelbrot [MinimumRealValue] [MaximumRealValue] [MinimumImaginary] [MaximumImaginary] [MaximumIterations]'");  
            frame.dispose();
            return;
        }
    
    }
    
    //check if input is Julia 
    else if(args.length>0 && args[0].equals("Julia")){
        if(args.length==1)
        frame.setContentPane(new Julia());   
        else if(args.length==3){
        	try{
        		float a=Float.parseFloat(args[1]);
        		float b=Float.parseFloat(args[2]);
        		frame.setContentPane(new Julia(a,b));
        	}
        catch (Exception pe) {
           // pe.printStackTrace();
            System.out.println(pe);
            frame.dispose();
            return;
        }
        }
        else if(args.length==4){
        	try{
        		float a=Float.parseFloat(args[1]);
        		float b=Float.parseFloat(args[2]);
        		int c=Integer.parseInt(args[3]);
        		frame.setContentPane(new Julia(a,b,c));
        	}
        catch (Exception pe) {
           // pe.printStackTrace();
            System.out.println(pe);
            frame.dispose();
            return;
        }
        }
        else{
            System.out.println("error in input-'Julia [RealPartOfComplexNumber] [ImaginaryPartOfComplexNumber] [MaximumIterations]'");  
            frame.dispose();
            return;
        }

    }
    //completely wrong input
    else{
        System.out.println("wrong input-'Julia [RealPartOfComplexNumber] [ImaginaryPartOfComplexNumber] [MaximumIterations]'\n           -'Mandelbrot [MinimumRealValue] [MaximumRealValue] [MinimumImaginary] [MaximumImaginary] [MaximumIterations]'");  
        frame.dispose();
        return;
    }
    
    frame.pack(); 
    frame.setLocationRelativeTo(null); 
    frame.setVisible(true); 
    }
}