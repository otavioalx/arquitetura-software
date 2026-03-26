package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Product implements EntityInterface{
    private UUID uuid;

    private String sku;
    private String name;
    private BigDecimal price;
    private Date datePrice;
    private ArrayList<Price> historicalPrice = new ArrayList<>();

    public Product() {
    }

    public Product(String sku, String name, BigDecimal price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }


    public Product(UUID uuid, String sku, String name, BigDecimal price) {
        this.uuid = uuid;
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (this.price != null && this.datePrice != null){
            Price oldPrice = new Price(this.price, this.datePrice);
            historicalPrice.add(oldPrice);
        }

        this.price = price;
        this.datePrice = new Date();


    }

    public Date getDatePrice() {
        return datePrice;
    }

    public void setDatePrice(Date datePrice) {
        this.datePrice = datePrice;
    }

    public ArrayList<Price> getHistoricalPrice() {
        return historicalPrice;
    }

    public void setHistoricalPrice(ArrayList<Price> historicalPrice) {
        this.historicalPrice = historicalPrice;
    }


    @Override
    public String toString() {
        return "Product{" +
                "Sku=" + sku +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", datePrice=" + datePrice +
                ", historicalPrice=" + historicalPrice +
                '}';
    }
}
