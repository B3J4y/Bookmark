package de.duckbase.bmt.neo4j.service;

import java.util.Optional;

interface Service<T> {

    Iterable<T> findAll();

    T find(Long id);

    Optional<T> findByTitle(String title);

    void delete(Long id);

    T createOrUpdate(T object);

}