/*************************************************************************
 * Eric Ruggieri
 * Jython
 * Date submitted 11/18/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation: javac Jython.java 
 *  Execution: java Jython
 *  Dependencies: stlib.jar, InToPost.java, Evaluate.java, Queue.java,
 *  HashTable.java, Tokenizer.java
 * 
 *************************************************************************/
public class Jython 
{
	public static void main(String[] args)
	{
		HashTable<String, Double> ST = new HashTable<String, Double>(100);
		System.out.print("> ");
		while(!StdIn.isEmpty())
		{
			String input = StdIn.readLine();
			input = input.trim();
			if(!input.equals("exit"))
			{
				Tokenizer line = new Tokenizer(input);
				String first = line.getToken();
				if(first.equals("print"))
				{
					InToPost expression = new InToPost(line);
					Queue<String> queue = expression.translate();
					if(queue == null)
					{
						System.out.print("> ");
						continue;
					}
					Evaluate expressResult = new Evaluate(queue);
					Double toPutIn = expressResult.evaluate(ST);
					if(toPutIn == null)
					{
						System.out.print("> ");
						continue;
					}
					System.out.println(toPutIn);
				}
				else
				{
					String second = line.getToken();
					if(second.equals("=") && (((int)first.charAt(0) >= 65 &&  (int)first.charAt(0) <= 90) || 
							((int)first.charAt(0) >= 97 &&  (int)first.charAt(0) <= 122)))
					{
						InToPost express = new InToPost(line);
						Queue<String> value = express.translate();
						if(value == null)
						{
							System.out.print("> ");
							continue;
						}
						Evaluate result = new Evaluate(value);
						Double toPutIn = result.evaluate(ST);
						if(toPutIn == null)
						{
							System.out.print("> ");
							continue;
						}
						ST.insert(first, toPutIn);
					}
					else if(!((int)first.charAt(0) >= 65 &&  (int)first.charAt(0) <= 90) || 
							((int)first.charAt(0) >= 97 &&  (int)first.charAt(0) <= 122))
					{
						if(second.equals("="))
						{
							System.out.println("Error: invalid variable name");
							System.out.print("> ");
							continue;
						}
						else
						{
							System.out.println("Error: enter print token");
							System.out.print("> ");
							continue;
						}
					}
				}
			}
			else
				System.exit(0);
			System.out.print("> ");
		}
	}

}
