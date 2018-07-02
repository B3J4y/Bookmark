package de.duckbase.bmt.neo4j.service;


import de.duckbase.bmt.neo4j.entity.Entity;
import org.neo4j.ogm.session.Session;

import java.util.Optional;

public abstract class GenericService<T extends Entity> implements Service<T> {

    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;
    private Session session;

    protected GenericService(Session session) {
        this.session = session;
    }

    @Override
    public Iterable<T> findAll() {
        return session.loadAll(getEntityType(), DEPTH_LIST);
    }

    @Override
    public T find(Long id) {
        return session.load(getEntityType(), id, DEPTH_ENTITY);
    }

    @Override
    public Optional<T> findByTitle(String title) {
        for (T entitiy : findAll()) {
            if (entitiy.getTitle().equals(title)) {
                return Optional.of(entitiy);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        session.delete(session.load(getEntityType(), id));
    }

    @Override
    public T createOrUpdate(T entity) {
        session.save(entity, DEPTH_ENTITY);
        return find(entity.getId());
    }

    abstract Class<T> getEntityType();
}
