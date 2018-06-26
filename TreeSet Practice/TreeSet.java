public class TreeSet
{
	TreeNode root;
	int size = 0;

	public TreeSet()
	{
		root = null;
	}

	public void add(int v)
	{
		TreeNode node = new TreeNode(v);
		TreeNode current = root;
		boolean add = true;
		if (root == null)
		{
			root = node;
			add=false;
			size++;
		}
		while (add)
		{
			if (v>current.getValue() && current.getRight()==null)
			{
				current.setRight(node);
				add = false;
				size++;
			}
			else if (v<current.getValue() && current.getLeft()==null)
			{
				current.setLeft(node);
				add = false;
				size++;
			}
			else if (v==current.getValue())
			{
				add=false;
				size--;
			}
			else if (v>current.getValue())
			{
				current = current.getRight();
			}
			else
			{
				current = current.getLeft();
			}
		}
	}

	public int getSize()
	{
		return size;
	}

	public TreeNode getRoot()
	{
		return root;
	}

	public void traverseInOrder(TreeNode root)
	{
		if (root != null)
		{
			traverseInOrder(root.getLeft());
			System.out.print(root.getValue()+",");
			traverseInOrder(root.getRight());
		}
	}

	public void traversePreOrder(TreeNode root)
	{
		if (root !=null)
		{
			System.out.print(root.getValue()+",");
			traversePreOrder(root.getLeft());
			traversePreOrder(root.getRight());
		}
	}

	public void traversePostOrder(TreeNode root)
	{
		if (root !=null)
		{
			traversePostOrder(root.getLeft());
			traversePostOrder(root.getRight());
			System.out.print(root.getValue()+",");
		}
	}

	public void postOrderAdd(TreeNode root)
	{
		if (root !=null)
		{
			postOrderAdd(root.getLeft());
			postOrderAdd(root.getRight());
			this.add(root.getValue());
		}
	}

	public void preOrderAdd(TreeNode root)
	{
		if (root !=null)
		{
			this.add(root.getValue());
			preOrderAdd(root.getLeft());
			preOrderAdd(root.getRight());
		}

	}

	public void inOrderAdd(TreeNode root)
	{
		if (root != null)
		{
			inOrderAdd(root.getLeft());
			this.add(root.getValue());
			inOrderAdd(root.getRight());
		}
	}


	public class TreeNode
	{
		int value;
		TreeNode right;
		TreeNode left;

		public TreeNode(int n)
		{
			value = n;
			right = null;
			left = null;
		}

		public TreeNode(int n, TreeNode l, TreeNode r)
		{
			value = n;
			left = l;
			right = r;
		}

		public TreeNode getRight()
		{
			return right;
		}

		public TreeNode getLeft()
		{
			return left;
		}

		public void setRight(TreeNode r)
		{
			right = r;
		}

		public void setLeft(TreeNode l)
		{
			left = l;
		}

		public int getValue()
		{
			return value;
		}

		public String toString()
		{
			return ""+value;
		}
	}
}