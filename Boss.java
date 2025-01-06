import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Boss extends Actor
{
    SimpleTimer moveTimer = new SimpleTimer();
    SimpleTimer attackCooldown = new SimpleTimer();
    SimpleTimer attackTimer = new SimpleTimer();
    SimpleTimer attackSlower = new SimpleTimer();
    public boolean isMoving;
    public boolean isAttacking;
    public boolean isAtSpot = true;
    boolean isSetup = false;
    boolean forceMove = false;
    boolean isBypassBoundries = false;
    int x = 300;
    int y = 100;
    int hp;
    int attackNumber;
    
    public Boss(int hp)
    {
        moveTimer.mark();
        attackCooldown.mark();
        this.hp = hp;
        turn(90);
    }
    
    public void act()
    {
        if(moveTimer.millisElapsed() > 10000 && !isAttacking)
        {
            changePosition();
        }
        moveToSpot();
        checkHp();
        if(attackCooldown.millisElapsed() > 4000 && isAttacking == false)
        {
            isAttacking = true;
            attackTimer.mark();
            randomAttack();
        }
        if(isAttacking)
        {
            resumeAttack();
        }
    }
    
    public void resumeAttack()
    {
        if(attackNumber == 1)
        {
            attack1();
        }
        else if(attackNumber == 2)
        {
            attack2();
        }
        else
        {
            attack3();
        }
    }
    
    public void randomAttack()
    {
        int chooseAttack = Util.randomInt(2);
        if(chooseAttack == 0)
        {
            attackNumber = 1;
            attack1();
        }
        else if(chooseAttack == 1)
        {
            attackNumber = 2;
            attack2();
        }
        else
        {
            attackNumber = 3;
            attack3();
        }
    }
    
    public void changePosition(){
        if(!isBypassBoundries)
        {
            x = Util.randomInt(501) + 50;
            y = Util.randomInt(301) + 50;
        }
        else
        {
            x = Util.randomInt(501) + 50;
            y = Util.randomInt(701) + 50;
        }
        moveTimer.mark();
    }
    
    public void moveToSpot(){
        if(!isAttacking || forceMove){
            int xDist = x - getX();
            int yDist = y - getY();
            this.setLocation(getX() + xDist/10, getY() + yDist/10);
        }
    }
    
    public void checkHp(){
        if(hp <= 0){
            World world = (World) getWorld();
            world.removeObject(this);
        }
    }
    
    public void endAttack()
    {
        isAttacking = false;
        isSetup = false;
        forceMove = false;
        if(getY() > 400)
        {
            changePosition();
        }
        attackCooldown.mark();
    }
    
    public abstract void attack1();

    public abstract void attack2();
    
    public abstract void attack3();
}