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
    public Brick[][] hartaBricksObiecte;

    int maxWorldCol = 13;
    int maxWorldRow = 42;

    Ball ball;

    public BrickPlacer(GamePanel gp,Ball ball) {
        this.gp = gp;
        this.ball=ball;
        this.hartaBricksObiecte = new Brick[42][13];
        loadBrickModel("/Bricks/map1.txt");
//        hartaBricks = new int[43][14];
//        hartaBricksObiecte = new Brick[43][14];


    }

//    public void fillBricksArray() {
//        bricks[0] = new EmptyBrick();
//        bricks[1] = new YellowBrick(ball);
//        bricks[2] = new RedBrick(ball);
//        bricks[3] = new BlueBrick(ball);
//    }

    public void loadBrickModel(String filepath) {
        //42 pe 13

        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < maxWorldCol && row < maxWorldRow) {
                String line = br.readLine();

                while (col < maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    switch (num) {
                        case 0 -> {
                            hartaBricksObiecte[row][col] = new EmptyBrick();
                        }
                        case 1 -> {
                            hartaBricksObiecte[row][col] = new YellowBrick(ball);
                        }
                        case 2 -> {
                            hartaBricksObiecte[row][col] = new RedBrick(ball);
                        }
                        case 3 -> {
                            hartaBricksObiecte[row][col] = new BlueBrick(ball);
                        }
                    }
                    col++;
                }


                if (col == maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }


    }

    public void draw(Graphics2D g2) {

        int brickWidth = 1920 / 10;  // Calculăm lățimea fiecărui brick
        int brickHeight = 1200 / 10;  // Calculăm înălțimea fiecărui brick

        for(int i=1;i<=41;i++)
        {
            for(int j=1;j<=12;j++)
            {
                int worldX = j * brickWidth;  // Calculăm poziția X a fiecărui brick
                int worldY = i * brickHeight;  // Calculăm poziția Y a fiecărui brick
               // System.out.println(worldX);
               // System.out.println(worldY);
                hartaBricksObiecte[i][j].draw(g2,worldX,worldY);
            }
            //System.out.println("\n");
        }
    }

    public void update()
    {
        for(int i=1;i<=41;i++)
        {
            for(int j=1;j<=12;j++)
            {
               // hartaBricksObiecte[i][j].update();
                System.out.println(hartaBricksObiecte[i][j].solidArea);
            }

        }

    }


}
