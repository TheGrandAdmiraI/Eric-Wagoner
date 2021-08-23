/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wagoner_hwk5;

/**
 *
 * @author Eric Wagoner
 */
public class Laptop extends Computer{
    
    private int battery; //in Wh
    private boolean webcam; //surprisingly, some laptops don't actually have webcams. One of the laptops I used in the main method doesn't
    
    
    public Laptop(String n) {
        super(n);
    }
    
    public Laptop(String n, String o, String c, String g, int r, int s, int b, boolean w) {
        super(n, o, c, g, r, s);
        battery = b;
        webcam = w;

    }
    
    public int getBattery(){
        return battery;
    }
    
    public boolean getWebcam(){
        return webcam;
    }
    
    public void setBattery(int b){
        battery = b;
    }
    
    public void setWebcam(boolean w) {
        webcam = w;
    }
    
    public double batteryLife(String workload){ //Calculates the battery life of the laptop for different work loads. (I just put in arbitrary values for the calculations, but the idea of heavier workloads = less battery life is true)
        double bl;
        if(workload.toUpperCase().equals("LIGHT")){
            bl = (double)battery/8;
        } else if(workload.toUpperCase().equals("HEAVY")){
            bl = (double)battery/32;
        } else {
            bl = (double)battery/16;
        }
        return bl;
    }
    
    public String toString(){
        String s = super.toString() +
                "\nBattery capacity: " + battery + "Wh" + 
                "\nHas a webcam: " + webcam;
        return s;
    }
    
    
    
}
