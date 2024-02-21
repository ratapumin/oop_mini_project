public class Product {
    protected int p_no;
    protected int p_stock;
    protected String p_status;
    protected String p_type;

    public Product() {

    }

    public Product(int p_no,
            int p_stock,
            String p_status,
            String p_type) {
        this.p_no = p_no;
        this.p_stock = p_stock;
        this.p_status = p_status;
        this.p_type = p_type;

    }

    public void setPno(int p_no) {
        this.p_no = p_no;
    }

    public int getPno() {
        return p_no;
    }

    public void setPstock(int p_stock) {
        this.p_stock = p_stock;
    }

    public int getPstock() {
        return p_stock;
    }

    public void setPstatus(String p_status) {
        this.p_status = p_status;
    }

    public String getPstatus() {
        return p_status;
    }

    public void setPtype(String p_type) {
        this.p_type = p_type;
    }

    public String getPtype() {
        return p_type;
    }

    public void printInfo() {
        System.out.println("Procuct No : " + p_no);
        System.out.println("Product Stock : " + p_stock);
        System.out.println("Product Status : " + p_status);
        System.out.println("Product type : " + p_type);

    }

}
