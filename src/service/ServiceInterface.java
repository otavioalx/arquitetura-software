package service;
import domain.EntityInterface;
import java.util.UUID;

public interface ServiceInterface {
    void create(EntityInterface entity);
    void delete (EntityInterface entity);
    void listAll();
    EntityInterface getById(UUID id);




}
