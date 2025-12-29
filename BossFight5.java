import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossFight1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossFight5 extends BaseWorld {  
    private GreenfootSound level5Song;
    public BossFight5() {    
        super(1280, 720, 1); 
        prepare();
        level5Song = new GreenfootSound("th06_13.mp3");
        level5Song.playLoop();
    }
    
    private void prepare() {
        int worldWidth = getWidth();  
        int tileWidth = 128;  
        int yPosition = 651; 

        for (int x = 0; x < worldWidth; x += tileWidth) {
            Tiles2 tile = new Tiles2();  
            addObject(tile, x + tileWidth / 2, yPosition);  
        }

        Gatot_StandBy gatot_StandBy = new Gatot_StandBy(90, 130);
        addObject(gatot_StandBy, 114, 480);
        gatot_StandBy.setLocation(111, 487);

        constrainEntityToWorldBounds(gatot_StandBy);
        
        //timer
        Timer timer = new Timer(); 
        addObject(timer, 1180, 50);  
        
        Crate1 crate1 = new Crate1();
        addObject(crate1, 360, 381);
        
        Crate1 crate12 = new Crate1();
        addObject(crate12, 900, 381);
        
        Crate1 crate13 = new Crate1();
        addObject(crate13, 633, 243);
        
        //add heart
        Heart heart1 = new Heart();
        heart1.getImage().scale(100, 100);
        addObject(heart1, 640, 150);
        
        //add boss
        BossMonster5 bossMonster5 = new BossMonster5();
        addObject(bossMonster5,1100,480);
    }

    public void act() {
        for (Actor actor : getObjects(Actor.class)) {
            constrainEntityToWorldBounds(actor);
        }
    }

    @Override
    public void stopped() {
    level5Song.stop(); // Menghentikan lagu ketika world tidak aktif
    }
    @Override
    public void started() {
    level5Song.playLoop(); // Memutar ulang lagu saat world aktif
    }
    public void stopMusic() {
    level5Song.stop();
    }
    
    private void constrainEntityToWorldBounds(Actor actor) {
        int worldWidth = getWidth();
        int worldHeight = getHeight();
        int x = actor.getX();
        int y = actor.getY();

        if (x < 0) {
            actor.setLocation(0, y);
        } else if (x >= worldWidth) {
            actor.setLocation(worldWidth - 1, y);
        }

        if (y < 0) {
            actor.setLocation(x, 0);
        } else if (y >= worldHeight) {
            actor.setLocation(x, worldHeight - 1);
        }
    }
}