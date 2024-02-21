import java.lang.String;

public class Info {
    protected String name;
    protected String pnumber;

    public Info() {
    }

    public Info(String name,
            String pnumber) {
        this.name = name;
        this.pnumber = pnumber;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void printInfo() {

        System.out.println("Name : " + name);
        System.out.println("Phone Number : " + pnumber);

    }

}