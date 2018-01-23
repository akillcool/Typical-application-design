# Assignment 3 : Java Generics and Implementation of Hash Tables
Dr. Huaqing Wang

## I.	Purposes:
	- Implementation of generic class for hash table with linear
	  probing as collision handling method.
	- search time complexities of hash table.

## II.	Notices:

       1. You are encouraged to generate HTML files for each display required
          below, and start default browser to display the required contents.
       2. For C/c (display the hash table contents), you may find out a better
          way to display object when the number of object is too big.
       3. Copy *Hast*.* to your directory and Run "java TestHashTable" to see
          results displayed below.
       
## III.	Description

	Write a program which display the following line:

  // Enter a command(H/h for help):
	Enter H/h/? for help, or command : 


   When H/h/? is entered, the following menu is printed:

   a/A: add a distance
   t/T: 100000001, 10000000013
   @Supress Warning



+===========================================================================================================================+
|                                               Implementation of Hash Table                                                |
|                                                       Assignment 3                                                        |
+===========================================================================================================================+
| Command  |                   Description                    | Command  |                   Description                    |
+----------+--------------------------------------------------+----------+--------------------------------------------------
|   g G    |  Prompt for two integers, the capacity and the   |   t T    | Perform a successful search on each of object in |
|          |  load factor of a hash table. Create a new hash  |          | the hash table, and 'capacity' many unsuccessful |
|          |table, with 20% as increment percentage, generate |          | searches, list the (1) average comparisons from  |
|          |(capacity * load factor) many mixed Member objects|          |all successful searches, the theoretic successful |
|          |         and add them to the hash table.          |          |search complexity [(1 + 1/(1-a))/2], and the      |
+----------+--------------------------------------------------+          |theoretical un-successful search time complexity  |
|   a A    | Instanciate a new member object, and the object  |          |[(1+1/(1-a)**2)/2], where a is the loading factor.|
|          |   into the hash table. Display the newly added   +----------+--------------------------------------------------+
|          |  member, its home address and current address.   |   b B    |Display information on blocks formed by contiguous|
+----------+--------------------------------------------------+          |data or empty cells inside table. For each block, |
|   r R    | Ask for an ID or hash code of an object. Remove  |          |display the type of block (either data or empty), |
|          |     the object whose ID matches the given ID.    |          |the starting and ending addresses, size of block. |
+----------+--------------------------------------------------+          |At the end of block listing, show the total number|
|   f F    |   Ask for an ID or hash code, of a object,       |          |   of blocks, the maximum, the minimum and the    |
|          | display the object, ID current address and home  |          |  average block sizes, for each type. Allow quit  |
|          |                     address.                     |          | listing the total, maximum, minimum and average  |
+----------+--------------------------------------------------+          |must show the correct data even if the listing of |
|   c C    |Show contents of hash table in a tabular way. one |          |                   block ends.                    |
|          | object per line and ten objects per screen. for  +----------+--------------------------------------------------+
|          |   each line, the following columns have to be    |   p P    |List the table parameters. The parameters include.|
|          | displayed: Object, current address, home address |          |    the capacity, size, load factor, increment    |
|          |  and the displacement from home address to its   |          |    percentage, and the actual load factor.       |
|          |                 current address.                 +----------+--------------------------------------------------+
+----------+--------------------------------------------------+   v V    |    Verify whether all non-null elements in table |
|  i I x   |   Remove object at table index to test Verify    |          |         are reachable or not.                    |
+----------+--------------------------------------------------+----------+--------------------------------------------------+
|  h H ?   |                 Show this menu.                  |   q Q    |                Exit the program.                 |
+----------+--------------------------------------------------+----------+--------------------------------------------------+


## IV.	Note that

  1. If both table[capacity-1] and table[0] contain null, then those
     two cells belong to the same empty block, and if both of those 
     two cells contains data, then two belongs to the same data block.

