package de.duckbase.bmt.neo4j.service;

import de.duckbase.bmt.neo4j.entity.Tag;
import org.neo4j.ogm.session.Session;

public class TagService extends GenericService<Tag> {
    public TagService(Session session) {
        super(session);
    }

    @Override
    Class<Tag> getEntityType() {
        return Tag.class;
    }
}
