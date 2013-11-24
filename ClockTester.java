/*************************************************************************
 * Eric Ruggieri
 * assign 4
 * Date submitted 10/17/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation: javac Clock.java, ClockTester.java
 *  Execution: java ClockTester
 * 
 *************************************************************************/
public class ClockTester 
{
	public static void main(String[] args)
	{
		Draw myDraw = new Draw("canvas");
		myDraw.setCanvasSize(500, 500);
		myDraw.setXscale(0, 500);
		myDraw.setYscale(0, 500);
		Clock myclock = new Clock(myDraw);
		while(true)
		{
			myclock.tick();
		}
	}
}
