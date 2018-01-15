import java.util.Scanner;


public class ShowClass {
	
 HashTable<Member> ht = null;  
 Scanner scan = new Scanner(System.in);

 int EmptyBlockCount =0;
 int DataBlockCount =0;	

 int  EmptyBlockMaximum = 0;
 int  EmptyBlockMinimum = 100000000;
 int  DataBlockMaximum = 0;
 int  DataBlockMinimum = 100000000;

 int  EmptyBlockToalSize =0;
 int  DataBlockToalSize =0;

 float  EmptyBlockAvgSize = 0.0f;
 float  DataBlockAvgSize = 0.0f;
 
public  void Show_H_Info() {
	System.out.printf("		+===========================================================================================================================+\n"); 
	System.out.printf("		|                                          Implementation of Hash Table                                                     |\n");
	System.out.printf("		|                                                Assignment 2                                                               |\n");
	System.out.printf("		+===========================================================================================================================+\n"); 
	System.out.printf("		| Command  |                   Description                    | Command  |                   Description                    |\n");
	System.out.printf("		+===========================================================================================================================+\n"); 
	System.out.printf("		|   g G    |  Prompt for two intergers, the capacity and the  |   t T    | Perform a successful search on each of object in |\n");
	System.out.printf("		|          |  load factor of a hash table. Create a new hash  |show Time | the hash table, and 'capacity' many unsuccessful |\n");
	System.out.printf("		|          |table, with 20%% as increment percentage, generate |Complexity| searches, list the (1) average comparisons from  |\n");
	System.out.printf("		|          |(capacity * load factor) many mixed Member objects|of Bin. & |all successful search, the theoretic successful   |\n");
	System.out.printf("		|          |         and add them to the hash table.          |Hash. Srch|search complexity [(1 + 1/(1-a))/2], and the      |\n");
	System.out.printf("		+----------+--------------------------------------------------+See exampl|theoretical un-successful search time complexity  |\n");
	System.out.printf("		|   a A    | Instantiate a new member object, and the object  |e below.  |[(1+1/(1-a)**2)/2], where a is the loading factor.|\n");
	System.out.printf("		|          |   into the hash table. Display the newly added   +----------+--------------------------------------------------+\n");
	System.out.printf("		|          |  member, its home address and current address.   |   b B    |Display information on blocks formed by contiguous|\n");
	System.out.printf("		+----------+--------------------------------------------------+          |data or empty cells inside table. For each block, |\n");
	System.out.printf("		|   r R    | Ask for an ID or hash code of an object. Remove  |          |display the type of block (either data or empty), |\n");
	System.out.printf("		|          |     the object whose ID matches the given ID.    |          |the starting and ending addresses, size of block. |\n");
	System.out.printf("		+----------+--------------------------------------------------+          |At the end of block listing, show the total number|\n");
	System.out.printf("		|   f F    |   Ask for an ID or hash code, of a object,       |          |   of blocks, the maximum, the minimum and the    |\n");
	System.out.printf("		|          | display the object, ID current address and home  |          |  average block sizes, for each type. Allow quit  |\n");
	System.out.printf("		|          |                     address.                     |          | listing the total, maximum, minimum and average  |\n");
	System.out.printf("		+----------+--------------------------------------------------+          |must show the correct data even if the listing of |\n");
	System.out.printf("		|   c C    |Show contents of hash table in a tabular way. one |          |                   block ends.                    |\n");
	System.out.printf("		|          | object per line and ten objects per screen. for  +----------+--------------------------------------------------+\n");
    System.out.printf("		|          |   each line, the following columns have to be    |   p P    |List the table parameters. The parameters include.|\n");
	System.out.printf("		|          | displayed: Object, current address, home address |          |    the capacity, size, load factor, increment    |\n");
	System.out.printf("		|          |  and the displacement from home address to its   |          |      percentage,and the actual load factor.      |\n");
	System.out.printf("		|          |  and the displacement from home address to its   |          |      percentage,and the actual load factor.      |\n");
	System.out.printf("		|          |                 current address.                 +----------+--------------------------------------------------+\n");
	System.out.printf("		+----------+--------------------------------------------------+   v V    |    Verify whether all non-null elements in table |\n");
	System.out.printf("		|  i I x   |   Remove object at table index to test Verify    |          |         are reachable or not.                    |\n");
	System.out.printf("		+----------+--------------------------------------------------+----------+--------------------------------------------------+\n");
	System.out.printf("		|  h H ?   |                 Show this menu.                  |   q Q    |                Exit the program.                 |\n");
	System.out.printf("		+----------+--------------------------------------------------+----------+--------------------------------------------------+\n");		
	}

public  void Show_G_Info() {
	
	if( ht != null)
	{
		System.out.printf(" Hashtable has been created ! \n");
		return;
	}
	System.out.printf("Input  capacity and load Max Num (like : 100  75 ) :");
	int capacity = scan.nextInt();
	int LMN      = scan.nextInt();
	while(capacity<LMN || capacity<=0 || LMN <=0)
	{
			System.out.printf("       Input error ! Capacity  need to >  Load Max Num   ,  capacity>0 ,Load Max Num >0 !\n");
			System.out.printf("Please Input  capacity and load Max Num again :");
			capacity = scan.nextInt();
            LMN      = scan.nextInt();
	}
     		
	ht = new HashTable<Member>(capacity,LMN);
	for ( int i = 0; i < ht.maxLDF ; i ++ )
		ht.put( new Member() );
	System.out.printf("random add object to table .......\n");
	System.out.printf("Create Hashtable successfull! \n");
	//ht.show();
}

public  void Show_A_Info() {

  if(ht == null)
  {
	  System.out.printf(" Please Create Hashtable first! \n");
	  return;
  }
  Member m  =	new Member();
  ht.put(m);
  System.out.printf(" Add radom element successful! \n");
  int home = m.hashCode() % ht.capacity;
  int current = ht.find(m);
  System.out.printf(
	    	 "%30s %8d %8d %8d\n", 
			 home < 0  ? "null" : m.toString().trim().substring(0,25) ,
			 current,		
		     home, 
			 ht.distance(home, current) 
		);

}

public  void Show_R_Info() {
	
	if(ht == null)
	  {
		  System.out.printf(" Please Create Hashtable first! \n");
		  return;
	  }
	 System.out.printf("Input hash code of an object:");
	 int hashCode = scan.nextInt();
	if( ht.remove(new Member(hashCode))== null)
	    System.out.printf("there is no the object with this hash code!\n");
	else
		System.out.printf("remove sucessfull!\n");
}

public  void Show_F_Info() {
   
	if(ht == null)
	  {
		  System.out.printf(" Please Create Hashtable first! \n");
		  return;
	  }
	 System.out.printf("Input hash code of an object:");
	  int hashCode = scan.nextInt();	
	  Member m  =	new Member(hashCode);
	  int current = ht.find(m);
	  if(current <0)
	  {
		  System.out.printf("there is no the object with this hash code!\n");
		  return;
	  }
	  int home = m.hashCode() % ht.capacity;
	  System.out.printf(
		    	 "%30s %8d %8d %8d\n", 
				 m.toString().trim().substring(0,25) ,
				 current,		
			     home  
			);
}

public  void Show_C_Info() {

	if(ht == null)
	  {
		  System.out.printf(" Please Create Hashtable first! \n");
		  return;
	  }
	
    System.out.printf("\n|+============================================================+\n");
	System.out.printf("|                  Contents of Hash Table                    |\n");     
    System.out.printf("| [Capacity-%d, Size-%d Load Fac.-%.2f, Increment-%.2f]     |\n",ht.capacity ,ht.size ,ht.Aphla , ht.incPct );     
    System.out.printf("+============================================================+\n"); 
	System.out.printf("|%21s %18s %8s %10s|\n", "Oject", "Current", "Home", "Distance");  
	System.out.printf("+------------------------------------------------------------+\n");
	
	int home = -1;
	String ch = "";
	int i = 0;
	int sumOfDisplacement = 0;
	for( i = 0; i < ht.capacity; i++)
	{
		Member t1 = ht.getTableMemByIndex(i);
	    home = ((t1 == null) ? -1 : t1.hashCode() % ht.capacity) ;
	    sumOfDisplacement +=  (home < 0 ? 0 : ht.distance (home, i));
	    if(ch.equals("q" )==false && ch.equals("Q" )==false)
	    {
	    	System.out.printf(
	    	 "|%30s |%8d |%8d |%8d|\n", 
			 home < 0  ? "null" : t1.toString().trim().substring(0,25) ,
			 i,		
		     home < 0 ? i : home,  
			 home < 0 ? 0 : ht.distance (home, i) 
		     );
	         System.out.printf("+------------------------------------------------------------+\n");
	    }
	    if((i+1)%10==0 && ch.equals("q" )==false && ch.equals("Q" )==false)
	    {
	    	System.out.printf("                        %d more record(s) to list (Q/q to quit) ... \n",ht.capacity-i-1);
	    	ch = scan.nextLine();	
	    }
	}
	System.out.printf("|    Displacement: Total = %d     Average = %.2f            |\n",sumOfDisplacement ,sumOfDisplacement*1.0f/ht.size );     
	System.out.printf("+------------------------------------------------------------+\n"); 
}

public  void Show_I_Info() {
	
	if(ht == null)
	  {
		  System.out.printf(" Please Create Hashtable first! \n");
		  return;
	  }
	 System.out.printf("Input the index of hashtable to remove :");
	 int index = scan.nextInt();
	 Member m = ht.removeByTableindex(index);
     if(m==null)
    	 System.out.printf("table[index] = null  remove failed! \n");
     else
	 System.out.printf(
	    	 "remove successfull !\n%30s %8d %8d %8d\n", 
			 m.toString().trim().substring(0,25) ,
			 index,		
			 m.hashCode()%ht.capacity,
			 ht.distance ( m.hashCode()%ht.capacity, index) 
		     ); 
}

public  void Show_T_Info() {

   if(ht == null)
	{
		  System.out.printf(" Please Create Hashtable first! \n");
		  return;
	}
	
	Member t1 = null;
	int home  = -1;
	int num   =  0;
	int i     =  0;
	int sumOffailedTime =0;
	int sumOfdistance=0; 
	float ASLsuccess = 0.0f;
	float ASLunsuccess = 0.0f;
	
	for(i =0;i<ht.capacity;i++)
	{
	  t1 = ht.getTableMemByIndex(i);
      home = ((t1 == null) ? -1 : t1.hashCode() % ht.capacity) ;
      sumOfdistance +=  home < 0 ? 0 : (ht.distance (home, i)+1);
      
      num++;
      if(t1==null)
      {
    	 sumOffailedTime += num*(num+1)/2;
         num=0;  
      }
	}
	
	if(num !=0)
	{
	  
       while(ht.getTableMemByIndex(i%ht.capacity)!=null)
       {
        i++;
        num++;
       }
       num++;
       sumOffailedTime += num*(num+1)/2;
	}
	
	ASLsuccess = sumOfdistance*1.0f/ht.size;
	
	ASLunsuccess = sumOffailedTime*1.0f/ht.capacity;
	
	float a = ht.Aphla;
	float TheoreticASLsuccess = (1 + 1/(1-a))/2.0f;
	float TheoreticUnASLsuccess = (1 + 1/((1-a)*(1-a)))/2.0f;
	
	
	System.out.printf("\n+===================================================+\n");      
	System.out.printf("|       Time Complexities of Practical              |\n");       
	System.out.printf("|         & Theoretic Hashtable Search              |\n");
    System.out.printf("+===================================================+\n");       
    System.out.printf("| Practical  | Practical  | Theoretic  | Theoretic  |\n");       
    System.out.printf("| Hashtable  | Hashtable  | Hashtable  | Hashtable  |\n");       
    System.out.printf("| Successful |Unsuccessful| Successful |Unsuccessful|\n");       
    System.out.printf("|   Search   |   Search   |   Search   |   Search   |\n");       
    System.out.printf("+------------+------------+------------+------------+\n");       
    System.out.printf("|    %.2f    |   %.2f    |    %.2f    |    %.2f    |\n",ASLsuccess,ASLunsuccess,TheoreticASLsuccess,TheoreticUnASLsuccess);       
    System.out.printf("+------------+------------+------------+------------+\n");   
}

public  void Show_B_Info() {

	if(ht == null)
	{
	  System.out.printf(" Please Create Hashtable first! \n");
	  return;
	}
	
	System.out.printf("\n+===================================================+\n");
	System.out.printf("|  Information on Data and Blank Blocks             |\n");     
	System.out.printf("+===================================================+\n"); 
	System.out.printf("| Block Type |  Starting  |   Ending   |    Size    |\n"); 
	System.out.printf("|            |  Address   |  Address   |            |\n"); 
	System.out.printf("+------------+------------+------------+------------+\n");  
	
	int flag  = -1;
	int num   =  0;
	int i     =  0;
	String ch ="";
	int begin = 0;
	int end   = ht.capacity-1;
	
	if((ht.IsTableMemEmptyByIndex(begin) ^ ht.IsTableMemEmptyByIndex(end))==0)
	{
	   if(ht.getTableMemByIndex(begin)==null)
	   {
		
		  while(ht.getTableMemByIndex(begin)==null)
		   begin++;
		  while(ht.getTableMemByIndex(end)==null)
		   end--;
	   }
	   else
	   {
		   while(ht.getTableMemByIndex(begin)!=null)
		   begin++;
		   while(ht.getTableMemByIndex(end)!=null)
		   end--;
	   }
	}
	
	flag = ht.IsTableMemEmptyByIndex(begin);
	for(i=begin;i<=end;i++ )
	{
       if(ht.IsTableMemEmptyByIndex(i)==flag)
    	   num++;
       else
       {
    	   System.out.printf("+------------+------------+------------+------------+\n");
    	   System.out.printf("|%12s| %10d |%11d |%12d|\n",flag!=0?"Empty":"Data",i-num, i-1, num);
    	   BlockComputer(flag ,  num);
    	  
    	   num=1;
    	   flag = ht.IsTableMemEmptyByIndex(i);
    	   
    	   if(( EmptyBlockCount+ DataBlockCount)%10==0)
   	       {
   	    	System.out.printf("                               press Q/q to quit list ... \n");
   	      	ch = scan.nextLine();
   	    	if(ch.equals("q" )|| ch.equals("Q" ))
   	    	break;	
   	       }
       }
	}
	
  if(ch.equals("q" )== false && ch.equals("Q" )==false)
  {
	System.out.printf("+------------+------------+------------+------------+\n");
	System.out.printf("|%12s| %10d |%11d |%12d|\n",flag!=0?"Empty":"Data",i-num, i-1, num);
	BlockComputer(flag ,  num);
	
	if(end<ht.capacity-1)
	{
	   num =ht.capacity-end+begin-1;
	   flag = ht.IsTableMemEmptyByIndex(ht.capacity-1);
	   System.out.printf("+------------+------------+------------+------------+\n");
	   System.out.printf("|%12s| %10d |%11d |%12d|\n",flag!=0?"Empty":"Data",end+1, begin-1, num);
	   BlockComputer(flag ,  num);
	} 
  }
    System.out.printf("+------------+------------+------------+------------+\n");
	System.out.printf("|  Block Type  Count   Maximum Minimum Avg Size     |\n");
	System.out.printf("|    Data         %d      %d      %d      %.2f      |\n",DataBlockCount,DataBlockMaximum,DataBlockMinimum,DataBlockToalSize*1.0f/DataBlockCount);
	System.out.printf("|   Empty         %d      %d      %d      %.2f      |\n",EmptyBlockCount,EmptyBlockMaximum,EmptyBlockMinimum,EmptyBlockToalSize*1.0f/EmptyBlockCount);
	System.out.printf("+------------+------------+------------+------------+         \n ");
}

public  void Show_P_Info() {

	if(ht == null)
	  {
		  System.out.printf(" Please Create Hashtable first! \n");
		  return;
	  }
     System.out.printf("\n+================================================================+\n");  
     System.out.printf("|                  Parameters of The Hash Table                  |\n");    
     System.out.printf("+================================================================+\n");  
     System.out.printf("|  Capacity  |    Size    | Increment  | Specified  |Actual Load |\n");    
     System.out.printf("|            |            |            |Load Factor |   Factor   |\n");
     System.out.printf("+------------+------------+------------+------------+------------+\n");  
     System.out.printf("|    %d      |    %d      |       %.2f | %.2f       |%.2f        |\n", ht.capacity ,ht.size , ht.incPct , ht.Aphla ,ht.size *1.0f/ ht.capacity);    
     System.out.printf("+------------+------------+------------+------------+------------+\n");
         	
}

public  void Show_V_Info() {
	if(ht == null)
	  {
		  System.out.printf(" Please Create Hashtable first! \n");
		  return;
	  }	
   	ht.verify();
}

public  void Show_Q_Info() {
	
	System.out.print("    Exit the program. \n");
	System.exit(0);
}

private void  BlockComputer(int flag , int num)
{
	 if(flag!=0)
	   {
		     EmptyBlockMaximum = EmptyBlockMaximum <num ? num:EmptyBlockMaximum;
		     EmptyBlockMinimum = EmptyBlockMinimum >num ? num:EmptyBlockMinimum;
		     EmptyBlockCount++;
		     EmptyBlockToalSize+=num;
	   }
	   else
	   {
		   DataBlockMaximum = DataBlockMaximum <num ? num:DataBlockMaximum;
		   DataBlockMinimum = DataBlockMinimum >num ? num:DataBlockMinimum;
		   DataBlockCount++;
		   DataBlockToalSize+=num;
	   }
}

}
