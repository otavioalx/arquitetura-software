package domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Price implements EntityInterface{
    private UUID uuid;
    private BigDecimal price;
    private Date date;


    public Price() {
    }

    public Price(BigDecimal price, Date date) {
        this.price = price;
        this.date = date;
    }

    public Price(UUID uuid, BigDecimal price, Date date) {
        this.uuid = uuid;
        this.price = price;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", date=" + date +
                '}';
    }
}
