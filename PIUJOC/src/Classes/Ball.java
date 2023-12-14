package Classes;

import Parents.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Ball extends Entity {


    GamePanel gp;
    Player p;
    Vector2D direction;
    public double speedX;
    public double speedY;
    Rectangle collider;


    //constructor -- initializare prop minge/mingi
    public Ball(GamePanel GP,Player player)
    {
        this.p=player;
        this.gp=GP;
        this.speed=3 * 2;
        this.speedX = speed;
        this.speedY = speed;
        this.direction = new Vector2D(300,200);
        solidArea = new Rectangle (300,200,25,25);
        getBallImage();
        this.dmg=1;
        this.collider = new Rectangle(0,p.y-20,gp.screenSize.width,1);

    }



    public void getBallImage()
    {
        try{
            skin = ImageIO.read(getClass().getResourceAsStream("/Classes/ball1.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }



    public void setDirection(Vector2D newDirection) {
        direction = newDirection;
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void update()
    {
        direction.x += speedX;
        direction.y += speedY;

        solidArea.x += speedX;
        solidArea.y += speedY;

        double playerIntersectsColliderX = solidArea.intersection(collider).getX();
        double playerIntersectsColliderY = solidArea.intersection(collider).getY();

        if(solidArea.intersects(p.solidArea)) {
            // punctul de coliziune al bilei cu playerul

            double hitPointX = solidArea.intersection(p.solidArea).getX();
            double hitPointY = solidArea.intersection(p.solidArea).getY();

            double paddleCenterX = p.solidArea.getCenterX();

            //diferenta de centru player si hit point

            double dif = paddleCenterX - hitPointX;

            Vector2D reflectionVector;

            Vector2D cornerLeft = new Vector2D(p.solidArea.getX(), p.solidArea.getY());
            Vector2D cornerRight = new Vector2D(p.solidArea.getX() + p.solidArea.width, p.solidArea.getY());

            Vector2D intersection = new Vector2D(playerIntersectsColliderX, playerIntersectsColliderY);

            double distanceLeft = direction.distance(cornerLeft);
            double distanceRight = direction.distance(cornerRight);

            if (solidArea.x < paddleCenterX) {
                speedY = -speedY;
            } else {
                speedY = -speedY;
            }

            solidArea.x=(int)direction.x;
            solidArea.y=(int)direction.y;
        }

        if (gp.leftWall.solidArea.intersects(solidArea)) {
            //reverseXDirection();
            speedX = -speedX;
            solidArea.x=(int)direction.x;
            solidArea.y=(int)direction.y;
        }
        if (gp.rightWall.solidArea.intersects(solidArea)) {
            //reverseYDirection();
            speedX = -speedX;
            solidArea.x=(int)direction.x;
            solidArea.y=(int)direction.y;
        }
        if (gp.topWall.solidArea.intersects(solidArea)) {
            //reverseYDirection();
            speedY = -speedY;
            solidArea.x=(int)direction.x;
            solidArea.y=(int)direction.y;
        }

        if(solidArea.y >= gp.screenHeight+10)
        {
            gp.gameState=gp.gameOverState;

        }


    }

    public void reverseYDirection() {
        direction = direction.reflectX();
        speedX = -speedX;
    }
    public void reverseXDirection() {
        direction = direction.reflectY();
        speedY = -speedY;
    }

    public void draw(Graphics2D g2)
    {
        g2.draw(solidArea);
        g2.draw(new Rectangle(0,0,30*16,20*16));
        g2.drawImage(skin,(int)direction.getX(),(int)direction.getY(),25,25,null);
        g2.draw(collider);
    }
}
