import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        userService userService = new userService();
        String fileHistory = "history.json";
        String fileUser = "user.json";
        String fileAdmin = "admin.json";
        userService.startProgram(scanner,fileUser,fileHistory,fileAdmin);
    }
}
