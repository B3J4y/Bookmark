package de.duckbase.bmt.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

public class Entity {
    @Id
    @GeneratedValue
    private Long id;
    String title;
    boolean isTestObject;

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTestObject() {
        return isTestObject;
    }

    public void setAsTestObject(boolean isTestObject) {
        this.isTestObject = isTestObject;
    }
}
