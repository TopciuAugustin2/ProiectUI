package Classes;

import Parents.Entity;

import java.awt.*;

public class Wall extends Entity {

    public int height;
    public int width;

    public Wall(int X,int Y,int Width,int Height)
    {
        this.x=X;
        this.y=Y;
        this.height=Height;
        this.width=Width;
        this.solidArea= new Rectangle(x,y,width,height);
    }

    public void draw(Graphics2D g2)
    {
        g2.draw(solidArea);
        g2.fillRect(solidArea.x,solidArea.y, solidArea.width,solidArea.height);
    }
}
