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
        this.xdir = 1;
        this.ydir = -1;
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

        if (x == 0) {

            setXDir(1);
        }

        if (x == screenSize.width - imageWidth) {

            setXDir(-1);
        }

        if (y == 0) {

            setYDir(1);
        }
    }


    public void draw(Graphics2D g2) {

        //g2.draw(new Rectangle(0, 0, 30 * 16, 20 * 16));
        //g2.drawImage(skin, (int) direction.getX(), (int) direction.getY(), 25, 25, null);
        //System.out.println("x: "+ this.x);
         //System.out.println("y: "+this.y);
        //System.out.println(this.skin.getWidth());
        //System.out.println(this.skin.getWidth());

        g2.drawImage(skin,x, y, skin.getWidth()*2, skin.getHeight()*2, null);

//        System.out.println("x: "+ x);
//        System.out.println("y: "+ y);
//        System.out.println("skin width: "+skin.getWidth());
//        System.out.println("skin hei: "+skin.getHeight());

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
