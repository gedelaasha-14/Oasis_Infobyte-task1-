import java.util.*;

class ATM {

    private String username;
    private String pin;
    private float balance;
    private List<String> transactions;
    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, Float> userBalances = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    ATM() {
        balance = 0.0f;
        transactions = new ArrayList<>();
    }

    void register() {
        System.out.println("Enter a new username:");
        username = sc.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists! Try a different one.");
            return;
        }

        System.out.println("Enter a new 4 or 6 digit pin:");
        pin = sc.nextLine();

        if (check_pin(pin.length())) {
            users.put(username, pin);
            userBalances.put(username, 0.0f);
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Invalid pin length. Registration failed.");
        }
    }

    void accept() {
        System.out.println("Enter your username:");
        username = sc.nextLine();

        System.out.println("Enter your pin:");
        pin = sc.nextLine();

        if (authenticate(username, pin)) {
            System.out.println("--------------------");
            System.out.println("Successful Login !!");
            System.out.println("--------------------");
            balance = userBalances.get(username);
            menu();
        } else {
            System.out.println("--------------------");
            System.out.println("Oops! Your username or pin is incorrect !!");
            System.out.println("--------------------");
        }
    }

    boolean authenticate(String username, String pin) {
        return users.containsKey(username) && users.get(username).equals(pin);
    }

    void menu() {
        int ch;
        while (true) {
            System.out.println(" ");
            System.out.println("----------------------------------");
            System.out.println("Select operations to be performed");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Quit");
            System.out.println("----------------------------------");
            System.out.println("Enter your choice");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    transaction_history();
                    break;
                case 2:
                    withdraw_cash();
                    break;
                case 3:
                    deposit_cash();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    check_balance();
                    break;
                case 6:
                    quit();
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    void transaction_history() {
        System.out.println("Transaction History:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    void withdraw_cash() {
        System.out.println("Enter amount to be withdrawn:");
        float amount = sc.nextFloat();

        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);
            System.out.println(amount + " withdrawn successfully");
        } else {
            System.out.println("OOPS!! Insufficient balance.");
        }
        userBalances.put(username, balance);
    }

    void deposit_cash() {
        System.out.println("Enter amount to be deposited:");
        float amount = sc.nextFloat();
        balance += amount;
        transactions.add("Deposited: " + amount);
        System.out.println("Rs " + amount + " successfully deposited");
        userBalances.put(username, balance);
    }

    void check_balance() {
        System.out.println("Your total balance is: " + balance);
    }

    void transfer() {
        sc.nextLine(); // Clear buffer
        System.out.println("Enter Bank Name:");
        String bank_name = sc.nextLine();

        System.out.println("Enter IFSC:");
        String ifsc = sc.nextLine();

        System.out.println("Enter account number:");
        long acc_no = sc.nextLong();

        System.out.println("Enter amount to be transferred:");
        float amount = sc.nextFloat();

        if (amount <= balance) {
            balance -= amount;
            transactions.add("Transferred: " + amount + " to Account No: " + acc_no);
            System.out.println("Amount transferred successfully to " + acc_no);
        } else {
            System.out.println("Insufficient balance!! Please try with sufficient balance.");
        }
        userBalances.put(username, balance);
    }

    void quit() {
        System.out.println("Thank you for using the ATM. Goodbye!");
        System.exit(0);
    }

    boolean check_pin(int length) {
        return length == 4 || length == 6;
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the ATM system");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    atm.register();
                    break;
                case 2:
                    atm.accept();
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}

