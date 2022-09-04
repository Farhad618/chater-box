package org.chaterbox;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class UserSignUp {
    private Connection connection = new Connection();
    private String userName;
    private String password;
    private String token;


    UserSignUp (String userName, String password, String token) {
        this.userName = userName;
        this.password = password;
        this.token = token;
    }

    String addUser() {
        DBObject query = new BasicDBObject("token", token);
//        connection.tokens.find((Bson) query);
//        if (){
//        System.out.println("d1");
//
//        }
        Bson projectionFields = Projections.fields(
                Projections.include("token", "users"),
                Projections.excludeId());
        Document doc = (Document) connection.tokens.find(eq("token", token))
                .projection(projectionFields)
                .first();
        if (doc == null) {
            System.out.println("Enter a valid token.");
        } else {
            Document doc1 = (Document) connection.users.find(eq("user-name", userName))
                    .projection(projectionFields)
                    .first();
            if (doc1 == null) {
                try {
                    InsertOneResult result = connection.users.insertOne(new Document()
                            .append("_id", new ObjectId())
                            .append("user-name", userName)
                            .append("password", password));
                    System.out.println("Success!");
                } catch (MongoException me) {
                    System.err.println("Unable to insert due to an error: " + me);
                }
                    return userName;
            } else {
                System.out.println("User already exist.");
                return null;
            }
        }
        return null;

    }

}
