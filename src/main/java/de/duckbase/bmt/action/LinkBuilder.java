package de.duckbase.bmt.action;

import de.duckbase.bmt.database.TagDatabase;
import de.duckbase.bmt.io.DataReceiver;

public class LinkBuilder {
    private TagDatabase database;
    private DataReceiver receiver;

    public LinkBuilder(TagDatabase database, DataReceiver receiver) {
        this.database = database;
        this.receiver = receiver;
    }

    public void saveInDB() {
        database.createEntities(receiver.getLinks());
    }
}
