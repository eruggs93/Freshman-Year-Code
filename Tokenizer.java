/*************************************************************************
 * Eric Ruggieri
 * Jython
 * Date submitted 11/18/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation: javac Tokenizer.java 
 *  Execution: java Jython
 *  Dependencies: stlib.jar
 * 
 *************************************************************************/
public class Tokenizer 
{
	private String theLine;
	private int lineIndex;
	private boolean done;

	public Tokenizer(String input)
	{
		theLine = input;
		lineIndex = 0;
		done = false;
	}

	public String getToken()
	{
		final int CODE = 0;
		final int P = 1;
		final int PR = 2;
		final int PRI = 3;
		final int PRIN = 4;
		final int PRINT = 5;
		final int LETTER = 6;
		final int NUMBER = 7;
		int state = CODE;
		int hold = lineIndex;
		while(lineIndex != theLine.length()) 
		{
			char c = theLine.charAt(lineIndex);
			switch(state)
			{
			case CODE:
				if((int)c == 112)
				{
					state = P;
					lineIndex++;
					continue;

				}
				else if((int)c == 32)
				{
					lineIndex++;
					continue;
				}
				else if(((int)c >= 65 &&  (int)c <= 90) || ((int)c >= 97 &&  (int)c <= 122))
				{
					state = LETTER;
					lineIndex++;
					continue;
				}
				else if(((int)c >= 48 &&  (int)c <= 57) || (int)c == 46)
				{
					state = NUMBER;
					lineIndex++;
					continue;
				}
				else if(((int)c >= 40 &&  (int)c <= 47) || (int)c == 37 || (int)c == 94 || (int)c == 61)
				{
					lineIndex++;
					return (theLine.substring(hold, lineIndex)).trim();
				}
				break;
			case P: 
				if((int)c == 114)
				{
					state = PR;
					lineIndex++;
					continue;
				}
				else if(((int)c >= 65 &&  (int)c <= 90) || ((int)c >= 97 &&  (int)c <= 122) || (int)c >= 48 &&  (int)c <= 57)				
				{
					state = LETTER;
					lineIndex++;
					continue;
				}
				break;
			case PR: 
				if((int)c == 105)
				{
					state = PRI;
					lineIndex++;
					continue;
				}
				else if(((int)c >= 65 &&  (int)c <= 90) || ((int)c >= 97 &&  (int)c <= 122) || (int)c >= 48 &&  (int)c <= 57)				
				{
					state = LETTER;
					lineIndex++;
					continue;
				}
				break;
			case PRI:
				if((int)c == 110)
				{
					state = PRIN;
					lineIndex++;
					continue;

				}
				else if(((int)c >= 65 &&  (int)c <= 90) || ((int)c >= 97 &&  (int)c <= 122) || (int)c >= 48 &&  (int)c <= 57)				
				{
					state = LETTER;					
					lineIndex++;
					continue;
				}
				break;
			case PRIN: 
				if((int)c == 116)
				{
					state = PRINT;
					lineIndex++;
					continue;
				}
				else if(((int)c >= 65 &&  (int)c <= 90) || ((int)c >= 97 &&  (int)c <= 122) || (int)c >= 48 &&  (int)c <= 57)
				{
					state = LETTER;
					lineIndex++;
					continue;
				}
				break;
			case PRINT:
				if(((int)c >= 65 &&  (int)c <= 90) || ((int)c >= 97 &&  (int)c <= 122) || ((int)c >= 48 &&  (int)c <= 57))
				{
					state = LETTER;
					lineIndex++;
					continue;
				}
				if(((int)c >= 42 &&  (int)c <= 47) || (int)c == 37 || (int)c == 94)
				{
					return "ERROR";
				}
				state = CODE;
				return (theLine.substring(hold, lineIndex)).trim();
			case LETTER:
				if(((int)c >= 41 &&  (int)c <= 47) || (int)c == 37 || (int)c == 94 || (int)c == 32)
				{
					state = CODE;
					return (theLine.substring(hold, lineIndex)).trim();
				}
				else if(((int)c >= 48 &&  (int)c <= 57) || ((int)c >= 65 &&  (int)c <= 90) || ((int)c >= 97 &&  (int)c <= 122))
				{
					lineIndex++;
					continue;
				}
				else
				{
					lineIndex++;
					return (theLine.substring(hold, lineIndex)).trim();
				}
			case NUMBER:
				if((int)c >= 48 &&  (int)c <= 57 || (int)c == 46)
				{
					if(c == '.' && (theLine.charAt(lineIndex-1) == '.' || theLine.charAt(lineIndex-2) == '.'))
					{
							System.out.println("Error: invalid syntax");
							return "ERROR";
					}
					if(lineIndex + 1 == theLine.length())
					{
						lineIndex++;
						return theLine.substring(hold, lineIndex).trim();
					}
					else
					{
						lineIndex++;
						continue;
					}
				}
				if(((int)c >= 41 &&  (int)c <= 47) || (int)c == 37 || (int)c == 94 || (int)c == 32)
				{
					state = CODE;
					return theLine.substring(hold, lineIndex).trim();
				}
				else
					return "ERROR";
			}
		}
		return theLine.substring(hold, lineIndex).trim();
	}

	public boolean done()
	{
		if(lineIndex == theLine.length())
			done = true;
		else
			done = false;
		return done;
	}

}
