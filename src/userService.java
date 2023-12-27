import java.time.LocalDate;
import java.util.*;

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
        try {
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
                        banking(scanner, fileUser);
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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
@Override
    public void userLogin(Scanner scanner,String fileUser){
    try {
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
                    return;
                }
            }
            System.out.println("Tài khoản hoặc mật khẩu không chính xác");
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }

}

    @Override
    public void userForgotPassword(Scanner scanner, String fileUser) {
    }

    @Override
    void checkBalance(Scanner scanner, User user, String fileUser) {
        System.out.println("Số tài khoản của bạn là : "+"\t"+user.getAccountNumber());
        System.out.println("Số dư tài khoản của bạn là : "+user.getBalance());
    }

    @Override
    void banking(Scanner scanner, String fileUser) {
        try {
            System.out.println("---------Chuyển tiền---------");
            System.out.println("Nhập số tài khoản thụ hưởng : ");
            String toAccount = scanner.nextLine();
            System.out.println("Nhập số tiền muốn gửi : ");
            double money = scanner.nextDouble();
            System.out.println("Nội dung chuyển khoản : ");
            String note = scanner.nextLine();
            List<User> users = getListObjectFromJsonFile(fileUser);
            Optional<List<User>> usersOptional = Optional.ofNullable(users);
            if (usersOptional.isPresent()) {
                for (User user : users) {
                    if (user.getAccountNumber().equals(toAccount)) {
                        if (50000 < money && money < user.getBalance() - 50000) {
                            user.setBalance(user.getBalance() - money);
                            users.add(user);
                            convertObjectToJsonFile("user.json", users);
                            System.out.println("Chuyển tiền thành công " + "\n" + "Thời gian giao dịch là : " + LocalDate.now() + "\n" + note);
                            break;
                        } else {
                            System.out.println("Số tiền bạn nhập sai.Lưu ý : số tiền phải trên 50000 và ít hơn số dư tài khoản của bạn trừ đi 50000");
                            break;
                        }
                    } else {
                        System.out.println("Số tài khoản thụ hưởng không đúng");
                        break;
                    }
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
    }
    }

    @Override
    void checkHistory(Scanner scanner, User user, String fileUser) {

    }
}
