package de.duckbase.bmt.entity;

public class TaggedLink extends TaggedEntity {
    private String url;

    public TaggedLink(String title, String url, boolean isTestObject) {
        super(title, isTestObject);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
