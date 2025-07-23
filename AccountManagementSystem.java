
    import java.util.*;

class BankAccount {
    private static int idCounter = 1000;
    private static int accNumberCounter = 100000;

    private int accountId;
    private String accountNumber;
    private String holderName;
    private String email;
    private String phone;
    private String bankName;
    private double balance;

    public BankAccount(String holderName, String email, String phone, String bankName) {
        this.accountId = idCounter++;
        this.accountNumber = "ACC" + accNumberCounter++;
        this.holderName = holderName;
        this.email = email;
        this.phone = phone;
        this.bankName = bankName;
        this.balance = 0.0;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void displayDetails() {
        System.out.println("\nAccount ID: " + accountId);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Bank Name: " + bankName);
        System.out.println("Balance: ₹" + balance);
    }
}

public class AccountManagementSystem{
    private static Scanner scanner = new Scanner(System.in);
    private static List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n====== Account Management System ======");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performTransaction("deposit");
                    break;
                case 3:
                    performTransaction("withdraw");
                    break;
                case 4:
                    viewBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using the Account Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private static void createAccount() {
        System.out.print("Enter holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();

        BankAccount account = new BankAccount(name, email, phone, bankName);
        accounts.add(account);
        System.out.println("Account created successfully!");
        account.displayDetails();
    }

    private static void performTransaction(String type) {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        BankAccount account = findAccount(accNum);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount: ₹");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        if (type.equals("deposit")) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }

    private static void viewBalance() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        BankAccount account = findAccount(accNum);

        if (account != null) {
            account.displayDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static BankAccount findAccount(String accNum) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accNum)) {
                return acc;
            }
        }
        return null;
    }
}

