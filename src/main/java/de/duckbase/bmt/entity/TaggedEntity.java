package de.duckbase.bmt.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class TaggedEntity {
    private String title;
    private final boolean isTestObject;
    private List<String> tags;

    public TaggedEntity(String title, boolean isTestObject) {
        this.title = title;
        this.isTestObject = isTestObject;
        tags = new ArrayList<>();
    }

    public boolean isTestObject() {
        return isTestObject;
    }


    public void addTag(String tag) {
        tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }
}
