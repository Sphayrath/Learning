package Strings;

import java.util.Formatter;

/**
 * Created by Vlad on 28.03.2016.
 */
public class Receipt {
    private double total = 0;
    private Formatter f = new Formatter(System.out);
    private final String W1 = "%-15";
    private final String W2 = "%5";
    private final String W3 = "%10";
    public void printTitle() {
        f.format(W1+"s "+W2+"s "+W3+"s\n", "Item", "Qty", "Price");
        f.format(W1+"s "+W2+"s "+W3+"s\n", "----", "---", "-----");
    }
    public void print (String name, int qty, double price) {
        f.format(W1+".15s "+W2+"d "+W3+".2f\n", name, qty, price);
        total += price;
    }
    public void printTotal() {
        f.format(W1+".15s "+W2+"s "+W3+".2f\n", "Tax", "", total*0.06);
        f.format(W1+".15s "+W2+"s "+W3+"s\n", "", "", "-----");
        f.format(W1+".15s "+W2+"s "+W3+".2f\n", "Total", "", total*1.06);
    }

    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess Peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }
}
