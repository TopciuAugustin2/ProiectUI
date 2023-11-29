import java.awt.*;
public class CollisionChecker {

    
    GamePanel gp;
    Ball ball;
    Player player;

    public CollisionChecker(GamePanel GP)
    {
        this.gp=GP;
    }
    public void checkCollisionPlayer()
    {

    }
    private void checkCollision() {
        if (ball.getBounds().intersects(player.getBounds())) {
            ball.reverseYDirection();
            //handleCollision(ball);
        }
    }
}
