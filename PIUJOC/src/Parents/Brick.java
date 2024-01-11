package Parents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Brick extends Entity {



    public abstract void getHit();
    public abstract void Destroy();
    public abstract void getBrickSkinImage();
    public abstract void update();
    public abstract boolean isDestroyed();
    public abstract void setDestroyed(boolean val);
    public abstract void draw(Graphics2D g2,int a,int b);

}
