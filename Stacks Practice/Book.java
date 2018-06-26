public class Book
{
	String author, title, numRatings, numVotes, score, rating;

	public Book()
	{
	}

	public String getAuthorFirstName()
	{
		return author.substring(0,author.indexOf(" "));
	}

	public String getAuthorLastName()
	{
		return author.substring(author.indexOf(" ")+1);
	}

	public String getTitle()
	{
		return title;
	}

	public String getRating()
	{
		return rating;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setRating(String rating)
	{
		this.rating = rating;
	}
	public void setNumRatings(String numRatings)
	{
		this.numRatings = numRatings;
	}
	public void setNumVotes(String numVotes)
	{
		this.numVotes = numVotes;
	}
	public void setScore(String score)
	{
		this.score = score;
	}

}