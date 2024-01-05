import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        users.add(new User("Dung01","dung2503","123456789",5000000,"0969298263"));
        users.add(new User("hello","abc12345","88884444",8000000,"0324685123"));
        users.add(new User("techmaster","aaaaa111","66668888",500000000,"0864212365"));
        userManager.convertObjectToJsonFile("user.json", users);
        userService userService = new userService();
        String History = "History.json";
        String fileUser = "user.json";
        userService.startProgram(scanner,fileUser);
    }
}
