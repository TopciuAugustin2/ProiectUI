import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;


    public Player(GamePanel GP,KeyHandler KH)
    {
        this.gp=GP;
        this.keyH= KH;

        solidArea= new Rectangle(600,900,48*3,48-20);

        setDefaultValues();
        getPlayerImage();
    }


    public void getPlayerImage()
    {
        try{
            skin = ImageIO.read(getClass().getResourceAsStream("skin.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    public void setDefaultValues()
    {
        x=600;
        y=900;
        speed=10;
    }

    public void update()
    {
        if(keyH.leftPressed==true)
        {
            if(x<12)
            {

                x=12;
                solidArea.x=12;
            }else
            {

                x-=speed;
                solidArea.x-=speed;
            }

        }
        else if(keyH.rightPressed==true)
        {
            if(x>700)
            {

                x=700;
                solidArea.x=700;
            }else
            {

                x+=speed;
                solidArea.x+=speed;
            }

        }


    }
    public void draw(Graphics2D g2)
    {
        // g2.setColor(Color.white);
        // g2.fillRect(x,y,gp.tileSize,gp.tileSize);

        g2.draw(solidArea);
        g2.drawImage(skin,x,y,gp.tileSize*3,gp.tileSize-20,null);
    }
}