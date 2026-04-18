package adapter;
import domain.EntityInterface;
import java.util.ArrayList;
import java.util.UUID;

public interface PersistInterface {
    void save(EntityInterface entity);
    void  delete(EntityInterface entity);
    ArrayList<EntityInterface> listAll();
    EntityInterface findOneById(UUID id);
}
