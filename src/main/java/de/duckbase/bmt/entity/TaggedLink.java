package de.duckbase.bmt.entity;

import java.util.ArrayList;
import java.util.List;

public class TaggedLink {

    private String title;
    private String url;
    private List<String> tags;

    public TaggedLink(String title, String url) {
        this.title = title;
        this.url = url;
        tags = new ArrayList<>();
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

    public String getUrl() {
        return url;
    }
}
