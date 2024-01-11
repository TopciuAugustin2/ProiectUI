package Classes;

import Parents.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public Player(GamePanel GP, KeyHandler KH)
    {
        this.gp=GP;
        this.keyH= KH;
        this.diameter=48*3;
        resetState();
        getPlayerImage();
        getImageDimensions();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter/3-20);
    }

    public void getPlayerImage()
    {
        try{
            skin = ImageIO.read(getClass().getResourceAsStream("/Classes/skin.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    public void update()
    {
        if(keyH.leftPressed)
        {
                x-=speed;
        }
        else if(keyH.rightPressed)
        {
                x+=speed;
        }


    }
    public void draw(Graphics2D g2)
    {
        //g2.drawImage(skin,x,y,gp.tileSize*3,gp.tileSize-20,null);
        g2.drawImage(skin, x, y,
                skin.getWidth()*9, skin.getHeight()*3-20, null);

    }
   public void resetState() {

        x=(int)screenSize.getWidth()/2;
        y=(int)screenSize.getHeight()-200;
        speed=10;
    }
}