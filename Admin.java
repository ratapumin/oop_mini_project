public class Admin extends Info {
    private String admin_no;
    public String admin_id;
    private String admin_pass;

    public Admin() {
    }

    public Admin(String admin_no,
            String admin_id,
            String admin_pass) {
        this.admin_no = admin_no;
        this.admin_id = admin_id;
        this.admin_pass = admin_pass;

    }

    public Admin(String name,
            String pnumber,
            String admin_no,
            String admin_id,
            String admin_pass) {
        super(name, pnumber);
        this.admin_no = admin_no;
        this.admin_id = admin_id;
        this.admin_pass = admin_pass;

    }

    public void setAdminno(String admin_no) {
        this.admin_no = admin_no;
    }

    public String getAdminno() {
        return admin_no;
    }

    public void setAdminid(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdminid() {
        return admin_id;
    }

    public void setAdminpass(String admin_pass) {
        this.admin_pass = admin_pass;
    }

    public String getAdminpass() {
        return admin_pass;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Admin No: "+admin_no);
        System.out.println("Admin Id: "+admin_id);
        System.out.println("Admin Pass: "+admin_pass);

    }
}
