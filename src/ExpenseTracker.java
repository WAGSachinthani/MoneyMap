import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseTracker {
    private static List<Expense> expenses = new ArrayList<>();
    private static Balance balance = new Balance(0);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- MoneyMap Menu ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Edit Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Set Bank Balance");
            System.out.println("6. View Current Balance");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addExpense(scanner);
                case 2 -> viewExpenses();
                case 3 -> editExpense(scanner);
                case 4 -> deleteExpense(scanner);
                case 5 -> setBankBalance(scanner);
                case 6 -> System.out.println("Current Balance: " + balance.getCurrentBalance());
                case 7 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

    private static void addExpense(Scanner scanner) {
        System.out.print("Enter date (yyyy-mm-dd): ");
        String date = scanner.next();
        System.out.print("Enter category: ");
        String category = scanner.next();
        System.out.print("Enter description: ");
        String description = scanner.next();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        Expense expense = new Expense(date, category, description, amount);
        expenses.add(expense);
        balance.deductExpense(amount);

        System.out.println("Expense added successfully! Updated Balance: " + balance.getCurrentBalance());
    }

    private static void viewExpenses() {
        System.out.println("\n--- Expenses ---");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void editExpense(Scanner scanner) {
        System.out.print("Enter expense index to edit: ");
        int index = scanner.nextInt();
        if (index < 0 || index >= expenses.size()) {
            System.out.println("Invalid index!");
            return;
        }

        Expense expense = expenses.get(index);
        System.out.print("Enter new category: ");
        expense.setCategory(scanner.next());
        System.out.print("Enter new description: ");
        expense.setDescription(scanner.next());
        System.out.print("Enter new amount: ");
        expense.setAmount(scanner.nextDouble());

        System.out.println("Expense updated successfully!");
    }

    private static void deleteExpense(Scanner scanner) {
        System.out.print("Enter expense index to delete: ");
        int index = scanner.nextInt();
        if (index < 0 || index >= expenses.size()) {
            System.out.println("Invalid index!");
            return;
        }

        Expense removedExpense = expenses.remove(index);
        balance.addIncome(removedExpense.getAmount());
        System.out.println("Expense deleted successfully! Updated Balance: " + balance.getCurrentBalance());
    }

    private static void setBankBalance(Scanner scanner) {
        System.out.print("Enter current bank balance: ");
        double amount = scanner.nextDouble();
        balance.addIncome(amount);
        System.out.println("Bank balance set successfully! Your Current Balance: " + balance.getCurrentBalance());
    }
}
