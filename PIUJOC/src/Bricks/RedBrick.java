package Bricks;

import Classes.Ball;
import Parents.Brick;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class RedBrick extends Brick {

    Ball ball;
    Rectangle solidArea;
    public RedBrick(Ball Ball)
    {
        this.ball=Ball;
        this.hp=1;
        this.diameter=48*3;
        this.solidArea= new Rectangle(500, 500,diameter,diameter/3-20);
        getBrickSkinImage();
        this.destroyed=false;
    }
    public void update()
    {
        if(ball.getRect().intersects(this.solidArea))
        {
            getHit();
        }
    }
    @Override
    public void getHit() {

        this.hp-=ball.dmg;

        if(hp<=0)
        {
            Destroy();
        }

    }
    @Override
    public void draw(Graphics2D g2,int a,int b)
    {
        g2.draw(solidArea);
        g2.drawImage(skin,a,b,48*3,48-20,null);
    }


    @Override
    public void Destroy() {

        this.skin=null;
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
    @Override
    public boolean isDestroyed() {

        return destroyed;
    }
    @Override
    public void setDestroyed(boolean val) {

        destroyed = val;
    }
}
