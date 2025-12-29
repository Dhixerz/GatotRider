import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class NewGame extends Actor
{
    /**
     * Act - do whatever the NewGame wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            // Akses world saat ini dan hentikan lagu
            World currentWorld = getWorld();
            if (currentWorld instanceof selectStage) {
                ((selectStage) currentWorld).stopMusic();
            }

            // Berpindah ke world Level1
            Greenfoot.setWorld(new Level1());
        }
    }
}
