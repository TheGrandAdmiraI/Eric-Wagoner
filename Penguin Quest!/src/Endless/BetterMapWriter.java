package Endless;

import java.io.*;
import java.util.Random;

public class BetterMapWriter {

    private String levelMap = "48\n8\n";


    Random rand = new Random();
    int r1;
    int r2;
    int r3;
    int r4;

    File file = new File("Resources/Maps/endless.map");

    public BetterMapWriter() throws IOException {
        generateBlocks();
        makeLine1();
        makeLine2();
        makeLine3();
        makeLine4();
        makeLine5();
        makeLine6();
        makeLine7();
        makeLine8();
        toFile();
    }

    private void generateBlocks() {
        r1 = rand.nextInt(5);
        r2 = rand.nextInt(5);
        r3 = rand.nextInt(5);
        r4 = rand.nextInt(5);
        
        while(r2 == r1) {
            r2 = rand.nextInt(5);
        }
        while(r3 == r2) {
            r3 = rand.nextInt(5);
        }
        while(r4 == r3) {
            r4 = rand.nextInt(5);
        }

    }

    private void makeLine1() {
        addString(B.startLine1);
        switch (r1) {
            case 0:
                addString(B.block0Line1);
                break;
            case 1:
                addString(B.block1Line1);
                break;
            case 2:
                addString(B.block2Line1);
                break;
            case 3:
                addString(B.block3Line1);
                break;
            case 4:
                addString(B.block4Line1);
                break;
        }
        switch (r2) {
            case 0:
                addString(B.block0Line1);
                break;
            case 1:
                addString(B.block1Line1);
                break;
            case 2:
                addString(B.block2Line1);
                break;
            case 3:
                addString(B.block3Line1);
                break;
            case 4:
                addString(B.block4Line1);
                break;
        }
        switch (r3) {
            case 0:
                addString(B.block0Line1);
                break;
            case 1:
                addString(B.block1Line1);
                break;
            case 2:
                addString(B.block2Line1);
                break;
            case 3:
                addString(B.block3Line1);
                break;
            case 4:
                addString(B.block4Line1);
                break;
        }
        switch (r4) {
            case 0:
                addString(B.block0Line1);
                break;
            case 1:
                addString(B.block1Line1);
                break;
            case 2:
                addString(B.block2Line1);
                break;
            case 3:
                addString(B.block3Line1);
                break;
            case 4:
                addString(B.block4Line1);
                break;
        }
        addString(B.endLine1);
    }

    private void makeLine2() {
        addString(B.startLine2);
        switch (r1) {
            case 0:
                addString(B.block0Line2);
                break;
            case 1:
                addString(B.block1Line2);
                break;
            case 2:
                addString(B.block2Line2);
                break;
            case 3:
                addString(B.block3Line2);
                break;
            case 4:
                addString(B.block4Line2);
                break;
        }
        switch (r2) {
            case 0:
                addString(B.block0Line2);
                break;
            case 1:
                addString(B.block1Line2);
                break;
            case 2:
                addString(B.block2Line2);
                break;
            case 3:
                addString(B.block3Line2);
                break;
            case 4:
                addString(B.block4Line2);
                break;
        }
        switch (r3) {
            case 0:
                addString(B.block0Line2);
                break;
            case 1:
                addString(B.block1Line2);
                break;
            case 2:
                addString(B.block2Line2);
                break;
            case 3:
                addString(B.block3Line2);
                break;
            case 4:
                addString(B.block4Line2);
                break;
        }
        switch (r4) {
            case 0:
                addString(B.block0Line2);
                break;
            case 1:
                addString(B.block1Line2);
                break;
            case 2:
                addString(B.block2Line2);
                break;
            case 3:
                addString(B.block3Line2);
                break;
            case 4:
                addString(B.block4Line2);
                break;
        }
        addString(B.endLine2);
    }

