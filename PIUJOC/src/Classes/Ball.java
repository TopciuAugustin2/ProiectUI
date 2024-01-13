package Classes;

import Parents.Entity;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball extends Entity {

    GamePanel gp;
    Player p;

    int xdir;
    int ydir;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public Ball(GamePanel GP) {
        this.gp = GP;
        this.speed=5;
        this.xdir = 5;
        this.ydir = -5;
        this.dmg = 1;

        getBallImage();
        getImageDimensions();
        resetState();
    }


    public void getBallImage() {
        try {
            skin = ImageIO.read(getClass().getResourceAsStream("/Classes/ball1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Rectangle getRect() {

        return new Rectangle(x, y, skin.getWidth()*2, skin.getHeight()*2);
    }

    public void update() {

        x += xdir;

        y += ydir;
    }


    public void draw(Graphics2D g2) {

        g2.drawImage(skin,x, y, skin.getWidth()*2, skin.getHeight()*2, null);

    }

    private void resetState() {

        x = (int)(screenSize.getWidth()/2);
        y = (int)(screenSize.getHeight()*3/4);
    }

    void setXDir(int x) {

        xdir = x;
    }

    void setYDir(int y) {

        ydir = y;
    }

    int getYDir() {

        return ydir;
    }
}
