package Repository;

import Domain.Entity;
import Domain.IValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<T extends Entity > implements IRepository<T> {
    private Map <String, T> storage = new HashMap <>( );
    private IValidator <T> validator;
    public InMemoryRepository(IValidator<T> validator){
        this.validator = validator;
    }

    @Override
    public T getById(String id) {
        return storage.get(id);
    }

    @Override
    public void insert(T entity) {
        if (storage.containsKey( entity.getId())){
            throw new RepositoryException("An entity with" + entity.getId()+ "already exists.");
        }
        validator.validate( entity );
        storage.put( entity.getId(), entity );

    }

    @Override
    public void update(T entity) {
        if (storage.containsKey( entity.getId() )){
            throw new RepositoryException( String.format( "An entity with %s does not exists", entity.getId() ) );
        }
        validator.validate( entity);
        storage.put(entity.getId(), entity);

    }

    @Override
    public void remove(String id) {
        if (!storage.containsKey( id )){
            throw new RepositoryException( String.format( "An entity with %s does not exists", id ) );
        }
        storage.remove( id );
    }

    @Override
    public List < T > getAll() {
        return new ArrayList <>( storage.values() );
    }
}
