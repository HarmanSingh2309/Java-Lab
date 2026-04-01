import java.util.ArrayList;

class Employee {
    protected final int empId;
    protected final String empName;
    protected double basicSalary;

    public Employee(int id, String name, double basic) {
        if (id <= 0) throw new IllegalArgumentException("Employee ID must be positive.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Employee name cannot be empty.");
        if (basic < 0) throw new IllegalArgumentException("Basic salary cannot be negative.");

        this.empId = id;
        this.empName = name;
        this.basicSalary = basic;
    }

    public double calculateGross() {
        return basicSalary;
    }

    public void printComponents() {
        System.out.printf("  Basic           : INR %.2f%n", basicSalary);
    }

    public void printHeader() {
        System.out.println("  EmpID           : " + empId);
        System.out.println("  Name            : " + empName);
        System.out.println("  Type            : " + getEmployeeType());
    }

    public String getEmployeeType() {
        return "EMPLOYEE";
    }
}

// Permanent Employee
class PermanentEmployee extends Employee {
    protected final double hra;
    protected final double da;

    public PermanentEmployee(int id, String name, double basic) {
        super(id, name, basic);
        this.hra = basic * 0.20;
        this.da  = basic * 0.15;
    }

    public String getEmployeeType() {
        return "PERMANENT";
    }

    public double calculateGross() {
        return basicSalary + hra + da;
    }

    public void printComponents() {
        System.out.printf("  Basic           : INR %.2f%n", basicSalary);
        System.out.printf("  HRA (20%%)       : INR %.2f%n", hra);
        System.out.printf("  DA  (15%%)       : INR %.2f%n", da);
    }
}

// Contract Employee
class ContractEmployee extends Employee {
    private final int hoursWorked;
    private final double hourlyRate;

    public ContractEmployee(int id, String name, int hoursWorked, double hourlyRate) {
        super(id, name, hoursWorked * hourlyRate);

        if (hoursWorked < 0) throw new IllegalArgumentException("Hours worked cannot be negative.");
        if (hourlyRate < 0) throw new IllegalArgumentException("Hourly rate cannot be negative.");

        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public String getEmployeeType() {
        return "CONTRACT";
    }

    public double calculateGross() {
        return basicSalary;
    }

    public void printComponents() {
        System.out.printf("  Hours Worked    : %d%n", hoursWorked);
        System.out.printf("  Hourly Rate     : INR %.2f/hr%n", hourlyRate);
        System.out.printf("  Earned          : INR %.2f%n", basicSalary);
    }
}

// Manager (Multilevel Inheritance)
class Manager extends PermanentEmployee {
    private final double bonus;
    private final double travelAllowance;

    public Manager(int id, String name, double basic, double bonus) {
        super(id, name, basic);

        if (bonus < 0) throw new IllegalArgumentException("Bonus cannot be negative.");

        this.bonus = bonus;
        this.travelAllowance = basic * 0.10;
    }

    public String getEmployeeType() {
        return "MANAGER";
    }

    public double calculateGross() {
        return super.calculateGross() + bonus + travelAllowance;
    }

    public void printComponents() {
        super.printComponents();
        System.out.printf("  Bonus           : INR %.2f%n", bonus);
        System.out.printf("  Travel (10%%)    : INR %.2f%n", travelAllowance);
    }
}

// Main Class
public class EmployeePayrollSystem {

    public static double calculateTax(double gross) {
        if (gross <= 20000)      return 0.0;
        else if (gross <= 50000) return gross * 0.10;
        else                     return gross * 0.20;
    }

    public static void printPayslip(Employee emp) {
        System.out.println("\n====== PAYSLIP ======");
        emp.printHeader();

        System.out.println("---- Salary Breakdown ----");
        emp.printComponents();

        double gross = emp.calculateGross();
        double tax   = calculateTax(gross);
        double net   = gross - tax;

        System.out.println("---- Summary ----");
        System.out.printf("Gross : INR %.2f%n", gross);
        System.out.printf("Tax   : INR %.2f%n", tax);
        System.out.printf("Net   : INR %.2f%n", net);
        System.out.println("=========================");
    }

    public static void main(String[] args) {

        ArrayList<Employee> staff = new ArrayList<>();

        staff.add(new PermanentEmployee(101, "Priya Sharma", 40000));
        staff.add(new ContractEmployee(102, "Rahul Verma", 160, 250));
        staff.add(new Manager(103, "Sunita Rao", 80000, 15000));

        for (Employee e : staff) {
            printPayslip(e);
        }
    }
}