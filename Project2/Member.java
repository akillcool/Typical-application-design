import java.util.Random;

public class Member implements Comparable<Member>{
	static Random rnd = new Random();
	
	public int hashCode(){return ID;}
	
	public Member(){
		generate();
	};

	public void generate(){
		ID = 100000000 + rnd.nextInt(899999999);
		firstname = Names.firstName[rnd.nextInt(Names.firstName.length)];
		lastname = Names.lastName[rnd.nextInt(Names.lastName.length)];
	};
	
	public int compareTo(Member m){
		return ID - m.ID;
	};
	
	public String toString(){
		return toString(true);
	};
	
	public String toString(boolean lab){
		return (lab ? "MEN" : "") + String.format("%03d-%02d-%04d %10s %10s",ID/1000000,ID/10000%100,ID%10000,firstname,lastname);
	};
	
	public String htmlColumns(){
		return String.format("<TD>%03d-%02d-%04d</TD> <TD>%10s</TD><TD>%10s</TD>",ID/1000000,ID/10000%100,ID%10000,firstname,lastname);
		
	};
	
	public String htmlRow(){
		return "<TR>" + htmlColumns() + "</TR>";
	};
	protected String firstname,lastname;
	Integer ID;

}
