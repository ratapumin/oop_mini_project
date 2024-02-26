import java.util.Date;
import java.util.Locale;
import java.lang.String;
import java.text.SimpleDateFormat;

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
    }

    public String getTableDateAsString() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        table_date = new Date();
        return dateFormat.format(table_date);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Name : " + table_no);
        System.out.println("Date : " + table_date.toString());

    }

}
