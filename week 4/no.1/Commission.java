//********************************************************************
// Commission.java
//
// Represents an hourly employee who also earns commission on sales.
//********************************************************************
public class Commission extends Hourly {
    private double totalSales;
    private double commissionRate;

    //----------------------------------------------------------------
    // Sets up a commission employee using the specified information.
    //----------------------------------------------------------------
    public Commission (String eName, String eAddress, String ePhone,
                       String socSecNumber, double rate, double commRate) {
        super (eName, eAddress, ePhone, socSecNumber, rate);
        commissionRate = commRate;
        totalSales = 0;
    }

    //----------------------------------------------------------------
    // Adds sales amount to the total sales of this employee.
    //----------------------------------------------------------------
    public void addSales (double sales) {
        totalSales += sales;
    }

    //----------------------------------------------------------------
    // Computes and returns the pay: hourly pay + commission on sales.
    //----------------------------------------------------------------
    public double pay() {
        double payment = super.pay() + (totalSales * commissionRate);
        totalSales = 0;
        return payment;
    }

    //----------------------------------------------------------------
    // Returns information about this commission employee as a string.
    //----------------------------------------------------------------
    public String toString() {
        String result = super.toString();
        result += "\nTotal Sales: " + totalSales;
        return result;
    }
}