package de.duckbase.bmt.database;

import de.duckbase.bmt.entity.TaggedEntity;
import de.duckbase.bmt.entity.TaggedLink;

import java.util.Arrays;
import java.util.List;

public class Neo4JTestData {
    public static final String NAME = "neo4j";
    public static final String PASSWORD = "111111";

    public static List<TaggedEntity> getTaggedEntities() {
        TaggedLink taggedHomeLink = new TaggedLink("My Homepage", "www.duckbase.de", true);
        taggedHomeLink.addTag("Homepage");
        taggedHomeLink.addTag("Work");

        TaggedLink taggedUniLink = new TaggedLink("Uni Potsdam", "www.uni-potsdam.de", true);
        taggedUniLink.addTag("Work");
        return Arrays.asList(taggedHomeLink, taggedUniLink);
    }
}
