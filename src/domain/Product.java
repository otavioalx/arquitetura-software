package domain;
import java.lang.Float;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
//CONFIRMAR SE TA CERTO ESSA MUDANÇA

public class Product implements EntityInterface{
    private UUID uuid;
    private String sku;
    private String name;
    private Float price;
    private Date datePrice;
    private ArrayList<Price> historicalPrice = new ArrayList<>();

    public Product() {
    }

    public Product(String sku, String name, Float price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public Product(UUID uuid, String sku, String name, Float price) {
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
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "Product{" +

                "UUID='"+ uuid.toString() + '\''+
                "Sku=" + sku +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", datePrice=" + datePrice +
                ", historicalPrice=" + historicalPrice +
                '}';
    }
}
