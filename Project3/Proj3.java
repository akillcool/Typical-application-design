import java.util.Scanner;

public class Proj3 {

  static Scanner scan = new Scanner(System.in);
  static ShowClass sc = new ShowClass();
  
  public static void main ( String args[] ) {
    while(true)
    {
	  System.out.printf("Enter H/h/? for help, or command : ");
	  String t = scan.next();
	  scan.nextLine();
	  if(t.length()>1||t.length()==0) 
	  {
		  System.out.printf("Input the right command!\n");
 		  continue;
	  }
		  
	  char a = t.charAt(0); 
	  switch(a)
	  {
	   case 'g': case 'G': sc.Show_G_Info(); break;
	   case 'a': case 'A': sc.Show_A_Info(); break;
	   case 'r': case 'R': sc.Show_R_Info(); break;
	   case 'f': case 'F': sc.Show_F_Info(); break;
	   case 'c': case 'C': sc.Show_C_Info(); break;
	   case 'i': case 'I': case 'x': case 'X': sc.Show_I_Info(); break;
	   case 'h': case 'H': case '?': sc.Show_H_Info(); break;
	   case 't': case 'T': sc.Show_T_Info(); break;
	   case 'b': case 'B': sc.Show_B_Info(); break;
	   case 'p': case 'P': sc.Show_P_Info(); break;
	   case 'v': case 'V': sc.Show_V_Info(); break;
	   case 'q': case 'Q': sc.Show_Q_Info(); break;
	   default:
		   System.out.printf("Input the right command!\n");
	  }	  
    }
  }
} 

