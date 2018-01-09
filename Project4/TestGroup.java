import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;


public class TestGroup {

    VGroup  vgroup 	= new VGroup();
    Random   rnd	= new Random();
    Scanner scan	= new Scanner(System.in);

    public static void main( String arg[] ) {
       TestGroup app = new TestGroup ();

       String str;

       do {
	   System.out.printf("\n\n\t\tEnter command or H/h/? for help, Q/E to Exit: " ) ;
	   str = app.scan.next();

	   switch ( Character.toUpperCase(str.charAt(0)) ) {
	       case 'G':
		   app.generateGroups ( );	break;
	       case 'C':
		   app.vgroup.clear();	break; 
	       case 'D':
		   app.display(app.vgroup, "VGROUP_main");	break; 
	       case 'A':
		   app.addVisitor();	break; 
	       case '1':
		   app.removeVisitor();	break; 
	       case '2':
		   app.removeGroup();	break; 
	       case '3':
		   app.testAddGroupList();break; 
	       case '4':
		   app.testBroadcast (); break; 
	       case '5':
		   app.testGroups();	break; 
	       case '6':
		   app.testChangeGroup();	break; 
	       case 'M': case 'H': case '?':
		   app.showMenu();	break;
	       case 'E': case 'Q':
		   System.exit(0);
		default:
		   System.out.printf("\t\tIncorrect choice\n");
	   }
	 } while ( true );
    } // end of main() 
   
    /* Test VGroup's functions: (have those two function in v_group class
       	toString(): A list of group information : name_ID #; name_ID #; ....
	toVector(str): Convert above string into a vector of visitors.
     */
    void testAddGroupList() {	

	// Test fucntion to convert a list of group ID and Names into a VGroup object.
	String str = "CMPS221_3 0; CMPS394_6 0 ; MATH221_8 0";
	VGroup vgroup2 = new VGroup(); 
	vgroup2.add( str );
	display( vgroup2, "VGROUP_2" );
    }

    /* Ask for group numer and visitor'sname add the the visitor
       to the group. The other fields of visitor will be default or
       come from other sources.
       */
    void addVisitor () {
       System.out.printf("\t\tEnter to Group ID and Visitor name: ");
       int gID;
       String name;
       gID = scan.nextInt(); name = scan.next();
	Visitor v = new Visitor( rnd.nextInt(100), name, false, null);
       vgroup.find(gID).add(v);
    }

    /* Ask for group numer and visitor number remove the visitor
       from the group. Remove the group when last visitor is removed.
       */
    void removeVisitor () {
       System.out.printf("\t\tEnter to Group ID and Visitor ID: ");
       int gID, vID ;
       gID = scan.nextInt(); vID = scan.nextInt();
       vgroup.remove( gID, vID );
    }

    /* Ask for group numer, clear visitors of the group and remove the group.  */
    void removeGroup() {
       System.out.printf("\t\tEnter to Group ID: ");
       int gID;
       gID = scan.nextInt();
       vgroup.remove( new Group(gID) );
    }

    /* Show a menu of options for the program.  */
    void showMenu() {
       String menu = 
	"\t\t==================== Groups and Visitors Testing Program ==================\n\n" +
	"\t\tG: Generate 2 to 4 vgroup and add them to v_group, and  generate3 to 5\n" +
	"\t\t   visitors and add them to a group.\n" + 
	"\t\tC: Clear all visitors and all vgroup from the VGroup object. Notice that. \n" +
	"\t\t   close ObjectOutputStream before a visitor is remove from vactor.\n" +
	"\t\tD: Display the v_group in a well designed form. It also tests\n" +
	"\t\t    VGroup's functions: \n" +
	"\t\t      toString(): A list of group information : name_ID #; name_ID #; ....\n" +
	"\t\t      toVector(str): Convert above string into a vector of visitors.\n" +
	"\t\t   Group's functions: (have these to functions in group class\n" +
       	"\t\t      toString(): gName_gID number of visitors in group.\n" + 
       	"\t\t      visitorList(): A list of vName_IDs.\n" + 
       	"\t\t      visitorIDList(): A list of visitors' IDs.\n" + 
       	"\t\t      visitorIDList(bool): A list vIDs of selected or unselected visitors.\n" + 
	"\t\tA: Ask for group numer and visitor name add the visitor to the group,\n" +
	"\t\t   Visitor's other data comes from other sources..\n" +
	"\t\t1: Ask for group numer and visitor number remove the visitor from the group,\n" +
	"\t\t   Remove the group when last visitor is removed.\n" +
	"\t\t2: Ask for group numer, clear the group's visitors and remove the group\n" +
       	"\t\t3: Test converting a string of lists into vgroup and addig them into \n" + 
	"\t\t   into VGROUP object..\n" +
	"\t\t4: Test broadcast( private string) and broadcast( private message)  \n" +
	"\t\t5: Test add and remove group and broadcaste to all visitors.\n" +
	"\t\t6: Test change a visitor from one group to another group..\n" +
	"\t\tM/H/?:  Show this menu.\n" +
	 "\t\tE/Q: Exit this application.\n" +
	"\n\t\t======================================================================\n\n\n" ;
       System.out.printf("\n\n%s", menu );
    }
  
