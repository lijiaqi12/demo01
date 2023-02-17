package priv.jesse.mall.entity.log;



public class ShoppingLogs {
    private String name;//用户名字
    private String phoneNumber;  //用户手机号
    private int productId;  //商品ID
    private String title;  //商品的名字
    private Double shopPrice;  //价钱
    private String times;  //购买的时间

    private String addr;  //地址

    public ShoppingLogs() {
    }

    public ShoppingLogs(String name, String phoneNumber, int productId, String title, Double shopPrice, String times, String addr) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.productId = productId;
        this.title = title;
        this.shopPrice = shopPrice;
        this.times = times;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "ShoppingLogs{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", productId=" + productId +
                ", title='" + title + '\'' +
                ", shopPrice=" + shopPrice +
                ", times='" + times + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
