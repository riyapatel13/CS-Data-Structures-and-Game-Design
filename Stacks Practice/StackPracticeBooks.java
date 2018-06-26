import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.Stack;
import java.util.Iterator;

public class StackPracticeBooks
{
	public static void main(String[] args)
	{
		File file = new File("100BestSciFi21stCentury.txt");
		Stack<Book> stack = new Stack<Book>();

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(file));

			String text = "";
			String output = "";
			int count = 1;
			Book book = new Book();

			while((text = input.readLine()) != null)
			{
				if ((count-2)%7==0)
				{
					book = new Book();
					//make new Object. add title. %1 add author. %2 rating and numRatings. %3 score & ppl voted. CHECK
					book.setTitle(text);
				}
				if ((count-2)%7==2)
				{
					book.setAuthor(text.substring(3));
				}
				if ((count-2)%7==3)
				{
					book.setRating(text.substring(0,4));
					String tempLine = text;
					tempLine = tempLine.replace(" avg rating — ",";");
					tempLine = tempLine.replace(" ratings","");
					book.setNumRatings(tempLine.substring(5));
				}
				if ((count-2)%7==4)
				{
					String line = text;
					line = line.replace("score: ", "");
					int commacount=0;
					for (int i=0; i<line.length(); i++)
					{
						if (line.substring(i,i+1).equals(","))
							commacount++;
						if (commacount==2)
						{
							book.setScore(line.substring(0,i));
							line=line.substring(i+1);
							commacount++;
						}
						if (commacount==3)
						{
							line = line.replace(" and ","");
							line = line.replace(" people voted","");
							book.setNumVotes(line);
						}
					}
				}
				if ((count-2)%7==5)
				{
					stack.push(book);
				}
				count++;
			}

			int numBooks = 0;
			while(!stack.empty())
			{
				Book tempBook = stack.pop();
				if ((tempBook.getAuthorFirstName().substring(0,1).equals("E"))||(tempBook.getAuthorFirstName().substring(0,1).equals("H"))||(tempBook.getAuthorFirstName().substring(0,1).equals("P"))||(tempBook.getAuthorFirstName().substring(0,1).equals("S"))||(tempBook.getAuthorLastName().substring(0,1).equals("E"))||(tempBook.getAuthorLastName().substring(0,1).equals("H"))||(tempBook.getAuthorLastName().substring(0,1).equals("P"))||(tempBook.getAuthorLastName().substring(0,1).equals("S")))
				{
					System.out.println(tempBook.getAuthorLastName()+", "+tempBook.getAuthorFirstName()+ " - "+tempBook.getTitle()+"; "+tempBook.getRating());
					numBooks++;
				}
			}
			System.out.println("Number of Books: "+numBooks);

		}catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}
}