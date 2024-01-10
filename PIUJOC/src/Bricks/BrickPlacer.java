package Bricks;

import Classes.Ball;
import Classes.GamePanel;
import Parents.Brick;
import Bricks.BlueBrick;
import Bricks.YellowBrick;
import Bricks.RedBrick;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BrickPlacer {

    GamePanel gp;
    public Brick[] bricks;
    public int mapBricks[][];

    int maxWorldCol = 13;
    int maxWorldRow = 42;

    Ball ball;
    public BrickPlacer(GamePanel gp)
    {
            this.gp = gp;
            bricks = new Brick[3];
            mapBricks = new int[gp.window.getWidth()][gp.window.getHeight()];

    }
    public void fillBricksArray()
    {
        bricks[0]=new EmptyBrick();
        bricks[1]=new YellowBrick(ball);
        bricks[2]=new RedBrick(ball);
        bricks[3]=new BlueBrick(ball);
    }

    public void loadBrickModel(String filepath)
    {
            //42 pe 13
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br= new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col < maxWorldCol && row < maxWorldRow)
            {
                String line = br.readLine();

                while(col < maxWorldCol)
                {
                    String []numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapBricks[col][row] = num;
                    col++;
                }

                if (col == maxWorldCol)
                {
                    col=0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }



    }

    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;
        int tileHeight=144;
        int tileWidth=28;
        while(worldCol < maxWorldCol && worldRow < maxWorldRow) {
            int tileNum = mapBricks[worldCol][worldRow];

            int worldX = worldCol * tileHeight;
            int worldY = worldRow * tileWidth;
            //int screenX = worldX - gp.player.worldX + gp.player.screenX;
            //int screenY = worldY - gp.player.worldY + gp.player.screenY;

//            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
//                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                //g2.drawImage(bricks[tileNum], screenX, screenY, null);
            bricks[tileNum].draw(g2,worldX,worldY);
            //}

            worldCol++;

            if(worldCol == maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }



}
