import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Finish_Labyrntine2 extends Actor
{
    /**
     * Act - do whatever the Finish_Labyrntine wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkCollision();
    }
    private void checkCollision() {
        if (isTouching(Gatot_Labyrntine.class)) {
            World currentWorld = getWorld();
            if (currentWorld instanceof Labyrntine2) {
                ((Labyrntine2) currentWorld).stopMusic();
            }
            Greenfoot.setWorld(new BossFight2()); // Pindah ke BossFight2 jika menyentuh Finish_Labyrntine
        }
    }
}
