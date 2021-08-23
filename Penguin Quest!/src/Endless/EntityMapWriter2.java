package Endless;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class EntityMapWriter2 {

    private String levelMap;

    //private Random rand; //not used in entity writer
    private int[] blocks;
    private int r1;
    private int r2;
    private int r3;
    private int r4;

    private int levelNumber;
    private int numCols;

    private boolean bossLevel = false;

    private File file;// = new File("Resources/Maps/endless.map");

    public EntityMapWriter2(String s, int[] variables, int length, int level) throws IOException {
        levelNumber = level;
        file = new File(s);
        //This part seems useless since it will already create a file for us.
        if(!file.exists()){
            file.createNewFile();
        }
        //rand = new Random();
        blocks = variables;
        //generateBlocks(); //don't need to generate blocks since those are being passed by the mapwriter
        
        //if its a boss level, then we need to add 10 more columns
        //but first, is it a bosslevel?
        if(levelNumber%5 == 0){
            bossLevel = true;
        }
        if(bossLevel){
            numCols = 8 + 10*length + 10;
        } else {
            numCols = 8 + 10*length;
        }
        
        levelMap = numCols + "\n8\n";
        for (int line = 0; line < 8; line++) {
            writeStart(line);
            for(int b = 0; b < blocks.length; b++){
                writeBlock(blocks[b], line);
            }
            //if its a boss level, we want the boss to be right before the end
            if(bossLevel){
                writeBoss(line);
            }
            writeEnd(line);
        }
        toFile();
    }

    private void writeBlock(int r, int line) {
        switch (r) {
            case 0:
                for (int i = 0; i < 10; i++) {
                    addString(E.block0[line][i]);
                }
                break;
            case 1:
                for (int i = 0; i < 10; i++) {
                    addString(E.block1[line][i]);
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    addString(E.block2[line][i]);
                }
                break;
            case 3:
                for (int i = 0; i < 10; i++) {
                    addString(E.block3[line][i]);
                }
                break;
            case 4:
                for (int i = 0; i < 10; i++) {
                    addString(E.block4[line][i]);
                }
                break;
            case 5:
                for (int i = 0; i < 10; i++) {
                    addString(E.block5[line][i]);
                }
                break;
            case 6:
                for (int i = 0; i < 10; i++) {
                    addString(E.block6[line][i]);
                }
                break;
            case 7:
                for (int i = 0; i < 10; i++) {
                    addString(E.block7[line][i]);
                }
                break;
            case 8:
                for (int i = 0; i < 10; i++) {
                    addString(E.block8[line][i]);
                }
                break;
            case 9:
                for (int i = 0; i < 10; i++) {
                    addString(E.block9[line][i]);
                }
                break;
            case 10:
                for (int i = 0; i < 10; i++) {
                    addString(E.block10[line][i]);
                }
            break;
            case 11:
                for (int i = 0; i < 10; i++) {
                    addString(E.block11[line][i]);
                }
                break;
            case 12:
                for (int i = 0; i < 10; i++) {
                    addString(E.block12[line][i]);
                }
                break;
            case 13:
                for (int i = 0; i < 10; i++) {
                    addString(E.block13[line][i]);
                }
            break;
            case 14:
                for (int i = 0; i < 10; i++) {
                    addString(E.block14[line][i]);
                }
            break;
            case 15:
                for (int i = 0; i < 10; i++) {
                    addString(E.block15[line][i]);
                }
            break;
            case 16:
                for (int i = 0; i < 10; i++) {
                    addString(E.block16[line][i]);
                }
            break;
            case 17:
                for (int i = 0; i < 10; i++) {
                    addString(E.block17[line][i]);
                }
            break;
            case 18:
                for (int i = 0; i < 10; i++) {
                    addString(E.block18[line][i]);
                }
            break;
            case 19:
                for (int i = 0; i < 10; i++) {
                    addString(E.block19[line][i]);
                }
            break;
            case 20:
                for (int i = 0; i < 10; i++) {
                    addString(E.block20[line][i]);
                }
            break;
        }
    }

    private void writeStart(int line) {
        for (int i = 0; i < 4; i++) {
            addString(E.start[line][i]);
        }
    }

    private void writeEnd(int line) {
        for (int i = 0; i < 4; i++) {
            addString(E.end[line][i]);
        }
    }

    private void writeBoss(int line){
        for (int i = 0; i < 10; i++) {
            addString(E.boss[line][i]);
        }
    }

    private void addString(String line) {
        levelMap = levelMap.concat(line);
    }

    /*
    //not used in entity writer
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
*/
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
}