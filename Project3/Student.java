
public class Student extends Member{
	protected String major;
	protected float GPA;
	public Student(){
		generate();
	}
	
	public void generate(){
		super.generate();
		major = Names.department[rnd.nextInt(Names.department.length)];
		GPA = rnd.nextInt(400)/100.0f;
	}
	
	public String toString(){
		return toString(true);
	}
	
	public String toString(boolean lab){
		return (lab ? "Stu" : "") + String.format("%s %15s  %-4.2f", super.toString(false),major,GPA);
	}
	
	public String htmlRow(){
		return "<TR>" + htmlColumns() + "</TR>";
	}
	
	public String htmlColumns(){
		return String.format("%s<TD>%10s</TD><TD>%1.2f</TD>",super.htmlColumns(),major,GPA);
	}
}
