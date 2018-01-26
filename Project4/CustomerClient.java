/**  Group Study:
  Things to do:
1: Keep the checked/uncked status after a new visitor is login and a visitir is logged
out.
2: Allow a visitor or client to swithch from current group io another group.
3. Allow visitor to create new group.
5. Convert it to a applet program.
 */

import java.awt.* ;
import java.awt.event.* ;
import javax.swing.* ;
import javax.swing.border.TitledBorder ;
import javax.swing.border.* ;
import java.io.* ;
import java.util.Random ;
import java.net.* ;
import java.util.* ;

public class CustomerClient extends JApplet {

    static Random	rnd	= new Random();
    static JFrame clientWindow ;
    static String titleStr = "Online Customer Service";
    String	hostAddress = "localhost";
    // String	hostAddress = "sleipnir.cs.csub.edu";
    // String	hostAddress = "delphi.cs.csubak.edu";
    static int	port 		= 8987;
    static int	frame_height	= 450;
    static int  frame_width 	= 900;
    static int	small_width 	= 280;
    int		font_size 	= 12 ;
    int		small_font_size = 10;
    JLabel	lbNameMsg[] 	= { new JLabel("Name"), new JLabel("Messsage") };
    JTextField	tfNameMsg[] 	= { new JTextField("Anthony"), new JTextField(20) };
    public JTextArea	msgBoard	= new JTextArea() ;
    JButton     btnChangeGroup  = new JButton("Change Agent");
    JButton     btnAddGroup     = new JButton("Add");
    // JButton     btnRemoveGroup     = new JButton("Delete");
    // JButton     btnSynchronize    = new JButton("Sync");
    JPanel      plBtnNorth      = new JPanel( new BorderLayout() );
    // JPanel      plBtnSouth      = new JPanel( new BorderLayout() );
    JPanel	plNameMsg[] = { new JPanel(), new JPanel() };
    JPanel	plSouth = new JPanel();
    JPanel	plVisitors = new JPanel( new FlowLayout( FlowLayout.LEFT ));
    JPanel	plGroups   = new JPanel( new FlowLayout( FlowLayout.LEFT ));
    JScrollPane	sclEast = new JScrollPane ();
    JScrollPane	sclWest = new JScrollPane( );
    JScrollPane	sclGroups = new JScrollPane( );
    TitledBorder  plVisitorsTitledBorder = new TitledBorder("Members");
    JPanel	plWest = new JPanel(new BorderLayout());
    TitledBorder  plGroupsTitledBorder = new TitledBorder("Agents");

    Socket	chatSocket = null;

    boolean	privateMessage = false;
    String	privateReceiverIDs;
    String	privateReceiverNames;
    boolean      justChecked    = false;
    Message 	message = new Message();

    ObjectOutputStream	out = null;  
    ObjectInputStream	in  = null; 
    VGroup       vgroup = new VGroup();
    Group		group; // shared by all clients in the same group.
    Visitor		visitor = null;
    int			groupID = 1; 
    String		groupName ; 
    String	privateList = "";
    String  	visitorName; 

    Container		c =  null;
    static JApplet	applet;
    CAgent	cAgent	= null;

    public CustomerClient() { }

