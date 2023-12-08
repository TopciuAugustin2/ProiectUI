package Bricks;

import Parents.Brick;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class RedBrick extends Brick {

    public RedBrick()
    {
        this.hp=5;
    }
    @Override
    public void getHit() {
        //hp=hp-p.dmg;

        //if(hp==0)
//        {
//            Destroy();
//        }


    }

    @Override
    public void Destroy() {

    }

    @Override
    public void getBrickSkinImage() {
        try{
            skin = ImageIO.read(getClass().getResourceAsStream("redbrick.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {
        g2.draw(solidArea);

    }
}
