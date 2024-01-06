import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class userService extends userManager implements userLogin,userForgotPassword {
    void startProgram(Scanner scanner,String fileUser,String fileHistory) {
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
                        userLogin(scanner,fileUser,fileHistory);
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
    void loginSuccess(Scanner scanner, String fileUser, User user,String fileHistory){
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
                        banking(scanner,user,fileUser,fileHistory);
                        break;
                    case 3:
                        checkHistory(scanner, user,fileUser,fileHistory);
                        break;
                    case 4:
                        RechargePhone(scanner, user,fileUser,fileHistory);
                        break;
                    case 5 : 
                        invoicing(scanner, user,fileUser,fileHistory);
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

     void invoicing(Scanner scanner, User user, String fileUser,String fileHistory) {
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
                        electric(scanner,user,fileUser,fileHistory);
                        break;
                    case 2:
                        internet(scanner,user,fileUser,fileHistory);
                        break;
                    case 3:
                        water(scanner,user,fileUser,fileHistory);
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

     void water(Scanner scanner, User user, String fileUser,String fileHistory) {
         List<User> users = getListObjectFromJsonFile(fileUser);
         String waterAccount ="92839482";
         System.out.println("Nhập mã hoá đơn của bạn : ");

         String invoicingWater = scanner.nextLine();

         System.out.println("Nhập số tiền để thanh toán hoá đơn");
         if (scanner.hasNextDouble()) {
             double money = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < money) {
                 System.out.println("Số dư trong tài khoản không đủ!");
             } else {
                 user.setBalance(user.getBalance() - money);
                 convertObjectToJsonFile(fileUser, users);
                 transactionHistory transaction = new transactionHistory();
                 transaction.setTransactionMoney(money);
                 transaction.setTransactionNumberAccount(waterAccount);
                 transaction.setTransactionNote(invoicingWater);
                 transaction.setTransferAccount(user.getAccountNumber());
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                 transaction.setDateTime(LocalDateTime.now().format(formatter));
                 List<transactionHistory> historiesTransactions = new ArrayList<>();
                 List<transactionHistory> histories = getHistoryFromJsonFile(fileHistory);
                 historiesTransactions.add(transaction);
                 if (!histories.isEmpty()) {
                     historiesTransactions.addAll(histories);
                 }
                 convertHistoryToJsonFile(fileHistory, historiesTransactions);
             }
         } else {
             System.out.println("Số tiền không hợp lệ!");
             scanner.nextLine();
         }
    }

     void internet(Scanner scanner, User user, String fileUser,String fileHistory) {
        List<User> users = getListObjectFromJsonFile(fileUser);
        String internetAccount = "83726384";
         System.out.println("Nhập mã hoá đơn của bạn : ");
         String invoicingInternet = scanner.nextLine();

         System.out.println("Nhập số tiền để thanh toán hoá đơn");
         if (scanner.hasNextDouble()) {
             double money = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < money) {
                 System.out.println("Số dư trong tài khoản không đủ!");
             } else {
                 user.setBalance(user.getBalance() - money);
                 convertObjectToJsonFile(fileUser, users);
                 transactionHistory transaction = new transactionHistory();
                 transaction.setTransactionMoney(money);
                 transaction.setTransactionNumberAccount(internetAccount);
                 transaction.setTransactionNote(invoicingInternet);
                 transaction.setTransferAccount(user.getAccountNumber());
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                 transaction.setDateTime(LocalDateTime.now().format(formatter));
                 List<transactionHistory> historiesTransactions = new ArrayList<>();
                 List<transactionHistory> histories = getHistoryFromJsonFile(fileHistory);
                 historiesTransactions.add(transaction);
                 if (!histories.isEmpty()) {
                     historiesTransactions.addAll(histories);
                 }
                 convertHistoryToJsonFile(fileHistory, historiesTransactions);
             }
         } else {
             System.out.println("Số tiền không hợp lệ!");
             scanner.nextLine();
         }

    }

     void electric(Scanner scanner, User user, String fileUser,String fileHistory) {
         List<User> users = getListObjectFromJsonFile(fileUser);
         String electricAccount = "7294723";
         System.out.println("Nhập mã hoá đơn của bạn : ");
         String invoicingElectric = scanner.nextLine();

         System.out.println("Nhập số tiền để thanh toán hoá đơn");
         if (scanner.hasNextDouble()) {
             double money = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < money) {
                 System.out.println("Số dư trong tài khoản không đủ!");
             } else {
                 user.setBalance(user.getBalance() - money);
                 convertObjectToJsonFile(fileUser, users);
                 transactionHistory transaction = new transactionHistory();
                 transaction.setTransactionMoney(money);
                 transaction.setTransactionNumberAccount(electricAccount);
                 transaction.setTransactionNote(invoicingElectric);
                 transaction.setTransferAccount(user.getAccountNumber());
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                 transaction.setDateTime(LocalDateTime.now().format(formatter));
                 List<transactionHistory> historiesTransactions = new ArrayList<>();
                 List<transactionHistory> histories = getHistoryFromJsonFile(fileHistory);
                 historiesTransactions.add(transaction);
                 if (!histories.isEmpty()) {
                     historiesTransactions.addAll(histories);
                 }
                 convertHistoryToJsonFile(fileHistory, historiesTransactions);
             }
         } else {
             System.out.println("Số tiền không hợp lệ!");
             scanner.nextLine();
         }
    }

     void RechargePhone(Scanner scanner, User user, String fileUser,String fileHistory) {
         List<User> users = getListObjectFromJsonFile(fileUser);
         System.out.println("Nhập số điện thoại : ");
         String phoneNumber = scanner.nextLine();

         if (!isValidVietnamesePhoneNumber(phoneNumber)) {
             System.out.println("Số điện thoại bạn nhập không chính xác");
             return;
         }

         System.out.println("Nhập số tiền cần nạp : ");
         if (scanner.hasNextDouble()) {
             double money = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < money) {
                 System.out.println("Số dư trong tài khoản không đủ!");
             } else {
                 user.setBalance(user.getBalance() - money);
                 convertObjectToJsonFile(fileUser, users);
                 transactionHistory transaction = new transactionHistory();
                 transaction.setTransactionMoney(money);
                 transaction.setTransactionNumberAccount(phoneNumber);
                 transaction.setTransactionNote("Nạp tiền điện thoại");
                 transaction.setTransferAccount(user.getAccountNumber());
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                 transaction.setDateTime(LocalDateTime.now().format(formatter));
                 List<transactionHistory> historiesTransactions = new ArrayList<>();
                 List<transactionHistory> histories = getHistoryFromJsonFile(fileHistory);
                 historiesTransactions.add(transaction);
                 if (!histories.isEmpty()) {
                     historiesTransactions.addAll(histories);
                 }
                 convertHistoryToJsonFile(fileHistory, historiesTransactions);
             }
         } else {
             System.out.println("Số tiền không hợp lệ!");
             scanner.nextLine();
         }
    }

    @Override
    public void userLogin(Scanner scanner,String fileUser,String fileHistory){
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
                    loginSuccess(scanner,fileUser,user,fileHistory);
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
    void banking(Scanner scanner,User user,String fileUser,String fileHistory) {
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

                            transactionHistory transaction = new transactionHistory();
                            transaction.setTransactionMoney(money);
                            transaction.setTransactionNumberAccount(toAccount);
                            transaction.setTransactionNote(note);
                            transaction.setTransferAccount(user.getAccountNumber());
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                            transaction.setDateTime(LocalDateTime.now().format(formatter));
                            List<transactionHistory> historiesTransactions = new ArrayList<>();
                            List<transactionHistory> histories = getHistoryFromJsonFile(fileHistory);
                            historiesTransactions.add(transaction);
                            if (!histories.isEmpty()) {
                                historiesTransactions.addAll(histories);
                            }
                            convertHistoryToJsonFile(fileHistory, historiesTransactions);


                            System.out.println("Chuyển tiền thành công.");
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
    void checkHistory(Scanner scanner, User user, String fileUser,String fileHistory) {
        System.out.println("------------Lịch sử giao dịch------------");
        List<transactionHistory> histories = getHistoryFromJsonFile(fileHistory);
        List<transactionHistory> newHistory = new ArrayList<>();
        for (transactionHistory history :histories){
            if (history.getTransferAccount().equals(user.getAccountNumber())){
                newHistory.add(history);
            }
        }
        System.out.println(newHistory);

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


