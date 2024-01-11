package Classes;

import Bricks.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class GamePanel extends JPanel implements Runnable{

    public JFrame window;
    final int originalTileSize =16;
    final int scale=3;
    final int tileSize = originalTileSize*scale;
    final int maxScreenCol=30;
    final int maxScreenRow=20;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public KeyHandler keyH = new KeyHandler(this);


    Wall leftWall,topWall,rightWall;

    Thread gameThread;
    Player player;
    Ball ball;
    BrickPlacer brickPlacer;

    public  UI ui = new UI(this);

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;

    //FPSJFrame frame;
    int FPS =60;

    //player position

    public void startGameThread()
    {

        gameThread=new Thread(this);
        gameThread.start();
    }
    public GamePanel(JFrame Window)
    {
        this.window=Window;

        this.setPreferredSize(new Dimension((int)screenSize.getWidth(),(int)screenSize.getHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.player = new Player(this,keyH);
        this.ball = new Ball(this);
        this.rightWall=new Wall((int)screenSize.getWidth()-30,0,30,(int)screenSize.getHeight());
        this.leftWall=new Wall(0,0,30,(int)screenSize.getHeight());
        this.topWall=new Wall(0,0,(int)screenSize.getWidth(),30);
        this.brickPlacer = new BrickPlacer(this,this.ball,screenSize);

    }

    public void setupGame() {
        gameState = titleState;
    }

    public void restart() {
        player.resetState();
        this.setPreferredSize(new Dimension((int)screenSize.getWidth(),(int)screenSize.getHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.player = new Player(this,keyH);
        this.ball = new Ball(this);
        this.rightWall=new Wall((int)screenSize.getWidth()-30,0,30,(int)screenSize.getHeight());
        this.leftWall=new Wall(0,0,30,(int)screenSize.getHeight());
        this.topWall=new Wall(0,0,(int)screenSize.getWidth(),30);
        this.brickPlacer = new BrickPlacer(this,this.ball,screenSize);

    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;

        while(null!=gameThread)
        {

            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            timer+=(currentTime-lastTime);
            lastTime=currentTime;

            if(delta>=1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer>=1000000000)
            {

                drawCount=0;
                timer=0;
            }

        }
    }


    public void update()
    {
        if(gameState==playState)
        {
            player.update();
            ball.update();
            brickPlacer.update();
            //checkCollision();


        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        BufferedImage bk;

        //TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        else{
            try {
                bk = ImageIO.read(getClass().getResourceAsStream("/Classes/backgroud.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            g2.drawImage(bk,0,0,(int)screenSize.getWidth(),(int)screenSize.getHeight(),null);

            player.draw(g2);

            ball.draw(g2);

            brickPlacer.draw(g2);
            topWall.draw(g2);
            leftWall.draw(g2);
            rightWall.draw(g2);


            //UI
            ui.draw(g2);
        }

        g2.dispose();
    }

   private void checkCollision() {

        if (ball.getRect().getMaxY() > this.screenSize.getHeight()-this.screenSize.getHeight()/8) {

            gameState=gameOverState;
        }

        if(brickPlacer.noOfBreakableBricks == 0)
        {
            gameState=gameOverState;
        }
}
//
//        if ((ball.solidArea).intersects(player.solidArea)) {
//
//            int paddleLPos = (int) player.solidArea.getMinX();
//            int ballLPos = (int) ball.solidArea.getMinX();
//
//            int first = paddleLPos + 8;
//            int second = paddleLPos + 16;
//            int third = paddleLPos + 24;
//            int fourth = paddleLPos + 32;
//
//            if (ballLPos < first) {
//
//                ball.setXDir(-1);
//                ball.setYDir(-1);
//            }
//
//            if (ballLPos >= first && ballLPos < second) {
//
//                ball.setXDir(-1);
//                ball.setYDir(-1 * ball.getYDir());
//            }
//
//            if (ballLPos >= second && ballLPos < third) {
//
//                ball.setXDir(0);
//                ball.setYDir(-1);
//            }
//
//            if (ballLPos >= third && ballLPos < fourth) {
//
//                ball.setXDir(1);
//                ball.setYDir(-1 * ball.getYDir());
//            }
//
//            if (ballLPos > fourth) {
//
//                ball.setXDir(1);
//                ball.setYDir(-1);
//            }
//        }
//
//        for (int i = 0; i < brickPlacer.noOfBreakableBricks; i++) {
//            for (int j = 0; j < brickPlacer.noOfBreakableBricks; j++)
//            if ((ball.getRect()).intersects(brickPlacer.hartaBricksObiecte[i][j].solidArea)) {
//
//                int ballLeft = (int) ball.getRect().getMinX();
//                int ballHeight = (int) ball.getRect().getHeight();
//                int ballWidth = (int) ball.getRect().getWidth();
//                int ballTop = (int) ball.getRect().getMinY();
//
//                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
//                var pointLeft = new Point(ballLeft - 1, ballTop);
//                var pointTop = new Point(ballLeft, ballTop - 1);
//                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
//
//                if (!brickPlacer.hartaBricksObiecte[i][j].isDestroyed()) {
//
//                    if (brickPlacer.hartaBricksObiecte[i][j].solidArea.contains(pointRight)) {
//
//                        ball.setXDir(-1);
//                    } else if (brickPlacer.hartaBricksObiecte[i][j].solidArea.contains(pointLeft)) {
//
//                        ball.setXDir(1);
//                    }
//
//                    if (brickPlacer.hartaBricksObiecte[i][j].solidArea.contains(pointTop)) {
//
//                        ball.setYDir(1);
//                    } else if (brickPlacer.hartaBricksObiecte[i][j].solidArea.contains(pointBottom)) {
//
//                        ball.setYDir(-1);
//                    }
//
//                    brickPlacer.hartaBricksObiecte[i][j].setDestroyed(true);
//                }
//            }
//        }
//    }
}