package org.chaterbox;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.io.Console;
import java.util.Scanner;
import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) {
        Connection connection = new Connection();
        Scanner sc = new Scanner(System.in);
        Console cnsl = System.console();

        System.out.println("Welcome to ChaterBox");
        String user = "";
        String pass = "";
        while (user.trim().length() < 3) {
            System.out.print("User: ");
            user = sc.next();
        }
        if (cnsl == null) {
            while (pass.trim().length() < 8) {
                System.out.print("Pass: ");
                pass = sc.next();
            }
        } else {
            while (pass.trim().length() < 8) {
//                System.out.print("Pass: ");
                pass = String.valueOf(cnsl.readPassword("Pass: "));
            }
        }


        UserLogin uli = new UserLogin(user, pass);
        String s = uli.loginKaro();

        while (s == null) {
            System.out.print("Token: ");
            String tok = sc.next();
            UserSignUp usu = new UserSignUp(user, pass, tok);
            s = usu.addUser();
        }

        if (s != null) {
            String option = "VIEW";
            String msgToken = "";

            while (msgToken.trim().length() < 10) {
                System.out.print("Koken: ");
                msgToken = sc.next();

                if (msgToken.trim().length() < 10) {
                    System.out.println("Koken must be at-list length of 10.");
                }
            }

            while (!option.equals("QUIT")) {
                switch (option.toUpperCase()) {
                    case "V":
                    case "VIEW": {
                        FindIterable<Document> iterable = connection.chats.find(eq("koken", msgToken));
                        for (Document doctemp : iterable) {
                            if (doctemp.get("msg").equals("[:bokhate]")) {
                                System.out.println("[ " + doctemp.get("chatof") + " ] " + ": " + "[farhad]");
                            } else {
                                System.out.println("[ " + doctemp.get("chatof") + " ] " + ": " + doctemp.get("msg"));
                            }
                        }
                        break;
                    }
                    case "S":
                    case "SEND": {
                        System.out.print("/> ");
                        sc.nextLine();
                        String chtStr = sc.nextLine();
                        if (chtStr.length() > 0) {
                            AddChat ac = new AddChat(s);
                            ac.addChat(chtStr, msgToken);
                        }
                        break;
                    }
                    default : System.out.println("Error command.");
                }
                System.out.print("\\> ");
                option = sc.next();
            }
        }
    }
}