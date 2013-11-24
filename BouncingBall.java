
/*************************************************************************
 * Eric Ruggieri
 * assign 2
 * Date Submitted 10/5/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation:  javac BouncingBall.java
 *  Execution:    java BouncingBall
 *  Dependencies: StdDraw.java
 *
 *  Implementation of a 2-d bouncing ball in the box from (-1, -1) to (1, 1).
 *
 *  % java BouncingBall
 *
 *************************************************************************/

public class BouncingBall { 
    
	static boolean pinball = false;
	static boolean[][] grid = new boolean[50][50];
	static int numberOfBlocks = 0;
	
	public static void main(String[] args) {
    	
    	assert Integer.parseInt(args[0]) <= 2500;
    	numberOfBlocks = Integer.parseInt(args[0]);
    	
    	if(args[1] == "-d")
    		pinball = true;
        // set the scale of the coordinate system
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);

        // initial values
        double rx = 0.480, ry = 0.860;     // position
        double vx = 0.021, vy = 0.015;     // velocity
        double radius = 0.02;              // radius
                
        int k = 0, holdx, holdy;
        //initializing which squares are filled
        while(k < numberOfBlocks)
        {
        	holdx = (int) (Math.random() * 50);
        	holdy = (int) (Math.random() * 50);
        	if(!grid[holdx][holdy])
        	{
        		grid[holdx][holdy] = true;
        		k++;
        		continue;
        	}
        	else
        		continue;
        }
        
        //printing out the board
        double boxX = 0, boxY = 0;
        StdDraw.show();
        // main animation loop
        while (true)  { 

            // bounce off wall according to law of elastic collision
            if (Math.abs(rx + vx) > 1.1 - radius) 
            	vx = -vx;
            if (Math.abs(ry + vy) > 1.1 - radius) 
            	vy = -vy;
            boxX = ((2/50.0*5 + 2/50.0*(1+5))/2.0 -1);
			boxY = ((2/50.0*5 + 2/50.0*(1+5))/2.0 -1);
            // update position
            rx = rx + vx; 
            ry = ry + vy; 

            // clear the background
	    StdDraw.clear();
	    
	    	//draw the blocks and check for collisions
	    for(int i = 0; i < 50; i++)
        	for(int j = 0; j < 50; j++)
        	{
        		StdDraw.setPenColor(StdDraw.BLUE);
        		if(grid[i][j])
        		{
        			boxX=(2/50.0*i + 2/50.0*(1+i))/2.0 -1;
        			boxY=(2/50.0*j + 2/50.0*(1+j))/2.0 -1;
        			StdDraw.filledSquare(boxX, boxY, 1/50.0);
        			if (Math.abs(rx - boxX) <= 1/50.0 + radius  &&
                    		Math.abs(ry - boxY) <= 1/50.0 + radius)
                    {
                    	if(Math.abs(rx - boxX) < Math.abs(ry - boxY))
                    		vy = -vy;
                    	else if(Math.abs(rx - boxX) > Math.abs(ry - boxY))
                    		vx = -vx;
                    		//check to see if you need to draw new blocks
                    	if(pinball)
                        	{
                        		grid[i][j] = false;
                        		holdx = (int) (Math.random() * 50);
                            	holdy = (int) (Math.random() * 50);
                            	grid[holdx][holdy] = true;
                        	}
                    }
        		}
        	}
	    StdDraw.setPenColor(StdDraw.GRAY);

            // draw ball on the screen
            StdDraw.setPenColor(StdDraw.BLACK); 
            StdDraw.filledCircle(rx, ry, radius); 

            // display and pause for 20 ms
	    StdDraw.show(10); 
        } 
    } 
} 
