import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameFont {
    public String fontFilePath = "/VerminVibesV-Zlg3.ttf";
    Font customFont = new Font("Your Custom Font", Font.PLAIN, 12);

    private void GameFont() {
        try {
            File fontFile = new File(fontFilePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}