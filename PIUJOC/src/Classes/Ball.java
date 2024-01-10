package Classes;

import Parents.Entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball extends Entity {


    GamePanel gp;
    Player p;
    Vector2D direction;
    public double speedX;
    public double speedY;
    Rectangle collider;

    private String currentDirection = "right";


    //constructor -- initializare prop minge/mingi
    public Ball(GamePanel GP, Player player) {
        this.p = player;
        this.gp = GP;
        this.speed = 3 * 2;
        this.speedX = speed;
        this.speedY = speed;
        this.direction = new Vector2D(300, 200);
        solidArea = new Rectangle(300, 200, 25, 25);
        getBallImage();
        this.dmg = 1;
        this.collider = new Rectangle(0, p.y - 20, gp.screenSize.width, 1);

    }


    public void getBallImage() {
        try {
            skin = ImageIO.read(getClass().getResourceAsStream("/Classes/ball1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setDirection(Vector2D newDirection) {
        direction = newDirection;
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void update() {
        direction.x += speedX;
        direction.y += speedY;

        solidArea.x += speedX;
        solidArea.y += speedY;

        if (solidArea.intersects(p.solidArea)) {
            // punctul de coliziune al bilei cu playerul
            double paddleCenterX = p.solidArea.getCenterX();

            //diferenta de centru player si hit point


            if (solidArea.x < paddleCenterX) {
                speedY = -speedY;
            } else {
                speedY = -speedY;
            }

            Rectangle lefSideOfPaddle = new Rectangle((int) p.solidArea.getX()
                    , (int) p.solidArea.getY(),
                    (int) (p.solidArea.getWidth() / 2 - p.solidArea.height),
                    (int) p.solidArea.getHeight());

            Rectangle rightSideOfPaddle = new Rectangle((int) (p.solidArea.getX() + (p.solidArea.getWidth() / 2 + p.solidArea.height))
                    , (int) p.solidArea.getY(),
                    (int) (p.solidArea.getWidth() / 2 - p.solidArea.height),
                    (int) p.solidArea.getHeight());
            if (currentDirection.equals("right") && solidArea.intersects(lefSideOfPaddle)) {
                reverseXDirection();
            } else if (currentDirection.equals("left") && solidArea.intersects(rightSideOfPaddle)) {
                reverseXDirection();
            }
            solidArea.x = (int) direction.x;
            solidArea.y = (int) direction.y;
        }

        if (gp.leftWall.solidArea.intersects(solidArea)) {
            reverseXDirection();
        }
        if (gp.rightWall.solidArea.intersects(solidArea)) {
            reverseXDirection();
        }
        if (gp.topWall.solidArea.intersects(solidArea)) {
            reverseYDirection();
        }

        if (solidArea.y >= gp.screenHeight + 10) {
            gp.gameState = gp.gameOverState;

        }
    }

    public void reverseYDirection() {
        speedY = -speedY;
        solidArea.x = (int) direction.x;
        solidArea.y = (int) direction.y;
    }

    public void reverseXDirection() {
        speedX = -speedX;
        solidArea.x = (int) direction.x;
        solidArea.y = (int) direction.y;
        reverseCurrentDirection();
    }

    public void draw(Graphics2D g2) {
        g2.draw(solidArea);
        g2.draw(new Rectangle(0, 0, 30 * 16, 20 * 16));
        g2.drawImage(skin, (int) direction.getX(), (int) direction.getY(), 25, 25, null);
        g2.draw(collider);
    }

    private void reverseCurrentDirection() {
        if (currentDirection.equals("right")) {
            currentDirection = "left";
        } else {
            currentDirection = "right";
        }
    }
}