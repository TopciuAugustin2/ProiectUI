import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Ball extends Entity{

    GamePanel gp;
    Player p;


    private int dirX = -1;
    private int dirY = -2;

    public Ball(GamePanel GP,Player player)
    {
        this.p=player;
        this.gp=GP;
        this.speed=3;
        this.x=100;
        this.y=100;
        solidArea = new Rectangle (100,100,30,30);
        getBallImage();

    }



    public void getBallImage()
    {
        try{
            skin = ImageIO.read(getClass().getResourceAsStream("ball.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(solidArea.intersects(p.solidArea)){
            dirY = -dirY;
            dirX+=dirX;
        }
        solidArea.x+=dirY;
        solidArea.y+=dirY;
        x+=dirY;
        y+=dirY;

        if(x < 0)
        {
            solidArea.x=-dirX;
            x= - dirX;
        }
        if(y < 0)
        {
            solidArea.y=-dirY;
            dirY= -dirY;
        }
        if(x > 670)
        {
            solidArea.x=-dirX;
            dirX= -dirX;
        }




    }
    public void draw(Graphics2D g2)
    {
        g2.draw(solidArea);
        g2.drawImage(skin,x,y,30,30,null);
    }
}
