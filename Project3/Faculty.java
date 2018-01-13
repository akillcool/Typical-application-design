
public class Faculty extends Employee{
	
	String degreeHeld;
	
	public Faculty(){
		generate();
	}
	
	public void generate(){
		super.generate();
		degreeHeld = Names.degree[rnd.nextInt(Names.degree.length)];
	}
	public String toString(){
		return toString(true);
	}
	public String toString(boolean lab){
		return (lab ? "Fac" : "") + String.format("%s %15s", super.toString(false),degreeHeld);
	}
	public String htmlRow(){
		return "<TR>" + htmlColumns() + "<TR>";
	};
	public String htmlColumns(){
		return String.format("%s<TD>%15s</TD>",super.htmlColumns(),degreeHeld); 
	}
}
