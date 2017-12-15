package hust.koi.Proj1;

public class Staff extends Employee {

	protected String jobTitle;
	protected String skill;

	@Override
	public void generate() {
		super.generate();
		jobTitle = Names.title[random.nextInt(Names.title.length)];
		skill = Names.skill[random.nextInt(Names.skill.length)];
	}

	@Override
	public String toString(boolean flag) {
		return (flag ? "STA " : "") + String.format("%s %s %s", super.toString(false), jobTitle, skill);
	}

	@Override
	public String htmlRow() {
		return "\t<TR>\n\t\t<TD>STA</TD>" + htmlColumns() + "\n</TR>";
	}

	@Override
	public String htmlColumns() {
		return super.htmlColumns() + String.format("<TD></TD><TD></TD><TD>%s</TD><TD>%s</TD>", jobTitle, skill);
	}

}
