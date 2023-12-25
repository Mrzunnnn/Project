import java.util.Objects;

public class User {
    private String account;
    private String password;
    private String accountNumber;
    private double balance;
    private String phone;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public User(){

    }
    public User(String account,String password,String accountNumber,double balance,String phone){
        this.account = account;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "Thông tin tài khoản  {" +
                "Số tài khoản  '" + accountNumber + '\'' +
                ", Số dư tài khoản '" + balance + '\'' +
                 + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User that = (User) obj;
        return Objects.equals(account, that.account);
    }

    // Override the hashCode method to ensure proper functioning in collections
    @Override
    public int hashCode() {
        return (account != null ? account.hashCode() : 0);
    }
}
