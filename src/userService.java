import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class userService extends userManager implements userLogin,userForgotPassword {
    void startProgram(Scanner scanner, String fileName) {
        try {
            while (true) {
                System.out.println("-----------------MENU-----------------");
                System.out.println("1. Dang nhap");
                System.out.println("2. Quen mat khau");
                System.out.println("3. Thoat");
                int optionMenu = checkIntNumber(scanner);
                scanner.nextLine();
                switch (optionMenu) {
                    case 1:
                        userLogin(scanner, fileName);
                        break;
                    case 2:
                        userForgotPassword(scanner, fileName);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Khong co chuc nang nay!");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void loginSuccess(Scanner scanner, String fileUser, User user){
        while (true) {
            System.out.println("-----------------MENU-----------------");
            System.out.println("1. Truy vấn số dư tài khoản");
            System.out.println("2. Chuyển tiền");
            System.out.println("3. Xem lịch sử giao dịch");
            System.out.println("4. Thoát");
            int optionMenu = checkIntNumber(scanner);
            scanner.nextLine();
            switch (optionMenu) {
                case 1:
                    checkBalance(scanner, user, fileUser);
                    break;
                case 2:
                    banking(scanner, user, fileUser);
                    break;
                case 3:
                    checkHistory(scanner, user,fileUser);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("không có chức năng này xin mời nhập lại");
                    break;
            }
        }
    }
@Override
    public void userLogin(Scanner scanner,String fileUser){
    System.out.println("----------Đăng nhập----------");
    System.out.println("Nhập tài khoàn của bạn : ");
    String accountName = scanner.nextLine();
    System.out.println("Nhập mật khẩu của bạn : ");
    String accountPassword = scanner.nextLine();
    List<User> users = getListObjectFromJsonFile(fileUser);
    Optional<List<User>> usersOptional = Optional.ofNullable(users);
    if (usersOptional.isPresent()){
        for (User user : users){
            if (user.getAccount().equals(accountName)&&user.getPassword().equals(accountPassword)){
                loginSuccess(scanner,fileUser,user);
            }
            else{
                System.out.println("Tài khoản hoặc mật khẩu không chính xác.");
            }
        }
    }

}

    @Override
    public void userForgotPassword(Scanner scanner, String fileUser) {
    }

    @Override
    void checkBalance(Scanner scanner, User user, String fileUser) {
        System.out.println("Số dư tài khoản của bạn là : "+user.getBalance());
    }

    @Override
    void banking(Scanner scanner, User user, String fileUser) {

    }

    @Override
    void checkHistory(Scanner scanner, User user, String fileUser) {

    }
}
