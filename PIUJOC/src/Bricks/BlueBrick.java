package Bricks;

import Classes.Ball;
import Parents.Brick;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BlueBrick extends Brick {

    Ball ball;
    Rectangle solidArea;
    public BlueBrick( Ball Ball)
    {
        this.destroyed=false;
        this.ball=Ball;
        this.hp=1;
        this.diameter=48*3;
        this.solidArea=new Rectangle(300, 300,diameter,diameter/3-20);
        getBrickSkinImage();
    }
    public void update()
    {
        if(ball.getRect().intersects(this.solidArea))
        {
            //System.out.println("hit");
            getHit();
        }
        //System.out.println("no hit");

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
        solidArea= new Rectangle(a, b,diameter,diameter/3-20);
        //g2.setColor(Color.RED);
        //g2.fillRect(solidArea.x, solidArea.y, diameter,diameter/3-20);
        g2.drawImage(skin,a,b,48*3,48-20,null);

    }
    @Override
    public boolean isDestroyed() {

        return destroyed;
    }
    @Override
    public void setDestroyed(boolean val) {

        destroyed = val;
    }

    @Override
    public void Destroy() {

        this.skin=null;
    }

    @Override
    public void getBrickSkinImage() {

            try{
                skin = ImageIO.read(getClass().getResourceAsStream("bluebrick.png"));
            }catch(IOException e)
            {
                e.printStackTrace();
            }

    }
}
