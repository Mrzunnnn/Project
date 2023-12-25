import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;


public abstract class userManager {
    abstract void checkBalance(Scanner scanner, User user, String fileUser);
    abstract void banking(Scanner scanner, User user, String fileUser);
    abstract void checkHistory(Scanner scanner, User user, String fileUser);
    public static void convertObjectToJsonFile(String s, List<User> users) {
        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Writer writer = Files.newBufferedWriter(Paths.get(s));

            gson.toJson(users, writer);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<User> getListObjectFromJsonFile(String fileUser) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(fileUser));
            if (gson.fromJson(reader, User[].class) == null) {
                return Collections.emptyList();
            } else {
                List<User> users = Arrays.asList(gson.fromJson(reader, User[].class));
                reader.close();
                return users;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    int checkIntNumber(Scanner scn) {
        while (!scn.hasNextInt()) {
            System.out.println("Ban can nhap vao 1 so nguyen!");
            scn.next();
        }
        return scn.nextInt();
    }
}
