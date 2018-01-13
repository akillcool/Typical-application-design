
public class Staff extends Employee{
	
	protected String jobTitle;
	
	public Staff(){
		generate();
	}
	
	public void generate(){
		super.generate();
		jobTitle = Names.title[rnd.nextInt(Names.title.length)];
	};
	
	public String toString(){
		return toString(true);
	};
	
	public String toString(boolean lab){
		return (lab ? "Sta" : "") + String.format("%s %15s", super.toString(false),jobTitle);
	};
	
	public String htmlRow(){
		return "<TR>"+ htmlColumns() +"</TR>";
	};
	
	public String htmlColumns(){
		return String.format("%s<TD>%15s</TD>",super.htmlColumns(),jobTitle);
	};
}
