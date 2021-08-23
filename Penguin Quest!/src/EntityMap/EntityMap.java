package EntityMap;

import java.io.*;
import java.util.ArrayList;

import TileMap.*;
import Entity.*;
import Entity.PowerUps.*;
import Entity.Enemies.*;
import java.util.Random;

public class EntityMap {

    private String filename;
    
    private ArrayList<Enemy> enemies;
    private ArrayList<PowerUp> powerups;
    private ArrayList<Coin> coins;
    private ArrayList<Flag> flags;

    private int currentRow;
    private int currentCol;

    private int[][] map;
    private int numRows; //how many rows are in the tilemap
    //this is the second line of the tilemap
    private int numCols; //how many cols are in the tilemap 
    //This is the first line of the tilemap

    //these ints store which number corresponds to what entity in the file
    private static final int SEAL = 1;
    private static final int JUMPINGSEAL = 2;
    private static final int SEAGULL = 3;
    private static final int POLARBEAR = 4;
    private static final int BIGBOSS = 5;
    private static final int HEALTHBOOST = 11;
    private static final int SPEEDBOOST = 12;
    private static final int DOUBLEJUMP = 13;
    private static final int ICEBALLPOWERINCREASE = 14;
    private static final int ONEUP = 15;
    private static final int RANDOMPOWERUP = 19;
    private static final int COIN = 21;
    private static final int FLAG = 31;
    private static final int SPIKE = 41;
    private static final int REDSPIKE = 42;

    public EntityMap(String file){
        //empty
        filename = file;
        enemies = new ArrayList<Enemy>();
        powerups = new ArrayList<PowerUp>();
        coins = new ArrayList<Coin>();
        flags = new ArrayList<Flag>();

        loadMap();
    }

    public void loadMap(){
        //copied reading code form TileMap
        //turns the bulk of the file into a 2d array
        try{
            InputStream in = getClass().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];

            String delims = "\\s+";
            for(int row = 0; row < numRows; row++){ //not currently sure how this works, will need to figure out another time But I think it splits up each row into differentiable columns to set to the map array
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for(int col = 0; col < numCols; col++){
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEntities(TileMap tileMap, Player player){
        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numCols; c++){
                //depending on what value is in the 2d array, we spawn a certain entity there
                switch(map[r][c]){
                    case SEAL:
                        Seal s;
                        s = new Seal(tileMap);
                        s.setPosition((c)*30 + 15, (r)*30 + 15); //+15 so they spawn in the middle of the block instead of the top left corner
                        enemies.add(s);
                        break;
                    case JUMPINGSEAL:
                        JumpingSeal js;
                        js = new JumpingSeal(tileMap);
                        js.setPosition((c)*30 + 15, (r)*30 + 15);
                        enemies.add(js);
                        break;
                    case SEAGULL: 
                        Seagull sg;
                        sg = new Seagull(tileMap);
                        sg.setPosition((c)*30 + 15, (r)*30 + 15);
                        enemies.add(sg);
                        break;
                    case POLARBEAR: 
                        PolarBear pb;
                        pb = new PolarBear(tileMap);
                        pb.setPosition((c)*30 + 15, (r)*30 + 15);
                        enemies.add(pb);
                        break;
                    case BIGBOSS:
                        BigBoss bb;
                        bb = new BigBoss(tileMap);
                        bb.setPosition((c)*30 + 15, (r)*30 + 15);
                        enemies.add(bb);
                        break;
                    case HEALTHBOOST:
                        HealthBoost hb;
                        hb = new HealthBoost(tileMap, player);
                        hb.setPosition((c)*30 + 15, (r)*30 + 15);
                        powerups.add(hb);
                        break;
                    case SPEEDBOOST:
                        SpeedBoost sb;
                        sb = new SpeedBoost(tileMap, player);
                        sb.setPosition((c)*30 + 15, (r)*30 + 15);
                        powerups.add(sb);
                        break;
                    case DOUBLEJUMP:
                        DoubleJump dj;
                        dj = new DoubleJump(tileMap, player);
                        dj.setPosition((c)*30 + 15, (r)*30 + 15);
                        powerups.add(dj);
                        break;
                    case ICEBALLPOWERINCREASE:
                        FireballPowerIncrease fpi;
                        fpi = new FireballPowerIncrease(tileMap, player);
                        fpi.setPosition((c)*30 + 15, (r)*30 + 15);
                        powerups.add(fpi);
                        break;
                    case ONEUP:
                        OneUp ou;
                        ou = new OneUp(tileMap, player);
                        ou.setPosition((c)*30 + 15, (r)*30 + 15);
                        powerups.add(ou);
                        break;
                    case RANDOMPOWERUP: 
                        Random rand = new Random();
                        int r1 = rand.nextInt(4);
                        switch (r1) {
                            case 0:
                                HealthBoost hbr;
                                hbr = new HealthBoost(tileMap, player);
                                hbr.setPosition((c) * 30 + 15, (r) * 30 + 15);
                                powerups.add(hbr);
                                break;
                            case 1:
                                SpeedBoost sbr;
                                sbr = new SpeedBoost(tileMap, player);
                                sbr.setPosition((c) * 30 + 15, (r) * 30 + 15);
                                powerups.add(sbr);
                                break;
                            case 2:
                                DoubleJump djr;
                                djr = new DoubleJump(tileMap, player);
                                djr.setPosition((c) * 30 + 15, (r) * 30 + 15);
                                powerups.add(djr);
                                break;
                            case 3:
                                FireballPowerIncrease fpir;
                                fpir = new FireballPowerIncrease(tileMap, player);
                                fpir.setPosition((c) * 30 + 15, (r) * 30 + 15);
                                powerups.add(fpir);
                                break;
                        }
                        break;
                    case COIN:
                        Coin cn;
                        cn = new Coin(tileMap);
                        cn.setPosition((c)*30 + 15, (r)*30 + 15);
                        coins.add(cn);
                        break;
                    case FLAG:
                        Flag f;
                        f = new Flag(tileMap);
                        f.setPosition((c)*30 + 15, (r)*30 + 15);
                        flags.add(f);
                        break;
                    case SPIKE:
                        Spike sk;
                        sk = new Spike(tileMap);
                        sk.setPosition((c)*30 + 15, (r)*30 + 15);
                        enemies.add(sk); //spikes are a type of enemy
                        break;
                    case REDSPIKE:
                        RedSpike rsk;
                        rsk = new RedSpike(tileMap);
                        rsk.setPosition((c)*30 + 15, (r)*30 + 15);
                        enemies.add(rsk);
                        break;
                    default:
                    break;
                }
            }
        }
    }

    public ArrayList<Enemy> returnEnemies(){
        return enemies;
    }

    public ArrayList<PowerUp> returnPowerUps(){
        return powerups;
    }

    public ArrayList<Coin> returnCoins(){
        return coins;
    }

    public ArrayList<Flag> returnFlags(){
        return flags;
    }

}
