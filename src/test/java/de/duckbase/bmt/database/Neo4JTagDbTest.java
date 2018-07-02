package de.duckbase.bmt.database;

import de.duckbase.bmt.entity.TaggedLink;
import de.duckbase.bmt.neo4j.entity.NodeLink;
import de.duckbase.bmt.neo4j.entity.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Neo4JTagDbTest {
    private Neo4JTagDb ntdb;
    private static final String NAME = "neo4j";
    private static final String PASSWORD = "111111";



    @Before
    public void setUp() throws Exception {
        ntdb = new Neo4JTagDb(NAME, PASSWORD);
    }

    @After
    public void tearDown() throws Exception {
        ntdb.clear();
        ntdb.close();
    }

    @Test
    public void testAddTaggedLink() {
        TaggedLink taggedHomeLink = new TaggedLink("My Homepage", "www.duckbase.de");
        taggedHomeLink.addTag("Homepage");
        taggedHomeLink.addTag("Work");

        TaggedLink taggedUniLink = new TaggedLink("Uni Potsdam", "www.uni-potsdam.de");
        taggedUniLink.addTag("Work");
        ntdb.createEntities(Arrays.asList(taggedHomeLink, taggedUniLink));

        List<NodeLink> links = ntdb.getLinks();
        assertEquals("Anzahl der Links in der Datenbank sind nicht exakt", 2, links.size());
        List<Tag> tags = ntdb.getTags();
        assertEquals("Anzahl der Tags in der Datenbank sind nicht exakt", 2, tags.size());
    }
}