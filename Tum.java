public class Tum extends Product {
    public String t_name;
    public double t_price;

    public Tum() {

    }

    public Tum(String t_name,
            double t_price) {
        this.t_name = t_name;
        this.t_price = t_price;
    }

    public Tum(int p_no,
            int p_stock,
            String p_status,
            String p_type, String t_name,
            double t_price) {
        super(p_no, p_stock, p_status, p_type);
        this.t_name = t_name;
        this.t_price = t_price;
    }

    public void setTname(String t_name) {
        this.t_name = t_name;
    }

    public String getTname() {
        return t_name;
    }

    public void setTprice(double t_price) {
        this.t_price = t_price;
    }

    public double getTprice() {
        return t_price;
    }

    public void printInfo() {
        System.out.println("================================================================");
        super.printInfo();
        System.out.println("Tum Name : " + t_name);
        System.out.println("Tum Price : " + t_price);

    }
}
