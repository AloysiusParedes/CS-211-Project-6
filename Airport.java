/* file to hold airport class and functions */

public class Airport {
	/* node class */
	public class Node{
		int element;
		boolean visited;
		Node next;
		//constructor
		public Node(int i, boolean j){
			element = i;
			visited = j;
			next = null;
		}
	}
		
	//data members of the class (set to private)
	private Node head = null;

	public Airport(){
		head = null;
	}

	/* return 1 if stack is empty */
	public int isEmpty(){
		if(head == null)
			return 1;
		else
			return 0;
	}

	/* checks to see if value is within the list */
	public int contains(int x){
		Node temp = head;
		if(head == null)
			return 0;
		while(temp != null){
			if(temp.element == x)
				return 1;
			temp = temp.next;
		}
		return 0;
	}
	
	/* will empty the entire linked list */
	public void emptyList(){
		head = null;
	}
	
	/* add the data to the front of the list */
	public void push(int i, boolean j){
		Node tmp = new Node(i, j);
		tmp.next = head;
		head = tmp;
	}

	/* return the data value in front of the list */
	public int top(){
		if(head != null)
			return head.element;
		else
			return -999;
	}

	/* remove the data value from the top of the stack */
	public void pop(int x){
		Node temp = head;
		Node prev = temp;
		if(this.isEmpty() == 1){
			System.out.print("\nCannot delete from empty stack\n\n");
			return;
		}
		
		//check front
		if(head.element == x){
			//System.out.print("\tPOP SUCCESS FRONT\n");
			head = head.next;
			return;
		}
		//check middle
		while(temp.next != null){
			if(temp.element == x){
				//System.out.print("\tPOP SUCCESS MIDDLE\n");
				prev.next = temp.next;
				temp = null;
				return;
			}
			prev = temp;
			temp = temp.next;
		}
		//check back
		if(temp.element == x){
			prev.next = null;
			//System.out.print("\tPOP SUCCESS BACK\n");
			return;
		}
		
		System.out.print("\t" + x +" was not in stack\n");
		return;
	}
	
	/* marks all nodes in list as unvisited */
	public void unvisitList(){
		Node temp = head;
		if(temp == null)
			return;
		while(temp != null){
			temp.visited = false;
			temp = temp.next;
		}
		return;
	}

	/* prints values in the list */
	public void printList(){
		Node temp = head;
		if(this.isEmpty() == 1){
			System.out.print("Empty");
		}
		while(temp != null){
			System.out.print(temp.element + ", ");
			/* if(temp.visited == true)
				System.out.print("visited");
			else
				System.out.print("not visited"); */
			temp = temp.next;
		}
		System.out.println();
	}

	public Node listHead(){
		if(head == null)
			return null;
		return head;
	}
}