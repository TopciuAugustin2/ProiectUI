import Classes.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Arkanoid");


        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        GamePanel gamePanel = new GamePanel(window);

        window.add(gamePanel);
        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();


    }
}