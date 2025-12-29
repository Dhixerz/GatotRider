import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GatotAttackEffect extends Actor {
    private boolean isFacingLeft; 
    private int speed = 8; 

    public GatotAttackEffect(boolean isFacingLeft) {
        this.isFacingLeft = isFacingLeft;

        GreenfootImage img = new GreenfootImage("GatotAttackEffect.png");
        if (isFacingLeft) {
            img.mirrorHorizontally();  
        }
        setImage(img);  

        getImage().scale(60, 50);
    }

    public void act() {
        if (isFacingLeft) {
            move(-speed);  
        } else {
            move(speed);  
        }

        if (getX() < 0 || getX() > getWorld().getWidth()) {
            getWorld().removeObject(this);
        }
    }
}