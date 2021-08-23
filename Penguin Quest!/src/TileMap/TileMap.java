package TileMap;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap {
    
    //position
    private double x;
    private double y;

    //bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    private double tween; //smoothly scrolls camera, but not fully implemented

    //map
    private int[][] map;
    private int tileSize; //number of pixels for the tile (we are using square tiles, declared in constructor)
    private int numRows; //how many rows are in the tilemap
    //this is the second line of the tilemap
    private int numCols; //how many cols are in the tilemap 
    //This is the first line of the tilemap
    private int width;
    private int height;

    //tileset
    private BufferedImage tileset;
    private int numTilesAcross; //how many tiles are in each row, calculated in the functions
    private int numTilesDown; //how many rows are in the tileset, also calculated automatically
    private Tile[][] tiles;

    //drawing
    private int rowOffset;//which row to start drawing
    private int colOffset;//same for columns
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize){
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        tween = 1; //not working completely yet
    }

    public void loadTiles(String s) {
        try{
            tileset = ImageIO.read(getClass().getResourceAsStream(s));
            numTilesAcross = tileset.getWidth() / tileSize;
            numTilesDown = tileset.getHeight() / tileSize;
            tiles = new Tile[numTilesDown][numTilesAcross];

            BufferedImage subimage;
            for(int col = 0; col < numTilesAcross; col++){

                for(int row = 0; row < numTilesDown; row++){
                    subimage = tileset.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
                    if(row == 0){ //the first row of tiles are simply decoration
                        tiles[row][col] = new Tile(subimage, Tile.NORMAL);
                    } else if(row == 1){ //the second row of tiles provide collision with the player
                        tiles[row][col] = new Tile(subimage, Tile.BLOCKED);
                    }
                    //more else ifs possible if we have different kinds of blocks for more rows
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String s) {
        try{
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;

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

    public int getTileSize(){
        return tileSize;

    }
    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getType(int row, int col) {
        int rc = map[row][col];
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;
        return tiles[r][c].getType();
    }

    public void setTween(double d) { 
        tween = d; 
    }

    public void setPosition(double x, double y) {
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();

        colOffset = (int)-this.x / tileSize;
        rowOffset = (int)-this.y / tileSize;
    }

    private void fixBounds() {
        if (x < xmin) x = xmin;
        if (y < ymin) y = ymin;
        if (x > xmax) x = xmax;
        if (y > ymax) y = ymax;
    }

    public void draw(Graphics2D g) {
        for(int row = rowOffset; row < rowOffset + numRowsToDraw; row ++){
            if (row >= numRows){
                break;
            }
            for(int col = colOffset; col < colOffset + numColsToDraw; col++){
                if(col >= numCols) {
                    break;
                }
                if(map[row][col] == 0) {
                    continue;
                }

                int rc = map[row][col];
                int r = rc/numTilesAcross;
                int c = rc % numTilesAcross;

                g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);

            }
        }
    }

}

