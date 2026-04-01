import java.util.ArrayList;

// Checked exception
class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }
}

// Base Class
class BankAccount {

    private final String accountNo;
    private final String holderName;
    protected double balance;

    public BankAccount(String accountNo, String holderName, double openingBalance) throws BankingException {

        if (accountNo == null || accountNo.isBlank()) {
            throw new BankingException("Account number cannot be empty.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new BankingException("Holder name cannot be empty.");
        }
        if (openingBalance < 0) {
            throw new BankingException("Opening balance cannot be negative.");
        }

        this.accountNo = accountNo;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    public void deposit(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Withdraw amount must be positive.");
        }
        if (amount > balance) {
            throw new BankingException("Insufficient balance.");
        }
        balance -= amount;
    }

    public String getAccountType() {
        return "GENERIC";
    }

    public String summary() {
        return String.format("[%s] AccNo=%s, Holder=%s, Balance=%.2f",
                getAccountType(), accountNo, holderName, balance);
    }
}

// Savings Account
class SavingsAccount extends BankAccount {

    private final double minBalance;
    private final double interestRate;

    public SavingsAccount(String accountNo, String holderName,
                          double openingBalance, double minBalance, double interestRate) throws BankingException {
        super(accountNo, holderName, openingBalance);

        if (minBalance < 0) {
            throw new BankingException("Minimum balance cannot be negative.");
        }
        if (interestRate < 0) {
            throw new BankingException("Interest rate cannot be negative.");
        }

        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }

    @Override
    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Withdraw amount must be positive.");
        }

        if (balance - amount < minBalance) {
            throw new BankingException("Minimum balance must be maintained.");
        }

        balance -= amount;
    }
}

// Current Account
class CurrentAccount extends BankAccount {

    private final double overdraftLimit;

    public CurrentAccount(String accountNo, String holderName,
                          double openingBalance, double overdraftLimit) throws BankingException {
        super(accountNo, holderName, openingBalance);

        if (overdraftLimit < 0) {
            throw new BankingException("Overdraft limit cannot be negative.");
        }

        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String getAccountType() {
        return "CURRENT";
    }

    @Override
    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Withdraw amount must be positive.");
        }

        if (balance - amount < -overdraftLimit) {
            throw new BankingException("Overdraft limit exceeded.");
        }

        balance -= amount;
    }
}

// Main Class
public class BankingSimpleDemo {

    public static void main(String[] args) {

        try {
            BankAccount acc1 = new SavingsAccount("SA-101", "Ayaan", 5000, 1000, 4.0);
            BankAccount acc2 = new CurrentAccount("CA-201", "Isha", 2000, 3000);
            BankAccount acc3 = new BankAccount("BA-301", "Rahul", 1500);

            ArrayList<BankAccount> accounts = new ArrayList<>();
            accounts.add(acc1);
            accounts.add(acc2);
            accounts.add(acc3);

            System.out.println("---- Initial State ----");
            printAll(accounts);

            System.out.println("\n---- Deposit 1000 ----");
            for (BankAccount acc : accounts) {
                acc.deposit(1000);
            }
            printAll(accounts);

            System.out.println("\n---- Withdraw ----");
            acc1.withdraw(4500);
            acc2.withdraw(6000);
            acc3.withdraw(500);

            printAll(accounts);

        } catch (BankingException ex) {
            System.out.println("Banking error: " + ex.getMessage());
        }
    }

    private static void printAll(ArrayList<BankAccount> accounts) {
        for (BankAccount acc : accounts) {
            System.out.println(acc.summary());
        }
    }
}