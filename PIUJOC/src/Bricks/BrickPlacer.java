package Bricks;

import Classes.Ball;
import Classes.GamePanel;
import Parents.Brick;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class BrickPlacer {

    GamePanel gp;
    public Brick[] bricks;
    public Brick[][] hartaBricksObiecte;
    Dimension screensize;
    int maxWorldCol = 13;
    int maxWorldRow = 12;
    Random random = new Random();
    Ball ball;

    public BrickPlacer(GamePanel gp,Ball ball,Dimension screensize) {
        this.gp = gp;
        this.ball=ball;
        this.hartaBricksObiecte = new Brick[12][13];
        this.screensize = screensize;
        int randomNum = random.nextInt(1,5);
        loadBrickModel("/Bricks/map"+randomNum+".txt");
    }


    public void loadBrickModel(String filepath) {
        //12 pe 13

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
                            hartaBricksObiecte[row][col] = new EmptyBrick(ball);
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

        int cols=13;
        int rows=12;
        int brickWidth = screensize.width / cols;
        int brickHeight = screensize.height / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int worldX = j * brickWidth;
                int worldY = i * brickHeight;
                hartaBricksObiecte[i][j].draw(g2,worldX,worldY);
                }
            }
        }


    public void update()
    {
        for(int i=0;i<12;i++)
        {
            for(int j=0;j<13;j++)
            {
                hartaBricksObiecte[i][j].update();
            }
        }
    }
}
