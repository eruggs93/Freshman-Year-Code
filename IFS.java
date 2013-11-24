import java.awt.Color;

/*************************************************************************
 *  Compilation:  javac IFS.java
 *  Execution:    java IFS N < input.txt
 *  Dependencies: StdDraw.java
 *
 *  Here are some sample data files:
 *  
 *  http://www.cs.princeton.edu/introcs/22library/barnsley.txt
 *  http://www.cs.princeton.edu/introcs/22library/binary.txt
 *  http://www.cs.princeton.edu/introcs/22library/culcita.txt
 *  http://www.cs.princeton.edu/introcs/22library/cyclosorus.txt
 *  http://www.cs.princeton.edu/introcs/22library/dragon.txt
 *  http://www.cs.princeton.edu/introcs/22library/fern-sedgewick.txt
 *  http://www.cs.princeton.edu/introcs/22library/fishbone.txt
 *  http://www.cs.princeton.edu/introcs/22library/floor.txt
 *  http://www.cs.princeton.edu/introcs/22library/koch.txt
 *  http://www.cs.princeton.edu/introcs/22library/sierpinski.txt
 *  http://www.cs.princeton.edu/introcs/22library/spiral.txt
 *  http://www.cs.princeton.edu/introcs/22library/swirl.txt
 *  http://www.cs.princeton.edu/introcs/22library/tree.txt
 *  http://www.cs.princeton.edu/introcs/22library/zigzag.txt
 *
 *************************************************************************/

public class IFS {
    public static void main(String[] args) {

	if (args.length < 1) {
	    System.out.println("Usage: java IFS T (number of points to plot)");
	    System.exit(0);
	}

        // number of iterations
        int T = Integer.parseInt(args[0]), counter = 0;

        // probability distribution for choosing each rule
        double[] dist = StdArrayIO.readDouble1D();
        
        float[][] color = new float[4][3];
        int count = 0;
        for(int i = 0; i < 4; i++)
        	for(int j = 0; j < 3; j++)
        	{
        		count++;
        		color[i][j] = Float.parseFloat(args[count]);
        	}
        // update matrices
        double[][] cx = StdArrayIO.readDouble2D();
        double[][] cy = StdArrayIO.readDouble2D();
//        double[][] cz = StdArrayIO.readDouble2D();

        // current value of (x, y)
        double x = 0.0, y = 0.0, z=0.0;

        // do T iterations of the chaos game
        for (int t = 0; t < T; t++) { 

            // pick a random rule according to the probability distribution
            int r = StdRandom.discrete(dist); 

            // do the update
            double x0 = cx[r][0]*x + cx[r][1]*y + cx[r][2]; //+ cx[r][3]; 
            double y0 = cy[r][0]*x + cy[r][1]*y + cy[r][2];// + cy[r][3];
//            double z0 = cz[r][0]*x + cz[r][1]*y + cz[r][2];
            x = x0; 
            y = y0;
//            z = z0;

            // draw the resulting point
            StdDraw.setPenColor(Color.getHSBColor(color[r][0], color[r][1], color[r][2]));
            StdDraw.point(x, y); 
//            StdDraw.point(y, z);
//            StdDraw.point(z, x);

            // for efficiency, display only every 100 iterations
            if (t % 100 == 0) StdDraw.show(10);
        } 

        // ensure everything gets drawn
        StdDraw.show(0);
    } 
} 

