package org.chaterbox;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connection {
    MongoClient clint = MongoClients.create("mongodb+srv://admin_farhad:9Xalc9Re9fBB2TPU@cluster0.guc3q.mongodb.net/?retryWrites=true&w=majority");

    MongoDatabase db = clint.getDatabase("chater-box");
    MongoCollection col = db.getCollection("sampleCol");
    MongoCollection users = db.getCollection("users");
    MongoCollection chats = db.getCollection("chats");
    MongoCollection tokens = db.getCollection("access-tokens");
}
