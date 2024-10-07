// GROUP W
// creatted by TSHEDZA
package part1i;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
        
// creating an abstract class of Expenses
abstract class Expense{
    
    protected String name;
    protected double amount;
                   
// constructor of expenses    
    public Expense(String name, double amount){
           this.name = name;
           this.amount = amount;
}
    // abstract method that will be used to calculate expenses
    
    public abstract double totalExpenses();
    
    public double getAmount(){
    return amount;
    }
    
   
    public String toString() {
    
    return name + ": " + amount; 
    }
}

// classes that extend abstract Expense to get different expenses

class Groceries extends Expense {
   public Groceries(double amount) {
   super("Groceries", amount);
   }
   
   @Override
   public double totalExpenses(){
   return amount;
   }

}
class Utilities extends Expense {
   public Utilities(double amount) {
   super("Water and lights", amount);
   }
   
   @Override
   public double totalExpenses(){
   return amount;
   }

}
class Travel extends Expense {
   public Travel(double amount) {
   super("Travelling cost", amount);
   }
   
   @Override
   public double totalExpenses(){
   return amount;
   }

}
class Communication extends Expense {
   public Communication(double amount) {
   super("Cellphone and Telephone", amount);
   }
   
   @Override
   public double totalExpenses(){
   return amount;
   }

}
class otherExpenses extends Expense {
   public otherExpenses(double amount) {
   super("Other Expenses", amount);
   }
   
   @Override
   public double totalExpenses(){
   return amount;
   }

}
// class for renring option
class Renting extends Expense{
    public Renting(double amount){
    super("Renting", amount);
    }
    @Override
    public double totalExpenses(){
    return amount;
    }
}
// class for buying the house option
final class Buying extends Expense{
private final double purchasePrice;
private final double depositAmount;
private final double interestRate;
private final double loanPeriod;

public Buying(double purchasePrice, double depositAmount, double interestRate, double loanPeriod){
    super("Monthly house repayment", 0);
    this.purchasePrice = purchasePrice;
    this.depositAmount =depositAmount;
    this.interestRate = interestRate;
    this.loanPeriod = loanPeriod;
    this.amount = totalExpenses(); // setting the total of all expenses
}

//calculating the monthly loan repayment and adding it to total expenses
@Override
public double totalExpenses(){
    double loanAmount = purchasePrice - depositAmount;
    double decimalInterestRate = interestRate/100;
    double periodInYears = loanPeriod/12;
    double totalLoanAmount = loanAmount*(1+decimalInterestRate*periodInYears);
    double monthlyPayment = totalLoanAmount/loanPeriod;
    
    return monthlyPayment;
}
}
// setting up a class for car loan
class CarLoan extends Expense {
    private final String model;
    private final String manufacturer;
    private final double vehiclePurchasePrice;
    private final double vehicleDeposit;
    private final double vehicleInterestRate;
    private final double insurancePremium;
    private final double monthsToRepay;

    public CarLoan(String model,String manufacturer, double purchasePrice, double deposit, double interestRate, double insurancePremium, double monthsToRepay) {
        super("Car monthly payment", 0);
        this.model = model;
        this.manufacturer = manufacturer;
        this.vehiclePurchasePrice = purchasePrice;
        this.vehicleDeposit = deposit;
        this.vehicleInterestRate = interestRate;
        this.insurancePremium = insurancePremium;
        this.monthsToRepay = monthsToRepay;
        this.amount = totalExpenses();
    }
// to calculate the monthly repayments for the car
    @Override
    public double totalExpenses() {
        double principal = vehiclePurchasePrice - vehicleDeposit;
        double monthlyInterestRate = (vehicleInterestRate / 100) / 12;
        double periodOfPayment = monthsToRepay/12;
        double carLoanRepayment = principal*(1+monthlyInterestRate*periodOfPayment);
        double monthlyCarPayment = carLoanRepayment/monthsToRepay;
        double monthlyCarInstallment = monthlyCarPayment + insurancePremium; // Total monthly cost = loan repayment + insurance premium
        return monthlyCarInstallment ; 
    }
}

       
public class Part1I {
// the main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Expense> expenses = new ArrayList<>(); // storing expenses in an array
        
        // requesting the user to enter monthly gross salary and tax deductions
        System.out.println("Enter your gross monthly income: ");
        double monthlyIncome = input.nextDouble();
        System.out.println("Enter your estimated monthly tax deductions: ");
        double deductedTax = input.nextDouble();
        
        // asking the user if they want to buy or rent property
        int propertyChoice =0;
        double livingCost = 0.0;
        // using do while to ensure that the program is only limmited to either buying or renting
        do {
            System.out.println("Would you like to rent or buy property? << Enter 0 for renting or 1 for buying >>");
           propertyChoice = input.nextInt();
           if (propertyChoice != 0 && propertyChoice !=1){
               System.out.println("invalid option selected. Please enter 0 for renting or 1 for buying");    
           }
           }
           while (propertyChoice != 0 && propertyChoice !=1);
                 
