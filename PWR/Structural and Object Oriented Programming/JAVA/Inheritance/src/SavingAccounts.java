class SavingAccount extends BankAccount{
    private double interestRate;

    public SavingAccount(double balance, String userName, int userId, double interestRate){
        super(balance, userName, userId);
        setInterestRate(interestRate);
    }

    public void setInterestRate(double interestRate){
        if (interestRate < 0 || interestRate > 1.0){
            System.out.println("The value of the interest rate should be 0.0 - 1.0");
        }
        else{
            this.interestRate = interestRate;
        }
    }

    public double getInterestRate(){
        return this.interestRate;
    }

    public void applyInterest(){
        setBalance(this.balance + this.balance * this.interestRate);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Interest Rate: " + this.interestRate);

    }
}