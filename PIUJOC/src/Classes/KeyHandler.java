package Classes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean leftPressed,rightPressed,upPressed,downPressed, enterPressed ;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.titleState) {
            if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if(key == KeyEvent.VK_S ||  key == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1) {
                    //add later
                }
                if(gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        if(key==KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
        {
            leftPressed=true;
        }
        if(key==KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
        {
            rightPressed=true;
        }

        if(key == KeyEvent.VK_P) {
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }

        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState) {
            if(key == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if(key == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    //aici e retry
                    gp.restart();
                    gp.gameState = gp.playState;
                }
                else if(gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.restart();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if(key==KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
        {
            leftPressed=false;
        }
        if(key==KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
        {
            rightPressed=false;
        }
        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
        {
            upPressed=false;
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
        {
            downPressed=false;
        }
    }
}