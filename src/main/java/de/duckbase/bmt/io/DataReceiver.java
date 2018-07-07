package de.duckbase.bmt.io;

import de.duckbase.bmt.entity.TaggedEntity;

import java.util.List;

public interface DataReceiver {
    List<TaggedEntity> getLinks();
}
