package org.chaterbox;

import com.mongodb.MongoException;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.management.QueryExp;

import static com.mongodb.client.model.Filters.eq;
import static javax.management.Query.and;

public class UserLogin {
    private Connection connection = new Connection();
    private String userName;
    private String password;

    UserLogin (String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    String loginKaro() {
        Bson projectionFields = Projections.fields(
                Projections.include("user-name", "password"),
                Projections.excludeId());

        Document doc2 = (Document) connection.users.find(eq("user-name", userName))
                .projection(projectionFields)
                .first();

        // System.out.println(doc2.get("password").equals(password));

        if (doc2 == null || !(doc2.get("password").equals(password))) {
            System.out.println("User name or password does not matched.");
        } else {
            return userName;
        }
        return null;
    }
}
