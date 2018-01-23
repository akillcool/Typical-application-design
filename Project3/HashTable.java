public class HashTable<T> {

   T [] table ;
  int	size, capacity, maxLDF;
  float incPct;
  float Aphla;

  public HashTable( ) {
     init(10, 80, 0.2f );
  }

  public HashTable( int cap, int maxLDF, float incPCT) {
     init( cap, maxLDF , incPCT);
  }
  
  public HashTable( int cap, int maxLDF) {
	     init( cap, maxLDF , 0.2f);
	  }
 
  public HashTable( int cap ) {
     init( cap, (int)(cap*0.75), 0.2f);
  }

  @SuppressWarnings("unchecked")
  private void init( int cap, int ldFactor, float inc ) {
     capacity = cap;  maxLDF = ldFactor ; incPct = inc;
     Aphla = maxLDF*1.0f/capacity;
     table = (T []) new Object[cap];
  } 
  
 public int  find ( T obj ) {
	int home = hash( obj ) ;

	while ( table[home] != null && obj.hashCode() !=  table[home].hashCode() )
	      home = (home + 1) % capacity ;

	return  table[home] != null ? home : -home ;
  }

  public T get ( T obj ) {
	int idx = find ( obj );
	return  idx >= 0  ? table[idx] : null;
  }
  
  public T remove( T obj ) {
	int currentLoc = find ( obj );
	if ( currentLoc < 0 || (currentLoc==0 && table[0]==null) ) return null;

	obj = table[currentLoc];
	table[currentLoc] = null; size --;

	shift( currentLoc, ( currentLoc + 1 ) % capacity );

	return obj;
  }
  
  public T removeByTableindex( int index ) {
		
		if(index<0 ||index > capacity-1)
		{
			return null;
		}
		T obj = table[index];
		table[index] = null; size --;
		shift( index, ( index + 1 ) % capacity );

		return obj;
	  }

  private boolean needed( int empty, int current ) {

	if (table[current] == null ) return false ;

	int home = hash( table[current] );
	int emptyToHome = distance( home, empty );
	int currentToHome = distance( home, current);
	return emptyToHome < currentToHome;
  }

  private void shift( int empty, int currentLoc) {
	if ( table[currentLoc] == null ) return ;

	if ( needed( empty, currentLoc) ) {
		table[empty] = table[currentLoc];
		table[currentLoc] = null;
		shift( currentLoc, (currentLoc+ 1) % capacity );
	} else shift( empty, (currentLoc+ 1) % capacity );
  }

  @SuppressWarnings("unchecked")
  private void rehash () {
     T [] old = table;
     
     capacity = (int) ((1 + incPct) * capacity)  + 1;
     maxLDF   = (int)(capacity*Aphla);
     table = (T[]) new Object[capacity];
     size = 0;
     for ( int i = 0; i < old.length; i ++ )
	if (old[i] != null ) put( old[i] );
 
     old = null;
  }

  private int hash( T obj ) { return  obj == null ? -1 : obj.hashCode() % capacity ; }

  public HashTable<T> put( T obj ) {
	if ( size * 1.0f / capacity  >= maxLDF / (capacity*1.0f) ) rehash();

	int home = hash( obj ) ;

	while ( table[home] != null && table[home].hashCode()!= obj.hashCode() )
		   home = (home + 1 ) % capacity;

	if(table[home] != null && table[home].hashCode() == obj.hashCode() )// if table[home].hashCode == obj.hashCode , do Update 
    {
		size--; 
    }
	table[home] = obj; 
    size++;

        return this;
  }

  int distance ( int home, int other ) {
	return home <= other ? other - home : capacity - home + other;
  } 

  public void show() {

	System.out.printf("%25s %18s %8s %9s\n", "Oject", "Current", "Home", "Distance");  

	int home;
	for(int i = 0; i < capacity; i++) {
	    home = hash( table[i] ) ;	
	    System.out.printf(
	    	 "%30s %8d %8d %8d\n", 
			 home < 0  ? "null" : table[i].toString().trim().substring(0,25) ,
			 i,		
		     home < 0 ? i : home,  
			 home < 0 ? 0 : distance (home, i  ) 
		);
	}
   }

 public int verify( ) {
      int found = 0, unFound = 0, res = 0;
      for ( int i = 0; i < table.length; i ++ )
      {
	     if ( table[i] != null )
	     {
	     res = find(table[i]);
	     if ( res < 0 ) unFound ++;
	     else found++;
	     }
      }
      System.out.printf("Found [%d], Not found [%d]!\n", found, unFound);
      return found;
   }

public T getTableMemByIndex(int index)
{
	   if(index>table.length-1 || index<0)
		   return null;
       return table[index]	;
}

public int IsTableMemEmptyByIndex(int index)
{
	   if(index>table.length-1 || index<0)
		   return -1;
       return table[index]==null? 1:0;
}

}
