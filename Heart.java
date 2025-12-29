import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Heart extends Actor {
    public void act() {
        checkTouchByGatot();
    }

    private void checkTouchByGatot() {
        // detek sentuhan
        Gatot_StandBy gatot = (Gatot_StandBy) getOneIntersectingObject(Gatot_StandBy.class);

        if (gatot != null) {
            // akses dunia
            BaseWorld world = (BaseWorld) getWorld();
            HealthBar healthBar = world.getHealthBar();

            if (healthBar != null && healthBar.canIncreaseLife()) {
                // +health
                healthBar.increaseLife();
                world.removeObject(this); // remove health
            }
        }
    }
}
