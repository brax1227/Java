abstract class Account {
    protected double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (balance < amount) {
            throw new Exception("Insufficient funds!");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void endOfMonth();
}

class CheckingAccount extends Account {
    private static final double FEE = 1.50;

    public CheckingAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        super.withdraw(amount + FEE);
    }

    @Override
    public void endOfMonth() {
        System.out.println("End-of-month processing: No interest for Checking Accounts.");
    }
}

class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.02;

    public SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    public void endOfMonth() {
        balance += balance * INTEREST_RATE;
        System.out.println("End-of-month processing: Interest added.");
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        Account checking = new CheckingAccount(100);
        Account savings = new SavingsAccount(1000);

        try {
            checking.deposit(50);
            checking.withdraw(20);
            System.out.println("Checking Balance: $" + checking.getBalance());

            savings.deposit(200);
            savings.withdraw(100);
            System.out.println("Savings Balance: $" + savings.getBalance());

            checking.endOfMonth();
            savings.endOfMonth();

            System.out.println("Final Balances:");
            System.out.println("Checking: $" + checking.getBalance());
            System.out.println("Savings: $" + savings.getBalance());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
