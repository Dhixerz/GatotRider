import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BaseWorld yang berfungsi untuk mengatur dunia dasar.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BaseWorld extends World {
    private Counter counter;       // Skor
    private HealthBar healthBar;   // Health Bar
    private Actor player;          // Pemain

        public BaseWorld(int width, int height, int worldWidth) {
            super(width, height, 1, false); // World tanpa wrap
            this.counter = new Counter();  // Inisialisasi skor
            addObject(counter, 1000, 50);
    
            this.healthBar = new HealthBar(); // Inisialisasi health bar
            addObject(healthBar, 180, 50);
        }

    protected void setPlayer(Actor player, int startX, int startY) {
        this.player = player;
        addObject(player, startX, startY);
    }

    protected Actor getPlayer() {
        return player;
    }

    public Counter getCounter() {
        return counter;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }
    
    protected void keepCounterSticky() {
        if (counter != null) {
            counter.setLocation(1000, 50);
        }
    }

    protected void keepHealthBarSticky() {
        if (healthBar != null) {
            healthBar.setLocation(180, 50);
        }
    }
}
