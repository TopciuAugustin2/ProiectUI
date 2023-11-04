public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel GP)
    {
        this.gp=GP;
    }
    public void checkCollisionPlayer()
    {

    }
    public void checkCollisionBall(Entity entity)
    {
        int ballLeftX=entity.solidArea.x;
        int ballRightX=entity.solidArea.x+entity.solidArea.width;
        int ballTopY=entity.solidArea.y;
        int ballBottomY=entity.solidArea.x+entity.solidArea.height;;

        int ballLeftCol = ballLeftX/gp.tileSize;
        int ballRightCol = ballRightX/gp.tileSize;
        int ballTopRow= ballTopY/gp.tileSize;
        int ballBottomRow=ballBottomY/gp.tileSize;

    }
}
