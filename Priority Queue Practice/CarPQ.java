import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Stack;

public class CarPQ
{
	public static void main(String[] args)
	{

		File file = new File("CarData.txt");
		String output = "";
		Stack<Car> stack = new Stack<Car>();
		Queue<Car> q = new LinkedList<Car>();
		PriorityQueue<Car> pq = new PriorityQueue<Car>();
		Car car = new Car();
		int lineNum = 1;

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(file));
			String text = "";

			while((text = input.readLine()) != null)
			{
				output += text;

				if (lineNum>1)
				{
					String[] arr = text.split("\t");

					car = new Car();

					car.setCarID(arr[0]);
					car.setMpg(arr[1]);
					car.setEngineSize(arr[2]);
					car.setHp(arr[3]);
					car.setWeight(arr[4]);
					car.setAcceleration(arr[5]);
					car.setCountry(arr[6]);
					car.setCylinders(arr[7]);

					stack.push(car);
					q.add(car);
					pq.add(car);
				}

				lineNum++;
			}


			/*while(!stack.empty())
			{
				System.out.println(stack.pop());
			}*/

			/*while (q.peek()!=null)
			{
				System.out.println(q.poll());
			}*/

			while (pq.peek()!=null && !stack.empty() && q.peek()!=null)
			{
				System.out.println(stack.pop()+"\t\t"+q.poll()+"\t\t"+pq.poll());
				System.out.println();
			}



		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}
}