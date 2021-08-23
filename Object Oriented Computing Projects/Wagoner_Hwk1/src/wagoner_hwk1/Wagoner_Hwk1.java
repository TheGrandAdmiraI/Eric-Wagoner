/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagoner_hwk1;

import java.util.Scanner; // This is necessary for an input scanner

/**
 * Date 1/23/20
 * @author Eric Wagoner
 */
public class Wagoner_Hwk1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner (System.in);//declare inpput scanner so I can recieve inputs from the keyboard
        
        //This is all problem 1
        //problem one takes two classes and their grades, outputs them, and determines whether they are in good or poor standing in those classes
        String class1; //stores the name of the first class
        String class2; //stores the name of the second class
        double grade1; //stores the grade in the first class
        double grade2; //stores the grade in the second class
        
        //first time doing part a
        System.out.println("Problem 1");
        System.out.println("Enter class name");
        class1 = input.nextLine();
        System.out.println("Enter class grade");
        grade1 = input.nextDouble();
        //it's rewind time
        input.nextLine(); //I think I need this here because the last input I recieved was a number and next I'll be recieving a string
        System.out.println("Enter class name");
        class2 = input.nextLine();
        System.out.println("Enter class grade");
        grade2 = input.nextDouble();
        //time to print them out
        System.out.println(class1 + "\t\t" + grade1);
        System.out.println(class2 + "\t\t" + grade2);
        
        //time to calculate the average and output the standing
        Double average = (grade1 + grade2)/2; //this stores the average of the two classes to determine the standing
        if(average >= 2.8) {
            System.out.println("Good Standing");
        } else {
            System.out.println("Poor Standing");
        }
        //end of problem 1
        
        
        //start of problem 2
        //problem 2 determines if an inputed character is an upper case letter or not
        System.out.println("Problem 2");
        char letter; //this is to store the input character
        System.out.println("Enter an upper case letter");
        letter = input.next().charAt(0); //
        if (letter >= 'A' && letter <= 'Z'){ //this checkes to make sure the input character is between A and Z. If the input isn't upper case then it will return false
            System.out.println(letter + " is a valid upper case letter");
        } else {
            System.out.println(letter + " is not a valid upper case letter");
        }
        //end of problem 2
        
        
        //start of problem 3
        //problem 3 determines if an integer is even or odd
        System.out.println("Problem 3");
        int num; //this stores the input integer
        System.out.println("Enter an integer");
        num = input.nextInt();
        if (num%2 == 0){// if a number is even, then it is divisible by 2 with no remainder
            System.out.println(num + " is even");
        } else {
            System.out.println(num + " is odd");
        }
        //end of problem 3
        
        //start of problem 4
        //problem 4 determines a person's age in hours given their age in years
        final int HOURS = 8760; // there are this many hours in a year
        double ageY; //this stores the age in years
        double ageH; //this stores the age in hours
        System.out.println("Problem 4");
        System.out.println("Enter your age in years");
        ageY = input.nextDouble();
        ageH = ageY * HOURS; // the age in hours is the age in years times the number of hours in a year
        System.out.println("Your age in years\t" + ageY);
        System.out.println("Your age in hours\t" + ageH);
        //end of problem 4
        
        //start of problem 5
        //problem 5 outputs the value of two integers divided by each other, unless one of them is 0 because that's undefined
        System.out.println("Problem 5");
        int var1; //stores input 1
        int var2; //stores input 2
        System.out.println("Enter an integer");
        var1 = input.nextInt();
        System.out.println("Enter another integer");
        var2 = input.nextInt();
        if (var1 == 0 || var2 == 0 ) {
            System.out.println("Sorry, but you cannot divide by zero");
        } else {
            System.out.println(var1 + "/" + var2 + " = " + (double) var1/var2);
            System.out.println(var2 + "/" + var1 + " = " + (double) var2/var1); //(double) converts the first var into a double type so that the division includes decimals
        }
        //end of problem 5
                
    }
    
}
