public class Word implements Comparable<Word>
{
	String word;

	public Word()
	{}

	public String toString()
	{
		return word;
	}

	public void setWord(String w)
	{
		word = w;
	}

	public int compareTo (Word otherWord)
	{
		if (word.compareTo(otherWord.toString())<0)
			return -1;
		if (word.compareTo(otherWord.toString())>0)
			return 1;
		return 0;
	}
}