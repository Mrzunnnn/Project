import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class userService extends userManager implements userLogin,userForgotPassword,register,registerAdmin {
    void startProgram(Scanner scanner,String fileUser,String fileHistory,String fileAdmin){
        try {
            while (true) {
                System.out.println("\n"+"-----------------MENU-----------------");
                System.out.println("1. Đăng nhập");
                System.out.println("2. Quên mật khẩu");
                System.out.println("3. Đăng kí");
                System.out.println("4. Đăng kí admin");
                System.out.println("5. Thoát");

                System.out.println("Nhập lựa chọn của bạn :");
                int optionMenu = checkIntNumber(scanner);
                scanner.nextLine();
                switch (optionMenu) {
                    case 1:
                        userLogin(scanner,fileUser,fileHistory,fileAdmin);
                        break;
                    case 2:
                        userForgotPassword(scanner, fileUser);
                        break;
                    case 3 :
                        register(scanner,fileUser);
                        break;
                    case 4:
                        registerAdmin(scanner,fileAdmin);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Không có chức năng này.Xin mời nhập lại : ");
                        break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loginSuccessAdmin(Scanner scanner, String fileUser,String fileHistory,String fileAdmin){
        try{
            while (true){
                System.out.println("-----------------MENU-----------------");
                System.out.println("1. Kiểm tra thông tin tài khoản");
                System.out.println("2. Kiếm tra lịch sử giao dịch");
                System.out.println("3. Khoá tài khoản ");
                System.out.println("4. Thoát");
                int optionMenu = checkIntNumber(scanner);
                scanner.nextLine();
                switch (optionMenu){
                    case 1 :
                        checkAccount(scanner,fileUser);
                        break;
                    case 2 :
                        checkHistoryAdmin(scanner,fileUser,fileHistory);
                        break;
                    case 3 :
                        banAccount(scanner,fileUser);
                        break;
                    case 4 :
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

     void banAccount(Scanner scanner, String fileUser) {
        System.out.println("Nhập số tài khoản bạn muốn khoá : ");
        String banAcc = scanner.nextLine();
        List<User> users = getListObjectFromJsonFile(fileUser);
        for (User user :users){
            if (user.getAccountNumber().equals(banAcc)){
                user.setRole(2);
                convertObjectToJsonFile(fileUser,users);
                System.out.println("Đã khoá tài khoản này");
                break;
            }
            System.out.println("Số tài khoản không tồn tại");
        }

    }

     void checkHistoryAdmin(Scanner scanner, String fileUser, String fileHistory) {
         System.out.println("Nhập số tài khoản bạn muốn kiểm tra : ");
         String checkAcc = scanner.nextLine();
         List<User> userList = getListObjectFromJsonFile(fileUser);
         List<transactionHistory> histories = getHistoryFromJsonFile(fileHistory);
         for (User user : userList) {
             if (user.getAccountNumber().equals(checkAcc)) {
                 List<transactionHistory> newHistory = new ArrayList<>();
                 for (transactionHistory history : histories) {
                     if (history.getTransferAccount().equals(user.getAccountNumber())) {
                         newHistory.add(history);
                     }
                 }
                 System.out.println(newHistory);
                 if (newHistory.isEmpty()) {
                     System.out.println("Chưa có giao dịch nào");
                 }
             }
         }
    }

     void checkAccount(Scanner scanner, String fileUser) {
         System.out.println("Nhập số tài khoản bạn muốn kiểm tra : ");
         String checkAcc = scanner.nextLine();
         List<User>userList=getListObjectFromJsonFile(fileUser);
         for (User user :userList){
             if (user.getAccountNumber().equals(checkAcc)){
                 System.out.println("Số tài khoản : "+user.getAccountNumber());
                 System.out.println("Số dư : " + user.getBalance());
                 System.out.println("Trạng thái : "+ user.getRole());
                 System.out.println("Số điện thoại : "+user.getPhone());
             }
         }
        
    }

    void loginSuccess(Scanner scanner, String fileUser, User user,String fileHistory){
        try {
            while (user.getRole()==0) {
                System.out.println("-----------------MENU-----------------");
                System.out.println("1. Truy vấn số dư tài khoản");
                System.out.println("2. Chuyển tiền");
                System.out.println("3. Xem lịch sử giao dịch");
                System.out.println("4. Nạp tiền điện thoại");
                System.out.println("5. Hoá đơn");
                System.out.println("6. Nạp tiền vào tài khoản");
                System.out.println("7. Thoát");
                System.out.println("Nhập lựa chọn của bạn : ");
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
                    case 6 :
                        payment(scanner,user,fileUser,fileHistory);
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("không có chức năng này xin mời nhập lại");
                        break;
                }
            }
            throw new RuntimeException("Tài khoản của bạn đã bị khoá");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerAdmin(Scanner scanner, String fileAdmin) {
        System.out.println("Mời nhập mã nhân viên của bạn : ");
        String id = scanner.nextLine();

        if (id.equals("25032001")) {
            System.out.println("Nhập tên tài khoản : ");
            String newAccount = scanner.nextLine();
            List<admin> admins = getlistAdminFromJsonFile(fileAdmin);

            boolean accountExists = false;
            for (admin ad : admins) {
                if (ad.getAccount().equals(newAccount)) {
                    System.out.println("Tài khoản của bạn đã tồn tại!");
                    accountExists = true;
                    break;
                }
            }

            if (!accountExists) {
                System.out.println("Nhập mật khẩu : ");
                String newpassword = scanner.nextLine();
                System.out.println("Nhập tên : ");
                String newname = scanner.nextLine();

                admin newAdmin = new admin();
                newAdmin.setName(newname);
                newAdmin.setAccount(newAccount);
                newAdmin.setRole(1);
                newAdmin.setPassword(newpassword);

                List<admin> adminList = new ArrayList<>();
                List<admin> listAdmin = getlistAdminFromJsonFile(fileAdmin);
                adminList.add(newAdmin);
                if (!listAdmin.isEmpty()) {
                    adminList.addAll(listAdmin);
                }
                convertlistAdminToJsonFile(fileAdmin, adminList);

                System.out.println("Bạn đã tạo tài khoản thành công");
            }
        } else {
            throw new RuntimeException("Mã nhân viên của bạn không hợp lệ");
        }
    }

    @Override
     public void register(Scanner scanner, String fileUser){
      try {
          System.out.println("Nhập tên tài khoản : ");
          String newAccount = scanner.nextLine();
          System.out.println("Nhập mật khẩu : ");
          String newPassword = scanner.nextLine();
          System.out.println("Nhập số điện thoại của bạn : ");
          String newTelephone = scanner.nextLine();


          List<User> users = getListObjectFromJsonFile(fileUser);

          boolean isValidAccount = true;

          for (User user : users) {
              if (user.getAccount().equals(newAccount)) {
                  System.out.println("Tài khoản đã có người sử dụng");
                  isValidAccount = false;
                  break;
              }
          }

          if (isValidAccount && !isValidVietnamesePhoneNumber(newTelephone)) {
              System.out.println("Số điện thoại của bạn không đúng");
              isValidAccount = false;
          }

          if (isValidAccount) {
              System.out.println("Bạn đã đăng kí thành công");
              Random random = new Random();
              int newAccountNumber = random.nextInt(99999999) + 10000000;

              User newUser = new User();
              newUser.setAccount(newAccount);
              newUser.setPassword(newPassword);
              newUser.setPhone(newTelephone);
              newUser.setBalance(0);
              newUser.setAccountNumber(String.valueOf(newAccountNumber));
              newUser.setRole(0);
              List<User> userList = new ArrayList<>();
              List<User> listUser = getListObjectFromJsonFile(fileUser);
              userList.add(newUser);
              if (!listUser.isEmpty()) {
                  userList.addAll(listUser);
              }
              convertObjectToJsonFile(fileUser, userList);
          }

      } catch (Exception e) {
          e.printStackTrace();
      }
  }

     void payment(Scanner scanner,User user ,String fileHistory,String fileUser){
         System.out.println("Số tiền bạn muốn nạp vào tài khoản:");
         double moneyPayment = scanner.nextDouble();
         List<User> userList = getListObjectFromJsonFile(fileUser);
         List<User> newmoney = new ArrayList<>();
         if (moneyPayment>50000) {
             for (User u : userList) {
                 if (!u.getAccount().equals(user.getAccount())) {
                     newmoney.add(u);
                 }
             }
             user.setBalance(user.getBalance() + moneyPayment);
             newmoney.add(user);
             convertObjectToJsonFile(fileUser, newmoney);
             System.out.println("Bạn đã nạp tiền thành công!");
         }
         throw new RuntimeException("số tiền phải trên 50000!");
     }

     void invoicing(Scanner scanner, User user, String fileUser,String fileHistory) {
        try {
            while (true) {
                System.out.println("-----------------MENU-----------------");
                System.out.println("1. Điện");
                System.out.println("2. Mạng");
                System.out.println("3. Nước");
                System.out.println("4. Thoát");
                System.out.println("Nhập lựa chọn của bạn : ");
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
         List<User> userList = getListObjectFromJsonFile(fileUser);
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
                 System.out.println("Bạn đã thanh toán thành công!");
                 List<User> newmoney = new ArrayList<>();
                 for (User u : userList) {
                     if (!u.getAccount().equals(user.getAccount() )) {
                         newmoney.add(u);
                     }
                 }
                 user.setBalance(user.getBalance() - money);
                 newmoney.add(user);
                 convertObjectToJsonFile(fileUser, newmoney);
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
             throw new RuntimeException("Số tiền không hợp lệ!");
         }
    }

     void internet(Scanner scanner, User user, String fileUser,String fileHistory) {
        List<User> userList = getListObjectFromJsonFile(fileUser);
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
                 System.out.println("Bạn đã thanh toán thành công!");
                 List<User> newmoney = new ArrayList<>();
                 for (User u : userList) {
                     if (!u.getAccount().equals(user.getAccount() )) {
                         newmoney.add(u);
                     }
                 }
                 user.setBalance(user.getBalance() - money);
                 newmoney.add(user);
                 convertObjectToJsonFile(fileUser, newmoney);
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
             throw new RuntimeException("Số tiền không hợp lệ!");

         }

    }

     void electric(Scanner scanner, User user, String fileUser,String fileHistory) {
         List<User> userList = getListObjectFromJsonFile(fileUser);
         String electricAccount = "7294723";
         System.out.println("Nhập mã hoá đơn của bạn : ");
         String invoicingElectric = scanner.nextLine();

         System.out.println("Nhập số tiền để thanh toán hoá đơn");
         if (scanner.hasNextDouble()) {
             double money = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < money) {
                 throw new RuntimeException("Số dư trong tài khoản không đủ!");
             } else {
                 System.out.println("Bạn đã thanh toán thành công!");
                 List<User> newmoney = new ArrayList<>();
                 for (User u : userList) {
                     if (!u.getAccount().equals(user.getAccount() )) {
                         newmoney.add(u);
                     }
                 }
                 user.setBalance(user.getBalance() - money);
                 newmoney.add(user);
                 convertObjectToJsonFile(fileUser, newmoney);
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
             throw new RuntimeException("Số tiền không hợp lệ!");
         }
    }

     void RechargePhone(Scanner scanner, User user, String fileUser,String fileHistory) {
         List<User> userList = getListObjectFromJsonFile(fileUser);
         System.out.println("Nhập số điện thoại : ");
         String phoneNumber = scanner.nextLine();

         if (!isValidVietnamesePhoneNumber(phoneNumber)) {
             throw new RuntimeException("Số điện thoại bạn nhập không chính xác");
         }

         System.out.println("Nhập số tiền cần nạp : ");
         if (scanner.hasNextDouble()) {
             double money = scanner.nextDouble();
             scanner.nextLine();

             if (user.getBalance() < money) {
                 throw  new RuntimeException("Số dư trong tài khoản không đủ!");
             } else {
                 System.out.println("Bạn đã nạp tiền thành công!");
                 List<User> newmoney = new ArrayList<>();
                 for (User u : userList) {
                     if (!u.getAccount().equals(user.getAccount() )) {
                         newmoney.add(u);
                     }
                 }
                 user.setBalance(user.getBalance() - money);
                 newmoney.add(user);
                 convertObjectToJsonFile(fileUser, newmoney);
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
             throw new RuntimeException("Số tiền không hợp lệ!");
         }
    }

    @Override
    public void userLogin(Scanner scanner,String fileUser,String fileHistory,String fileAdmin){
    try {
        System.out.println("----------Đăng nhập----------");
        System.out.println("Nhập tài khoàn của bạn : ");
        String accountName = scanner.nextLine();
        System.out.println("Nhập mật khẩu của bạn : ");
        String accountPassword = scanner.nextLine();
        List<User> userList = getListObjectFromJsonFile(fileUser);
        List<admin> adminList = getlistAdminFromJsonFile(fileAdmin);
        Optional<List<User>> usersOptional = Optional.ofNullable(userList);
        if (usersOptional.isPresent()){
            for (User user : userList){
                if (user.getAccount().equals(accountName)&&user.getPassword().equals(accountPassword)){
                    loginSuccess(scanner,fileUser,user,fileHistory);
                    return;
                }
            }

        }
        Optional<List<admin>> adminsOptional = Optional.ofNullable(adminList);
        if (adminsOptional.isPresent()){
            for (admin admins : adminList){
                if (admins.getAccount().equals(accountName)&&admins.getPassword().equals(accountPassword)){
                    loginSuccessAdmin(scanner,fileUser,fileHistory,fileAdmin);
                    return;
                }
            }throw new RuntimeException("Tài khoản hoặc mật khẩu không chính xác");
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

            List<User> userList = getListObjectFromJsonFile(fileUser);
            Optional<List<User>> usersOptional = Optional.ofNullable(userList);

            if (usersOptional.isPresent()) {
                for (User recipient : userList) {
                    if (recipient.getAccountNumber().equals(toAccount)) {
                        if (50000 < money && money < user.getBalance() - 50000) {
                            List<User> newmoney = new ArrayList<>();
                            for (User u : userList) {
                                if (!u.getAccount().equals(user.getAccount() )) {
                                    newmoney.add(u);
                                }
                            }
                            for (User toU : userList){
                             if(toU.getAccountNumber().equals(toAccount)){
                                 recipient.setBalance(recipient.getBalance() + money);
                             }
                            }
                            user.setBalance(user.getBalance() - money);
                            newmoney.add(user);
                            convertObjectToJsonFile(fileUser, newmoney);
                            recipient.setBalance(recipient.getBalance() + money);
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
                            throw new RuntimeException("Số tiền bạn nhập sai.\nLưu ý: số tiền phải trên 50000 và ít hơn số dư tài khoản của bạn trừ đi 50000");
                        }
                    }
                }
                System.out.println("Số tài khoản thụ hưởng không đúng.");
            } else {
                System.out.println("Danh sách người dùng trống.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Đã xảy ra lỗi trong quá trình chuyển tiền. Vui lòng thử lại sau.");
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
        if (newHistory.isEmpty()){
            System.out.println("Chưa có giao dịch nào");
        }
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
            throw new RuntimeException("Số điện thoại không tồn tại trong hệ thống.");
        }
    }
}


