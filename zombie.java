import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class zombie extends Actor {
    private GreenfootImage[] images; 
    private int currentImageIndex = 0; 
    private long lastFrameTime = 0; 
    private int frameDelay = 200; 
    private int moveDistance = 8; 
    private boolean isTouchingGatot = false;
    private int health = 2; // 2 hit zombie ded

    public zombie() {
        images = new GreenfootImage[6];
        for (int i = 0; i < 6; i++) {
            images[i] = new GreenfootImage("zombie" + i + ".png");
            images[i].scale(90, 130); 
        }
        setImage(images[0]); 
        lastFrameTime = System.currentTimeMillis();
    }

    public void act() {
        if (getWorld() == null) return; 

        animateAndMove(); 

        if (getWorld() == null) return; 
        checkHitByAttack(); 

        if (getWorld() == null) return; 
        checkHitByGatot();
    }

    private void animateAndMove() {
        if (System.currentTimeMillis() - lastFrameTime >= frameDelay) {
            currentImageIndex = (currentImageIndex + 1) % images.length;
            setImage(images[currentImageIndex]);
            setLocation(getX() - moveDistance, getY());
            lastFrameTime = System.currentTimeMillis();
        }
    }

    private void checkHitByAttack() {
        if (getWorld() == null) return; 
        GatotAttackEffect attack = (GatotAttackEffect) getOneIntersectingObject(GatotAttackEffect.class);

        if (attack != null) {
            BaseWorld world = (BaseWorld) getWorld();

            if (world != null) { 
                world.getCounter().addScore(1); // +skor
                world.removeObject(attack); // hapus serangan

                health--; // kurangi nyawa zombie
                if (health <= 0) {
                    world.getCounter().addScore(1); // +Skor
                    world.removeObject(this); //  zombie hilang
                }
            }
        }
    }

    private void checkHitByGatot() {
        if (getWorld() == null) return; 
        Gatot_StandBy gatot = (Gatot_StandBy) getOneIntersectingObject(Gatot_StandBy.class);

        if (gatot != null && !isTouchingGatot) {
            isTouchingGatot = true; // zombie touch gatot
            BaseWorld world = (BaseWorld) getWorld();

            if (world != null) { // dunia masih ada sebelum mengakses HealthBar
                HealthBar healthBar = world.getHealthBar();
                healthBar.decreaseLife(); // -nyawa
            }
        } else if (gatot == null) {
            isTouchingGatot = false; // reset
        }
    }
}
