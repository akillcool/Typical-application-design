
import java.util.Random;

public class Member implements Comparable <Member> {

    static Random rdm = new Random();

    protected String firstName, lastName;
    protected int ID;

    public int compareTo (Member m) { return this.ID - m.ID; }
    public int hashCode( ) { return ID; }
    public Member setID( int newID ) { ID = newID; return this; }
    public boolean equals( Member m ) { return ID == m.ID ; }

    public Member() { generate(); }
    public Member( int id ) { generate(); ID = id; }

    protected void generate() {
        ID = rdm.nextInt(999999999 - 100000000 + 1) + 100000000;
        lastName = Names.lastName[rdm.nextInt(Names.lastName.length)];
        firstName = Names.firstName[rdm.nextInt(Names.firstName.length)];
    }

    public String toString() {
        return toString(false);
    }

    public String toString(boolean lb) {
        return (lb ? "MEM " : "") + 
                String.format("%3d-%02d-%04d %10s %-10s", 
                    ID / 1000000, ID % 1000000 / 10000, ID % 10000, lastName, firstName);
    }

    public String htmlRow() {
        return String.format("<TR>\n\t %s \n</TR>", htmlColumns());    
    }

    public String htmlColumns() {
        return String.format("<TD>%d</TD><TD>%s</TD><TD>%s</TD>", ID, lastName, firstName);
    }



}
