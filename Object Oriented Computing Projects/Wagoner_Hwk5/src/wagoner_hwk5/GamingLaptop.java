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
public class GamingLaptop extends Laptop{
    
    private int refreshRate; //in Hz
    private boolean hasRGB;
    
    public GamingLaptop(String n){
        super(n);
    }
    
    public GamingLaptop(String n, String o, String c, String g, int r, int s, int b, boolean w, int rr, boolean rgb) {
        super(n, o, c, g, r, s, b, w);
        refreshRate = rr;
        hasRGB = rgb;
    }
    
    public int getRefreshRate(){
        return refreshRate;
    }
    
    public boolean getHasRGB(){
        return hasRGB;
    }
    
    public void setRefreshRate(int rr){
        refreshRate = rr;
    }
    
    public void setHasRGB(boolean rgb) {
        hasRGB = rgb;
    }
    
    public void overclock(){ //While you can't actually overclock all gaming laptops, they generally have different fan profiles to increase performance if you're willing to deal with the extra noise
        System.out.println("Fan speed increasing...");
        System.out.println("Power levels rising...");
        System.out.println("CPU and GPU speeds climbing...");
        System.out.println("This system is now prepared for maximum performance.");
    }
    
    public String toString(){
        String s = super.toString() + 
                "\nScreen refresh rate: " + refreshRate + "Hz" +
                "\nHas RGB: " + hasRGB;
        return s;
    }
    
    
}
