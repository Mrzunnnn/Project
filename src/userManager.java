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
    abstract void water(Scanner scanner, User user, String fileUser);
    abstract void internet(Scanner scanner, User user, String fileUser);
    abstract void electric(Scanner scanner, User user, String fileUser);
    abstract  void RechargePhone(Scanner scanner,User user,String fileUser);
    abstract void invoicing(Scanner scanner,User user, String fileUser);
    abstract void checkBalance(Scanner scanner, User user, String fileUser);
    abstract void banking(Scanner scanner,User user ,String fileUser);
    abstract void checkHistory(Scanner scanner, User user, String fileUser);
    public static void convertObjectToJsonFile(String fileName, List<User> users) {
        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Writer writer = Files.newBufferedWriter(Paths.get(fileName));
            gson.toJson(users, writer);
            Gson historyGson = new Gson();
            Object historyData;
            String historyJson = historyGson.toJson(historyData);
            writer.write(historyJson);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<User> getListObjectFromJsonFile(String fileUser) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(fileUser));
            User[] userLstGson = gson.fromJson(reader, User[].class);

            if (userLstGson == null) {
                return Collections.emptyList();
            } else {
                List<User> users = Arrays.asList(userLstGson);
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

    public boolean isValidVietnamesePhoneNumber(String phoneNumber) {
        String regex = "^0[0-9]{9}$";
        return phoneNumber.matches(regex);
    }
}
