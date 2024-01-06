public class transactionHistory {
    private String transferAccount;
    private String dateTime;
    private double transactionMoney;
    private String transactionNote;

    public String getDateTime() {
        return dateTime;
    }

    public String getTransferAccount() {
        return transferAccount;
    }

    public void setTransferAccount(String transferAccount) {
        this.transferAccount = transferAccount;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(double transactionMoney) {
        this.transactionMoney = transactionMoney;
    }

    public String getTransactionNote() {
        return transactionNote;
    }

    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }

    public String getTransactionNumberAccount() {
        return transactionNumberAccount;
    }

    public void setTransactionNumberAccount(String transactionNumberAccount) {
        this.transactionNumberAccount = transactionNumberAccount;
    }

    private String transactionNumberAccount;
    public transactionHistory(){

    }
    public transactionHistory(String dateTime,double transactionMoney,String transactionNote,String transactionNumberAccount,String transferAccount){
        this.dateTime = dateTime;
        this.transactionMoney = transactionMoney;
        this.transactionNote = transactionNote;
        this.transactionNumberAccount = transactionNumberAccount;
        this.transferAccount = transferAccount;
    }
@Override
    public String toString(){
        return "Tài khoản gốc là :"+transferAccount+"\n"+
                "thời gian giao dịch"+ dateTime +"\n"+
                "Nội dung chuyển khoản là :"+transactionNote+"\n"
                +"Số tiền :"+"\n"+ transactionMoney+"\n"
                +"Tài khoản hưởng thụ"+"\n"+ transactionNumberAccount+"\n"
                +"}";
}
}
