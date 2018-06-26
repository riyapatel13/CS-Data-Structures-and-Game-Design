import java.util.Stack;

public class StackPracticeBinary
{
	public static void main(String[] args)
	{
		Stack<Integer> stack = new Stack<Integer>();
		int input = 5825;

		while(input != 0)
		{
			stack.push(input%2);
			input = input/2;
		}

		while(!stack.empty())
		{
			System.out.print(stack.pop());
		}
		System.out.println();
	}
}