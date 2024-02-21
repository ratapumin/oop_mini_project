import java.util.Date;
import java.lang.String;

public class Customer extends Info {
    public String table_no;
    public Date table_date;

    public Customer() {
        System.out.println("conn");

    }

    public Customer(String table_no) {
        this.table_no = table_no;

    }

    public Customer(String name,
            String pnumber,
            String table_no) {
        super(name, pnumber);
        this.table_no = table_no;
    }

    public void setTableno(String table_no) {
        this.table_no = table_no;
    }

    public String getTableno() {
        return table_no;
    }

    public void setTabledate(Date table_date) {
        this.table_date = table_date;
    }

    public Date getTabledate() {
        return table_date;
    }

    public void calDate() {
        table_date = new Date();
        // System.out.println(table_date.toString());
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Name : " + table_no);
        System.out.println("Date : " + table_date.toString());

    }

}