    /* Test add and remove group, and VGroup.broadcast( all-visitor) method. */
    void testGroups( ) {
	System.out.printf("\n\n\t\tOrginal Groups: [%s] = %d", vgroup.toString(), vgroup.size());
	Group grp = vgroup.get( rnd.nextInt( vgroup.size() ));
	String strGrp = grp.toString();
	vgroup.remove(grp);
	System.out.printf("\n\t\tAfter removing [%s], vgroup contain [%s] = %d\n", strGrp, vgroup.toString(), vgroup.size()) ;
	vgroup.broadcast( new Message( Message.REMOVEGROUP, grp.toString(), null) );

	String str = new String( Names.group[ rnd.nextInt( Names.group.length )]) ; // get group name
	grp =  new Group ( rnd.nextInt(100), str ); // create or instanciate new group.
	strGrp = grp.toString(); 
	vgroup.add( grp );		// add new group to v_group instance.
	System.out.printf("\n\t\tAfter adding [%s], vgroup contain [%s] = %d\n", strGrp, vgroup.toString(), vgroup.size()) ;
	vgroup.broadcast( new Message( Message.ADDGROUP, grp.toString(), null) );
    }
    /* Test broadcast ( public message  ), and broadcast( priviate_message, receive_list  */
    void testBroadcast () {
	Message [] ma = {  new Message( Message.PUBLIC, "A public message.", "HWANG"),
	   		   new Message( Message.PRIVATE, "A private message.", "1, 2")};
	Group grp;

	System.out.printf("\n\n\t\tEneter 0 for public message and 1 or private message: ");
	int choice = scan.nextInt();
	for ( int j = 0; j <  vgroup.size(); j ++ ) {
	    grp = vgroup.get( j ); 
	    System.out.printf("\n\n\t\t____ %s message to Group :%s _______________________\n\n",
		     choice == 0 ? "PUBLIC": "PRIVATE", grp);
	    if ( choice == 0 ) { grp.broadcast( ma[choice] ) ; }
	    else { grp.broadcast( ma[choice],  grp.visitorIDList( true ) );
	   }
	}
    }
 
    // Test of chanaging a visitor from the current group to anther group.
    void testChangeGroup ( ) {
	int vID, fromGroupID, toGroupID ;
	System.out.printf("\t\tEnter a Visitor's ID, his/her current group ID and new Group ID: ");
	vID = scan.nextInt(); fromGroupID = scan.nextInt(); toGroupID = scan.nextInt();
	Group fromGroup = vgroup.find( fromGroupID );
	System.out.printf("Find fromGroup? [%s].\n", fromGroup == null ? "No" : "Yes" );
	Group toGroup   = vgroup.find( toGroupID );
	System.out.printf("Find toGroup? [%s].\n", toGroup == null ? "No" : "Yes" );
	Visitor v       = fromGroup.find( vID );
	System.out.printf("Find visitor in fromGroup? [%s].\n", v == null ? "No" : "Yes" );
	boolean suc = vgroup.changeGroup(v, fromGroup, toGroup );
	System.out.printf("Change %s from %s, %s %s.\n", v, fromGroup, toGroup, suc ? "succeded" : "failed");
    }

    /* Create 3 to 5 visitors and add them to a group. */
    /* Create a 2 to 4 vgroup and add then to v_group. */
    void generateGroups() {
	Group newGroup ;
	for ( int i = 0; i < rnd.nextInt( 3 ) + 2 ; i++ ) {
	    String str = new String( Names.group[ rnd.nextInt( Names.group.length )]) ; // get group name
	    newGroup =  new Group ( i+1, str ); // create or instanciate new group.
	    vgroup.add( newGroup );		// add new group to v_group instance.
	    generateVisitors( newGroup );
	}
    }
  
    /* Create 3 to 5 visitors and add them to a group. */
    void generateVisitors( Group group ) {
       Visitor v;
       for ( int i = 0; i < rnd.nextInt(3) + 3 ;  i ++ ) {
	  // Create or instanciate a visitor
	  v =  new Visitor ( rnd.nextInt(1000),
		 	     Names.visitors[rnd.nextInt(Names.visitors.length)],
			     (rnd.nextInt(2) == 0? true : false), null
			  );
	  group.add(v); // add new visitor to the group.
       } 
    }

    /* Display the v_group in a well designed form. */
    void display ( VGroup groups, String vgName) {
	System.out.printf("\n\nThe of groups to display: [%d]\n", groups.size());;
	System.out.printf("\n%s : ", vgName); System.out.printf("%s", groups );
	Group grp;
	for ( int i = 0; i < groups.size(); i++ ) {
	    grp = groups.get(i);
	    System.out.printf("\n\n\t_______________________ Group [%s] : Information Start  _________________________", grp) ;
	    System.out.printf("\n\n\tg.toString()            : %s",  grp.toString() );
	    System.out.printf("\n\tg.visitorList()         : %s",  grp.visitorList() );
	    System.out.printf("\n\tg.visitorIDList()       : %30s -- all visitors",  grp.visitorIDList() );
	    System.out.printf("\n\tg.visitorIDList(true)   : %30s -- selected visitors",  grp.visitorIDList(true) );
	    // System.out.printf("\n\tg.visitorIDList(false)  : %30s -- unslected visitors",  grp.visitorIDList(false) );
	    int[] arr =grp.visitorIDListToArray( groups.get(i).visitorIDList(true));
            System.out.printf("\n\tg.visitorIDListToArray(): %30s  -- selected visitors", arr != null ? Arrays.toString(arr): "null");	

	    System.out.printf("\n\n\t\t\t__________ Visitor List of %s _____________", grp);
	    for ( int j = 0; j < grp.size(); j++ )
		System.out.printf("%s %30s", j % 2 == 0 ? "\n\t\t\t" : "", grp.vecVisitor.get(j).longString() );

	    // System.out.printf("\n\t\t\t__ End of Visitor List of %s ____", grp);
	    // System.out.printf("\n\n\t_____________________ Group [%s] : Information  End ____________________________", grp );
	}
    }  // end of display()
}    