Some output examples:

  1. To show the table contents:
  
                   Enter a command, or Q/E/X to quite .... S

            +======================================================+            
            |                Contents of Hash Table                |            
            |[Capacity-100, Size-75 Load Fac.-0.75, Increment-0.2] |            
            +======================================================+            
            |         Object Value         |Current| Home  |Displac|            
            |                              |Address|Address| ement |            
            +------------------------------+-------+-------+-------+            
            |   850-09-7379 Castle Frank   |   0   |  79   |  21   |            
            +------------------------------+-------+-------+-------+            
            |   483-64-6001 Pratt Isabel   |   1   |   1   |   0   |            
            +------------------------------+-------+-------+-------+            
            | 254-75-1488 Cisneros Patrick |   2   |  88   |  14   |            
            +------------------------------+-------+-------+-------+            
            | 641-81-4703 Mondragon Simon  |   3   |   3   |   0   |            
            +------------------------------+-------+-------+-------+            
            |                              |   4   |       |       |            
            +------------------------------+-------+-------+-------+            
            |   158-85-7905 Kone Silvia    |   5   |   5   |   0   |            
            +------------------------------+-------+-------+-------+            
            | 140-67-0405 Dodrill Gurinder |   6   |   5   |   1   |            
            +------------------------------+-------+-------+-------+            
            |                              |   7   |       |       |            
            +------------------------------+-------+-------+-------+            
            |                              |   8   |       |       |            
            +------------------------------+-------+-------+-------+            
            |390-80-7009 Coldewey Gurinder |   9   |   9   |   0   |            
            +------------------------------+-------+-------+-------+            
                                90 more record(s) to list (Q/q to quit) ... 
            |                              |  10   |       |       |            
            +------------------------------+-------+-------+-------+            
            |   187-18-1011 White Victor   |  11   |  11   |   0   |            
            +------------------------------+-------+-------+-------+            
            |  400-28-0812 Wuertz Barbara  |  12   |  12   |   0   |            
            +------------------------------+-------+-------+-------+            
            |                              |  13   |       |       |            
            +------------------------------+-------+-------+-------+            
            |                              |  14   |       |       |            
            +------------------------------+-------+-------+-------+            
            |                              |  15   |       |       |            
            +------------------------------+-------+-------+-------+            
            |                              |  16   |       |       |            
            +------------------------------+-------+-------+-------+            
            |  528-72-0317 Fisher Suzanne  |  17   |  17   |   0   |            
            +------------------------------+-------+-------+-------+            
            |   737-47-2418 Elias George   |  18   |  18   |   0   |            
            +------------------------------+-------+-------+-------+            
            |    280-05-4317 Cooper Gil    |  19   |  17   |   2   |            
            +------------------------------+-------+-------+-------+            
                                80 more record(s) to list (Q/q to quit) ... Q

            |       Displacement: Total = 104 Average = 1.39       |            
            +------------------------------------------------------+

	    where "Displacement" means the records are not at their home addresses.
	    The total number of records which are not in their home address and the 
	    average displacements of the records listed so far must be calculated
            and shown at the end of listing.
           
  2. Show the parameters of the hash table:

                   Eneter a command, or Q/E/X to quite .... P

        +================================================================+       
        |                  Parameters of The Hash Table                  |       
        +================================================================+       
        |  Capacity  |    Size    | Increment  | Specified  |Actual Load |       
        |            |            |            |Load Factor |   Factor   |       
        +------------+------------+------------+------------+------------+       
        |         100|          75|        0.20|        0.75|        0.75|       
        +------------+------------+------------+------------+------------+       


   3. To show the time complexities of hash table and binary search:

                   Eneter a command, or Q/E/X to quite .... T

        +================================================================+       
        |      Time Complexities of Practical & Theoretic Hashtable      |       
        |          Search vs. Theoretic Binary Search Algorithm          |       
        +================================================================+       
        | Practical  | Practical  | Theoretic  | Theoretic  |Theoretical |       
        | Hashtable  | Hashtable  | Hashtable  | Hashtable  |   Binary   |       
        | Successful |Unsuccessful| Successful |Unsuccessful|   Search   |       
        |   Search   |   Search   |   Search   |   Search   |            |       
        +------------+------------+------------+------------+------------+       
        |    2.39    |   10.20    |    2.50    |    8.50    |    6.23    |       
        +------------+------------+------------+------------+------------+       


   3. To show the data and empty blocks information:

                   Eneter a command, or Q/E/X to quite .... B

             +===================================================+              
             |       Information on Data and Blank Blocks        |              
             +===================================================+              
             | Block Type |  Starting  |   Ending   |    Size    |              
             |            |  Address   |  Address   |            |              
             +------------+------------+------------+------------+              
             |   Empty    |     4      |     4      |     1      |              
             +------------+------------+------------+------------+              
             |    Data    |     5      |     6      |     2      |              
             +------------+------------+------------+------------+              
             |   Empty    |     7      |     8      |     2      |              
             +------------+------------+------------+------------+              
             |    Data    |     9      |     9      |     1      |              
             +------------+------------+------------+------------+              
             |   Empty    |     10     |     10     |     1      |              
             +------------+------------+------------+------------+              
             |    Data    |     11     |     12     |     2      |              
             +------------+------------+------------+------------+              
             |   Empty    |     13     |     16     |     4      |              
             +------------+------------+------------+------------+              
             |    Data    |     17     |     23     |     7      |              
             +------------+------------+------------+------------+              
             |   Empty    |     24     |     26     |     3      |              
             +------------+------------+------------+------------+              
             |    Data    |     27     |     36     |     10     |              
             +------------+------------+------------+------------+              
                                           Enter Q/q to quit listing ...
             |   Empty    |     37     |     37     |     1      |              
             +------------+------------+------------+------------+              
             |    Data    |     38     |     46     |     9      |              
             +------------+------------+------------+------------+              
             |   Empty    |     47     |     48     |     2      |              
             +------------+------------+------------+------------+              
             |    Data    |     49     |     50     |     2      |              
             +------------+------------+------------+------------+              
             |   Empty    |     51     |     52     |     2      |              
             +------------+------------+------------+------------+              
             |    Data    |     53     |     53     |     1      |              
             +------------+------------+------------+------------+              
             |   Empty    |     54     |     55     |     2      |              
             +------------+------------+------------+------------+              
             |    Data    |     56     |     59     |     4      |              
             +------------+------------+------------+------------+              
             |   Empty    |     60     |     61     |     2      |              
             +------------+------------+------------+------------+              
             |    Data    |     62     |     63     |     2      |              
             +------------+------------+------------+------------+              
                                           Enter Q/q to quit listing ... 
             |   Empty    |     64     |     65     |     2      |              
             +------------+------------+------------+------------+              
             |    Data    |     66     |     66     |     1      |              
             +------------+------------+------------+------------+              
             |   Empty    |     67     |     68     |     2      |              
             +------------+------------+------------+------------+              
             |    Data    |     69     |     70     |     2      |              
             +------------+------------+------------+------------+              
             |   Empty    |     71     |     71     |     1      |              
             +------------+------------+------------+------------+              
             |    Data    |     72     |     3      |     32     |              
             +------------+------------+------------+------------+              
             |   Block Type  Count   Maximum Minimum Avg. Size   |              
             |     Data        13      32      1       5.77      |              
             |     Empty       13      4       1       1.92      |              
             +---------------------------------------------------+

Notice that the counts, maximum, minimum and average size of blocks must be
        shown at the end of listing. If the listing is stopped by user, the numbers
        should show only the blocks counted before listing is stopped.  
             
