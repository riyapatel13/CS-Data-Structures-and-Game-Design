import java.util.Stack;

public class StackPracticeReverseString
{
	public static void main(String[] args)
	{
		Stack<String> stack = new Stack<String>();
		String input = "Hello, my name is Riya.";

		for(int i=0; i<input.length(); i++)
		{
			stack.push(input.substring(i,i+1));
		}

		while(!stack.empty())
		{
			System.out.print(stack.pop());
		}
		System.out.println();
	}
}