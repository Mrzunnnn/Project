public class admin  {
    private String account;
    private String password;
    private int role;
    private String name;


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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public admin(){}
    public admin(String account,String password,String name,int role){
        this.account = account;
        this.password = password;
        this.name = name;
        this.role = role;
    }
@Override
public String toString() {
    return "Thông tin Admin  {" +
            "Tên admin  '" + name + '\''
            ;
}
}
