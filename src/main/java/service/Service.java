package service;

import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

//To be tested (search for the possibility for testing the whole package)

public interface Service<Entity> {
    Optional<Entity> get(long id);
    List<Entity> getAll();
    boolean save(Entity entity) throws SystemException;
    boolean update(Entity entity) throws SystemException;
    boolean delete(Entity entity) throws SystemException;
}
