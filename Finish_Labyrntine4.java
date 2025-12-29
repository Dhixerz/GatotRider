import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Finish_Labyrntine4 extends Actor
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
            if (currentWorld instanceof Labyrntine4) {
                ((Labyrntine4) currentWorld).stopMusic();
            }
            Greenfoot.setWorld(new BossFight4()); // Pindah ke BossFight4 jika menyentuh Finish_Labyrntine
        }
    }
}
