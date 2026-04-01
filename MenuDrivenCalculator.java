import java.util.Scanner;

public class MenuDrivenCalculator {

    // Method for Addition
    public static double add(double a, double b) {
        return a + b;
    }

    // Method for Subtraction
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Method for Multiplication
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Method for Division
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return a / b;
    }

    // Method to display menu
    public static void displayMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            displayMenu();

            // Input validation for choice
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number (1-5).");
                sc.next(); // discard invalid input
                displayMenu();
            }

            choice = sc.nextInt();

            if (choice >= 1 && choice <= 4) {

                double num1, num2;

                // Input validation for numbers
                System.out.print("Enter first number: ");
                while (!sc.hasNextDouble()) {
                    System.out.println("Invalid input! Enter a valid number.");
                    sc.next();
                }
                num1 = sc.nextDouble();

                System.out.print("Enter second number: ");
                while (!sc.hasNextDouble()) {
                    System.out.println("Invalid input! Enter a valid number.");
                    sc.next();
                }
                num2 = sc.nextDouble();

                try {
                    double result = 0;

                    switch (choice) {
                        case 1:
                            result = add(num1, num2);
                            break;
                        case 2:
                            result = subtract(num1, num2);
                            break;
                        case 3:
                            result = multiply(num1, num2);
                            break;
                        case 4:
                            result = divide(num1, num2);
                            break;
                    }

                    System.out.println("Result: " + result);

                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (choice == 5) {
                System.out.println("Exiting calculator. Thank you!");
            } else {
                System.out.println("Invalid choice! Please select between 1-5.");
            }

        } while (choice != 5);

        sc.close();
    }
}