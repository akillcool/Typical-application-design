package hust.koi.Proj1;

public class Student extends Member {

	protected String major;
	protected float GPA;
	protected String sport;

	// There is no need to write a Constructor for the inherited class,
	// because we have overload the generate() method.
	// public StudentMember() {
	// generate();
	// }

	@Override
	public void generate() {
		super.generate();
		major = Names.department[random.nextInt(Names.department.length)];
		GPA = random.nextFloat() * 4;
		sport = Names.sport[random.nextInt(Names.sport.length)];
	}

	@Override
	public String htmlRow() {
		return "\t<TR>\n\t\t<TD>STU</TD>" + htmlColumns() + "\n</TR>";
	}

	@Override
	// set the flag if there will be child class
	public String toString(boolean flag) {
		return (flag ? "STU " : "") + String.format("%s %s %1.2f %s", super.toString(false), major, GPA, sport);
	}

	@Override
	public String htmlColumns() {
		return super.htmlColumns() + String.format("<TD>%s</TD><TD>%1.2f</TD><TD>%s</TD>", major, GPA, sport);
	}

}
