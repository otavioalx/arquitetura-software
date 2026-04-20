package domain;
import java.lang.Float;
import java.util.Date;
import java.util.UUID;
//CONFIRMAR SE TA CERTO ESSA MUDANÇA

public class Price implements EntityInterface{
    private UUID uuid;
    private Float price;
    private Date date;

    public Price() {
    }

    public Price(Float price, Date date) {
        this.price = price;
        this.date = date;
    }

    public Price(UUID uuid, Float price, Date date) {
        this.uuid = uuid;
        this.price = price;
        this.date = date;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", date=" + date +
                '}';
    }
}
