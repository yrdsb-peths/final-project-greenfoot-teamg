import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Bullet extends Actor implements Freezable
{
    SimpleTimer moveTimer = new SimpleTimer();
    
    /**
     * Moves bullet and checks if out of bounds if game is not frozen
     */
    public void act()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            moveBullet();
            checkBounds();
        }
    }
    
    public abstract void moveBullet();

    /**
     * Makes sure the bullets are removed if touching borders of game
     */ 
    public void checkBounds() {
        Game game = (Game) getWorld();
        if(getX() <= 0 || getY() <= 0 || getX() >= game.getWidth() - 1 || getY() >= game.getHeight() - 1) {
            game.removeObject(this);
        }
    }
    
    /**
     * Freezes the move timer
     */
    public void freeze()
    {
        moveTimer.freeze();
    }
    
    /**
     * Unfreezes the move timer
     */
    public void unfreeze()
    {
        moveTimer.unfreeze();
    }
}