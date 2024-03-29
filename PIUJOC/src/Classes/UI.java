package Classes;

import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished = false;
    public int commandNum = 0;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2)
    {
        if(gameFinished == true) {
            gp.gameState= gp.wonState;
            g2.setFont(arial_40);
            g2.setColor(Color.black);

            String text;
            int textLength;
            int x;
            int y;

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "You won!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize * 2);
            g2.drawString(text, x, y);
        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.black);
        }

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.black);

        //TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();

        }

        //WON STATE
        if(gp.gameState == gp.wonState)
        {
            drawWonState();
        }
    }

    public void drawGameOverScreen()
    {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,screenSize.width,screenSize.width);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        text = "Game Over";
        //Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y =gp.tileSize * 3;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x-4, y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y = y + gp.tileSize * 3;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-40, y);
        }

        //Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y = y + 60;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
    }

    public void drawTitleScreen()
    {
        g2.setColor(new Color(0,96,255));
        g2.fillRect(0,0,screenSize.width,screenSize.width);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Arkanoid";
        int x = getXforCenteredText(text);
        int y = (int)screenSize.getHeight() / 3;

        //SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "LEVEL 1";
        x = getXforCenteredText(text);
        y = y + gp.tileSize * 2;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LEVEL 2";
        x = getXforCenteredText(text);
        y = y + gp.tileSize * 2;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LEVEL 3";
        x = getXforCenteredText(text);
        y = y + gp.tileSize * 2;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);

        }

        text = "SETTINGS";
        x = getXforCenteredText(text);
        y = y + gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 3) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y = y + gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 4) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawWonState()
    {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,screenSize.width,screenSize.width);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        text = "You Won!";
        //Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y =gp.tileSize * 3;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x-4, y-4);


        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y = y + gp.tileSize * 3;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-40, y);
        }

        //Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y = y + 60;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-40, y);
        }


    }

    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = screenSize.width/2 - length/2;
        return x;
    }
}
