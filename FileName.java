public class FileName{
	/* fnode class */
	public class fNode{
		String name;
		fNode next;
		//constructor
		public fNode(String i){
			name = i;
			next = null;
		}
	}
	
	//data members of the class (set to private)
	private fNode fhead = null;
	
	/* push name to the front of the list */
	public void fpush(String i){
		//System.out.println("PUSHING " + i);
		fNode temp = new fNode(i);
		temp.next = fhead;
		fhead = temp;
	}
	
	public void fpop(String i){
		fNode temp = fhead;
		fNode prev = temp;
		if(temp == null){
			System.out.print("\nCannot delete from empty stack\n\n");
			return;
		}
		
		//check front
		if((fhead.name).equals(i)){
			//System.out.print("\tPOP SUCCESS FRONT\n");
			fhead = fhead.next;
			return;
		}
		//check middle
		while(temp.next != null){
			if((temp.name).equals(i)){
				//System.out.print("\tPOP SUCCESS MIDDLE\n");
				prev.next = temp.next;
				temp = null;
				return;
			}
			prev = temp;
			temp = temp.next;
		}
		//check back
		if((temp.name).equals(i)){
			prev.next = null;
			//System.out.print("\tPOP SUCCESS BACK\n");
			return;
		}
		
		System.out.print("\t" + i +" was not in stack\n");
		return;
	}
	
	/* returns 1 if the name is already in the list */
	public int nameExists(String name){
        fNode temp = fhead;
		if(temp == null)
			return 0;
		
        while(temp != null) {
            if(temp.name.equals(name))
                return 1;
            temp = temp.next;
        }
		
        return 0;
    }

	/* print the linked list */
	public void printList(){
		fNode temp = fhead;
		if(temp == null){
			System.out.println("Empty");
			return;
		}
		while(temp != null){
			System.out.print(temp.name + ", ");
			temp = temp.next;
		}
		System.out.println();
	}
} 