import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


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
    Player player = new Player(this,keyH);


    //FPS
    int FPS =60;

    //player position

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
       player.update();

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage bk;
        try {
             bk = ImageIO.read(getClass().getResourceAsStream("/backgroud.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(bk,100,100,500,500,null);
        player.draw(g2);

        g2.dispose();

    }
}
