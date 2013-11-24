/* Eric Ruggieri
 * assign 1
 * Date Submitted _______
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 */

public class CubeRoot
{
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			System.out.println("You need to enter a number on" 
				+ " the command line.");
			System.exit(0);
		}
		else
		{
			int cube = Integer.parseInt(args[0]);
			double root = findCubeRoot(cube);
			System.out.println("Root to " + cube + " = " + 
				root);
		}
	}
	public static double findCubeRoot(int cube)
	{
		double guess = 0;
		int N = cube;
		System.out.println("N = " + N);
		for(int i = 0; i < N; i++)
		{
			//System.out.println("in loop");
			if(i * i * i <= N && (i+1) * (i+1) * (i+1) > N)
			{
				guess = i;
				System.out.println("guess start = " + guess);
				while(Math.abs(N - guess*guess*guess) > 
.00000001)
				{
					guess +=(1/3.0)* ((N / (guess * 
						guess))-guess);
				}
				return guess;
			}
		}
		return guess;
	}
}
