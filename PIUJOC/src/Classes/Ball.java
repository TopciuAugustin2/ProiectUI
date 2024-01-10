package Classes;

import Parents.Entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;
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

    boolean flag = false;

    public void update() {
        if (flag) {
            direction.y += speedY;
            solidArea.y += speedY;
        } else {
            direction.x += speedX;
            direction.y += speedY;
            solidArea.x += speedX;
            solidArea.y += speedY;
        }

        if (solidArea.intersects(p.solidArea)) {
            // punctul de coliziune al bilei cu playerul
            double paddleCenterX = p.solidArea.getCenterX();

            Rectangle lefSideOfPaddle =
                    new Rectangle((int) p.solidArea.getX(), (int) p.solidArea.getY(), (int) ((p.solidArea.getWidth() / 2) - (solidArea.getWidth() / 2)), (int) p.solidArea.getHeight());

            Rectangle rightSideOfPaddle = new Rectangle((int) (paddleCenterX + (solidArea.getWidth() / 2)), (int) p.solidArea.getY(), (int) ((p.solidArea.getWidth() / 2) - (solidArea.getWidth() / 2)),
                    (int) p.solidArea.getHeight());

            Rectangle centerSideOfPaddle = new Rectangle((int) (paddleCenterX - solidArea.getWidth() / 8), (int) p.solidArea.getY(), (int) solidArea.getWidth() / 4, (int) p.solidArea.getHeight());

            if (solidArea.intersects(centerSideOfPaddle)) {
                flag = true;
                currentDirection = "straight";
            } else if (currentDirection.equals("straight") && solidArea.intersects(rightSideOfPaddle)) {
                currentDirection = "right";
                if (speedX < 0) {
                    speedX = -speedX;
                }
                flag = false;
            } else if (currentDirection.equals("straight") && solidArea.intersects(lefSideOfPaddle)) {
                currentDirection = "left";
                if (speedX > 0) {
                    speedX = -speedX;
                }
                flag = false;
            } else if (currentDirection.equals("right") && solidArea.intersects(lefSideOfPaddle)) {
                double v = (p.solidArea.getWidth() - Math.abs(solidArea.x - paddleCenterX)) / 50;
                speedY += v;
                reverseXDirection();
            } else if (currentDirection.equals("left") && solidArea.intersects(rightSideOfPaddle)) {
                reverseXDirection();
                double v = (p.solidArea.getWidth() - Math.abs(solidArea.x - paddleCenterX)) / 50;
                speedY += v;
            } else {
                if (speedY < 0) {
                    speedY = -6;
                } else {
                    speedY = 6;
                }
            }
            solidArea.x = (int) direction.x;
            solidArea.y = (int) direction.y;

            speedY = -speedY;
        }

        if (gp.leftWall.solidArea.intersects(solidArea)) {
            resetAngle();
            reverseXDirection();
        }
        if (gp.rightWall.solidArea.intersects(solidArea)) {
            reverseXDirection();
            resetAngle();
        }
        if (gp.topWall.solidArea.intersects(solidArea)) {
            reverseYDirection();
        }

//        if (solidArea.y >= gp.screenHeight + 10) {
//            gp.gameState = gp.gameOverState;
//
//        }
    }

    private void changeAngleSlightly(int maxAngle) {
        if (speedY > 0) {
            int n = new Random().nextInt(2);
            int n2 = new Random().nextInt(maxAngle) + 2;
            if (n == 0) {
                speedY += n2;
            } else {
                speedY -= n2;
            }
        }
    }

    private void resetAngle() {
        if (speedY < 0) {
            speedY = -6;
        } else {
            speedY = 6;
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
