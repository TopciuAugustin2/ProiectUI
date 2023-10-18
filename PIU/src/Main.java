import javax.swing.*;
public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        Gameplay game = new Gameplay();
        frame.setBounds(10,10,700,600);
        frame.setTitle("Game");``
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
    }
}