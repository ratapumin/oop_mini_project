public class Order {
    protected String order_no;
    protected String order_date;

    public Order() {

    }

    public Order(String order_no,
            String order_date) {
        this.order_no = order_no;
        this.order_date = order_date;

    }

    public void setOrderno(String order_no) {
        this.order_no = order_no;
    }

    public String getOrderno() {
        return order_no;
    }

    public void setOrderdate(String order_date) {
        this.order_date = order_date;
    }

    public String getOrderdate() {
        return order_date;
    }

    public void printInfo() {
        System.out.println("Order No: " + order_no);
        System.out.println("Order Date: " + order_date);

    }
}