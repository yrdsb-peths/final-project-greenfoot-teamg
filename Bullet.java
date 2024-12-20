import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Bullet extends Actor
{
    public abstract void moveBullet();

    // Makes sure the bullets are removed if touching borders of game
    public void checkBounds() {
        Game game = (Game) getWorld();
        if(getX() <= 0 || getY() <= 0 || getX() >= game.getWidth() - 1 || getY() >= game.getHeight() - 1) {
            game.removeObject(this);
        }
    }
}
