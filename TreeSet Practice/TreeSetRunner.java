public class TreeSetRunner
{
	public TreeSetRunner()
	{
		TreeSet set = new TreeSet();

		//add 30 random values
		while (set.getSize()<30)
		{
			int rand = (int)(Math.random()*100)+1;
			set.add(rand);
		}
		System.out.println("Size: "+ set.getSize());

		//ADDING PREORDER
		TreeSet set2 = new TreeSet();
		set2.preOrderAdd(set.getRoot());

		System.out.println();
		System.out.println("PreOrder Add - Traverse PreOrder");
		set2.traversePreOrder(set2.getRoot());
		System.out.println();
		System.out.println("PreOrder Add - Traverse InOrder");
		set2.traverseInOrder(set2.getRoot());
		System.out.println();
		System.out.println("PreOrder Add - Traverse PostOrder");
		set2.traversePostOrder(set2.getRoot());
		
		//ADDING INORDER
		TreeSet set3 = new TreeSet();
		set3.inOrderAdd(set.getRoot());
		System.out.println();
		System.out.println();
		System.out.println("InOrder Add - Traverse PreOrder");
		set3.traversePreOrder(set3.getRoot());
		System.out.println();
		System.out.println("InOrder Add - Traverse InOrder");
		set3.traverseInOrder(set3.getRoot());
		System.out.println();
		System.out.println("InOrder Add - Traverse PostOrder");
		set3.traversePostOrder(set3.getRoot());
		
		//OBSERVATIONS OF INORDER
		System.out.println();
		System.out.println("Observations:");
		System.out.println("The InOrder and PreOrder traversals both go from least to greatest are always the same.");
		System.out.println("The PostOrder traversal goes from greatest to least.");
		
		
		//ADDING POSTORDER
		TreeSet set4 = new TreeSet();
		set4.postOrderAdd(set.getRoot());
		System.out.println();
		System.out.println();
		System.out.println("PostOrder Add - Traverse PreOrder");
		set4.traversePreOrder(set4.getRoot());
		System.out.println();
		System.out.println("PostOrder Add - Traverse InOrder");
		set4.traverseInOrder(set4.getRoot());
		System.out.println();
		System.out.println("PostOrder Add - Traverse PostOrder");
		set4.traversePostOrder(set4.getRoot());
		
		//OBSERVATIONS OF POSTORDER
		System.out.println();
		System.out.println("Observations:");
		System.out.println("The InOrder traversal goes from least to greatest.");
		System.out.println("The PostOrder traversal has the larger numbers in the middle and the smallest numbers on either side.");
		System.out.println("The first half of the numbers are in ascending order and the 2nd half are in descending order.");
	}

	public static void main(String[] args)
	{
		TreeSetRunner app = new TreeSetRunner();
	}
}