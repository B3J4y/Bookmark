package de.duckbase.bmt.database;

import de.duckbase.bmt.neo4j.entity.Entity;
import de.duckbase.bmt.neo4j.entity.NodeLink;
import de.duckbase.bmt.neo4j.entity.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Neo4JTagDbTest {
    private Neo4JTagDb ntdb;

    @Before
    public void setUp() throws Exception {
        ntdb = new Neo4JTagDb(Neo4JTestData.NAME, Neo4JTestData.PASSWORD);
    }

    @After
    public void tearDown() throws Exception {
        ntdb.clearTestObjects();
        ntdb.close();
    }

    @Test
    public void testAddTaggedLink() {
        ntdb.createEntities(Neo4JTestData.getTaggedEntities());
        List<NodeLink> links = ntdb.getLinks();
        assertEquals("Anzahl der Links in der Datenbank sind nicht exakt", 2, links.stream()
                .filter(Entity::isTestObject)
                .count());
        List<Tag> tags = ntdb.getTags();
        assertEquals("Anzahl der Tags in der Datenbank sind nicht exakt", 2, tags.stream()
                .filter(Entity::isTestObject)
                .count());
    }
}