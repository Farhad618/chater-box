package org.chaterbox;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;

public class AddChat {
    private final Connection connection = new Connection();
    private final String userName;

    AddChat (String userName) {
        this.userName = userName;
    }

    void addChat (String cht, String msgToken) {
        try {
            InsertOneResult result = connection.chats.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("chatof", userName)
                    .append("msg", cht)
                    .append("koken", msgToken));
            System.out.println("Success!");
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }
}
