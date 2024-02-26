public class Employee extends Info {
    private String emp_no;
    public double calproduct;

    public Employee() {
    }

    public Employee(String emp_no) {
        this.emp_no = emp_no;

    }

    public Employee(String name,
            String pnumber,
            String emp_no) {
        super(name, pnumber);
        this.emp_no = emp_no;

    }

    public void setEmpno(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmpno() {
        return emp_no;
    }

    public void setCalproduct(double calproduct) {
        this.calproduct = calproduct;
    }

    public double getCalproduct() {
        return calproduct;
    }

    public double cal


}
