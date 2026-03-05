import java.time.LocalDate;


public class Transaction {
    private double ammount;
    private LocalDate date;
    private TransactionType transactionType;
    private String description;
    private Categories category;
    private int idx;

    Transaction(TransactionType transactionType, Categories category, double ammount, String description, int idx){
        if(ammount <= 0 )
            throw new IllegalArgumentException("Ammount must be greater than 0");
        if(description.isEmpty())
            throw new IllegalArgumentException("You must enter the description");

        this.ammount = ammount;
        this.transactionType = transactionType;
        this.description = description;
        this.date = LocalDate.now();
        this.category = category;
        this.idx = idx;
    }

    public double getAmmount() {
        return ammount;
    }

    public LocalDate getDate() {
        return date;
    }


    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getDescription() {
        return description;
    }


    public Categories getCategory() {
        return category;
    }


    public int getIdx() {
        return idx;
    }

    @Override
    public String toString(){
            return transactionType + " | " + category + " | " + ammount + " | " + description + " | " + date.toString();
    }
}

