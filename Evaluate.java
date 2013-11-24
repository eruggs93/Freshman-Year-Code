/*************************************************************************
 * Eric Ruggieri
 * Jython
 * Date submitted 11/18/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation: javac Evaluate.java 
 *  Execution: java Jython
 *  Dependencies: stlib.jar, Queue.java, Stack.java, HashTable.java
 * 
 *************************************************************************/
import java.util.Stack;


public class Evaluate 
{
	private Queue<String> inputQ;
	
	public Evaluate(Queue<String> in)
	{
		inputQ = in;
	}
	
	public Double evaluate(HashTable<String, Double> ST)
	{
		Stack<Double> numbers = new Stack<Double>();
		while(!inputQ.isEmpty())
		{
			String current = inputQ.dequeue();
			if(((int)current.charAt(0) >= 48 &&  (int)current.charAt(0) <= 57) ||
					(int)current.charAt(0) == 46)
				numbers.push((Double)Double.parseDouble(current));
			else if(((int)current.charAt(0) >= 42 &&  (int)current.charAt(0) <= 45) || 
					(int)current.charAt(0) == 37 || (int)current.charAt(0) == 94 || (int)current.charAt(0) <= 47)
			{
				if(numbers.isEmpty())
				{
					System.out.println("Error: invalid syntax");
					return null;
				}
				double second = numbers.pop();
				if(numbers.isEmpty())
				{
					System.out.println("Error: invalid syntax");
					return null;
				}
				double first = numbers.pop();
				int ascii = (int)current.charAt(0);
				double answer = 0.0;
				switch(ascii)
				{
				case 42: answer = first * second;
				break;
				case 37: answer = first % second;
				break;
				case 43: answer = first + second;
				break;
				case 45: answer = first - second;
				break;
				case 47: answer = first / second;
				break;
				case 94: answer = Math.pow(first, second);
				break;
				}
				numbers.push(answer);
			}
			else if(((int)current.charAt(0) >= 65 &&  (int)current.charAt(0) <= 90) || ((int)current.charAt(0) >= 97 &&  (int)current.charAt(0) <= 122))
			{
				if(ST.find(current) == null)
				{
					System.out.println("Error: variable '" + current + "' is not defined");
					return null;
				}
				double find = ST.find(current);
				numbers.push(find);
			}
		}
		if(!numbers.isEmpty())
			return numbers.pop();
		return null;
	}

}
