
public class Employee extends Member{
	protected	String department; 
	protected int yearHired;

	public Employee(){
		generate();
	}
	
	public void generate(){
		super.generate();
		department = Names.department[rnd.nextInt(Names.department.length)];
		yearHired = rnd.nextInt(19) + 1;
	}
	
	public String toString(){
		return toString(true);
	}
	
	public String toString( boolean lab){
		return (lab ? "Emp" : "") + String.format("%s %15s  %-4d", super.toString(false),department,yearHired);
	}
	
	public String htmlRow(){
		return "<TR>" + htmlColumns() + "</TR>";
	};
	
	public String htmlColumns(){
		return String.format("%s<TD>%10s</TD><TD>%2d</TD>",super.htmlColumns(),department,yearHired);
	}
}
