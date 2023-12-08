package Bricks;

import Parents.Brick;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class YellowBrick extends Brick {


    public YellowBrick()
    {
        this.hp=1;
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
            skin = ImageIO.read(getClass().getResourceAsStream("yellowBrick.png"));
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