    public void init() {
	sclEast.getViewport().add( plVisitors );
	sclWest.getViewport().add( plWest);
	sclGroups.getViewport().add( plGroups );
	plWest.add( sclGroups, BorderLayout.CENTER );
	c = getContentPane();
	plVisitors.setBorder( plVisitorsTitledBorder );
	plVisitors.setToolTipText("Selct Member(s) to send private Message"); 
	plVisitors.setPreferredSize(new Dimension(130, frame_height));
	plVisitors.setFont(new Font("verdana", Font.PLAIN, small_font_size));	
	//c.add(sclEast, BorderLayout.EAST);
	c.add(sclWest, BorderLayout.WEST);

	plGroups.setBorder( plGroupsTitledBorder );
	plWest.setToolTipText("Selct a group to continue"); 
	plWest.setPreferredSize(new Dimension(150, frame_height - 100));
	plWest.setFont(new Font("verdana", Font.PLAIN, small_font_size - 70));	
	plGroups.setPreferredSize(new Dimension(140, frame_height-100));
	plGroups.setFont(new Font("verdana", Font.PLAIN, small_font_size));	
	// plGroups.setLayout( new BorderLayout() );
	btnAddGroup.setEnabled( false ); btnChangeGroup.setEnabled( false );
	// btnRemoveGroup.setEnabled( false ); btnSynchronize.setEnabled( false );
	btnAddGroup.setToolTipText ("Add a new group");
	// btnRemoveGroup.setToolTipText ("Remove a group");
	btnChangeGroup.setToolTipText ("Change agent for current client");
	// btnSynchronize.setToolTipText ("Synchronize groups' sizes");

    //plBtnNorth.add( btnAddGroup,  BorderLayout.WEST );
	plBtnNorth.add( btnChangeGroup, BorderLayout.CENTER);
	// plBtnSouth.add( btnAddGroup, BorderLayout.EAST );
	// plBtnSouth.add( btnRemoveGroup,  BorderLayout.WEST );
	plWest.add( plBtnNorth, BorderLayout.NORTH );
	// plWest.add( plBtnSouth, BorderLayout.SOUTH );
								 
	plSouth.setLayout(new BorderLayout());

	c.setBackground(Color.black);
	msgBoard.setBackground(Color.black);
	msgBoard.setForeground(Color.yellow );
	for ( int i = 0; i < plNameMsg.length; i ++ ) {
	    plNameMsg[i].setLayout(new FlowLayout());
	    lbNameMsg[i].setFont(new Font("verdana", Font.PLAIN, font_size));	
	    tfNameMsg[i].setFont(new Font("verdana", Font.PLAIN, font_size));	
	    plNameMsg[i].add(lbNameMsg[i]);
	    plNameMsg[i].add(tfNameMsg[i]);
	    tfNameMsg[i].setEnabled( false );
	}
	tfNameMsg[0].setColumns(8); tfNameMsg[0].setForeground(Color.blue);
	tfNameMsg[1].setColumns(20);
	tfNameMsg[1].setToolTipText("Enter message to send and press ENTER");
	plSouth.add(plNameMsg[0], BorderLayout.WEST) ;
	plSouth.add(plNameMsg[1], BorderLayout.CENTER);

	msgBoard.setFont(new Font("verdana", Font.PLAIN, font_size + 2) );
	c.add(new JScrollPane( msgBoard ), BorderLayout.CENTER);
	c.add(plSouth, BorderLayout.SOUTH);

	addListeners();

	c.setSize( frame_width, frame_height);
	setVisible( true );
	makeConnection();
	tfNameMsg[0].setText( Names.visitors[rnd.nextInt(Names.visitors.length)]);
    }

    public void stop() {
	try {
	    // Make a LOGOUT message, and send out.
	    message.set(Message.LOGOUT, null, null); 
	    Thread thr = new ClientSender( out, new Message(message));
	    thr.start() ;
	    try { thr.join(); } catch ( InterruptedException e ) { e.printStackTrace(); }
	    if ( in != null ) in.close(); 
	    if ( out != null ) out.close();
	    if ( chatSocket != null ) chatSocket.close();
	    out = null; in = null; message = null;
	} catch (IOException e ) {e.printStackTrace();}
    }

    boolean isInt( String numStr ) {
	if ( numStr == null || numStr.trim().length() < 1 ) return false;
	for ( int i = 0; i < numStr.length(); i++ )
	    if ( numStr.charAt(i) < '0' || numStr.charAt(i) > '9' ) return false;
	return true;
    }

