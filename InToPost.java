/*************************************************************************
 * Eric Ruggieri
 * Jython
 * Date submitted 11/18/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation: javac InToPost.java 
 *  Execution: java Jython
 *  Dependencies: stlib.jar, Tokenizer.java, Ququq.java, Stack.java
 * 
 *************************************************************************/


public class InToPost 
{
	private Queue<String> theQ;
	private Tokenizer theT;
	private boolean complete;

	public InToPost(Tokenizer in)
	{
		theT = in;
		theQ = new Queue<String>();
		complete = true;
	}

	public Queue<String> translate()
	{
		Stack<String> operators = new Stack<String>();
		while(!theT.done())
		{
			String token = theT.getToken();
			if(token.equals("ERROR"))
				return null;
			char c = token.charAt(0);
			if(((int)c >= 48 &&  (int)c <= 57) || ((int)c >= 65 &&  (int)c <= 90) || ((int)c >= 97 &&  (int)c <= 122) || (int)c == 46)
			{
				theQ.enqueue(token);
			}
			else if(token.equals("("))
			{
				operators.push(token);
				complete = false;
			}
			else if(token.equals(")"))
			{
				complete = true;
				while(!operators.isEmpty() && !operators.peek().equals("("))
				{
					theQ.enqueue(operators.pop());
				}
				operators.pop();
			}
			else
			{
				if(operators.isEmpty())
					operators.push(token);
				else
				{
					int tokenpref = getOrder(token);
					String last = operators.peek();
					int lastpref = getOrder(last);
					if(lastpref == 4)
						operators.push(token);
					else
					{
						if(tokenpref <= lastpref)
						{
							theQ.enqueue(operators.pop());
							operators.push(token);
						}
						else if(tokenpref > lastpref)
						{
							operators.push(token);
						}
					}
				}
			}

		}
		while(!operators.isEmpty())
		{
			if(operators.peek().equals("(") || operators.peek().equals(")"))
			{
				operators.pop();
				complete = false;
			}
			else
				theQ.enqueue(operators.pop());
		}
		if(complete)
			return theQ;
		else
		{
			System.out.println("Error: invalid syntax");
			return null;
		}
	}

	private int getOrder(String last) {
		// TODO Auto-generated method stub
		if(last.equals("+") || last.equals("-"))
			return 1;
		if(last.equals("*") || last.equals("/"))
			return 2;
		if(last.equals("^"))
			return 3;
		if(last.equals("("))
			return 4;
		return 0;
	}
}
