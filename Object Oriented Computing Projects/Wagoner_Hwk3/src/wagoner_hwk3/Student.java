/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagoner_hwk3;

/**
 *
 * @author mitth
 */
public class Student {
    
    private String name;
    private String courseName;
    private double exam1;
    private double exam2;
    private double exam3; //declases the variables to be used in the class
    
    public Student(String n, String cn) { //name and classname are necessary when initializing the object
        name = n;
        courseName = cn;
        exam1 = 0;
        exam2 = 0;
        exam3 = 0;
        
    }
    
    public String getName(){
        return name;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setExam1(double grade) {
        exam1 = grade;
    }
    
    public void setExam2(double grade) {
        exam2 = grade;
    }
    
    public void setExam3(double grade) {
        exam3 = grade;
    }
    
    public double getExam1() {
        return exam1;
    }
    
    public double getExam2() {
        return exam2;
    }
    
    public double getExam3() {
        return exam3;
    }
    
    private char calcGrade() { //calculates the student's grade based off their average grade on the exams
        char grade;
        
        double avg = (exam1 + exam2 + exam3)/3;
        if(avg >= 90) {
            grade = 'A';
        } else if(avg >= 80) {
            grade = 'B';
        } else if(avg >= 70) {
            grade = 'C';
        } else if(avg >= 60) {
            grade = 'D';
        } else {
            grade = 'E';
        }
        
        return grade;
    }
    
    public String toString() { //only prints out the student's name, class, and grade. The exam specific grades aren't necessary since they were just used to calculate the letter grade
        String s;
        char grade;
        grade = calcGrade();
        s = "Student: " + name + 
                "\n\tCourse Name: " + courseName + 
                "\n\tGrade: " + grade;
        return s;
    }
    
}
