# Assignment 1: Class, Inheritance and Polymorphism
Huaqing Wang

## I.	Purpose: Object-Oriented Prgraming in Java 

## II.	Description:

   The exercise is aimed at practicing the object-oriented
programming methodology n Java. The OOP programming languages three
features including , data encapsulation, inheritance and polymorphism.

   The project  use OOP feature to 
	- implement a a group of related Member class and its classes.
    - Randomly generate a group of objects of the related classes.
	- Save the class objects in HTML Table format into a HTML file,
	- Display the html file by launching the default internet browser
	  to display the html file containing the objects you will define.


## III.	Specification:

   Define the following class and minimum members:


    Class/	Super Class	Members (minimum request)
   Interface     & interface
=============   ==============  =======================================
   Names        Object		Interface containing constant names such
				first names, last names, department names
				and etc.

   Member 	Object		public default constructor
		Comparable	public void generate();
				public String toString();
				public String toString( boolean );
				public int    compareTo(Member);
				public String htmlRow();
				public String htmlColumns();
				other necessary methods....
				protected String firstName, lastName; long ID;

   Student	Member		public default constructor
				public void generate();
				public String toString();
				public String toString( boolean);
				public String htmlRow();
				public String htmlColumns();
				other necessary methods....
				protected String major; float GPA;

   Employee	Member		public default constructor
				public void generate();
				public String toString();
				public String toString( boolean);
				public String htmlRow();
				public String htmlColumns();
				other necessary methods....
				protected String department; int yearHired;

   Faculty	Employee        public default  constructor
				public void generate();
				public String toString();
				public String toString(boolean);
				public String htmlRow();
				public String htmlColumns();
				other necessary methods....
				protected String degreeHeld;

   Staff        Employee        public default constructor
				public void generate();
				public String toString();
				public String toString(boolean);
				public String htmlRow();
				public String htmlColumns();
				other necessary methods....
				protected String jobTitle;

---------------------------------------------------------------------------

	Write a program which displays the following line:


	Enter H/h/? for help, or command : 


   When H/h/? is entered, the following menu is printed:

		================ Project 1 Java OOP ================
		G/g:   Ask for a N, and generate N members of mixed
                       Member class's objects, and store in a Vector 
		       and a array Objects.

		S/s/ : Sort the members in the vector and array in
                       ascending order.

		V/v/ : Show the members in the vector and array .

		O/o/ : Save objects inside vector into a HTML file
		       with objects saved in the format of HTML
		       Table.
		F/f  : Show HTML file contents on screen.

		L/l  : Launch the default internet browser to
		       display the generated HTML file.
		--------------------------------------------------
		H/h/?: Display this menu.
		E/e  : Exit
		=================================================

## IV.	Notices

  1. Install latest Java JDK, and VIM on your PC. For Java assignments.

     You have to add the java commands' path into the PATH environment variable, and 
     and create CLASSPATH environment variables on PC so that you can use java
     commmands.

      Use internet search to find how to set those two environment variable if your 
     "javac Hello.java" and "java Hello" commands cause "Cannot find javac/java command".
  
 2. Randomly generate mixed N members (including its subclass objects). When an object is generated, add the N members into vector and array.
  3. An class can 'extends' one class and can 'implements' many interfaces.
  4. An interface can 'extends' many interfaces.
  5. Methods in interfaces are always abstract and public even if you don't
     specify them that way.
  6. Data members in interface are always static and final even if you don't
     specify that way.
  7. A abstract function is function begun with 'abstract' or any function defined
     in an interface.
  8. When a class implements an interface and the class doesent implement all
     function in the interface, the class is an abstract.
  9. Abstract class can be used to define a variable but cannot be used to
     instantciate a variable. That is, you cannot use interface or abstract as
     operand of the 'new' operator.
  10. Always use a search engine (such as google) on any question you may have.
     For example, type "how to launch browser in Java" to search for what you
     want.

## V.	Files Available

	Names.java : interface contain names of members, department, sports.
	ScreenIO.java:
		Format int, long into string or phone, SSN.
		Add additional spaces after or before string.
		Read number from standard input.
		Print Strings/Menu on screen.
		Prompt for char/string from user.

## VI.	CLASSPATH Environment Variable

    To use those classes anywhere, add the path of those classes in CLASSPATH environment
    variable:

	On unix system, add a line
		CLASSPATH=.:my_java_classes:
    in .bash_profile.

    In Windows, go to Settings->Control Pannel->System->Environment

    Add a CLASSPATH variable if it does not exists. Add .;my_java_classes; as
    value of that variable.

    Other subdirectories may be added into CLASSPATH,

## VII.	How to turn in your java programs

    	1. Form a team of 4 members and assign a unique number between 01 to 20 to the team.
	   Work individually, discuss within group, combine the individual's best programs
	   into one set of programs.
	   Put each team members' school IDs and name at the beginning of each file.

	2. Always create a sub-dirctory for each homework in that sub-directory. 
	   Store all source code file, and byte code (compiled class ) file in that
	   subdirectory. You can name the subdirectory as Project1, Project2, and etc.

	3. Inside project subdirectory, use the zip command to store all java files
	   into one zip file in by the following command: 

		$ zip LLLLDD_d.zip *.java

	   where 'LLLL' are the first 4 letter(pin ying) of team members' last names,
	  the 'DD' are the team number, and 'd' is the project number. 

           Make sure you have all the java files in the same subdirectory and issue
	   the zip commend in that subdirectory.

        4. Verify the all files are included by
		- Create a sub-directoy,
		- move the zip file the subdirectory,
		- unzip it (unzip FLxxx01.zip)
		- compile your java file containing the main function
		- run it.

	5. Email the zip file LLLLdd_D.zip file  to hwang_csub@163,com, and add the following
	   information as email subject:
		- Team number such as "Team 03"
		- Project number such as "Project 1", and
        - Each number's names 
