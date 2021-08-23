/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagoner_hwk4;

/**
 *
 * @author Eric Wagoner
 */
public class Student {
    
    private String name;
    private static int studentCount;
    private double exam1;
    private double exam2;
    private double exam3;
    private CourseInfo studentCourse;
    
    public Student(String n, String crsName, int crsSec) {
        name = n;
        studentCourse = new CourseInfo(crsName, crsSec);
        studentCount++;
    }
    
    public int getSectionNum(){
        return studentCourse.getSectNum();
    }
    
    public void setExam1(double e1) {
        exam1 = e1;
    }
    
    public void setExam2(double e2) {
        exam2 = e2;
    }
    
    public void setExam3(double e3) {
        exam3 = e3;
    }
    
    public String toString(){
        String s = "Name: " + name + 
                "\n" + studentCourse.toString() + 
                "\nAverage grade: " + getAvgExamGrade();
        return s;
    }
    
    public int getAvgExamGrade(){ //I'm not sure why this needs to be an int, but that's what the UML says
        double sum = exam1 + exam2 + exam3;
        double average = sum/3;
        return (int) average;
        
    }
    
    public static int getStudnetCount(){
        return studentCount;
    }
    
}