              if (propertyChoice == 0) {
        System.out.println("Enter your monthly rental amount: ");
        double rentAmount = input.nextDouble();
        expenses.add(new Renting(rentAmount));
        livingCost = rentAmount;
        }
        else if (propertyChoice == 1){
        System.out.println("Enter the house purchase price");
        double purchasePrice = input.nextDouble();
        System.out.println("Enter the deposit amount: ");
        double depositAmount = input.nextDouble();
        System.out.println("Enter the interest rate in percentage");
        double interestRate = input.nextDouble();
        System.out.println("Enter the loan period in months ( should be between 240 and 360 months) ");
        double loanPeriod = input.nextDouble();
        
        Buying buying = new Buying(purchasePrice, depositAmount, interestRate, loanPeriod);
        expenses.add(buying);
        livingCost = buying.totalExpenses();
        // checking the likelihood of getting the loan
        if (buying.totalExpenses()>monthlyIncome/3)
            System.out.println("WARNING: your monthly repayment is greater than one-third of your gross salary. Approval of your loan is unlikely");
        
        }
        // asking the user to enter their monthly expenses
        System.out.println("Enter the amount of money spent on grocery monthly: ");
        double groceryMoney = input.nextDouble();
        expenses.add(new Groceries(groceryMoney));
        
        System.out.println("Enter the amount of money spent on water and Lights monthly: ");
        double utilitiesMoney = input.nextDouble();
        expenses.add(new Groceries(utilitiesMoney));
        
        System.out.println("Enter the amount of money spent on travelling monthly: ");
        double travelCost = input.nextDouble();
        expenses.add(new Utilities(travelCost));
        
        System.out.println("Enter the amount of money spent on cellphone and telephone monthly: ");
        double communicationMoney = input.nextDouble();
        expenses.add(new Communication(communicationMoney));
        
        System.out.println("Enter the amount of money spent on any other expenses monthly: ");
        double otherExpensesMoney = input.nextDouble();
        expenses.add(new otherExpenses(otherExpensesMoney));
        
        
        // Ask the user if they want to buy a vehicle
        System.out.println("Do you want to buy a vehicle? (Enter 'yes' or 'no'): ");
        String buyVehicle = input.next();
        double monthlyCarPayment =0.0;

        if (buyVehicle.equalsIgnoreCase("yes")) {
            System.out.println("Enter the model of the vehicle: ");
            String model = input.next();
            
             System.out.println("Enter the make of the vehicle: ");
            String manufacturer = input.next();

            System.out.println("Enter the purchase price of the vehicle: ");
            double vehiclePurchasePrice = input.nextDouble();

            System.out.println("Enter the total deposit: ");
            double vehicleDeposit = input.nextDouble();

            System.out.println("Enter the interest rate (as a percentage): ");
            double vehicleInterestRate = input.nextDouble();
            
            System.out.println("Enter the period of payment in months ");
            double monthsToRepay = input.nextDouble();

            System.out.println("Enter the estimated monthly insurance premium: ");
            double insurancePremium = input.nextDouble();

            CarLoan carLoan = new CarLoan(model,manufacturer, vehiclePurchasePrice, vehicleDeposit, vehicleInterestRate, insurancePremium, monthsToRepay); // 5 years = 60 months
            expenses.add(carLoan);
            monthlyCarPayment = carLoan.totalExpenses();
        }

        //calculating the net income, total expenses and available money
        
        double netIncome = monthlyIncome - deductedTax; 
        double totalExpenses = 0;
        
        for (Expense expense : expenses){
            totalExpenses +=  expense.totalExpenses();
        }
      
      
        if (totalExpenses > 0.75 * monthlyIncome) {
            System.out.println("Warning: Your total expenses exceed 75% of your gross income.");
        }
        
        // Display the total expenditure in descending order by value
        Collections.sort(expenses, (a, b) -> Double.compare(b.totalExpenses(), a.totalExpenses()));
        System.out.println("Expenses in descending order by value:");
        for (Expense expense : expenses) {
            System.out.println(expense.toString());
        }


// Display the breakdown of expenses
       // System.out.println("\n----- Expenses Breakdown ----");
           // for (Expense expense : expenses){
             //  System.out.println(expense.toString());
           // }
            
        double availableMoney = netIncome - totalExpenses;
        // displaying the results
        System.out.println("\n----- Summary ----");
        System.out.println("Gross Income: " + monthlyIncome);
        System.out.println("Net income: " + netIncome);
        System.out.println("Total Expenditures: " + totalExpenses);
        System.out.println("Available Income after expenditure: " + availableMoney);
            
    }
}