package Parents;

import java.awt.*;
import java.awt.image.BufferedImage;

 public class Entity {

    public int x,y;
    public int dmg;
    public int hp;
    public int imageWidth;
    public int imageHeight;
    public int speed;
    public BufferedImage skin;
    public int diameter;
    public boolean destroyed;

     protected void setX(int x) {

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

     public Rectangle getRect() {

         return new Rectangle(x, y, skin.getWidth(), skin.getHeight());
     }


     public void getImageDimensions() {

         imageWidth = skin.getWidth();
         imageHeight = skin.getHeight();
     }






}