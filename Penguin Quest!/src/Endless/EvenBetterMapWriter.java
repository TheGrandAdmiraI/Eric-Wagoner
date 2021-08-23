package Endless;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class EvenBetterMapWriter {

    private String levelMap = "48\n8\n";

    private Random rand;
    private int r1;
    private int r2;
    private int r3;
    private int r4;

    private File file;// = new File("Resources/Maps/endless.map");

    public EvenBetterMapWriter(String s) throws IOException {
        file = new File(s);
        rand = new Random();
        generateBlocks();
        for (int line = 0; line < 8; line++) {
            writeStart(line);
            writeBlock(r1, line);
            writeBlock(r2, line);
            writeBlock(r3, line);
            writeBlock(r4, line);
            writeEnd(line);
        }
        toFile();
    }

    private void writeBlock(int r, int line) {
        switch (r) {
            case 0:
                for (int i = 0; i < 10; i++) {
                    addString(A.block0[line][i]);
                }
                break;
            case 1:
                for (int i = 0; i < 10; i++) {
                    addString(A.block1[line][i]);
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    addString(A.block2[line][i]);
                }
                break;
            case 3:
                for (int i = 0; i < 10; i++) {
                    addString(A.block3[line][i]);
                }
                break;
            case 4:
                for (int i = 0; i < 10; i++) {
                    addString(A.block4[line][i]);
                }
                break;

        }
    }

    private void writeStart(int line) {
        for (int i = 0; i < 4; i++) {
            addString(A.start[line][i]);
        }
    }

    private void writeEnd(int line) {
        for (int i = 0; i < 4; i++) {
            addString(A.end[line][i]);
        }
    }

    private void addString(String line) {
        levelMap = levelMap.concat(line);
    }

    private void generateBlocks() {
        r1 = rand.nextInt(5);
        r2 = rand.nextInt(5);
        r3 = rand.nextInt(5);
        r4 = rand.nextInt(5);

        while (r2 == r1) {
            r2 = rand.nextInt(5);
        }
        while (r3 == r2) {
            r3 = rand.nextInt(5);
        }
        while (r4 == r3) {
            r4 = rand.nextInt(5);
        }

    }

    private void toFile() throws IOException {
        file.createNewFile();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(levelMap);
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getR1() {
        return r1;
    }

    public int getR2() {
        return r2;
    }

    public int getR3() {
        return r3;
    }

    public int getR4() {
        return r4;
    }
}
