public class DoublyLinkedList
{

	ListNode root/* = new ListNode()*/;
	ListNode end;

	//list reversed, add (at random places), remove, sort

	public DoublyLinkedList()
	{
	}

	public void add(int n)
	{
		ListNode node = new ListNode(n);
		//ListNode last = root;
		node.setNext(null);
		if(root == null)
		{
			node.setPrevious(null);
			root = node;
   		}
   		else
   		{
			ListNode last = root;
			while(last.getNext() != null)
				last = last.getNext();

			last.setNext(node);
			node.setPrevious(last);
		}
	}

	public void add(int newVal, int index) //out of bounds exception check
	{
		ListNode newNode = new ListNode(newVal);
		ListNode locNode = root;
		for (int i=0; i<index-1; i++)
			locNode = locNode.getNext();

		newNode.setNext(locNode.getNext());
		locNode.setNext(newNode);
		newNode.setPrevious(locNode);
		newNode.getNext().setPrevious(newNode);
	}

	public ListNode getEnd()
	{
		ListNode last = root;
		while(last.getNext() != null)
			last = last.getNext();
		end = last;
		return end;
	}

	public ListNode getRoot()
	{
		return root;
	}

	public boolean contains(int newVal)
	{
		ListNode current = root;
		while(current.getNext() != null)
		{
			if (current.getValue() == newVal)
				return true;
			current = current.getNext();
		}
		return false;
	}

	public int size()
	{
		int count = 1;
		ListNode last = root;
		if (root == null)
			return 0;
		while(last.getNext() != null)
		{
			last = last.getNext();
			count++;
		}
		return count;
	}

	//root is null and end is null for clear
	public void clear()
	{
		root = null;
		end = null;
	}

	public String toReversedString() //not working
	{
		String str = "[";
		ListNode current = end;
		while(current != null)
		{
			str += current.getValue() + ",";
			current = current.getPrevious();
		}
		if (str.length()>1)
			str = str.substring(0,str.length()-1);
		str += "]";
		return str;
	}

	public String toString()
	{
		String str = "[";
		ListNode current = root;
        while(current != null)
        {
            str += current.getValue() + ",";
            current = current.getNext();
        }
        if (str.length()>1)
			str = str.substring(0,str.length()-1);
		str += "]";
        return str;
	}

	/*public static void main(String[] args)
	{
		DoublyLinkedList dll = new DoublyLinkedList();
	}*/

	public class ListNode
	{
		int value;
		ListNode previous;
		ListNode next;

		public ListNode()
		{
		}

		public ListNode(int v)
		{
			value = v;
		}

		public int getValue()
		{
			return value;
		}

		public ListNode getNext()
		{
			return next;
		}

		public void setNext(ListNode n)
		{
			next = n;
		}

		public ListNode getPrevious()
		{
			return previous;
		}

		public void setPrevious(ListNode p)
		{
			previous = p;
		}

		public boolean hasPrevious() //this might not work
		{
			if(getPrevious()!=null)
				return true;
			return false;
		}

		public boolean hasNext()
		{
			if(getNext()!=null)
				return true;
			return false;
		}
	}
}