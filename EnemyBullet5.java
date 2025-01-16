import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyBullet5 extends Bullet
{
    Boss boss;
    boolean isReturning = false;
    
    /**
     * Constructor for the return bullet
     */
    public EnemyBullet5(Boss boss) {
        GreenfootImage image = new GreenfootImage("EnemyBullet0.png");
        image.scale(10, 10);
        this.setImage(image);
        this.boss = boss;
        moveTimer.mark();
    }


    /**
     * If game is not frozen, move the bullet and check bounds if bullet is returning, automatically turn towards the boss
     */
    public void act()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            moveBullet();
            if(boss.getWorld() != null)
            {
                checkBounds();
                if(isReturning)
                {
                    turnTowards(boss.getX(), boss.getY());
                }
            }
            else
            {
                super.checkBounds();
            }
        }
    }

    /**
     * checks if bullet is touching the boundries
     */
    public void checkBounds() {
        Game game = (Game) getWorld();
        if(getX() <= 0 || getY() <= 0 || getX() >= game.getWidth() - 1 || getY() >= game.getHeight() - 1) {
            GreenfootImage image = new GreenfootImage("EnemyBullet5.png");
            image.scale(10, 10);
            this.setImage(image);
            isReturning = true;
        }
    }
    
    /**
     * Moves the bullet in a straight line.
     */
    public void moveBullet() {
        if(moveTimer.millisElapsed() > 20) {
            move(10);
            moveTimer.mark();
        }
    }
}
