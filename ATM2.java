import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATM {
    private int balance;
    private final List<String> transactionHistory = new ArrayList<>();

    public ATM(int initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        balance += amount;
        String transaction = "Deposited: Rs." + amount;
        recordTransaction(transaction);
        System.out.println(transaction);
    }

    public boolean withdraw(int amount) {
        if (amount > balance) {
            String transaction = "Failed Withdrawal: Rs." + amount + " (Insufficient funds)";
            recordTransaction(transaction);
            System.out.println("Insufficient funds!");
            return false;
        }
        balance -= amount;
        String transaction = "Withdrawn: Rs." + amount;
        recordTransaction(transaction);
        System.out.println(transaction);
        return true;
    }

    public int getBalance() {
        String transaction = "Checked Balance: Rs." + balance;
        recordTransaction(transaction);
        return balance;
    }

    public void displayMenu() {
        System.out.println("Select a service:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Last 5 Transactions");
        System.out.println("5. Exit");
    }

    private void recordTransaction(String transaction) {
        if (transactionHistory.size() == 5) {
            transactionHistory.remove(0); // Remove the oldest transaction
        }
        transactionHistory.add(transaction);
    }

    public void displayLastFiveTransactions() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Last 5 Transactions:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM(10000);
        System.out.println("Welcome to the ATM!");

        String continueMenu;
        do {
            atm.displayMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter deposit amount:");
                    int depositAmount = sc.nextInt();
                    atm.deposit(depositAmount);
                    break;
                case 2:
                    System.out.println("Enter withdrawal amount:");
                    int withdrawAmount = sc.nextInt();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Current balance: Rs." + atm.getBalance());
                    break;
                case 4:
                    atm.displayLastFiveTransactions();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM!");
                    sc.close();
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            System.out.println("Do you want to return to the menu? (Y/N)");
            continueMenu = sc.next();
        } while (continueMenu.equalsIgnoreCase("Y"));
    }
}
