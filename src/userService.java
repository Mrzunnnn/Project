import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class userService extends userManager implements userLogin,userForgotPassword {
    void startProgram(Scanner scanner,String fileUser) {
        try {
            while (true) {
                System.out.println("\n"+"-----------------MENU-----------------");
                System.out.println("1. Đăng nhập");
                System.out.println("2. Quên mật khẩu");
                System.out.println("3. Thoát");
                int optionMenu = checkIntNumber(scanner);
                scanner.nextLine();
                switch (optionMenu) {
                    case 1:
                        userLogin(scanner,fileUser);
                        break;
                    case 2:
                        userForgotPassword(scanner, fileUser);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Không có chức năng này.Xin mời nhập lại : ");
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
                System.out.println("4. Nạp tiền điện thoại");
                System.out.println("5. Hoá đơn");
                System.out.println("6. Thoát");
                int optionMenu = checkIntNumber(scanner);
                scanner.nextLine();
                switch (optionMenu) {
                    case 1:
                        checkBalance(scanner, user, fileUser);
                        break;
                    case 2:
                        banking(scanner,user,fileUser);
                        break;
                    case 3:
                        checkHistory(scanner, user,fileUser);
                        break;
                    case 4:
                        RechargePhone(scanner, user,fileUser);
                        break;
                    case 5 : 
                        invoicing(scanner, user,fileUser);
                        break;
                    case 6:
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

     void invoicing(Scanner scanner, User user, String fileUser) {
        try {
            while (true) {
                System.out.println("-----------------MENU-----------------");
                System.out.println("1. Điện");
                System.out.println("2. Mạng");
                System.out.println("3. Nước");
                System.out.println("4. Thoát");
                int optionMenu = checkIntNumber(scanner);
                scanner.nextLine();
                switch (optionMenu){
                    case 1:
                        electric(scanner,user,fileUser);
                        break;
                    case 2:
                        internet(scanner,user,fileUser);
                        break;
                    case 3:
                        water(scanner,user,fileUser);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("không có chức năng này xin mời nhập lại");
                        break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();}
    }

     void water(Scanner scanner, User user, String fileUser) {
         List<User> users = getListObjectFromJsonFile(fileUser);
         System.out.println("Nhập mã hoá đơn của bạn : ");
         String invoicingWater = scanner.nextLine();

         System.out.println("Nhập số tiền để thanh toán hoá đơn");
         if (scanner.hasNextDouble()) {
             double moneyPhone = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < moneyPhone) {
                 System.out.println("Số dư trong tài khoản không đủ!");
             } else {
                 user.setBalance(user.getBalance() - moneyPhone);
                 convertObjectToJsonFile(fileUser, users);
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                 String formattedDateTime = LocalDateTime.now().format(formatter);
                 System.out.println("Bạn đã thanh toán hoá đơn"+"\t"+invoicingWater+"\t"+"thành công"+"\t"+formattedDateTime);
             }
         } else {
             System.out.println("Số tiền không hợp lệ!");
             scanner.nextLine();
         }
    }

     void internet(Scanner scanner, User user, String fileUser) {
        List<User> users = getListObjectFromJsonFile(fileUser);
         System.out.println("Nhập mã hoá đơn của bạn : ");
         String invoicingInternet = scanner.nextLine();

         System.out.println("Nhập số tiền để thanh toán hoá đơn");
         if (scanner.hasNextDouble()) {
             double moneyPhone = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < moneyPhone) {
                 System.out.println("Số dư trong tài khoản không đủ!");
             } else {
                 user.setBalance(user.getBalance() - moneyPhone);
                 convertObjectToJsonFile(fileUser, users);
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                 String formattedDateTime = LocalDateTime.now().format(formatter);
                 System.out.println("Bạn đã thanh toán hoá đơn"+"\t"+invoicingInternet+"\t"+"thành công"+"\t"+formattedDateTime);
             }
         } else {
             System.out.println("Số tiền không hợp lệ!");
             scanner.nextLine();
         }

    }

     void electric(Scanner scanner, User user, String fileUser) {
         List<User> users = getListObjectFromJsonFile(fileUser);
         System.out.println("Nhập mã hoá đơn của bạn : ");
         String invoicingElectric = scanner.nextLine();

         System.out.println("Nhập số tiền để thanh toán hoá đơn");
         if (scanner.hasNextDouble()) {
             double moneyPhone = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < moneyPhone) {
                 System.out.println("Số dư trong tài khoản không đủ!");
             } else {
                 user.setBalance(user.getBalance() - moneyPhone);
                 convertObjectToJsonFile(fileUser, users);

                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                 String formattedDateTime = LocalDateTime.now().format(formatter);
                 System.out.println("Bạn đã thanh toán hoá đơn"+"\t"+invoicingElectric+"\t"+"thành công"+"\t"+formattedDateTime);
             }
         } else {
             System.out.println("Số tiền không hợp lệ!");
             scanner.nextLine();
         }
    }

     void RechargePhone(Scanner scanner, User user, String fileUser) {
         List<User> users = getListObjectFromJsonFile(fileUser);
         System.out.println("Nhập số điện thoại : ");
         String phoneNumber = scanner.nextLine();

         if (!isValidVietnamesePhoneNumber(phoneNumber)) {
             System.out.println("Số điện thoại bạn nhập không chính xác");
             return;
         }

         System.out.println("Nhập số tiền cần nạp : ");
         if (scanner.hasNextDouble()) {
             double moneyPhone = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < moneyPhone) {
                 System.out.println("Số dư trong tài khoản không đủ!");
             } else {
                 user.setBalance(user.getBalance() - moneyPhone);
                 convertObjectToJsonFile(fileUser, users);
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                 String formattedDateTime = LocalDateTime.now().format(formatter);
                 System.out.println("Bạn đã nạp tiền thành công"+formattedDateTime);
             }
         } else {
             System.out.println("Số tiền không hợp lệ!");
             scanner.nextLine();
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
    void checkBalance(Scanner scanner, User user, String fileUser) {
        System.out.println("Số tài khoản của bạn là : "+"\t"+user.getAccountNumber());
        System.out.println("Số dư tài khoản của bạn là : "+user.getBalance());
    }

    @Override
    void banking(Scanner scanner,User user,String fileUser) {
        try {
            System.out.println("---------Chuyển tiền---------");
            System.out.println("Nhập số tài khoản thụ hưởng: ");
            String toAccount = scanner.nextLine();

            System.out.println("Nhập số tiền muốn gửi: ");
            double money = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Nội dung chuyển khoản: ");
            String note = scanner.nextLine();

            List<User> users = getListObjectFromJsonFile(fileUser);
            Optional<List<User>> usersOptional = Optional.ofNullable(users);

            if (usersOptional.isPresent()) {
                for (User recipient : users) {
                    if (recipient.getAccountNumber().equals(toAccount)) {
                        if (50000 < money && money < user.getBalance() - 50000) {
                            user.setBalance(user.getBalance() - money);
                            recipient.setBalance(recipient.getBalance() + money);
                            convertObjectToJsonFile(fileUser, users);
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                            String formattedDateTime = LocalDateTime.now().format(formatter);
                            System.out.println("Chuyển tiền thành công.\nThời gian giao dịch là: " + formattedDateTime + "\n" + note);
                            return;
                        } else {
                            System.out.println("Số tiền bạn nhập sai.\nLưu ý: số tiền phải trên 50000 và ít hơn số dư tài khoản của bạn trừ đi 50000");
                            return;
                        }
                    }
                }
                System.out.println("Số tài khoản thụ hưởng không đúng.");
            } else {
                System.out.println("Danh sách người dùng trống.");
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi trong quá trình chuyển tiền. Vui lòng thử lại sau.");
            e.printStackTrace();
        }
    }
    @Override
    void checkHistory(Scanner scanner, User user, String fileUser) {
    }

    @Override
    public void userForgotPassword(Scanner scanner, String fileUser) {
        System.out.println("Nhập số điện thoại của bạn : ");
        String telephone = scanner.nextLine();


        List<User> users = getListObjectFromJsonFile(fileUser);
        Optional<List<User>> usersOptional = Optional.ofNullable(users);
        if (usersOptional.isPresent()) {
            for (User user : users) {
                if (user.getPhone().equals(telephone)) {
                    System.out.println("Tổng đài sẽ gửi cho bạn mã xác nhận.");

                    Random random = new Random();


                    int randomNumber = random.nextInt(9000) + 1000;

                    System.out.println("Mã xác nhận: " + randomNumber);
                    System.out.println("\nNhập mã xác thực : ");
                    String verificationInput = scanner.nextLine();

                    String generatedVerification = String.valueOf(randomNumber);

                    if (verificationInput.equals(generatedVerification)) {
                        System.out.println("Nhập mật khẩu mới:");
                        String newPassword = scanner.nextLine();
                        user.setPassword(newPassword);
                        convertObjectToJsonFile(fileUser, users);
                        System.out.println("Mật khẩu đã được cập nhật thành công!");
                        return;
                    } else {
                        System.out.println("Mã xác thực không chính xác!");
                        return;
                    }
                }
            }
            System.out.println("Số điện thoại không tồn tại trong hệ thống.");
        }
    }
}


