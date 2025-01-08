public class Balance {
    private double currentBalance;

    public Balance(double initialBalance) {
        this.currentBalance = initialBalance;
    }

    public void addIncome(double amount) {
        currentBalance += amount;
    }

    public void deductExpense(double amount) {
        currentBalance -= amount;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }
}
