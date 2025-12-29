import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class btnPlay extends Actor
{
    /**
     * Act - do whatever the btnPlay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Counter.setGlobalScore(0); // skor global reset jadi 0
            
            // Akses world saat ini dan hentikan lagu
            World currentWorld = getWorld();
            if (currentWorld instanceof MainMenu) {
                ((MainMenu) currentWorld).stopMusic();
            }

            // Berpindah ke world Level1
            Greenfoot.setWorld(new selectStage());
        }
    }
        
}
