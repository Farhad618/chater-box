package org.chaterbox;

import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;

public class UserLogin {
    private final Connection connection = new Connection();
    private final String userName;
    private final String password;

    UserLogin (String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    String loginKaro() {
        Bson projectionFields = Projections.fields(
                Projections.include("user-name", "password"),
                Projections.excludeId());

        Document doc2 = connection.users.find(eq("user-name", userName))
                .projection(projectionFields)
                .first();

        if (doc2 == null || !(doc2.get("password").equals(password))) {
            System.out.println("User name or password does not matched.");
        } else {
            return userName;
        }
        return null;
    }
}
