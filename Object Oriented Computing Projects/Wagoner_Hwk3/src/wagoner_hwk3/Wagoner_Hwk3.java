/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagoner_hwk3;

import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

;

/**
 *
 * @author Eric Wagoner
 */
public class Wagoner_Hwk3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        ArrayList<Student> students = new ArrayList(); //create the array list to store the objects of class Student
        Scanner fileIn = new Scanner(new File("bonus.txt")); //scanner for reading from a file
        Scanner input = new Scanner(System.in); //scanner for receiving input from keyboard

        String name;
        String courseName;
        double exam1;
        double exam2;
        double exam3; //variables corresponding to object variables
            
        while (fileIn.hasNext()) { //repeats until end of file is reached
            
            name = fileIn.next();
            courseName = fileIn.next();
            exam1 = fileIn.nextDouble();
            exam2 = fileIn.nextDouble();
            exam3 = fileIn.nextDouble();
            Student person = new Student(name, courseName); //creates an object with the information from one line of the file
            person.setExam1(exam1);
            person.setExam2(exam2);
            person.setExam3(exam3); //sets the exam grades for that object
            students.add(person); //adds the object to the end of the array to be used later

        }
        fileIn.close(); //always gotta remember to close the file

        for (int i = 0; i < students.size(); i++) { //looks at all the objects in the array
            if (students.get(i).getCourseName().compareTo("CSI1000") == 0) { //prints out all the students who's class is csi1000
                System.out.println(students.get(i).toString());
            }

        }

        for (int i = 0; i < students.size(); i++) { //looks at all the objects in the array again, but this time prints out the students in class csi2000
            if (students.get(i).getCourseName().compareTo("CSI2000") == 0) {
                System.out.println(students.get(i).toString());
            }

        }

        for (int i = 0; i < students.size(); i++) { //same as the previous 2 loops but for csi3000
            if (students.get(i).getCourseName().compareTo("CSI3000") == 0) {
                System.out.println(students.get(i).toString());
            }

        }

        System.out.println("Enter course name");
        String course = input.nextLine(); //stores input

        //System.out.println("Students in that course");
        double avg1 = 0;
        double avg2 = 0;
        double avg3 = 0;
        int count = 0;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getCourseName().compareToIgnoreCase(course) == 0) { //looks at all the students who are in the class inputed earlier
                System.out.println(students.get(i).getName());//prints out the student's name
                avg1 += students.get(i).getExam1();
                avg2 += students.get(i).getExam2();
                avg3 += students.get(i).getExam3();//these lines keep track of the grade scores in the class
                count++; //keeps track of the number of students in that class
            }

        }
        avg1 = avg1/count;
        avg2 = avg2/count;
        avg3 = avg3/count; //finished calculating the average exam scores
        DecimalFormat fmt = new DecimalFormat("#0.0"); //formats to 1 decimal place
        if (count == 0) { //if count = 0, then there were no students in the class, which means the user input an invalid class name
            System.out.println("Invalid course name");
        } else {
            System.out.println(count + " students in " + course); //prints out number of students in the class
            System.out.println("Average exam grades for " + course //prints out the average exam scores for the class
                    + "\nExam 1: " + fmt.format(avg1)
                    + "\nExam 2: " + fmt.format(avg2)
                    + "\nExam 3: " + fmt.format(avg3));
        }

    }

}
