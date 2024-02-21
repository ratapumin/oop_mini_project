public class Yum extends Product {

    public String y_name;
    public double y_price;

    public Yum() {

    }

    public Yum(String y_name,
            double y_price) {
        this.y_name = y_name;
        this.y_price = y_price;
    }

    public Yum(int p_no,
            int p_stock,
            String p_status,
            String p_type, String y_name,
            double y_price) {
        super(p_no, p_stock, p_status, p_type);
        this.y_name = y_name;
        this.y_price = y_price;
    }

    public void setYname(String y_name) {
        this.y_name = y_name;
    }

    public String getYname() {
        return y_name;
    }

    public void setYprice(double y_price) {
        this.y_price = y_price;
    }

    public double getYprice() {
        return y_price;
    }

    public void printInfo() {
        System.out.println("================================================================");
        super.printInfo();
        System.out.println("Tum Name : " + y_name);
        System.out.println("Tum Price : " + y_price);

    }

}
