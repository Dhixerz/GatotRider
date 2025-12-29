import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Sign3 extends Actor
{
    /**
     * Act - do whatever the Sign wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkSignReached();
    }
    private void checkSignReached() {
        if (getOneIntersectingObject(Gatot_StandBy.class) != null) {
            World currentWorld = getWorld();
            if (currentWorld instanceof Level3) {
                ((Level3) currentWorld).stopMusic();
            }
            Greenfoot.setWorld(new FiLLiT3()); 
        }
    }
}
