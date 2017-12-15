package hust.koi.Proj2;

import java.util.Random;
import java.util.Scanner;

public class Proj2 {

	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();
	static final String[] menu = { "+===================================================+",
			"|    Assignment 2:        Linked List Structures    |",
			"+===================================================+",
			"| G: Ask for a N, and generate N members of mixed   |",
			"|     kinds and put them into three list structures.|",
			"|     Make sure you destroy the lists before create |",
			"|     new ones if the lists are not empty.          |",
			"+---------------------------------------------------+",
			"| S: List members in stack, 1 memebr per line,      |",
			"|     20 members per screen with header line, allow |",
			"|     Q/q to quit listing after each 20 members.    |",
			"+---------------------------------------------------+",
			"| Q: List members in queue (same requirements).     |",
			"+---------------------------------------------------+",
			"| O: List members in ordered queue sorted by SSN    |",
			"|    (same requirements).                           |",
			"+---------------------------------------------------+",
			"| D: Remove first element from queue, pop member    |",
			"|    from stack, and delete the same member from    |",
			"|    sorted queue as the one removed from stack.    |",
			"+---------------------------------------------------+",
			"| I: Randomly generate new member, and put          |",
			"|    the object into the three structures. Print    |",
			"|    out the new member added in.                   |",
			"+---------------------------------------------------+",
			"| H/?: Display this menu.                           |",
			"| E  : Exit                                         |",
			"+===================================================+" };

	public static void main(String[] args) {
		showMenu();
		
		while (true) {
			System.out.print("Please input a command: ");
			String argString = scanner.nextLine();

			// confirm that argString is not empty, or that charAt(0) will be overflow
			if (argString == null || argString.isEmpty()) {
				System.out.println("Please input a valid argument.");
				continue;
			}
			char firstChar = argString.toUpperCase().charAt(0);

			// only use first char in the switch statement to be faster.
			switch (firstChar) {
			case 'G':
				generate();
				break;
			case 'S':
				listStack();
				break;
			case 'Q':
				listQueue();
				break;
			case 'O':
				listSortedQueue();
				break;
			case 'D':
				delete();
				break;
			case 'I':
				randomInsert();
				break;
			case 'H':
				showMenu();
				break;
			case 'E':
				close();
			default:
				System.out.println("Please input a valid argument.");
				break;
			}
		}
	}

	private static void close() {
		// TODO Auto-generated method stub
		
	}

	private static void randomInsert() {
		// TODO Auto-generated method stub
		
	}

	private static void delete() {
		// TODO Auto-generated method stub
		
	}

	private static void listSortedQueue() {
		// TODO Auto-generated method stub
		
	}

	private static void listQueue() {
		// TODO Auto-generated method stub
		
	}

	private static void listStack() {
		// TODO Auto-generated method stub
		
	}

	private static void generate() {
		// TODO Auto-generated method stub
		
	}

	private static void showMenu() {
		for (String string : menu) {
			System.out.print(string+"\n");
		}
	}

}
