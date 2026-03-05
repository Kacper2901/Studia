import java.util.ArrayList;
public class FinanceManager {
    private ArrayList<Transaction> listOfTransactions;
    private int countTransactions;
    private double total;

    public FinanceManager(){
        this.total = 0;
        this.listOfTransactions = new ArrayList<>(100000);
        this.countTransactions = 0;
    }

    public void addTransaction(Transaction transaction){
        this.listOfTransactions.add(transaction);
        this.countTransactions += 1;
        if(transaction.getTransactionType().equals(TransactionType.INCOME)) this.total += transaction.getAmmount();
        else this.total -= transaction.getAmmount();
    }

    public double calculateTotalIncome(){
        double totalIncome = 0;
        for(int i = 0; i<this.countTransactions; i++){
            Transaction currTransaction = this.listOfTransactions.get(i);
            if(currTransaction.getTransactionType().equals(TransactionType.INCOME)) totalIncome += currTransaction.getAmmount();
        }
        return totalIncome;
    }

    public double calculateTotalExpenses(){
        double totalExpenses = 0;
        for(int i = 0; i<this.countTransactions; i++){
            Transaction currTransaction = this.listOfTransactions.get(i);
            if(currTransaction.getTransactionType().equals(TransactionType.EXPENSE)) totalExpenses += currTransaction.getAmmount();
        }
        return totalExpenses;
    }

    public void deleteTransaction(int transactionIdx){
        if(listOfTransactions.get(transactionIdx - 1).getTransactionType() == TransactionType.EXPENSE){
            this.total += listOfTransactions.get(transactionIdx - 1).getAmmount();
        }
        else{
            this.total -= listOfTransactions.get(transactionIdx - 1).getAmmount();
        }
        this.listOfTransactions.remove(transactionIdx - 1);
        this.countTransactions--;
    }

    public void printTransactions(){
        for(int i = 0; i < this.countTransactions; i++){
            System.out.println((i + 1) + ". " + this.listOfTransactions.get(i));
        }
    }


    public int getCountTransactions() {
        return countTransactions;
    }

    public double getTotal() {
        return total;
    }

    public Transaction getTransactionAtIdx(int index) {
        return listOfTransactions.get(index);
    }
}
