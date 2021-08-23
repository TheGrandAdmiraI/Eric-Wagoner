/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagoner_hwk2;

import java.text.*; //this import allows both decimal and number formating
import java.util.*;//this imports the scanner package so inputs can be recieved


/**
 *
 * @author Eric Wagoner
 * date: 2/2/20
 */
public class Wagoner_Hwk2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //This is where the fun begins
        
        Scanner input = new Scanner (System.in);//this allows the program to recieve input values
        
        //start of Problem 1
        System.out.println("Problem 1");
        String lower, upper; //these variables will store the input, and the input converted to upper case
        System.out.println("Please input a string");
        lower = input.nextLine(); //this assigns the input to the variable lower
        upper = lower.toUpperCase(); //this converts the input string to upper case and stores it in a separate variable
        if (upper.compareTo("G") < 0) { //compares the converted string to G and determines if it comes before
            System.out.println(upper + " comes before G");
        } else if (upper.compareTo("G") > 0) {
            System.out.println(upper + " comes after G");
        } else {
            System.out.println(upper + " is equal to G");
        }
        //end of problem 1
        
        //start of problem 2
        System.out.println("Problem 2");
        Random rand = new Random(); //this allows random numbers to be generated
        int min = 5; //this variable stores the lowest value that will be randomly generated
        int max = 25; //this variable stores the highest value
        int range = max - min; // this is the range of numbers
        int x = rand.nextInt(range + 1) + min; //this generates a random number between and including the min and max numbers
        System.out.println("The random value generated in the range is " + x);
        //end of problem 2
        
        //start of problem 3
        System.out.println("Problem 3");
        double value; //this variables will store the randomly generated double
        value = rand.nextDouble() * 10; // this generates a random double within the range [0,10)
        System.out.println("Random double is " + value);
        //end of problem 3
        
        //start of problem 4
        System.out.println("Problem 4");
        int n = 452; //this is the variable that will generate the currency amount
        double root = Math.sqrt(n); //this finds the square root of the variable
        NumberFormat fmtcurr = NumberFormat.getCurrencyInstance(); //fmtcurr will now format a number in currency form
        String money; //the fmtcurr object stores values as a string, so the variable to store the formatted number must be a string
        money = fmtcurr.format(root); //this formats the square root value into currency form and stores it to the variable
        System.out.println("The money is " + money);
        
        //System.out.println(root);
        //end of problem 4
        
        //start of problem 5
        System.out.println("Problem 5");
        double i = rand.nextDouble(); //this creates a random double on the range [0,1)
        //System.out.println(i);
        NumberFormat fmtPerc = NumberFormat.getPercentInstance(); //this will format a value to a percent
        String percent = fmtPerc.format(i); //this variable stores the formatted randomly generated double
        System.out.println("Percent value is " + percent);
        //end of problem 5
        
        //start of problem 6
        System.out.println("Problem 6");
        DecimalFormat dec = new DecimalFormat("#,##0.000"); //this will format a value to the desired pattern
        int z = 56789; //first variable to be formatted
        double frac = .43210; //second variable that will be formatted
        System.out.println(z + " is formatted as " + dec.format(z)); //formats and prints out the first variable
        System.out.println(frac + " is formatted as " + dec.format(frac)); //formats and prints out the second variable
        //end of problem 6
        
        
    }
    
}