    private void makeLine3() {
        addString(B.startLine3);
        switch (r1) {
            case 0:
                addString(B.block0Line3);
                break;
            case 1:
                addString(B.block1Line3);
                break;
            case 2:
                addString(B.block2Line3);
                break;
            case 3:
                addString(B.block3Line3);
                break;
            case 4:
                addString(B.block4Line3);
                break;
        }
        switch (r2) {
            case 0:
                addString(B.block0Line3);
                break;
            case 1:
                addString(B.block1Line3);
                break;
            case 2:
                addString(B.block2Line3);
                break;
            case 3:
                addString(B.block3Line3);
                break;
            case 4:
                addString(B.block4Line3);
                break;
        }
        switch (r3) {
            case 0:
                addString(B.block0Line3);
                break;
            case 1:
                addString(B.block1Line3);
                break;
            case 2:
                addString(B.block2Line3);
                break;
            case 3:
                addString(B.block3Line3);
                break;
            case 4:
                addString(B.block4Line3);
                break;
        }
        switch (r4) {
            case 0:
                addString(B.block0Line3);
                break;
            case 1:
                addString(B.block1Line3);
                break;
            case 2:
                addString(B.block2Line3);
                break;
            case 3:
                addString(B.block3Line3);
                break;
            case 4:
                addString(B.block4Line3);
                break;
        }
        addString(B.endLine3);
    }

    private void makeLine4() {
        addString(B.startLine4);
        switch (r1) {
            case 0:
                addString(B.block0Line4);
                break;
            case 1:
                addString(B.block1Line4);
                break;
            case 2:
                addString(B.block2Line4);
                break;
            case 3:
                addString(B.block3Line4);
                break;
            case 4:
                addString(B.block4Line4);
                break;
        }
        switch (r2) {
            case 0:
                addString(B.block0Line4);
                break;
            case 1:
                addString(B.block1Line4);
                break;
            case 2:
                addString(B.block2Line4);
                break;
            case 3:
                addString(B.block3Line4);
                break;
            case 4:
                addString(B.block4Line4);
                break;
        }
        switch (r3) {
            case 0:
                addString(B.block0Line4);
                break;
            case 1:
                addString(B.block1Line4);
                break;
            case 2:
                addString(B.block2Line4);
                break;
            case 3:
                addString(B.block3Line4);
                break;
            case 4:
                addString(B.block4Line4);
                break;
        }
        switch (r4) {
            case 0:
                addString(B.block0Line4);
                break;
            case 1:
                addString(B.block1Line4);
                break;
            case 2:
                addString(B.block2Line4);
                break;
            case 3:
                addString(B.block3Line4);
                break;
            case 4:
                addString(B.block4Line4);
                break;
        }
        addString(B.endLine4);
    }

    private void makeLine5() {
        addString(B.startLine5);
        switch (r1) {
            case 0:
                addString(B.block0Line5);
                break;
            case 1:
                addString(B.block1Line5);
                break;
            case 2:
                addString(B.block2Line5);
                break;
            case 3:
                addString(B.block3Line5);
                break;
            case 4:
                addString(B.block4Line5);
                break;
        }
        switch (r2) {
            case 0:
                addString(B.block0Line5);
                break;
            case 1:
                addString(B.block1Line5);
                break;
            case 2:
                addString(B.block2Line5);
                break;
            case 3:
                addString(B.block3Line5);
                break;
            case 4:
                addString(B.block4Line5);
                break;
        }
        switch (r3) {
            case 0:
                addString(B.block0Line5);
                break;
            case 1:
                addString(B.block1Line5);
                break;
            case 2:
                addString(B.block2Line5);
                break;
            case 3:
                addString(B.block3Line5);
                break;
            case 4:
                addString(B.block4Line5);
                break;
        }
        switch (r4) {
            case 0:
                addString(B.block0Line5);
                break;
            case 1:
                addString(B.block1Line5);
                break;
            case 2:
                addString(B.block2Line5);
                break;
            case 3:
                addString(B.block3Line5);
                break;
            case 4:
                addString(B.block4Line5);
                break;
        }
        addString(B.endLine5);
    }

