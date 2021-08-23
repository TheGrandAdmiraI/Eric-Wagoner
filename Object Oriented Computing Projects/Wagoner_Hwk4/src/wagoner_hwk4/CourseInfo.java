/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagoner_hwk4;

/**
 *
 * @author Eric Wagoenr
 */
public class CourseInfo {
    
    private String courseName;
    private int sectNum;
    
    public CourseInfo(String cn, int sect) {
        courseName = cn;
        sectNum = sect;
    }
    
    public String toString(){
        String s = "course name: " + courseName + 
                "\nsection number: " + sectNum;
        return s;
        
    }
    
    public String getCourseName(){
        return courseName;
    }
    
    public int getSectNum(){
        return sectNum;
    }
    
}
