import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

public class HashSetTask
{
	public static void main(String[] args)
	{
		//ADDING HASH SETS TO ARRAYLIST

		ArrayList<HashSet<Integer>> arr = new ArrayList<HashSet<Integer>>();

		for (int i=0; i<(int)(Math.random()*10)+2; i++)
		{
			HashSet<Integer> set = new HashSet<Integer>();

			while (set.size()<20)
			{
				set.add((int)(Math.random()*60)+1);
			}

			arr.add(set);
		}

		//INTERSECTION OF ALL SETS

		HashSet<Integer> combinedSet = new HashSet<Integer>();

		for (int i=0; i<arr.size()-1; i++)
		{
			if (i==0)
				combinedSet = intersect(arr.get(i), arr.get(i+1));
			else
			{
				combinedSet = intersect(combinedSet, arr.get(i+1));
			}
		}

		HashSet<Integer> combinedEvenSet = new HashSet<Integer>();

		for (int i=0; i<arr.size()-1; i++)
		{
			if (i==0)
				combinedEvenSet = evenIntersect(arr.get(i), arr.get(i+1));
			else
			{
				combinedEvenSet = evenIntersect(combinedEvenSet, arr.get(i+1));
			}
		}


		//UNION OF ALL HASHSETS

		HashSet<Integer> halfSet1 = new HashSet<Integer>();
		HashSet<Integer> halfSet2 = new HashSet<Integer>();

		for (int i=0; i<arr.size()/2; i++)
		{
			for (Integer s : arr.get(i))
			{
				halfSet1.add(s);
			}
		}

		for (int i=arr.size()/2; i<arr.size(); i++)
		{
			for (Integer s : arr.get(i))
			{
				halfSet2.add(s);
			}
		}

		HashSet<Integer> uSet = union(halfSet1, halfSet2);
		HashSet<Integer> euSet = union(halfSet1, halfSet2);

		//PRINT STATEMENTS

		System.out.println("HASH SETS:");
		for (int i=0; i<arr.size(); i++)
		{
			for (Integer s : (HashSet<Integer>)(arr.get(i)))
			{
				System.out.print(s+", ");
			}
			System.out.println();
		}

		System.out.println("INTERSECTION OF ALL HASH SETS:");
		for (Integer s : (HashSet<Integer>)(combinedSet))
		{
			System.out.print(s+", ");
		}
		System.out.println();

		System.out.println("EVEN INTERSECTION OF ALL HASH SETS:");
		for (Integer s : (HashSet<Integer>)(combinedEvenSet))
		{
			System.out.print(s+", ");
		}
		System.out.println();

		System.out.println("UNION OF ALL HASH SETS:");
		for (Integer s : uSet)
		{
			System.out.print(s+", ");
		}
		System.out.println();

		System.out.println("EVEN UNION OF ALL HASH SETS:");
		for (Integer s : euSet)
		{
			System.out.print(s+", ");
		}
		System.out.println();

	}

	//METHODS

	public static HashSet<Integer> intersect(HashSet<Integer> set1, HashSet<Integer> set2)
	{
		HashSet<Integer> combinedSet = new HashSet<Integer>();

		for (Integer x : set1)
		{
			for (Integer y : set2)
			{
				if (y==x)
				{
					combinedSet.add(y);
				}
			}
		}
		return combinedSet;
	}

	public static HashSet<Integer> union(HashSet<Integer> set1, HashSet<Integer> set2)
	{
		HashSet<Integer> unionSet = new HashSet<Integer>();

		unionSet.addAll(set1);
		unionSet.addAll(set2);

		return unionSet;
	}

	public static HashSet<Integer> evenIntersect(HashSet<Integer> set1, HashSet<Integer> set2)
	{
		HashSet<Integer> combinedSet = new HashSet<Integer>();

		for (Integer x : set1)
		{
			for (Integer y : set2)
			{
				if (y==x && y%2==0)
				{
					combinedSet.add(y);
				}
			}
		}
		return combinedSet;
	}

	public static HashSet<Integer> evenUnion(HashSet<Integer> set1, HashSet<Integer> set2)
	{
		HashSet<Integer> unionSet = new HashSet<Integer>();

		for (Integer x : set1)
		{
			if(x%2==0)
				unionSet.add(x);
		}

		for (Integer x : set2)
		{
			if(x%2==0)
				unionSet.add(x);
		}
		return unionSet;
	}
}