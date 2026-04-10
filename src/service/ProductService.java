package service;

import adapter.DatabaseStorage;
import adapter.PersistInterface;
import domain.EntityInterface;

import java.util.ArrayList;
import java.util.UUID;

public class ProductService implements ServiceInterface{
    PersistInterface armazenamento = new DatabaseStorage();

    @Override
    public void create(EntityInterface entity) {
        this.armazenamento.save(entity);
    }




    @Override
    public void delete(EntityInterface entity) {
        this.armazenamento.delete(entity);
    }




    @Override
    public void listAll() {
        ArrayList<EntityInterface> dados = armazenamento.listAll();
        for(int i = 0; i <dados.size();i++ ){
            IO.println(dados.get(i));
        }
    }




    @Override
    public EntityInterface getById(UUID id) {
        return armazenamento.findOneById(id);
    }




    public UUID generateUUID(){
        return UUID.randomUUID();
    }

}
