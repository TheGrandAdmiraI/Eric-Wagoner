package Endless;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class EvenBetterMapWriter2 {

    private String levelMap; //this is the start of the map file

    private Random rand;
    private int[] blocks; //this array holds the randomized int values for each of the blocks for the level

    private int difficulty; //we will use the level number to determine the difficulty
    private int levelNumber;
    private int length;
    private boolean bossLevel = false;
    private int numCols;

    private EntityMapWriter2 entityMapWriter;

    private File file;// = new File("Resources/Maps/endless.map");

    public EvenBetterMapWriter2(String s, int level) throws IOException {
        levelNumber = level;
        file = new File(s + ".map");
        //This part seems useless since it will already create a file for us.
        if(!file.exists()){
            file.createNewFile();
        }
        rand = new Random();
        generateBlocks();
        //first we determine the size of the file and write that
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
            //if its a boss level, we want to add the boss right before the flag
            if(bossLevel){
                writeBoss(line);
            }
            writeEnd(line);
        }
        toFile();
        //we're going to write the entity file in the same class so the random variables are the same
        //we pass the random variables in the constructor
        entityMapWriter = new EntityMapWriter2(s + "entities.map", blocks, length, level);
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
            case 5:
                for (int i = 0; i < 10; i++) {
                    addString(A.block5[line][i]);
                }
                break;
            case 6:
                for (int i = 0; i < 10; i++) {
                    addString(A.block6[line][i]);
                }
                break;
            case 7:
                for (int i = 0; i < 10; i++) {
                    addString(A.block7[line][i]);
                }
                break;
            case 8:
                for (int i = 0; i < 10; i++) {
                    addString(A.block8[line][i]);
                }
                break;
            case 9:
                for (int i = 0; i < 10; i++) {
                    addString(A.block9[line][i]);
                }
                break;
            case 10:
                for (int i = 0; i < 10; i++) {
                    addString(A.block10[line][i]);
                }
                break;
            case 11:
                for (int i = 0; i < 10; i++) {
                    addString(A.block11[line][i]);
                }
                break;
            case 12:
                for (int i = 0; i < 10; i++) {
                    addString(A.block12[line][i]);
                }
                break;
            case 13:
                for (int i = 0; i < 10; i++) {
                    addString(A.block13[line][i]);
                }
            break;
            case 14:
                for (int i = 0; i < 10; i++) {
                    addString(A.block14[line][i]);
                }
            break;
            case 15:
                for (int i = 0; i < 10; i++) {
                    addString(A.block15[line][i]);
                }
            break;
            case 16:
                for (int i = 0; i < 10; i++) {
                    addString(A.block16[line][i]);
                }
            break;
            case 17:
                for (int i = 0; i < 10; i++) {
                    addString(A.block17[line][i]);
                }
            break;
            case 18:
                for (int i = 0; i < 10; i++) {
                    addString(A.block18[line][i]);
                }
            break;
            case 19:
                for (int i = 0; i < 10; i++) {
                    addString(A.block19[line][i]);
                }
            break;
            case 20:
                for (int i = 0; i < 10; i++) {
                    addString(A.block20[line][i]);
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

    private void writeBoss(int line){
        for (int i = 0; i < 10; i++) {
            addString(A.boss[line][i]);
        }
    }

    private void addString(String line) {
        levelMap = levelMap.concat(line);
    }

    private void generateBlocks() {
        //the difficulty will determine the randomization of the variables
        //System.out.println("\n\nlevel number: " + levelNumber);
//        difficulty = 10 + (10 * ((levelNumber - 1) / 5)); //increase difficulty by 10 after every boss level
        /*if(difficulty > 100){ 
            difficulty = 100; //max difficulty of 100
        }*///since we're increasing the number of blocks after every boss level, we don't really need to have a max difficulty
        //System.out.println("level difficulty: " + difficulty);
        
        //first we determine how many blocks will be generated for the level since we want the level length to change
//        length = 2 + difficulty/5; //default is 4 blocks, increase by 2 after every boss level
        //difficulty increases by increments of 10. 10/5 = 2. Starts at 10 difficulty


        //what if we did this instead?
        length = 4 + 2 * ((levelNumber - 1) / 5); //default is 4 block length, increase by 2 after every boss level
        difficulty = ((4 * (levelNumber - 1) / 5) + 2) * length;//we want the difficulty to grow per block after every boss level, let's say starts with average of 2, then grows by 4 per block. Max avg of 20.
        if(difficulty > (10 * length)){ //max average of 20 difficulty per length because we have max of 20 difficulty block;
            difficulty = 20 * length;
        }


        //System.out.println("The level length is: " + length + " blocks");
        blocks = new int[length];
        //next we do the math to determine what the values of each block will be
        int blockDifficulty = 0;
        for(int i = 0; i < blocks.length; i++){
            //every 10 levels will be a boss level, still have yet to add that

            //each block has a difficulty rating = to its block number currently
            //with more than 1 difficulty left, we don't want each block to take up all the difficulty, so the max difficulty of each block is half the total left
            if(difficulty - blockDifficulty > 1){
                //we want the max number to either be half the difficulty left or the max number of blocks available
                blocks[i] = rand.nextInt(lesser(A.numberOfBlocksAvailable + 1, (difficulty - blockDifficulty) / 2 + 1)); //we do +1 since the randint is exclusive
            } else {
                blocks[i] = rand.nextInt(difficulty - blockDifficulty + 1);
            }
            //if we can choose between different blocks, then we want to make sure no two blocks next to each other are the same
            if(i > 0 && difficulty - blockDifficulty > 0){
                while(blocks[i-1] == blocks[i]){ //if the block before is equal to the one just selected, then we choose again.
                    //same math as before
                    if(difficulty - blockDifficulty > 1){
                        blocks[i] = rand.nextInt(lesser(A.numberOfBlocksAvailable + 1, (difficulty - blockDifficulty) / 2 + 1));
                    } else {
                        blocks[i] = rand.nextInt(difficulty - blockDifficulty + 1);
                    }
                }
            }
            blockDifficulty += blocks[i]; //the total difficulty of the blocks increases by the difficulty of the block just calculated
            //System.out.println(i + "th block chosen is: " + blocks[i]);
            //System.out.println("current block difficulty total: " + blockDifficulty);
        }

    }

    private int lesser(int x, int y){
        if(x < y){
            return x;
        } else{
            return y;
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
}