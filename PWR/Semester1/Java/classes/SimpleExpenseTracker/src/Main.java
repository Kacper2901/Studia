    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JOptionPane; //komunikaty i pobieranie dancyh
    import java.awt.GridLayout;


    public class Main {

        public static void main(String[] args) {
            FinanceManager manager = new FinanceManager();
            JFrame frame = new JFrame("Finance Manager");
            frame.setSize(450, 450);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(8, 1));

            JButton button1 = new JButton("1. Add new income");
            JButton button2 = new JButton("2. Add new expense");
            JButton button3 = new JButton("3. Show all transactions");
            JButton button4 = new JButton("4. Calculate total income");
            JButton button5 = new JButton("5. Calculate total expense");
            JButton button6 = new JButton("6. Calculate current balance");
            JButton button7 = new JButton("7. Remove n-th transaction");
            JButton button8 = new JButton("8. Exit");

            //add income
            button1.addActionListener(e -> {
                    Categories category = askCategory(frame);
                    if (category == null) return;
                    Double amount = askAmount(frame);
                    if (amount == null) return;
                    String description = askDescription(frame);
                    if (description == null) return;

                    Transaction transaction = new Transaction(TransactionType.INCOME,category,amount,description,manager.getCountTransactions());

                    manager.addTransaction(transaction);
                    JOptionPane.showMessageDialog(frame, "Income has been added");

            });
            //add expense
            button2.addActionListener(e -> {
                    Categories category = askCategory(frame);
                    if (category == null) return;
                    Double amount = askAmount(frame);
                    if (amount == null) return;
                    String description = askDescription(frame);
                    if (description == null) return;

                    Transaction transaction = new Transaction(TransactionType.EXPENSE,category,amount,description,manager.getCountTransactions());

                    manager.addTransaction(transaction);
                    JOptionPane.showMessageDialog(frame, "Expense has been added");
            });

            // show transactions
            button3.addActionListener(e -> {
                    String text = "";
                    int i = 0;

                    if (manager.getCountTransactions() == 0) {
                        JOptionPane.showMessageDialog(frame, "There was no transactions");
                        return;
                    }

                    while (i < manager.getCountTransactions()) {
                        int number = i + 1;
                        String line = number + ". " + manager.getTransactionAtIdx(i).toString();
                        text = text + line + "\n";
                        i = i + 1;
                    }

                    JOptionPane.showMessageDialog(frame, text);

            });

            // total income
            button4.addActionListener(e -> {
                    double totalIncome = manager.calculateTotalIncome();
                    JOptionPane.showMessageDialog(frame, "Total income: " + totalIncome);
            });

            // total expense
            button5.addActionListener(e ->{
                    double totalExpense = manager.calculateTotalExpenses();
                    JOptionPane.showMessageDialog(frame, "Total expense: " + totalExpense);

            });

            // current balance
            button6.addActionListener(e -> {
                    JOptionPane.showMessageDialog(frame, "Current balance: " + manager.getTotal());
            });

            // remove n-th transaction
            button7.addActionListener(e -> {
                    if (manager.getCountTransactions() == 0) {
                        JOptionPane.showMessageDialog(frame, "No transactions to delete.");
                        return;
                    }
                    String input = JOptionPane.showInputDialog(frame,"Enter transaction number to delete (1-" + manager.getCountTransactions() + "):");
                    if (input == null) return;
                    int index;

                    try {
                        index = Integer.parseInt(input);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "You must enter a number!");
                        return;
                    }
                    if (index < 1 || index > manager.getCountTransactions()) {
                        JOptionPane.showMessageDialog(frame, "Wrong number. Must be 1-" + manager.getCountTransactions());
                        return;
                    }
                    manager.deleteTransaction(index);
                    JOptionPane.showMessageDialog(frame, "Transaction deleted");

            });

            // exit
            button8.addActionListener(e -> {
                    frame.dispose();
            });

            frame.add(button1);
            frame.add(button2);
            frame.add(button3);
            frame.add(button4);
            frame.add(button5);
            frame.add(button6);
            frame.add(button7);
            frame.add(button8);

            frame.setVisible(true);
        }

        private static Categories askCategory(JFrame frame) {
            String all = converstCategoriesToString();
            String input = JOptionPane.showInputDialog(frame, "Enter category name (for example: SALARY)\nAvailable:\n" + all);
            if (input == null) return null;
            input = input.trim();
            input = input.toUpperCase();

            if (input.equals("")) {
                JOptionPane.showMessageDialog(frame, "Category cannot be empty.");
                return null;
            }
            Categories category;

            try {
                category = Categories.valueOf(input);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, "Wrong category. Available:\n" + all);
                return null;
            }
            return category;
        }

        private static Double askAmount(JFrame frame) {
            String input = JOptionPane.showInputDialog(frame, "Enter amount (example: 12.50):");
            if (input == null) return null;
            input = input.trim();

            if (input.equals("")) {
                JOptionPane.showMessageDialog(frame, "Amount cannot be empty.");
                return null;
            }

            double amount;
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Amount must be a number.");
                return null;
            }
            if (amount <= 0) {
                JOptionPane.showMessageDialog(frame, "Amount must be greater than 0.");
                return null;
            }
            amount = Math.round(amount * 100.0) / 100.0;

            return amount;
        }

        private static String askDescription(JFrame frame) {
            String input = JOptionPane.showInputDialog(frame, "Enter description:");

            if (input == null) return null;
            input = input.trim();
            if (input.equals("")) {
                JOptionPane.showMessageDialog(frame, "Description cannot be empty.");
                return null;
            }
            return input;
        }

        private static String converstCategoriesToString() {
            Categories[] categories = Categories.values();

            String text = "";
            int i = 0;
            while (i < categories.length) {
                text = text + categories[i].name();
                if (i != categories.length - 1) {
                    text = text + ", ";
                }
                i = i + 1;
            }

            return text;
        }
    }
