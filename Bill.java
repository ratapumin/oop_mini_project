public class Bill extends Order {

  public int bill_no;
  public double vat;
  public double total;
  public double net;

  public Bill() {
  }

  public Bill(int bill_no, double bill_vat) {
    this.bill_no = bill_no;
    this.vat = vat;
  }

  public Bill(String order_no,
      String order_date, int bill_no, double vat) {
    super(order_no, order_date);
    this.bill_no = bill_no;
    this.vat = vat;
  }

  public void setBillno(int bill_no) {
    this.bill_no = bill_no;
  }

  public int getBillno() {
    return bill_no;
  }

  public void setVat(double vat) {
    this.vat = vat;
  }

  public double getVat() {
    return vat;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public double getTotal() {
    return total;
  }

  public void setNet(double net) {
    this.net = net;
  }

  public double getNet() {
    return net;
  }

  public void callvat_total(Bill bill) {
    net = bill.getTotal() - bill.getVat();
    bill.setNet(net);
  }

  public void callVat(Bill bill) {
    vat = bill.getTotal() * 7 / 100;
    bill.setVat(vat);
  }

  public void printInfo() {
    super.printInfo();
    System.out.println("Order vat : " + vat);
    System.out.println("Order total : " + total);
    System.out.println("Ornet Net: " + net);

  }
}