    void addListeners() {

	btnAddGroup.addActionListener( new ActionListener() {
		public void actionPerformed ( ActionEvent e ) {
		String gName = JOptionPane.showInputDialog(null, "Enter the  group name or cancel");
		gName = gName.replaceAll( ";", "").replaceAll("_", "").replaceAll(" ", "");
		sendMessage( new Message( Message.ADDGROUP, gName, null) ); 
		}});
        /*
	btnRemoveGroup.addActionListener( new ActionListener() {
		public void actionPerformed ( ActionEvent e ) {
		String gID = JOptionPane.showInputDialog(null, "Enter the  group number or cancel");
		if ( ! isInt( gID ) ) return;
		sendMessage( new Message( Message.REMOVEGROUP, gID, null) ); 
		}});

	btnSynchronize.addActionListener( new ActionListener() {
		public void actionPerformed ( ActionEvent e ) {
		sendMessage( new Message( Message.SYNCHRONIZE, null, null) ); 
		}});
	*/
	btnChangeGroup.addActionListener( new ActionListener() {
		public void actionPerformed ( ActionEvent e ) {
		String intStr = JOptionPane.showInputDialog(null, "Enter an int or cancel");
		if ( intStr == null )  return; 
		int toGroupID = Integer.parseInt( intStr );
		Group grp = vgroup.find( toGroupID );
		if ( grp == null ) { 
			JOptionPane.showMessageDialog( null, "The group doesn't exist.");
			return;
		}
		groupID = toGroupID;
		sendMessage ( new Message ( Message.CHANGEGROUP, toGroupID + "", null ) );
		}});

	tfNameMsg[0].addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent e ) {
		visitorName = new String(tfNameMsg[0].getText()).trim() ;
		if ( visitorName.equals("") ) {
		JOptionPane.showMessageDialog( null, "Enter yur name and press ENTER!");
		return;
		}
		tfNameMsg[0].setEnabled(false); tfNameMsg[1].setEnabled(true);
		clientWindow.setTitle(titleStr);
		clientWindow.setSize( frame_width, frame_height);
		sendMessage( new Message( Message.LOGIN, groupID+"", visitorName ));
		return; 
		}});

	tfNameMsg[1].addActionListener( new ActionListener () {
		public void actionPerformed(ActionEvent e ) {
		String msg = tfNameMsg[1].getText().trim();
		if ( msg.length() < 1 ) return;
		msg = visitorName + ": " + msg; 
		if ( privateMessage ) sendMessage ( new Message( Message.PRIVATE,
			msg + String.format( "  \t <private to %s>"  , privateReceiverIDs),
			privateReceiverIDs));
		else   sendMessage ( new Message( Message.PUBLIC, msg, null));

		tfNameMsg[1].setText("");
		}}); // end of actionPerformed(), anonymos innter class and addListener().

	clientWindow.addWindowListener( new WindowAdapter ( ) {
		public void 	windowClosed(WindowEvent e) { System.exit(0); }
		public void 	windowClosing(WindowEvent e) { applet.stop(); System.exit(0); }
		} );
    }
    void makeConnection() {

	try {
	    chatSocket = new Socket(hostAddress, port); 
	    out = new ObjectOutputStream ( chatSocket.getOutputStream());
	    in  = new ObjectInputStream( chatSocket.getInputStream());
	    (cAgent = new CAgent()).start();

	    // get exiting groups from server.
	    message.set( Message.GETGROUPS, null, null); 
	    sendMessage( message );
	} catch(IOException er) { 
	    msgBoard.append(er.toString());
	    System.exit(-1);
	}
    }

    void sendMessage( Message message ) {

	try { out.writeObject(message) ; } catch(IOException e ) { e.printStackTrace(); }
	/*
	   Thread thr = new ClientSender( out, message);
	   thr.start() ;
	   try { thr.join(); } catch (InterruptedException f )  { }
	 */
    }

    // Implementation of Action Listener.
    static int k = 1;
    static String str = null;



    public void paint (Graphics g) {
	super.paint(g);
    }

    public static void main( String arg[] ) {
	clientWindow =  new JFrame ( titleStr );
	// clientWindow.setBackground(Color.black);
	applet = new CustomerClient();
	applet.init();
	clientWindow.setTitle("Select a agent");
	//clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	clientWindow.getContentPane().add( applet );
	clientWindow.setSize( frame_width, frame_height);
	
	EventQueue.invokeLater ( new Runnable () {
	    public void run() {
		clientWindow.setVisible(true);
	    }
	} );
    }


    class ClientSender extends Thread {

	ObjectOutputStream	out;  // OutputStreamWrite derived from Socket.
	Message	message;  // Message from a chat room client to other clients

	public ClientSender ( ObjectOutputStream w, Message s)
	{ 
	    out = w; message = s ; 
	} 

	public void run() {
	    try {
		out.writeObject( message ) ;
	    } catch( IOException e )
	    {  e.printStackTrace() ; }
	}
    }


    GroupButtonMap		groupMap = new GroupButtonMap ();
    VisitorButtonMap		visitorMap = new VisitorButtonMap ();
    boolean			loggedIn = false;

    class CAgent extends Thread {

	public CAgent ( ) { }

	ActionListener listener = new ActionListener() {
	    public void actionPerformed(ActionEvent actionEvent) {
		AbstractButton aButton = (AbstractButton) actionEvent.getSource();
		switch(aButton.getClass().getName()) {
		    case "javax.swing.JRadioButton" :
			group = groupMap.get( (JRadioButton) aButton);
			groupID = group.ID;
			tfNameMsg[0].setEnabled( true); 
			clientWindow.setTitle("Enter your Name"); 
			groupName = aButton.getText();
			break;
		    case "javax.swing.JCheckBox" :
			visitorMap.setStatus( (JCheckBox) aButton );
			plVisitorsTitledBorder.setTitle( String.format("Members ( %d )", visitorMap.selected));
			privateMessage = (visitorMap.selected > 0);
			plVisitors.revalidate(); plVisitors.repaint();
			if ( privateMessage ) {
			    privateReceiverIDs = group.visitorIDList( true ); 
			    privateReceiverNames = group.visitorList( true ); 
			}
			break;
		    default : break;
		}

	    }
	};

	void addOneGroup( String newGroup_info ) { 
	    Group grp = Group.toGroup(newGroup_info) ;
	    vgroup.add( grp );  // add to group vecor.
	    JRadioButton button = new JRadioButton( newGroup_info, false ); // read a radio button for the group
	    button.setEnabled( ! loggedIn );
	    button.addActionListener( listener ) ;  // ad listener for the button.
	    groupMap.add( button, grp );	// add button-group pair to map. 
	    plGroups.add( button );		// add button a button panel.
	    plGroups.revalidate(); plGroups.repaint();   // repaint button panel.
	}
	
	void removeOneGroup( String gID ) { 
	    Group grp = vgroup.find( Integer.parseInt( gID )) ;
	    JRadioButton button = groupMap.get( grp );
	    vgroup.remove( grp );  // add to group vecor.
	    groupMap.remove( grp );
	    plGroups.remove( button );		// add button a button panel.
	    plGroups.revalidate(); plGroups.repaint();   // repaint button panel.
	}
	
	void synchronizeGroups ( String group_list ) { // update groups, sizes with new counts.
	
	    VGroup vgrp = VGroup.toVGroup( group_list );
	    Set<Group> set = groupMap.groupMap.keySet();
	    Group  grpMap[]    = set.toArray(new Group[0]); // .getGroupArray();
	    Group  grpSvr[]    = vgrp.vgroup.toArray( new Group[0] );
	    // System.out.printf("\nArray sizes Synchronize(): %d, %d\n", grpMap.length, grpSvr.length);
	    Arrays.sort( grpMap); Arrays.sort( grpSvr );
	    int i = 0, j = 0;
	    while ( i < grpSvr.length && j < grpMap.length )  {
	       
	       if ( grpSvr[i].ID == grpMap[j].ID ) {
		   groupMap.get( grpMap[j] ).setText( grpSvr[i].toString() );
		   i++; j++; 
		} else if ( grpSvr[i].ID > grpMap[j].ID ) {
		   		removeOneGroup ( grpMap[j].ID + "" );   j++;
		} else { addOneGroup( grpSvr[i].toString() ); i++; }
	    }
	    
	    while ( i < grpSvr.length ) {
	       addOneGroup ( grpSvr[i].toString() ); i ++;
	    }
	    
	    while ( j < grpMap.length ) {
		   removeOneGroup ( grpMap[j].ID + "" );   j++;
	    }
	    repaintGroupPanel();
	}


	void addAllGroups( String groupList ) {
	    vgroup.add( groupList );
	    JRadioButton button; Group group;
	    for ( int i = 0; i < vgroup.size(); i ++ ) {
		group = vgroup.get(i);
		button = new JRadioButton( group.toString(), false  );
		button.addActionListener( listener ) ;
		groupMap.add( button, group );
		plGroups.add( button );
	    }
	    plGroups.revalidate(); plGroups.repaint();
	}

	void loginSetup( Visitor v ) { // setup visitor name, group variables and window title.
	    visitorName = v.toString() ;
	    tfNameMsg[0].setText( visitorName );
	    visitor = v;
	    clientWindow.setTitle( String.format("%s - %s (%s_%d)", titleStr, v, group.name, group.ID ) ); 
	    for (Component c: plGroups.getComponents() ) c.setEnabled(false);
	    btnChangeGroup.setEnabled( true );
	    btnAddGroup.setEnabled( true );
	    // btnRemoveGroup.setEnabled( true );
	    // btnSynchronize.setEnabled( true );
	    loggedIn = true;
	}

	void addAllVisitors ( String allVisitors ) {
	    Group grp = new Group();
	    grp.add( allVisitors );
	    for(int i = 0; i <  grp.size(); i++) 
		addOneVisitor( grp.get(i) ) ;
	}

	void addOneVisitor( Visitor v ) {
	    group.add( v );
	    JCheckBox box = new JCheckBox(v.toString());
	    visitorMap.add(box, v);
	    box.addActionListener(listener);
	    plVisitors.add(box);
	}

	void addOneVisitor( String vName_vID ) {
	    Visitor v = Visitor.toVisitor( vName_vID );
	    addOneVisitor ( v );
	}

	void removeAllVisitors ( ) {
	   group.clear();
	   visitorMap.clear();
	   plVisitors.removeAll();
	   repaintVisitorPanel();
	}
	// Remove the visitor from group, visitor-buttom mal, and
	// remove the button from button map and panel.
	void removeOneVisitor ( String vId_vName ) {
	    Visitor v = Visitor.toVisitor( vId_vName );
	    v = group.find(v.ID);
	    plVisitors.remove ( visitorMap.get( v ) );
	    group.remove( v );
	    visitorMap.remove( v );
	    plVisitorsTitledBorder.setTitle( String.format("Members ( %d )", visitorMap.selected));
	}

	void processGroupChanged( Message changeGroupMsg ) {  // group list is rececived.
	    Visitor v = Visitor.toVisitor( changeGroupMsg.body);
	    if ( v.ID == visitor.ID ) {
	        privateMessage = false;
	        removeOneVisitor( changeGroupMsg.body); // it is necessary to descease the size of group.
							   
	    	// groupMap.get(group).setText( String.format("%s_%d %d", group.name, group.ID, group.size()));
		removeAllVisitors();
		group = vgroup.find (groupID);
		groupName = group.toString();
		addAllVisitors( changeGroupMsg.others );
		groupMap.get( group ).setSelected( true );
	    	clientWindow.setTitle( String.format("%s - %s (%s_%d)", titleStr, v, group.name, group.ID ) ); 
	    } else if ( changeGroupMsg.others == null ) { // This client is in the visitor of the original group..
		//System.out.printf("%s is to go removed since others == null\n", v );
		removeOneVisitor( changeGroupMsg.body);
		repaintVisitorPanel();
	    } else {  // For the visitors in the new group of the visitor of changing group.
		addOneVisitor( v );
	    }
	    // groupMap.get(group).setText( String.format("%s_%d %d", group.name, group.ID, group.size()));
	    repaintVisitorPanel();
	}

	void repaintVisitorPanel() { plVisitors.revalidate(); plVisitors.repaint(); }
	void repaintGroupPanel() { plGroups.revalidate(); plGroups.repaint(); }

	public void run() {
	    if ( in == null || msgBoard == null ) return;
	    try {
		while ( true ) {

		    // Read message
		    try {
			message = (Message) in.readObject() ;
		    } catch (ClassNotFoundException nfdEx) {
			msgBoard.append("Error in readObject() from socket client agent.\n"
			    + nfdEx); 
			return ;
			}

		    switch( message.type ) {

			case Message.GETGROUPS: // message: ( GETGROUP, null, group_list) 
			    addAllGroups( message.others );  // group list is rececived.
			    break;

			case Message.ADDGROUP: // message: (ADDGROUP, new_group_info, group_list )
			    addOneGroup( message.body ); // add the new group.
			    synchronizeGroups ( message.others); // update groups, sizes with new counts.
			    break;

			case Message.REMOVEGROUP: // message: (REMOVEGROUP, removed_gID,, group_list).
			    removeOneGroup( message.body ); // removethe new group.
			    synchronizeGroups ( message.others); // update groups, sizes with new counts.
			    break;
			case Message.SYNCHRONIZE: // message: (SYNCHRONIZE, null,, group_list).
			    synchronizeGroups ( message.others); // update groups, sizes with new counts.
			    break;

			case Message.CHANGEGROUP: // message received: (CHANGEGROUP, v_info, list_v_info_of_toGroup))
			    processGroupChanged( message );  // group list is rececived.
			    repaintVisitorPanel();
			    break;

			case Message.LOGIN: // message: (LOGIN, login_visitor, group_member_list)
			    group = vgroup.find(groupID );
			    //System.out.printf("message.body : [%s] to converted to a visitor\n",message.body);
			    Visitor v = Visitor.toVisitor( message.body);
			    if ( (v.name.compareTo( tfNameMsg[0].getText().trim()) == 0) && ! loggedIn ) { 
				loginSetup( v );
				addAllVisitors( message.others); 
			    } else { 
				addOneVisitor( v );
			    }
			    repaintVisitorPanel();
			    // groupMap.get(group).setText( String.format("%s_%d %d", group.name, group.ID, group.size()));
			    msgBoard.append(message.body+ ": logged in.\n" );
			    break;

			case Message.PUBLIC:
			    msgBoard.append(message.body + '\n' );
			    break;

			case Message.PRIVATE:
			    msgBoard.append(message.body + '\n' );
			    break;

			case Message.LOGOUT: // messge Received: LOGOUT, out_visitor, remaing_visitors ). 
			    removeOneVisitor( message.body );
			    //groupMap.get(group).setText( String.format("%s_%d %d", group.name, group.ID, group.size()));
			    repaintVisitorPanel();
			    msgBoard.append(message.body + ": logged out.\n" );
			    break;
		    }
		    if ( msgBoard.getText().length() > 0 )
			msgBoard.setCaretPosition(msgBoard.getText().length()  - 1) ;
		} // end of 
	    } catch( IOException e ) { msgBoard.append("Chatroom server is down.\n"); return; }
	} // end of run()
    }  // end of class CAgent, the client agent or receiver.

} // end of class Client
