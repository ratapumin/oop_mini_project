public class Calorder {

    public static void main(String[] args) {

        Order order = new Order();
        order.setOrderno("Order No : 022");
        order.setOrderdate("20-12-2024");
        order.setTotal(100);
        order.callVat(order);
        order.callvat_total(order);
        order.getVat();

        order.printInfo();
        System.out.println("net " + order.getNet());

    }
}
