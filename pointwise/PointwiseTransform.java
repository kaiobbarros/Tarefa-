
public class PointwiseTransform extends Object {

	/**
	* Question 1.1 Contrast reversal
	*/
	static public ImageAccess inverse(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		double value = 0.0;
		for (int x=0; x<nx; x++)
		for (int y=0; y<ny; y++) {
			value = input.getPixel(x, y);
			value = 255 - value;
			output.putPixel(x, y, value);
		}
		return output;	
	} 

	/**
	* Question 1.2 Stretch normalized constrast
	*/
	static public ImageAccess rescale(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		double max = input.getMaximum();
		double min = input.getMinimum();
		// gerando alpha e beta baseado nas funções do script
		double maximum = input.getMaximum();
		double minimum = input.getMinimum();
			double alpha = 255/(maximum - minimum);
		
			double beta = minimum;
		
		// gerando função g
		double g = 0.0;
		ImageAccess output = new ImageAccess(nx, ny);
	    
	    for (int x=0; x<nx; x++)
			for (int y=0; y<ny; y++) {
				g = alpha * (input.getPixel(x, y) - beta);
				output.putPixel(x, y, g);
			}
		return output;	
	}

	/**
	* Question 1.3 Saturate an image
	*/
	static public ImageAccess saturate(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		//trunca o valor do pixel para 1000 no maximo 
		//(if valor <1000, valor = valor; if valor >= 1000, valor = 1000)
		for (int x=0; x<nx; x++)
		for (int y=0; y<ny; y++) {
			if (input.getPixel(x, y) > 10000) {
				output.putPixel(x, y, 10000);
			} else {
				output.putPixel(x, y, input.getPixel(x, y));
			}
		}
		return output;
	}
	
	/**
	* Question 3.1 Maximum Intensity Projection
	*/
	static public ImageAccess zprojectMaximum(ImageAccess[] zstack) {
		int nx = zstack[0].getWidth();
		int ny = zstack[0].getHeight();
		int nz = zstack.length;
		ImageAccess output = new ImageAccess(nx, ny);
		//declarar valor de máxima intensidade
		double valormax = 0.0;
    	for (int x=0; x<nx; x++)
		for (int y=0; y<ny; y++) {
    	  	valormax = zstack[0].getPixel(x, y);
    	  	//iteração em Z
    	  	for (int z=0; z<nz; z++){
				if (value_max < zstack[z].getPixel(x, y)) {
    	      	valormax = zstack[z].getPixel(x, y);
				}
    	  	}

			output.putPixel(x, y, valormax);
    	}
		return output;	
	}

	/**
	* Question 3.2 Z-stack mean
	*/
	static public ImageAccess zprojectMean(ImageAccess[] zstack) {
		int nx = zstack[0].getWidth();
		int ny = zstack[0].getHeight();
		int nz = zstack.length;
		ImageAccess output = new ImageAccess(nx, ny);
		
		double zstack = 0.0;
		//iteração para stackar em Z
    	for (int x=0; x<nx; x++)
			for (int y=0; y<ny; y++) {
				zstack = 0.0;
    		for (int z=0; z<nz; z++){
    	    	zstack += zstack[z].getPixel(x, y); 
		  	}
			//Gera output com z stackado
			output.putPixel(x, y, zstack/nz);
    	}
		return output;	
	}

}
