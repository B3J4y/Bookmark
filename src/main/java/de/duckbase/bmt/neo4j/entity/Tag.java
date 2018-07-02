package de.duckbase.bmt.neo4j.entity;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Tag extends Entity {
    @Relationship(type="TAGGED_BY", direction =  Relationship.INCOMING)
    Set<NodeLink> nodeLinks;

    public Tag() {
       // nodeLinks = new HashSet<>();
    }

    public Tag(String title) {
        this.title = title;
    }
}
