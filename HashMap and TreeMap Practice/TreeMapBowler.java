import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Map.Entry;


public class TreeMapBowler
{
	public static void main(String[] args)
	{
		File name = new File("BowlingData.txt");
		TreeMap<Integer,PriorityQueue<Bowler>> map = new TreeMap<Integer,PriorityQueue<Bowler>>();
		Bowler bowler = new Bowler();

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));

			String text="";
			while( (text=input.readLine())!= null)
			{
				//System.out.println(text);

				String[] arr = text.split(" ");

				bowler = new Bowler();
				bowler.setFirstName(arr[0]);
				bowler.setLastName(arr[1]);
				bowler.setScore(Integer.valueOf(arr[2]));

				if (!map.containsKey(bowler.getScore()))
				{
					map.put(bowler.getScore(), new PriorityQueue<Bowler>());
				}
				map.get(bowler.getScore()).add(bowler);
			}
		}catch (IOException io)
		{
			System.err.println("File does not exist");
		}

		Set<Entry<Integer,PriorityQueue<Bowler>>> entrySet = map.entrySet();
	        for (Entry<Integer,PriorityQueue<Bowler>> entry : entrySet) 
	        {
			int score = entry.getKey();
			System.out.print(score+": ");
			PriorityQueue<Bowler> pq = entry.getValue();
			while (pq.peek()!=null)
			{
				System.out.print(pq.poll()+"; ");
			}
			System.out.println();
		}
	}
}