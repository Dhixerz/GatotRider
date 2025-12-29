import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Finish_Labyrntine extends Actor
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
            if (currentWorld instanceof Labyrntine1) {
                ((Labyrntine1) currentWorld).stopMusic();
            }
            Greenfoot.setWorld(new BossFight1()); // Pindah ke BossFight1 jika menyentuh Finish_Labyrntine
        }
    }
}
