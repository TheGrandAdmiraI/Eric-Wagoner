/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagoner_hwk5;

import java.util.Scanner;

/**
 *
 * @author Eric Wagoner 
 * Date 4/8/20
 */
public class Wagoner_Hwk5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //This is where the fun begins
        Scanner input = new Scanner(System.in);//This creates a scanner to allow for inputs

        Computer[] machines = new Computer[4];//creates the array of abstract objects which can be filled with the child objects because of the is-a relationship
        machines[0] = new Laptop("Dell xps 13", "Windows", "i5 8550u", "Intel HD620", 8, 256, 60, true); //fill the array with objects and initialize them
        machines[1] = new GamingLaptop("Razer Blade 15", "Windows", "i7 9750H", "RTX 2070 Max-Q", 16, 512, 80, true, 144, true);
        machines[2] = new GamingLaptop("Asus Zephyrus G14", "Windows", "Ryzen 9 4800HS", "RTX 2060", 16, 512, 76, false, 144, false);
        machines[3] = new Laptop("MacBook Air", "MacOS", "Intel Core i5", "Intel Iris Plus", 8, 128, 50, true);
        for (int i = 0; i < machines.length; i++) { //run through the array
            System.out.println("The class of the object is: " + machines[i].getClass().getSimpleName());//first print out which class the object in the array belongs to
            System.out.println(machines[i].toString()); //then print out all the details for the object
            System.out.println("");//this is just here to separate the text belonging to each object
        }
        
        for (int i = 0; i < machines.length; i++) { //now we're running through the array again, but doing something different
            String s = machines[i].getClass().getSimpleName(); //this string holds the class name of each object to be compared to later
            if(s.equals("Laptop")){ //checks to see if the class name of the object matches Laptop, and if it does, that means we can cast the object to Laptop
                System.out.println("What kind of workload will you have? (light, heavy, or other)"); //the method I created for Laptop checks the battery life in different kinds of workloads, so I figured I would allow for the different scenarios
                String in = input.next(); //holds the String input
                System.out.println("The battery life of the " + machines[i].getName() + " for a " + in + " workload is: " + ((Laptop)machines[i]).batteryLife(in) + " hours"); //prints out the unique method for Laptop, calculating battery life
                System.out.println("");//again, this is just to create separation between text
            }else if(s.equals("GamingLaptop")){ //this checks to see if the class name of the object matches GamingLaptop, and if it does, then we can cast the object to that class
                System.out.println("Overclocking your " + machines[i].getName()); //tells the user what the program is doing
                ((GamingLaptop)machines[i]).overclock(); //runs the code for the unique method belonging to this class
                System.out.println("");//again, text separation
            }
            
        }
        

    }

}
