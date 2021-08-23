/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagoner_hwk4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Eric Wagoner 
 * Date: 3/20/20
 */
public class Wagoner_Hwk4 extends Application {

    //These variables need to be outside the methods because they are calculated in the main method and used in the start method for the graphic
    static double class1Avg;
    static double class2Avg;
    static int size1 = 0;
    static int size2 = 0;

    @Override
    public void start(Stage primaryStage) {

        final int WIDTH = 600;
        final int HEIGHT = 250;
        DecimalFormat decF = new DecimalFormat("0.0"); //This is so the displayed averages don't have a million digits

        Text wrd1 = new Text(20, 60, "Total Student Count: " + Student.getStudnetCount()); //Static methods are called with the class name
        Text wrd2 = new Text(20, 80, "Average grade of section 87649: " + decF.format(class1Avg));
        Text wrd3 = new Text(20, 100, "Average grade of section 12345: " + decF.format(class2Avg));
        Text wrd4 = new Text(20, 120, "Number of Students per section identified by color");
        
        wrd2.setFill(Color.BLUE); //The colors show which rectangle go with which section
        wrd3.setFill(Color.ORANGERED);

        Rectangle box1 = new Rectangle(20, 130, 10 * size1, 10); //The length of the rectangle is proportional to the number of students in that section
        Rectangle box2 = new Rectangle(20, 150, 10 * size2, 10); //Same for this rectangle
        box1.setFill(Color.BLUE); 
        box2.setFill(Color.ORANGERED);

        //This does all the stuff necessary to display the objects
        Group text = new Group(wrd1, wrd2, wrd3, wrd4);
        Group lines = new Group(box1, box2);
        Group root = new Group(lines, text);

        Scene aScene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Wagoner_Hwk4");

        primaryStage.setScene(aScene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //This is the array list to store all the students that will be read from the file
        ArrayList<Student> list = new ArrayList();

        Scanner FileIn = new Scanner(new File("hwk4students.txt")); //this lets us read from the file

        String name; //these variables correspond to the values we will be reading from the file
        String className;
        int section;
        double exam1;
        double exam2;
        double exam3;
        Student person;

        while (FileIn.hasNext()) {
            name = FileIn.next();
            className = FileIn.next();
            section = FileIn.nextInt();
            exam1 = FileIn.nextDouble();
            exam2 = FileIn.nextDouble();
            exam3 = FileIn.nextDouble();
            person = new Student(name, className, section); //each time the loop runs a new student object is created
            person.setExam1(exam1);
            person.setExam2(exam2);
            person.setExam3(exam3);
            list.add(person); //can't forget to add the object to the array to be used later

        }
        FileIn.close(); //can't forget this

        //this is just leftover code from debugging
//        for(int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).toString());
//            System.out.println("");
//        }
//        double class1Avg; //This needed to be moved outside the method so it could be called in the start method
//        double class2Avg;
        int sum1 = 0;
        int sum2 = 0;
//        int size1 = 0; //same for these variables
//        int size2 = 0;

        for (int i = 0; i < list.size(); i++) { //this loop runs through the array list and prints out all the students, and depending on which class they belong to, does math for calculating the average exam grade
            if (list.get(i).getSectionNum() == 87649) {
                System.out.println(list.get(i).toString());
                System.out.println("");

                sum1 += list.get(i).getAvgExamGrade();
                size1++;

            }

            if (list.get(i).getSectionNum() == 12345) {
                System.out.println(list.get(i).toString());
                System.out.println("");

                sum2 += list.get(i).getAvgExamGrade();
                size2++;
            }
        }
        //create 4 text objects

        class1Avg = (double) sum1 / size1;//calculate the exam averages for each class
        class2Avg = (double) sum2 / size2;

        
        //This was just some code I used when making sure I was getting the right values
//        System.out.println(size1);
//        System.out.println(size2);
//        
//        System.out.println("Total Student Count: " + Student.getStudnetCount());
//        System.out.println("Class 1 average grade: " + class1Avg);
//        System.out.println("Class 2 average grade: " + class2Avg);
        

        launch(args); //This causes the start method to launch and create the graphic.
        //The graphic needs to be generated at the end because it uses values calculated in this method

    }

}
