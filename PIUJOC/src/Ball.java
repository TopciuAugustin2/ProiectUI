import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Ball extends Entity{

    GamePanel gp;
    Player p;
    int collision=0;


    private int dirX = -1;
    private int dirY = -2;

    public Ball(GamePanel GP,Player player)
    {
        this.p=player;
        this.gp=GP;
        this.speed=5000;
        this.x=100;
        this.y=100;
        solidArea = new Rectangle (100,100,25,25);
        getBallImage();

    }



    public void getBallImage()
    {
        try{
            skin = ImageIO.read(getClass().getResourceAsStream("ball1.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(solidArea.intersects(p.solidArea)){
            collision++;

            if(collision > 1)
            {
                dirY+=15;
            }
            dirY-=13;


            dirY=-dirY;

        }

        solidArea.x+=dirX;
        solidArea.y+=dirY;
        x+=dirX;
        y+=dirY;

        if(x < 0)
        {
           // solidArea.x=-dirX;
            dirX= - dirX;
        }
        if(y < 0)
        {
            //solidArea.y=-dirY;
            dirY= -dirY;
        }
        if(x > 670)
        {
            //solidArea.x=-dirX;
            dirX= -dirX;
        }




    }
    public void draw(Graphics2D g2)
    {
        g2.draw(solidArea);
        g2.drawImage(skin,x,y,25,25,null);
    }
}
