package de.duckbase.bmt.database;

import de.duckbase.bmt.entity.TaggedLink;

import java.util.List;

public interface TagDatabase {
    void createEntities(List<TaggedLink> taggedLinks);

    void addTag(String tag);

    void addLink(String title, String url);

    void close();

    List<String> queryTags();

    void clear();
}
