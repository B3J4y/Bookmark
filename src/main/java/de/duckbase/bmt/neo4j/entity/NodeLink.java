package de.duckbase.bmt.neo4j.entity;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class NodeLink extends Entity {


    @Relationship(type="TAGGED_BY")
    Set<Tag> tags;

    public NodeLink() {
        tags = new HashSet<>();
    }

    public NodeLink(String title) {
        this.title = title;
        tags = new HashSet<>();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }
}