    private void makeLine6() {
        addString(B.startLine6);
        switch (r1) {
            case 0:
                addString(B.block0Line6);
                break;
            case 1:
                addString(B.block1Line6);
                break;
            case 2:
                addString(B.block2Line6);
                break;
            case 3:
                addString(B.block3Line6);
                break;
            case 4:
                addString(B.block4Line6);
                break;
        }
        switch (r2) {
            case 0:
                addString(B.block0Line6);
                break;
            case 1:
                addString(B.block1Line6);
                break;
            case 2:
                addString(B.block2Line6);
                break;
            case 3:
                addString(B.block3Line6);
                break;
            case 4:
                addString(B.block4Line6);
                break;
        }
        switch (r3) {
            case 0:
                addString(B.block0Line6);
                break;
            case 1:
                addString(B.block1Line6);
                break;
            case 2:
                addString(B.block2Line6);
                break;
            case 3:
                addString(B.block3Line6);
                break;
            case 4:
                addString(B.block4Line6);
                break;
        }
        switch (r4) {
            case 0:
                addString(B.block0Line6);
                break;
            case 1:
                addString(B.block1Line6);
                break;
            case 2:
                addString(B.block2Line6);
                break;
            case 3:
                addString(B.block3Line6);
                break;
            case 4:
                addString(B.block4Line6);
                break;
        }
        addString(B.endLine6);
    }

    private void makeLine7() {
        addString(B.startLine7);
        switch (r1) {
            case 0:
                addString(B.block0Line7);
                break;
            case 1:
                addString(B.block1Line7);
                break;
            case 2:
                addString(B.block2Line7);
                break;
            case 3:
                addString(B.block3Line7);
                break;
            case 4:
                addString(B.block4Line7);
                break;
        }
        switch (r2) {
            case 0:
                addString(B.block0Line7);
                break;
            case 1:
                addString(B.block1Line7);
                break;
            case 2:
                addString(B.block2Line7);
                break;
            case 3:
                addString(B.block3Line7);
                break;
            case 4:
                addString(B.block4Line7);
                break;
        }
        switch (r3) {
            case 0:
                addString(B.block0Line7);
                break;
            case 1:
                addString(B.block1Line7);
                break;
            case 2:
                addString(B.block2Line7);
                break;
            case 3:
                addString(B.block3Line7);
                break;
            case 4:
                addString(B.block4Line7);
                break;
        }
        switch (r4) {
            case 0:
                addString(B.block0Line7);
                break;
            case 1:
                addString(B.block1Line7);
                break;
            case 2:
                addString(B.block2Line7);
                break;
            case 3:
                addString(B.block3Line7);
                break;
            case 4:
                addString(B.block4Line7);
                break;
        }
        addString(B.endLine7);
    }

    private void makeLine8() {
        addString(B.startLine8);
        switch (r1) {
            case 0:
                addString(B.block0Line8);
                break;
            case 1:
                addString(B.block1Line8);
                break;
            case 2:
                addString(B.block2Line8);
                break;
            case 3:
                addString(B.block3Line8);
                break;
            case 4:
                addString(B.block4Line8);
                break;
        }
        switch (r2) {
            case 0:
                addString(B.block0Line8);
                break;
            case 1:
                addString(B.block1Line8);
                break;
            case 2:
                addString(B.block2Line8);
                break;
            case 3:
                addString(B.block3Line8);
                break;
            case 4:
                addString(B.block4Line8);
                break;
        }
        switch (r3) {
            case 0:
                addString(B.block0Line8);
                break;
            case 1:
                addString(B.block1Line8);
                break;
            case 2:
                addString(B.block2Line8);
                break;
            case 3:
                addString(B.block3Line8);
                break;
            case 4:
                addString(B.block4Line8);
                break;
        }
        switch (r4) {
            case 0:
                addString(B.block0Line8);
                break;
            case 1:
                addString(B.block1Line8);
                break;
            case 2:
                addString(B.block2Line8);
                break;
            case 3:
                addString(B.block3Line8);
                break;
            case 4:
                addString(B.block4Line8);
                break;
        }
        addString(B.endLine8);
    }

    private void addString(String line) {
        levelMap = levelMap.concat(line);
    }

    private void toFile() throws IOException {
        file.createNewFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(levelMap);
        }
    }

}
