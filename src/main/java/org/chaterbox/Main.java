package org.chaterbox;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
//import org.apache.log4j.BasicConfigurator;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.Console;
import java.util.Iterator;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    //Java console color constants
    public static final String TEXT_RED = "";
    public static final String TEXT_BLACK = "";
    public static final String TEXT_GREEN = "";
    public static final String TEXT_BLUE = "";
    public static final String TEXT_RESET = "";
    public static final String TEXT_PURPLE = "";
    public static final String TEXT_CYAN = "";
    public static final String TEXT_YELLOW = "";
    public static final String TEXT_WHITE = "";
    public static void main(String[] args) {
//        System.out.println("\u001B[40m");
//        Document sampleDoc = new Document().append("name", "js").append("chat", "55");
//        connection.col.insertOne(sampleDoc);

//        UserSignUp usu = new UserSignUp("sdf", "sdf", "1234563");
//        usu.addUser();

//        UserLogin uli = new UserLogin("sdf", "sdf");
//        String s = uli.loginKaro();
//        System.out.println(s);
//
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//
//        if (s != null) {
//            AddChat sendChatAs = new AddChat(s);
//
//            sendChatAs.addChat("from sdf hi 3");
//        }
//        BasicConfigurator.configure();
        Connection connection = new Connection();
        Scanner sc = new Scanner(System.in);
//        Console console = System.console();

        System.out.println("Welcome to ChaterBox");

        System.out.print("User: " + TEXT_RED);
        String user = sc.next();
        System.out.print(TEXT_RESET + "Pass: " + TEXT_GREEN);
        String pass = sc.next();
        System.out.print(TEXT_RESET);
//        char[] passwordChars = console.readPassword();
//        String pass= new String(passwordChars);


        UserLogin uli = new UserLogin(user, pass);
        String s = uli.loginKaro();
//        System.out.println(s);

        while (s == null) {
            System.out.print("Token: ");
            String tok = sc.next();
            UserSignUp usu = new UserSignUp(user, pass, tok);
            s = usu.addUser();

        }
//        System.out.println(s);

        if (s != null) {
            String option = "VIEW";
            String msgToken = "";

            while (msgToken.length() < 10) {
                System.out.print("Koken: ");
                msgToken = sc.next();

                if (msgToken.length() < 10) {
                    System.out.println("Koken must be at-list length of 10.");
                }
            }

            while (!option.equals("QUIT")) {
                switch (option.toUpperCase()) {
                    case "VIEW" -> {
//                        System.out.print("\033[H\033[2J");
//                        System.out.flush();

                        FindIterable<Document> iterable = connection.chats.find(eq("koken", msgToken));
                        for (Document doctemp : iterable) {
                            if (doctemp.get("msg").equals("[:bokhate]")){
                                System.out.println(TEXT_BLUE + "[ " + TEXT_RED + doctemp.get("chatof") + TEXT_RESET
                                        + TEXT_BLUE + " ] " + TEXT_GREEN + ": " + TEXT_YELLOW + "[farhad]" + TEXT_RESET);
                            } else {
                                System.out.println(TEXT_BLUE + "[ " + TEXT_RED + doctemp.get("chatof") + TEXT_RESET
                                    + TEXT_BLUE + " ] " + TEXT_GREEN + ": " + TEXT_YELLOW + doctemp.get("msg") + TEXT_RESET);
                            }

                        }
                    }
                    case "SEND" -> {
                        System.out.print(TEXT_PURPLE + "/> " + TEXT_RESET);
                        sc.nextLine();
                        String chtStr = sc.nextLine();
                        if (chtStr.length() > 0) {
                            AddChat ac = new AddChat(s);
                            ac.addChat(chtStr, msgToken);
                        }
                    }
                    default -> System.out.println("Error command.");
                }
                System.out.print("\\> ");
                option = sc.next();
            }
        }

    }
}