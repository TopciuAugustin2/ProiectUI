import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Ball extends Entity{

    GamePanel gp;
    Player p;
    Vector2D direction;
    public double speedX;
    public double speedY;



    //constructor -- initializare prop minge/mingi
    public Ball(GamePanel GP,Player player)
    {
        this.p=player;
        this.gp=GP;
        this.speed=3;
        this.speedX = speed;
        this.speedY = speed;
        this.direction = new Vector2D(300,200);
        solidArea = new Rectangle (300,200,25,25);
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

        solidArea.x+=speedX;
        solidArea.y+=speedY;

        System.out.println("X:" +direction.x+ " Y:" +direction.y);
        if(solidArea.intersects(p.solidArea)) {


            //speed=-speed;
            // punctul de coliziune al bilei cu playerul

            double hitPointX = solidArea.intersection(p.solidArea).getX();

            System.out.println("******Intersection" + solidArea.intersection(p.solidArea).getY());

            double paddleCenterX = p.solidArea.getCenterX();

            //diferenta de centru player si hit point

            double dif = paddleCenterX - hitPointX;

            Vector2D reflectionVector;

            if (solidArea.x < paddleCenterX) {
                reflectionVector = new Vector2D(direction.getX(), direction.getY()-2);
                speedY = -speedY;
            } else {
                reflectionVector = new Vector2D(direction.getX(), direction.getY()-2);
                speedY = -speedY;
            }

            setDirection(reflectionVector);

            solidArea.x=(int)direction.x;
            solidArea.y=(int)direction.y;
        }

        if (direction.x < 0 || direction.x > 1400) {
            //reverseXDirection();
            speedX = -speedX;
            solidArea.x=(int)direction.x;
            solidArea.y=(int)direction.y;
        }

        if (direction.y < 0 || direction.y > 900) {
            //reverseYDirection();
            speedY = -speedY;
            solidArea.x=(int)direction.x;
            solidArea.y=(int)direction.y;
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
        g2.drawImage(skin,(int)direction.getX(),(int)direction.getY(),25,25,null);
    }
}
