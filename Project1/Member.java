
import java.util.Random;

public class Member implements Comparable<Member> {

	protected String firstName;
	protected String lastName;
	protected int ID;

	// Static value should be used in a static way, using this.XXX is invalid.
	protected static Random random = new Random();

	public Member() {
		this.generate();
	}

	public void generate() {
		ID = random.nextInt(999999999 - 100000000 + 1) + 100000000;
		firstName = Names.firstName[random.nextInt(Names.firstName.length)];
		lastName = Names.lastName[random.nextInt(Names.lastName.length)];
	}

	// The toString() method are called in order to show details of the class
	// Double overload in order to set different condense name for each inherent
	// class
	public String toString() {
		return this.toString(false);
	}

	public String toString(boolean flag) {
		return (flag ? "MEM " : "") + String.format("%03d-%02d-%04d %15s %15s", ID / 1000000, ID % 1000000 / 10000,
				ID % 10000, lastName, firstName);
	}

	@Override
	public int compareTo(Member member) {
		return this.ID - member.ID;
	}

	// <TR> equals rows
	public String htmlRow() {
		return "\t<TR>\n\t\t<TD>MEM</TD>" + htmlColumns() + "\n</TR>";
	}

	// <TD> equals columns in 1 row
	public String htmlColumns() {
		return String.format("<TD>%s</TD><TD>%s</TD><TD>%03d-%02d-%04d </TD>", lastName, firstName, ID / 1000000,
				ID % 1000000 / 10000, ID % 10000);
	}
}
