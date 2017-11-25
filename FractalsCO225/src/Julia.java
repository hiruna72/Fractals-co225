
class Julia extends Mandelbrot {
	//super class Mandelbrot has the implementation only necessary changes are made here
	Julia(){
		super();
		super.cR=(float)-0.4;
		super.cI=(float)0.6;
		super.lock=true;
		
	}
	Julia(float cR,float cI){
		super();
		super.cR=cR;
		super.cI=cI;
		super.lock=true;
	}
	Julia(float cR,float cI,int maxIter){
		super();
		super.cR=cR;
		super.cI=cI;
		super.maxIter=maxIter;
		super.lock=true;
	}
}