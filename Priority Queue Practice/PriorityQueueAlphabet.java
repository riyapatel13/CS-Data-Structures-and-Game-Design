import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;

public class PriorityQueueAlphabet
{
	public static void main(String[] args)
	{
		File file = new File("RandomParagraph.txt");
		String output = "";
		Queue<Word> q = new LinkedList<Word>();
		PriorityQueue<Word> pq = new PriorityQueue<Word>();
		Word word = new Word();

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(file));

			String text = "";

			while((text = input.readLine()) != null)
			{
				output += text;
			}

			String[] wordList = output.split(" ");
			for (int i=0; i<wordList.length; i++)
			{
				wordList[i] = wordList[i].replace(",","");
				wordList[i] = wordList[i].replace(".","");
				word = new Word();
				word.setWord(wordList[i]);
				q.add(word);
				pq.add(word);

			}

			while (q.peek()!=null && pq.peek()!=null)
			{
				System.out.println(q.poll()+"\t\t\t"+pq.poll());
			}



		}catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}
}