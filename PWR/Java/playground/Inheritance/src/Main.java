public class Main {
    public static void main(String[] args){
        BankAccount myBankAccount = new BankAccount(2000.99, "Kacper", 2111);
        SavingAccount mySavingAccount = new SavingAccount(100000, "Kacper", 2111, 0.1);

        myBankAccount.info();
        System.out.println();
        mySavingAccount.info();

        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        mySavingAccount.applyInterest();
        mySavingAccount.info();

        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        mySavingAccount.withdraw(200);
        myBankAccount.withdraw(1000000);

        mySavingAccount.info();
        myBankAccount.info();

    }
}
