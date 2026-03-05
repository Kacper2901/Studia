public class BankAccount{
    protected double balance;
    protected String userName;
    protected int accountId;

    public BankAccount(double balance, String userName, int accountId){
        setBalance(balance);
        setAccountId(accountId);
        setUserName(userName);
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            System.out.println("The balans must not be less than zero!");
        } else {
            this.balance = balance;
        }
    }

    public double getBalance(){
        return this.balance;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return "Username: " + this.userName;
    }

    public void setAccountId(int accountId){
        if (accountId > 9999 || accountId < 1000){
            System.out.println("Id must be four-digit natural number!");
        }
        else{
            this.accountId = accountId;
        }
    }

    public int getAccountId(){
        return this.accountId;
    }

    public void deposit(double money){
        if (money <= 0){
            System.out.println("You can't deposit that ammount of money!");
        }
        else{
            setBalance(money + this.balance);
            //this.balance =+ money;
        }
    }

    public void withdraw(double money){
        if (money <= 0){
            System.out.println("You can't withdraw that ammount of money!");
        }
        else{
            setBalance(this.balance - money);
            //this.balance =- money;
        }
    }

    public void info(){
        System.out.println("User: " + this.userName);
        System.out.println("Id: " + this.accountId);
        System.out.println("Balance: " + this.balance + "$");
    }
}