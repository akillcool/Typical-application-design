package hust.koi.Proj1;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Proj1 {

	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();
	static Member[] memberArray = null;
	static Vector<Member> memberVector = null;

	static final String htmlHeader ="<caption>Project 1 Java OOP</caption>"+ "<TR>" + "<TH>Type</TH>" + "<TH>Last Name</TH>" + "<TH>First Name</TH>"
			+ "<TH>ID</TH>" + "<TH>Major</TH>" + "<TH>GPA</TH>" + "<TH>Sport</TH>" + "<TH>Department</TH>"
			+ "<TH>Year hired</TH>" + "<TH>Degree</TH>" + "<TH>Position</TH>" + "<TH>Job Title</TH>" + "<TH>Skill</TH>"
			+ "</TR>" + "\n";
	static final String[] menu = { "================ Project 1 Java OOP ================",
			"G/g:   Ask for a N, and generate N members of mixed", "Member class's objects, and store in a Vector",
			"and a array Objects.", "S/s : Sort the members in the vector and array in", "ascending order.",
			"V/v : Show the members in the vector and array .", "O/o : Save objects inside vector into a HTML file",
			"with objects saved in the format of HTML Table.", "F/f  : Show HTML file contents on screen.",
			"L/l  : Launch the default internet browser to", "display the generated HTML file.",
			"--------------------------------------------------", "H/h/?: Display this menu.", "E/e  : Exit",
			"=================================================" };

	public static void main(String[] args) {

		// Show the help menu in the entry.
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
				sort();
				break;
			case 'V':
				showMembers();
				break;
			case 'O':
				saveAsHTML();
				break;
			case 'F':
				showRawHTML();
				break;
			case 'L':
				openHTML();
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
		System.out.println("Program exited.");
		System.exit(0);
	}

	private static void openHTML() {
		File htmlFile = new File("table.html");
		try {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				desktop.browse(htmlFile.toURI());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void showRawHTML() {
		File file = new File("table.html");
		try {
			if (!file.exists()) {
				System.out.println("Please use 'O/o' command to save the html file first.");
				return;
			} else {
				FileInputStream fileInputStream = new FileInputStream(file);
				byte[] fileByte = new byte[fileInputStream.available()];
				fileInputStream.read(fileByte);
				fileInputStream.close();
				System.out.println("table.html\n\n" + new String(fileByte));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void saveAsHTML() {
		String htmlString = "<html><body>\n\t<table border=1>\n" + htmlHeader;
		for (Member member : memberArray) {
			htmlString += member.htmlRow();
		}
		htmlString += "\n\t</table>\n</body></html>";

		File file = new File("table.html");
		try {
			file.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(htmlString.getBytes());
			fileOutputStream.close();
			System.out.println("Save success!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void showMembers() {
		System.out.println("Here are the Array and Vector:\nArray:");
		for (Member member : memberArray) {
			System.out.println(member.toString());
		}
		System.out.println("\nVector:");
		for (Member member : memberVector) {
			System.out.println(member.toString());
		}
	}

	private static void sort() {
		Arrays.sort(memberArray);
		Collections.sort(memberVector);
		System.out.println("Sort success!");
	}

	private static void generate() {

		int n;
		System.out.print("PLease input N(N>0): ");
		n = scanner.nextInt();
		// skip a line
		scanner.nextLine();

		memberArray = new Member[n];
		memberVector = new Vector<Member>();

		for (int i = 0; i < n; i++) {
			switch (random.nextInt(5)) {
			case 0:
				memberArray[i] = new Member();
				memberVector.addElement(memberArray[i]);
				break;
			case 1:
				memberArray[i] = new Student();
				memberVector.addElement(memberArray[i]);
				break;
			case 2:
				memberArray[i] = new Employee();
				memberVector.addElement(memberArray[i]);
				break;
			case 3:
				memberArray[i] = new Faculty();
				memberVector.addElement(memberArray[i]);
				break;
			case 4:
				memberArray[i] = new Staff();
				memberVector.addElement(memberArray[i]);
				break;
			}
		}
		System.out.println("Generation succeed!");
	}

	private static void showMenu() {

		System.out.println("\n\n");
		for (int i = 0; i < menu.length; i++) {
			System.out.println(menu[i]);
		}
	}

}
