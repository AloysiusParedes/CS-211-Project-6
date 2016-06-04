import java.io.*;
import java.util.*;


public class apared5proj6{
	private static Airport[] network;
	private static FileName filenames;
	
	public static void main(String[] args){
		int i = 0;
		/* create and initialist array of 10 linked lists */
		network = new Airport[1];
		for(i = 0; i < 1; i++)
			network[i] = new Airport();
		filenames = new FileName();

		// set up an instance of the BufferedReader class to read from standard input
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));

		// set up the data needed for the airport adjcency list
		apared5proj6 airportData = new apared5proj6();

		// call the method that reads and parses the input
		airportData.processCommandLoop (br);

		System.out.println ("Goodbye");
	}
  
	public void processCommandLoop (BufferedReader br){
		try{  //try-catch clauses are needed since BufferedReader and Scanner classes throw exceptions on errors
			String inline = br.readLine();   // get a line of input
			Scanner sc;

			// loop until all lines are read from the input
			while(inline != null){
				sc = new Scanner (inline);   // process each line of input using the Scanner iterators
				String command;
				if(sc.hasNext()){
					command = sc.next();
					//System.out.println ("*" + command + "*");
				}
				else
					command = " ";

				if (command.equals("q") == true){
					System.out.print("\n\nExiting Program...\n\n");
					System.exit(1);
				}
				else if (command.equals("?") == true)
					showCommands();
				else if (command.equals("t") == true)
					doTravel(sc);
				else if (command.equals("r") == true)
					doResize(sc);
				else if (command.equals("i") == true)
					doInsert(sc);
				else if (command.equals("d") == true)
					doDelete(sc);
				else if (command.equals("l") == true)
					doList(sc);
				else if (command.equals("f") == true)
					doFile(sc);
				else if (command.equals("#") == true)
					;
				else if (command.equals(" ") == true)
					;
				else
					System.out.println ("Command is not known: " + command);

				inline = br.readLine();   // get the next line of input
			}
		}
		catch (IOException ioe){
			System.out.println ("Error in Reading - Assuming End of File");
		}
	}
  
	/* '?' command */
	public void showCommands(){
		System.out.println ("The commands for this project are:");
		System.out.println ("  q ");
		System.out.println ("  ? ");
		System.out.println ("  # ");
		System.out.println ("  t <int1> <int2> ");
		System.out.println ("  r <int> ");
		System.out.println ("  i <int1> <int2> ");
		System.out.println ("  d <int1> <int2> ");
		System.out.println ("  l ");
		System.out.println ("  f <filename> ");
	}
  
	/* 't' command */
	public void doTravel(Scanner sc){
		int val1 = 0, val2 = 0;

		if( sc.hasNextInt() == true )
			val1 = sc.nextInt();
		else{
			System.out.println ("Integer value expected");
			return;
		}

		if( sc.hasNextInt() == true )
			val2 = sc.nextInt();
		else{
			System.out.println ("Integer value expected");
			return;
		}
		
		/* check to see if val1 is within the array */
		if(val1 > network.length || val1 - 1 < 0){
			System.out.println ("\tERROR: " + val1 + " does not exist within airport network\n");
			return;
		}
		
		/* check to see if val2 is within the array */
		if(val2 - 1 > network.length || val2 - 1 < 0){
			System.out.println ("\tERROR: " + val2 + " does not exist within airport network\n");
			return;
		}
		
		System.out.println ("Performing the Travel Command from " + val1 + " to " + val2);
		
		/* check to see if you can travel from airport val1 to airport val2 */
		depthFirstSearchHelper(val1, val2);
	}
  
	/* 'r' command */
	public void doResize(Scanner sc){
		int val1 = 0, i = 0;

		if( sc.hasNextInt() == true )
			val1 = sc.nextInt();
		else{
			System.out.println ("\tERROR: Integer value expected");
			return;
		}
		
		/* check to see if val1 is not greater than 0 */
		if(val1 <= 0){
			System.out.println ("\tERROR: Integer is supposed to be greater than 0!!");
			return;
		}
		
		/* remove all values from the traffic network */
		for(i = 0; i < network.length; i++){
			network[i].emptyList();
		} 
		
		/* check to see if val1 is greater than current size of array */
		while(val1 > network.length){
			/* grow baby grow */
			//allocate new array for new size
			Airport[] newNetwork = new Airport[val1];
			//allocate each index of array as a linked list
			for(i = 0; i < val1; i++)
				newNetwork[i] = new Airport();
			/* //copy over old values to new array
			for(i = 0; i < network.length; i++)
				newNetwork[i] = network[i]; */
			//set new identifier
			network = newNetwork;	
		}

		System.out.println ("Performing the Resize Command with " + val1);
	}

	/* 'i' command */
	public void doInsert(Scanner sc){
		int val1 = 0, val2 = 0, i = 0;

		if( sc.hasNextInt() == true )
			val1 = sc.nextInt();
		else{
			System.out.println ("\tERROR: Integer value expected");
			return;
		}

		if( sc.hasNextInt() == true )
			val2 = sc.nextInt();
		else{
			System.out.println ("\tERROR: Integer value expected");
			return;
		}
		
		/* check to see if val1 is within the array */
		if(val1 > network.length || val1 - 1 < 0){
			System.out.println ("\tERROR: " + val1 + " does not exist within airport network\n");
			return;
		}
		
		/* check to see if val2 is within the array */
		if(val2 - 1 > network.length || val2 - 1 < 0){
			System.out.println ("\tERROR: " + val2 + " does not exist within airport network\n");
			return;
		}
		
		/* check to see if val2 is a duplicate within that network */
		if(network[val1 - 1].contains(val2) == 1){
			System.out.println("\tERROR: Cannot insert duplicate- " + val2);
			return;
		}
		
		/* push airport in the corresponding index of array */
		network[val1 - 1].push(val2, false);
		
		System.out.println ("Performing the Insert Command from " + val1 + " to " + val2);
	}

	/* 'r' command */
	public void doDelete(Scanner sc){
		int val1 = 0, val2 = 0, i = 0;

		if( sc.hasNextInt() == true )
			val1 = sc.nextInt();
		else{
			System.out.println ("\tERROR: Integer value expected");
			return;
		}

		if( sc.hasNextInt() == true )
			val2 = sc.nextInt();
		else{
			System.out.println ("\tERROR: Integer value expected");
			return;
		}
		
		/* check to see if val1 is within the array */
		if(val1 > network.length || val1 - 1 < 0){
			System.out.println ("\tERROR: " + val1 + " does not exist within airport network\n");
			return;
		}
		
		/* check to see if val2 is within the array */
		if(val2 - 1 > network.length || val2 - 1 < 0){
			System.out.println ("\tERROR: " + val2 + " does not exist within airport network\n");
			return;
		}
		
		/* pop airport from the corresponding index of array */
		network[val1 - 1].pop(val2);

		System.out.println ("Performing the Delete Command from " + val1 + " to " + val2);
	}

	/* 'l' command */
	public void doList(Scanner sc){
		int i = 0;
		for(i = 0; i < network.length; i++){
			System.out.print((i + 1) + ": ");
			network[i].printList();
		}
	}

	/* 'f' command */
	public void doFile(Scanner sc){
		String fname = null;

		if ( sc.hasNext() == true )
			fname = sc.next();
		else{
			System.out.println ("\tERROR: Filename expected");
			return;
		}
		
		System.out.println ("Performing the File command with file: " + fname);
		/*
		// next steps:(print an error message and return if any step fails) 
		//  1. verify the file name is not currently in use
		//  2. open the file
		//  3. create a new instance of BufferedReader
		//        BufferedReader br = new BufferedReader (new FileReader ("MyFileReader.txt"));
		//  4. recursively call processCommandLoop() with this new instance of BufferedReader as the parameter
		//  5. close the file when processCommandLoop() returns
		*/
		
		/* verify the file name is not currently in use */
		if(filenames.nameExists(fname) == 1){
			//filenames.printList();
			System.out.println("\t\n\nERROR: " + fname + " currently in use.\n\n");
			return;
		}
		//add fname to linked list
		filenames.fpush(fname);
		//create a new instance of BufferedReader
		BufferedReader br = null;
		
		try{
			br = new BufferedReader(new FileReader(fname));
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("\tERROR: File does not exist");
		}
		finally{
			//recursibely call processCommandLoop() with new instance of BufferedReader as the parameter
			processCommandLoop(br);
			
			//remove the name from the linked list
			filenames.fpop(fname);
			try{
				if(br != null)
					//close the file when processCommandLoop() returns;
					br.close();
			}
			catch(IOException ex){
				ex.printStackTrace();
				System.out.println("\tERROR: File does not exist");
			}
		}
	}
  
	/* function to see if airport is able to transport from airport x to airport y */
	void depthFirstSearchHelper(int x, int y){
		int i = 0;
		//mark all airports as unvisited
		for(i = 0; i < network.length; i++){
			network[i].unvisitList();
		}
		//check if dfs is true
		if(dfs(x, y) == true)
			System.out.println("\tYou can get from airport " + x + " to airport " + y + " in one or more flights\n");
		else
			System.out.println("\tYou can NOT get from airport " + x + " to airport " + y + " in one or more flights\n");
	}
  
	/* returns true if able to go from airport a to airport b. returns false if not able to go from airport a to airport b */
	boolean dfs(int a, int b){
		Airport.Node c = network[a - 1].listHead();
		
		//list is empty, so cannot travel anywhere
		if(c == null)
			return false;
		//list is not empty; look at all possible airports that can travel to
		while(c != null){
			//see if that is a possible airport that can travel to
			if(c.element == b)
				return true;
			//see if that airport is visited
			if(c.visited == false){
				//mark as visited
				c.visited = true;
				//recursive call
				if(dfs(c.element, b) == true)
					return true;
			}
			//look at the next airport
			c = c.next;
		}
		//airport not found
		return false;
	}
	
}