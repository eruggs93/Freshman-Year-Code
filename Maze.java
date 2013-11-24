/*************************************************************************
 * Eric Ruggieri
 * assign 3
 * Date Submitted 10/5/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation:  javac Maze.java
 *  Execution:    java Maze
 *  Dependencies: StdDraw.java
 *
 *************************************************************************/

public class Maze 
{
	static boolean[] cases;
	static int holey = 0, holex = 0, hole2y = 0, hole2x = 0, hold = 0;
	public static void main(String[] args)
	{
		StdDraw.setXscale(0, 32);
		StdDraw.setYscale(0, 32);
		cases = new boolean[4];
		int holdx = (int) (Math.random() *29) + 2;
		int holdy = (int) (Math.random() *29) + 2;
		drawMaze(0,0,32,32,holdx, holdy, 1);
		StdDraw.show();
	}

	private static void drawMaze(int x1, int y1, int x2, int y2,
			int x, int y, int count) {
		// TODO Auto-generated method stub
		if(x2-x1 <= 1 || y2-y1 <= 1)
		{
		}
		else
		{
			//draws original lines
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.line(x, y1, x, y2);
			StdDraw.line(x1, y, x2, y);
			
			//decides which line to not put a hole in
			hold = (int) (Math.random() * 4);
			cases[hold] = true;
			StdDraw.setPenColor(StdDraw.WHITE);
			
			//finds a point for the holes
			if(y2-y <= 1 || x2-x <= 1 || x-x1 <= 1 || y-y1 <=1)
			{
				if(y2-y <= 1)
				{
					holey = y;
					holex = (int) (Math.random() *(x2 - x - 1)) + x + 1;
					hole2y = (int) (Math.random() *(y - y1 - 1)) + y1 + 1;
					hole2x = (int) (Math.random() *(x - x1 - 1)) + x1 + 1;
				}
				if(x2-x <= 1)
				{
					holex = x;
					holey = (int) (Math.random() *(y2 - y - 1)) + y + 1;
					hole2y = (int) (Math.random() *(y - y1 - 1)) + y1 + 1;
					hole2x = (int) (Math.random() *(x - x1 - 1)) + x1 + 1;
				}
				if(y-y1 <= 1)
				{
					hole2y = y1;
					holey = (int) (Math.random() *(y2 - y - 1)) + y + 1;
					holex = (int) (Math.random() *(x2 - x - 1)) + x + 1;
					hole2x = (int) (Math.random() *(x - x1 - 1)) + x1 + 1;
				}
				if(x-x1 <= 1)
				{
					hole2x = x1;
					holey = (int) (Math.random() *(y2 - y - 1)) + y + 1;
					holex = (int) (Math.random() *(x2 - x - 1)) + x + 1;
					hole2y = (int) (Math.random() *(y - y1 - 1)) + y1 + 1;
				}
			}
			else
			{
				holey = (int) (Math.random() *(y2 - y - 1)) + y + 1;
				holex = (int) (Math.random() *(x2 - x - 1)) + x + 1;
				hole2y = (int) (Math.random() *(y - y1 - 1)) + y1 + 1;
				hole2x = (int) (Math.random() *(x - x1 - 1)) + x1 + 1;
			}
			
			//makes the holes in 3 of the sides and recurs the function
			if(hold == 0)
			{
				StdDraw.line(holex, y, holex+1, y);
				StdDraw.line(x, hole2y, x, hole2y+1);
				StdDraw.line(hole2x, y, hole2x+1, y);
				drawMaze(x1, y1, x, y, hole2x, (int) (Math.random() *(y - y1 - 1)) + y1 + 1, 1);
				drawMaze(x, y1, x2, y, holex, (int) (Math.random() *(y - y1 - 1)) + y1 + 1, 2);
				drawMaze(x, y, x2, y2, holex, holey, 3);
				drawMaze(x1, y, x, y2, hole2x, holey, 4);
			}
			else if(hold == 1)
			{
				StdDraw.line(x, holey, x, holey+1);
				StdDraw.line(x, hole2y, x, hole2y+1);
				StdDraw.line(hole2x, y, hole2x+1, y);
				drawMaze(x1, y1, x, y, hole2x, hole2y, 1);
				drawMaze(x, y1, x2, y, (int) (Math.random() *(x2 - x - 1)) + x + 1, hole2y, 2);
				drawMaze(x, y, x2, y2, (int) (Math.random() *(x2 - x - 1)) + x + 1, holey, 3);
				drawMaze(x1, y, x, y2, hole2x, holey, 4);
			}
			else if(hold == 2)
			{
				StdDraw.line(x, holey, x, holey+1);
				StdDraw.line(holex, y, holex+1, y);
				StdDraw.line(hole2x, y, hole2x+1, y);
				drawMaze(x1, y1, x, y, hole2x, hole2y, 1);
				drawMaze(x, y1, x2, y, holex, hole2y, 2);
				drawMaze(x, y, x2, y2, holex, (int) (Math.random() *(y2 - y - 1)) + y + 1, 3);
				drawMaze(x1, y, x, y2, hole2x, (int) (Math.random() *(y2 - y - 1)) + y + 1, 4);
			}
			else if(hold == 3)
			{
				StdDraw.line(x, holey, x, holey+1);
				StdDraw.line(holex, y, holex+1, y);
				StdDraw.line(x, hole2y, x, hole2y+1);
				drawMaze(x1, y1, x, y, (int) (Math.random() *(x - x1 - 1)) + x1 + 1, hole2y, 1);
				drawMaze(x, y1, x2, y, holex, hole2y, 2);
				drawMaze(x, y, x2, y2, holex, holey, 3);
				drawMaze(x1, y, x, y2, (int) (Math.random() *(x - x1 - 1)) + x1 + 1, holey, 4);
			}
		}
	}
}
