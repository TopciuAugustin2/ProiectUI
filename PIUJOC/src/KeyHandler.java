import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean leftPressed,rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();


        if(key==KeyEvent.VK_LEFT)
        {
            leftPressed=true;
        }
        if(key==KeyEvent.VK_RIGHT)
        {
            rightPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if(key==KeyEvent.VK_LEFT)
        {
            leftPressed=false;
        }
        if(key==KeyEvent.VK_RIGHT)
        {
            rightPressed=false;
        }
    }
}