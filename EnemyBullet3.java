import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Large projectile splits into many small ones

public class EnemyBullet3 extends Bullet
{
    int inX, inY;
    int splitDis = 200; // Distance before splitting
    int spawnNum = 5; // Number of bullets that split off
    int spawnAngle = 180; // Angle the bullets make when splitting
    
    public EnemyBullet3() {
        GreenfootImage image = new GreenfootImage("EnemyBullet3.png");
        image.scale(20, 20);
        this.setImage(image);
        moveTimer.mark();
    }

    public void act()
    {
        super.act();
    }

    // Initial position when spawned
    public void setInitial(int x, int y) {
        inX = x;
        inY = y;
    }

    public void moveBullet() {
        if(moveTimer.millisElapsed() > 20) {
            move(10);
            moveTimer.mark();
            if(calcDistance(inX, inY) > splitDis) {
                splitBullets();
            }
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
            bullet.setRotation((int) Math.round(iRotate + (spawnAngle / spawnNum) * i)); // Rotate more for each bullet
        }

        game.removeObject(this);
    }
}