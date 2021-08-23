package Endless;

import java.io.*;
import java.util.Random;

public class MapWriter {

    File file = new File("Resources/Maps/endless.map");

//    String[] block1Line1 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};
//    String[] block1Line2 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};
//    String[] block1Line3 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};
//    String[] block1Line4 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};
//    String[] block1Line5 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};
//    String[] block1Line6 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};
//    String[] block1Line7 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};
    
    String[] block0Line1 = {"00 ", "00 ", "00 ", "00 ", "08 ", "08 ", "08 ", "00 ", "00 ", "00 "};
    String[] block0Line2 = {"00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "08 ", "00 ", "00 ", "00 "};
    String[] block0Line3 = {"00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "08 ", "00 ", "00 ", "00 "};
    String[] block0Line4 = {"00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "08 ", "00 ", "00 ", "00 "};
    String[] block0Line5 = {"00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "08 ", "00 ", "00 ", "00 "};
    String[] block0Line6 = {"00 ", "00 ", "00 ", "00 ", "08 ", "08 ", "08 ", "00 ", "00 ", "00 "};
    String[] block0Line7 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};

    String[] block1Line1 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block1Line2 = {"00 ", "00 ", "00 ", "00 ", "08 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block1Line3 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block1Line4 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block1Line5 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block1Line6 = {"00 ", "00 ", "00 ", "00 ", "08 ", "08 ", "08 ", "00 ", "00 ", "00 "};
    String[] block1Line7 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};

    String[] block2Line1 = {"00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 ", "00 "};
    String[] block2Line2 = {"00 ", "00 ", "00 ", "08 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block2Line3 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block2Line4 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block2Line5 = {"00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 ", "00 "};
    String[] block2Line6 = {"00 ", "00 ", "00 ", "08 ", "08 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block2Line7 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};

    String[] block3Line1 = {"00 ", "00 ", "00 ", "08 ", "08 ", "00 ", "00 ", "00 ", "00 ", "00 "};
    String[] block3Line2 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block3Line3 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block3Line4 = {"00 ", "00 ", "00 ", "00 ", "08 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block3Line5 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block3Line6 = {"00 ", "00 ", "00 ", "08 ", "08 ", "00 ", "00 ", "00 ", "00 ", "00 "};
    String[] block3Line7 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};

    String[] block4Line1 = {"00 ", "00 ", "00 ", "08 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block4Line2 = {"00 ", "00 ", "00 ", "08 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block4Line3 = {"00 ", "00 ", "00 ", "08 ", "08 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block4Line4 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block4Line5 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block4Line6 = {"00 ", "00 ", "00 ", "00 ", "00 ", "08 ", "00 ", "00 ", "00 ", "00 "};
    String[] block4Line7 = {"00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 ", "00 "};

    public MapWriter() throws IOException {
        file.createNewFile();
        Random rand = new Random();
        int r1 = rand.nextInt(4);
        int r2 = rand.nextInt(4);
        int r3 = rand.nextInt(4);
        int r4 = rand.nextInt(4);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("42\n8\n");
            for (int i = 1; i <= 7; i++) {
                writer.write("22 ");
                switch (r1) {
                    case 0:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line7[j]);
                                }
                                break;
                        }
                        break;

                    case 1:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 3:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 4:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line7[j]);
                                }
                                break;
                        }
                        break;
                }
                switch (r2) {
                    case 0:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line7[j]);
                                }
                                break;
                        }
                        break;

                    case 1:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 3:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 4:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line7[j]);
                                }
                                break;
                        }
                        break;
                }

                switch (r3) {
                    case 0:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line7[j]);
                                }
                                break;
                        }
                        break;

                    case 1:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 3:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 4:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line7[j]);
                                }
                                break;
                        }
                        break;
                }
                switch (r4) {
                    case 0:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block0Line7[j]);
                                }
                                break;
                        }
                        break;

                    case 1:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block1Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block2Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 3:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block3Line7[j]);
                                }
                                break;
                        }
                        break;
                    case 4:
                        switch (i) {
                            case 1:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line1[j]);
                                }
                                break;
                            case 2:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line2[j]);
                                }
                                break;
                            case 3:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line3[j]);
                                }
                                break;
                            case 4:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line4[j]);
                                }
                                break;
                            case 5:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line5[j]);
                                }
                                break;
                            case 6:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line6[j]);
                                }
                                break;
                            case 7:
                                for (int j = 0; j < 10; j++) {
                                    writer.write(this.block4Line7[j]);
                                }
                                break;
                        }
                        break;
                }
                writer.write("22\n");
            }
            writer.write("22 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 21 22");

        }
    }

}