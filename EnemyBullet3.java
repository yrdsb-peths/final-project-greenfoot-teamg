import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Large projectile splits into many small ones

public class EnemyBullet3 extends Bullet
{
    int inX, inY;
    int splitDis = 200; // Distance before splitting
    int spawnNum = 5; // Number of bullets that split off
    int spawnAngle = 180; // Angle the bullets make when splitting
    
    public EnemyBullet3(int x, int y) {
        GreenfootImage image = new GreenfootImage("EnemyBullet3.png");
        image.scale(20, 20);
        this.setImage(image);
        inX = x;
        inY = y;
        moveTimer.mark();
    }

    public void act()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            moveBullet();
        }
    }

    public void moveBullet() {
        if(moveTimer.millisElapsed() > 20) {
            move(10);
            moveTimer.mark();
            if(calcDistance(inX, inY) > splitDis) {
                splitBullets();
            }
            else
            {
                checkBounds();
            }
        }
    }

    public void checkBounds() {
        Game game = (Game) getWorld();
        if(getX() <= 0 || getY() <= 0 || getX() >= game.getWidth() - 1 || getY() >= game.getHeight() - 1) {
            game.removeObject(this);
        }
    }
    
    // Current distance to initial
    public int calcDistance(int x, int y) {
        int dx = Math.abs(getX() - x);
        int dy = Math.abs(getY() - y);

        return (int) Math.round(Math.sqrt(dx * dx + dy * dy));
    }

    public void splitBullets() {
        Game game = (Game) getWorld();
        int iRotate = getRotation() - spawnAngle / 2; // First bullet's rotation

        // Make each bullet that splits off
        for(int i = 0; i < spawnNum; i++) {
            EnemyBullet0 bullet = new EnemyBullet0();
            GreenfootImage bulletImage = new GreenfootImage("EnemyBullet3.png"); // Set colour
            bulletImage.scale(10, 10);
            bullet.setImage(bulletImage);

            game.addObject(bullet, getX(), getY());

            bullet.setLocation(getX(), getY());
            bullet.setRotation((int) Math.round(iRotate + (spawnAngle / (spawnNum - 1)) * i)); // Rotate more for each bullet
        }

        game.removeObject(this);
    }
}