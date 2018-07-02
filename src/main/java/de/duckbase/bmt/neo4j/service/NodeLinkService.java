package de.duckbase.bmt.neo4j.service;

import de.duckbase.bmt.neo4j.entity.NodeLink;
import org.neo4j.ogm.session.Session;

public class NodeLinkService extends GenericService<NodeLink> {
    public NodeLinkService(Session session) {
        super(session);
    }

    @Override
    Class<NodeLink> getEntityType() {
        return NodeLink.class;
    }
}
