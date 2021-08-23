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
public abstract class Computer {

    //these are the basic components required for every computer
    private String name;
    private String os;
    private String cpu;
    private String gpu;
    private int ramGB;
    private int storageGB;

    public Computer(String n) {
        name = n;
    }

    public Computer(String n, String o, String c, String g, int r, int s) {
        name = n;
        os = o;
        cpu = c;
        gpu = g;
        ramGB = r;
        storageGB = s;

    }

    public String getName() {
        return name;
    }

    public String getOS() {
        return os;
    }

    public String getCpu() {
        return cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public int getRamGB() {
        return ramGB;
    }

    public int getStorageGB() {
        return storageGB;
    }

    public void setName(String n) {
        name = n;
    }

    public void setOS(String o) {
        os = o;
    }

    public void setCpu(String c) {
        cpu = c;
    }

    public void setGpu(String g) {
        gpu = g;
    }

    public void setRamGB(int ram) {
        ramGB = ram;
    }

    public void setStorageGB(int storage) {
        storageGB = storage;
    }

    public String toString() {
        String s = "Computer name: " + name
                + "\nOperating System: " + os
                + "\nProcessor: " + cpu
                + "\nGraphics: " + gpu
                + "\nRam: " + ramGB + "GB"
                + "\nStorage: " + storageGB + "GB";
        return s;
    }

}
