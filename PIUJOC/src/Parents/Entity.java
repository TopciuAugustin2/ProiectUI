package Parents;

import java.awt.*;
import java.awt.image.BufferedImage;

 public abstract class Entity {

    public int x,y;
    public int dmg;
    public int hp;
    public int imageWidth;
    public int imageHeight;
    public int speed;
    public BufferedImage skin;
    public int diameter;
    public boolean destroyed;
    public  int hp_basic;

    void setX(int x) {

         this.x = x;
     }

     public int getX() {

         return x;
     }

     protected void setY(int y) {

         this.y = y;
     }

     public int getY() {

         return y;
     }

     public int getImageWidth() {

         return imageWidth;
     }

     public int getImageHeight() {

         return imageHeight;
     }

     public Image getImage() {

         return skin;
     }


     public void getImageDimensions() {

         imageWidth = skin.getWidth();
         imageHeight = skin.getHeight();
     }


     public abstract Rectangle getRect();
 }