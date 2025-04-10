

import java.util.Scanner;

abstract class Account {
    protected String accountNumber;
    protected String pin;
    protected double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public abstract void displayMenu();
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
}

class ATMUser extends Account {

    public ATMUser(String accountNumber, String pin, double balance) {
        super(accountNumber, pin, balance);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n==== ATM MENU ====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Deposited $" + amount);
        } else {
            System.out.println(" Invalid amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(" Withdrawn " + amount);
        } else {
            System.out.println(" Insufficient balance or invalid amount.");
        }
    }
}

public class ATMSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a test user (In future: store multiple users)
        ATMUser user = new ATMUser("12345678", "1234", 1000.00);

        System.out.println(" Welcome to Java ATM");
        System.out.print(" Enter your PIN: ");
        String enteredPin = scanner.nextLine();

        if (user.authenticate(enteredPin)) {
            boolean running = true;
            while (running) {
                user.displayMenu();
                System.out.print(" Select an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("💰 Balance: " + user.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        user.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        user.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println(" Thank you for using Java ATM. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println(" Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println(" Incorrect PIN. Access denied.");
        }

        scanner.close();
    }
}

