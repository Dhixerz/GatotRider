import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Coin extends Actor {
    public void act() {
        checkTouchByGatot();
    }

    private void checkTouchByGatot() {
        // yang bisa ambil gatotstandby & gatotlabyrntine
        Gatot_StandBy gatotStandBy = (Gatot_StandBy) getOneIntersectingObject(Gatot_StandBy.class);
        Gatot_Labyrntine gatotLabyrntine = (Gatot_Labyrntine) getOneIntersectingObject(Gatot_Labyrntine.class);
        
        if (gatotStandBy != null) {
            // Akses dunia dan counter
            BaseWorld world = (BaseWorld) getWorld();
            Counter counter = world.getCounter();

            if (counter != null) {
                counter.addScore(3); // 3 skor untuk Gatot_StandBy
            }

            world.removeObject(this); // Hapus objek koin klo disentuh
        } else if (gatotLabyrntine != null) {
            // Akses dunia dan counter
            BaseWorld world = (BaseWorld) getWorld();
            Counter counter = world.getCounter();

            if (counter != null) {
                counter.addScore(2); // 2 skor untuk Gatot_Labyrintine
            }

            world.removeObject(this); // Hapus objek koin klo disentuh
        }
    }
}
