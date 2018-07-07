package de.duckbase.bmt.database;

import de.duckbase.bmt.entity.TaggedEntity;

import java.util.List;

public interface TagDatabase {
    void createEntities(List<TaggedEntity> taggedLinks);

    void close();

    List<String> queryTags();

    void clear();
}
