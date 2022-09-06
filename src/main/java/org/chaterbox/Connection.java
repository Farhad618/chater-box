package org.chaterbox;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Connection {
    MongoClient clint = MongoClients.create("mongodb+srv://admin_farhad:9Xalc9Re9fBB2TPU@cluster0.guc3q.mongodb.net/?retryWrites=true&w=majority");

    MongoDatabase db = clint.getDatabase("chater-box");
//    MongoCollection<Document> col = db.getCollection("sampleCol");
    MongoCollection<Document> users = db.getCollection("users");
    MongoCollection<Document> chats = db.getCollection("chats");
    MongoCollection<Document> tokens = db.getCollection("access-tokens");
}
