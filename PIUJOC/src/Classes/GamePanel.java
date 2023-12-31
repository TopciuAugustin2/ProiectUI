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
    public CollisionChecker cChecker = new CollisionChecker(this);

    Wall leftWall,topWall,rightWall;



    Thread gameThread;
    Player player;
    Ball ball;
    BlueBrick brick1;

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
        this.ball = new Ball(this,player);
        this.brick1 = new BlueBrick(this.ball);

        this.rightWall=new Wall((int)screenSize.getWidth()-30,0,30,(int)screenSize.getHeight());
        this.leftWall=new Wall(0,0,30,(int)screenSize.getHeight());
        this.topWall=new Wall(0,0,(int)screenSize.getWidth(),30);

    }

    public void setupGame() {
        gameState = titleState;
    }

    public void restart() {
        player.setDefaultValues();
        this.setPreferredSize(new Dimension((int)screenSize.getWidth(),(int)screenSize.getHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.player = new Player(this,keyH);
        this.ball = new Ball(this,player);
        this.brick1 = new BlueBrick(this.ball);

        this.rightWall=new Wall((int)screenSize.getWidth()-30,0,30,(int)screenSize.getHeight());
        this.leftWall=new Wall(0,0,30,(int)screenSize.getHeight());
        this.topWall=new Wall(0,0,(int)screenSize.getWidth(),30);

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
        if(gameState==playState) {
            player.update();
            ball.update();
            brick1.update();
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
            g2.drawImage(bk,0,0,window.getWidth(),window.getHeight(),null);

            player.draw(g2);
            ball.draw(g2);

            topWall.draw(g2);
            leftWall.draw(g2);
            rightWall.draw(g2);


            if(brick1.hp>0)
            {

                brick1.draw(g2);

            }

            //UI
            ui.draw(g2);
        }

        g2.dispose();
    }
}