// GROUP W
// creatted by MT
package part1i;

import java.util.Scanner;
import java.util.ArrayList;
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
    
    public double gatAmount(){
    return amount;
    }
    
    @Override
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
// class for loan option
final class Buying extends Expense{
private final double purchasePrice;
private final double depositAmount;
private final double interestRate;
private final double loanPeriod;

public Buying(double purchasePrice, double depositAmount, double interestRate, double loanPeriod){
    super("Monthly repayment", 0);
    this.purchasePrice = purchasePrice;
    this.depositAmount =depositAmount;
    this.interestRate = interestRate;
    this.loanPeriod = loanPeriod;
    this.amount = totalExpenses(); // setting the amount to monthly payment
}

//calculating the monthly loan repayment
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
       
public class Part1I {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Expense> expenses = new ArrayList<>();
        
        // requesting the user to enter monthly gross salary and tax deductions
        System.out.println("Enter your gross monthly income: ");
        double monthlyIncome = input.nextDouble();
        System.out.println("Enter your estimated monthly tax deductions: ");
        double deductedTax = input.nextDouble();
        
        // asking the user if they want to buy or rent property
        int propertyChoice =0;
        double livingCost = 0.0;
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
        
        //calculating the net income, total expenses and available money
        
        double netIncome = monthlyIncome - deductedTax; 
        double totalExpenses = 0;
        
        for (Expense expense : expenses){
            totalExpenses +=  expense.totalExpenses();
        }
       
        double availableMoney = netIncome - totalExpenses;
      
        
        // Display the breakdown of expenses
        System.out.println("\n----- Expenses Breakdown ----");
            for (Expense expense : expenses){
               System.out.println(expense.toString());
            }
        // displaying the results
        System.out.println("\n----- Summary ----");
        System.out.println("Gross Income: " + monthlyIncome);
        System.out.println("Net income: " + netIncome);
        System.out.println("Total Expenditures: " + totalExpenses);
        System.out.println("Available Income after expenditure: " + availableMoney);
            
    }
}