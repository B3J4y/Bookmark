package de.duckbase.bmt.database;

import de.duckbase.bmt.entity.TaggedEntity;
import de.duckbase.bmt.entity.TaggedLink;
import de.duckbase.bmt.neo4j.entity.Entity;
import de.duckbase.bmt.neo4j.entity.NodeLink;
import de.duckbase.bmt.neo4j.entity.Tag;
import de.duckbase.bmt.neo4j.service.NodeLinkService;
import de.duckbase.bmt.neo4j.service.TagService;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Neo4jSession;
import org.neo4j.ogm.session.SessionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class Neo4JTagDb implements TagDatabase {
    private NodeLinkService nls;
    private TagService ts;
    private SessionFactory factory;

    public Neo4JTagDb(String name, String password) {
        Configuration configuration = new Configuration.Builder()
                .uri("bolt://localhost:7687")
                .credentials(name, password)
                .build();
        factory = new SessionFactory(configuration, "de.duckbase.bmt.neo4j.entity");

        Neo4jSession session = (Neo4jSession) factory.openSession();
        nls = new NodeLinkService(session);
        ts = new TagService(session);
    }

    public static void main(String[] args) {
        Neo4JTagDb ntdb = new Neo4JTagDb(args[0], args[1]);

        TaggedLink taggedHomeLink = new TaggedLink("My Homepage", "www.duckbase.de", false);
        taggedHomeLink.addTag("Homepage");
        taggedHomeLink.addTag("Work");

        TaggedLink taggedUniLink = new TaggedLink("Uni Potsdam", "www.uni-potsdam.de", false);
        taggedUniLink.addTag("Work");
        ntdb.createEntities(Arrays.asList(taggedHomeLink, taggedUniLink));
        ntdb.close();
    }

    @Override
    public void createEntities(List<TaggedEntity> taggedLinks) {
        for (TaggedEntity taggedEntity : taggedLinks) {
            NodeLink nodeLink = nls.findByTitle(taggedEntity.getTitle())
                    .filter(node -> node.isTestObject() == taggedEntity.isTestObject())
                    .orElse(new NodeLink(taggedEntity.getTitle()));
            nodeLink.setTitle(taggedEntity.getTitle());
            nodeLink.setAsTestObject(taggedEntity.isTestObject());
            for (String tagTitle : taggedEntity.getTags()) {
                Tag tag = ts.findByTitle(tagTitle)
                        .filter(t -> t.isTestObject() == taggedEntity.isTestObject())
                        .orElse(new Tag(tagTitle));
                tag.setAsTestObject(nodeLink.isTestObject());
                ts.createOrUpdate(tag);
                nodeLink.addTag(tag);
            }
            nls.createOrUpdate(nodeLink);
        }
    }

    @Override
    public void close() {
        factory.close();
    }

    @Override
    public List<String> queryTags() {
        return null;
    }

    public List<NodeLink> getLinks() {
        List<NodeLink> nodeLinks = new ArrayList<>();
        nls.findAll().forEach(nodeLinks::add);
        return nodeLinks;
    }

    public List<Tag> getTags() {
        List<Tag> tagLinks = new ArrayList<>();
        ts.findAll().forEach(tagLinks::add);
        return tagLinks;
    }

    @Override
    public void clear() {
        nls.findAll().forEach(node -> nls.delete(node.getId()));
        ts.findAll().forEach(tag -> ts.delete(tag.getId()));
    }

    public void clearTestObjects() {
        StreamSupport.stream(nls.findAll().spliterator(), false)
                .filter(Entity::isTestObject)
                .forEach(node -> nls.delete(node.getId()));
        StreamSupport.stream(ts.findAll().spliterator(), false)
                .filter(Entity::isTestObject)
                .forEach(tag -> ts.delete(tag.getId()));

    }
}
