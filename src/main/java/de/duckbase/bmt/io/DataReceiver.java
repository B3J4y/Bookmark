package de.duckbase.bmt.io;

import de.duckbase.bmt.entity.TaggedLink;

import java.util.List;

public interface DataReceiver {
    List<TaggedLink> getLinks();
}
