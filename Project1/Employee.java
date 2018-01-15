
public class Employee extends Member {

	protected String department;
	protected int yearHired;

	@Override
	public void generate() {
		super.generate();
		department = Names.department[random.nextInt(Names.department.length)];
		yearHired = random.nextInt(40);
	}

	@Override
	public String toString() {
		return this.toString(false);
	}

	@Override
	public String toString(boolean flag) {
		return (flag ? "EMP " : "") + String.format("%s %36s %15s %4d", super.toString(false), "", department, yearHired);
	}

	@Override
	public String htmlRow() {
		return "\t<TR>\n\t\t<TD>EMP</TD>" + htmlColumns() + "\n</TR>";
	}

	@Override
	public String htmlColumns() {
		return super.htmlColumns()
				+ String.format("<TD></TD><TD></TD><TD></TD><TD>%s</TD><TD>%2d</TD>", department, yearHired);
	}

}
