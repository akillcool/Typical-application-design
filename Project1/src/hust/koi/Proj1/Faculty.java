package hust.koi.Proj1;

public class Faculty extends Employee {

	protected String degreeHeld;
	protected String position;

	@Override
	public void generate() {
		super.generate();
		degreeHeld = Names.degree[random.nextInt(Names.degree.length)];
		position = Names.position[random.nextInt(Names.position.length)];
	}

	@Override
	public String toString(boolean flag) {
		return (flag ? "FAC " : "") + String.format("%s %10s %15s", super.toString(false), degreeHeld, position);
	}

	@Override
	public String htmlRow() {
		return "\t<TR>\n\t\t<TD>FAC</TD>" + htmlColumns() + "\n</TR>";
	}

	@Override
	public String htmlColumns() {
		return super.htmlColumns() + String.format("<TD>%s</TD><TD>%s</TD>", degreeHeld, position);
	}

}
