package de.duckbase.bmt.action;

import de.duckbase.bmt.database.Neo4JTagDb;
import de.duckbase.bmt.database.Neo4JTestData;
import de.duckbase.bmt.io.DataReceiver;
import de.duckbase.bmt.neo4j.entity.Entity;
import de.duckbase.bmt.neo4j.entity.NodeLink;
import de.duckbase.bmt.neo4j.entity.Tag;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class LinkBuilderTest {
    @Test
    public void testTagdb() {
        Neo4JTagDb neoDb = new Neo4JTagDb(Neo4JTestData.NAME, Neo4JTestData.PASSWORD);
        DataReceiver receiver = Neo4JTestData::getTaggedEntities;
        LinkBuilder linkBuilder = new LinkBuilder(neoDb, receiver);
        linkBuilder.saveInDB();
        List<NodeLink> links = neoDb.getLinks();
        try {

            assertEquals("Anzahl der Links in der Datenbank sind nicht exakt", 2, links.stream()
                    .filter(Entity::isTestObject)
                    .count());
            List<Tag> tags = neoDb.getTags();
            assertEquals("Anzahl der Tags in der Datenbank sind nicht exakt", 2, tags.stream()
                    .filter(Entity::isTestObject)
                    .count());
        } finally {
            neoDb.clearTestObjects();
            neoDb.close();
        }
    }
}