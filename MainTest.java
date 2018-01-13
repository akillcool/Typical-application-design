import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class MainTest{
	list<Member> l = new list<Member>();
	Stack<Member> stk = new Stack<Member>();
	Queue<Member> que = new Queue<Member>();
	SQueue<Member> sque = new SQueue<Member>();
    Random rnd = new Random();
    Scanner scn = new Scanner(System.in);
	public static void main(String[] agrs){
		MainTest app = new MainTest();
		while(true){
			System.out.printf("Enter H/h/? for help, or command :");
			String s = app.scn.nextLine();
			int exitflag = 0;
			if (s != null && s != "" ){
			switch(s.trim().toUpperCase().charAt(0)){
				case 'H': app.showMenu();break;
				case '?': app.showMenu();break;
				case 'E': exitflag = 1;break;
				case 'G': app.fillList();break;
				case 'S': app.display("Stack");break;
				case 'Q': app.display("Queue");break;
				case 'O': app.display("Sorted Queue");break;
				case 'D': app.remove();break;
				case 'I': app.addNode();break;
				default:app.showMenu();
			}
			}else {
				System.out.println("");
			}
			if(exitflag == 1)
				break;
		}
	}
	
	void showMenu(){
		System.out.println("=====================================================");
		System.out.println("|      CS 394 Assignment:Linked List Structures     |");
		System.out.println("=====================================================");
	    System.out.println("| G: Ask for a N, and generate N members of mixed   |");
	    System.out.println("|    kinds and put them into three list structures. |");
	    System.out.println("|    Make sure you destroy the lists before create  |");
	    System.out.println("|    new ones if the lists are not empty.           |");
	    System.out.println("-----------------------------------------------------");
	    System.out.println("| S: List members in stack, 1 memebr per line,      |");
	    System.out.println("|    20 members per screen with header line, allow  |");
	    System.out.println("|    Q/q to quit listing after each 20 members.     |");
	    System.out.println("-----------------------------------------------------");
	    System.out.println("| Q: List members in queue (same requirements).     |");
	    System.out.println("-----------------------------------------------------");
	    System.out.println("| O: List members in ordered queue sorted by SSN    |");
	    System.out.println("|    (same requirements).Sorted by the id of member |");
	    System.out.println("|                                                   |");
	    System.out.println("-----------------------------------------------------");
	    System.out.println("| D: Remove first element from queue, pop member    |");
	    System.out.println("|    from stack, and delete the same member from    |");
	    System.out.println("|    sorted queue as the one removed from stack.    |");
	    System.out.println("|                                                   |");
	    System.out.println("-----------------------------------------------------");
	    System.out.println("| I: Randomly generate new member, and put          |");
	    System.out.println("|    the object into the three structures. Print    |");
	    System.out.println("|    out the new member added in.                   |");
	    System.out.println("-----------------------------------------------------");
	    System.out.println("| H/?: Display this menu.                           |");
	    System.out.println("| E: Exit                                           |");
	    System.out.println("=====================================================");
	}
	
	void addNode(){
		 Member v = null;
		 switch(Member.rnd.nextInt(4)){
	    	case 0 : v = new Member();break;
	    	case 1 : v = new Student();break;
	    	case 2 : v = new Employee();break;
	    	case 3 : v = new Faculty();break;
	    	case 4 : v = new Staff();break;
		}	 
		stk.push(v);
     	que.enque(v);
     	sque.enque(v);
     	System.out.printf("\n\t\t%s is pushed in from stack and enqued in from queue and sorted queue.\n",v);
	}
	
	void fillList(){
	    Member v = null;
	    int Count = 0;
	    System.out.printf("please Enter N to generate a list structures:");
	    try{
	    	Count = Integer.parseInt(in.nextLine());
	    	while(stk.count != 0){
		    	stk.remove(stk.count - 1);
		    }
	    	while(que.count != 0){
		    	que.remove(que.count - 1);
		    }
	    	while(sque.count != 0){
		    	sque.remove(sque.count - 1);
		    }
			for (int i = 0 ; i < Count; i++){
				 switch(Member.rnd.nextInt(4)){
			    	case 0 : v = new Member();break;
			    	case 1 : v = new Student();break;
			    	case 2 : v = new Employee();break;
			    	case 3 : v = new Faculty();break;
			    	case 4 : v = new Staff();break;
				}	 
	        	stk.push(v);
	        	que.enque(v);
	        	sque.enque(v);
	        } 		
	    }
	    catch(Exception e){
	    	fillList();
	    }  
	}
	
	Scanner in = new Scanner(System.in);
	void remove(){
	    Member vs = null,vq = null,vsq2 = null;
		if(!stk.empty())
			vs = stk.pop();
		if(!que.empty())
			vq = que.deque();
		if(!sque.empty())	
			vsq2 = sque.deque(vs);
		System.out.printf("\n############# Test of remove() #############\n");
		System.out.printf("\n\t\t%s is popped out from stack.\n",vs);
		System.out.printf("\n\t\t%s is deque from queue.\n",vq);
		System.out.printf("\n\t\t%s is removed from sorted queue ,euqal to top of stack.\n",vsq2);
		System.out.printf("\n############# End of Remove #############\n");			
	}
	
	
	void display(String name){
		if(name == "Stack")
			display(stk,"Stack");
		if(name == "Queue")
			display(que,"Queue");
		if(name == "Sorted Queue")
			display(sque,"Sorted Queue");
	}
	
	void display(list<Member> l, String name){
		int i = 0;
		Iterator<Member> itr = l.iterator(true);
		System.out.printf("%40s\n",name);
		while(itr.hasNext() ){
			System.out.printf("%s\n",itr.next().toString());
			i++;
			if(i % 20 == 0) {
				String flag = "";
				System.out.printf("continue printf?(Y is continue,other is break):");
				flag = scn.nextLine();
				if(!flag.trim().toUpperCase().equals("Y"))
					break;
			}
		}
		System.out.printf("%30s end of %s listing\n"," ",name);
	}
}
