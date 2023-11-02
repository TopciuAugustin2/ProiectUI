import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize =16;
    final int scale=3;
    final int tileSize = originalTileSize*scale;
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWidth=tileSize*maxScreenCol;
    final int screenHeight=tileSize*maxScreenRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;


    //FPS
    int FPS =60;

    //player position

    int playerX=350;
    int playerY=500;
    int playerSpeed=10;

    public void startGameThread()
    {
        gameThread=new Thread(this);
        gameThread.start();
    }
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

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
                System.out.println("FPS:"+drawCount);
                drawCount=0;
                timer=0;
            }

        }
    }


    public void update()
    {
        if(keyH.leftPressed==true)
        {
            if(playerX<10)
            {
                playerX=10;
            }else
            {
                playerX-=playerSpeed;
            }

        }
        else if(keyH.rightPressed==true)
        {
            if(playerX>=700)
            {
                playerX=700;
            }else
            {
                playerX+=playerSpeed;
            }

        }

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);

        g2.dispose();

    }
}
